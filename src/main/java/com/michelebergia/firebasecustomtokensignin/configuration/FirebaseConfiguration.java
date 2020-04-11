package com.michelebergia.firebasecustomtokensignin.configuration;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;
import java.io.FileInputStream;
import java.io.IOException;

@Configuration
@ConfigurationProperties("firebase.config")
@Getter
@Setter
public class FirebaseConfiguration {

    private String serviceAccountPath;
    private String apiKey;
    private String databaseUrl;

    @PostConstruct
    public void init() throws IOException {

        final var serviceAccount = new FileInputStream(serviceAccountPath);

        final var options = new FirebaseOptions.Builder()
                .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                .setDatabaseUrl(databaseUrl)
                .build();

        FirebaseApp.initializeApp(options);
    }
}
