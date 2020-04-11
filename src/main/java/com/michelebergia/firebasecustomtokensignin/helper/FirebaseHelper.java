package com.michelebergia.firebasecustomtokensignin.helper;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.UserRecord;
import com.michelebergia.firebasecustomtokensignin.exception.FirebaseAuthRuntimeException;
import org.springframework.stereotype.Service;

@Service
public class FirebaseHelper {

    public String createCustomToken(String customID) {
        try {
            return FirebaseAuth.getInstance().createCustomToken(customID);
        } catch (FirebaseAuthException e) {
            throw new FirebaseAuthRuntimeException(e.getMessage());
        }
    }

    public void createUser(String uid, String mail) {
        final var createUser = new UserRecord.CreateRequest();
        createUser.setUid(uid);
        createUser.setEmail(mail);
        createUser.setEmailVerified(true);

        try {
            FirebaseAuth.getInstance().createUser(createUser);
        } catch (FirebaseAuthException e) {
            throw new FirebaseAuthRuntimeException(e.getMessage());
        }
    }

    public UserRecord getUser(String uid) {
        try {
            return FirebaseAuth.getInstance().getUser(uid);
        } catch (FirebaseAuthException e) {
            if (e.getMessage().equals("No user record found for the provided user ID: " + uid)) {
                return null;
            }
            throw new FirebaseAuthRuntimeException(e.getMessage());
        }
    }

    public void updateUser(String uid, String mail) {
        final var updateUser = new UserRecord.UpdateRequest(uid);
        updateUser.setEmail(mail);
        updateUser.setEmailVerified(true);

        try {
            FirebaseAuth.getInstance().updateUser(updateUser);
        } catch (FirebaseAuthException e) {
            throw new FirebaseAuthRuntimeException(e.getMessage());
        }
    }

    public void validateToken(String token) {
        try {
            FirebaseAuth.getInstance().verifyIdToken(token);
        } catch (FirebaseAuthException e) {
            throw new FirebaseAuthRuntimeException(e.getMessage());
        }
    }

}
