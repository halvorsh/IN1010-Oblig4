public class HvitResept extends Resept {

    public HvitResept(Legemiddel legemiddel, Lege utskrivendeLege, Pasient p, int reit) {
        super(legemiddel, utskrivendeLege, p, reit);
    }

    @Override
    public String hentInfo() {
        return "hvit, " + legemiddel.hentID() + ", " + lege.hentNavn() + ", " + p.hentID() + ", " + reit;
    }

    @Override
    public String farge() {
        return "Hvit";
    }

    @Override
    public double prisAaBetale() {
        return legemiddel.hentPris();
    }
}
