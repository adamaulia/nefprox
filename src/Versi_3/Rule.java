/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Versi_3;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;

/**
 *
 * @author mickeyMice
 */
public class Rule {

    private double[] x1 = new double[2];
    private double[] x2 = new double[2];
    private double[] x3 = new double[2];
    private double[] target = new double[2];
    
//    private double sharedWeight, errorOutput;

    public Rule() {
    }

    public Rule(double[] input1, double[] input2, double[] input3, double[] input4) {
        x1 = Choose(input1);
        x2 = Choose(input2);
        x3 = Choose(input3);
        target = Choose(input4);
//        setStaticRule();
    }

    public void setRule(double[] input1, double[] input2, double[] input3, double[] input4) {
        x1 = Choose(input1);
        x2 = Choose(input2);
        x3 = Choose(input3);
        target = Choose(input4);
    }
    
    public double[] Choose(double[] input) {
        double max = 0;
        int idx = 0;
        double temp[] = new double[2];
        
        for (int i = 0; i < input.length; i++) {
            if (input[i] > max) {
                max = input[i];
                idx = i;
            }
        }
        
        temp[0]=idx;
        temp[1] = max;
        return temp;

    }

    public double[][] getRule() {
        double temp[][] = new double[4][2];
        temp[0] = x1;
        temp[1] = x2;
        temp[2] = x3;
        temp[3] = target;

        return temp;
    }
    
//    public double getErrorRule(){
//        double outputRule = Double.parseDouble(target[1]);
//        return outputRule * (1 - outputRule) * (2 * sharedWeight - 1) * Math.abs(errorOutput);
//    }
}
