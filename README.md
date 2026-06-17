# Lead Automation System

## Overview

Lead Automation System is a Spring Boot-based backend application designed to automate the lead intake and qualification workflow.

The system captures prospect information, stores lead details in a MySQL database, generates AI-powered company summaries using the Mistral AI API, and supports automated email and document-processing workflows.

The goal of this project is to reduce manual effort in lead management and create a personalized first interaction for potential clients.

---

# Problem Statement

Many organizations rely on lead forms to collect prospect information. After submission, teams often spend significant time researching companies, creating reports, and preparing outreach emails.

This project aims to automate that workflow by:

* Capturing lead information
* Storing and managing lead records
* Generating AI-powered company insights
* Supporting automated email workflows
* Processing PDF documents automatically
* Reducing manual lead qualification effort

---

# Features

## Lead Management

* Create new leads
* Store lead information in MySQL
* Retrieve all submitted leads
* Manage lead data through REST APIs

### Lead Information Captured

* Name
* Email
* Company Name
* Website
* AI Generated Company Summary

---

## AI-Powered Company Profiling

The system integrates with the Mistral AI API to generate contextual company summaries.

### AI Workflow

1. User submits company details.
2. Company name and website are sent to Mistral AI.
3. AI generates a business summary.
4. Generated summary is stored in the database.
5. Summary can be used for personalized outreach and reporting.

### Reliability Features

* API exception handling
* Fallback response generation
* Graceful degradation during AI service failures

---

## Email Automation

The application supports Gmail integration for workflow automation.

### Features

* Gmail SMTP configuration
* Gmail IMAP integration
* Automated inbox monitoring
* Email processing workflow support

---

## Automated PDF Processing

The system periodically checks email inboxes for PDF attachments.

### Features

* Automatic inbox scanning
* PDF attachment detection
* Automatic PDF download
* Duplicate file protection
* Local document storage

### Automation

Spring Scheduler is used to execute background tasks automatically.

Current implementation checks for new emails every 60 seconds.

---

# Technology Stack

## Backend

* Java 21
* Spring Boot
* Spring MVC
* REST APIs

## Database

* MySQL
* Spring Data JPA
* Hibernate

## AI Integration

* Mistral AI API

## Email Integration

* Java Mail API
* Gmail SMTP
* Gmail IMAP

## Build & Development Tools

* Maven
* Git
* GitHub
* Lombok

---

# Architecture

User Request
│
▼
REST Controller Layer
│
▼
Service Layer
│
┌────┴───────────┐
▼                ▼
Mistral AI      Email Module
Integration     (SMTP/IMAP)
│                │
└────┬───────────┘
▼
Repository Layer
│
▼
MySQL Database

---

# Project Structure

src/main/java

├── controller

├── service

├── entity

├── repository

├── config

└── exception

---

# API Endpoints

## Save Lead

POST /api/leads

### Request Body

```json
{
  "name": "John Doe",
  "email": "john@example.com",
  "companyName": "ABC Technologies",
  "website": "https://abc.com"
}
```

### Response

Lead information along with generated AI summary.

---

## Get All Leads

GET /api/leads

Returns all stored lead records.

---

## Health Check

GET /

Response:

```text
Server is working
```

---

GET /test

Response:

```text
API Working
```

---

# Database Schema

Table: leads

| Column      | Type   |
| ----------- | ------ |
| id          | Long   |
| name        | String |
| email       | String |
| companyName | String |
| website     | String |
| aiSummary   | String |

---

# Current Workflow

1. Prospect submits lead information.
2. Lead data is received through REST API.
3. Company details are processed.
4. Mistral AI generates company summary.
5. Lead information is stored in MySQL.
6. Email workflow services are triggered.
7. PDF processing services monitor incoming documents.

---

# Challenges Faced

* Public company information availability varies significantly.
* AI-generated content quality depends on provided company information.
* Email integration requires secure authentication and configuration.
* PDF generation and formatting require further refinement.
* External APIs may occasionally fail or return incomplete responses.

---

# Current Status

## Completed

* Lead Capture APIs
* Spring Boot Backend
* MySQL Integration
* Spring Data JPA Integration
* Mistral AI Integration
* AI Summary Generation
* Email Configuration
* Gmail IMAP Integration
* Automated Inbox Monitoring
* Scheduled Background Jobs
* PDF Detection and Download
* Exception Handling

## In Progress

* Dynamic PDF Report Generation
* Automated Report Email Attachments
* Advanced Company Data Enrichment

---

# Future Enhancements

* Dynamic PDF Audit Report Generation
* Google Sheets Lead Logging
* Google Drive PDF Archiving
* CRM Integration
* Lead Scoring System
* Analytics Dashboard
* Advanced AI-Based Business Insights

---

# Learning Outcomes

Through this project, I gained practical experience in:

* Spring Boot Application Development
* REST API Design
* MySQL Database Integration
* Spring Data JPA
* AI API Integration
* Gmail SMTP & IMAP Integration
* Background Task Scheduling
* File Processing
* Exception Handling
* Backend System Design

---

# Author

Kumkum Kumari

B.Tech Computer Science & Engineering

Java | Spring Boot | MySQL | REST APIs | Backend Development
