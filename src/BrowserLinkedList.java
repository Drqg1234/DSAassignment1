// A custom doubly linked list with only the essential methods for functionality (add, remove, and getters)

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

    public BrowserLinkedList(){
        head = null;
        tail = null;
        size = 0;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public Node<T> getTail(){
        return tail;
    }

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
            throw new NoSuchElementException();
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

    public int size(){
        return size;
    }

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
