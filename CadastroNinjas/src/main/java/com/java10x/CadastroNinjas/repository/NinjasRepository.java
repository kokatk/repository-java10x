package com.java10x.CadastroNinjas.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.java10x.CadastroNinjas.model.NinjaModel;

public interface NinjasRepository extends JpaRepository<NinjaModel, Long> {

}
