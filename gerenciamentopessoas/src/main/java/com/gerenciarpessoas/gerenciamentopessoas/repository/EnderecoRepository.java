package com.gerenciarpessoas.gerenciamentopessoas.repository;

import com.gerenciarpessoas.gerenciamentopessoas.model.EnderecoModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EnderecoRepository extends JpaRepository<EnderecoModel, Long> {
}
