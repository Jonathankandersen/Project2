/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package automat;

/**
 *
 * @author saxok_000
 */
public class Billet {
    private int billetpris;
    private String billettype;
    
    public Billet(String type, int pris) {
        billettype = type;
        billetpris = pris;
    }
    
    public int GetBilletpris() {
        return billetpris;
    }
}
