
import java.util.Iterator;
import java.util.NoSuchElementException;

public class StackIterator<T> implements Iterator<T> {
    private BrowserLinkedList<T>.Node<T> cur;

    public StackIterator(BrowserLinkedList<T> list){
        this.cur = list.getTail();
    }

    @Override
    public boolean hasNext(){
        return cur != null;
    }

    @Override
    public T next(){
        if (!hasNext()){
            throw new NoSuchElementException();
        }
        T data = cur.data;
        cur = cur.prev;
        return data;
    }

}
