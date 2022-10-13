package com.codej.controller;

import com.codej.model.Direccion;
import com.codej.service.IDireccionService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = {"http://localhost:4200"})
@RestController
@RequestMapping("/api")
public class DireccioRestController {
    private IDireccionService direccionService;

    public DireccioRestController(IDireccionService direccionService) {
        this.direccionService = direccionService;
    }

    //Obtener direcion principal de cliente
    @GetMapping("/direccion/cliente/{id}")
    public Direccion findByClientePrincipal(@PathVariable Integer id){
        return direccionService.findByClientePrincipal(id);
    }

    @PostMapping("/direcciones")
    public Direccion create(@RequestBody Direccion direccion) {
        return direccionService.save(direccion);
    }

 /*   @PutMapping("/direcciones/{id}")
    public Categoria update(@RequestBody Direccion direccion, @PathVariable Integer id) {
        Direccion direcionActual = direccionService.findById(id);
        direcionActual.setNombre(categoria.getNombre());
        direcionActual.setDescripcion(categoria.getDescripcion());
        return categoriaService.save(categoriaActual);
    }*/
    //Obtener por id
    @GetMapping("/direcciones/{id}")
    public List<Direccion> show(@PathVariable Integer id) {
        return direccionService.findByCliente(id);
    }

    @DeleteMapping("/direcciones/{id}")
    public void delete(@PathVariable Integer id) {
        direccionService.delete(id);
    }


}
