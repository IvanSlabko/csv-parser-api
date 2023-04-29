package com.slabko.parser.model;

import jakarta.annotation.Nonnull;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * Crypto entity representation in database.
 */
@Getter
@Setter
@Entity
@Table(name = "crypto")
public class CryptoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long cryptoId;
    @Nonnull
    private LocalDateTime date;
    @Nonnull
    private String name;
    @Nonnull
    private BigDecimal price;

}
