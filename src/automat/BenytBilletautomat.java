package automat;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.Date;
import java.util.Scanner;

public class BenytBilletautomat {

    public static void main(String[] arg) {
        Billetautomat automat = new Billetautomat();
        Scanner tastatur = new Scanner(System.in);  // forbered

        System.out.println("BenytBilletautomat Project2");
        System.out.println();

        // Første udskrift mangler en struktur til at printe info (priser) fra array.
        while (true) {
            System.out.println("-----------------------------------------------");
            System.out.println("En billet koster " + automat.getBilletpriser() + " kroner");
            System.out.println("Balancen er på " + automat.getBalance() + " kroner");
            System.out.println();
            System.out.println("Tast 1 for at indbetale penge");
            System.out.println("Tast 2 for at udskrive din billet");
            System.out.println("Tast 3 for at få returpengene");
            System.out.println("Tast 4 for at logge ind som medlem");
            System.out.println("Tast 5 for at oprette medlemsskab");
            if (automat.erMedlem()) {
                System.out.println("Tast 6 for at købe rabatbillet");
                System.out.println();
                System.out.println("Tast 20 for at logge ud som medlem");
            }
            System.out.println();
            System.out.println("Tast 10 for at logge ind som montør");
            if (automat.erMontør()) {
                System.out.println("Tast 11 for at se status (montør)");
                System.out.println("Tast 12 for at nulstille (montør)");
                System.out.println("Tast 13 for at sætte billetpris (montør)");
                System.out.println("Tast 14 for at logge ud af montørtilstand");
                System.out.println("Tast 15 for at vise logs");
                System.out.println("Tast 16 for at printe log til fil");
            }
            int valg = tastatur.nextInt();
            tastatur.nextLine();

            switch (valg) {
                case 1: {
                    System.out.print("Skriv beløb: ");
                    int beløb = tastatur.nextInt();
                    automat.indsætPenge(beløb);
                    break;
                }
                case 2: {
                    automat.udskrivBillet();
                    break;
                }
                case 3: {
                    int beløb = automat.returpenge();
                    break;
                }
                  case 4: {
                    System.out.print("Skriv medlemskode: ");
                    int medlemsKode = tastatur.nextInt();
                    automat.medlemLogin(medlemsKode);

                    break;
                }
                case 5: {
                    automat.tilføjMedlem();
                    break;
                }
                case 6: {
                    automat.rabatBillet();
                    break;
                }
                case 10: {
                    System.out.print("Skriv kode: ");
                    String kode = tastatur.next();
                    automat.montørLogin(kode);
                    break;

                }
                case 11: {
                    System.out.println("Antal billetter solgt: " + automat.getAntalBilletterSolgt());
                    System.out.println("Total indkomst: " + automat.getTotal() + " kr");
                    break;
                }
                case 12: {
                    automat.nulstil();
                    break;
                }
                case 13: {
                    System.out.print("Skriv beløb: ");
                    int beløb = tastatur.nextInt();
                    automat.setBilletpris(beløb);
                    break;
                }
                case 14: {
                    automat.montørLogin("");
                    break;
                }
                 case 15: {

                    automat.getLog();

                    break;
                }
                case 16: {
                    try {
                        automat.writerLog();
                    } catch (IOException ex) {
                        Logger.getLogger(BenytBilletautomat.class.getName()).log(Level.SEVERE, null, ex);
                        System.out.println("Log printet til fil");
                    }
                    break;

                }
                default: {
                    System.out.println("Ugyldigt valg, prøv igen");
                    break;

                }
                case 20: {
                   // automat.medlemLogin("");
                    automat.setBilletpris(10);
                    break;
                }

            }
        }
    }
}
