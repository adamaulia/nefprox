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
        double [] nilaiLinguistik = new double[3];
        nilaiLinguistik[0]=0;
        nilaiLinguistik[1]=0;
        nilaiLinguistik[2]=0;
        
        
        double tengah = ((fk.getBatas(1)+fk.getBatas(4))/2) ;
        //buat rendah daerah 1, value<732
        
        if(input < fk.getBatas(1)){
            nilaiLinguistik[0]=-(input-fk.getBatas(2))/(fk.getBatas(2)-minValue);
            nilaiLinguistik[1]=0;
            nilaiLinguistik[2]=0;
            
        }else 
//     buat sedang / tengah daerah 2 , 267<value<849
        if((input > fk.getBatas(1)) &&(input <=tengah ) ){
           nilaiLinguistik[0]= -(input-fk.getBatas(2))/(fk.getBatas(2)-minValue);
           nilaiLinguistik[1]=(input-fk.getBatas(1))/(tengah-fk.getBatas(1));
           nilaiLinguistik[2]=0;
            
        }else
//      buat tinggi atau sedang daerah 3 , 849<value<1431
        if((input >tengah)&&(input <= fk.getBatas(4))) {
           nilaiLinguistik[0]=0;
           nilaiLinguistik[1]=-(input-fk.getBatas(3))/(fk.getBatas(3)-tengah);
           nilaiLinguistik[2]=(-(input-fk.getBatas(3))/(maxValue-fk.getBatas(4)));
            
        }else
//      buat tinggi  daerah 4, value>1431
        if(input > fk.getBatas(4)){
          nilaiLinguistik[0]=0;
          nilaiLinguistik[1]=0;
          nilaiLinguistik[2]=((input-fk.getBatas(3))/(maxValue-fk.getBatas(4)));
            
        }
        return nilaiLinguistik;
    }
    
}
