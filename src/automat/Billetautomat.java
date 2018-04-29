package automat;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.util.Scanner;
import java.util.Date;
import java.util.ArrayList;

/**
 *
 * @author Hold 10
 */
public class Billetautomat {

    private int billetpris;    // Prisen for én billet.
    private int balance; // Hvor mange penge kunden p.t. har puttet i automaten
    private int antalBilletterSolgt; // Antal billetter automaten i alt har solgt
    private int medlemspris;        // Prisen for én billet til medlemmer
    private boolean montørtilstand;
    private boolean medlemstilstand;
    private int medlemsType;
    private int Date;

    Scanner tastatur = new Scanner(System.in);

    /**
     * Opret en billetautomat der sælger billetter til 10 kr.
     */
    public Billetautomat() {
        billetpris = 10;
        balance = 0;
        antalBilletterSolgt = 0;
    }

    ArrayList<String> Medlemsnavn = new ArrayList();
    ArrayList<String> Billetter = new ArrayList();

    /**
     * Giver prisen for en billet.
     */
    public int getBilletpris() {
        int resultat = billetpris;
        return resultat;
    }

    /**
     * Modtag nogle penge (i kroner) fra en kunde.
     */
    public void indsætPenge(int beløb) {
        if (beløb > 0 && beløb <= 1000) { //Første Krav Ændring med Boolan Udtryk 
            if (balance < 1000) { // Første krav sikre at der ikke kan indsættes mere end 1000 total
                balance = balance + beløb;
            } else {
                System.err.println("Der kan max indsættes 1000 kr "); // Første krav vis over 1000 prøves at indsættes 
            }
        } else if (balance > 1000) {
            System.err.println("Max balance er 1000 kroner");
        } else if (beløb < 0) { // ændring 
            System.err.println("Hvis du ønsker penge retur tast '3'");
        } else if (beløb > 1000) { // Første Krav MAX 1000 KR kan indsættes 
            System.err.println("Det maximale beløb der kan indsættes er 1000 KR");
        } else {
            System.err.println("Forkert indtastning");
        }
    }

    /**
     * Giver balancen (beløbet maskinen har modtaget til den næste billet).
     */
    public int getBalance() {
        return balance;
    }

    /**
     * Udskriv en billet. Opdater total og nedskriv balancen med billetprisen
     */
    public void udskrivBillet() {
        if (balance < 10) {
            System.out.println("Du mangler at indbetale nogle penge");
        }
        System.out.println("##########B##T#########");
        System.out.println("# BlueJ Trafikselskab #");
        System.out.println("#                     #");
        System.out.println("#        Billet       #");
        System.out.println("#        " + billetpris + " kr.       #");
        System.out.println("#                     #");
        System.out.println("##########B##T#########");
        System.out.println("# Du har " + (balance - billetpris) + " kr til gode       #");
        System.out.println("##########B##T#########");
        System.out.println();

        antalBilletterSolgt = antalBilletterSolgt + 1;
        balance = balance - billetpris; // Billetter koster 10 kroner
    }

    public int returpenge() {
        int returbeløb = balance;
        balance = 0;
        System.out.println("Du får " + returbeløb + " kr retur");
        return returbeløb;
    }

    void montørLogin(String adgangskode) {
        if ("1234".equals(adgangskode)) {
            montørtilstand = true;
            System.out.println("Montørtilstand aktiveret");
            System.out.println("Du kan nu angive billetpris");
        } else {
            montørtilstand = false;
            System.out.println("Montørtilstand deaktiveret");
        }
    }

    public int getTotal() {
        if (montørtilstand) {
            return billetpris * antalBilletterSolgt;
        } else {
            System.out.println("Afvist - log ind først");
            return 0;
        }
    }

    public int getAntalBilletterSolgt() {
        if (montørtilstand) {
            return antalBilletterSolgt;
        } else {
            System.out.println("Afvist - log ind først");
            return 0;
        }
    }

    public void setBilletpris(int billetpris) {
        this.billetpris = billetpris;
    }

    public void nulstil() {
        if (montørtilstand) {
            antalBilletterSolgt = 0;
        } else {
            System.out.println("Afvist - log ind først");
        }
    }

    public void setAntalBilletterSolgt(int antalBilletterSolgt) {
        if (montørtilstand) {
            this.antalBilletterSolgt = antalBilletterSolgt;
        } else {
            System.out.println("Afvist - log ind først");
        }
    }

    public void tilføjMedlem() {

        System.out.println("Indtast dit fornavn: ");
        Medlemsnavn.add(tastatur.nextLine());
        System.out.println("Dit navn: " + Medlemsnavn);
        System.out.println("Indtast din kode: ");
        MedlemsKode.add(tastatur.nextLine());
        System.out.println("Din kode er: " + MedlemsKode);

    }

    public boolean erMedlem() {
        return medlemstilstand;
    }

    public void medlemLogin(String medlemsKode) {
        if (.contains(medlemsKode)
        
            ) {
            medlemstilstand = true;
            System.out.println("Du er logget ind som medlem!");
            int index = MedlemsKode.indexOf(medlemsKode);
        }else {
            medlemstilstand = false;

            System.out.println("Ikke medlem!");
        }
    }
    public Date dato = new Date();

    public void getLog() { // Lavet en ny log metode
        System.out.println("Log: ");
        System.out.println("Dato: " + dato);
        System.out.println("Antal solgte billetter: " + getAntalBilletterSolgt());
        System.out.println("Total omsætning: " + getTotal());
    }

    public void writerLog()
            throws IOException {
        FileWriter fileWriter = new FileWriter("fil.txt");
        PrintWriter printWriter = new PrintWriter(fileWriter);
        printWriter.println("Antal solgte billetter: " + getAntalBilletterSolgt() + " Styk");
        printWriter.println("Total: " + getTotal() + "Kroner");
        printWriter.close();

    }

    public void rabatBillet() {
        if (medlemstilstand) {
            switch (medlemsType) {
                case 1:
                    billetpris = 5;
                    break;
                case 2:
                    billetpris = 10;
                    break;
                case 3:
                    billetpris = 15;
                    break;
                case 4:
                    billetpris = 20;
                    break;
            }
            this.billetpris = medlemspris;
            System.out.println("Din billetpris " + medlemspris);
        } else {
            System.out.println("Du betaler normalpris");
        }
    }

    public boolean erMontør() {
        return montørtilstand;
    }

    public void FTP(String[] a) throws Exception {
        FtpForbindelse f = new FtpForbindelse();
        // bemærk - vær altid MEGET FORSIGTIG med at angive adgangskoder i en fil!!
        f.forbind("192.168.0.105", "TESTER", "Hvadlaverjeg?");

        String indhold = "Indhold af en lille fil med navnet:\nfil.txt\n";
        f.sendTekst("STOR log.txt", indhold);

        indhold = f.modtagTekst("RETR fil.txt");
        System.out.println("Fil hentet med indholdet: " + indhold);
    }
}
