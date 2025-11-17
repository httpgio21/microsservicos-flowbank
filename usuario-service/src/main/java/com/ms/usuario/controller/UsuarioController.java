package com.ms.usuario.controller;

import com.ms.usuario.model.Usuario;
import com.ms.usuario.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @PostMapping
    public ResponseEntity<?> criar(@RequestBody Usuario usuario) {
    try {
        // 1️⃣ Salva usuário no DB
        Usuario u = usuarioRepository.save(usuario);

        // 2️⃣ Chama ContaService para criar a conta vinculada
        RestTemplate restTemplate = new RestTemplate();
        String contaServiceUrl = "http://localhost:8081/contas";

        Map<String, Object> payload = new HashMap<>();
        payload.put("userId", u.getId());

        HttpEntity<Map<String, Object>> request = new HttpEntity<>(payload);

        ResponseEntity<ContaResponse> response = restTemplate.postForEntity(
                contaServiceUrl,
                request,
                ContaResponse.class
        );

        if (response.getStatusCode().is2xxSuccessful() && response.getBody() != null) {
            u.setContaId(response.getBody().getId());
            usuarioRepository.save(u);

            return ResponseEntity.status(HttpStatus.CREATED)
                    .body("Usuário criado com sucesso! Conta vinculada.");
        }

        return ResponseEntity.status(HttpStatus.CREATED)
                .body("Usuário criado, mas não foi possível criar a conta.");

    } catch (Exception e) {
        e.printStackTrace();
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body("Erro ao criar usuário: " + e.getMessage());
    }
}
    @GetMapping
    public List<Usuario> listar() {
        return usuarioRepository.findAll();
    }

    // Classe interna para mapear a resposta do ContaService
    static class ContaResponse {
        private Long id;
        private Double saldo;
        private Long userId;

        public Long getId() { return id; }
        public void setId(Long id) { this.id = id; }

        public Double getSaldoInicial() { return saldo; }
        public void setSaldoInicial(Double saldoInicial) { this.saldo = saldoInicial; }

        public Long getUserId() { return userId; }
        public void setUserId(Long userId) { this.userId = userId; }
    }
}
