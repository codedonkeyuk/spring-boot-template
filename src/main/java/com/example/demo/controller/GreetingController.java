package com.example.demo.controller;

import com.example.demo.dto.PingResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
import java.lang.management.ManagementFactory;
import org.springframework.boot.info.BuildProperties;

import java.util.Map;
import java.util.LinkedHashMap;
import java.time.LocalDateTime;

/**
 * Controller providing system health and diagnostic information.
 */
@RestController
@Tag(name = "Health Check", description = "Endpoints for system status and heartbeat")
public class GreetingController {

    public static final String UNKNOWN = "unknown";

    /**
     * Provides build metadata (version, name, etc.) injected from Gradle/Maven
     * build properties.
     */
    @Autowired
    private BuildProperties buildProperties;

    /**
     * Returns a heartbeat response containing system status, uptime, and version
     * info.
     * This is primarily used for health checks and monitoring.
     * 
     * @return A PingResponse object containing the system's current state.
     */
    @Operation(summary = "Ping the server", description = "Returns status, uptime, and version information")
    @GetMapping("/api/ping")
    public PingResponse ping() {
        // Calculate how long the JVM has been running
        long uptimeSeconds = ManagementFactory.getRuntimeMXBean().getUptime() / 1000;
        String timestamp = LocalDateTime.now().toString();

        // Extract build information if available, otherwise provide defaults
        String version = (buildProperties != null) ? buildProperties.getVersion() : UNKNOWN;
        String projectName = (buildProperties != null) ? buildProperties.getName() : UNKNOWN;

        /**
         * The details map is reserved for extensible metadata.
         * Currently, it acts as a placeholder for future components
         * like database connection status, cache availability, or memory metrics.
         */
        Map<String, Object> details = new LinkedHashMap<>();

        return new PingResponse(
                "success",
                timestamp,
                uptimeSeconds,
                version,
                projectName,
                details);
    }
}
