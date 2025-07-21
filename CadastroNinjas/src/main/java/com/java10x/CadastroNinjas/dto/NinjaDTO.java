package com.java10x.CadastroNinjas.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class NinjaDTO {

    private Long id;
    private String nome;
    private String email;
    private String rank;
    private String imgUrl;
    private int idade;
    private MissaoDTO missao;

}
