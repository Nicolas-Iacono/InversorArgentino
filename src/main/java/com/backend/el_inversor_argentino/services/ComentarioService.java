package com.backend.el_inversor_argentino.services;


import com.backend.el_inversor_argentino.dto.ComentarioDTO;
import com.backend.el_inversor_argentino.entity.Articulo;
import com.backend.el_inversor_argentino.entity.Comentario;
import com.backend.el_inversor_argentino.entity.Usuario;
import com.backend.el_inversor_argentino.exception.NotFoundException;
import com.backend.el_inversor_argentino.repository.ArticuloRepository;
import com.backend.el_inversor_argentino.repository.ComentarioRepository;
import com.backend.el_inversor_argentino.repository.UsuarioRepository;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ComentarioService {

    private final ComentarioRepository comentarioRepository;
    private final ArticuloRepository articuloRepository;
    private final UsuarioRepository usuarioRepository;

    public ComentarioService(final ComentarioRepository comentarioRepository,
                             final ArticuloRepository articuloRepository,
                             final UsuarioRepository usuarioRepository) {
        this.comentarioRepository = comentarioRepository;
        this.articuloRepository = articuloRepository;
        this.usuarioRepository = usuarioRepository;
    }

    public List<ComentarioDTO> findAll() {
        final List<Comentario> comentarios = comentarioRepository.findAll(Sort.by("id"));
        return comentarios.stream()
                .map(comentario -> mapToDTO(comentario, new ComentarioDTO()))
                .toList();
    }

    public ComentarioDTO get(final Long id) {
        return comentarioRepository.findById(id)
                .map(comentario -> mapToDTO(comentario, new ComentarioDTO()))
                .orElseThrow(NotFoundException::new);
    }

    public Long create(final ComentarioDTO comentarioDTO) {
        final Comentario comentario = new Comentario();
        mapToEntity(comentarioDTO, comentario);
        return comentarioRepository.save(comentario).getId();
    }

    public void update(final Long id, final ComentarioDTO comentarioDTO) {
        final Comentario comentario = comentarioRepository.findById(id)
                .orElseThrow(NotFoundException::new);
        mapToEntity(comentarioDTO, comentario);
        comentarioRepository.save(comentario);
    }

    public void delete(final Long id) {
        comentarioRepository.deleteById(id);
    }

    private ComentarioDTO mapToDTO(final Comentario comentario, final ComentarioDTO comentarioDTO) {
        comentarioDTO.setId(comentario.getId());
        comentarioDTO.setContenido(comentario.getContenido());
        comentarioDTO.setFecha(comentario.getFecha());
        comentarioDTO.setArticulo(comentario.getArticulo() == null ? null : comentario.getArticulo().getId());
        comentarioDTO.setUsuario(comentario.getUsuario() == null ? null : comentario.getUsuario().getId());
        return comentarioDTO;
    }

    private Comentario mapToEntity(final ComentarioDTO comentarioDTO, final Comentario comentario) {
        comentario.setContenido(comentarioDTO.getContenido());
        comentario.setFecha(comentarioDTO.getFecha());
        final Articulo articulo = comentarioDTO.getArticulo() == null ? null : articuloRepository.findById(comentarioDTO.getArticulo())
                .orElseThrow(() -> new NotFoundException("articulo not found"));
        comentario.setArticulo(articulo);
        final Usuario usuario = comentarioDTO.getUsuario() == null ? null : usuarioRepository.findById(comentarioDTO.getUsuario())
                .orElseThrow(() -> new NotFoundException("usuario not found"));
        comentario.setUsuario(usuario);
        return comentario;
    }

}