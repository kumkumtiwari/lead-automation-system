package com.example.leadautomation.service;

import jakarta.mail.*;
import jakarta.mail.internet.MimeBodyPart;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import java.io.File;
import java.util.Properties;

@Service
public class EmailReceiverService {

    @Value("${spring.mail.username}")
    private String username;

    @Value("${spring.mail.password}")
    private String password;

    @Value("${file.upload-dir}")
    private String downloadFolder;
    @Scheduled(fixedRate = 60000)
    public void checkNewEmails() {
        System.out.println(" Checking Gmail inbox for new PDF assessments...");
        try {
            Properties properties = new Properties();
            properties.put("mail.store.protocol", "imaps");
            properties.put("mail.imaps.host", "imap.gmail.com");
            properties.put("mail.imaps.port", "993");
            properties.put("mail.imaps.ssl.enable", "true");

            Session emailSession = Session.getInstance(properties);
            Store store = emailSession.getStore("imaps");
            store.connect("imap.gmail.com", username, password);

            Folder inbox = store.getFolder("INBOX");
            inbox.open(Folder.READ_ONLY);
            Message[] messages = inbox.getMessages();
            int start = Math.max(0, messages.length - 5);
            for (int i = messages.length - 1; i >= start; i--) {
                Message message = messages[i];
                if (message.isMimeType("multipart/*")) {
                    Multipart multipart = (Multipart) message.getContent();
                    for (int j = 0; j < multipart.getCount(); j++) {
                        BodyPart bodyPart = multipart.getBodyPart(j);
                        if (Part.ATTACHMENT.equalsIgnoreCase(bodyPart.getDisposition()) && bodyPart.getFileName() != null) {
                            String fileName = bodyPart.getFileName();
                            if (fileName.toLowerCase().endsWith(".pdf")) {
                                File folder = new File(downloadFolder);
                                if (!folder.exists()) folder.mkdirs();
                                MimeBodyPart mimeBodyPart = (MimeBodyPart) bodyPart;
                                String destinationPath = folder + File.separator + fileName;
                                File checkFile = new File(destinationPath);
                                if (!checkFile.exists()) {
                                    mimeBodyPart.saveFile(destinationPath);
                                    System.out.println(" PDF Successfully Downloaded: " + fileName);
                                }
                            }
                        }
                    }
                }
            }
            inbox.close(false);
            store.close();
        } catch (Exception e) {
            System.err.println(" IMAP Email Receiver Error: " + e.getMessage());
        }
    }
}