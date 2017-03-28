package com.rimonmostafiz.core.controller;

import com.rimonmostafiz.component.logger.Loggable;
import com.rimonmostafiz.core.model.FileInfo;
import com.rimonmostafiz.core.services.FileInfoService;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

/**
 * @author Rimon Mostafiz
 */
@RestController
public class FileUploadController {

    private static final String SAVE_FILE_PATH = "/home/rimonmostafiz/file-upload";

    @Loggable
    Logger logger;

    @Autowired
    FileInfoService fileInfoService;

    @RequestMapping(value = "/upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE, method = RequestMethod.POST)
    public void uploadFile(@RequestParam("file") MultipartFile file) {
        if (!file.isEmpty()) {
            try {
                FileInfo fileInfo = new FileInfo();
                fileInfo.setOriginalFileName(file.getOriginalFilename());
                String[] splitFileName = file.getOriginalFilename().split("\\.");
                fileInfo.setFileName(splitFileName[0]);
                fileInfo.setFileSize(file.getSize());
                byte[] bytes = file.getBytes();
                String rootPath = SAVE_FILE_PATH;
                logger.info("Root Path : {}", rootPath);
                File dir = new File(rootPath);
                if (!dir.exists()) {
                    logger.info("Directory doesn't Exist!! Creating Directory....");
                    dir.mkdir();
                }
                File serverFile = new File(dir.getAbsolutePath() + File.separator + file.getOriginalFilename());
                BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(serverFile));
                stream.write(bytes);
                stream.close();
                logger.info("File Uploaded Successfully, File Location = {}", serverFile.getAbsolutePath());
                fileInfo.setFileUploadPath(serverFile.getAbsolutePath());
                logger.debug("File Content Type : {}", file.getContentType());
                fileInfo.setFileType(file.getContentType());
                fileInfoService.addFileInfo(fileInfo);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @RequestMapping("/upload/{id}")
    public FileInfo getFileInfo(@PathVariable long id) {
        return fileInfoService.getFileInfo(id);
    }

    @RequestMapping("/upload")
    public List<FileInfo> getAll() {
        return fileInfoService.getAllFileInfo();
    }
}
