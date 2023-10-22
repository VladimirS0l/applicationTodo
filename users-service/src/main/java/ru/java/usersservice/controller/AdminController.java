package ru.java.usersservice.controller;

import lombok.RequiredArgsConstructor;
import org.keycloak.admin.client.CreatedResponseUtil;
import org.keycloak.representations.idm.UserRepresentation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.java.usersservice.dto.UserDto;
import ru.java.usersservice.keycloak.KeycloakUtils;
import ru.java.usersservice.mq.func.MessageFuncActions;
import ru.java.usersservice.service.UserService;
import ru.java.utils.webclient.UserWebClientBuilder;
import javax.ws.rs.core.Response;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/admin/user")
@RequiredArgsConstructor
public class AdminController {

    public static final String ID_COLUMN = "id";
    public static final int CONFLICT = 409;
    private static final String USER_ROLE_NAME = "user";
    private final UserService userService;
    private final KeycloakUtils keycloakUtils;
    private UserWebClientBuilder userWebClientBuilder;
    private MessageFuncActions messageFuncActions;

    @PostMapping("/add")
    public ResponseEntity add(@RequestBody UserDto user) {

        Response createdResponse = keycloakUtils.createKeycloakUser(user);

        if (createdResponse.getStatus() == CONFLICT) {
            return new ResponseEntity("User or email already exist " + user.getEmail(), HttpStatus.CONFLICT);
        }

        String userId = CreatedResponseUtil.getCreatedId(createdResponse);
        System.out.println("User created with userId: " + userId);
        List<String> defaultRoles = new ArrayList<>();
        defaultRoles.add(USER_ROLE_NAME);
        keycloakUtils.addRoles(userId, defaultRoles);

        return ResponseEntity.status(createdResponse.getStatus()).build();
    }

    @PutMapping("/update")
    public ResponseEntity<UserDto> update(@RequestBody UserDto user) {

        if (user.getId().isBlank()) {
            return new ResponseEntity("missed param: id", HttpStatus.NOT_ACCEPTABLE);
        }
        keycloakUtils.updateKeyCloak(user);
        return new ResponseEntity(HttpStatus.OK);

    }

    @PostMapping("/deletebyid")
    public ResponseEntity deleteByUserId(@RequestBody String userId) {
        keycloakUtils.deleteKeycloakUser(userId);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @PostMapping("/id")
    public ResponseEntity<UserRepresentation> findById(@RequestBody String id) {
        return ResponseEntity.ok(keycloakUtils.findByUserId(id));
    }

    @PostMapping("/search")
    public ResponseEntity<List<UserRepresentation>> search(@RequestBody String email) throws ParseException {
        return ResponseEntity.ok(keycloakUtils.searchKeycloakusers(email));
    }
}
