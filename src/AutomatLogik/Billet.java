/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AutomatLogik;

/**
 *
 * @author Hold 10
 */
public class Billet {

    private int billetpris;
    private String billettype;
    private int rabatpris;

    public Billet(String type, int pris, int rabat) {
        billettype = type;
        billetpris = pris;
        rabatpris = rabat;
    }

    public int GetBilletpris() {
        return billetpris;
    }

    public String GetBillettype() {
        return billettype;
    }

    public int GetRabat() {
        return rabatpris;
    }

    public void setBilletPris(int nyPris) {
        billetpris = nyPris;
    }

    public void setBilletPris(int nyPris, int nyRabat) {
        billetpris = nyPris;
        rabatpris = nyRabat;
    }
}
