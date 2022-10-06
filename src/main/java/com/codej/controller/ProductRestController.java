package com.codej.controller;

import com.codej.model.*;
import com.codej.service.IProductService;
import com.codej.service.IUploadService;
import org.springframework.core.io.Resource;
import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@CrossOrigin(origins = {"http://localhost:4200"})
@RestController
@RequestMapping("/api")
public class ProductRestController {
    private IProductService productService;
    private IUploadService uploadService;

    public ProductRestController(IProductService productService, IUploadService uploadService
                                 ) {
        this.productService = productService;
        this.uploadService = uploadService;
    }
    //Listar todos los productos
    @GetMapping("/products")
    public List<Product> index() {
        return productService.findAll();
    }
    //Listar productos paginados
    @GetMapping("/products/page/{page}")
    public Page<Product> index(@PathVariable Integer page) {
        Pageable pageable = PageRequest.of(page, 6);
        return productService.findAll(pageable);
    }


    //Crear un nuevo producto
    @PostMapping("/products")
    public ResponseEntity<?> create (@Valid @RequestBody Product product, BindingResult result){
        Product productNew = null;
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
            productNew = productService.save(product);
            Inventario inventario = new Inventario();
            inventario.setProduct(productNew);
            inventario.setCantidad(productNew.getStock());
            inventario.setCreateAt(new Date());
            productService.guardar(inventario);

        }catch (DataAccessException e){
            response.put("mensaje", "Error al realizar el insert en la base de datos");
            response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        response.put("mensaje", "El producto ha sido creado con éxito");
        response.put("product", productNew);
        return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);

    }

    //Buscar producto por id
    @GetMapping("/products/{id}")
    public ResponseEntity<?> show (@PathVariable Integer id){
        Product product = null;
        Map<String, Object> response = new HashMap<>();
        try {
            product = productService.findById(id);
        } catch (DataAccessException e) {
            response.put("mensaje", "Error al realizar la consulta en la base de datos");
            response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<Map<String, Object>>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        if (product ==null){
            response.put("mensaje", "El producto ID: ".concat(id.toString().concat(" no existe en la base de datos")));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
        }
        return  new ResponseEntity<Product>(product, HttpStatus.OK);

    }

    @PutMapping("/products/{id}")
    public  ResponseEntity<?> update(@Valid @RequestBody Product product, BindingResult result
            ,@PathVariable Integer id){
        Product updatedProduct = null;
        Product productActual = productService.findById(id);
        Map<String, Object> response = new HashMap<>();
        if (result.hasErrors()){
            List<String> errors= result.getFieldErrors()
                    .stream()
                    .map(err -> "El campo '"+err.getField()+"' "+err.getDefaultMessage())
                    .collect(Collectors.toList());
            response.put("errors", errors);
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
        }
        if (productActual ==null){
            response.put("mensaje", "Error: no se puede editar, El Product ID: ".concat(id.toString()
                    .concat(" no existe en la base de datos")));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
        }
        try {
            productActual.setSlug(product.getSlug());
            productActual.setTitulo(product.getTitulo());
            productActual.setPrecio(product.getPrecio());
            productActual.setDescripcion(product.getDescripcion());
            productActual.setContenido(product.getContenido());
            productActual.setStock(product.getStock());
            productActual.setNventas(product.getNventas());
            productActual.setNpuntos(product.getNpuntos());
            productActual.setEstado(product.getEstado());
            productActual.setCreatedAt(product.getCreatedAt());
            productActual.setImagen(product.getImagen());
            productActual.setCategoria(product.getCategoria());
            productActual.setMarca(product.getMarca());

            updatedProduct=productService.save(productActual);

        }
        catch (DataAccessException e){
            response.put("mensaje", "Error al actualizar en la base de datos");
            response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        response.put("mensaje", "El cliente ha sido actualizado con éxito");
        response.put("producto", updatedProduct);
        return  new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);

    }

    @DeleteMapping("/products/{id}")
    public ResponseEntity<?> delete(@PathVariable Integer id){
        Map<String, Object> response = new HashMap<>();
        try {
            Product product = productService.findById(id);
            String nombreFotoAnt= product.getImagen();
            uploadService.eliminar(nombreFotoAnt);
            productService.delete(id);
        } catch (DataAccessException e) {
            response.put("mensaje", "Error al eliminar en la base de datos");
            response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        response.put("mensaje", "El producto ha sido eliminado con éxito");
        return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
    }

    @GetMapping("/products/categories")
    public List<Categoria> listCategories() {
        return productService.findAllCategories();
    }

    @GetMapping("/products/marcas")
    public List<Marca> listMarcas() {
        return productService.findAllMarcas();
    }
    @GetMapping("upload/img/{nombreFoto:.+}")
    public ResponseEntity<Resource> verFoto(@PathVariable String nombreFoto) {
        Resource recurso= null;
        try{
            recurso=uploadService.cargar(nombreFoto);
        }
        catch (MalformedURLException e){
            e.printStackTrace();
        }
        HttpHeaders cabecera= new HttpHeaders();
        cabecera.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\""+recurso.getFilename()+ "\"");
        return  new ResponseEntity<>(recurso, cabecera, HttpStatus.OK);
    }

    @PostMapping("/products/upload")
    public ResponseEntity<?> upload(@RequestParam("file") MultipartFile archivo, @RequestParam("id") Integer id) {
        Map<String, Object> response = new HashMap<>();
        Product product = productService.findById(id);
        if(!archivo.isEmpty()){
            String nombreArchivo= null;
            try {
                nombreArchivo= uploadService.copiar(archivo);
            }catch (IOException e){
                response.put("mensaje", "Error al subir la imagen del producto ");
                response.put("error", e.getMessage().concat(": ").concat(e.getCause().getMessage()));
                return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
            }
            String nombreFotoAnt= product.getImagen();
            uploadService.eliminar(nombreFotoAnt);
            product.setImagen(nombreArchivo);
            productService.save(product);
            response.put("product", product);
            response.put("mensaje", "Has subido corectamente la imagen"+nombreArchivo);
        }
        return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
    }

    //Buscar inventario por producto
    @GetMapping("/products/inventario/{id}")
    public ResponseEntity<?> buscarInventario (@PathVariable Integer id) {
        Inventario inventario = null;
        Map<String, Object> response = new HashMap<>();
        try {
            inventario = productService.findAllInventarioByProduct(id);
        } catch (DataAccessException e) {
            response.put("mensaje", "Error al realizar la consulta en la base de datos");
            response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<Map<String, Object>>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        if (inventario ==null){
            response.put("mensaje", "El Inventario ID: ".concat(id.toString().concat(" no existe en la base de datos")));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
        }
        return  new ResponseEntity<Inventario>(inventario, HttpStatus.OK);
    }
    //Buscar porducto por slug
    @GetMapping("/products/slug/{slug}")
    public ResponseEntity<?> buscarSlug (@PathVariable String slug) {
        Product product = null;
        Map<String, Object> response = new HashMap<>();
        try {
            product = productService.findBySlug(slug);
        } catch (DataAccessException e) {
            response.put("mensaje", "Error al realizar la consulta en la base de datos");
            response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<Map<String, Object>>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        if (product ==null){
            response.put("mensaje", "El producto con el slug: ".concat(slug.concat(" no existe en la base de datos")));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
        }
        return  new ResponseEntity<Product>(product, HttpStatus.OK);
    }
    //Listar productos por categoria
    @GetMapping("/products/categoria/{id}")
    public List<Product> listProductsByCategory(@PathVariable Integer id) {
            return productService.findByCategoria(id);
    }




}
