package com.quantweb.springserver.domain.graph.entity;

import jakarta.persistence.Embeddable;

import java.io.Serializable;

@Embeddable

public class PricachangeAndMddFK implements Serializable {

    private Long priceChangeGraph;
    private Long mddGraph;
}
