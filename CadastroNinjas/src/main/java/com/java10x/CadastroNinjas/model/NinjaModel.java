package com.java10x.CadastroNinjas.model;

import java.util.*;

import jakarta.persistence.*;
import lombok.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "tb_cadastros")
public class NinjaModel {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;
    private String nome, email;
    private int idade;
    private List<MissoesModel> missoes;
}
