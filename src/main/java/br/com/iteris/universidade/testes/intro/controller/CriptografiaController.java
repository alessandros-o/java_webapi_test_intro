package br.com.iteris.universidade.testes.intro.controller;

import br.com.iteris.universidade.testes.intro.model.Criptografia;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/cripto")
public class CriptografiaController {

    @PostMapping
    public ResponseEntity<String> criptografa(@RequestParam("palavra") String palavra){
        return ResponseEntity.ok(Criptografia.criptografar(palavra));
    }
}
