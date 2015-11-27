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
public class Data {
int baris =6016;
int kolom =4;
    
String[][] rawdata2 = new String[kolom][baris];
Double[][] data2 = new Double[kolom][baris];
 double max1, max2, max3, max4 = 0,min1, min2, min3,min4 =0;    
   
     //String dir="src\\data\\BUNDESBANK-BBK01_WT5511.xls";
    
public String[][] getData(String dir){
        System.out.println(" start get data");
        String[][] rawdata = new String[kolom][baris]; 
        try {

            Workbook w = Workbook.getWorkbook(new File(dir)); //ambil data
            Sheet sh = w.getSheet(2);               //sheet kedua

            for (int i = 0; i < kolom; i++) {
                for (int j = 0; j < baris; j++) {
                    Cell c = sh.getCell(i, j);
                    String isi = c.getContents();
                    rawdata[i][j] = isi;

                }
            }
        } catch (IOException ex) {
            Logger.getLogger(Nefprox.class.getName()).log(Level.SEVERE, null, ex);
        } catch (BiffException ex) {
            Logger.getLogger(Nefprox.class.getName()).log(Level.SEVERE, null, ex);
        }  
        return rawdata;
    }

public Double[][] normalisasi(String[][] rawdata){
    System.out.println("start normalisasi");
       Double[][] data = new Double[kolom][baris];
      
    //convert 2 double    
        for (int i = 0; i < kolom; i++) {
            for (int j = 0; j < baris; j++) {
                data[i][j] = Double.parseDouble(rawdata[i][j]);
            }
        }

//    System.out.println("hasil double");
//    for (int i = 0; i < data[1].length; i++) {
//            for (int j = 0; j < data.length; j++) {
//                System.out.print(i+". "+data[j][i]+" ");
//            }
//            System.out.println("");
//        }
        
        
     //find max & min each column
        max1 = data[0][0];
        for (int i = 0; i < baris; i++) {
            if (data[0][i] > max1) {
                max1 = data[0][i];
            }

        }
        
        //find min baris 1
        min1 = data[0][0];
        for (int i = 0; i < baris; i++) {
            if (data[0][i] < min1) {
                min1 = data[0][i];
            }

        }
//
//        //find max baris 2
//        max2 = data[1][0];
//        for (int i = 0; i < baris; i++) {
//            if (data[1][i] > max2) {
//                max2 = data[1][i];
//            }
//
//        }
//        //find min baris 2
//        min2 = data[1][0];
//        for (int i = 0; i < baris; i++) {
//            if (data[1][i] < min2) {
//                min2 = data[1][i];
//            }
//
//        }
//
//        //find max baris 3
//        max3 = data[2][0];
//        for (int i = 0; i < baris; i++) {
//            if (data[2][i] > max3) {
//                max3 = data[2][i];
//            }
//
//        }
//        //find min baris 3
//        min3 = data[2][0];
//        for (int i = 0; i < baris; i++) {
//            if (data[2][i] < min3) {
//                min3 = data[2][i];
//            }
//
//        }
//        
//        //find max baris 4
//        max3 = data[3][0];
//        for (int i = 0; i < baris; i++) {
//            if (data[3][i] > max3) {
//                max4 = data[2][i];
//            }
//
//        }
//        //find min baris 4
//        min3 = data[3][0];
//        for (int i = 0; i < baris; i++) {
//            if (data[3][i] < min3) {
//                min4 = data[2][i];
//            }
//
//        }

         //normalisasi baris 1
        for (int i = 0; i < baris; i++) {
           // data[0][i] = (2 * data[0][i] - (max1 + min1)) / (max1 + min1);
            data[0][i]=(((data[0][i]-min1)/(max1-min1))*(0.9-0.1)+0.1);
        }
        //normalisasi baris 2
        for (int i = 0; i < baris; i++) {
            //data[1][i] = (2 * data[1][i] - (max2 + min2)) / (max2 + min2);
            data[1][i]=(((data[1][i]-min1)/(max1-min1))*(0.9-0.1)+0.1);
        }
        //normalisasi baris 3
        for (int i = 0; i < baris; i++) {
            //data[2][i] = (2 * data[2][i] - (max3 + min3)) / (max3 + min3);
            data[2][i]=(((data[2][i]-min1)/(max1-min1))*(0.9-0.1)+0.1);
        }
        
        //normalisasi baris 4
        for (int i = 0; i < baris; i++) {
            //data[3][i] = (3 * data[3][i] - ( max4 + min4)) / (max4 + min4);
            data[3][i]=(((data[3][i]-min1)/(max1-min1))*(0.9-0.1)+0.1);
        }
        System.out.println("end normalisasi");
        return data;

    }

    public void run(){
        System.out.println("max1 "+max1);
        System.out.println("min1 "+min1);
//        System.out.println("max3 "+max3);
//        System.out.println("max4 "+max4);
//        System.out.println("min1 "+min1);
//        System.out.println("min2 "+min2);
//        System.out.println("min3 "+min3);
//        System.out.println("min4 "+min4);
    }
    
}
