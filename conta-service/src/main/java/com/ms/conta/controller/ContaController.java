package com.ms.conta.controller;

import com.ms.conta.model.Conta;
import com.ms.conta.repository.ContaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/contas")
public class ContaController {

    @Autowired
    private ContaRepository contaRepository;

    @PostMapping
    public ResponseEntity<Conta> criar(@RequestBody Conta conta) {
        Conta c = contaRepository.save(conta);
        return ResponseEntity.status(HttpStatus.CREATED).body(c);
    }

    
}

