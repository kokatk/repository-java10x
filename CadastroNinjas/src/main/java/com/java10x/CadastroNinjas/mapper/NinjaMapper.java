package com.java10x.CadastroNinjas.mapper;

import org.mapstruct.*;

import com.java10x.CadastroNinjas.dto.MissaoDTO;
import com.java10x.CadastroNinjas.dto.NinjaDTO;
import com.java10x.CadastroNinjas.model.MissoesModel;
import com.java10x.CadastroNinjas.model.NinjaModel;

import java.util.List;

@Mapper(componentModel = "spring") // Integra com Spring para @Autowired
public interface NinjaMapper {

    // Mapeamento de NinjaModel para NinjaDTO
    @Mappings({
        @Mapping(target = "id", source = "Id"), // Campos com nomes diferentes
        @Mapping(target = "missao", source = "missoes") // Mapeia o ManyToOne
    })
    NinjaDTO toDTO(NinjaModel ninja);

    // Mapeamento reverso: NinjaDTO para NinjaModel
    @Mappings({
        @Mapping(target = "Id", source = "id"),
        @Mapping(target = "missoes", source = "missao")
    })
    NinjaModel toModel(NinjaDTO dto);

    // Para listas de Ninjas
    List<NinjaDTO> toDTOList(List<NinjaModel> ninjas);

    // Mapeamento de MissaoModel para MissaoDTO
    @Mapping(target = "ninjas", source = "ninjas") // Mapeia a lista OneToMany
    MissaoDTO toMissaoDTO(MissoesModel missao);

    // Mapeamento reverso para Missao
    @Mapping(target = "ninjas", source = "ninjas")
    MissoesModel toMissaoModel(MissaoDTO dto);

    // Método para lidar com o ciclo bidirecional (seta a referência reversa após mapeamento)
    @AfterMapping
    default void linkNinjas(@MappingTarget MissaoDTO missaoDTO) {
        if (missaoDTO.getNinjas() != null) {
            missaoDTO.getNinjas().forEach(ninjaDTO -> ninjaDTO.setMissoes(missaoDTO));
        }
    }

    // Similar para o outro lado, se necessário
    @AfterMapping
    default void linkMissao(@MappingTarget NinjaDTO ninjaDTO, NinjaModel ninjaModel) {
        if (ninjaDTO.getMissoes() != null && ninjaModel.getMissoes() != null) {
            // Lógica adicional se precisar sincronizar
        }
    }
}
