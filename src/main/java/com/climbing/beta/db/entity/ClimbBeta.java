package com.climbing.beta.db.entity;

import javax.persistence.*;

@Entity
@Table(name = "BETACLIMB")
public class ClimbBeta {

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "CRAG")
    private String crag;

    @Column(name = "CLIMB")
    private String climb;

    @Column(name = "BETAURL")
    private String betaUrl;

    public ClimbBeta() {
    }

    public ClimbBeta(long id, String crag, String climb, String betaUrl) {
        this.id = id;
        this.crag = crag;
        this.climb = climb;
        this.betaUrl = betaUrl;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getCrag() {
        return crag;
    }

    public void setCrag(String crag) {
        this.crag = crag;
    }

    public String getClimb() {
        return climb;
    }

    public void setClimb(String climb) {
        this.climb = climb;
    }

    public String getBetaUrl() {
        return betaUrl;
    }

    public void setBetaUrl(String betaUrl) {
        this.betaUrl = betaUrl;
    }
}
