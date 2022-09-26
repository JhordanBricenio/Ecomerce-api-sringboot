package com.codej.controller;

import com.codej.model.Categoria;
import com.codej.model.Product;
import com.codej.service.ICategoriaService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = {"http://localhost:4200"})
@RestController
@RequestMapping("/api")
public class CategoriaRestController {
    private ICategoriaService categoriaService;

    public CategoriaRestController(ICategoriaService categoriaService) {
        this.categoriaService = categoriaService;
    }
    //Listar todos las categorias
    @GetMapping("/categorias")
    public List<Categoria> index() {
        return categoriaService.findAll();
    }
    //Guardar una categoria
    @PostMapping("/categorias")
    public Categoria create(@RequestBody Categoria categoria) {
        return categoriaService.save(categoria);
    }
    //Actualizar categoria
    @PutMapping("/categorias/{id}")
    public Categoria update(@RequestBody Categoria categoria, @PathVariable Integer id) {
        Categoria categoriaActual = categoriaService.findById(id);
        categoriaActual.setNombre(categoria.getNombre());
        categoriaActual.setDescripcion(categoria.getDescripcion());
        return categoriaService.save(categoriaActual);
    }
    //Obtener por id
    @GetMapping("/categorias/{id}")
    public Categoria show(@PathVariable Integer id) {
        return categoriaService.findById(id);
    }

    @DeleteMapping("/categorias/{id}")
    public void delete(@PathVariable Integer id) {
        categoriaService.delete(id);
    }

}
