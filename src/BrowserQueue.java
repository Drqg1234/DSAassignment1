
import java.util.Iterator;

public class BrowserQueue<T> implements Iterable<T>{
    public BrowserArrayList<T> list;

    public BrowserQueue(){
        list = new BrowserArrayList<>();
    }

    public void enqueue(T data){
        list.add(data);
    }

    public T dequeue(){
        return list.remove();
    }

    public boolean isEmpty(){
        return list.isEmpty();
    }

    @Override
    public Iterator<T> iterator(){
        return list.iterator();
    }
}