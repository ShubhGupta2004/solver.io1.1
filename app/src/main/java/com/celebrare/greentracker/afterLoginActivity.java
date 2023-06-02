package com.celebrare.greentracker;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.EditText;

public class afterLoginActivity extends AppCompatActivity {
    EditText[] list = new EditText[10];
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_after_login);

        
        initalize();

        CarbonFootprintCalculator cfc = new CarbonFootprintCalculator();


    }

    private void initalize() {
        list[0]=findViewById(R.id.editBikeDistance);
        list[1]=findViewById(R.id.editCarDistance);
        list[2]=findViewById(R.id.editBicycleDistance);
        list[3]=findViewById(R.id.editFanUsage);
        list[4]=findViewById(R.id.editTVUsage);
        list[5]=findViewById(R.id.editFridgeUsage);
        list[6]=findViewById(R.id.editMeatCalorieIntake);
        list[7]=findViewById(R.id.editGrainCalorieIntake);
        list[8]=findViewById(R.id.editDairyCalorieIntake);
        list[9]=findViewById(R.id.editFruitCalorieIntake);
    }
}

 class CarbonFootprintCalculator {

    // Constants for emissions (adjust the values as needed)
    private static final double kwhUsedByFanPerHour = 0.065;
    private static final double kwhUsedByTVPerHour = 0.03;
    private static final double kwhUsedByFridgePerHour = 0.25;
    private static final double emissionPerUnitElectricity = 0.475;
    private static final double emissionPerUnitWater = 0.001;
    private static final double emissionPerKmCar = 0.313;
    private static final double emissionPerKmBike = 0.0687;
    private static final double emissionPerKmBicycle = 0.016;
    private static final double emissionPerUnitCalorieOfMeat = 219.67;
    private static final double emissionPerUnitCalorieOfGrain = 15.34;
    private static final double emissionPerUnitCalorieOfDairy = 1.9;
    private static final double emissionPerUnitCalorieOfFruit = 1.55;

    public static double getDailyHouseHoldCarbonFootPrint(double hoursFanUsed, double hoursTVUsed, double hoursFridgeUsed, double litresOfWaterUsed) {
        double electricityConsumptionInKWH = (hoursFanUsed * kwhUsedByFanPerHour) +
                (hoursTVUsed * kwhUsedByTVPerHour) +
                (hoursFridgeUsed * kwhUsedByFridgePerHour);

        double emissionDueToElectricity = emissionPerUnitElectricity * electricityConsumptionInKWH;
        double emissionDueToWater = emissionPerUnitWater * litresOfWaterUsed;

        return emissionDueToElectricity + emissionDueToWater;
    }

    public static double getDailyTravelFootPrint(double distanceTravelledByBike, double distanceTravelledByCar, double distanceTravelledByBicycle) {
        double emissionDueToBike = emissionPerKmBike * distanceTravelledByBike;
        double emissionDueToCar = emissionPerKmCar * distanceTravelledByCar;
        double emissionDueToBicycle = emissionPerKmBicycle * distanceTravelledByBicycle;

        return emissionDueToBike + emissionDueToCar + emissionDueToBicycle;
    }

    public static double getDailyFoodCarbonFootPrint(double meatCalorieIntake, double grainCalorieIntake, double dairyCalorieIntake, double fruitCalorieIntake) {
        double emissionDueToMeat = meatCalorieIntake * emissionPerUnitCalorieOfMeat;
        double emissionDueToGrain = grainCalorieIntake * emissionPerUnitCalorieOfGrain;
        double emissionDueToDairy = dairyCalorieIntake * emissionPerUnitCalorieOfDairy;
        double emissionDueToFruit = fruitCalorieIntake * emissionPerUnitCalorieOfFruit;

        return (emissionDueToMeat + emissionDueToGrain + emissionDueToDairy + emissionDueToFruit) / 1000;
    }

    public static double getTotalCarbonFootPrint(double hoursFanUsed, double hoursTVUsed, double hoursFridgeUsed, double litresOfWaterUsed, double distanceTravelledByBike, double distanceTravelledByCar, double distanceTravelledByBicycle, double meatCalorieIntake, double grainCalorieIntake, double dairyCalorieIntake, double fruitCalorieIntake) {
        double householdEmission = getDailyHouseHoldCarbonFootPrint(hoursFanUsed, hoursTVUsed, hoursFridgeUsed, litresOfWaterUsed);
        double travelEmission = getDailyTravelFootPrint(distanceTravelledByBike, distanceTravelledByCar, distanceTravelledByBicycle);
        double foodEmission = getDailyFoodCarbonFootPrint(meatCalorieIntake, grainCalorieIntake, dairyCalorieIntake, fruitCalorieIntake);

        return householdEmission + travelEmission + foodEmission;
    }
}