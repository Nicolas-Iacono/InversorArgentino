package com.backend.el_inversor_argentino.repository;

import com.backend.el_inversor_argentino.entity.Autor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AutorRepository extends JpaRepository<Autor, Long> {
}

