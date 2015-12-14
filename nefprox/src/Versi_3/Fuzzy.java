/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Versi_3;

import Versi_2.*;

/**
 *
 * @author mickeyMice
 */
public class Fuzzy {   
    private FungsiKeanggotaan fk;
    private double output[]=new double[3];
    
    public Fuzzy(){
        
    }
    
    public void setFK(FungsiKeanggotaan fk){
        this.fk = fk;
    }
    public Fuzzy(FungsiKeanggotaan temp){
        fk = temp;
    }
    
    public double[] fuzzyfikasi(double input){
        double[] tempOutput = new double[3];

        //batas 0 : nilai minimal data (kaki pertama segitiga pertama)
        //batas 1 : kaki pertama segitiga kedua
        //batas 2 : kaki kedua segitiga pertama
        //batas 3 : kaki pertama segitiga ketiga
        //batas 4 : kaki kedua segitiga kedua
        //batas 5 : nilai maksimal data (kaki kedua segitiga ketiga)
        double tengah = ((fk.getKaki(1) + fk.getKaki(4)) / 2);

        if (input <= fk.getKaki(1)) { //buat rendah daerah 1, value<732
            tempOutput[0] = (fk.getKaki(2) - input) / (fk.getKaki(2) - fk.getKaki(0));
            tempOutput[1] = 0;
            tempOutput[2] = 0;
        } else if ((input > fk.getKaki(1)) && (input <= fk.getKaki(2))) { //     buat sedang / tengah daerah 2 , 267<value<849
            tempOutput[0] = (fk.getKaki(2) - input) / (fk.getKaki(2) - fk.getKaki(0));
            tempOutput[1] = (input - fk.getKaki(1)) / (tengah - fk.getKaki(1));
            tempOutput[2] = 0;
        } else if (input > fk.getKaki(2) && input <= fk.getKaki(3)) {
            if (input < tengah) {
                tempOutput[0] = 0;
                tempOutput[1] = (input - fk.getKaki(1)) / (tengah - fk.getKaki(1));
                tempOutput[2] = 0;
            } else {
                tempOutput[0] = 0;
                tempOutput[1] = (fk.getKaki(4) - input) / (fk.getKaki(4) - tengah);
                tempOutput[2] = 0;
            }
        } else if ((input > fk.getKaki(3)) && (input <= fk.getKaki(4))) { //      buat tinggi atau sedang daerah 3 , 849<value<1431
            tempOutput[0] = 0;
            tempOutput[1] = (fk.getKaki(4) - input) / (fk.getKaki(4) - tengah);
            tempOutput[2] = (input - fk.getKaki(3)) / (fk.getKaki(5) - fk.getKaki(3));
        } else if (input > fk.getKaki(4)) { //      buat tinggi  daerah 4, value>1431
            tempOutput[0] = 0;
            tempOutput[1] = 0;
            tempOutput[2] = (input - fk.getKaki(3)) / (fk.getKaki(5) - fk.getKaki(3));
        }

        return tempOutput;
    }
    
    public double dodefuzyfikasi(double[] input){
        double tempRendah=0;
        double tempSedang=0;
        double tempTinggi=0;
        double output;
        //rendah 10,20,30
        //sedang 40,50,60,70
        //tinggi 80,90,100
        
        for (int i = 0; i <=100; i+=10) {
            if(i<40){
                tempRendah = tempRendah+tempRendah*input[0]; //rendah
            }if (i >=40 && i<80){
                tempSedang = tempSedang+tempSedang*input[1]; //sedang
            }
            if(i >=80){
                tempTinggi = tempTinggi+tempTinggi*input[2]; //tinggi
            }
        }
            
        
        output=tempRendah+tempSedang+tempTinggi/((input[0]*3)+(input[1]*4)+(input[2]*3));
        return output; 
    }
    
}
