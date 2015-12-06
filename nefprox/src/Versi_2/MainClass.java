/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Versi_2;

import com.sun.corba.se.impl.orbutil.ObjectWriter;
import java.util.ArrayList;

/**
 *
 * @author mickeyMice
 */
public class MainClass {

    static ArrayList<Rule> rules = new ArrayList<Rule>();
    static Fuzzifikasi fuzzy;
    static double[][] fuzzyinput;

    public static void printRule() {
        String[][] tempRule;
        for (int i = 0; i < rules.size(); i++) {
            tempRule = rules.get(i).getRule();
            String temp = i + " : IF X1=" + tempRule[0][0] + "(" + tempRule[0][1] + ") AND X2=" + tempRule[1][0] + "(" + tempRule[1][1] + ") AND X3=" + tempRule[2][0] + "(" + tempRule[2][1] + ") THEN " + tempRule[3][0] + "(" + tempRule[3][1] + ")";
            System.out.println(temp);
        }
    }

    public static boolean checkRule(Rule inputRule) {
        boolean temp = false;
        //False : belum ada
        //True  : sudah ada
        String[][] tempRuleInput = inputRule.getRule();
        for (int i = 0; i < rules.size(); i++) {
            String[][] tempRule = rules.get(i).getRule();
            if ((tempRuleInput[0][0].equals(tempRule[0][0]))
                    && (tempRuleInput[1][0].equals(tempRule[1][0]))
                    && (tempRuleInput[2][0].equals(tempRule[2][0]))
                    && (tempRuleInput[3][0].equals(tempRule[3][0]))) {

                temp = true;
                if (Double.parseDouble(tempRuleInput[0][1]) > Double.parseDouble(tempRule[0][1])) {
                    tempRule[0][1] = tempRuleInput[0][1];
                }
                if (Double.parseDouble(tempRuleInput[1][1]) > Double.parseDouble(tempRule[1][1])) {
                    tempRule[1][1] = tempRuleInput[1][1];
                }
                if (Double.parseDouble(tempRuleInput[2][1]) > Double.parseDouble(tempRule[2][1])) {
                    tempRule[2][1] = tempRuleInput[2][1];
                }
                if (Double.parseDouble(tempRuleInput[3][1]) > Double.parseDouble(tempRule[3][1])) {
                    tempRule[3][1] = tempRuleInput[3][1];
                }
            }
        }
        return temp;
    }

    public static void makeRule() {
        Rule tempRule;
        boolean temp;
        for (int i = 0; i < fuzzyinput.length - 3; i++) {
            tempRule = new Rule();
            tempRule.setRule(fuzzyinput[i], fuzzyinput[i + 1], fuzzyinput[i + 2], fuzzyinput[i + 3]);
            temp = checkRule(tempRule);
            if (temp == false) {
                rules.add(tempRule);
            }
        }
    }

    public static void makeFuzzyInput(double[] input) {
        fuzzyinput = new double[input.length][3];
        for (int i = 0; i < input.length; i++) {
            fuzzyinput[i] = fuzzy.doFuzzy(input[i]);
        }
    }

    public static void shareWeight() {
        for (int i = 0; i < rules.size(); i++) {
            String[][] tempRule1 = rules.get(i).getRule();
            for (int j = 0; j < rules.size(); j++) {
                String max;
                String[][] tempRule2 = rules.get(j).getRule();
                if (tempRule1[0][0].equals(tempRule2[0][0])) {
                    if (Double.parseDouble(tempRule1[0][1]) > Double.parseDouble(tempRule2[0][1])) {
                        max = tempRule1[0][1];
                    } else {
                        max = tempRule2[0][1];
                    }
                    rules.get(i).getRule()[0][1] = max;
                    rules.get(j).getRule()[0][1] = max;
                }

                if (tempRule1[1][0].equals(tempRule2[1][0])) {
                    if (Double.parseDouble(tempRule1[1][1]) > Double.parseDouble(tempRule2[1][1])) {
                        max = tempRule1[1][1];
                    } else {
                        max = tempRule2[1][1];
                    }
                    rules.get(i).getRule()[1][1] = max;
                    rules.get(j).getRule()[1][1] = max;
                }

                if (tempRule1[2][0].equals(tempRule2[2][0])) {
                    if (Double.parseDouble(tempRule1[2][1]) > Double.parseDouble(tempRule2[2][1])) {
                        max = tempRule1[2][1];
                    } else {
                        max = tempRule2[2][1];
                    }
                    rules.get(i).getRule()[2][1] = max;
                    rules.get(j).getRule()[2][1] = max;
                }
            }
        }
    }

    public static void main(String[] args) {
        Data data = new Data();
        data.loadData("src\\data\\BUNDESBANK-BBK01_WT5511.xls");
        data.setMaxMin();

        FungsiKeanggotaan fk = new FungsiKeanggotaan();
        fk.setBatas(0, data.getMin());
        fk.setBatas(1, 267);
        fk.setBatas(2, 732);
        fk.setBatas(3, 965);
        fk.setBatas(4, 1431);
        fk.setBatas(5, data.getMax());

        fuzzy = new Fuzzifikasi(fk, data.getMax(), data.getMin());
        makeFuzzyInput(data.getAllData());
        makeRule();
        shareWeight();
        printRule();
    }

}
