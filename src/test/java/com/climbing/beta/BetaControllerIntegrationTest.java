package com.climbing.beta;

import com.climbing.beta.db.entity.ClimbBeta;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(classes = Application.class)
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class BetaControllerIntegrationTest {
    private ObjectMapper objectMapper;

    @Autowired
    MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        objectMapper = new ObjectMapper();
    }

    @Test
    void climbRequestIsInDb_returnsClimbBeta() throws Exception {
        ClimbBeta climbBeta = new ClimbBeta();
        climbBeta.setId(1);
        climbBeta.setCrag("crag");
        climbBeta.setClimb("climb");
        climbBeta.setBetaUrl("betaUrl");

        String climbBetaAsJson = objectMapper.writeValueAsString(climbBeta);

        MockHttpServletRequestBuilder requestBuilder = get("/api/crag/crag/climb/climb")
                .contentType(MediaType.APPLICATION_JSON);

        this.mockMvc.perform(requestBuilder)
                .andExpect(status().isOk())
                .andExpect(content().string(climbBetaAsJson));
    }

    @Test
    void cragRequestedIsNotInDb_returnsNotFoundStatus() throws Exception {
        MockHttpServletRequestBuilder requestBuilder = get("/api/crag/cragNotInDb/climb/anyClimb")
                .contentType(MediaType.APPLICATION_JSON);

        this.mockMvc.perform(requestBuilder)
                .andExpect(status().isNotFound());
    }

    @Test
    void climbRequestedIsNotInDb_returnsNotFoundStatus() throws Exception {
        MockHttpServletRequestBuilder requestBuilder = get("/api/crag/crag/climb/climbNotInDb")
                .contentType(MediaType.APPLICATION_JSON);

        this.mockMvc.perform(requestBuilder)
                .andExpect(status().isNotFound());
    }
}
