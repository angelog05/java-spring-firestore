package com.example.demo.config;

import java.io.FileInputStream;
import java.io.IOException;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.firestore.Firestore;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.cloud.FirestoreClient;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FirebaseConfig {

  @Bean
  public Firestore firestore() throws IOException {
    FileInputStream serviceAccount = new FileInputStream("./firebase-key.json");
    
    FirebaseOptions options = FirebaseOptions.builder()
      .setCredentials(GoogleCredentials.fromStream(serviceAccount))
      .setDatabaseUrl("https://testing-a7743.firebaseio.com")
      .build();
    
    FirebaseApp myApp = FirebaseApp.initializeApp(options);
    
    return FirestoreClient.getFirestore(myApp);
  }

}
