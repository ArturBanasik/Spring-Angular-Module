package com.module.app.web.services.file;

import com.module.app.web.entity.file.UploadData;
import com.module.app.web.repository.file.FileUploadRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;

@Slf4j
@Service
@RequiredArgsConstructor
public class FileUploadService {
    private final HttpServletRequest request;
    private final FileUploadRepository fileUploadRepository;

    private static final String UPLOADS_DIRECTORY = "/upload-dir/";

    public UploadData saveAndReturnFile(MultipartFile file) throws IOException {
        String realativeUploadPath = request.getServletContext().getRealPath(UPLOADS_DIRECTORY);
        String filePath = realativeUploadPath + file.getOriginalFilename();
        File dest = new File(filePath);
        file.transferTo(dest);

        return fileUploadRepository.save(mapToUploadData(file));
    }

    private UploadData mapToUploadData(@RequestParam MultipartFile file) throws IOException {
        return UploadData.builder()
                .uploadedFilePath(processFile(file))
                .uploadedFileName(file.getOriginalFilename())
                .build();
    }

    private String processFile(MultipartFile file) throws IOException {
        if (!file.isEmpty()) {
            String realativeUploadPath = request.getServletContext().getRealPath(UPLOADS_DIRECTORY);
            if (!new File(realativeUploadPath).exists()) {
                new File(realativeUploadPath).mkdir();
            }
            log.info("realPathtoUploads = {}", realativeUploadPath);
//            https://www.baeldung.com/spring-classpath-file-access
            String orgName = file.getOriginalFilename();
            String filePath = realativeUploadPath + orgName;
            File dest = new File(filePath);
            file.transferTo(dest);
            return filePath;

        }
        return null;
    }

    public Page<UploadData> getAllDataPage(Pageable pageable) {
//        parse to DTO class? not return entity
        return fileUploadRepository.findAll(pageable);
    }
    //todo
    //https://stackoverflow.com/questions/10847994/spring-mvc-save-uploaded-multipartfile-to-specific-folder
}
