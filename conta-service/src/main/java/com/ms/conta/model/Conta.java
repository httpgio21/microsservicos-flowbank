package com.ms.conta.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class Conta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long userId;
    private Double saldo = 0.0;
}

