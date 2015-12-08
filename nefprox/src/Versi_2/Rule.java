/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Versi_2;

import java.util.Arrays;

/**
 *
 * @author mickeyMice
 */
public class Rule {

    private String[] x1 = new String[2];
    private String[] x2 = new String[2];
    private String[] x3 = new String[2];
    private String[] target = new String[2];

    public Rule() {
    }

    public Rule(double[] input1, double[] input2, double[] input3, double[] input4) {
        x1 = Choose(input1);
        x2 = Choose(input2);
        x3 = Choose(input3);
        //target = Choose(input4);
        target = konsekuen(x1, x2, x3);
    }

    public void setRule(double[] input1, double[] input2, double[] input3, double[] input4) {
        x1 = Choose(input1);
        x2 = Choose(input2);
        x3 = Choose(input3);
        //target = Choose(input4);
        target = konsekuen(x1, x2, x3);
    }

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

    public String[] konsekuen(String[] X1, String[] X2, String[] X3) {
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
        output[0] = tempdouble[0] + "";

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

//        System.out.println("X1[0]"+X1[0]);
//        System.out.println("X1[0]"+X2[0]);
//        System.out.println("X1[0]"+X3[0]);
<<<<<<< HEAD

        
        //menentukan hasil variabel linguistik sedang,rendah,tinggi
        
        if(tinggi==3){
            output[1]="tinggi";
        }else if(sedang==3){
            output[1]="sedang";
        }else if(rendah==3){
            output[1]="rendah";
        }else if((rendah==2)&&(sedang==1)){
            output[1]="rendah";
        }else if((sedang==2)&&(rendah==1)){
            output[1]="sedang";
        }else if((sedang==2)&&(tinggi==1)){
            output[1]="sedang";
        }else if((tinggi==2)&&(sedang==1)){
            output[1]="tinggi";
        }else if((rendah==2)&&(tinggi==1)){
            output[1]="sedang";
        }else if((tinggi==2)&&(rendah==1)){
            output[1]="tinggi";

=======
>>>>>>> origin/master
        if (tinggi == 3) {
            output[1] = "Tinggi";
        } else if (sedang == 3) {
            output[1] = "Sedang";
        } else if (rendah == 3) {
            output[1] = "Rendah";
        } else if ((rendah == 2) && (sedang == 1)) {
            output[1] = "Rendah";
        } else if ((sedang == 2) && (rendah == 1)) {
            output[1] = "Sedang";
        } else if ((sedang == 2) && (tinggi == 1)) {
            output[1] = "Sedang";
        } else if ((tinggi == 2) && (sedang == 1)) {
            output[1] = "Tinggi";
        } else if ((rendah == 2) && (tinggi == 1)) {
            output[1] = "Sedang";
        } else if ((tinggi == 2) && (rendah == 1)) {
            output[1] = "Tinggi";
<<<<<<< HEAD

=======
>>>>>>> origin/master
        }

//        if(tempdouble[0]<0.3){
//            output[1]="Rendah";
//        }else if((tempdouble[0]>0.3)&&(tempdouble[0]<=0.6)){
//            output[1]="Sedang";
//        }else if(tempdouble[0]>0.6){
//            output[1]="Sedang";
//        }
<<<<<<< HEAD
        
        }
		return output;
=======
        return output;
>>>>>>> origin/master
    }

    public String[][] getRule() {
        String temp[][] = new String[4][2];
        temp[0] = x1;
        temp[1] = x2;
        temp[2] = x3;
        temp[3] = konsekuen(x1,x2,x3);
//        temp[3] = target;

        return temp;
    }
}
