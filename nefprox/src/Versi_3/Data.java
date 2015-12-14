/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Versi_3;

import Versi_2.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
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
    int numrow = 0;
    double min = 0;
    double max = 0;
    int finish, start;

    public void loadData(String dir, int jenis) {
        try {
            int start, finish;
            Workbook w = Workbook.getWorkbook(new File(dir)); //ambil data
            Sheet sh = w.getSheet(0);               //sheet kedua
            numrow = sh.getRows() - 1;

            if (jenis == 0) {
                start = 1;
                finish = numrow - 3;
            } else if (jenis == 1) {
                start = 2;
                finish = numrow - 2;
            } else if (jenis == 2) {
                start = 3;
                finish = numrow - 1;
            } else {
                start = 4;
                finish = numrow;
            }

            value = new double[(finish - start) + 1];
            int j = 0;
            for (int i = start; i <=finish; i++) {
                Cell c = sh.getCell(1, i);
                double tempData = Double.parseDouble(c.getContents());
                value[j] = (tempData);
                j++;
            }
            setMaxMin();
            normalisasi();
            setMaxMin();

        } catch (IOException ex) {
            Logger.getLogger(Nefprox.class.getName()).log(Level.SEVERE, null, ex);
        } catch (BiffException ex) {
            Logger.getLogger(Nefprox.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void setMaxMin() {
        double temp[] = value;
        Arrays.sort(temp);
        max = temp[value.length - 1];
        min = temp[0];
    }

    public double getMax() {
        return max;
    }

    public double getMin() {
        return min;
    }

    public int getNumRow() {
        return numrow;
    }

    public double[] getAllData() {
        return value;
    }

    public void normalisasi() {
        double batasMax = 0.9;
        double batasMin = 0.1;
        double temp[]=new double[numrow-3];
        for (int i = 0; i <(numrow-3); i++) {
            temp[i] = (((value[i] - min) / (max - min)) * (batasMax - batasMin) + batasMin);
        }
        value = temp;
    }
    
}
