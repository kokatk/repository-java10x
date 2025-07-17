package com.java10x.CadastroNinjas.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping
public class NinjaController {

    @GetMapping("/boasVidas")
    public String boasVindas(){
        return "Essa é minha mensagem de boas vindas";
    }

}
