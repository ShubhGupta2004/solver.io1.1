package com.celebrare.greentracker;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

public class rtriveData {

    // Firestore collection names
    private static final String USERS_COLLECTION = "users";
    private static final String VALUES_COLLECTION = "values";

    public static CompletableFuture<List<String>> retrieveDataFromFirestore() {
        // Get the current user ID
        String userId = FirebaseAuth.getInstance().getCurrentUser().getUid();
        CompletableFuture<List<String>> future = new CompletableFuture<>();
        // Create a new Firestore instance
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        List<String> map = new ArrayList<>();

        // Retrieve the values subcollection under the user document
        db.collection(USERS_COLLECTION)
                .document(userId)
                .collection(VALUES_COLLECTION)
                .get()
                .addOnSuccessListener(queryDocumentSnapshots -> {
                    // Get the list of documents in the subcollection
                    List<DocumentSnapshot> documents = queryDocumentSnapshots.getDocuments();

                    // Iterate over the documents
                    for (DocumentSnapshot document : documents) {
                        // Retrieve the data from each document
                        Date date = document.getDate("date");
                        String value = String.valueOf(document.getLong("value"));

                        // Log the retrieved data
                        System.out.println("Data: "+value);
                        map.add(value);
                    }
                    future.complete(map);
                })
                .addOnFailureListener(e -> {
                    // Error occurred while retrieving data
                    // Handle error or perform any other operations
                    future.completeExceptionally(e);
                });
        return future;
    }
}


