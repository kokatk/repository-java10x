package com.java10x.CadastroNinjas.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MissaoDTO {

    private Long id;
    private String nome;
    private String dificuldade;

    @JsonIgnore
    private List<NinjaDTO> ninjas;

}
