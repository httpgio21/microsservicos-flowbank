package com.ms.conta.controller;

import com.ms.conta.model.Conta;
import com.ms.conta.repository.ContaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/contas")
public class ContaController {

    @Autowired
    private ContaRepository contaRepository;

    // Cria uma conta
    @PostMapping
    public ResponseEntity<Conta> criar(@RequestBody Conta conta) {
        Conta c = contaRepository.save(conta);
        return ResponseEntity.status(HttpStatus.CREATED).body(c);
    }

    // Lista todas as contas
    @GetMapping
    public ResponseEntity<List<Conta>> listar() {
        List<Conta> contas = contaRepository.findAll();
        return ResponseEntity.ok(contas);
    }

    // Busca uma conta por ID
    @GetMapping("/{id}")
    public ResponseEntity<Conta> buscarPorId(@PathVariable Long id) {
        return contaRepository.findById(id)
                .map(conta -> ResponseEntity.ok(conta))
                .orElse(ResponseEntity.notFound().build());
    }
}
