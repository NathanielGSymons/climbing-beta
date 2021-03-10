package com.climbing.beta.controller;

import com.climbing.beta.db.entity.ClimbBeta;
import com.climbing.beta.service.BetaService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest
@ExtendWith(MockitoExtension.class)
class BetaControllerTest {
    @MockBean
    private BetaService betaService;

    private MockMvc mockMvc;
    private ObjectMapper objectMapper;

    @BeforeEach
    void setUp() {
        BetaController betaController = new BetaController(betaService);
        this.mockMvc = MockMvcBuilders.standaloneSetup(betaController).build();
        objectMapper = new ObjectMapper();
    }

    @Test
    void returnsBetaUrl() throws Exception {
        ClimbBeta climbBeta = new ClimbBeta();
        climbBeta.setCrag("crag");
        climbBeta.setClimb("climb");

        when(betaService.GetBeta(any(), any())).thenReturn(Optional.of(climbBeta));

        String climbBetaAsJson = objectMapper.writeValueAsString(climbBeta);

        MockHttpServletRequestBuilder requestBuilder = get("/api/crag/crag/climb/climb")
                .contentType(MediaType.APPLICATION_JSON);

        this.mockMvc.perform(requestBuilder)
                .andExpect(status().isOk())
                .andExpect(content().string(climbBetaAsJson));

        verify(betaService).GetBeta("crag", "climb");
    }
}