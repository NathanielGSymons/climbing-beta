package com.climbing.beta.service;

import com.climbing.beta.db.BetaRepository;
import com.climbing.beta.db.entity.ClimbBeta;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class BetaServiceTest {
    @Mock
    private BetaRepository betaRepository;

    private BetaService betaService;

    @BeforeEach
    void setUp() {
        betaService = new BetaService(betaRepository);
    }

    @Test
    void returnsOptionalOfClimbBeta() {
        ClimbBeta climbBeta = new ClimbBeta();
        Optional<ClimbBeta> climbBetaOptional = Optional.of(climbBeta);

        when(betaRepository.findByCragAndClimb(any(), any())).thenReturn(climbBetaOptional);

        assertThat(betaService.GetBeta("crag", "climb")).isEqualTo(climbBetaOptional);
    }
}