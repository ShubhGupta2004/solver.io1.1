package com.celebrare.greentracker;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class preferCarbonMl {

    public static List<String> calculateRecommendations(double fanUsage, double tvUsage, double fridgeUsage,
                                                        double waterUsage, double bikeDistance, double carDistance,
                                                        double bicycleDistance, double meatCalorieIntake,
                                                        double grainCalorieIntake, double dairyCalorieIntake,
                                                        double fruitCalorieIntake) {

        // Calculate carbon emissions for each factor
        double fanEmissions = fanUsage * 0.1;
        double tvEmissions = tvUsage * 0.2;
        double fridgeEmissions = fridgeUsage * 0.3;
        double waterEmissions = waterUsage * 0.1;
        double bikeEmissions = bikeDistance * 0.05;
        double carEmissions = carDistance * 0.15;
        double bicycleEmissions = bicycleDistance * 0.05;
        double meatEmissions = meatCalorieIntake * 0.2;
        double grainEmissions = grainCalorieIntake * 0.1;
        double dairyEmissions = dairyCalorieIntake * 0.1;
        double fruitEmissions = fruitCalorieIntake * 0.1;

        // Create a list of emissions with corresponding factors
        List<FactorEmission> factorEmissions = new ArrayList<>();
        factorEmissions.add(new FactorEmission("Fan Usage", fanEmissions));
        factorEmissions.add(new FactorEmission("TV Usage", tvEmissions));
        factorEmissions.add(new FactorEmission("Fridge Usage", fridgeEmissions));
        factorEmissions.add(new FactorEmission("Water Usage", waterEmissions));
        factorEmissions.add(new FactorEmission("Bike Distance", bikeEmissions));
        factorEmissions.add(new FactorEmission("Car Distance", carEmissions));
        factorEmissions.add(new FactorEmission("Bicycle Distance", bicycleEmissions));
        factorEmissions.add(new FactorEmission("Meat Calorie Intake", meatEmissions));
        factorEmissions.add(new FactorEmission("Grain Calorie Intake", grainEmissions));
        factorEmissions.add(new FactorEmission("Dairy Calorie Intake", dairyEmissions));
        factorEmissions.add(new FactorEmission("Fruit Calorie Intake", fruitEmissions));

        // Sort emissions in descending order
        Collections.sort(factorEmissions, Collections.reverseOrder());

        // Provide recommendations for the two factors with the highest emissions
        List<String> recommendations = new ArrayList<>();
        if (!factorEmissions.isEmpty()) {
            recommendations.add("Recommended actions to reduce carbon emissions:");

            for (int i = 0; i < Math.min(2, factorEmissions.size()); i++) {
                FactorEmission factorEmission = factorEmissions.get(i);
                recommendations.add("Reduce " + factorEmission.getFactor() + " to significantly reduce emissions.");
            }
        } else {
            recommendations.add("Great job! Your carbon emissions are already low.");
        }

        return recommendations;
    }


    private static class FactorEmission implements Comparable<FactorEmission> {
        private final String factor;
        private final double emission;

        public FactorEmission(String factor, double emission) {
            this.factor = factor;
            this.emission = emission;
        }

        public String getFactor() {
            return factor;
        }

        @Override
        public int compareTo(FactorEmission o) {
            return Double.compare(this.emission, o.emission);
        }
    }
}