package com.example.leadautomation.controller;

import com.example.leadautomation.entity.Lead;
import com.example.leadautomation.service.LeadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/leads")
public class LeadController {

    @Autowired
    private LeadService leadService;

    // SAVE LEAD API
    @PostMapping
    public Lead saveLead(@RequestBody Lead lead) {
        return leadService.saveLead(lead);
    }
    // GET ALL LEADS API
    @GetMapping
    public java.util.List<Lead> getAllLeads() {
        return leadService.getAllLeads();
    }
}