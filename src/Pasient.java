public class Pasient {
    private String navn;
    private String fodselsnummer;
    private Stabel<Resept> resepter;

    private int id;
    private static int lastID = -1;

    public Pasient(String navn, String fodselsnummer){
        this.navn = navn;
        this.fodselsnummer = fodselsnummer;

        resepter = new Stabel <>();

        id = lastID + 1;
        lastID = id;
    }

    public void nyResept(Resept resept){
        resepter.leggPaa(resept);
    }

    public Stabel<Resept> hentResepter(){
        return resepter;
    }

    public void brukResept(){
        if(!resepter.hent(resepter.stoerrelse() -1).bruk()){
            resepter.taAv();
        }
    }

    public int hentID(){
        return id;
    }

    public String hentNavn(){
        return navn;
    }

    public String hentInfo(){
        return navn + ", " + fodselsnummer;
    }
}
