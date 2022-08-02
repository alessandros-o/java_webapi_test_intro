package br.com.iteris.universidade.testes.intro.controller;

import br.com.iteris.universidade.testes.intro.model.Salario;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

@RestController
@RequestMapping("/bonus")
public class SalarioController {

    @PostMapping
    public ResponseEntity<String> calcula(@RequestParam("salario") BigDecimal salario, @RequestParam("vendas") BigDecimal vendas){
        return ResponseEntity.ok(Salario.comBonus(salario, vendas));
    }
}
