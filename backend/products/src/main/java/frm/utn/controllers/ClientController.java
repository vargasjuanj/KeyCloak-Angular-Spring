package frm.utn.controllers;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import frm.utn.services.ClientService;

import org.springframework.security.access.prepost.PreAuthorize;

@CrossOrigin("*")
@RestController
@RequestMapping("api/v1/ecom/clients")
public class ClientController {
  @Autowired
  private ClientService clientService;


  @GetMapping("/")
  @PreAuthorize("hasAnyRole('ROLE_admin')")
  @Transactional
  public ResponseEntity<?> getAll() throws Exception {
  try{
    return ResponseEntity.status(HttpStatus.OK).body(clientService.findAll());

  }catch(Exception e){
    return ResponseEntity.status(HttpStatus.NOT_FOUND)
    .body("{\"Mi mensaje get todos\": \"" + e.getMessage() + "\"}");
  }
  }
  
}