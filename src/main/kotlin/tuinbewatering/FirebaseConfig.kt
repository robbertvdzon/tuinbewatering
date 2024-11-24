package tuinbewatering

import com.google.auth.oauth2.GoogleCredentials
import com.google.cloud.firestore.Firestore
import com.google.firebase.FirebaseApp
import com.google.firebase.FirebaseOptions
import com.google.firebase.cloud.FirestoreClient
import java.io.FileInputStream

object FirebaseConfig {
    fun initializeFirestore(): Firestore? {
        val serviceAccount =
            FileInputStream("/Users/robbertvanderzon/tuinbewatering-firebase-adminsdk-mdooy-b394f2553c.json")
        val options = FirebaseOptions.builder()
            .setCredentials(GoogleCredentials.fromStream(serviceAccount))
            .setDatabaseUrl("https://tuinbewatering.firebaseio.com")
            .build()
        FirebaseApp.initializeApp(options)
        val dbFirestore = FirestoreClient.getFirestore()
        return dbFirestore
    }
}