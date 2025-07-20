package com.java10x.CadastroNinjas.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.java10x.CadastroNinjas.dto.MissaoDTO;
import com.java10x.CadastroNinjas.mapper.NinjaMapper;
import com.java10x.CadastroNinjas.model.MissoesModel;
import com.java10x.CadastroNinjas.repository.MissoesRepository;

@Service
public class MissaoService {

    private final MissoesRepository missoesRepository;
    private final NinjaMapper ninjaMapper;

    public MissaoService(@Autowired MissoesRepository missoesRepository, @Autowired NinjaMapper ninjaMapper) {
        this.missoesRepository = missoesRepository;
        this.ninjaMapper = ninjaMapper;
    }

    // Exemplo de método: Listar todos as missões
    public List<MissaoDTO> listarMissoes() {
        List<MissoesModel> models = missoesRepository.findAll();
        return models.stream().map(ninjaMapper::toMissaoDTO).collect(Collectors.toList());
    }

    // Exemplo: Buscar por ID
    public MissaoDTO buscarId(Long id) {
        Optional<MissoesModel> modelOpt = missoesRepository.findById(id);
        return modelOpt.map(ninjaMapper::toMissaoDTO).orElse(null);
    }

    public MissaoDTO registrarMissao(MissaoDTO missao) {
        MissoesModel model = ninjaMapper.toMissaoModel(missao);
        MissoesModel saved = missoesRepository.save(model);
        return ninjaMapper.toMissaoDTO(saved);
    }

    public MissaoDTO alterarMissao(Long id, MissaoDTO missao) {
        return missoesRepository.findById(id)
                .map(n -> {
                    n.setNome(missao.getNome());
                    n.setDificuldade(missao.getDificuldade());
                    MissoesModel updated = missoesRepository.save(n);
                    return ninjaMapper.toMissaoDTO(updated);
                })
                .orElse(null);
    }

    // Exemplo: Deletar
    public void deletarMissao(Long id) {
        missoesRepository.deleteById(id);
    }

}