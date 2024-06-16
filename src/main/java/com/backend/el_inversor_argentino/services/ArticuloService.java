package com.backend.el_inversor_argentino.services;



import com.backend.el_inversor_argentino.dto.ArticuloDTO;
import com.backend.el_inversor_argentino.entity.Articulo;
import com.backend.el_inversor_argentino.entity.Autor;
import com.backend.el_inversor_argentino.entity.Comentario;
import com.backend.el_inversor_argentino.exception.NotFoundException;
import com.backend.el_inversor_argentino.repository.*;
import com.backend.el_inversor_argentino.util.ReferencedWarning;
import jakarta.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;


@Service
@Transactional
public class ArticuloService {

    private final ArticuloRepository articuloRepository;
    private final AutorRepository autorRepository;
    private final CategoriaRepository categoriaRepository;
    private final UsuarioRepository usuarioRepository;
    private final ComentarioRepository comentarioRepository;

    public ArticuloService(final ArticuloRepository articuloRepository,
                           final AutorRepository autorRepository, final CategoriaRepository categoriaRepository,
                           final UsuarioRepository usuarioRepository,
                           final ComentarioRepository comentarioRepository) {
        this.articuloRepository = articuloRepository;
        this.autorRepository = autorRepository;
        this.categoriaRepository = categoriaRepository;
        this.usuarioRepository = usuarioRepository;
        this.comentarioRepository = comentarioRepository;
    }

    public List<ArticuloDTO> findAll() {
        final List<Articulo> articuloes = articuloRepository.findAll(Sort.by("id"));
        return articuloes.stream()
                .map(articulo -> mapToDTO(articulo, new ArticuloDTO()))
                .toList();
    }

    public ArticuloDTO get(final Long id) {
        return articuloRepository.findById(id)
                .map(articulo -> mapToDTO(articulo, new ArticuloDTO()))
                .orElseThrow(NotFoundException::new);
    }


    public Long create(final ArticuloDTO articuloDTO) {
        final Articulo articulo = new Articulo();
        mapToEntity(articuloDTO, articulo);
        return articuloRepository.save(articulo).getId();
    }

    public void update(final Long id, final ArticuloDTO articuloDTO) {
        final Articulo articulo = articuloRepository.findById(id)
                .orElseThrow(NotFoundException::new);
        mapToEntity(articuloDTO, articulo);
        articuloRepository.save(articulo);
    }

    public void delete(final Long id) {
        final Articulo articulo = articuloRepository.findById(id)
                .orElseThrow(NotFoundException::new);
        // remove many-to-many relations at owning side
        categoriaRepository.findAllByArticuloCategoriaArticuloes(articulo)
                .forEach(categoria -> categoria.getArticuloCategoriaArticuloes().remove(articulo));
        usuarioRepository.findAllByFavoritosArticuloes(articulo)
                .forEach(usuario -> usuario.getFavoritosArticuloes().remove(articulo));
        articuloRepository.delete(articulo);
    }

    private ArticuloDTO mapToDTO(final Articulo articulo, final ArticuloDTO articuloDTO) {
        articuloDTO.setId(articulo.getId());
        articuloDTO.setFechaPublicacion(articulo.getFechaPublicacion());
        articuloDTO.setImagenes(articulo.getImagenes());
        articuloDTO.setParrafo(articulo.getParrafo());
        articuloDTO.setSubtitulo(articulo.getSubtitulo());
        articuloDTO.setTitulo(articulo.getTitulo());
        articuloDTO.setAutorId(articulo.getAutor() == null ? null : articulo.getAutor().getId());
        return articuloDTO;
    }

    private Articulo mapToEntity(final ArticuloDTO articuloDTO, final Articulo articulo) {
        articulo.setFechaPublicacion(articuloDTO.getFechaPublicacion());
        articulo.setImagenes(articuloDTO.getImagenes());
        articulo.setParrafo(articuloDTO.getParrafo());
        articulo.setSubtitulo(articuloDTO.getSubtitulo());
        articulo.setTitulo(articuloDTO.getTitulo());
        final Autor autor = articuloDTO.getAutorId() == null ? null : autorRepository.findById(articuloDTO.getAutorId())
                .orElseThrow(() -> new NotFoundException("autor not found"));
        articulo.setAutor(autor);
        return articulo;
    }

    public ReferencedWarning getReferencedWarning(final Long id) {
        final ReferencedWarning referencedWarning = new ReferencedWarning();
        final Articulo articulo = articuloRepository.findById(id)
                .orElseThrow(NotFoundException::new);
        final Comentario articuloComentario = comentarioRepository.findFirstByArticulo(articulo);
        if (articuloComentario != null) {
            referencedWarning.setKey("articulo.comentario.articulo.referenced");
            referencedWarning.addParam(articuloComentario.getId());
            return referencedWarning;
        }
        return null;
    }

    public List<ArticuloDTO> getLatestFourArticles() {
        Pageable pageable = PageRequest.of(0, 4, Sort.by("id").descending());
        List<Articulo> articulos = articuloRepository.findLastFour(pageable);
        return articulos.stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    private ArticuloDTO mapToDTO(Articulo articulo) {
        ArticuloDTO articuloDTO = new ArticuloDTO();
        articuloDTO.setId(articulo.getId());
        articuloDTO.setFechaPublicacion(articulo.getFechaPublicacion());
        articuloDTO.setImagenes(articulo.getImagenes());
        articuloDTO.setParrafo(articulo.getParrafo());
        articuloDTO.setSubtitulo(articulo.getSubtitulo());
        articuloDTO.setTitulo(articulo.getTitulo());

        if (articulo.getAutor() != null) {
            articuloDTO.setAutorId(articulo.getAutor().getId());
        }

        return articuloDTO;
    }

}

