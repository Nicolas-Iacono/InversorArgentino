package com.backend.el_inversor_argentino.controller;

import com.backend.el_inversor_argentino.dto.ArticuloDTO;
import com.backend.el_inversor_argentino.entity.Articulo;
import com.backend.el_inversor_argentino.services.ArticuloService;
import com.backend.el_inversor_argentino.util.ReferencedException;
import com.backend.el_inversor_argentino.util.ReferencedWarning;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping(value = "/api/articulos", produces = MediaType.APPLICATION_JSON_VALUE)
public class ArticuloController {

    private final ArticuloService articuloService;

    public ArticuloController(final ArticuloService articuloService) {
        this.articuloService = articuloService;
    }

    @GetMapping
    public ResponseEntity<List<ArticuloDTO>> getAllArticulos() {
        return ResponseEntity.ok(articuloService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ArticuloDTO> getArticulo(@PathVariable(name = "id") final Long id) {
        return ResponseEntity.ok(articuloService.get(id));
    }

    @PostMapping
    public ResponseEntity<Long> createArticulo(@RequestBody @Valid final ArticuloDTO articuloDTO) {
        final Long createdId = articuloService.create(articuloDTO);
        return new ResponseEntity<>(createdId, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Long> updateArticulo(@PathVariable(name = "id") final Long id,
                                               @RequestBody @Valid final ArticuloDTO articuloDTO) {
        articuloService.update(id, articuloDTO);
        return ResponseEntity.ok(id);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteArticulo(@PathVariable(name = "id") final Long id) {

        articuloService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/ultimos-cuatro")
    public ResponseEntity<List<ArticuloDTO>> getLastFourArticulos() {
        List<ArticuloDTO> articulos = articuloService.getLatestFourArticles();
        return ResponseEntity.ok(articulos);
    }

}
