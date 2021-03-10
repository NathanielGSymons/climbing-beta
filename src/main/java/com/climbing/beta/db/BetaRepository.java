package com.climbing.beta.db;

import com.climbing.beta.db.entity.ClimbBeta;
import org.springframework.data.repository.Repository;

import java.util.Optional;

public interface BetaRepository extends Repository<ClimbBeta, String> {
    Optional<ClimbBeta> findByCragAndClimb(String crag, String climb);
}
