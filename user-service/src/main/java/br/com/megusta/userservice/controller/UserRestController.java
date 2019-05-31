package br.com.megusta.userservice.controller;


import br.com.megusta.userservice.model.domain.User;
import br.com.megusta.userservice.model.dto.AddressDTO;
import br.com.megusta.userservice.model.dto.UserDTO;
import br.com.megusta.userservice.service.UserService;
import br.com.megusta.userservice.validators.groups.OnCreate;
import br.com.megusta.userservice.validators.groups.OnPasswordChange;
import br.com.megusta.userservice.validators.groups.OnUpdate;
import org.springframework.beans.factory.annotation.Autowired;
import static org.springframework.http.HttpStatus.*;
import static org.springframework.http.MediaType.*;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

/**
 * @author Guilherme Camargo
 * */
@RestController
@RequestMapping
@Validated
public class UserRestController {

    @Autowired
    private UserService userService;

    @GetMapping(value = "/{id}", produces = APPLICATION_JSON_UTF8_VALUE)
    @ResponseStatus(OK)
    @ResponseBody
    public User findById(@PathVariable String id){
        return  userService.findById(id);
    }

    @PostMapping(consumes = APPLICATION_JSON_UTF8_VALUE)
    @Validated(OnCreate.class)
    public ResponseEntity<Void> insert(@Valid @RequestBody UserDTO userDTO){
        User user = userService.saveUser(userDTO);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{id}")
                .buildAndExpand(user.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @PutMapping(value = "/{id}")
    @ResponseStatus(NO_CONTENT)
    @Validated(OnUpdate.class)
    public void update(@PathVariable String id, @Valid @RequestBody UserDTO userDto){
        userDto.setId(id);
        userService.saveUser(userDto);
    }

    @PutMapping(value = "/password/{id}")
    @Validated(OnPasswordChange.class)
    public ResponseEntity<Void> updatePassword(@PathVariable String id, @Valid @RequestBody UserDTO userDTO){
        userDTO.setId(id);
        userService.updatePassword(userDTO);
        return ResponseEntity.noContent().build();
    }

    @PostMapping(value = "/{id}/address")
    @ResponseStatus(NO_CONTENT)
    @Validated(OnCreate.class)
    public void addAddress(@PathVariable String id, @Valid @RequestBody AddressDTO addressDTO){
        userService.addAddress(addressDTO, id);
    }

    @GetMapping(produces = APPLICATION_JSON_UTF8_VALUE)
    @ResponseStatus(OK)
    @ResponseBody
    public List<UserDTO> findAll(){
        return  userService.findAll();
    }

    @DeleteMapping(value = "/{id}/address/{addressId}")
    @ResponseStatus(NO_CONTENT)
    public void delete(@PathVariable String id, @PathVariable String addressId){
        userService.removeAddress(id, addressId);
    }
}
