package com.java10x.CadastroNinjas.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.java10x.CadastroNinjas.dto.MissaoDTO;
import com.java10x.CadastroNinjas.service.MissaoService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;


@RestController
@RequestMapping("missoes")
public class MissaoController {

    private final MissaoService missaoService;


    MissaoController(MissaoService missaoService) {
        this.missaoService = missaoService;
    }

    @Operation(summary = "Mensagem de boas-vindas", description = "Exibe uma mensagem de boas-vindas ao sistema.")
    @GetMapping("/boasvindas")
    public String boasVindas() {
        return "Bem vindo ao sistema de ninjas e missões!";
    }

    @Operation(summary = "Criar Missão", description = "Cria uma nova missão no sistema.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "Missão criada com sucesso!", content = @Content(schema = @Schema(implementation = String.class))),
        @ApiResponse(responseCode = "400", description = "Requisição inválida", content = @Content)
    })
    @PostMapping("/registrar")
    public ResponseEntity<String> criarMissao(@RequestBody @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "Dados da missão a ser criada", required = true, content = @Content(schema = @Schema(implementation = MissaoDTO.class))) MissaoDTO missao) {
        MissaoDTO novaMissao = missaoService.registrarMissao(missao);
        return ResponseEntity.status(HttpStatus.CREATED)
            .body("Nova missão criada com sucesso! " + novaMissao.getNome() + " com (ID): " + novaMissao.getId());
    }

    @Operation(summary = "Listar Missões", description = "Lista todas as missões cadastradas.")
    @ApiResponse(responseCode = "200", description = "Lista de missões retornada com sucesso.", content = @Content(schema = @Schema(implementation = MissaoDTO.class)))
    @GetMapping("/listar")
    public ResponseEntity<List<MissaoDTO>> listarMissoes() {
        List<MissaoDTO> missoes = missaoService.listarMissoes();
        return ResponseEntity.ok(missoes);
    }

    @Operation(summary = "Buscar Missão por ID", description = "Busca uma missão pelo seu ID.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Missão encontrada.", content = @Content(schema = @Schema(implementation = MissaoDTO.class))),
        @ApiResponse(responseCode = "404", description = "Missão não encontrada.", content = @Content)
    })
    @GetMapping("/listar/{id}")
    public ResponseEntity<?> buscarMissaoPorId(@Parameter(description = "ID da missão", required = true) @PathVariable Long id) {
        MissaoDTO missao = missaoService.buscarId(id);
        if (missao != null) {
            return ResponseEntity.ok(missao);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body("A missão com o id " + id + " não existe em nossos registros!");
        }
    }

    @Operation(summary = "Atualizar Missão", description = "Atualiza os dados de uma missão existente.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Missão atualizada com sucesso.", content = @Content(schema = @Schema(implementation = String.class))),
        @ApiResponse(responseCode = "404", description = "Missão não encontrada.", content = @Content)
    })
    @PutMapping("/alterar/{id}")
    public ResponseEntity<String> atualizarMissao(@Parameter(description = "ID da missão", required = true) @PathVariable Long id, @RequestBody @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "Dados da missão a serem atualizados", required = true, content = @Content(schema = @Schema(implementation = MissaoDTO.class))) MissaoDTO missao) {
        if(missaoService.buscarId(id) != null){
            missaoService.alterarMissao(id, missao);
            return ResponseEntity.ok("Dados da missão " + missao.getNome() + " com (ID):" + id + " alterados com sucesso!");
        }else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body("A missão " + missao.getNome() + " com o id " + id + " não encontrado!");
        }
    }

    @Operation(summary = "Remover Missão", description = "Remove uma missão do sistema.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Missão removida com sucesso.", content = @Content(schema = @Schema(implementation = String.class))),
        @ApiResponse(responseCode = "404", description = "Missão não encontrada.", content = @Content)
    })
    @DeleteMapping("/deletar/{id}")
    public ResponseEntity<String> removerMissao(@Parameter(description = "ID da missão", required = true) @PathVariable Long id){
        if(missaoService.buscarId(id) != null){
            missaoService.deletarMissao(id);
            return ResponseEntity.ok("Missão com ID " + id + " deletado com sucesso!");
        }else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body("A missão com o id " + id + " não encontrado!");
        }
    }
}