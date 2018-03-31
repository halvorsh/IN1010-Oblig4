public class LegemiddelA extends Legemiddel {
    private int narkotiskStyrke;

    public LegemiddelA(String navn, double pris, double virkestoff, int styrke){
        super(navn, pris, virkestoff);
        narkotiskStyrke = styrke;
    }

    private int hentNarkotiskStyrke(){
        return narkotiskStyrke;
    }

    @Override
    public String hentInfo(){
        return hentNavn() +", a, " + hentPris() + ", " + hentVirkestoff() + ", " + hentNarkotiskStyrke();
    }
}
