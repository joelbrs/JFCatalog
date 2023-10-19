package br.com.joelbrs.JFCatalog.controllers;

import br.com.joelbrs.JFCatalog.dtos.UserDTOIn;
import br.com.joelbrs.JFCatalog.dtos.UserDTOOut;
import br.com.joelbrs.JFCatalog.services.UserService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public ResponseEntity<Page<UserDTOOut>> findAllPaged(Pageable pageable) {
        return ResponseEntity.ok().body(userService.findAllPaged(pageable));
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<UserDTOOut> findById(@PathVariable Long id) {
        return ResponseEntity.ok().body(userService.findById(id));
    }

    @PostMapping
    public ResponseEntity<UserDTOOut> insert(@RequestBody UserDTOIn dto) {
        return ResponseEntity.ok().body(userService.insert(dto));
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<UserDTOOut> update(@PathVariable Long id, @RequestBody UserDTOIn dto) {
        return ResponseEntity.ok().body(userService.update(id, dto));
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        userService.delete(id);

        return ResponseEntity.noContent().build();
    }
}















