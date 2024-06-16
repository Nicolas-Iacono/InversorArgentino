package com.backend.el_inversor_argentino.repository;

import com.backend.el_inversor_argentino.entity.Articulo;
import com.backend.el_inversor_argentino.entity.Comentario;
import com.backend.el_inversor_argentino.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ComentarioRepository extends JpaRepository<Comentario, Long> {

    Comentario findFirstByArticulo(Articulo articulo);

    Comentario findFirstByUsuario(Usuario usuario);

    List<Comentario> findByUsuarioId(Long userId);
}