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
    /*
    * "id": 8,
    "destinatario": "Hugo Voluarte",
    "dni": "77349223",
    "zip": "13301",
    "direccion": "lima 545",
    "pais": "Perú",
    "region": "La Libertad",
    "provincia": "Sánchez Carrión ",
    "distrito": "Huamachuco",
    "telefono": "+51995052331",
    "principal": true,*/

    @PostMapping("/direcciones")
    public Direccion create(@RequestBody Direccion direccion) {
        //Si ya existe una direccion principal, se cambia a false
        if(direccion.isPrincipal()){
            Direccion direccionPrincipal = direccionService
                    .findByClientePrincipal(direccion.getCliente().getId());
            if(direccionPrincipal != null){
                direccionPrincipal.setPrincipal(false);
                direccionService.save(direccionPrincipal);
            }
            direccionService.save(direccion);
        }
        return direccionService.save(direccion);
    }

    //Obtener por id
    @GetMapping("/direcciones/{id}")
    public List<Direccion> show(@PathVariable Integer id) {
        return direccionService.findByCliente(id);
    }

    @DeleteMapping("/direcciones/{id}")
    public void delete(@PathVariable Integer id) {
        direccionService.delete(id);
    }
    //Cambiar direccion principal
    @PutMapping("/direcciones/{id}/{idCliente}")
    public Direccion update( @PathVariable Integer id, @PathVariable Integer idCliente) {
        List<Direccion> direcciones = direccionService.findByCliente(idCliente);
        for (Direccion d: direcciones) {
            if(d.isPrincipal() == true){
                d.setPrincipal(false);
                direccionService.save(d);
            }
        }
        Direccion direccionActual = direccionService.findById(id);
        direccionActual.setPrincipal(true);
        return direccionService.save(direccionActual);
    }


}
