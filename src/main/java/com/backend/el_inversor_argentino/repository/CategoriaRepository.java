package com.backend.el_inversor_argentino.repository;

import com.backend.el_inversor_argentino.entity.Articulo;
import com.backend.el_inversor_argentino.entity.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CategoriaRepository extends JpaRepository<Categoria, Long> {

    Categoria findFirstByArticuloCategoriaArticuloes(Articulo articulo);

    List<Categoria> findAllByArticuloCategoriaArticuloes(Articulo articulo);

}
