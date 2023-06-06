package com.celebrare.greentracker;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.graphics.Color;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.CompletableFuture;

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
    List<Float> dataPercentages = new ArrayList<>();
    Map<Integer,String> ansFinal = new HashMap<>();

    TextView curr ;
    private static final String USERS_COLLECTION = "users";
    private static final String VALUES_COLLECTION = "values";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_analytics_page);

        // Initialize the BarChart
        barChart = findViewById(R.id.barChart);
        prev = findViewById(R.id.consumPrev);
        curr = findViewById(R.id.consumCurr);
        rtriveData.retrieveDataFromFirestore().thenAccept(map -> {
            // Convert the List to a Map
            Map<Integer, String> resultMap = new LinkedHashMap<>();

            for (int i = 0; i < map.size(); i++) {
                resultMap.put(i, map.get(i));
            }

            // Use the converted map here
            curr.setText(""+ resultMap.get(0)+" Metric tons");
            prev.setText(""+ resultMap.get(1)+" Metric tons");
            System.out.println("Data: " + resultMap.toString());
            try {
                for (int i = 0; i < resultMap.size(); i++) {
                    dataPercentages.add((float) Integer.parseInt(Objects.requireNonNull(resultMap.get(i))));
                }
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

                barChart.invalidate();
            }catch (Exception e){
                Toast.makeText(AnalyticsPage.this,"Something went wrong..",Toast.LENGTH_SHORT).show();
            }

        }).exceptionally(e -> {
            // Handle exception if the data retrieval fails
            e.printStackTrace();
            return null;
        });




        // Create a list of data percentages (example values)
    }
}