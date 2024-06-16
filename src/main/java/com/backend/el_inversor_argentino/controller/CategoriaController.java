package com.backend.el_inversor_argentino.controller;

import com.backend.el_inversor_argentino.dto.CategoriaDTO;
import com.backend.el_inversor_argentino.services.CategoriaService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping(value = "/api/categorias", produces = MediaType.APPLICATION_JSON_VALUE)
public class CategoriaController {
    private final CategoriaService categoriaService;

    public CategoriaController(final CategoriaService categoriaService) {
        this.categoriaService = categoriaService;
    }

    @GetMapping
    public ResponseEntity<List<CategoriaDTO>> getAllCategorias() {
        return ResponseEntity.ok(categoriaService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<CategoriaDTO> getCategoria(@PathVariable(name = "id") final Long id) {
        return ResponseEntity.ok(categoriaService.get(id));
    }

    @PostMapping
    public ResponseEntity<Long> createCategoria(
            @RequestBody @Valid final CategoriaDTO categoriaDTO) {
        final Long createdId = categoriaService.create(categoriaDTO);
        return new ResponseEntity<>(createdId, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Long> updateCategoria(@PathVariable(name = "id") final Long id,
                                                @RequestBody @Valid final CategoriaDTO categoriaDTO) {
        categoriaService.update(id, categoriaDTO);
        return ResponseEntity.ok(id);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCategoria(@PathVariable(name = "id") final Long id) {
        categoriaService.delete(id);
        return ResponseEntity.noContent().build();
    }


}
