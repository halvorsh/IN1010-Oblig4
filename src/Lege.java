public class Lege implements Comparable<Lege>{
    private String navn;
    private Lenkeliste<Resept> utskrevneResepter;
    private int narkotiskeLegemidlerUtskrevet;

    public Lege(String navn){
        this.navn = navn;
        utskrevneResepter = new Lenkeliste <>();
    }

    public String hentNavn(){
        return navn;
    }

    public int hentAntallUtskrevneNarkotiskeLegemidler(){
        return narkotiskeLegemidlerUtskrevet;
    }

    public String hentInfo(){
        return hentNavn() + ", 0";
    }

    public void skrivUtResept(Resept resept){
        if(resept.hentLegemiddel() instanceof LegemiddelA){
            narkotiskeLegemidlerUtskrevet++;
        }
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
