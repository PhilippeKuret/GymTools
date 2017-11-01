package com.example.philippe.gymtools.Tools;

import java.text.DecimalFormat;

/**
 * Created by Philippe on 2017-10-16.
 */

public class MathTools {

    public double KiloToLbs(double kilo) {
        double lbs = kilo * 2.20462;
        return FormatNumber(lbs);
    }

    public double LbsToKilo(double lbs) {
        double kilo = lbs / 2.20462;
        return FormatNumber(kilo);
    }

    private double FormatNumber(double number)
    {
        DecimalFormat numberFormat = new DecimalFormat("#.00");
        return Double.parseDouble(numberFormat.format(number));
    }
}
