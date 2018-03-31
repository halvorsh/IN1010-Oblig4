public class MilitaerResept extends HvitResept {
    public MilitaerResept(Legemiddel legemiddel, Lege utskrivendeLege, Pasient p, int reit) {
        super(legemiddel, utskrivendeLege, p, reit);
    }

    @Override
    public double prisAaBetale() {
        return 0;
    }

    @Override
    public String hentInfo() {
        return "militaer, " + legemiddel.hentID() + ", " + lege.hentNavn() + ", " + p.hentID() + ", " + reit;
    }

}
