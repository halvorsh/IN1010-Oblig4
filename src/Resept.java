public abstract class Resept {
    private int id;
    private static int lastID = -1;
    protected Legemiddel legemiddel;
    protected Lege lege;
    protected Pasient p;
    protected int reit;

    public Resept(Legemiddel legemiddel, Lege utskrivendeLege, Pasient p, int reit){
        this.legemiddel = legemiddel;
        lege = utskrivendeLege;
        this.p = p;
        this.reit = reit;

        id = lastID + 1;
        lastID = id;
    }

    public int hentID(){
        return id;
    }

    public Legemiddel hentLegemiddel(){
        return legemiddel;
    }

    public Lege hentLege(){
        return lege;
    }

    public Pasient hentPasient(){
        return p;
    }

    public int hentReit(){
        return reit;
    }

    public boolean bruk(){
        reit--;
        return reit>=0;
    }

    public abstract String hentInfo();

    public abstract String farge();

    public abstract double prisAaBetale();
}
