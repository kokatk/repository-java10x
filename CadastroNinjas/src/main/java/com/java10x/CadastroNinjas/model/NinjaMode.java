package com.java10x.CadastroNinjas.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "tb_cadastros")
public class NinjaMode {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long Id;
    String nome, email;
    int idade;
}
