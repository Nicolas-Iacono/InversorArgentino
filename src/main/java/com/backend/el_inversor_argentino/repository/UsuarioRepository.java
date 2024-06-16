package com.backend.el_inversor_argentino.repository;

import com.backend.el_inversor_argentino.entity.Articulo;
import com.backend.el_inversor_argentino.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    Usuario findFirstByFavoritosArticuloes(Articulo articulo);

    List<Usuario> findAllByFavoritosArticuloes(Articulo articulo);


}
