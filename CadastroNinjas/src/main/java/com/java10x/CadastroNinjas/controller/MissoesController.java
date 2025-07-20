package com.java10x.CadastroNinjas.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.java10x.CadastroNinjas.model.MissoesModel;
import com.java10x.CadastroNinjas.model.NinjaModel;

@RestController
@RequestMapping("missoes")
public class MissoesController {

    @PostMapping("/registrar")
    public ResponseEntity<String> registrarMissao(@RequestBody MissoesModel missao) {
        NinjaModel novaMissao = missaoService.registrarNinja(missao);
        return ResponseEntity.status(HttpStatus.CREATED)
            .body("Nova missão criada com sucesso! " + novaMissao.getNome() + " com (ID): " + novaMissao.getId());
    }

    @GetMapping("/listar")
    public ResponseEntity<List<NinjaModel>> listarTodos() {
        List<MissoesModel> missoes = missaoService.listarMissoes();
        return ResponseEntity.ok(missoes);
    }


    @GetMapping("/listar/{id}")
    public ResponseEntity<?> listarId(@PathVariable Long id) {
        NinjaModel missao = missaoService.buscarId(id);
        if (missao != null) {
            return ResponseEntity.ok(missao);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body("A missão com o id " + id + " não existe em nossos registros!");
        }
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
