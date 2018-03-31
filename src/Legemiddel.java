public abstract class Legemiddel {
    private String navn;
    private double pris;
    private double virkestoff;
    private int id;
    private static int lastID = -1;
    
    public Legemiddel(String navn, double pris, double virkestoff){
        this.navn = navn;
        this.pris = pris;
        this.virkestoff = virkestoff;

        id = lastID+1;
        lastID = id;
    }

    public int hentID(){
        return id;
    }

    public String hentNavn(){
        return navn;
    }
    
    public double hentPris(){
        return pris;
    }

    public double hentVirkestoff(){
        return virkestoff;
    }

    public void settNyPris(double nyPris){
        pris = nyPris;
    }

    public abstract String hentInfo();
}
