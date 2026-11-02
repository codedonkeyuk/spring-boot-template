package com.example.demo.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.info.BuildProperties;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(GreetingController.class)
class GreetingControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private BuildProperties buildProperties;

    @BeforeEach
    void setUp() {
        Mockito.when(buildProperties.getVersion()).thenReturn("1.0.0-SNAPSHOT");
        Mockito.when(buildProperties.getName()).thenReturn("my-starter-project");
    }

    @Test
    void shouldReturnSuccessfulPingResponseWithCorrectPayload() throws Exception {
        mockMvc.perform(get("/api/ping")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.status", is("success")))
                .andExpect(jsonPath("$.timestamp", notNullValue()));
    }

    @Test
    void shouldHandleNullBuildPropertiesGracefully() throws Exception {
        Mockito.when(buildProperties.getVersion()).thenReturn(null);
        Mockito.when(buildProperties.getName()).thenReturn(null);

        mockMvc.perform(get("/api/ping"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.status", is("success")));
    }
}
