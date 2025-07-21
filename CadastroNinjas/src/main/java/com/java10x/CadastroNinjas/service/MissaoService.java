package com.java10x.CadastroNinjas.service;

import java.util.List;
import org.springframework.stereotype.Service;

import com.java10x.CadastroNinjas.dto.MissaoDTO;
import com.java10x.CadastroNinjas.model.MissoesModel;
import com.java10x.CadastroNinjas.repository.MissoesRepository;

@Service
public class MissaoService {

    private final MissoesRepository missoesRepository;

    public MissaoService(MissoesRepository missoesRepository) {
        this.missoesRepository = missoesRepository;
    }

    private MissaoDTO mapToDTO(MissoesModel model) {
        if (model == null) return null;
        MissaoDTO dto = new MissaoDTO();
        dto.setId(model.getId());
        dto.setNome(model.getNome());
        dto.setDificuldade(model.getDificuldade());
        return dto;
    }

    private MissoesModel mapToModel(MissaoDTO dto) {
        if (dto == null) return null;
        MissoesModel model = new MissoesModel();
        model.setId(dto.getId());
        model.setNome(dto.getNome());
        model.setDificuldade(dto.getDificuldade());
        return model;
    }

    public MissaoDTO registrarMissao(MissaoDTO missaoDTO) {
        MissoesModel missaoModel = mapToModel(missaoDTO);
        MissoesModel missaoSalva = missoesRepository.save(missaoModel);
        return mapToDTO(missaoSalva);
    }
    
    public List<MissaoDTO> listarMissoes() {
        return missoesRepository.findAll().stream()
                .map(this::mapToDTO)
                .collect(java.util.stream.Collectors.toList());
    }

    public MissaoDTO buscarId(Long id) {
        java.util.Optional<MissoesModel> missaoOptional = missoesRepository.findById(id);
        return missaoOptional.map(this::mapToDTO).orElse(null);
    }

    public MissaoDTO alterarMissao(Long id, MissaoDTO missaoDTO) {
        return missoesRepository.findById(id)
                .map(missaoExistente -> {
                    missaoExistente.setNome(missaoDTO.getNome());
                    missaoExistente.setDificuldade(missaoDTO.getDificuldade());
                    MissoesModel missaoAtualizada = missoesRepository.save(missaoExistente);
                    return mapToDTO(missaoAtualizada);
                })
                .orElse(null);
    }

    public void deletarMissao(Long id) {
        missoesRepository.deleteById(id);
    }
}