package com.java10x.CadastroNinjas.dto;

import java.util.List;

import com.java10x.CadastroNinjas.model.NinjaModel;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MissaoDTO {

    private Long id;
    private String nome;
    private String dificuldade;
    private List<NinjaModel> ninjas;

}
