package com.java10x.CadastroNinjas.dto;

import com.java10x.CadastroNinjas.model.MissoesModel;


import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class NinjaDTO {

    private Long Id;
    private String nome;
    private String email;
    private String rank;
    private String imgUrl;
    private int idade;
    private MissoesModel missoes;

}
