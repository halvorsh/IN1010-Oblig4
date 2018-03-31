public class Lege implements Comparable<Lege>{
    private String navn;
    private Lenkeliste<Resept> utskrevneResepter;

    public Lege(String navn){
        this.navn = navn;
        utskrevneResepter = new Lenkeliste <>();
    }

    public String hentNavn(){
        return navn;
    }

    public String hentInfo(){
        return hentNavn() + ", 0";
    }

    public void skrivUtResept(Resept resept){
        utskrevneResepter.leggTil(resept);
    }

    public Resept fjernResept(){
        return utskrevneResepter.fjern();
    }

    @Override
    public int compareTo(Lege o) {
        return hentNavn().compareTo(o.hentNavn());
    }
}
