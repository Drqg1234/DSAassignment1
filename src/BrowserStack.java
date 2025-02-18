
import java.util.EmptyStackException;
import java.util.Iterator;

public class BrowserStack<T> implements Iterable<T>{
    public BrowserLinkedList<T> list;

    public BrowserStack(){
        list = new BrowserLinkedList<>();
    }

    public void push(T data){
        list.add(data);
    }

    public T pop(){
        if (list.isEmpty()){
            throw new EmptyStackException();
        }
        return list.removeLast();
    }

    public boolean isEmpty(){
        return list.isEmpty();
    }

    @Override
    public Iterator<T> iterator(){
        return new StackIterator<>(list);
    }
}
