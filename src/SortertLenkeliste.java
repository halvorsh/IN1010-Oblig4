public class SortertLenkeliste<T extends Comparable<T>> extends Lenkeliste<T> {

    @Override
    public void leggTil(int pos, T x) throws UgyldigListeIndeks {
        throw(new UnsupportedOperationException());
    }

    @Override
    public void leggTil(T x) {
        Node nyNode = new Node(x);
        if(start == null){
            start = nyNode;
            slutt = nyNode;
        }else{
            Node forrigeNode = null;
            Node gjeldeneNode = start;

            boolean erFrammme = nyNode.gjenstand.compareTo(gjeldeneNode.gjenstand) < 0;

            while(!erFrammme){
                forrigeNode = gjeldeneNode;
                gjeldeneNode = gjeldeneNode.neste;
                if(gjeldeneNode != null) {
                    erFrammme = nyNode.gjenstand.compareTo(gjeldeneNode.gjenstand) < 0;
                }else{
                    erFrammme = true;
                }
            }

            if(forrigeNode == null){
                gjeldeneNode.forrige = nyNode;
                nyNode.neste = gjeldeneNode;
                start = nyNode;
            }else if(gjeldeneNode == null){
                forrigeNode.neste = nyNode;
                nyNode.forrige = forrigeNode;
                slutt = nyNode;
            }else{
                forrigeNode.neste = nyNode;
                nyNode.forrige = forrigeNode;
                nyNode.neste = gjeldeneNode;
                gjeldeneNode.forrige = nyNode;
            }
        }
    }

    @Override
    public void sett(int pos, T x) throws UgyldigListeIndeks {
        throw(new UnsupportedOperationException());
    }

    @Override
    public T fjern() throws UgyldigListeIndeks {
        Node fjernetNode = slutt;
        if(slutt == null){
            throw(new UgyldigListeIndeks(0));
        }
        slutt = slutt.forrige;
        if(fjernetNode == start){
            start = null;
        }else{
            slutt.neste = null;
        }
        return fjernetNode.gjenstand;
    }
}
