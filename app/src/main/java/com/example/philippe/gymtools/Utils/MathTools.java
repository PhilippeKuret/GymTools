package com.example.philippe.gymtools.Utils;

import java.text.DecimalFormat;

public class MathTools {

    public static double KiloToLbs(double kilo)
    {
        double lbs = kilo * 2.20462;
        return FormatNumber(lbs);
    }

    public static double LbsToKilo(double lbs)
    {
        double kilo = lbs / 2.20462;
        return FormatNumber(kilo);
    }

    public static long StringTimeMinuteToMilli(String time)
    {
        String splittedTime[] = time.split(":");

        long secondsToMilli = Long.parseLong(splittedTime[1]) * 1000;
        long minutesToMilli = Long.parseLong(splittedTime[0]) * 60000;

        return minutesToMilli + secondsToMilli;
    }

    public static String MilliToMinuteTimeInString(long time)
    {
        long minute = time/60000;
        time = time - (minute*60000);
        long second = time/1000;

        if(second < 10)
		{
			return minute + ":0" + second;
		}
        else
		{
			return minute + ":" + second;
		}
    }

    private static double FormatNumber(double number)
    {
        DecimalFormat numberFormat = new DecimalFormat("#.00");
        return Double.parseDouble(numberFormat.format(number));
    }
}
