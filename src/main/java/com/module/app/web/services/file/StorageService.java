package com.module.app.web.services.file;

import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Path;
import java.util.stream.Stream;

@Service
public interface StorageService {
    void init() throws IOException;

    void store(MultipartFile file) throws IOException;

    Stream<Path> loadAll() throws IOException;

    Path load(String filename);

    Resource loadAsResource(String filename) throws MalformedURLException;

    void deleteAll() throws IOException;
}
