package com.celebrare.greentracker;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.graphics.Color;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;


public class AnalyticsPage extends AppCompatActivity {

    private BarChart barChart;
    TextView prev ;
    TextView curr ;
    private static final String USERS_COLLECTION = "users";
    private static final String VALUES_COLLECTION = "values";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_analytics_page);

        // Initialize the BarChart
        barChart = findViewById(R.id.barChart);

        List<String> ans = rtriveData.retrieveDataFromFirestore();
        prev = findViewById(R.id.consumPrev);
        curr = findViewById(R.id.consumCurr);
        String userId = FirebaseAuth.getInstance().getCurrentUser().getUid();

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
                        value="14.3";
                        // Log the retrieved data
                        curr.setText("Todays footprint: "+value+ " Metric Ton");
                    }
                })
                .addOnFailureListener(e -> {
                    // Error occurred while retrieving data
                    // Handle error or perform any other operations
                });
        if(ans.size()>1){
            prev.setText(ans.get(ans.size() - 2).toString());
        }else{
            prev.setText("Previous footprint: "+17.38+" Metric Ton");
        }
        if(ans.size()>0) {
            curr.setText(ans.get(ans.size() - 1).toString());
        }else{

        }
        // Create a list of data percentages (example values)

        List<Float> dataPercentages = new ArrayList<>();
        dataPercentages.add(50f);
        dataPercentages.add(29f);
        dataPercentages.add(22f);
        dataPercentages.add(25f);
        dataPercentages.add(30f);
        dataPercentages.add(40f);
        dataPercentages.add(55f);


        // Create a list of BarEntries from the data percentages
        List<BarEntry> barEntries = new ArrayList<>();
        for (int i = 0; i < dataPercentages.size(); i++) {
            barEntries.add(new BarEntry(i, dataPercentages.get(i)));
        }

        // Create a BarDataSet with the BarEntries and a label
        BarDataSet barDataSet = new BarDataSet(barEntries, "Reduced Percentages");

        // Set custom colors for the bars
        int[] colors = new int[]{
                Color.rgb(255, 0, 0),
                Color.rgb(0, 255, 0),
                Color.rgb(0, 0, 255),
                Color.rgb(255, 255, 0),
                Color.rgb(255, 0, 255),
                Color.rgb(0, 255, 255),
                Color.rgb(128, 128, 128)
        };
        barDataSet.setColors(colors);

        // Create a BarData object with the BarDataSet
        BarData barData = new BarData(barDataSet);

        // Configure the BarChart
        barChart.setData(barData);
        barChart.setFitBars(true);
        barChart.getDescription().setEnabled(false);  // Disable chart description
        barChart.getXAxis().setEnabled(false);  // Disable x-axis labels

        // Set a custom description for the chart
        Description description = new Description();
        description.setText("Data Percentages");
        description.setTextColor(Color.BLACK);
        barChart.setDescription(description);

        // Refresh the chart
        barChart.invalidate();
    }
}