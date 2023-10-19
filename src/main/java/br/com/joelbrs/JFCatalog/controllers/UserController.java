package br.com.joelbrs.JFCatalog.controllers;

import br.com.joelbrs.JFCatalog.dtos.UserDTOOut;
import br.com.joelbrs.JFCatalog.services.UserService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public ResponseEntity<Page<UserDTOOut>> findAll(Pageable pageable) {
        return ResponseEntity.ok().body(userService.findAll(pageable));
    }
}















