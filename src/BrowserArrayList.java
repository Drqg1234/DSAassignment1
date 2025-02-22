// BrowserArrayList uses a circular array to create a simple ArrayList, with a dynamic array

import java.util.Iterator;
import java.util.NoSuchElementException;

public class BrowserArrayList<T> implements Iterable<T> {
    private static final int CAPACITY = 10;
    private T[] arr;
    private int size;
    private int head;
    private int tail;

    
    @SuppressWarnings("unchecked")
    public BrowserArrayList(){
        arr = (T[]) new Object[CAPACITY];
        size = 0;
        head = 0;
        tail = -1;
    }

    @SuppressWarnings("unchecked")
    private void resize(){
        
        T[] nArr = (T[]) new Object[arr.length * 2];
        for (int i = 0; i < size; i++){
            nArr[i] = arr[(head + i) % arr.length];
        }
        arr = nArr;
        head = 0;
        tail = size - 1;
    }

    public int size(){
        return size;
    }

    public boolean isEmpty(){
        return size == 0;
    }

    public void add (T data){
        if (size == arr.length){
            resize();
        }
        tail = (tail + 1) % arr.length;
        arr[tail] = data;
        size++;
    }

    public T remove(){
        if (size == 0){
            throw new NoSuchElementException();
        }
        T data = arr[head];
        arr[head] = null;
        head = (head + 1) % arr.length;
        size--;
        return data;
    }

    private class BrowserArrayListIterator implements Iterator<T>{
        private int curIndex = 0;
        
        @Override
        public boolean hasNext(){
            return curIndex < size;
        }

        @Override
        public T next(){
            if (!hasNext()){
                throw new NoSuchElementException();
            }
            T data = arr[(head + curIndex) % arr.length];
            curIndex++;
            return data;
        }
    }

    @Override
    public Iterator<T> iterator(){
        return new BrowserArrayListIterator();
    }
}
