package br.com.megusta.userservice.controller;

import br.com.megusta.userservice.model.domain.Address;
import br.com.megusta.userservice.model.dto.AddressDTO;
import br.com.megusta.userservice.service.AddressService;
import br.com.megusta.userservice.validators.groups.OnUpdate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * @author Guilherme Camargo
 * */
@RestController
@RequestMapping("address")
@Validated
public class AddressRestController {
    @Autowired
    private AddressService addressService;

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public Address findById(@PathVariable String id){
        return  addressService.findById(id);
    }
    @PutMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Validated(OnUpdate.class)
    public void update(@PathVariable String id, @Valid @RequestBody AddressDTO addressDTO){
        addressDTO.setId(id);
        addressService.saveAddress(addressDTO);
    }
}
