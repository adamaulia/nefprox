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
        for (int i=0; i<rules.size(); i++){
            String[][] tempRule = rules.get(i).getRule();
            String temp = "IF X1="+tempRule[0][0]+"("+tempRule[0][1]+") AND "+tempRule[1][0]+"("+tempRule[1][1]+") AND "+tempRule[2][0]+"("+tempRule[2][1]+") THEN "+tempRule[3][0]+"("+tempRule[3][1]+")";
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
                break;
            }
        }
        return temp;
    }
    
    public static  void makeRule(){
        Rule tempRule = new Rule();
        boolean temp;
        for(int i=0; i<fuzzyinput.length; i++){
            tempRule.setRule(fuzzyinput[i], fuzzyinput[1], fuzzyinput[2], fuzzyinput[3]);
            temp = checkRule(tempRule);
            if(temp==false){
                rules.add(tempRule);
            }
        }
    }
    
    public static void makeFuzzyInput(double[] input){
        fuzzyinput = new double[input.length][2];
        for (int i=0; i<input.length; i++){
            fuzzyinput[i] = fuzzy.doFuzzy(input[i]);
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
        printRule();
    }

}
