package com.codej.controller;

import com.codej.model.Categoria;
import com.codej.model.Promocion;
import com.codej.service.IPromocionService;
import com.codej.service.IUploadService;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.*;

@CrossOrigin(origins = {"http://localhost:4200"})
@RestController
@RequestMapping("/api")
public class PromocionRestController {
    private final IPromocionService promocionService;
    private IUploadService uploadService;

    public PromocionRestController(IPromocionService promocionService, IUploadService uploadService) {
        this.uploadService = uploadService;
        this.promocionService = promocionService;
    }
    @GetMapping("/promocion")
    public List<Promocion> index() {
        return promocionService.findAll();
    }
    //Buscar por titulo
    @GetMapping("/promocion/buscar/{titulo}")
    public Promocion buscar(@PathVariable String titulo) {
        return promocionService.findByTitulo(titulo);
    }

    @Secured("ROLE_ADMIN")
    @PostMapping("/promocion")
    public Promocion create(@RequestBody Promocion promocion) {
        return promocionService.save(promocion);
    }

    @GetMapping("/promocion/{id}")
    public Promocion show(@PathVariable Integer id) {
        return promocionService.findById(id);
    }

    @Secured("ROLE_ADMIN")
    @DeleteMapping("/promocion/{id}")
    public void delete(@PathVariable Integer id) {
        promocionService.delete(id);
    }

    @Secured("ROLE_ADMIN")
    @PutMapping("/promocion/{id}")
    public Promocion update(@RequestBody Promocion cupon, @PathVariable Integer id) {
        Promocion promocionActual = promocionService.findById(id);
        promocionActual.setTitulo(cupon.getTitulo());
        promocionActual.setDescuento(cupon.getDescuento());
        promocionActual.setFechainicio(cupon.getFechainicio());
        promocionActual.setFechafin(cupon.getFechafin());
        return promocionService.save(promocionActual);
    }
    @GetMapping("/uploadss/img/{nombreFoto:.+}")
    public ResponseEntity<Resource> verFoto(@PathVariable String nombreFoto){
        Resource recurso=null;
        try {
            recurso=uploadService.cargar(nombreFoto);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        HttpHeaders cabecera= new HttpHeaders();
        cabecera.add(HttpHeaders.CONTENT_DISPOSITION,"attachment; filename=\""+recurso.getFilename()+"\"");
        return new ResponseEntity<Resource>(recurso,cabecera, HttpStatus.OK);
    }
    //Guardar foto
    @Secured("ROLE_ADMIN")
    @PostMapping("/promocion/upload")
    public ResponseEntity<?> upload(@RequestParam("archivo") MultipartFile archivo, @RequestParam("id") Integer id) {
        Map<String, Object> response = new HashMap<>();
        Promocion promocion = promocionService.findById(id);
        if (!archivo.isEmpty()) {
            String nombreArchivo = null;
            try {
                nombreArchivo = uploadService.copiar(archivo);
            } catch (IOException e) {
                response.put("mensaje", "Error al subir la imagen del cliente");
                response.put("error", e.getMessage().concat(": ").concat(e.getCause().getMessage()));
                return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
            }
            String nombreFotoAnterior = promocion.getBanner();
            uploadService.eliminar(nombreFotoAnterior);
            promocion.setBanner(nombreArchivo);
            promocionService.save(promocion);
            response.put("promocion", promocion);
            response.put("mensaje", "Has subido correctamente la imagen: " + nombreArchivo);
        }
        return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
    }
    //Obenter promocion activa
    @GetMapping("/promocion/activa")
    public List<Promocion> activa() {
       List<Promocion> activa=promocionService.findAll();
       List<Promocion> activa2= new ArrayList<>();
       //Obenter fecha actual
        Date fechaActual = new Date();
        for (Promocion promocion:activa) {
            if (promocion.getFechainicio().before(fechaActual) && promocion.getFechafin().after(fechaActual)) {
                activa2.add(promocion);
            }
        }
        return activa2;
    }

}
