package br.com.megusta.userservice.controller;


import br.com.megusta.userservice.model.domain.User;
import br.com.megusta.userservice.model.dto.UserDTO;
import br.com.megusta.userservice.service.UserService;
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
@RequestMapping
public class UserRestController {

    @Autowired
    private UserService userService;

   /* @PostMapping(value = "/login", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<UsuarioVO> authenticate(@RequestBody UsuarioLoginDTO dto){
        User user = userService.authenticate(dto.getEmail(), dto.getPassword());
        return ResponseEntity.ok(new UsuarioVO(user));
    }*/

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public User findById(@PathVariable Long id){
        return  userService.findById(id);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<Void> insert(@Valid @RequestBody UserDTO userDTO){
        User user = userService.saveUser(userDTO);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{id}")
                .buildAndExpand(user.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @PutMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update(@PathVariable Long id, @Valid @RequestBody UserDTO userDto){
        userDto.setId(id);
        userService.saveUser(userDto);
    }
    /*//Todo change UsuarioDto to UsuarioPasswordDto
    @PutMapping(value = "/password/{id}")
    public ResponseEntity<Void> updatePassword(@PathVariable Long id, @RequestBody UserDTO userDTO){
        userDTO.setId(id);
        userService.updatePassword(userDTO);
        return ResponseEntity.noContent().build();
    }*/

}
