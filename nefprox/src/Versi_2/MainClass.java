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
    static Fuzzifikasi fuzzy[] = new Fuzzifikasi[4];
    static double[][][] fuzzyinput = new double[4][][];
    static double LR = 0.1;
    static Data data[] = new Data[4];
    static FungsiKeanggotaan fk[] = new FungsiKeanggotaan[4];
    static double[] bobot, sharedWeightRO = new double[3];
    static double[] targettt;

    static VarOutput vo;

    public static void printRule() {
        String[][] tempRule;
//        System.out.println(rules.size());
        for (int i = 0; i < rules.size(); i++) {
            tempRule = rules.get(i).getRule();
//            System.out.println(tempRule[0][1]+" "+tempRule[1][1]+" "+tempRule[2][1]+" "+tempRule[3][0]);
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
                    && (tempRuleInput[2][0].equals(tempRule[2][0])) //                    && (tempRuleInput[3][0].equals(tempRule[3][0]))
                    ) {

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
//                if (Double.parseDouble(tempRuleInput[3][1]) > Double.parseDouble(tempRule[3][1])) {
//                    tempRule[3][1] = tempRuleInput[3][1];
//                }
            }
        }
        return temp;
    }

    public static void makeRule() {
//        System.out.println("xxx : " + fuzzyinput[1].length);
        for (int i = 0; i < fuzzyinput[1].length - 3; i++) {
            Rule tempRule = new Rule();
//            tempRule.setRule(fuzzyinput[i], fuzzyinput[i + 1], fuzzyinput[i + 2], fuzzyinput[i + 3]);
            double[] x1 = fuzzyinput[0][i];
            double[] x2 = fuzzyinput[1][i];
            double[] x3 = fuzzyinput[2][i];
            double[] target = fuzzyinput[3][i];

            tempRule.setRule(x1, x2, x3, target);
            boolean temp = checkRule(tempRule);
            if (temp == false) {
                rules.add(tempRule);
//                System.out.println(tempRule.getRule()[0][1]);
//                System.out.println(tempRule.getRule()[1][1]);
//                System.out.println(tempRule.getRule()[2][1]);
//                System.out.println("");
            }
        }
    }

    public static double[][] makeFuzzyInput(double[] input, Fuzzifikasi fuzzyinput) {
        double[][] tempFuzzy = new double[input.length][3];
//        fuzzyinput = new double[input.length][3];
        for (int i = 0; i < input.length; i++) {
            tempFuzzy[i] = fuzzyinput.doFuzzy(input[i]);
        }
        return tempFuzzy;
    }

    public static void shareWeight1() {
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
                rules.get(i).getRule()[3][1] = rules.get(i).setKonsekuen(rules.get(i).getRule()[0], rules.get(i).getRule()[1], rules.get(i).getRule()[2])[1];

            }
        }
    }

    public static void shareWeight2() {
        for (int i = 0; i < rules.size(); i++) {
            String[][] tempRule1 = rules.get(i).getRule();
            for (int j = 0; j < rules.size(); j++) {
                String max;
                String[][] tempRule2 = rules.get(j).getRule();
                if (tempRule1[3][0].equals(tempRule2[3][0])) {
                    if (Double.parseDouble(tempRule1[3][1]) > Double.parseDouble(tempRule2[3][1])) {
                        max = tempRule1[3][1];
                    } else {
                        max = tempRule2[3][1];
                    }
                    rules.get(i).getRule()[3][1] = max;
                    rules.get(j).getRule()[3][1] = max;
//                    System.out.println(max);
//                    System.out.println(tempRule1[3][0]);
                    if (tempRule1[3][0].equals("Rendah")) {
                        sharedWeightRO[0] = Double.parseDouble(max);
//                        System.out.println(sharedWeightRO[0]);
                    }
                    if (tempRule1[3][0].equals("Sedang")) {
                        sharedWeightRO[1] = Double.parseDouble(max);
//                        System.out.println(sharedWeightRO[1]);
                    }
                    if (tempRule1[3][0].equals("Tinggi")) {
                        sharedWeightRO[2] = Double.parseDouble(max);
//                        System.out.println(sharedWeightRO[2]);
                    }
                }
            }
        }
    }

    public static void makeAll(int idx, int jenis) {
        data[idx] = new Data();
        data[idx].loadData("src\\data\\BUNDESBANK-BBK01_WT5511.xls", idx);
        data[idx].setMaxMin();
        if(idx==3){
            targettt = data[idx].getAllData();
        }

        if (jenis == 0) {
            fk[idx] = new FungsiKeanggotaan();
            fk[idx].setBatas(0, data[idx].getMin());
            fk[idx].setBatas(1, 267);
            fk[idx].setBatas(2, 732);
            fk[idx].setBatas(3, 965);
            fk[idx].setBatas(4, 1431);
            fk[idx].setBatas(5, data[idx].getMax());
        }

        fuzzy[idx] = new Fuzzifikasi(fk[idx], data[idx].getMax(), data[idx].getMin());

        fuzzyinput[idx] = new double[data[idx].getNumRow() - 3][3];
        fuzzyinput[idx] = makeFuzzyInput(data[idx].getAllData(), fuzzy[idx]);
    }

    public static void setBobotRuleToOutput() {
        bobot = new double[rules.size()];
        for (int i = 0; i < bobot.length; i++) {
            String kons = rules.get(i).getKonsekuen();
            if (kons.equals("Rendah")) {
                bobot[i] = sharedWeightRO[0];
            } else if (kons.equals("Sedang")) {
                bobot[i] = sharedWeightRO[1];
            } else if (kons.equals("Tinggi")) {
                bobot[i] = sharedWeightRO[2];
            }
//            System.out.println(bobot[i]);
        }
//        System.out.println("1: " + sharedWeightRO[0]);
//        System.out.println("2: " + sharedWeightRO[1]);
//        System.out.println("3: " + sharedWeightRO[2]);
    }

    public static void parameterLearning(int idx) {
        setBobotRuleToOutput();
        Defuzzyfikasi def = new Defuzzyfikasi();
        double tempA[] = new double[rules.size()];
        double tempB[] = new double[rules.size()];
        Rule[] tempRule = new Rule[rules.size()];
        for (int i = 0; i < rules.size(); i++) {
            tempA[i] = def.dodefuzyfikasi(rules.get(i).getTarget());
            tempB[i] = def.dodefuzyfikasi(rules.get(i).getValueKonsekuen());
            tempRule[i] = rules.get(i);
        }
        
       
        vo = new VarOutput(tempA, tempB, bobot);
        vo.setRulesIn(tempRule);
        vo.setFK(fk[idx]);
        vo.countDelta();
        double temp[][] = vo.getGeser();
        for (int i = 0; i < rules.size(); i++) {
//            System.out.println("Before");
//            System.out.println(fk[idx].getBatas(4));
//            System.out.println(fk[idx].getBatas(1));
//            System.out.println(fk[idx].getBatas(2));
//            System.out.println(fk[idx].getBatas(3));
            
            fk[idx].setBatas(2, fk[idx].getBatas(2) + temp[i][2]);

            fk[idx].setBatas(1, fk[idx].getBatas(1) + temp[i][3]);
            fk[idx].setBatas(4, fk[idx].getBatas(4) + temp[i][5]);

            fk[idx].setBatas(3, fk[idx].getBatas(3) + temp[i][6]);
//            System.out.println("After");
//            System.out.println(fk[idx].getBatas(4));
//            System.out.println(fk[idx].getBatas(1));
//            System.out.println(fk[idx].getBatas(2));
//            System.out.println(fk[idx].getBatas(3));

//            fk[1].setBatas(2, fk[1].getBatas(2) + temp[i][2]);
//
//            fk[1].setBatas(1, fk[1].getBatas(1) + temp[i][3]);
//            fk[1].setBatas(4, fk[1].getBatas(4) + temp[i][5]);
//
//            fk[1].setBatas(3, fk[1].getBatas(3) + temp[i][6]);
//
//            fk[2].setBatas(2, fk[2].getBatas(2) + temp[i][2]);
//
//            fk[2].setBatas(1, fk[2].getBatas(1) + temp[i][3]);
//            fk[2].setBatas(4, fk[2].getBatas(4) + temp[i][5]);
//
//            fk[2].setBatas(3, fk[2].getBatas(3) + temp[i][6]);
//
//            fk[3].setBatas(2, fk[3].getBatas(2) + temp[i][2]);
//
//            fk[3].setBatas(1, fk[3].getBatas(1) + temp[i][3]);
//            fk[3].setBatas(4, fk[3].getBatas(4) + temp[i][5]);
//
//            fk[3].setBatas(3, fk[3].getBatas(3) + temp[i][6]);
        }
//        vo.printError();
    }

    public static void main(String[] args) {
//        System.out.println("Generasi 1");
        makeAll(0, 0);
        makeAll(1, 0);
        makeAll(2, 0);
        makeAll(3, 0);

        makeRule();
        shareWeight1();
        shareWeight2();

        printRule();
        
//        parameterLearning(0);
//        parameterLearning(1);
//        parameterLearning(2);
//        parameterLearning(3);

        int epoh = 5;
        for (int i = 0; i < epoh; i++) {
//            System.out.println("");
//            System.out.println("Generasi " + (i + 1));
//            makeAll(0, 1);
//            makeAll(1, 1);
//            makeAll(2, 1);
//            makeAll(3, 1);
//
//            makeRule();
//            shareWeight1();
//            shareWeight2();
//
//            printRule();
            parameterLearning(0);
            parameterLearning(1);
            parameterLearning(2);
            parameterLearning(3);
        }
    }

}
