public class FastLege extends Lege implements Kommuneavtale{
    private int avtaleNummer;

    public FastLege(String navn, int avtaleNummer) {
        super(navn);
        this.avtaleNummer = avtaleNummer;
    }

    @Override
    public int hentAvtaleNummer() {
        return avtaleNummer;
    }

    @Override
    public String hentInfo() {
        return hentNavn() + ", " + hentAvtaleNummer();
    }
}
