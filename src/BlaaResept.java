public class BlaaResept extends Resept {
    public BlaaResept(Legemiddel legemiddel, Lege utskrivendeLege, Pasient p, int reit) {
        super(legemiddel, utskrivendeLege, p, reit);
    }

    @Override
    public String farge() {
        return "Blå";
    }

    @Override
    public double prisAaBetale() {
        return legemiddel.hentPris()*0.25;
    }

    @Override
    public String hentInfo() {
        return "blaa, " + legemiddel.hentID() + ", " + lege.hentNavn() + ", " + p.hentID() + ", " + reit;
    }

}
