package com.codej.controller;

import com.codej.model.Categoria;
import com.codej.model.Cupon;
import com.codej.model.Product;
import com.codej.service.ICuponService;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    @Secured("ROLE_ADMIN")
    @PostMapping("/cupones")
    public Cupon create(@RequestBody Cupon cupon) {
        return cuponService.save(cupon);
    }

    @GetMapping("/cupones/{id}")
    public Cupon show(@PathVariable Integer id) {
        return cuponService.findById(id);
    }
    @Secured("ROLE_ADMIN")
    @DeleteMapping("/cupones/{id}")
    public void delete(@PathVariable Integer id) {
        cuponService.delete(id);
    }
    @Secured("ROLE_ADMIN")
    @PutMapping("/cupones/{id}")
    public Cupon update(@RequestBody Cupon cupon, @PathVariable Integer id) {
        Cupon cuponActual = cuponService.findById(id);
        cuponActual.setCodigo(cupon.getCodigo());
        cuponActual.setTipo(cupon.getTipo());
        cuponActual.setValor(cupon.getValor());
        cuponActual.setLimite(cupon.getLimite());
        return cuponService.save(cuponActual);
    }
    @GetMapping("/cupones/aplicar/{codigo}")
    public ResponseEntity<?> buscar (@PathVariable String codigo){
        Cupon cupon = null;
        Map<String, Object> response = new HashMap<>();
        try {
            cupon = cuponService.findByCodigo(codigo);

            if(cupon!=null){
                if(cupon.getLimite()==0){
                    response.put("mensaje", "El cupon ya superó el limite");
                    return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
                }
            }else {
                response.put("mensaje", "El cupon no existe");
                return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
            }


        } catch (DataAccessException e) {
            response.put("mensaje", "Error al realizar la consulta en la base de datos");
            response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<Map<String, Object>>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        if (cupon ==null){
            response.put("mensaje", "El cupon ID: ".concat(codigo.concat(" no existe en la base de datos")));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
        }
        return  new ResponseEntity<Cupon>(cupon, HttpStatus.OK);

    }
    //Disminuir el limite del cupon

    //Buscar cupon activo
    @GetMapping("/cupon/activo")
    public Cupon cuponActivo() {
        List<Cupon> cupones = cuponService.findAll();
        Cupon cuponActivo = null;
        for (Cupon cupon: cupones) {
            if(cupon.getLimite()>0){
                cuponActivo = cupon;
            }
        }
        return cuponActivo;
    }





}
