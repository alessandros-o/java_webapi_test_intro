package br.com.iteris.universidade.testes.intro.controller;

import br.com.iteris.universidade.testes.intro.model.Media;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/media")
public class MediaController {

    @PostMapping(value = "/dois-valores")
    public ResponseEntity<String> calcula(@RequestParam("n1") double n1, @RequestParam("n2") double n2){
        return ResponseEntity.ok(Media.simples(n1, n2));
    }

    @PostMapping(value = "/tres-valores")
    public ResponseEntity<String> calcula(@RequestParam("n1") double n1, @RequestParam("n2") double n2, @RequestParam("n3") double n3){
        return ResponseEntity.ok(Media.simples(n1, n2, n3));
    }
}
