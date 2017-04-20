package com.rimonmostafiz.core.services;

import com.rimonmostafiz.core.model.FileInfo;
import com.rimonmostafiz.core.repositories.FileInfoRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Rimon Mostafiz
 */
@Service
public class FileInfoService {

    @Autowired
    private FileInfoRepo fileInfoRepo;

    public void addFileInfo(FileInfo fileInfo) {
        fileInfoRepo.save(fileInfo);
    }

    public FileInfo getFileInfo(long id) {
        return fileInfoRepo.findOne(id);
    }

    public Long getFileByName(String filename) {
        FileInfo info = fileInfoRepo.findByFileName(filename);
        if (info != null) {
            return info.getId();
        } else {
            return 0L;
        }
    }

    public List<FileInfo> getAllFileInfo() {
        return (List<FileInfo>) fileInfoRepo.findAll();
    }

    public List<FileInfo> findByStatus() {
        return fileInfoRepo.findByActive(false);
    }

    public void updateFileInfo(FileInfo fileInfo) {
        fileInfoRepo.save(fileInfo);
    }

    public FileInfo isFileAlreadyExist(String fileName) {
        List<FileInfo> filesList = getAllFileInfo();
        if (filesList != null && filesList.size() > 0) {
            for (FileInfo file : filesList) {
                if (file.getOriginalFileName().equals(fileName)) {
                    // setting status false for include this files info in mail
                    file.setActive(false);
                    return file;
                }
            }
        }
        return null;
    }



}
