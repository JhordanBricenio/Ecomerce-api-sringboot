package com.codej.controller;

import com.codej.model.Admin;
import com.codej.service.IAdminService;
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

@CrossOrigin(origins = {"*"})
@RestController
@RequestMapping("/api")
public class AdminRestController {


    private IAdminService adminService;
     public AdminRestController(IAdminService adminService) {
            this.adminService = adminService;
        }
    //Listar todos los admis
    @GetMapping("/admin")
    public List<Admin> index() {
        return adminService.findAll();
    }

    //Crear un nuevo producto
    @PostMapping("/admin")
    public ResponseEntity<?> create (@Valid @RequestBody Admin admin, BindingResult result){
        Admin adminNew = null;
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
            adminNew = adminService.save(admin);
        }catch (DataAccessException e){
            response.put("mensaje", "Error al realizar el insert en la base de datos");
            response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        response.put("mensaje", "El administardor ha sido registrado con éxito");
        response.put("admin", adminNew);
        return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);

    }


}
