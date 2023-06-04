package com.celebrare.greentracker;
import android.util.Log;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class FirestoreUtils {

    // Firestore collection names
    private static final String USERS_COLLECTION = "users";
    private static final String VALUES_COLLECTION = "values";

    // Method to push data to Firestore
    public static void pushDataToFirestore(double value) {
        // Get the current user ID
        String userId = FirebaseAuth.getInstance().getCurrentUser().getUid();

        // Create a new Firestore document reference using the user ID
        FirebaseFirestore db = FirebaseFirestore.getInstance();

        // Create a new document reference for the values collection
        Map<String, Object> data = new HashMap<>();
        data.put("date", new Date());
        data.put("value", value);

        db.collection(USERS_COLLECTION)
                .document(userId)
                .collection(VALUES_COLLECTION)
                .add(data)
                .addOnSuccessListener(documentReference -> {
                })
                .addOnFailureListener(e -> {
                    Log.d("findme","Some");
                });
    }
}

