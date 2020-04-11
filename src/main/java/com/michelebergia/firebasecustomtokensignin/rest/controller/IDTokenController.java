package com.michelebergia.firebasecustomtokensignin.rest.controller;

import com.michelebergia.firebasecustomtokensignin.rest.api.CustomTokenResource;
import com.michelebergia.firebasecustomtokensignin.rest.service.IDTokenService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Profile;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("idToken")
public class IDTokenController {

    private final IDTokenService idTokenService;

    @GetMapping("generate")
    public CustomTokenResource generateIDToken(
            @RequestParam(value = "customUID", defaultValue = "abc") String customUID,
            @RequestParam(value = "returnSecureToken", defaultValue = "true") boolean returnSecureToken,
            @RequestParam(value = "mail", defaultValue = "abc@gmail.com") String mail) {
        return idTokenService.generateValidIDToken(customUID, returnSecureToken, mail);
    }

    @PostMapping("validate")
    public ResponseEntity<Void> isValidToken(@RequestBody String idToken) {
        idTokenService.validateToken(idToken);
        return ResponseEntity.noContent().build();
    }

}
