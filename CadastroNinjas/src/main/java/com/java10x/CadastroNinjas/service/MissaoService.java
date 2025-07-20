package com.java10x.CadastroNinjas.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;

import com.java10x.CadastroNinjas.model.MissoesModel;
import com.java10x.CadastroNinjas.repository.MissoesRepository;

@Service
@RequestMapping("/missoes")
public class MissaoService {

    @Autowired
    private final MissoesRepository missoesRepository;

    public MissaoService(MissoesRepository missoesRepository) {
        this.missoesRepository = missoesRepository;
    }

    // Exemplo de método: Listar todos os ninjas
    public List<MissoesModel> listarMissoes() {
        return missoesRepository.findAll();
    }

    // Exemplo: Buscar por ID
    public MissoesModel buscarId(Long id) {
        return missoesRepository.findById(id).orElse(null);
    }


    
    public MissoesModel registrarMissao(MissoesModel missao) {
        return missoesRepository.save(missao);
    }

    public MissoesModel alterarMissao(Long id, MissoesModel missao) {
        return missoesRepository.findById(id)
                .map(n -> {
                    n.setNome(missao.getNome());
                    n.setDificuldade(missao.getDificuldade());
                    return missoesRepository.save(n);
                })
                .orElse(null);
    }

    // Exemplo: Deletar
    
    public void deletarMissao(Long id) {
        missoesRepository.deleteById(id);
    }

}

