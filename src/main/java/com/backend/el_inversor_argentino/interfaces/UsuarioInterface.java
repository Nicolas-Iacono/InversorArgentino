package com.backend.el_inversor_argentino.interfaces;

import com.backend.el_inversor_argentino.dto.AutorDTO;
import com.backend.el_inversor_argentino.dto.UsuarioDTO;
import com.backend.el_inversor_argentino.dto.salida.TokenSalidaDTO;

public interface UsuarioInterface {

    TokenSalidaDTO createUser(UsuarioDTO usuarioDTO) throws Exception;

    TokenSalidaDTO createUserAdmin(AutorDTO autorDTO) throws Exception;
}
