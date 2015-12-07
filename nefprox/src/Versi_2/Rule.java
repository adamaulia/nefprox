/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Versi_2;

import java.util.Arrays;

/**
 *
 * @author mickeyMice
 */
public class Rule {
    String[] x1= new String[2];
    String[] x2= new String[2];
    String[] x3= new String[2];
    String[] target= new String[2];
    
    public Rule(){}
    
    public Rule(double[] input1,double[] input2,double[] input3,double[] input4){
        x1 = Choose(input1);
        x2 = Choose(input2);
        x3 = Choose(input3);
        //target = Choose(input4);
       target=konsekuen(x1,x2,x3);
    }
    
    public void setRule(double[] input1,double[] input2,double[] input3,double[] input4){
        x1 = Choose(input1);
        x2 = Choose(input2);
        x3 = Choose(input3);
        //target = Choose(input4);
        target=konsekuen(x1,x2,x3);
    }
    
    public String[] Choose(double[] input){
        double max = input[0];
        int idx=0;
        String temp[] = new String[2];
        for(int i=0; i<input.length; i++){
            if (input[i]>max){
                max=input[i];
                idx=i;
            }
        }
        if(idx==0){
            temp[0]="Rendah";
        }else if(idx==1){
            temp[0]="Sedang";
        }else{
            temp[0]="Tinggi";
        }
        
        temp[1]=String.valueOf(max);
        return temp;
        
    }
    
    public String[] konsekuen(String[] X1, String[] X2,String[] X3){
        double d1,d2,d3;
        double [] tempdouble = new double[3];
        String [] output = new String [2];
        
        d1=Double.valueOf(X1[1]);
        d2=Double.valueOf(X2[1]);
        d3=Double.valueOf(X3[1]);
        
        tempdouble[0]=d1;
        tempdouble[1]=d2;
        tempdouble[2]=d3;
        
        Arrays.sort(tempdouble);
        output[0]=tempdouble[0]+"";
        
        if(tempdouble[0]<0.3){
            output[1]="Rendah";
        }else if((tempdouble[0]>0.3)&&(tempdouble[0]<=0.6)){
            output[1]="Sedang";
        }else if(tempdouble[0]>0.6){
            output[1]="Sedang";
        }
        
        
        return output;
    }
    
    public String[][] getRule(){
        String temp[][] = new String[4][2];
        temp[0]=x1;
        temp[1]=x2;
        temp[2]=x3;
        temp[3]=target;
        
        return temp;
    }
}
