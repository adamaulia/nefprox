/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Versi_2;

/**
 *
 * @author Adam
 */
public class Defuzzyfikasi {
    
    private double output;
    private double []crips={10,20,30,40,50,60,70,80,90,100}; //y
    
    public Defuzzyfikasi(){}
    
    public Defuzzyfikasi(double[] input){        
        dodefuzyfikasi(input);
    }
    
    public double doSugeno(double input){
        double output = 0;
        double rendah = 40;
        double sedang = 60;
        double tinggi = 80;
        
        //output = ((rendah*input)+(sedang*input)+(tinggi*input)/());
        
        return output;
    }
    
    
    public double dodefuzyfikasi(double[] input){
        double tempRendah=0;
        double tempSedang=0;
        double tempTinggi=0;
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
