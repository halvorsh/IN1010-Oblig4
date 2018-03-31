public class Stabel<T> extends Lenkeliste<T> {

    public void leggPaa(T x){
        Node nyNode = new Node(x);
        if(start == null){
            start = nyNode;
        }else {
            nyNode.forrige = slutt;
            slutt.neste = nyNode;
        }
        slutt = nyNode;
    }

    public T taAv() throws UgyldigListeIndeks {
        Node sisteNode = slutt;
        if(sisteNode == null){
            throw(new UgyldigListeIndeks(0));
        }
        slutt = sisteNode.forrige;
        if(slutt != null) {
            slutt.neste = null;
        }else{
            start = null;
        }
        return sisteNode.gjenstand;
    }
}
