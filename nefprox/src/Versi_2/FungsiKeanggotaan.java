/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Versi_2;

import java.util.ArrayList;

/**
 *
 * @author mickeyMice
 */
public class FungsiKeanggotaan {
    double[] batas = new double[6];
//    double[][] nilai = new double[3][2];
    
    public FungsiKeanggotaan (){}
    
    public FungsiKeanggotaan(double b_min, double b_max){
        batas[0] = b_min;
        batas[5] = b_max;
    }
    
    public void setBatas(int idx,double b1){
        batas[idx]=b1;
    }
    
    public double getBatas(int idx){
        double temp = batas[idx];
        return temp;
    }
}
