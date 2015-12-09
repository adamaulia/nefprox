/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Versi_2;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.logging.Level;
import java.util.logging.Logger;
import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;
import nefprox.Nefprox;

/**
 *
 * @author mickeyMice
 */
public class Data {
    double[] value;
    ArrayList<Double> as = new ArrayList<>();
    int numrow=0;
    double min=0;
    double max=0;
    
    public void loadData(String dir){
        try {

            Workbook w = Workbook.getWorkbook(new File(dir)); //ambil data
            Sheet sh = w.getSheet(0);               //sheet kedua
            numrow = sh.getRows();
            value = new double[numrow];
            

            for (int i = 0; i < numrow-1; i++) {
                    Cell c = sh.getCell(1, i+1);
                    double tempData = Double.parseDouble(c.getContents());
                    as.add(tempData);
                    value[i]=(tempData);
            }
            
            
        } catch (IOException ex) {
            Logger.getLogger(Nefprox.class.getName()).log(Level.SEVERE, null, ex);
        } catch (BiffException ex) {
            Logger.getLogger(Nefprox.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void setMaxMin(){
        Collections.sort(as);
        max = as.get(as.size()-1);
        min = as.get(0);
        System.out.println("max = "+max);
        System.out.println("min = "+min);
    }
    
    public double getMax(){
        return max;
    }
    
    public double getMin(){
        return min;
    }
    
    public int getNumRow(){
        return numrow;
    }
    
    public double[] getAllData(){
        return value;
    }
}
