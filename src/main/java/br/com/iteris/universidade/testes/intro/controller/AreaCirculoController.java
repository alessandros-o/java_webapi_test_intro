package br.com.iteris.universidade.testes.intro.controller;

import br.com.iteris.universidade.testes.intro.model.AreaCirculo;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/area-circulo")
public class AreaCirculoController {

    @PostMapping
    public ResponseEntity<String> calcula(@RequestParam("raio") double raio) {
        return ResponseEntity.ok(AreaCirculo.calcula(raio));
    }

}
