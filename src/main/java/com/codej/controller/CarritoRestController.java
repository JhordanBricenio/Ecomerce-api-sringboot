package com.codej.controller;

import com.codej.model.Admin;
import com.codej.model.Carrito;
import com.codej.model.Product;
import com.codej.service.ICarritoService;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@CrossOrigin(origins = {"http://localhost:4200"})
@RestController
@RequestMapping("/api")
public class CarritoRestController {
    private ICarritoService carritoService;

    public CarritoRestController(ICarritoService carritoService) {
        this.carritoService = carritoService;
    }

    //listar el carrito por cliente
    @GetMapping("/carrito/{id}")
    public List<Carrito> index(@PathVariable Integer id) {
        return carritoService.findByCliente(id);
    }

    @GetMapping("/carrito")
    public List<Carrito> index() {
        return carritoService.findAll();
    }

    //Crear un nuevo producto
    @PostMapping("/carrito")
    public ResponseEntity<?> create (@Valid @RequestBody Carrito carrito, BindingResult result){
        Carrito carritoNew = null;
        Map<String, Object> response = new HashMap<>();
        if (result.hasErrors()){
            List<String> errors= result.getFieldErrors()
                    .stream()
                    .map(err -> "El campo '"+err.getField()+"' "+err.getDefaultMessage())
                    .collect(Collectors.toList());
            response.put("errors", errors);
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
        }
        try {
            carritoNew = carritoService.save(carrito);
        }catch (DataAccessException e){
            response.put("mensaje", "Error al realizar el insert en la base de datos");
            response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        response.put("mensaje", "El carrito ha sido registrado con éxito");
        response.put("admin", carritoNew);
        return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);

    }



    @DeleteMapping("/carrito/{id}")
    public ResponseEntity<?> delete(@PathVariable Integer id){
        Map<String, Object> response = new HashMap<>();
        try {
            carritoService.delete(id);
        } catch (DataAccessException e) {
            response.put("mensaje", "Error al eliminar producto del carrito en la base de datos");
            response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        response.put("mensaje", "El producto del carrito fue eliminado con éxito");
        return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
    }

}
