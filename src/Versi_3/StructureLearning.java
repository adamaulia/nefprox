/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Versi_3;

import java.util.ArrayList;

/**
 *
 * @author mickeyMice
 */
public class StructureLearning {

    static ArrayList<Rule> rules = new ArrayList<Rule>();
    static FungsiKeanggotaan fk[] = new FungsiKeanggotaan[4];
//    static FungsiKeanggotaan fkInput1 = new FungsiKeanggotaan();
//    static FungsiKeanggotaan fkInput2 = new FungsiKeanggotaan();
//    static FungsiKeanggotaan fkInput3 = new FungsiKeanggotaan();
//    static FungsiKeanggotaan fkTarget = new FungsiKeanggotaan();
    static Fuzzy fuzzy = new Fuzzy();
    static Data data[] = new Data[4];
    static double[][][] dataFuzzyInput = new double[4][][];

    public static void printRule() {
        double[][] tempRule;
//        System.out.println(rules.size());
        for (int i = 0; i < rules.size(); i++) {
            tempRule = rules.get(i).getRule();
//            System.out.println(tempRule[0][1]+" "+tempRule[1][1]+" "+tempRule[2][1]+" "+tempRule[3][0]);
            String temp = (i + 1) + " : IF X1=" + tempRule[0][0] + "(" + tempRule[0][1] + ") AND X2=" + tempRule[1][0] + "(" + tempRule[1][1] + ") AND X3=" + tempRule[2][0] + "(" + tempRule[2][1] + ") THEN " + tempRule[3][0] + "(" + tempRule[3][1] + ")";
            System.out.println(temp);
        }
    }

    public static boolean checkRule(Rule inputRule) {
        boolean temp = false;
        //False : belum ada
        //True  : sudah ada
        double[][] tempRuleInput = inputRule.getRule();
        if (rules.size() > 0) {
            for (int i = 0; i < rules.size(); i++) {
                double[][] tempRule = rules.get(i).getRule();
                if ((tempRuleInput[0][0] == (tempRule[0][0]))
                        && (tempRuleInput[1][0] == (tempRule[1][0]))
                        && (tempRuleInput[2][0] == (tempRule[2][0]))
                        && (tempRuleInput[3][0] == (tempRule[3][0]))) {

                    temp = true;
                    if (tempRuleInput[0][1] > tempRule[0][1]) {
                        tempRule[0][1] = tempRuleInput[0][1];
                    }
                    if (tempRuleInput[1][1] > tempRule[1][1]) {
                        tempRule[1][1] = tempRuleInput[1][1];
                    }
                    if (tempRuleInput[2][1] > tempRule[2][1]) {
                        tempRule[2][1] = tempRuleInput[2][1];
                    }
                    if (tempRuleInput[3][1] > tempRule[3][1]) {
                        tempRule[3][1] = tempRuleInput[3][1];
                    }
                }
            }
        }
        return temp;
    }

    public static void shareWeight1() {
        for (int i = 0; i < rules.size(); i++) {
            double[][] tempRule1 = rules.get(i).getRule();
            for (int j = 0; j < rules.size(); j++) {
                double max;
                double[][] tempRule2 = rules.get(j).getRule();
                if (tempRule1[0][0] == (tempRule2[0][0])) {
                    if (tempRule1[0][1] > tempRule2[0][1]) {
                        max = tempRule1[0][1];
                    } else {
                        max = tempRule2[0][1];
                    }
                    rules.get(i).getRule()[0][1] = max;
                    rules.get(j).getRule()[0][1] = max;

                }

                if (tempRule1[1][0] == tempRule2[1][0]) {
                    if (tempRule1[1][1] > tempRule2[1][1]) {
                        max = tempRule1[1][1];
                    } else {
                        max = tempRule2[1][1];
                    }
                    rules.get(i).getRule()[1][1] = max;
                    rules.get(j).getRule()[1][1] = max;
                }

                if (tempRule1[2][0] == tempRule2[2][0]) {
                    if (tempRule1[2][1] > tempRule2[2][1]) {
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

    public static void shareWeight2() {
        for (int i = 0; i < rules.size(); i++) {
            double[][] tempRule1 = rules.get(i).getRule();
            for (int j = 0; j < rules.size(); j++) {
                double max;
                double[][] tempRule2 = rules.get(j).getRule();
                if (tempRule1[3][0] == (tempRule2[3][0])) {
                    if (tempRule1[3][1] > tempRule2[3][1]) {
                        max = tempRule1[3][1];
                    } else {
                        max = tempRule2[3][1];
                    }
                    rules.get(i).getRule()[3][1] = max;
                    rules.get(j).getRule()[3][1] = max;
//                    System.out.println(max);
//                    System.out.println(tempRule1[3][0]);
                }
            }
        }
    }

    public static void makeAll(int idx) {
        data[idx] = new Data();
        data[idx].loadData("src\\data\\BUNDESBANK-BBK01_WT5511.xls", idx);

        fk[idx] = new FungsiKeanggotaan();
        fk[idx].setBatas(0, data[idx].getMin());
        fk[idx].setBatas(1, 0.2);
        fk[idx].setBatas(2, 0.33);
        fk[idx].setBatas(3, 0.55);
        fk[idx].setBatas(4, 0.7);
        fk[idx].setBatas(5, data[idx].getMax());

        fuzzy.setFK(fk[idx]);

        dataFuzzyInput[idx] = new double[data[idx].getNumRow() - 3][3];
        dataFuzzyInput[idx] = makeFuzzyInput(data[idx].getAllData(), fuzzy);
    }

    public static double[][] makeFuzzyInput(double[] input, Fuzzy fuzzyinput) {
        double[][] tempFuzzy = new double[input.length][3];
//        fuzzyinput = new double[input.length][3];
        System.out.println(input[0]);
        for (int i = 0; i < input.length; i++) {
            tempFuzzy[i] = fuzzyinput.fuzzyfikasi(input[i]);
        }
//        System.out.println(tempFuzzy[0][0]);
//        System.out.println(tempFuzzy[0][1]);
//        System.out.println(tempFuzzy[0][2]);
        return tempFuzzy;
    }

    public static void makeRule() {
//        System.out.println("xxx : " + fuzzyinput[1].length);
        for (int i = 0; i < dataFuzzyInput[1].length; i++) {
            Rule tempRule = new Rule();
            double[] x1 = dataFuzzyInput[0][i];
            double[] x2 = dataFuzzyInput[1][i];
            double[] x3 = dataFuzzyInput[2][i];
            double[] target = dataFuzzyInput[3][i];

            tempRule.setRule(x1, x2, x3, target);
            boolean temp = checkRule(tempRule);
            if (temp == false) {
                rules.add(tempRule);
//                System.out.println(i);
//                System.out.println(tempRule.getRule()[0][1]);
//                System.out.println(tempRule.getRule()[1][1]);
//                System.out.println(tempRule.getRule()[2][1]);
//                System.out.println("");
            }
        }
    }

    public void doStructureLearning() {
        makeAll(0);
        makeAll(1);
        makeAll(2);
        makeAll(3);

        makeRule();
//        shareWeight1();
//        shareWeight2();
        System.out.println(data[0].getMax());
        System.out.println(data[0].getMin());
        printRule();
    }
}
