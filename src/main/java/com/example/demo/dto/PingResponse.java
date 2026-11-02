package com.example.demo.dto;

import java.util.Map;

public record PingResponse(
        String status,
        String timestamp,
        long uptimeSeconds,
        String version,
        String projectName,
        Map<String, Object> details) {
}
