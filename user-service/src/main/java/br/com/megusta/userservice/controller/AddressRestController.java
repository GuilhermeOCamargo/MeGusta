package br.com.megusta.userservice.controller;

import br.com.megusta.userservice.model.domain.Address;
import br.com.megusta.userservice.model.dto.AddressDTO;
import br.com.megusta.userservice.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;

/**
 * @author Guilherme Camargo
 * */
@RestController
@RequestMapping("/address")
public class AddressRestController {

    @Autowired
    private AddressService addressService;

    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update(@Valid @RequestBody AddressDTO addressDTO, @PathVariable Long id){
        addressDTO.setId(id);
        addressService.saveAddress(addressDTO);
    }

    @GetMapping( produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public Address findByUsuario(@RequestParam(name = "userId", required = true) Long userId){
        return addressService.findByUser(userId);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<Void> insert(@Valid @RequestBody AddressDTO addressDTO){
        Address address = addressService.saveAddress(addressDTO);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{id}")
                .buildAndExpand(address.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public Address findById(@PathVariable Long id){
        return addressService.findById(id);
    }

}
