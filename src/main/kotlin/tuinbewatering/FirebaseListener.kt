package tuinbewatering

import com.google.cloud.firestore.DocumentReference
import com.google.cloud.firestore.DocumentSnapshot
import com.google.cloud.firestore.FieldValue
import com.google.cloud.firestore.Firestore
import java.util.concurrent.Executors

object FirebaseListener {
    private val executor = Executors.newSingleThreadExecutor()

    fun processCommands(dbFirestore: Firestore?, process: (String) -> Unit) {
        val documentRef = dbFirestore?.collection("bewatering")?.document("commands")
        documentRef?.addSnapshotListener { snapshot, error ->
            if (error != null) {
                println("Error listening to document: ${error.message}")
                return@addSnapshotListener
            }
            if (snapshot != null && snapshot.exists()) {
                processDocumentSnapshot(snapshot, documentRef, process)
            } else {
                println("Document does not exists")
            }
        }
    }

    private fun processDocumentSnapshot(
        snapshot: DocumentSnapshot,
        documentRef: DocumentReference,
        process: (String) -> Unit
    ) {
        val data = snapshot.data ?: emptyMap()
        for ((key, value) in data) {
            process(value.toString())
            documentRef.update(key, FieldValue.delete())
                .addListener({ }, executor)
        }
    }

}