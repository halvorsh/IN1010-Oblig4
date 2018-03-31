public class PResept extends HvitResept {
    public PResept(Legemiddel legemiddel, Lege utskrivendeLege, Pasient p) {
        super(legemiddel, utskrivendeLege, p, 3);
    }

    public PResept(Legemiddel legemiddel, Lege utskrivendeLege, Pasient p, int reit) {
        super(legemiddel, utskrivendeLege, p, reit);
    }

    @Override
    public double prisAaBetale() {
        double endeligPris;

        if(legemiddel.hentPris()>116){
            endeligPris = legemiddel.hentPris()-116;
        }else{
            endeligPris = 0;
        }

        return endeligPris;
    }

    @Override
    public String hentInfo() {
        return "prevensjon, " + legemiddel.hentID() + ", " + lege.hentNavn() + ", " + p.hentID() + ", " + reit;
    }

}
