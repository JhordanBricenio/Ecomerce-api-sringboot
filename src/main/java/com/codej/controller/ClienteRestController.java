package com.codej.controller;

import com.codej.model.Cliente;
import com.codej.service.IClienteService;
import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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
public class ClienteRestController {
    private IClienteService clienteService;

    public ClienteRestController(IClienteService clienteService) {
        this.clienteService = clienteService;
    }
    //Listar todos los admis
    @GetMapping("/clientes")
    public List<Cliente> index() {
        return clienteService.findAll();
    }

    //Listar clientes paginados
    @GetMapping("/clientes/page/{page}")
    public Page<Cliente> index(@PathVariable Integer page) {
        Pageable pageable = PageRequest.of(page, 3);
        return clienteService.findAll(pageable);
    }

    //Crear un nuevo cliente
    @PostMapping("/clientes")
    public ResponseEntity<?> create (@Valid @RequestBody Cliente cliente, BindingResult result){
        Cliente clienteNew = null;
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
            clienteNew = clienteService.save(cliente);
        }catch (DataAccessException e){
            response.put("mensaje", "Error al realizar el insert en la base de datos");
            response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        response.put("mensaje", "El cliente ha sido registrado con éxito");
        response.put("cliente", clienteNew);
        return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);

    }

    //Buscar cliente por id
    @GetMapping("/clientes/{id}")
    public ResponseEntity<?> show (@PathVariable Integer id){
        Cliente cliente = null;
        Map<String, Object> response = new HashMap<>();
        try {
            cliente = clienteService.findById(id);
        } catch (DataAccessException e) {
            response.put("mensaje", "Error al realizar la consulta en la base de datos");
            response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<Map<String, Object>>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        if (cliente ==null){
            response.put("mensaje", "El producto ID: ".concat(id.toString().concat(" no existe en la base de datos")));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
        }
        return  new ResponseEntity<Cliente>(cliente, HttpStatus.OK);

    }

    @PutMapping("/clientes/{id}")
    public  ResponseEntity<?> update(@Valid @RequestBody Cliente cliente, BindingResult result
            ,@PathVariable Integer id){
        Cliente updatedClient = null;
        Cliente clientActual = clienteService.findById(id);
        Map<String, Object> response = new HashMap<>();
        if (result.hasErrors()){
            List<String> errors= result.getFieldErrors()
                    .stream()
                    .map(err -> "El campo '"+err.getField()+"' "+err.getDefaultMessage())
                    .collect(Collectors.toList());
            response.put("errors", errors);
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
        }
        if (clientActual ==null){
            response.put("mensaje", "Error: no se puede editar, El Product ID: ".concat(id.toString()
                    .concat(" no existe en la base de datos")));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
        }
        try {
            clientActual.setNombres(cliente.getNombres());
            clientActual.setApellidos(cliente.getApellidos());
            clientActual.setEmail(cliente.getEmail());
            clientActual.setPais(cliente.getPais());
            clientActual.setPassword(cliente.getPassword());
            clientActual.setPerfil(cliente.getPerfil());
            clientActual.setTelefono(cliente.getTelefono());
            clientActual.setFechaNac(cliente.getFechaNac());
            clientActual.setGenero(cliente.getGenero());
            clientActual.setDni(cliente.getDni());

            updatedClient=clienteService.save(clientActual);

        }
        catch (DataAccessException e){
            response.put("mensaje", "Error al actualizar en la base de datos");
            response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        response.put("mensaje", "El cliente ha sido actualizado con éxito");
        response.put("cliente", updatedClient);
        return  new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);

    }


    @DeleteMapping("/clientes/{id}")
    public ResponseEntity<?> delete(@PathVariable Integer id){
        Map<String, Object> response = new HashMap<>();
        try {
            clienteService.delete(id);
        } catch (DataAccessException e) {
            response.put("mensaje", "Error al eliminar en la base de datos");
            response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        response.put("mensaje", "El cliente ha sido eliminado con éxito");
        return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
    }


}
