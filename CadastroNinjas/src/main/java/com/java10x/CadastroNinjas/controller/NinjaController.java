package com.java10x.CadastroNinjas.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.java10x.CadastroNinjas.dto.NinjaDTO;
import com.java10x.CadastroNinjas.service.NinjaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;


@RestController
@RequestMapping("/ninjas")
public class NinjaController {

    private final NinjaService ninjaService;


    NinjaController(NinjaService ninjaService) {
        this.ninjaService = ninjaService;
    }

    

    @Operation(summary = "Criar Ninja", description = "Cria um novo ninja no sistema.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "Ninja criado com sucesso!", content = @Content(schema = @Schema(implementation = String.class))),
        @ApiResponse(responseCode = "400", description = "Requisição inválida", content = @Content)
    })
    @PostMapping("/registrar")
    public ResponseEntity<String> criarNinja(@RequestBody @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "Dados do ninja a ser criado", required = true, content = @Content(schema = @Schema(implementation = NinjaDTO.class))) NinjaDTO ninja) {
        NinjaDTO novoNinja = ninjaService.registrarNinja(ninja);
        return ResponseEntity.status(HttpStatus.CREATED)
            .body("Ninja criado com sucesso! " + novoNinja.getNome() + " com (ID): " + novoNinja.getId());
    }

    @Operation(summary = "Listar Ninjas", description = "Lista todos os ninjas cadastrados.")
    @ApiResponse(responseCode = "200", description = "Lista de ninjas retornada com sucesso.", content = @Content(schema = @Schema(implementation = NinjaDTO.class)))
    @GetMapping("/listar")
    public ResponseEntity<List<NinjaDTO>> listarNinjas() {
        List<NinjaDTO> ninjas = ninjaService.listarNinjas();
        return ResponseEntity.ok(ninjas);
    }

    @Operation(summary = "Buscar Ninja por ID", description = "Busca um ninja pelo seu ID.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Ninja encontrado.", content = @Content(schema = @Schema(implementation = NinjaDTO.class))),
        @ApiResponse(responseCode = "404", description = "Ninja não encontrado.", content = @Content)
    })
    @GetMapping("/listar/{id}")
    public ResponseEntity<?> buscarNinjaPorId(@Parameter(description = "ID do ninja", required = true) @PathVariable Long id) {
        NinjaDTO ninja = ninjaService.buscarId(id);
        if (ninja != null) {
            return ResponseEntity.ok(ninja);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body("O ninja com o id " + id + " não existe em nossos registros!");
        }
    }

    @Operation(summary = "Atualizar Ninja", description = "Atualiza os dados de um ninja existente.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Ninja atualizado com sucesso.", content = @Content(schema = @Schema(implementation = String.class))),
        @ApiResponse(responseCode = "404", description = "Ninja não encontrado.", content = @Content)
    })
    @PutMapping("/alterar/{id}")
    public ResponseEntity<String> atualizarNinja(@Parameter(description = "ID do ninja", required = true) @PathVariable Long id, @RequestBody @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "Dados do ninja a serem atualizados", required = true, content = @Content(schema = @Schema(implementation = NinjaDTO.class))) NinjaDTO ninja) {
        if(ninjaService.buscarId(id) != null){
            ninjaService.alterarNinja(id, ninja);
            return ResponseEntity.ok("Dados do ninja " + ninja.getNome() + " com (ID):" + id + " alterados com sucesso!");
        }else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body("O ninja " + ninja.getNome() + " com o id " + id + " não encontrado!");
        }
    }

    @Operation(summary = "Remover Ninja", description = "Remove um ninja do sistema.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Ninja removido com sucesso.", content = @Content(schema = @Schema(implementation = String.class))),
        @ApiResponse(responseCode = "404", description = "Ninja não encontrado.", content = @Content)
    })
    @DeleteMapping("/deletar/{id}")
    public ResponseEntity<String> removerNinja(@Parameter(description = "ID do ninja", required = true) @PathVariable Long id){
        if(ninjaService.buscarId(id) != null){
            ninjaService.deletarNinja(id);
            return ResponseEntity.ok("Ninja com ID " + id + " deletado com sucesso!");
        }else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body("O ninja com o id " + id + " não encontrado!");
        }
    }
    
}