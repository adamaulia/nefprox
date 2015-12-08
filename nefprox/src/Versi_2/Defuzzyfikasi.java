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
    private double []crips={0,10,20,30,40,50,60,70,80,90,100}; //y
    
    public Defuzzyfikasi(double input){        
        dodefuzyfikasi(input);
    }
    
    
    public double dodefuzyfikasi(double input){
        double temp = 0;
        double temp2=0;
        
        for (int i = 0; i < crips.length; i++) {
            temp=temp+(crips[i]*input);
        }
        
        for (int i = 0; i < crips.length; i++) {
            temp2=temp2+input;
        }
        
        output=temp/temp2;
        return output; 
    }

}
