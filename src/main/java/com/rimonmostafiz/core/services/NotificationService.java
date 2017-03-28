package com.rimonmostafiz.core.services;

import com.rimonmostafiz.core.model.FileInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.util.List;

/**
 * @author Rimon Mostafiz
 */
@Service
public class NotificationService {

    private JavaMailSender javaMailSender;

    @Autowired
    FileInfoService fileInfoService;

    @Autowired
    public NotificationService(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }

    @Scheduled(fixedDelay = 600000)
    public void sendNotification() throws MailException {
        try {
            MimeMessage message = javaMailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setSubject("noreply");
            //helper.setTo("qhfan@ucdavis.edu");
            helper.setTo("ferary.rimon@gmail.com");
            String mailBody = makeMailBody();
            helper.setText(mailBody, true);
            javaMailSender.send(message);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }

    private String makeMailBody() {
        List<FileInfo> fileInfoList = fileInfoService.findByStatus();
        String msg = "Dear User,\n" + "This is an automated mail form you File Upload Server.\n";
        for (FileInfo fileInfo : fileInfoList) {
            msg.concat("\n");
            msg += fileInfo.toString();
            fileInfo.setActive(true);
            fileInfoService.updateFileInfo(fileInfo);
        }
        return msg;
    }
}
