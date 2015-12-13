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
    static FungsiKeanggotaan fkInput1 = new FungsiKeanggotaan();
    static FungsiKeanggotaan fkInput2 = new FungsiKeanggotaan();
    static FungsiKeanggotaan fkInput3 = new FungsiKeanggotaan();
    static FungsiKeanggotaan fkTarget = new FungsiKeanggotaan();
    static Fuzzy fuzzy ;
    static Data data[] = new Data[4];
    static double[][][] dataFuzzyInput = new double[4][][];

    public static void printRule() {
        double[][] tempRule;
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
        double[][] tempRuleInput = inputRule.getRule();
        for (int i = 0; i < rules.size(); i++) {
            double[][] tempRule = rules.get(i).getRule();
            if ((tempRuleInput[0][0]==(tempRule[0][0]))
                    && (tempRuleInput[1][0]==(tempRule[1][0]))
                    && (tempRuleInput[2][0]==(tempRule[2][0]))
                    && (tempRuleInput[3][0]==(tempRule[3][0]))
                    ) {

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
        return temp;
    }

    public static void makeAll(int idx, int jenis) {
        data[idx] = new Data();
        data[idx].loadData("src\\data\\BUNDESBANK-BBK01_WT5511.xls", idx);
        data[idx].normalisasi();
        data[idx].setMaxMin();

        if (jenis == 0) {
            fk[idx] = new Versi_2.FungsiKeanggotaan();
            fk[idx].setBatas(0, data[idx].getMin());
            fk[idx].setBatas(1, 267);
            fk[idx].setBatas(2, 732);
            fk[idx].setBatas(3, 965);
            fk[idx].setBatas(4, 1431);
            fk[idx].setBatas(5, data[idx].getMax());
        }

        fuzzy[idx] = new Fuzzifikasi(fk[idx], data[idx].getMax(), data[idx].getMin());

        dataFuzzyInput[idx] = new double[data[idx].getNumRow() - 3][3];
        dataFuzzyInput[idx] = makeFuzzyInput(data[idx].getAllData(), fuzzy[idx]);
    }
    
    public static void makeRule() {
//        System.out.println("xxx : " + fuzzyinput[1].length);
        for (int i = 0; i < dataFuzzyInput[1].length - 3; i++) {
            Versi_2.Rule tempRule = new Versi_2.Rule();
//            tempRule.setRule(fuzzyinput[i], fuzzyinput[i + 1], fuzzyinput[i + 2], fuzzyinput[i + 3]);
            double[] x1 = dataFuzzyInput[0][i];
            double[] x2 = dataFuzzyInput[1][i];
            double[] x3 = dataFuzzyInput[2][i];
            double[] target = dataFuzzyInput[3][i];

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

}
