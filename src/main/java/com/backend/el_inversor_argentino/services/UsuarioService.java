package com.backend.el_inversor_argentino.services;

import com.backend.el_inversor_argentino.dto.AutorDTO;
import com.backend.el_inversor_argentino.dto.UsuarioDTO;
import com.backend.el_inversor_argentino.dto.salida.TokenSalidaDTO;
import com.backend.el_inversor_argentino.entity.Articulo;
import com.backend.el_inversor_argentino.entity.Comentario;
import com.backend.el_inversor_argentino.entity.Role;
import com.backend.el_inversor_argentino.entity.Usuario;
import com.backend.el_inversor_argentino.exception.NotFoundException;
import com.backend.el_inversor_argentino.repository.ArticuloRepository;
import com.backend.el_inversor_argentino.repository.ComentarioRepository;
import com.backend.el_inversor_argentino.repository.RoleRepository;
import com.backend.el_inversor_argentino.repository.UsuarioRepository;
import com.backend.el_inversor_argentino.util.ReferencedWarning;
import com.backend.el_inversor_argentino.util.RoleConstants;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@Transactional
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final ArticuloRepository articuloRepository;
    private final ComentarioRepository comentarioRepository;

    public UsuarioService(final UsuarioRepository usuarioRepository,
                          final ArticuloRepository articuloRepository,
                          final ComentarioRepository comentarioRepository) {
        this.usuarioRepository = usuarioRepository;
        this.articuloRepository = articuloRepository;
        this.comentarioRepository = comentarioRepository;
    }

    public List<UsuarioDTO> findAll() {
        final List<Usuario> usuarios = usuarioRepository.findAll(Sort.by("id"));
        return usuarios.stream()
                .map(usuario -> mapToDTO(usuario, new UsuarioDTO()))
                .toList();
    }

    public UsuarioDTO get(final Long id) {
        return usuarioRepository.findById(id)
                .map(usuario -> mapToDTO(usuario, new UsuarioDTO()))
                .orElseThrow(NotFoundException::new);
    }

    public Long create(final UsuarioDTO usuarioDTO) {
        final Usuario usuario = new Usuario();
        mapToEntity(usuarioDTO, usuario);
        return usuarioRepository.save(usuario).getId();
    }

    public void update(final Long id, final UsuarioDTO usuarioDTO) {
        final Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(NotFoundException::new);
        mapToEntity(usuarioDTO, usuario);
        usuarioRepository.save(usuario);
    }

    public void delete(final Long id) {
        usuarioRepository.deleteById(id);
    }

    private UsuarioDTO mapToDTO(final Usuario usuario, final UsuarioDTO usuarioDTO) {
        usuarioDTO.setId(usuario.getId());
        usuarioDTO.setApellido(usuario.getApellido());
        usuarioDTO.setPassword(usuario.getPassword());
        usuarioDTO.setEmail(usuario.getEmail());
        usuarioDTO.setFechaRegistro(usuario.getFechaRegistro());
        usuarioDTO.setNombre(usuario.getNombre());
        usuarioDTO.setFavoritosArticuloes(usuario.getFavoritosArticuloes().stream()
                .map(articulo -> articulo.getId())
                .toList());
        return usuarioDTO;
    }

    private Usuario mapToEntity(final UsuarioDTO usuarioDTO, final Usuario usuario) {
        usuario.setApellido(usuarioDTO.getApellido());
        usuario.setPassword(usuarioDTO.getPassword());
        usuario.setEmail(usuarioDTO.getEmail());
        usuario.setFechaRegistro(usuarioDTO.getFechaRegistro());
        usuario.setNombre(usuarioDTO.getNombre());
        final List<Articulo> favoritosArticuloes = articuloRepository.findAllById(
                usuarioDTO.getFavoritosArticuloes() == null ? Collections.emptyList() : usuarioDTO.getFavoritosArticuloes());
        if (favoritosArticuloes.size() != (usuarioDTO.getFavoritosArticuloes() == null ? 0 : usuarioDTO.getFavoritosArticuloes().size())) {
            throw new NotFoundException("one of favoritosArticuloes not found");
        }
        usuario.setFavoritosArticuloes(new HashSet<>(favoritosArticuloes));
        return usuario;
    }

    public ReferencedWarning getReferencedWarning(final Long id) {
        final ReferencedWarning referencedWarning = new ReferencedWarning();
        final Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(NotFoundException::new);
        final Comentario usuarioComentario = comentarioRepository.findFirstByUsuario(usuario);
        if (usuarioComentario != null) {
            referencedWarning.setKey("usuario.comentario.usuario.referenced");
            referencedWarning.addParam(usuarioComentario.getId());
            return referencedWarning;
        }
        return null;
    }

}