/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Versi_2;

/**
 *
 * @author Afkar
 */
public class VarOutput {

    private double error;
    double[] target, output;
    private double[] bobot;
    private Rule[] rules;
    private FungsiKeanggotaan fk;
    private double[] deltaA1, deltaB1, deltaC1, deltaA2, deltaB2, deltaC2, deltaA3, deltaB3, deltaC3;

    public VarOutput() {
    }

    public VarOutput(double[] target, double[] output, double[] bobot) {
        this.bobot = bobot;
        this.target = target;
        this.output = output;
    }

    public void setFK(FungsiKeanggotaan f) {
        this.fk = f;
    }

    public void setRulesIn(Rule[] x) {
        this.rules = x;
    }

    public void countDelta() {
        deltaA1 = new double[rules.length];
        deltaB1 = new double[rules.length];
        deltaC1 = new double[rules.length];
        deltaA2 = new double[rules.length];
        deltaB2 = new double[rules.length];
        deltaC2 = new double[rules.length];
        deltaA3 = new double[rules.length];
        deltaB3 = new double[rules.length];
        deltaC3 = new double[rules.length];
        for (int i = 0; i < rules.length; i++) {
            error = target[i] - output[i];
//            System.out.println("Target "+target[i]);
//            System.out.println("Output "+output[i]);
//            System.out.println("Error : "+error);
            for (int j = 0; j < bobot.length; j++) {
                if (rules[i].getValueKonsekuen() > 0) {
                    if (bobot[j] > 0) {
                        deltaB1[i] = MainClass.LR * error * (fk.getBatas(2) - fk.getBatas(0)) * rules[i].getValueKonsekuen() * (1 - bobot[j]);
                        deltaA1[i] = MainClass.LR * (fk.getBatas(2) - fk.getBatas(0)) * rules[i].getValueKonsekuen() + deltaB1[i];
                        deltaC1[i] = -MainClass.LR * (fk.getBatas(2) - fk.getBatas(0)) * rules[i].getValueKonsekuen() + deltaB1[i];
                        deltaB2[i] = MainClass.LR * error * (fk.getBatas(4) - fk.getBatas(1)) * rules[i].getValueKonsekuen() * (1 - bobot[j]);
                        deltaA2[i] = MainClass.LR * (fk.getBatas(4) - fk.getBatas(1)) * rules[i].getValueKonsekuen() + deltaB2[i];
                        deltaC2[i] = -MainClass.LR * (fk.getBatas(4) - fk.getBatas(1)) * rules[i].getValueKonsekuen() + deltaB2[i];
                        deltaB3[i] = MainClass.LR * error * (fk.getBatas(5) - fk.getBatas(3)) * rules[i].getValueKonsekuen() * (1 - bobot[j]);
                        deltaA3[i] = MainClass.LR * (fk.getBatas(5) - fk.getBatas(3)) * rules[i].getValueKonsekuen() + deltaB3[i];
                        deltaC3[i] = -MainClass.LR * (fk.getBatas(5) - fk.getBatas(3)) * rules[i].getValueKonsekuen() + deltaB3[i];
                    } else if (bobot[j] == 0){
                        deltaB1[i] = MainClass.LR * error * (fk.getBatas(2) - fk.getBatas(0)) * rules[i].getValueKonsekuen() * (1 - bobot[j]);
                        deltaA1[i] = Math.signum(target[i] - fk.getBatas(0)) * MainClass.LR * (fk.getBatas(2) - fk.getBatas(0)) * rules[i].getValueKonsekuen() + deltaB1[i];
                        deltaC1[i] = Math.signum(target[i] - fk.getBatas(0)) * -MainClass.LR * (fk.getBatas(2) - fk.getBatas(0)) * rules[i].getValueKonsekuen() + deltaB1[i];
                        deltaB2[i] = MainClass.LR * error * (fk.getBatas(4) - fk.getBatas(1)) * rules[i].getValueKonsekuen() * (1 - bobot[j]);
                        deltaA2[i] = Math.signum(target[i] - ((fk.getBatas(1) + fk.getBatas(4)) / 2)) * MainClass.LR * (fk.getBatas(4) - fk.getBatas(1)) * rules[i].getValueKonsekuen() + deltaB2[i];
                        deltaC2[i] = Math.signum(target[i] - ((fk.getBatas(1) + fk.getBatas(4)) / 2)) * -MainClass.LR * (fk.getBatas(4) - fk.getBatas(1)) * rules[i].getValueKonsekuen() + deltaB2[i];
                        deltaB3[i] = MainClass.LR * error * (fk.getBatas(5) - fk.getBatas(3)) * rules[i].getValueKonsekuen() * (1 - bobot[j]);
                        deltaA3[i] = Math.signum(target[i] - fk.getBatas(5)) * MainClass.LR * (fk.getBatas(5) - fk.getBatas(3)) * rules[i].getValueKonsekuen() + deltaB3[i];
                        deltaC3[i] = Math.signum(target[i] - fk.getBatas(5)) * -MainClass.LR * (fk.getBatas(5) - fk.getBatas(3)) * rules[i].getValueKonsekuen() + deltaB3[i];
                    }
                }
            }
        }
        
    }
    
    public double[][] getGeser(){
        double[][] temp= new double[rules.length][9];
        for(int i=0; i<rules.length; i++){
            temp[i][0]=deltaA1[i];
            temp[i][1]=deltaB1[i];
            temp[i][2]=deltaC1[i];
            temp[i][3]=deltaA2[i];
            temp[i][4]=deltaB2[i];
            temp[i][5]=deltaC2[i];
            temp[i][6]=deltaA3[i];
            temp[i][7]=deltaB3[i];
            temp[i][8]=deltaC3[i];
        }
        return temp;
    }
        
    public void printError(){
        System.out.println("Error : "+error);
    }
    
}
