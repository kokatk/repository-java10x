package com.java10x.CadastroNinjas.service;


import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;

import com.java10x.CadastroNinjas.model.NinjaModel;
import com.java10x.CadastroNinjas.repository.NinjasRepository;

@Service
@RequestMapping("/ninjas")
public class NinjaService {

    
    private NinjasRepository ninjasRepository;

    public NinjaService(NinjasRepository ninjasRepository) {
        this.ninjasRepository = ninjasRepository;
    }

    // Exemplo de método: Listar todos os ninjas
    public List<NinjaModel> listarNinjas() {
        return ninjasRepository.findAll();
    }

    // Exemplo: Buscar por ID
    public NinjaModel buscarId(Long id) {
        Optional<NinjaModel> ninjaPorId = ninjasRepository.findById(id);
        return ninjaPorId.orElse(null);
    }

    // Exemplo: Salvar ou atualizar (com transação para atomicidade)
    @Transactional 
    public NinjaModel registrarNinja(NinjaModel ninja) {
        return ninjasRepository.save(ninja);
    }

   public NinjaModel alterarNinja(Long id, NinjaModel ninja) {
        if (ninjasRepository.existsById(id)){
            ninja.setId(id);
            return ninjasRepository.save(ninja);
        }
    }

    // Exemplo: Deletar
    @Transactional
    public void deletarNinja(Long id) {
        ninjasRepository.deleteById(id);
    }

}
