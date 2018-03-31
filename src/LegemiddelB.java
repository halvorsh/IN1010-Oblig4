public class LegemiddelB extends Legemiddel {
    private int vanedannedeStyrke;

    public LegemiddelB(String navn, double pris, double virkestoff, int styrke){
        super(navn, pris, virkestoff);

        vanedannedeStyrke = styrke;
    }

    public int hentVanedannendeStyrke(){
        return vanedannedeStyrke;
    }

    @Override
    public String hentInfo(){
        return hentNavn() +", b, " + hentPris() + ", " + hentVirkestoff() + ", " + hentVanedannendeStyrke();
    }
}
