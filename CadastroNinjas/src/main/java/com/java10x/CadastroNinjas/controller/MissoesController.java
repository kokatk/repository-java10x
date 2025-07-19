package com.java10x.CadastroNinjas.controller;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("missoes")
public class MissoesController {

    @PostMapping("/registrar")
    public String registrarMissao() {
    
        return "ninja registrado";
    } 

    @GetMapping("/listar")
    public String listarMissoes() {
        return "lista todos";
    }

    @PutMapping("editar/{id}")
    public String editarMissao() {
      return "alterar";
    }

    @DeleteMapping("/deletar/{id}")
    public String deletarMissao(){
        return "deletar";
    }
}
