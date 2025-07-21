package com.java10x.CadastroNinjas.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.java10x.CadastroNinjas.dto.NinjaDTO;
import com.java10x.CadastroNinjas.service.NinjaService;



@RestController
@RequestMapping("ninjas")
public class NinjaController {

    private final NinjaService ninjaService;


    NinjaController(NinjaService ninjaService) {
        this.ninjaService = ninjaService;
    }

    

    //Adicionar Ninja
    @PostMapping("/registrar")
    public ResponseEntity<String> registrarNinja(@RequestBody NinjaDTO ninja) {
        NinjaDTO novoNinja = ninjaService.registrarNinja(ninja);
        return ResponseEntity.status(HttpStatus.CREATED)
            .body("Ninja criado com sucesso! " + novoNinja.getNome() + " com (ID): " + novoNinja.getId());
    }
    
    //Lista todos os Ninjas
    @GetMapping("/listar")
    public ResponseEntity<List<NinjaDTO>> listarTodos() {
        List<NinjaDTO> ninjas = ninjaService.listarNinjas();
        return ResponseEntity.ok(ninjas);
    }

    //Listar Ninja por ID
    @GetMapping("/listar/{id}")
    public ResponseEntity<?> listarId(@PathVariable Long id) {
        NinjaDTO ninja = ninjaService.buscarId(id);
        if (ninja != null) {
            return ResponseEntity.ok(ninja);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body("O ninja com o id " + id + " não existe em nossos registros!");
        }
    }
    

    //Alterar Ninja
    @PutMapping("/alterar/{id}")
    public ResponseEntity<String> alterarNinja(@PathVariable Long id, @RequestBody NinjaDTO ninja) {
        if(ninjaService.buscarId(id) != null){
            ninjaService.alterarNinja(id, ninja);
        return ResponseEntity.ok("Dados do ninja " + ninja.getNome() + " com (ID):" + id + " alterados com sucesso!");
        }else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body("O ninja " + ninja.getNome() + " com o id " + id + " não encontrado!");
        }
    }

    //Deletar Ninja
    @DeleteMapping("/deletar/{id}")
    public ResponseEntity<String> deletarNinja(@PathVariable Long id){
        if(ninjaService.buscarId(id) != null){
            ninjaService.deletarNinja(id);
        return ResponseEntity.ok("Ninja com ID " + id + " deletado com sucesso!");
        }else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body("O ninja com o id " + id + " não encontrado!");
        }
    }
    
}