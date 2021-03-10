package com.climbing.beta.controller;

import com.climbing.beta.db.entity.ClimbBeta;
import com.climbing.beta.service.BetaService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class BetaController {
    private final BetaService betaService;

    public BetaController(BetaService betaService) {
        this.betaService = betaService;
    }

    @GetMapping(value = "/crag/{cragName}/climb/{climbName}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ClimbBeta> getClimbBeta(@PathVariable("cragName") String crag,
                                                  @PathVariable("climbName") String climb) {
        return betaService.GetBeta(crag, climb)
                .map(climbBeta -> ResponseEntity.ok().body(climbBeta))
                .orElseGet(() -> ResponseEntity.status(HttpStatus.BAD_REQUEST).build());
    }
}
