package com.module.app.web.repository.file;

import com.module.app.web.entity.file.UploadData;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FileUploadRepository extends CrudRepository<UploadData, Long> {
    Page<UploadData> findAll(Pageable pageable);
}
