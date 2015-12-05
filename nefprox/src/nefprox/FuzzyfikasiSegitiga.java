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
   double batasAtas = 1431;  //0.8 // 0.7
   double batasBawah = 732; //0.5 // 0.4
   double [] batasTengah = {267,965}; //0.3,0.6  //  0.2,0.5

   
   public double[] dofuzy(double value){
   
   double [] segitiga = new double[3];
   segitiga[0]=0;
   segitiga[1]=0;
   segitiga[2]=0;
   double tengah = ((batasTengah[0]+batasTengah[1])/2) ;
    //buat rendah daerah 1, value<732
        if(value < batasTengah[0]){
            segitiga[0]=-(value-batasBawah)/(batasBawah-34.7);
            segitiga[1]=0;
            segitiga[2]=0;
            
        }else 
//     buat sedang / tengah daerah 2 , 267<value<849
        if((value > batasTengah[0]) &&(value <=tengah ) ){
           segitiga[0]= -(value-batasBawah)/(batasBawah-34.7);
           segitiga[1]=(value-batasTengah[0])/(tengah-batasTengah[0]);
           segitiga[2]=0;
            
        }else
//      buat tinggi atau sedang daerah 3 , 849<value<1431
        if((value >tengah)&&(value <= batasTengah[1])) {
           segitiga[0]=0;
           segitiga[1]=-(value-batasAtas)/(batasAtas-tengah);
           segitiga[2]=(-(value-batasAtas)/(1896-batasTengah[1]));
            
        }else
//      buat tinggi  daerah 4, value>1431
        if(value > batasTengah[1]){
          segitiga[0]=0;
          segitiga[1]=0;
          segitiga[2]=((value-batasAtas)/(1896-batasTengah[1]));
            
        }

    return segitiga;
   }
    
}
