package com.backend.el_inversor_argentino.controller;

import com.backend.el_inversor_argentino.dto.AutorDTO;
import com.backend.el_inversor_argentino.services.AutorService;
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
@RequestMapping(value = "/api/autors", produces = MediaType.APPLICATION_JSON_VALUE)
public class AutorController {

    private final AutorService autorService;

    public AutorController(final AutorService autorService) {
        this.autorService = autorService;
    }

    @GetMapping
    public ResponseEntity<List<AutorDTO>> getAllAutors() {
        return ResponseEntity.ok(autorService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<AutorDTO> getAutor(@PathVariable(name = "id") final Long id) {
        return ResponseEntity.ok(autorService.get(id));
    }

    @PostMapping
    public ResponseEntity<Long> createAutor(@RequestBody @Valid final AutorDTO autorDTO) {
        final Long createdId = autorService.create(autorDTO);
        return new ResponseEntity<>(createdId, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Long> updateAutor(@PathVariable(name = "id") final Long id,
                                            @RequestBody @Valid final AutorDTO autorDTO) {
        autorService.update(id, autorDTO);
        return ResponseEntity.ok(id);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAutor(@PathVariable(name = "id") final Long id) {

        autorService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
