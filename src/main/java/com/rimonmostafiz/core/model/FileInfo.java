package com.rimonmostafiz.core.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * @author Rimon Mostafiz
 */
@Entity
public class FileInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String fileName;
    private String originalFileName;
    private long fileSize;
    private String fileUploadPath;
    private String fileType;
    private boolean active;
    private String userName;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getFileType() {
        return fileType;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public void setFileType(String fileType) {
        this.fileType = fileType;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFileUploadPath() {
        return fileUploadPath;
    }

    public void setFileUploadPath(String fileUploadPath) {
        this.fileUploadPath = fileUploadPath;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public long getFileSize() {
        return fileSize;
    }

    public void setFileSize(long fileSize) {
        this.fileSize = fileSize;
    }

    public String getOriginalFileName() {
        return originalFileName;
    }

    public void setOriginalFileName(String originalFileName) {
        this.originalFileName = originalFileName;
    }

    @Override
    public String toString() {
        return "FileInfo{" +
                "id=" + id +
                ", File Name ='" + fileName + '\'' +
                ", File Name With Extension ='" + originalFileName + '\'' +
                ", File Size =" + fileSize +
                ", File Upload Path ='" + fileUploadPath + '\'' +
                ", File Type ='" + fileType + '\'' +
                ", Status =" + active +
                ", User ='" + userName + '\'' +
                '}';
    }
}
