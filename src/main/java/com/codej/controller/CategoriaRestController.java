package com.codej.controller;

import com.codej.model.Categoria;
import com.codej.model.Product;
import com.codej.service.ICategoriaService;
import com.codej.service.IUploadService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import org.springframework.core.io.Resource;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin(origins = {"http://localhost:4200"})
@RestController
@RequestMapping("/api")
public class CategoriaRestController {
    private ICategoriaService categoriaService;
    private IUploadService uploadService;

    public CategoriaRestController(ICategoriaService categoriaService, IUploadService uploadService) {
        this.uploadService = uploadService;
        this.categoriaService = categoriaService;
    }
    //Listar todos las categorias
    @GetMapping("/categorias")
    public List<Categoria> index() {
        return categoriaService.findAll();
    }
    //Guardar una categoria
    @Secured("ROLE_ADMIN")
    @PostMapping("/categorias")
    public Categoria create(@RequestBody Categoria categoria) {
        return categoriaService.save(categoria);
    }
    //Actualizar categoria
    @Secured("ROLE_ADMIN")
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

    @Secured("ROLE_ADMIN")
    @DeleteMapping("/categorias/{id}")
    public void delete(@PathVariable Integer id) {
        categoriaService.delete(id);
    }

    //Ver foto
    @GetMapping("/uploads/img/{nombreFoto:.+}")
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
    @PostMapping("/categorias/upload")
    public ResponseEntity<?> upload(@RequestParam("archivo") MultipartFile archivo, @RequestParam("id") Integer id) {
        Map<String, Object> response = new HashMap<>();
        Categoria categoria = categoriaService.findById(id);
        if (!archivo.isEmpty()) {
            String nombreArchivo = null;
            try {
                nombreArchivo = uploadService.copiar(archivo);
            } catch (IOException e) {
                response.put("mensaje", "Error al subir la imagen del cliente");
                response.put("error", e.getMessage().concat(": ").concat(e.getCause().getMessage()));
                return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
            }
            String nombreFotoAnterior = categoria.getFoto();
            uploadService.eliminar(nombreFotoAnterior);
            categoria.setFoto(nombreArchivo);
            categoriaService.save(categoria);
            response.put("categoria", categoria);
            response.put("mensaje", "Has subido correctamente la imagen: " + nombreArchivo);
        }
        return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
    }

}
