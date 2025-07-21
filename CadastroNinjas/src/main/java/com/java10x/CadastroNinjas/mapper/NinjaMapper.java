package com.java10x.CadastroNinjas.mapper;

import org.springframework.stereotype.Component;

import com.java10x.CadastroNinjas.dto.NinjaDTO;
import com.java10x.CadastroNinjas.model.NinjaModel;
import com.java10x.CadastroNinjas.dto.MissaoDTO;
import com.java10x.CadastroNinjas.model.MissoesModel;

@Component
public class NinjaMapper {

    public NinjaModel map(NinjaDTO ninjaDTO) {
        NinjaModel ninjaModel = new NinjaModel();
        ninjaModel.setId(ninjaDTO.getId());
        ninjaModel.setNome(ninjaDTO.getNome());
        ninjaModel.setEmail(ninjaDTO.getEmail());
        ninjaModel.setRank(ninjaDTO.getRank());
        ninjaModel.setImgUrl(ninjaDTO.getImgUrl());
        ninjaModel.setIdade(ninjaDTO.getIdade());
        ninjaModel.setMissao(mapToModel(ninjaDTO.getMissao()));
        return ninjaModel;
    }

    public NinjaDTO map(NinjaModel ninjaModel) {
        NinjaDTO ninjaDTO = new NinjaDTO();
        ninjaDTO.setId(ninjaModel.getId());
        ninjaDTO.setNome(ninjaModel.getNome());
        ninjaDTO.setEmail(ninjaModel.getEmail());
        ninjaDTO.setRank(ninjaModel.getRank());
        ninjaDTO.setImgUrl(ninjaModel.getImgUrl());
        ninjaDTO.setIdade(ninjaModel.getIdade());
        ninjaDTO.setMissao(mapToDTO(ninjaModel.getMissao()));
        return ninjaDTO;
    }

    private MissoesModel mapToModel(MissaoDTO missaoDTO) {
        if (missaoDTO == null) return null;
        MissoesModel model = new MissoesModel();
        model.setId(missaoDTO.getId());
        model.setNome(missaoDTO.getNome());
        model.setDificuldade(missaoDTO.getDificuldade());
        return model;
    }

    private MissaoDTO mapToDTO(MissoesModel missaoModel) {
        if (missaoModel == null) return null;
        MissaoDTO dto = new MissaoDTO();
        dto.setId(missaoModel.getId());
        dto.setNome(missaoModel.getNome());
        dto.setDificuldade(missaoModel.getDificuldade());
        return dto;
    }
}