/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package nefprox;

/**
 *
 * @author Adam
 */

import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;

public class Nefprox {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        
        //import data
        int baris =6017;
        int kolom =4;
        double temp;
        double [] tempFuzzy = new double[2];
        String[][] rawdata = new String[kolom][baris];
        double[][] data = new double[kolom][baris];
        String dir="src\\data\\BUNDESBANK-BBK01_WT5511.xls";
        Data d = new Data();
        FuzzyfikasiSegitiga f = new FuzzyfikasiSegitiga();
        
//        try {
//
//            Workbook w = Workbook.getWorkbook(new File("src\\data\\BUNDESBANK-BBK01_WT5511.xls")); //ambil data
//            Sheet sh = w.getSheet(2);               //sheet kedua
//
//            for (int i = 0; i < kolom; i++) {
//                for (int j = 0; j < baris; j++) {
//                    Cell c = sh.getCell(i, j);
//                    String isi = c.getContents();
//                    rawdata[i][j] = isi;
//
//                }
//            }
//        } catch (IOException ex) {
//            Logger.getLogger(Nefprox.class.getName()).log(Level.SEVERE, null, ex);
//        } catch (BiffException ex) {
//            Logger.getLogger(Nefprox.class.getName()).log(Level.SEVERE, null, ex);
//        }
//                
//
//        
//        for (int i = 0; i < kolom; i++) {
//            for (int j = 0; j < baris; j++) {
//                data[i][j] = Double.parseDouble(rawdata[i][j]);
//            }
//        }
//        
//        for (int i = 0; i < rawdata[1].length; i++) {
//            for (int j = 0; j < rawdata.length; j++) {
//                System.out.print(rawdata[j][i]+" ");
//            }
//            System.out.println("");
//        }
    

       rawdata=d.getData(dir);
       data=d.dataDouble(rawdata);
       //d.dataDouble(rawdata);
       
        System.out.println("data double");
//        for (int i = 0; i < rawdata[1].length; i++) {
//            for (int j = 0; j < rawdata.length; j++) {
//                System.out.print(i+". "+data[j][i]+" ");
//            }
//            System.out.println("");
//        }
        
        temp=data[0][0];
        System.out.println("temp "+temp);
        tempFuzzy=f.dofuzy(temp);
        
        for (int i = 0; i <= 2; i++) {
            System.out.println("fuzzy "+tempFuzzy[i]);
        }
        //tes fuzzy
        
    }
    
}
