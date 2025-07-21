package com.java10x.CadastroNinjas.model;



import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "tb_cadastros")
public class NinjaModel {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    @Column(name = "nome")
    private String nome;

    @Column(unique = true)
    private String email;

    @Column(name = "rank")
    private String rank;

    @Column(name = "img_url")
    private String imgUrl;

    @Column(name = "idade")
    private int idade;
    
    @ManyToOne
    @JoinColumn(name = "missao_id")
    private MissoesModel missao;

}
