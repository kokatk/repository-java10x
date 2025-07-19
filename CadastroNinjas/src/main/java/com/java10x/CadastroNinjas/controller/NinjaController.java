package com.java10x.CadastroNinjas.controller;

import org.springframework.web.bind.annotation.*;






@RestController
@RequestMapping("ninjas")
public class NinjaController {

    @GetMapping("/boasVidas")
    public String boasVindas(){
        return "Essa é minha mensagem de boas vindas";
    }


    //Adicionar Ninja
   @PostMapping("/registrar")
    public String registrarNinja() {
    
        return "ninja registrado";
    }
    
    //Lista todos os Ninjas
     @GetMapping("/listar")
    public String listarTodos() {
        return "lista todos";
    }

    //Procurar Ninja por ID
    @GetMapping("/buscar/{id}")
    public String buscarId() {
        return "buscar por id";
    }
    

    //Alterar Ninja
    @PutMapping("editar/{id}")
    public String editarNinja() {
      return "alterar";
    }

    //Deletar Ninja
    @DeleteMapping("/deletar/{id}")
    public String deletarNinja(){
        return "deletar";
    }
    
}
