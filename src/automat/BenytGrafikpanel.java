/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package automat;

import javax.swing.JFrame;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.Date;
import java.util.Scanner;

/**
 *
 * @author Hold 10
 */
public class BenytGrafikpanel {

    public static void main(String[] arg) {
        GrafiskBilletautomat panel = new GrafiskBilletautomat();    // opret panelet

        JFrame vindue = new JFrame("Grafikpanel");  // opret et vindue på skærmen
        vindue.add(panel);                          // vis panelet i vinduet

        vindue.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);   // reagér på luk
        vindue.setSize(350, 70);    // sæt vinduets størrelse
        vindue.setVisible(true);    // åbn vinduet
    }
}
