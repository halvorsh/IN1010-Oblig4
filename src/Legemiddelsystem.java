import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import static java.lang.Double.parseDouble;
import static java.lang.Integer.parseInt;

public class Legemiddelsystem {
    private static Lenkeliste<Pasient> pasienter;
    private static Lenkeliste<Legemiddel> legemidler;
    private static SortertLenkeliste<Lege> leger;
    private static Lenkeliste<Resept> resepter;
    private static boolean programmetkjorer = true;
    private static Scanner in;

    public static void main(String args[]){
        pasienter = new Lenkeliste <>();
        legemidler = new Lenkeliste <>();
        leger = new SortertLenkeliste <>();
        resepter = new Lenkeliste <>();

        in = new Scanner(System.in);

        lesInnFraFil("innfil.txt");

        while(programmetkjorer){
            System.out.println("\nHva vil du gjore? \n" +
                    "0: Se all data\n" +
                    "1: Legge til et nytt element\n" +
                    "2: Bruke en resept\n" +
                    "3: Se statistikk\n" +
                    "4: Lagre data\n" +
                    "5: Gaa ut av programmet");

            String svar = in.nextLine();

            switch(svar){
                case "0":
                    skrivUtInformasjon();
                    break;
                case "1":
                    leggTilElementMeny();
                    break;
                case "2":
                    brukResept();
                    break;
                case "3":
                    statistikk();
                    break;
                case "4":
                    skrivTilFil();
                    break;
                case "5":
                    programmetkjorer = false;
                    break;
                default:
                    System.out.println("Ugyldig input, skriv inn tallet til alternativet du onsker");
                    break;
            }
        }
    }

    private static void skrivUtInformasjon(){
        System.out.println("Pasienter (navn, fnr)");
        for(Pasient pasient : pasienter){
            System.out.println(pasient.hentInfo());
        }

        System.out.println("Legemidler (navn, type, pris, virkestoff [, styrke])");
        for(Legemiddel legemiddel : legemidler){
            System.out.println(legemiddel.hentInfo());
        }

        System.out.println("Leger (navn, avtalenr / 0 hvis ingen avtale)");
        for(Lege lege : leger){
            System.out.println(lege.hentInfo());
        }

        System.out.println("Resepter (type, legemiddelNummer, legeNavn, persID, [reit])");
        for(Resept resept : resepter){
            System.out.println(resept.hentInfo());
        }
    }

    private static void lesInnFraFil(String filnavn){
        File fil = new File(filnavn);
        Scanner scanner = null;
        try{
            scanner = new Scanner(fil);
        }catch(FileNotFoundException e){
            e.printStackTrace();
        }

        int fase = 0;

        while(scanner.hasNextLine()){
            String nesteLinje = scanner.nextLine();
            if(nesteLinje.contains("#")){
                fase++;
            }else {
                String[] biter = nesteLinje.split(", ");

                switch(fase){
                    case 1:
                        leggTilPasient(biter[0], biter[1]);
                        break;
                    case 2:
                        int styrke = 0;
                        if(biter.length > 4){
                            styrke = parseInt(biter[4]);
                        }

                        leggTilLegemiddel(biter[0], biter[1], parseDouble(biter[2]), parseDouble(biter[3]), styrke);
                        break;
                    case 3:
                        leggTilLege(biter[0], parseInt(biter[1]));
                        break;
                    case 4:
                        int reit = 3;
                        if(biter.length > 4){
                            reit = parseInt(biter[4]);
                        }

                        leggTilResept(biter[0], parseInt(biter[1]), biter[2], parseInt(biter[3]), reit);
                        break;
                    default:
                        System.out.println("Feil med fase");
                        break;
                }
            }
        }
    }

    public static void skrivTilFil(){

    }

