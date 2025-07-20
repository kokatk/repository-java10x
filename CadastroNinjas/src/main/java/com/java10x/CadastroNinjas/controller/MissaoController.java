package com.java10x.CadastroNinjas.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.java10x.CadastroNinjas.model.MissoesModel;
import com.java10x.CadastroNinjas.service.MissaoService;


@RestController
@RequestMapping("missoes")
public class MissaoController {

    private final MissaoService missaoService;


    MissaoController(MissaoService missaoService) {
        this.missaoService = missaoService;
    }

    @PostMapping("/registrar")
    public ResponseEntity<String> registrarMissao(@RequestBody MissoesModel missao) {
        MissoesModel novaMissao = missaoService.registrarMissao(missao);
        return ResponseEntity.status(HttpStatus.CREATED)
            .body("Nova missão criada com sucesso! " + novaMissao.getNome() + " com (ID): " + novaMissao.getId());
    }

    @GetMapping("/listar")
    public ResponseEntity<List<MissoesModel>> listarTodos() {
        List<MissoesModel> missoes = missaoService.listarMissoes();
        return ResponseEntity.ok(missoes);
    }


    @GetMapping("/listar/{id}")
    public ResponseEntity<?> listarId(@PathVariable Long id) {
        MissoesModel missao = missaoService.buscarId(id);
        if (missao != null) {
            return ResponseEntity.ok(missao);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body("A missão com o id " + id + " não existe em nossos registros!");
        }
    }


    @PutMapping("alterar/{id}")
    public ResponseEntity<String> alterarMissao(@PathVariable Long id, @RequestBody MissoesModel missao) {
        if(missaoService.buscarId(id) != null){
            missaoService.alterarMissao(id, missao);
        return ResponseEntity.ok("Dados da missao " + missao.getNome() + " com (ID):" + id + " alterados com sucesso!");
        }else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body("A missao " + missao.getNome() + " com o id " + id + " não encontrado!");
        }
    }

    @DeleteMapping("/deletar/{id}")
    public ResponseEntity<String> deletarMissao(@PathVariable Long id){
        if(missaoService.buscarId(id) != null){
            missaoService.deletarMissao(id);
        return ResponseEntity.ok("Missao com ID " + id + " deletado com sucesso!");
        }else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body("A missao com o id " + id + " não encontrado!");
        }
    }
}
