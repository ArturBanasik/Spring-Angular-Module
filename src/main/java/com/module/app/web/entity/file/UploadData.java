package com.module.app.web.entity.file;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.*;
import java.io.InputStream;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "upload_data")
public class UploadData {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "upload_data_id")
    private long id;

    @Column(name = "uploaded_file_name")
    private String uploadedFileName;

    @Column(name = "uploaded_file_path")
    private String uploadedFilePath;
}
