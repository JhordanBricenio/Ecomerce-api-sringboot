package com.codej.controller;

import com.codej.model.Categoria;
import com.codej.model.Cupon;
import com.codej.service.ICuponService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = {"http://localhost:4200"})
@RestController
@RequestMapping("/api")
public class CuponRestController {
    private final ICuponService cuponService;

    public CuponRestController(ICuponService cuponService) {
        this.cuponService = cuponService;
    }
    @GetMapping("/cupones")
    public List<Cupon> index() {
        return cuponService.findAll();
    }
    @PostMapping("/cupones")
    public Cupon create(@RequestBody Cupon cupon) {
        return cuponService.save(cupon);
    }

    @GetMapping("/cupones/{id}")
    public Cupon show(@PathVariable Integer id) {
        return cuponService.findById(id);
    }

    @DeleteMapping("/cupones/{id}")
    public void delete(@PathVariable Integer id) {
        cuponService.delete(id);
    }
    @PutMapping("/cupones/{id}")
    public Cupon update(@RequestBody Cupon cupon, @PathVariable Integer id) {
        Cupon cuponActual = cuponService.findById(id);
        cuponActual.setCodigo(cupon.getCodigo());
        cuponActual.setTipo(cupon.getTipo());
        cuponActual.setValor(cupon.getValor());
        cuponActual.setLimite(cupon.getLimite());
        return cuponService.save(cuponActual);
    }

}
