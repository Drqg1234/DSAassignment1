
import java.util.Iterator;
import java.util.NoSuchElementException;

public class BrowserArrayList<T> implements Iterable<T> {
    private static final int CAPACITY = 10;
    private T[] arr;
    private int size;
    private int head;
    private int tail;

    //yes
    @SuppressWarnings("unchecked")
    public BrowserArrayList(){
        arr = (T[]) new Object[CAPACITY];
        size = 0;
        head = 0;
        tail = -1;
    }

    @SuppressWarnings("unchecked") //yes - check
    private void resize(){
        
        T[] nArr = (T[]) new Object[arr.length * 2];
        for (int i = 0; i < size; i++){
            nArr[i] = arr[(head + i) % arr.length];
        }
        arr = nArr;
        head = 0;
        tail = size - 1;
    }

    //yes
    public int size(){
        return size;
    }

    public boolean isEmpty(){
        return size == 0;
    }

    //yes - check
    public void add (T data){
        if (size == arr.length){
            resize();
        }
        tail = (tail + 1) % arr.length;
        arr[tail] = data;
        size++;
    }

    //maybe
    public T get(int index){
        if (index < 0 || index >= size){
            throw new IndexOutOfBoundsException();
        }
        int in = (head + index) % arr.length; 
        return arr[in];
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

    // public T remove(int index){
    //     if (index < 0 || index >= size){
    //         throw new IndexOutOfBoundsException();
    //     }
    //     int in = (head + index) % arr.length; 
    //     T removedT = arr[in];

    //     if (index < size / 2) {
    //         for (int i = index; i > 0; i--){
    //             int cur = (head + i) % arr.length;
    //             int prev = (head + i - 1) % arr.length;
    //             arr[cur] = arr[prev];
    //         }
    //         head = (head + 1) % arr.length;
    //     }
    //     else{
    //         for (int i = index; i < size - 1; i++){
    //             int cur = (head + i) % arr.length;
    //             int next = (head + i + 1) % arr.length;
    //             arr[cur] = arr[next];
    //         }
    //         tail = (tail - 1 + arr.length) % arr.length;
    //     }
    //     size--;
    //     return removedT;
    // }



    private class BrowserArrayListIterator implements Iterator<T>{
        private int curIndex = 0 ;
        

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
