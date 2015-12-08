/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Versi_2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;

/**
 *
 * @author mickeyMice
 */
public class Rule {

    private String[] x1 = new String[2];
    private String[] x2 = new String[2];
    private String[] x3 = new String[2];
    private String[] target = new String[2];
    private String[] konsekuen = new String[2];
    static String[][] staticRule = new String[27][3];

    public Rule() {
    }

    public Rule(double[] input1, double[] input2, double[] input3, double[] input4) {
        x1 = Choose(input1);
        x2 = Choose(input2);
        x3 = Choose(input3);
        target = Choose(input4);
        konsekuen = setKonsekuen(x1, x2, x3);
//        setStaticRule();
    }

    public void setRule(double[] input1, double[] input2, double[] input3, double[] input4) {
        x1 = Choose(input1);
        x2 = Choose(input2);
        x3 = Choose(input3);
        target = Choose(input4);
        konsekuen = setKonsekuen(x1, x2, x3);
    }

//    public static void setStaticRule() {
//        staticRule[0] = new String[]{"Rendah","Rendah","Rendah","Rendah"};
//        staticRule[1] = new String[]{"Rendah","Rendah","Sedang","Rendah"};
//        staticRule[2] = new String[]{"Rendah","Rendah","Tinggi","Rendah"};
//        staticRule[3] = new String[]{"Rendah","Sedang","Rendah","Rendah"};
//        staticRule[4] = new String[]{"Rendah","Sedang","Sedang","Rendah"};
//        staticRule[5] = new String[]{"Rendah","Sedang","Tinggi","Rendah"};
//        staticRule[6] = new String[]{"Rendah","Tinggi","Rendah","Rendah"};
//        staticRule[7] = new String[]{"Rendah","Tinggi","Sedang","Rendah"};
//        staticRule[8] = new String[]{"Rendah","Tinggi","Tinggi","Rendah"};
//        
//        staticRule[9] = new String[]{"Sedang","Rendah","Rendah","Rendah"};
//        staticRule[10] = new String[]{"Sedang","Rendah","Sedang","Rendah"};
//        staticRule[11] = new String[]{"Sedang","Rendah","Tinggi","Rendah"};
//        staticRule[12] = new String[]{"Sedang","Sedang","Rendah","Rendah"};
//        staticRule[13] = new String[]{"Sedang","Sedang","Sedang","Rendah"};
//        staticRule[14] = new String[]{"Sedang","Sedang","Tinggi","Rendah"};
//        staticRule[15] = new String[]{"Sedang","Tinggi","Rendah","Rendah"};
//        staticRule[16] = new String[]{"Sedang","Tinggi","Sedang","Rendah"};
//        staticRule[17] = new String[]{"Sedang","Tinggi","Tinggi","Rendah"};
//        
//        staticRule[18] = new String[]{"Tinggi","Rendah","Rendah","Rendah"};
//        staticRule[19] = new String[]{"Tinggi","Rendah","Sedang","Rendah"};
//        staticRule[20] = new String[]{"Tinggi","Rendah","Tinggi","Rendah"};
//        staticRule[21] = new String[]{"Tinggi","Sedang","Rendah","Rendah"};
//        staticRule[22] = new String[]{"Tinggi","Sedang","Sedang","Rendah"};
//        staticRule[23] = new String[]{"Tinggi","Sedang","Tinggi","Rendah"};
//        staticRule[24] = new String[]{"Tinggi","Tinggi","Rendah","Rendah"};
//        staticRule[25] = new String[]{"Tinggi","Tinggi","Sedang","Rendah"};
//        staticRule[26] = new String[]{"Tinggi","Tinggi","Tinggi","Rendah"};
//    }
    
//    public String[] getKonsekuen(String[] x1,String[] x2,String[] x3){
//        String temp[]=new String[2];
//        ArrayList<Double> tempValue = new ArrayList<>();
//        for(int i=0; i<staticRule.length; i++){
//            if(staticRule[i][0]==x1[0] && staticRule[i][1]==x2[0] && staticRule[i][2]==x2[0]){
//                tempValue.clear();
//                tempValue.add(Double.parseDouble(x1[1]));
//                tempValue.add(Double.parseDouble(x2[1]));
//                tempValue.add(Double.parseDouble(x3[1]));
//                Collections.sort(tempValue);
//                double max = tempValue.get(tempValue.size()-1);
//                temp[0]=staticRule[i][3];
//                temp[1]=String.valueOf(max);
//                break;
//            }
//        }
//        return temp;
//    }
    
