package com.java10x.CadastroNinjas.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.java10x.CadastroNinjas.model.NinjaModel;
import com.java10x.CadastroNinjas.service.NinjaService;






@RestController
@RequestMapping("ninjas")
public class NinjaController {


    @Autowired
    private NinjaService ninjaService;


    //Adicionar Ninja
    @PostMapping("/registrar")
    public String registrarNinja() {
    
        return "ninja registrado";
    }
    
    //Lista todos os Ninjas
    @GetMapping("/listar")
    public List<NinjaModel> listarTodos() {
        return ninjaService.listarNinjas();
    }

    //Listar Ninja por ID
    @GetMapping("/listar/{id}")
    public NinjaModel listarId(@PathVariable Long id) {
        return ninjaService.buscarId(id);
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
