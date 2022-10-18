package com.codej.controller;

import com.codej.model.Product;
import com.codej.model.Venta;
import com.codej.service.ICarritoService;
import com.codej.service.IProductService;
import com.codej.service.IVentaService;
import com.codej.serviceImp.UsuarioService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@CrossOrigin(origins = {"http://localhost:4200"})
@RestController
@RequestMapping("/api")
public class VentaRestController {
    private IVentaService ventaService;
    private IProductService productService;

    private ICarritoService carritoService;

    //imprmir el objeto
    private static final Logger logger = LoggerFactory.getLogger(UsuarioService.class);


    public VentaRestController(IVentaService ventaService, IProductService productService,
                               ICarritoService carritoService) {
        this.carritoService = carritoService;
        this.productService = productService;
        this.ventaService = ventaService;
    }
    //Obtener todas las ventas de un cliente
    @GetMapping("/ventas/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Venta show(@PathVariable Integer id){
        return ventaService.findById(id);
    }
    //Crear factura


    @PostMapping("/ventas")
    public ResponseEntity<?> create (@Valid @RequestBody Venta venta, BindingResult result){
        venta.setNventa("N"+new Date().getTime());
        Product productActual = null;
        //Relacionar venta con dventa

        Venta ventaNew = null;
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
            ventaNew = ventaService.save(venta);
           //Disminuir el stock
            productActual = productService.findById(venta.getDventas().get(0).getProducto().getId());
            productActual.setStock(productActual.getStock()-venta.getDventas().get(0).getCantidad());
            productActual.setStock(productActual.getStock());
            productService.save(productActual);

            //limpiar carrito
            carritoService.deleteByCliente(venta.getCliente().getId());


        }catch (DataAccessException e){
            response.put("mensaje", "Error al realizar el insert en la base de datos");
            response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        response.put("mensaje", "la venta ha sido creada con éxito");
        response.put("venta", ventaNew);
        return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);

    }


    public String create(@RequestBody Venta venta){
        //imprimir el objeto
        logger.info("Venta: "+venta);
        return "create";
    }


}
