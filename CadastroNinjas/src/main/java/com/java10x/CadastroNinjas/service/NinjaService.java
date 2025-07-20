package com.java10x.CadastroNinjas.service;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;

import com.java10x.CadastroNinjas.model.NinjaModel;
import com.java10x.CadastroNinjas.repository.NinjasRepository;

@Service
@RequestMapping("/ninjas")
public class NinjaService {

    @Autowired
    private final NinjasRepository ninjasRepository;

    public NinjaService(NinjasRepository ninjasRepository) {
        this.ninjasRepository = ninjasRepository;
    }

    // Exemplo de método: Listar todos os ninjas
    public List<NinjaModel> listarNinjas() {
        return ninjasRepository.findAll();
    }

    // Exemplo: Buscar por ID
    public NinjaModel buscarId(Long id) {
        return ninjasRepository.findById(id).orElse(null);
    }


    
    public NinjaModel registrarNinja(NinjaModel ninja) {
        return ninjasRepository.save(ninja);
    }

    public NinjaModel alterarNinja(Long id, NinjaModel ninja) {
        return ninjasRepository.findById(id)
                .map(n -> {
                    n.setNome(ninja.getNome());
                    n.setEmail(ninja.getEmail());
                    n.setRank(ninja.getRank());
                    n.setImgUrl(ninja.getImgUrl());
                    n.setIdade(ninja.getIdade());
                    n.setMissoes(ninja.getMissoes());
                    return ninjasRepository.save(n);
                })
                .orElse(null);
    }

    // Exemplo: Deletar
    
    public void deletarNinja(Long id) {
        ninjasRepository.deleteById(id);
    }

}
