package com.example.demo.config;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;

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

    FirebaseApp firebaseApp = null;
    List<FirebaseApp> firebaseApps = FirebaseApp.getApps();
    if(firebaseApps!=null && !firebaseApps.isEmpty()){
        for(FirebaseApp app : firebaseApps){
            if(app.getName().equals(FirebaseApp.DEFAULT_APP_NAME))
                firebaseApp = app;
        }
    }
    else
        firebaseApp = FirebaseApp.initializeApp(options);  
    
    return FirestoreClient.getFirestore(firebaseApp);
  }

}