    private static void leggTilElementMeny(){
        System.out.println("\nHva slags element skal legges til?\n" +
                "0: Pasient\n" +
                "1: Legemiddel\n" +
                "2: Lege\n" +
                "3: Resept");

        String svar = in.nextLine();

        switch(svar){
            case "0":
                System.out.println("Skriv inn pasientens navn:");
                String pasientNavn = in.nextLine();
                System.out.println("Skriv inn pasientens fodselsnummer:");
                String fodselsnummer = in.nextLine();

                leggTilPasient(pasientNavn, fodselsnummer);

                System.out.println("Pasienten " + pasientNavn + " er lagt til.");
                break;
            case "1":
                System.out.println("Skriv inn legemiddelets navn:");
                String legemiddelNavn = in.nextLine();
                System.out.println("Skriv inn legemiddelets type (a = narkotisk, b = vanedannende, c = normalt):");
                String type = in.nextLine();
                System.out.println("Skriv inn legemiddelets pris:");
                float pris = in.nextFloat();
                System.out.println("Skriv in mengen virkestoff i mg:");
                float virkestoff = in.nextFloat();

                int styrke = 0;

                if(type == "a"){
                    System.out.println("Skriv inn hvor sterkt narktosik legemiddelet er:");
                    styrke = parseInt(in.nextLine());
                }else if(type == "b"){
                    System.out.println("Skriv inn hvor vanedannende legemiddelet er:");
                    styrke = parseInt(in.nextLine());
                }

                leggTilLegemiddel(legemiddelNavn, type, pris, virkestoff, styrke);

                System.out.println("Legemiddelet " + legemiddelNavn + " er lagt til.");
                break;
            case "2":
                System.out.println("Skriv inn legens navn:");
                String legeNavn = in.nextLine();
                System.out.println("Skriv inn legens avtalenummer (0 hvis ingen avtale):");
                int avtaleNummer = parseInt(in.nextLine());

                leggTilLege(legeNavn, avtaleNummer);

                System.out.println("Legen " + legeNavn + " er lagt til.");
                break;
            case "3":
                System.out.println("Skriv inn resepttype (blaa, hvit, militaer, prevensjon):");
                String reseptType = in.nextLine();

                System.out.println("Skriv inn IDen til reseptlegemiddelet:");
                for(Legemiddel legemiddel : legemidler){
                    System.out.println(legemiddel.hentID() + ": " + legemiddel.hentNavn());
                }
                int legemiddelNummer = parseInt(in.nextLine());

                System.out.println("Skriv inn navnet på legen:");
                for(Lege lege : leger){
                    System.out.println(lege.hentNavn());
                }
                String reseptLegeNavn = in.nextLine();

                System.out.println("Skriv inn pasientens ID:");
                for(Pasient pasient : pasienter){
                    System.out.println(pasient.hentID() + ": " + pasient.hentNavn());
                }
                int persID = parseInt(in.nextLine());

                int reit;

                if(reseptType != "prevensjon"){
                    System.out.println("Skriv inn antall reit:");
                    reit = parseInt(in.nextLine());
                }else{
                    reit = 3;
                }

                leggTilResept(reseptType, legemiddelNummer, reseptLegeNavn, persID, reit);

                System.out.println("Resept av type " + reseptType + " er lagt til.");
                break;
            default:
                System.out.println("Ugyldig input, går til hovedmenyen.");
                break;
        }
    }

    private static void leggTilPasient(String navn, String fodselsnummer){
        Pasient pasient = new Pasient(navn, fodselsnummer);

        pasienter.leggTil(pasient);
    }

    private static void leggTilLegemiddel(String navn, String type, double pris, double virkestoff, int styrke){
        Legemiddel legemiddel = null;

        switch(type){
            case "a":
                legemiddel = new LegemiddelA(navn, pris, virkestoff, styrke);
                break;
            case "b":
                legemiddel = new LegemiddelB(navn, pris, virkestoff, styrke);
                break;
            case "c":
                legemiddel = new LegemiddelC(navn, pris, virkestoff);
                break;
            default:
                System.out.println("Ikke gyldig legemiddeltype");
                break;
        }

        if(legemiddel != null){
            legemidler.leggTil(legemiddel);
        }
    }

