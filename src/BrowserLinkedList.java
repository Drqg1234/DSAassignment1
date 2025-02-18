import java.util.Iterator;
import java.util.NoSuchElementException;

public class BrowserLinkedList<T> implements Iterable<T> {
    @SuppressWarnings("hiding")
    public class Node<T>{
        T data;
        Node<T> next;
        Node<T> prev;

        public Node(T data){
            this.data = data;
            this.next = null;
            this.prev = null;
        }
    }

    private Node<T> head;
    private Node<T> tail;
    private int size;

    //yes
    public BrowserLinkedList(){
        head = null;
        tail = null;
        size = 0;
    }

    //yes
    public boolean isEmpty() {
        return size == 0;
    }

    public Node<T> getTail(){
        return tail;
    }

    //yes
    public void add(T data){
        Node<T> node = new Node<>(data);
        if (head == null){
            head = node;
            tail = node;
        }
        else{
            tail.next = node;
            node.prev = tail;
            tail = node;
        }
        size++;
    }

    public T removeLast(){
        if (tail == null){
            throw new NoSuchElementException("Babagee mobail");
        }
        T data = tail.data;
        if (head == tail){
            head = null;
            tail = null;
        }
        else{
            tail = tail.prev;
            tail.next = null;
        }
        size--;
        return data;
    }
    // //maybe
    // public boolean remove(T data){
    //     Node<T> cur = head;
    //     while (cur != null){
    //         if (cur.data.equals(data)){
    //             if (cur.prev != null){
    //                 cur.prev.next = cur.next;
    //             }
    //             else{
    //                 head = cur.next;
    //             }

    //             if (cur.next != null){
    //                 cur.next.prev = cur.prev;
    //             }
    //             else{
    //                 tail = cur.prev;
    //             }
    //             size--;
    //             return true;
    //         }
    //         cur = cur.next;
    //     }
    //     return false;
    // }

    // //maybe have only 1 remove to make it simple
    // public T remove(int index){
    //     if (index < 0 || index >= size){
    //         throw new IndexOutOfBoundsException();
    //     }
    //     Node<T> cur = head;
    //     for (int i = 0; i < index; i++){
    //         cur = cur.next;
    //     }
    //     if (cur.prev != null){
    //         cur.prev.next = cur.next;
    //     }
    //     else{
    //         head = cur.next;
    //     }
    //     if (cur.next != null){
    //         cur.next.prev = cur.prev;
    //     }
    //     else{
    //         tail = cur.prev;
    //     }
    //     size--;
    //     return cur.data;
    // }

    //maybe
    public T get(int index){
        if (index < 0 || index >= size){
            throw new IndexOutOfBoundsException();
        }
        Node<T> cur = head;
        for (int i = 0; i < index; i++){
            cur = cur.next;
        }
        return cur.data;
    }

    //yes
    public int size(){
        return size;
    }

    //maybe
    public void clear(){
        head = null;
        tail = null;
        size = 0;
    }

    //yes
    private class BrowserLinkedListIterator implements Iterator<T>{
        private Node<T> cur = head;

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
            cur = cur.next;
            return data;
        }

        
    }


    @Override
    public Iterator<T> iterator(){
        return new BrowserLinkedListIterator();
    }
}
