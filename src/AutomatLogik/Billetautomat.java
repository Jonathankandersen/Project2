package AutomatLogik;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;
import java.util.Date;
import java.util.ArrayList;

/**
 *
 * @author Hold 10
 */
public class Billetautomat {

    private int billetpris;     // Prisen for en billet.
    private int balance;        // Hvor mange penge kunden p.t. har puttet i automaten
    private int antalBilletterSolgt; // Antal billetter automaten i alt har solgt
    private int medlemspris;        // Prisen for en billet til medlemmer
    private boolean montørtilstand;
    private boolean medlemstilstand;
    private int medlemsType;
    private int Date;

    ArrayList<Medlemmer> Medlem = new ArrayList();
    ArrayList<Billet> Billetter = new ArrayList();
    ArrayList<String> Logbog = new ArrayList();
    
    String newLine = System.getProperty("line.separator");
    

    Scanner tastatur = new Scanner(System.in);

    /**
     * Opret en billetautomat med fire typer af billetter med hver sin pris
     */
    public Billetautomat() {
        balance = 0;
        billetpris = 20;
        antalBilletterSolgt = 0;
        Billet voksenbillet = new Billet("voksen", 20, 15);
        Billetter.add(voksenbillet);
        Billet barnebillet = new Billet("barn", 15, 10);
        Billetter.add(barnebillet);
        Billet cykelbillet = new Billet("cykel", 25, 20);
        Billetter.add(cykelbillet);
        Billet pensionist = new Billet("pensionist", 15, 10);
        Billetter.add(pensionist);
    }

    /**
     * Giver prisen for en billet.
     *
     * @return
     */
    public String getBilletpriser() {
        String priser = "";
        for (int i = 0; i < Billetter.size(); i++) {
            int k = Billetter.get(i).GetBilletpris();
            String t = Billetter.get(i).GetBillettype();
            int r = Billetter.get(i).GetRabat();
            System.out.println("En " + t + " koster " + k + " Kr. Eller " + r + " Kr. hvis du er medlem.");

            priser = priser.concat("" + k);
        }
        return priser;
    }

    /**
     * Modtag nogle penge (i kroner) fra en kunde.
     *
     * @param beløb
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
       Logbog.add("Den "+dato+ "blev der indsat: "+beløb+newLine);
    }

    /**
     * Giver balancen (beløbet maskinen har modtaget til den næste billet).
     *
     * @return
     */
    public int getBalance() {
        return balance;
    }

    /**
     * Udskriv en billet. Opdater total og nedskriv balancen med billetprisen
     */
    public void udskrivBillet() {
        if (balance < billetpris) {
            System.out.println("Du mangler at indbetale nogle penge");
        } else {
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
            balance = balance - billetpris;
        }
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

    public void setBilletpris() {
        for (int i = 0; i < Billetter.size(); i++) {
            System.out.println("Angiv ny pris for billet: " + Billetter.get(i).GetBillettype() + " der har den nuværende pris: " + Billetter.get(i).GetBilletpris());
            int nyPris = tastatur.nextInt();
            System.out.println("Skal rabatprisen også sænkes? ");
            boolean prisValg = false;
            int nyRabat;
            if (prisValg == true) {
                nyRabat = tastatur.nextInt();
            } else {
                nyRabat = Billetter.get(i).GetRabat();
            }
            Billetter.get(i).setBilletPris(nyPris, nyRabat);
        }
    }

    public void nulstil() {
        if (montørtilstand) {
            antalBilletterSolgt = 0;
        } else {
            System.out.println("Afvist - log ind som montør først");
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
        String memberName = tastatur.nextLine();
        System.out.println("Indtast din kode: ");
        int memberCode = tastatur.nextInt();
        Medlemmer Member = new Medlemmer(memberName, memberCode);
        Medlem.add(Member);
    }

    public boolean erMedlem() {
        return medlemstilstand;
    }

    public void medlemLogin(int medlemsKode) {
        for (int i = 0; i < Medlem.size(); i++) {
            if (Medlem.get(i).medlemsKode == medlemsKode) {
                medlemstilstand = true;
                System.out.println("Du er logget ind som medlem! Hej " + Medlem.get(i).medlemsNavn);
                break;
            } else {
                medlemstilstand = false;
                System.out.println("Du er ikke medlem!");
            }
        }
    }
    Date dato = new Date();

    public void getLog() {  // Lavet en ny log metode
        Logbog.add(newLine+ "Dato: " + dato + "Antal solgte billetter: " + getAntalBilletterSolgt() + "Total omsætning: " + getTotal());
        System.out.println("Logbog: " + Logbog);
    }

    public void writerLog()
            throws IOException {
        FileWriter fileWriter = new FileWriter("fil.txt");
        PrintWriter printWriter = new PrintWriter(fileWriter);
        printWriter.println("Log: " + Logbog);
        printWriter.close();

    }

    public void rabatBillet() {
        if (medlemstilstand) {
            
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
        f.forbind("ftp.dlptest.com", "dlpuser@dlptest.com", "eiTqR7EMZD5zy7M"); //https://dlptest.com/ftp-test/ er brugt til at teste FTP. Filen gemmes på serveren i 5 minutter.

        String indhold = ("Log: " + Logbog);
        f.sendTekst("STOR log.txt", indhold);

        indhold = f.modtagTekst("RETR fil.txt");
        System.out.println("Fil hentet med indholdet: " + indhold);
    }
}
