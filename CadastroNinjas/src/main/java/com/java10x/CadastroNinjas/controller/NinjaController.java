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
    public NinjaModel registrarNinja(@RequestBody NinjaModel ninja) {
        return ninjaService.registrarNinja(ninja);
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
    @PutMapping("alterar/{id}")
    public NinjaModel alterarNinja(@PathVariable Long id, @RequestBody NinjaModel ninja) {
        return ninjaService.alterarNinja(id, ninja);
    }

    //Deletar Ninja
    @DeleteMapping("/deletar/{id}")
    public void deletarNinja(@PathVariable Long id){
        ninjaService.deletarNinja(id);
    }
    
}
