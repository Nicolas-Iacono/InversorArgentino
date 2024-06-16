package com.backend.el_inversor_argentino.repository;


import com.backend.el_inversor_argentino.entity.Articulo;
import com.backend.el_inversor_argentino.entity.Autor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ArticuloRepository extends JpaRepository<Articulo, Long> {

    Articulo findFirstByAutor(Autor autor);

    @Query("SELECT a FROM Articulo a ORDER BY a.fechaPublicacion DESC")
    List<Articulo> findLastFour(Pageable pageable);

}