    public String[] Choose(double[] input) {
        double max = input[0];
        int idx = 0;
        String temp[] = new String[2];
        for (int i = 0; i < input.length; i++) {
            if (input[i] > max) {
                max = input[i];
                idx = i;
            }
        }
        if (idx == 0) {
            temp[0] = "Rendah";
        } else if (idx == 1) {
            temp[0] = "Sedang";
        } else {
            temp[0] = "Tinggi";
        }

        temp[1] = String.valueOf(max);
        return temp;

    }

    public String[] setKonsekuen(String[] X1, String[] X2, String[] X3) {
        double d1, d2, d3;
        double[] tempdouble = new double[3];
        String[] output = new String[2];
        String[] tempoutput = new String[3];
        int rendah = 0, sedang = 0, tinggi = 0;

        d1 = Double.valueOf(X1[1]);
        d2 = Double.valueOf(X2[1]);
        d3 = Double.valueOf(X3[1]);

        tempdouble[0] = d1;
        tempdouble[1] = d2;
        tempdouble[2] = d3;

        Arrays.sort(tempdouble);
//        System.out.println(tempdouble[0]+" "+tempdouble[1]+" "+tempdouble[2]);
        output[1] = tempdouble[0] + "";
        

        tempoutput[0] = X1[0];
        tempoutput[1] = X2[0];
        tempoutput[2] = X3[0];

        for (int i = 0; i < tempoutput.length; i++) {
            if (tempoutput[i].equals("Rendah")) {
                rendah = rendah + 1;
            } else if (tempoutput[i].equals("Sedang")) {
                sedang = sedang + 1;
            } else if (tempoutput[i].equals("Tinggi")) {
                tinggi = tinggi + 1;
            }
        }

        //menentukan hasil variabel linguistik sedang,rendah,tinggi
        if (tinggi == 3) {
            output[0] = "Tinggi";
        } else if (sedang == 3) {
            output[0] = "Sedang";
        } else if (rendah == 3) {
            output[0] = "Rendah";
        } else if ((rendah == 2) && (sedang == 1)) {
            output[0] = "Rendah";
        } else if ((sedang == 2) && (rendah == 1)) {
            output[0] = "Sedang";
        } else if ((sedang == 2) && (tinggi == 1)) {
            output[0] = "Sedang";
        } else if ((tinggi == 2) && (sedang == 1)) {
            output[0] = "Tinggi";
        } else if ((rendah == 2) && (tinggi == 1)) {
            output[0] = "Sedang";
        } else if ((tinggi == 2) && (rendah == 1)) {
            output[0] = "Tinggi";

        }

//        if(tempdouble[0]<0.3){
//            output[1]="Rendah";
//        }else if((tempdouble[0]>0.3)&&(tempdouble[0]<=0.6)){
//            output[1]="Sedang";
//        }else if(tempdouble[0]>0.6){
//            output[1]="Sedang";
//        }
        return output;

    }

    public String[][] getRule() {
        String temp[][] = new String[4][2];
        setKonsekuen(x1, x2, x3);
        temp[0] = x1;
        temp[1] = x2;
        temp[2] = x3;
        temp[3] = konsekuen;

        return temp;
    }
    
    public void setX1(String nilai){
    }
}
