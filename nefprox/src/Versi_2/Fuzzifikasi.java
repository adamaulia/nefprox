/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Versi_2;

/**
 *
 * @author mickeyMice
 */
public class Fuzzifikasi {   
    FungsiKeanggotaan fk;
    double output[]=new double[3];
    int rownum = 0;
    double maxValue;
    double minValue;
    
    public Fuzzifikasi(FungsiKeanggotaan temp, double max,double min){
        fk = temp;
        this.rownum = rownum;
        maxValue=max;
        minValue=min;
    }
    
    public double[] doFuzzy(double input){
        double []nilaiLinguistik = new double[3];
//        nilaiLinguistik[0]=0;
//        nilaiLinguistik[1]=0;
//        nilaiLinguistik[2]=0;
        
        
        double tengah = ((fk.getBatas(1)+fk.getBatas(4))/2) ;
        
        
        if(input <= fk.getBatas(1)){ //buat rendah daerah 1, value<732
            nilaiLinguistik[0]=(fk.getBatas(2)-input)/(fk.getBatas(2)-fk.getBatas(0));
            nilaiLinguistik[1]=0;
            nilaiLinguistik[2]=0; 
        }else if((input > fk.getBatas(1)) && (input <=fk.getBatas(2) ) ){ //     buat sedang / tengah daerah 2 , 267<value<849
            nilaiLinguistik[0]=(fk.getBatas(2)-input)/(fk.getBatas(2)-fk.getBatas(0));
            nilaiLinguistik[1]=(input-fk.getBatas(1))/(tengah-fk.getBatas(1));
            nilaiLinguistik[2]=0;
        }else if(input>fk.getBatas(2) && input<=fk.getBatas(3)){
            if(input<tengah){
                nilaiLinguistik[0]=0;
                nilaiLinguistik[1]=(input-fk.getBatas(1))/(tengah-fk.getBatas(1));
                nilaiLinguistik[2]=0;
            }else{
                nilaiLinguistik[0]=0;
                nilaiLinguistik[1]=(fk.getBatas(4)-input)/(fk.getBatas(4)-tengah);
                nilaiLinguistik[2]=0;
            }
        }else if((input >fk.getBatas(3))&&(input <= fk.getBatas(4))) { //      buat tinggi atau sedang daerah 3 , 849<value<1431
            nilaiLinguistik[0]=0;
            nilaiLinguistik[1]=(fk.getBatas(4)-input)/(fk.getBatas(4)-tengah);
            nilaiLinguistik[2]=(input-fk.getBatas(3)/(fk.getBatas(5)-fk.getBatas(3)));
        }else if(input > fk.getBatas(4)){ //      buat tinggi  daerah 4, value>1431
            nilaiLinguistik[0]=0;
            nilaiLinguistik[1]=0;
            nilaiLinguistik[2]=(fk.getBatas(5)-input)/(fk.getBatas(5)-fk.getBatas(3));
        }
        
        return nilaiLinguistik;
    }
    
}
