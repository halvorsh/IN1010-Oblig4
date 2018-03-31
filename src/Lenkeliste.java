import java.util.Iterator;

public class Lenkeliste<T> implements Liste<T>{

    protected class Node{
        Node neste;
        Node forrige;
        T gjenstand;

        public Node(T gjenstand){
            this.gjenstand = gjenstand;
        }
    }

    public class LenkelisteIterator<T> implements Iterator<T> {
        Node gjeldeneNode = start;
        int stoerrelse = stoerrelse();

        @Override
        public boolean hasNext() {
            for(int i = 0; i < stoerrelse; i++) {
                if (gjeldeneNode != null) {
                    return true;
                }
            }
            return false;
        }

        @Override
        public T next() {
            for(int i = 0; i < stoerrelse; i++){
                if(gjeldeneNode.gjenstand != null){
                    T gjenstand = (T) gjeldeneNode.gjenstand;
                    gjeldeneNode = gjeldeneNode.neste;
                    return gjenstand;
                }
            }
            return null;
        }
    }


    protected Node start;
    protected Node slutt;

    public Lenkeliste(){
        start = null;
        slutt = null;
    }

    @Override
    public int stoerrelse() {
        int stoerrelse = 0;
        if(start!=null) {
            Node gjeldeneNode = start;
            stoerrelse++;
            while (gjeldeneNode != slutt) {
                gjeldeneNode = gjeldeneNode.neste;
                stoerrelse++;
            }
        }
        return stoerrelse;
    }

    @Override
    public void leggTil(int pos, T x) throws UgyldigListeIndeks{
        if(start == null && pos!=0 || pos<0){
            throw(new UgyldigListeIndeks(pos));
        }else if(start == null){
            Node node = new Node(x);
            start = node;
            slutt = node;
        }else{
            Node nyNode = new Node(x);
            Node gjeldeneNode = start;
            Node forrigeNode = gjeldeneNode.forrige;

            for(int i = 0; i<pos; i++){
                forrigeNode = gjeldeneNode;
                if(forrigeNode == null){
                    throw(new UgyldigListeIndeks(pos));
                }
                gjeldeneNode = gjeldeneNode.neste;
            }

            if(gjeldeneNode == null){
                slutt = nyNode;
                forrigeNode.neste = nyNode;
            }else if(forrigeNode == null){
                start = nyNode;
                nyNode.neste = gjeldeneNode;
            }else{
                forrigeNode.neste = nyNode;
                nyNode.forrige = forrigeNode;
                nyNode.neste = gjeldeneNode;
                gjeldeneNode.forrige = nyNode;
            }
        }
    }

    @Override
    public void leggTil(T x) {
        if(start == null){
            Node node = new Node(x);

            start = node;
            slutt = node;
        }else{
            Node nyNode = new Node(x);
            nyNode.forrige = slutt;
            slutt.neste = nyNode;
            slutt = nyNode;
        }
    }

    @Override
    public void sett(int pos, T x) throws UgyldigListeIndeks{
        if(pos<0 || start == null){
            throw(new UgyldigListeIndeks(pos));
        }
        Node gjeldeneNode = start;
        for(int i = 0; i<pos; i++){
            gjeldeneNode = gjeldeneNode.neste;
            if(gjeldeneNode == null){
                throw(new UgyldigListeIndeks(pos));
            }
        }
        gjeldeneNode.gjenstand = x;
    }

    @Override
    public T hent(int pos) throws UgyldigListeIndeks{
        if(pos<0){
            throw(new UgyldigListeIndeks(pos));
        }
        Node gjeldeneNode = start;
        if(gjeldeneNode == null){
            throw(new UgyldigListeIndeks(0));
        }
        for(int i = 0; i<pos; i++){
            gjeldeneNode = gjeldeneNode.neste;
            if(gjeldeneNode == null){
                throw(new UgyldigListeIndeks(pos));
            }
        }
        return gjeldeneNode.gjenstand;
    }

    @Override
    public T fjern(int pos) throws UgyldigListeIndeks{
        if(start == null){
            throw(new UgyldigListeIndeks(0));
        }else if(pos<0){
            throw(new UgyldigListeIndeks(pos));
        }

        Node forrigeNode = null;
        Node gjeldeneNode = start;
        Node nesteNode = gjeldeneNode.neste;

        for(int i = 0; i<pos; i++){
            forrigeNode = gjeldeneNode;
            gjeldeneNode = gjeldeneNode.neste;
            if(gjeldeneNode == null){
                throw(new UgyldigListeIndeks(pos));
            }
            nesteNode = gjeldeneNode.neste;
        }

        if(forrigeNode == null && nesteNode == null){
            start = null;
            slutt = null;
        }else if(forrigeNode == null){
            start = nesteNode;
            nesteNode.forrige = null;
        }else if(nesteNode == null){
            slutt = forrigeNode;
            forrigeNode.neste = null;
        }else{
            forrigeNode.neste = nesteNode;
            nesteNode.forrige = forrigeNode;
        }

        return gjeldeneNode.gjenstand;
    }

    @Override
    public T fjern() throws UgyldigListeIndeks{
        Node fjernetNode = start;
        if(start == null){
            throw(new UgyldigListeIndeks(0));
        }
        start = start.neste;
        if(fjernetNode == slutt){
            slutt = null;
        }
        return fjernetNode.gjenstand;
    }

    @Override
    public Iterator <T> iterator() {
        return new LenkelisteIterator <>();
    }
}
