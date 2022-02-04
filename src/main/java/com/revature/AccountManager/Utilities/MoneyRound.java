package com.revature.AccountManager.Utilities;

import java.text.DecimalFormat;

import static java.lang.Float.parseFloat;

public class MoneyRound {
    private static final DecimalFormat df = new DecimalFormat("0.00");

    public static Float round( Float input){
        return parseFloat(df.format(input));
    }
}
