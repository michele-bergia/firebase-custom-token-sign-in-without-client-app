package com.michelebergia.firebasecustomtokensignin.rest.service;

import com.google.gson.JsonObject;
import com.michelebergia.firebasecustomtokensignin.configuration.FirebaseConfiguration;
import com.michelebergia.firebasecustomtokensignin.helper.FirebaseHelper;
import com.michelebergia.firebasecustomtokensignin.rest.api.CustomTokenResource;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@RequiredArgsConstructor
public class IDTokenService {

    private final FirebaseHelper firebaseHelper;
    private final FirebaseConfiguration firebaseConfiguration;

    public CustomTokenResource generateValidIDToken(String customUID, boolean returnSecureToken, String mail) {
        final var customToken = firebaseHelper.createCustomToken(customUID);

        if (firebaseHelper.getUser(customUID) == null) {
            firebaseHelper.createUser(customUID, mail);
        } else {
            firebaseHelper.updateUser(customUID, mail);
        }

        final var apiKey = firebaseConfiguration.getApiKey();

        final var jsonObject = new JsonObject();
        jsonObject.addProperty("token", customToken);
        jsonObject.addProperty("returnSecureToken", String.valueOf(returnSecureToken));

        return new RestTemplate().postForObject("https://www.googleapis.com/identitytoolkit/v3/relyingparty/verifyCustomToken?key=" + apiKey,
                new HttpEntity<>(jsonObject.toString()),
                CustomTokenResource.class);
    }

    public void validateToken(String tokenID) {
        firebaseHelper.validateToken(tokenID);
    }

}
