/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nefprox;

import java.util.ArrayList;

/**
 *
 * @author mickeyMice
 */
public class FungsiKeanggotaan {
    ArrayList poin = new ArrayList();
    
    public FungsiKeanggotaan(){
        poin.add(0.0);
        poin.add(0.0);
        poin.add(0.0);
        poin.add(0.0);
    }
    
    public int getPointNum(){
        return poin.size();
    }
    
    public void addPoin()
}
