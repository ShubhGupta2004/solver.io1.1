package com.celebrare.greentracker;


import android.util.Log;

class  helper {

    public static double calculateCarbonEmission(double airConditionerUsage, double waterHeaterUsage,
                                                 double washingMachineUsage, double incandescentBulbsUsage,
                                                 double compactFluorescentBulbsUsage, double ledBulbsUsage,
                                                 int star1, int star2, int star3, int star4, int star5,
                                                 double ovenUsage, double waterUsage, double bikeDistance,
                                                 double carDistance, double bicycleDistance, double bmi, double bmr,
                                                 int age, String sex, int exerciseTime,boolean carPoling,boolean publicTrans) {
        // Assign emission factors for each star category
        double emissionFactor1 = 1.0;
        double emissionFactor2 = 0.8;
        double emissionFactor3 = 0.6;
        double emissionFactor4 = 0.4;
        double emissionFactor5 = 0.2;

        // Calculate carbon emissions based on the given attributes and star categories
        double carbonEmission = airConditionerUsage * 0.5 +
                waterHeaterUsage * 0.025 +
                washingMachineUsage * 0.2 +
                incandescentBulbsUsage * 0.15 +
                compactFluorescentBulbsUsage * 0.05 +
                ledBulbsUsage * 0.03 +
                star1 * emissionFactor1 +
                star2 * emissionFactor2 +
                star3 * emissionFactor3 +
                star4 * emissionFactor4 +
                star5 * emissionFactor5 +
                ovenUsage * 0.6 +
                waterUsage * 0.002 +
                bikeDistance * 0.03 +
                carDistance * 0.2 +
                bicycleDistance * 0.02 +
                bmi * 0.1 +
                bmr * 0.001 +
                age * 0.05 +
                (sex.equalsIgnoreCase("male") ? 0.5 : 0.3) +
                exerciseTime * 0.01;
        if(carPoling && carDistance!=0){
            carbonEmission -= 0.5;
        }else if (publicTrans && carDistance!=0){
            carbonEmission -= 0.7;
        }
        return carbonEmission;
    }
}
