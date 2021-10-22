package com.module.app.web.controllers.file;

import com.module.app.web.entity.file.UploadData;
import com.module.app.web.exceptions.file.StorageFileNotFoundException;
import com.module.app.web.services.file.FileUploadService;
import com.module.app.web.services.file.StorageService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.Resource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;
import javax.validation.constraints.NotNull;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.stream.Collectors;

@Slf4j
@RestController
@CrossOrigin(origins = "*")
@RequestMapping(path = "/upload")
@RequiredArgsConstructor
public class FileUploadController {
    private final StorageService storageService;
    private final FileUploadService fileUploadService;
    private final HttpServletRequest request;

    @GetMapping("/")
    public String listUploadedFiles(Model model) throws IOException {
        model.addAttribute("files", storageService.loadAll().map(
                        path -> MvcUriComponentsBuilder.fromMethodName(FileUploadController.class,
                                "serveFile", path.getFileName().toString()).build().toUri().toString())
                .collect(Collectors.toList()));

        return "uploadForm";
    }

    @GetMapping("/files/{filename:.+}")
    @ResponseBody
    public ResponseEntity<Resource> serveFile(@PathVariable String filename) throws MalformedURLException {

        Resource file = storageService.loadAsResource(filename);
        return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION,
                "attachment; filename=\"" + file.getFilename() + "\"").body(file);
    }

    @PostMapping("/add")
    public ResponseEntity<UploadData> handleFileUpload(@RequestParam MultipartFile file) throws IOException {
        return new ResponseEntity(fileUploadService.saveAndReturnFile(file), HttpStatus.OK);

//        Old version
//        return new ResponseEntity(fileUploadRepository.save(mapToUploadData(file)), HttpStatus.OK);
    }

    @GetMapping("/get")
    public ResponseEntity<UploadData> getUploadedFile() {
//        move to service
//        return new ResponseEntity(fileUploadRepository.findById(2L), HttpStatus.OK);
        return null;
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("/get-all")
    public Page<UploadData> getAllUploadedFiles(@NotNull final Pageable pageable) {
        return fileUploadService.getAllDataPage(pageable);
    }

    //add to service
    private UploadData mapToUploadData(@RequestParam MultipartFile file) throws IOException {
        return UploadData.builder()
                .uploadedFilePath(processFile(file))
                .uploadedFileName(file.getOriginalFilename())
                .build();
    }

    private String processFile(MultipartFile file) {
        if (!file.isEmpty()) {
            try {
                String uploadsDir = "/uploads/";
                String realPathtoUploads = request.getServletContext().getRealPath(uploadsDir);
                if (!new File(realPathtoUploads).exists()) {
                    new File(realPathtoUploads).mkdir();
                }
                log.info("realPathtoUploads = {}", realPathtoUploads);
//
                String orgName = file.getOriginalFilename();
                String filePath = realPathtoUploads + orgName;
                File dest = new File(filePath);
                file.transferTo(dest);
                return filePath;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    @ExceptionHandler(StorageFileNotFoundException.class)
    public ResponseEntity<?> handleStorageFileNotFound(StorageFileNotFoundException exc) {
        return ResponseEntity.notFound().build();
    }
}
