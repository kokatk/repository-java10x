package com.java10x.CadastroNinjas.service;


import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;

import com.java10x.CadastroNinjas.dto.NinjaDTO;
import com.java10x.CadastroNinjas.mapper.NinjaMapper;
import com.java10x.CadastroNinjas.model.NinjaModel;
import com.java10x.CadastroNinjas.repository.NinjasRepository;

@Service
@RequestMapping("/ninjas")
public class NinjaService {

    
    private final NinjasRepository ninjasRepository;
    private final NinjaMapper ninjaMapper;

    public NinjaService(@Autowired NinjasRepository ninjasRepository, @Autowired NinjaMapper ninjaMapper) {
        this.ninjasRepository = ninjasRepository;
        this.ninjaMapper = ninjaMapper;
    }

    public NinjaDTO registrarNinja(NinjaDTO ninjaDTO) {
        return ninjaMapper.map(ninjasRepository.save(ninjaMapper.map(ninjaDTO)));
    }

    // Exemplo de método: Listar todos os ninjas
    public List<NinjaDTO> listarNinjas() {
        return ninjasRepository.findAll().stream()
                .map(ninjaMapper::map)
                .collect(Collectors.toList());
    }

    // Exemplo: Buscar por ID
    public NinjaDTO buscarId(Long id) {
        Optional<NinjaModel> ninja = ninjasRepository.findById(id);
        return ninja.map(ninjaMapper::map).orElse(null);
    }



    public NinjaDTO alterarNinja(Long id, NinjaDTO ninjaDTO) {
        Optional<NinjaModel> ninja = ninjasRepository.findById(id);
        if (ninja.isPresent()) {
            NinjaModel ninjaAlterado = ninjaMapper.map(ninjaDTO);
            ninjaAlterado.setId(id);
            NinjaModel ninjaSalvo = ninjasRepository.save(ninjaAlterado);
            return ninjaMapper.map(ninjaSalvo);
        } else {
            return null;
        }
    }

    // Exemplo: Deletar
    public void deletarNinja(Long id) {
        ninjasRepository.deleteById(id);
    }

}