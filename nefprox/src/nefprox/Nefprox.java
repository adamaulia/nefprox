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
        StructureLearning sl = new StructureLearning();
        FuzzyfikasiSegitiga f = new FuzzyfikasiSegitiga();
        rawdata=d.getData(dir);
        data=d.dataDouble(rawdata);

        
        sl.inisialisasi();
    }
    
}
