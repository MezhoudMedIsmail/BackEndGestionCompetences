package org.example.backendamine.Service;

import org.example.backendamine.Entities.FileEntity;
import org.springframework.web.multipart.MultipartFile;

public interface FileService {
    FileEntity storeFile(MultipartFile file, long userId);
    FileEntity getFile(long userId);
}
