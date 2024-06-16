package com.backend.el_inversor_argentino.services;

import com.backend.el_inversor_argentino.dto.CategoriaDTO;
import com.backend.el_inversor_argentino.entity.Articulo;
import com.backend.el_inversor_argentino.entity.Categoria;
import com.backend.el_inversor_argentino.exception.NotFoundException;
import com.backend.el_inversor_argentino.repository.ArticuloRepository;
import com.backend.el_inversor_argentino.repository.CategoriaRepository;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;

@Service
@Transactional
public class CategoriaService {

    private final CategoriaRepository categoriaRepository;
    private final ArticuloRepository articuloRepository;

    public CategoriaService(final CategoriaRepository categoriaRepository,
                            final ArticuloRepository articuloRepository) {
        this.categoriaRepository = categoriaRepository;
        this.articuloRepository = articuloRepository;
    }

    public List<CategoriaDTO> findAll() {
        final List<Categoria> categorias = categoriaRepository.findAll(Sort.by("id"));
        return categorias.stream()
                .map(categoria -> mapToDTO(categoria, new CategoriaDTO()))
                .toList();
    }

    public CategoriaDTO get(final Long id) {
        return categoriaRepository.findById(id)
                .map(categoria -> mapToDTO(categoria, new CategoriaDTO()))
                .orElseThrow(NotFoundException::new);
    }

    public Long create(final CategoriaDTO categoriaDTO) {
        final Categoria categoria = new Categoria();
        mapToEntity(categoriaDTO, categoria);
        return categoriaRepository.save(categoria).getId();
    }

    public void update(final Long id, final CategoriaDTO categoriaDTO) {
        final Categoria categoria = categoriaRepository.findById(id)
                .orElseThrow(NotFoundException::new);
        mapToEntity(categoriaDTO, categoria);
        categoriaRepository.save(categoria);
    }

    public void delete(final Long id) {
        categoriaRepository.deleteById(id);
    }

    private CategoriaDTO mapToDTO(final Categoria categoria, final CategoriaDTO categoriaDTO) {
        categoriaDTO.setId(categoria.getId());
        categoriaDTO.setNombre(categoria.getNombre());
        categoriaDTO.setArticuloCategoriaArticuloes(categoria.getArticuloCategoriaArticuloes().stream()
                .map(articulo -> articulo.getId())
                .toList());
        return categoriaDTO;
    }

    private Categoria mapToEntity(final CategoriaDTO categoriaDTO, final Categoria categoria) {
        categoria.setNombre(categoriaDTO.getNombre());
        final List<Articulo> articuloCategoriaArticuloes = articuloRepository.findAllById(
                categoriaDTO.getArticuloCategoriaArticuloes() == null ? Collections.emptyList() : categoriaDTO.getArticuloCategoriaArticuloes());
        if (articuloCategoriaArticuloes.size() != (categoriaDTO.getArticuloCategoriaArticuloes() == null ? 0 : categoriaDTO.getArticuloCategoriaArticuloes().size())) {
            throw new NotFoundException("one of articuloCategoriaArticuloes not found");
        }
        categoria.setArticuloCategoriaArticuloes(new HashSet<>(articuloCategoriaArticuloes));
        return categoria;
    }

}
