package com.michelebergia.firebasecustomtokensignin.rest.api;

import lombok.Data;

@Data
public class CustomTokenResource {
    private String kind;
    private String idToken;
    private String refreshToken;
    private Integer expiresIn;
    private boolean isNewUser;
}