    private static void leggTilLege(String navn, int avtalenummer){
        Lege lege;

        if(avtalenummer == 0){
            lege = new Lege(navn);
        }else{
            lege = new FastLege(navn, avtalenummer);
        }

        leger.leggTil(lege);
    }

    private static void leggTilResept(String type, int legemiddelNummer, String legeNavn, int persID, int reit){
        Resept resept = null;
        Legemiddel reseptLegemiddel = null;
        Lege reseptLege = null;
        Pasient reseptPasient = null;

        for(Legemiddel legemiddel : legemidler){
            if(legemiddelNummer == legemiddel.hentID()){
                reseptLegemiddel = legemiddel;
            }
        }

        for(Lege lege : leger){
            if(legeNavn.compareTo(lege.hentNavn()) == 0){
                reseptLege = lege;
            }
        }

        for(Pasient pasient : pasienter){
            if(pasient.hentID() == persID){
                reseptPasient = pasient;
            }
        }

        if(reseptLegemiddel == null || reseptLege == null || reseptPasient == null){
            System.out.println("Feil med data ved reseptOppretting");
        }else {
            switch (type) {
                case "blaa":
                    resept = new BlaaResept(reseptLegemiddel, reseptLege, reseptPasient, reit);
                    break;
                case "hvit":
                    resept = new HvitResept(reseptLegemiddel, reseptLege, reseptPasient, reit);
                    break;
                case "prevensjon":
                    if(reit >= 3){
                        resept = new PResept(reseptLegemiddel, reseptLege, reseptPasient);
                    }else {
                        resept = new PResept(reseptLegemiddel, reseptLege, reseptPasient, reit);
                    }
                    break;
                case "militaer":
                    resept = new MilitaerResept(reseptLegemiddel, reseptLege, reseptPasient, reit);
                    break;
                default:
                    System.out.println("Feil med type ved reseptoppreting");
                    break;
            }

            if(resept != null){
                resepter.leggTil(resept);
                reseptPasient.nyResept(resept);
                reseptLege.skrivUtResept(resept);
            }
        }
    }

    private static void brukResept(){
        System.out.println("Hvilken pasient vil du se resepter for?");
        int pasientMedReseptNummer = 0;
        Lenkeliste<Pasient> pasienterMedResept = new Lenkeliste <>();

        for(Pasient pasient : pasienter) {
            if (pasient.hentResepter().stoerrelse() > 0) {
                System.out.println(pasientMedReseptNummer + ": " + pasient.hentNavn() + " (fnr" + pasient.hentFodselnummer() + ")");
                pasienterMedResept.leggTil(pasient);
                pasientMedReseptNummer++;
            }
        }
        if(pasienterMedResept.stoerrelse() > 0) {
            int valgtPasientNummer = parseInt(in.nextLine());

            if (valgtPasientNummer >= 0 && valgtPasientNummer < pasienterMedResept.stoerrelse()) {
                Pasient valgtPasient = pasienterMedResept.hent(valgtPasientNummer);
                System.out.println("Valgt pasient: " + valgtPasient.hentNavn() + " (fnr" + valgtPasient.hentFodselnummer() + ")\n" +
                        "Hvilken resept vil du bruke?");

                int reseptNummer = 0;

                for (Resept resept : valgtPasient.hentResepter()) {
                    System.out.println(reseptNummer + ": " + resept.hentLegemiddel().hentNavn() + " (" + resept.hentReit() + " reit)");
                    reseptNummer++;
                }

                int valgtReseptNummer = parseInt(in.nextLine());

                Resept valgtResept = valgtPasient.hentResepter().hent(valgtReseptNummer);

                valgtPasient.brukResept(valgtReseptNummer);

                System.out.println("Brukte resept paa " + valgtResept.hentLegemiddel().hentNavn() + ". Antall gjenverende reit: " + valgtResept.hentReit());
            } else {
                System.out.println("Ikke gyldig pasientnummer, går tilbake til hovedmenyen.");
            }
        }else{
            System.out.println("Ingen pasienter har resepter.");
        }
    }

    public static void statistikk(){

    }
}
