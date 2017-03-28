package com.rimonmostafiz.core.controller;

import com.rimonmostafiz.component.logger.Loggable;
import com.rimonmostafiz.core.model.FileInfo;
import com.rimonmostafiz.core.services.FileInfoService;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;

/**
 * @author Rimon Mostafiz
 */
@RestController
public class FileDownloadController {

    @Loggable
    Logger logger;

    @Autowired
    FileInfoService fileInfoService;

    @RequestMapping(value = "/download/{id}", method = RequestMethod.GET)
    public void getFile(@PathVariable long id, HttpServletRequest request, HttpServletResponse response) {
        FileInfo fileInfo = fileInfoService.getFileInfo(id);
        ServletContext context = request.getServletContext();
        if (fileInfo != null) {
            try {
                File downloadFile = new File(fileInfo.getFileUploadPath());
                InputStream inputStream = new BufferedInputStream(new FileInputStream(downloadFile));
                // get MIME type of the file
                String mimeType = context.getMimeType(downloadFile.getAbsolutePath());
                if (mimeType == null) {
                    // set to binary type if MIME mapping not found
                    mimeType = "application/octet-stream";
                }
                logger.info("MIME type: " + mimeType);

                response.setContentType(mimeType);
                response.setContentLength((int) downloadFile.length());
                response.setHeader("Content-Disposition", "attachment;filename=\"" + fileInfo.getFileName() + "\"");
                IOUtils.copy(inputStream, response.getOutputStream());

            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            logger.info("NO FILE FOUND WITH ID : {}", id);
        }
    }

    @RequestMapping("/details/{filename}")
    public String getFileDetails(@PathVariable String filename) {
        logger.info("Searching for File Name : {}", filename);

        Long id = fileInfoService.getFileByName(filename);
        if (id != 0) {
            return "Id of the " + filename + " is : " + id;
        } else {
            return "No File of Name : " + filename;
        }
    }
}
