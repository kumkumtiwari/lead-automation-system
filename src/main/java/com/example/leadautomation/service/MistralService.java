package com.example.leadautomation.service;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.*;
import java.util.*;

@Service
public class MistralService {
    @Value("${mistral.api.key}")
    private String API_KEY;
    private final String MISTRAL_API_URL = "https://api.mistral.ai/v1/chat/completions";

    public String getCompanySummary(String companyName, String website) {
        try {
            RestTemplate restTemplate = new RestTemplate();
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            headers.setBearerAuth(API_KEY.trim());
            String prompt = "Provide a short 1-line professional summary for the company: "
                    + companyName + " with website: " + website;
            Map<String, Object> message = new HashMap<>();
            message.put("role", "user");
            message.put("content", prompt);
            Map<String, Object> requestBody = new HashMap<>();
            requestBody.put("model", "mistral-small-latest");
            requestBody.put("messages", Collections.singletonList(message));
            HttpEntity<Map<String, Object>> entity = new HttpEntity<>(requestBody, headers);
            ResponseEntity<Map> response = restTemplate.postForEntity(MISTRAL_API_URL, entity, Map.class);
            if (response.getBody() != null && response.getBody().containsKey("choices")) {
                List<Map<String, Object>> choices = (List<Map<String, Object>>) response.getBody().get("choices");
                if (choices != null && !choices.isEmpty()) {
                    Map<String, Object> messageResult = (Map<String, Object>) choices.get(0).get("message");
                    return (String) messageResult.get("content");
                }
            }
            return "Summary standard company profile.";
        } catch (Exception e) {
            // Agar AI fail bhi ho jaye, toh error throw nahi karega, default text dega
            System.err.println("Mistral AI Error: " + e.getMessage());
            return "Automated lead generated from website context.";
        }
    }
}