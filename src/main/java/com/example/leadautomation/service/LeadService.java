package com.example.leadautomation.service;

import com.example.leadautomation.entity.Lead;
import com.example.leadautomation.repository.LeadRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import jakarta.mail.internet.MimeMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import java.util.List;
@Service
public class LeadService {
    @Autowired
    private LeadRepository leadRepository;
    @Autowired
    private MistralService mistralService;
    @Autowired(required = false)
    private JavaMailSender mailSender;
    @Value("${spring.mail.username}")
    private String adminEmail;
    public Lead saveLead(Lead lead) {
        String summary = mistralService.getCompanySummary(lead.getCompanyName(), lead.getWebsite());
        lead.setAiSummary(summary);
        Lead savedLead = leadRepository.save(lead);
        if (mailSender != null) {
            try {
                MimeMessage mimeMessage = mailSender.createMimeMessage();
                MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, false, "UTF-8");
                helper.setTo(adminEmail);
                helper.setSubject("Alert: New Lead Captured!");
                String emailText = "Hi Kumkum,\n\nA new lead has been successfully saved in the MySQL database:\n\n" +
                        "Name: " + savedLead.getName() + "\n" +
                        "Email: " + savedLead.getEmail() + "\n" +
                        "Company: " + savedLead.getCompanyName() + "\n" +
                        "Website: " + savedLead.getWebsite() + "\n" +
                        "AI Summary: " + savedLead.getAiSummary() + "\n\n" +
                        "Regards,\nLead Automation Bot";
                helper.setText(emailText);
                mailSender.send(mimeMessage);
                System.out.println("Simple Notification Email Sent Successfully!");
            } catch (Exception e) {
                System.err.println("Email sending failed: " + e.getMessage());
            }
        }
        return savedLead;
    }
    // Get All Leads
    public List<Lead> getAllLeads() {
        return leadRepository.findAll();
    }
}