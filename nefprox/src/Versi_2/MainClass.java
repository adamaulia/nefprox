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
    
    public static void printRule(){
        String[][] tempRule;
        for (int i=0; i<rules.size(); i++){
            tempRule = rules.get(i).getRule();
            String temp = i+" : IF X1="+tempRule[0][0]+"("+tempRule[0][1]+") AND X2="+tempRule[1][0]+"("+tempRule[1][1]+") AND X3="+tempRule[2][0]+"("+tempRule[2][1]+") THEN "+tempRule[3][0]+"("+tempRule[3][1]+")";
            System.out.println(temp);
        }
    }
    
    public static boolean checkRule(Rule inputRule){
        boolean temp=false;
        //False : belum ada
        //True  : sudah ada
        
        for (int i=0; i<rules.size(); i++){
            if(inputRule.getRule()==rules.get(i).getRule()){
                temp=true;
            }
        }
        return temp;
    }
    
    public static  void makeRule(){
        Rule tempRule = new Rule();
        boolean temp;
        for(int i=0; i<fuzzyinput.length-3; i++){
            tempRule.setRule(fuzzyinput[i], fuzzyinput[i+1], fuzzyinput[i+2], fuzzyinput[i+3]);
            temp = checkRule(tempRule);
            if(temp==false){
                rules.add(tempRule);
            }
        }
    }
    
    public static void makeFuzzyInput(double[] input){
        fuzzyinput = new double[input.length][3];
        for (int i=0; i<input.length; i++){
            fuzzyinput[i] = fuzzy.doFuzzy(input[i]);
            System.out.println(i);
            System.out.println(fuzzyinput[i][0]);
            System.out.println(fuzzyinput[i][1]);
            System.out.println(fuzzyinput[i][2]);
            System.out.println("");
        }
    }
    
    public static void main(String[] args) {
        Data data = new Data();
        data.loadData("src\\data\\BUNDESBANK-BBK01_WT5511.xls");
        
        FungsiKeanggotaan fk = new FungsiKeanggotaan();
        fk.setBatas(0, data.getMin());
        fk.setBatas(1, 267);
        fk.setBatas(2, 732);
        fk.setBatas(3, 965);
        fk.setBatas(4, 1431);
        fk.setBatas(5, data.getMax());
        
        fuzzy = new Fuzzifikasi(fk,data.getMax(),data.getMin());
        makeFuzzyInput(data.getAllData());
        makeRule();
//        printRule();
    }

}
