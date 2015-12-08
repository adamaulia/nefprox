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

    private double error, target, output;
    private double[] bobot;
    private Rule[] rules;
    private FungsiKeanggotaan fk;
    private double deltaA1, deltaB1, deltaC1, deltaA2, deltaB2, deltaC2, deltaA3, deltaB3, deltaC3;

    public VarOutput() {
    }

    public VarOutput(double target, double output, double[] bobot) {
        this.bobot = bobot;
        this.target = target;
        this.output = output;
        this.error = target - output;
    }

    public void setFK(FungsiKeanggotaan f) {
        this.fk = f;
    }

    public void setRulesIn(Rule[] x) {
        this.rules = x;
    }

    public void countDelta() {
        for (int i = 0; i < rules.length; i++) {
            for (int j = 0; j < bobot.length; j++) {
                if (rules[i].getValueKonsekuen() > 0) {
                    if (bobot[j] > 0) {
                        deltaB1 = MainClass.LR * error * (fk.getBatas(2) - fk.getBatas(0)) * rules[i].getValueKonsekuen() * (1 - bobot[j]);
                        deltaA1 = MainClass.LR * (fk.getBatas(2) - fk.getBatas(0)) * rules[i].getValueKonsekuen() + deltaB1;
                        deltaC1 = -MainClass.LR * (fk.getBatas(2) - fk.getBatas(0)) * rules[i].getValueKonsekuen() + deltaB1;
                        deltaB2 = MainClass.LR * error * (fk.getBatas(4) - fk.getBatas(1)) * rules[i].getValueKonsekuen() * (1 - bobot[j]);
                        deltaA2 = MainClass.LR * (fk.getBatas(4) - fk.getBatas(1)) * rules[i].getValueKonsekuen() + deltaB2;
                        deltaC2 = -MainClass.LR * (fk.getBatas(4) - fk.getBatas(1)) * rules[i].getValueKonsekuen() + deltaB2;
                        deltaB3 = MainClass.LR * error * (fk.getBatas(5) - fk.getBatas(3)) * rules[i].getValueKonsekuen() * (1 - bobot[j]);
                        deltaA3 = MainClass.LR * (fk.getBatas(5) - fk.getBatas(3)) * rules[i].getValueKonsekuen() + deltaB3;
                        deltaC3 = -MainClass.LR * (fk.getBatas(5) - fk.getBatas(3)) * rules[i].getValueKonsekuen() + deltaB3;
                    } else if (bobot[j] == 0){
                        deltaB1 = MainClass.LR * error * (fk.getBatas(2) - fk.getBatas(0)) * rules[i].getValueKonsekuen() * (1 - bobot[j]);
                        deltaA1 = Math.signum(target - fk.getBatas(0)) * MainClass.LR * (fk.getBatas(2) - fk.getBatas(0)) * rules[i].getValueKonsekuen() + deltaB1;
                        deltaC1 = Math.signum(target - fk.getBatas(0)) * -MainClass.LR * (fk.getBatas(2) - fk.getBatas(0)) * rules[i].getValueKonsekuen() + deltaB1;
                        deltaB2 = MainClass.LR * error * (fk.getBatas(4) - fk.getBatas(1)) * rules[i].getValueKonsekuen() * (1 - bobot[j]);
                        deltaA2 = Math.signum(target - ((fk.getBatas(1) + fk.getBatas(4)) / 2)) * MainClass.LR * (fk.getBatas(4) - fk.getBatas(1)) * rules[i].getValueKonsekuen() + deltaB2;
                        deltaC2 = Math.signum(target - ((fk.getBatas(1) + fk.getBatas(4)) / 2)) * -MainClass.LR * (fk.getBatas(4) - fk.getBatas(1)) * rules[i].getValueKonsekuen() + deltaB2;
                        deltaB3 = MainClass.LR * error * (fk.getBatas(5) - fk.getBatas(3)) * rules[i].getValueKonsekuen() * (1 - bobot[j]);
                        deltaA3 = Math.signum(target - fk.getBatas(5)) * MainClass.LR * (fk.getBatas(5) - fk.getBatas(3)) * rules[i].getValueKonsekuen() + deltaB3;
                        deltaC3 = Math.signum(target - fk.getBatas(5)) * -MainClass.LR * (fk.getBatas(5) - fk.getBatas(3)) * rules[i].getValueKonsekuen() + deltaB3;
                    }
                }
            }
        }

    }
}
