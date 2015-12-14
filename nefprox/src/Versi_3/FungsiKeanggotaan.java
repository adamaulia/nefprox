/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Versi_3;

import Versi_2.*;
import java.util.ArrayList;

/**
 *
 * @author mickeyMice
 */
public class FungsiKeanggotaan {
    private double[] kaki = new double[6];
    
    public FungsiKeanggotaan (){}
    
    public void setBatas(int idx,double b1){
        kaki[idx]=b1;
    }
    
    public double getKaki(int idx){
        return kaki[idx];
    }
}
