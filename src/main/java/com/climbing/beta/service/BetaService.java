package com.climbing.beta.service;

import com.climbing.beta.db.BetaRepository;
import com.climbing.beta.db.entity.ClimbBeta;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class BetaService {
    private final BetaRepository betaRepository;

    public BetaService(BetaRepository betaRepository) {
        this.betaRepository = betaRepository;
    }

    public Optional<ClimbBeta> GetBeta(String crag, String climb) {
        return betaRepository.findByCragAndClimb(crag, climb);
    }
}
