package com.java10x.CadastroNinjas.controller;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping
public class MissoesController {

    @PostMapping("/registrarMissao")
    public String registrarMissao() {
    
        return "ninja registrado";
    } 

    @GetMapping("/listarMissoes")
    public String listarMissoes() {
        return "lista todos";
    }

    @PutMapping("editarMissao/{id}")
    public String editarMissao() {
      return "alterar";
    }

    @DeleteMapping("/deletarMissao/{id}")
    public String deletarMissao(){
        return "deletar";
    }
}
