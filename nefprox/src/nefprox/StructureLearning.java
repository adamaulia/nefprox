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
public class StructureLearning {
    Data d = new Data();
    FuzzyfikasiSegitiga f = new FuzzyfikasiSegitiga();
    String dir="src\\data\\BUNDESBANK-BBK01_WT5511.xls";
    int baris =6017;
    int kolom =4;
    String[][] rawdata = new String[kolom][baris];
    double[][] data = new double[kolom][baris];
    double[][] datafuzzyfikasi = new double[kolom*3][baris];
    
    public double[][] inisialisasi(){
        rawdata=d.getData(dir);
        data=d.dataDouble(rawdata);
        System.out.println("done inisialisasi");

        double [] temp1 = new double[3]; //nampung hasil fuzzy 1-3
        double [] temp2 = new double[3]; //nampung hasil fuzzy 4-6
        double [] temp3 = new double[3]; //nampung hasil fuzzy 7-9
        double [] temp4 = new double[3]; //nampung hasil fuzzy 10-12
        double [] tempdata = new double[4]; //nampung data per kolom
        
        for (int i = 0; i < data[1].length; i++) {
//             System.out.println("pindah ke tempdata");
//             System.out.println("data[1].length  i "+data[1].length);

            for (int j = 0; j < data.length; j++) {
                tempdata[j]=data[j][i];
            }
//            System.out.println("i "+i);
//                System.out.println(tempdata[0]);
//                System.out.println(tempdata[1]);
//                System.out.println(tempdata[2]);
//                System.out.println(tempdata[3]);
//                
                temp1=f.dofuzy(tempdata[0]);
                temp2=f.dofuzy(tempdata[1]);
                temp3=f.dofuzy(tempdata[2]);
                temp4=f.dofuzy(tempdata[3]);
                
                datafuzzyfikasi[0][i]=temp1[0];
                datafuzzyfikasi[1][i]=temp1[1];
                datafuzzyfikasi[2][i]=temp1[2];
                datafuzzyfikasi[3][i]=temp2[0];
                datafuzzyfikasi[4][i]=temp2[1];
                datafuzzyfikasi[5][i]=temp2[2];
                datafuzzyfikasi[6][i]=temp3[0];
                datafuzzyfikasi[7][i]=temp3[1];
                datafuzzyfikasi[8][i]=temp3[2];
                datafuzzyfikasi[9][i]=temp4[0];
                datafuzzyfikasi[10][i]=temp4[1];
                datafuzzyfikasi[11][i]=temp4[2];
        }
        System.out.println("done move tempdata");

        for (int i = 0; i < datafuzzyfikasi[1].length; i++) {
            for (int j = 0; j < datafuzzyfikasi.length; j++) {
                System.out.print(datafuzzyfikasi[j][i]+" , ");
            }
            System.out.println("");
        }

        return datafuzzyfikasi;
        

    }
    
}
