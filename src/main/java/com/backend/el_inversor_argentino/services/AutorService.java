package com.backend.el_inversor_argentino.services;

import com.backend.el_inversor_argentino.dto.AutorDTO;
import com.backend.el_inversor_argentino.entity.Articulo;
import com.backend.el_inversor_argentino.entity.Autor;
import com.backend.el_inversor_argentino.exception.NotFoundException;
import com.backend.el_inversor_argentino.repository.ArticuloRepository;
import com.backend.el_inversor_argentino.repository.AutorRepository;
import com.backend.el_inversor_argentino.util.ReferencedWarning;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AutorService {

    private final AutorRepository autorRepository;
    private final ArticuloRepository articuloRepository;

    public AutorService(final AutorRepository autorRepository,
                        final ArticuloRepository articuloRepository) {
        this.autorRepository = autorRepository;
        this.articuloRepository = articuloRepository;
    }

    public List<AutorDTO> findAll() {
        final List<Autor> autors = autorRepository.findAll(Sort.by("id"));
        return autors.stream()
                .map(autor -> mapToDTO(autor, new AutorDTO()))
                .toList();
    }

    public AutorDTO get(final Long id) {
        return autorRepository.findById(id)
                .map(autor -> mapToDTO(autor, new AutorDTO()))
                .orElseThrow(NotFoundException::new);
    }

    public Long create(final AutorDTO autorDTO) {
        final Autor autor = new Autor();
        mapToEntity(autorDTO, autor);
        return autorRepository.save(autor).getId();
    }

    public void update(final Long id, final AutorDTO autorDTO) {
        final Autor autor = autorRepository.findById(id)
                .orElseThrow(NotFoundException::new);
        mapToEntity(autorDTO, autor);
        autorRepository.save(autor);
    }

    public void delete(final Long id) {
        autorRepository.deleteById(id);
    }

    private AutorDTO mapToDTO(final Autor autor, final AutorDTO autorDTO) {
        autorDTO.setId(autor.getId());
        autorDTO.setApellido(autor.getApellido());
        autorDTO.setPassword(autor.getPassword());
        autorDTO.setEmail(autor.getEmail());
        autorDTO.setNombre(autor.getNombre());
        autorDTO.setImagenPerfil(autor.getImagenPerfil());
        return autorDTO;
    }

    private Autor mapToEntity(final AutorDTO autorDTO, final Autor autor) {
        autor.setApellido(autorDTO.getApellido());
        autor.setPassword(autorDTO.getPassword());
        autor.setEmail(autorDTO.getEmail());
        autor.setNombre(autorDTO.getNombre());
        autor.setImagenPerfil(autor.getImagenPerfil());
        return autor;
    }


}
