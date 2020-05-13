package ru.itis.springbootrest.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.itis.springbootrest.models.FileInfo;

public interface FileInfoRepository extends JpaRepository<FileInfo, Long> {
    FileInfo findOneByStorageFileName(String storageFileName);
    FileInfo findOneById(Long fileId);
}
