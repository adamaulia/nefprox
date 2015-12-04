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
public class FuzzyfikasiSegitiga {
   double batasAtas = 1556;  //0.8
   double batasBawah = 965; //0.5
   double [] batasTengah = {500,1198}; //0.3,0.6

   
   public double[] dofuzy(double value){
   
   double [] segitiga = new double[3];
   segitiga[0]=0;
   segitiga[1]=0;
   segitiga[2]=0;
   
    //buat rendah
        if(value < batasTengah[0]){
            segitiga[0]=-(value-batasBawah)/(batasBawah-0);
            segitiga[1]=0;
            segitiga[2]=0;
        }else 
//     buat sedang     
        if((value > batasTengah[0]) &&(value < batasBawah) ){
           segitiga[0]= -(value-batasBawah)/(batasBawah-0);
           segitiga[1]=(value-batasTengah[0]/((batasTengah[1]+batasTengah[0])/2)-batasTengah[0]);
           segitiga[2]=0;
        }else
//      buat tinggi 
        if((value >(batasTengah[1]+batasTengah[0])/2)&&(value < batasAtas)) {
           segitiga[0]=0;
           segitiga[1]=-(value-batasTengah[1])/(batasAtas-((batasTengah[1]-batasTengah[0])/2));
           segitiga[2]=(value-batasAtas)/(1-batasAtas);
        }
   
    return segitiga;
   }
    
}
