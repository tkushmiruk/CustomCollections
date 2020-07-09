import com.sun.xml.internal.xsom.impl.scd.Iterators;

import java.lang.reflect.Array;
import java.util.Collection;
import java.util.Deque;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class ArrayDequeCustom<T> implements Deque<T> {
    /**
     * The array in which the elements of the deque are stored.
     * All array cells not holding deque elements are always null.
     * The array always has at least one null slot (at tail).
     */
    private T[] elements;

    /**
     * The index of the element at the head of the deque (which is the
     * element that would be removed by remove() or pop()); or an
     * arbitrary number 0 <= head < elements.length equal to tail if
     * the deque is empty.
     */
    private int head = 0;

    /**
     * The index at which the next element would be added to the tail
     * of the deque (via addLast(E), add(E), or push(E));
     * elements[tail] is always null.
     */
    private int tail = 10;

    /**
     * The maximum size of array to allocate.
     * Some VMs reserve some header words in an array.
     * Attempts to allocate larger arrays may result in
     * OutOfMemoryError: Requested array size exceeds VM limit
     */
    private static final int MAX_ARRAY_SIZE = Integer.MAX_VALUE - 8;

    public ArrayDequeCustom() {
        elements = (T[]) new Object[tail];
    }


    @Override
    public void addFirst(T t) {
        verifyCapacity();
        if (head == 0) {
            elements[head] = t;
            head++;

        } else {
            Object[] bufArray = new Object[tail];
            for (int i = 0; i < elements.length; i++) {
                bufArray[i + 1] = elements[i];
            }
            elements[0] = t;
            for (int i = 1; i < elements.length; i++) {
                elements[i] = (T) bufArray[i];
            }
            head++;
        }

    }

    @Override
    public void addLast(T t) {
        verifyCapacity();
        elements[head] = t;
        head++;


    }

    @Override
    public boolean offerFirst(T t) {
        verifyCapacity();
        if (elements[0] == null) {
            elements[0] = t;
            head++;
            return true;
        } else if (elements[0] != null) {
            Object[] bufArray = new Object[tail];
            for (int i = 0; i < elements.length; i++) {
                bufArray[i + 1] = elements[i];
            }
            elements[0] = t;
            head++;
            for (int i = 1; i < elements.length; i++) {
                elements[i] = (T) bufArray[i];
            }
        }
        return false;
    }

    @Override
    public boolean offerLast(T t) {
        verifyCapacity();
        elements[head] = t;
        head++;
        return true;
    }

    @Override
    public T removeFirst() {
        if (elements[0] != null) {
            Object[] bufArray = new Object[tail];
            Object ob = elements[0];
            for (int i = 1; i < tail; i++) {
                bufArray[i] = elements[i];
            }
            for (int i = 0; i < tail; i++) {
                elements[i] = (T) bufArray[i + 1];
            }
            head--;
            return (T) ob;
        } else {
            throw new NullPointerException();
        }
    }

    @Override
    public T removeLast() {
        if (elements[head] != null) {
            Object o = elements[elements.length - 1];
            elements[head] = null;
            head--;
            return (T) o;
        } else {
            throw new NullPointerException();
        }
    }

    @Override
    public T pollFirst() {
        if (elements[0] != null) {
            Object[] bufArray = new Object[tail];
            Object ob = elements[0];
            for (int i = 1; i < tail; i++) {
                bufArray[i] = elements[i];
            }
            for (int i = 0; i < tail; i++) {
                elements[i] = (T) bufArray[i + 1];
            }
            head--;
            return (T) ob;
        }
        return null;
    }

    @Override
    public T pollLast() {
        if (elements[head] != null) {
            Object o = elements[head];
            elements[head] = null;
            head--;
            return (T) o;
        }
        return null;
    }

    @Override
    public T getFirst() {
        return elements[0];

    }

    @Override
    public T getLast() {
        return elements[head];
    }

    @Override
    public T peekFirst() {
        if (elements[0] != null) {
            return elements[0];
        }
        return null;
    }

    @Override
    public T peekLast() {
        if (elements[head] != null) {
            return elements[head];
        }
        return null;
    }

    @Override
    public boolean removeFirstOccurrence(Object o) {
        for (int i = 0; i < elements.length; i++) {
            if (elements[i] != null) {
                elements[i] = null;
                head--;
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean removeLastOccurrence(Object o) {
        for (int i = elements.length - 1; i > 0; i--) {
            if (elements[i] != null) {
                elements[i] = null;
                head--;
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean add(T t) {
        verifyCapacity();
        elements[head] = t;
        head++;
        return true;
    }

    @Override
    public boolean offer(T t) {
        verifyCapacity();
        elements[head] = t;
        head++;

        return true;
    }

    @Override
    public T remove() {
        if (head > 0) {
            Object o = elements[head];
            elements[head] = null;
            head--;
            return (T) o;

        }

        throw new NoSuchElementException();
    }

    @Override
    public T poll() {
        if (head > 0) {
            Object o = elements[head];
            elements[head] = null;
            head--;
            return (T) o;

        }
        return null;
    }

    @Override
    public T element() {
        if(head>0){
            return elements[head] ;
        }
        throw new NoSuchElementException();
    }

    @Override
    public T peek() {
        if(head>0){
            return elements[head] ;
        }
        return null;
    }

    @Override
    public boolean addAll(Collection<? extends T> c) {
        verifyCapacity();
        if(tail<c.size()) {
            addAll(c);
        }
        Object[] collection = c.toArray();
        for (int i = 0; i < c.size(); i++) {
            elements[i] = (T) collection[i];
            head++;
        }
        return true;

    }

    @Override
    public boolean removeAll(Collection<?> c) {
        int count = 0;
        Object[] collection = c.toArray();
        for (int i = 0; i < elements.length; i++) {
            for (int j = 0; j < c.size(); j++) {
                if (elements[i] == (T) collection[j]) {
                    elements[i] = null;
                    count++;
                    head--;
                    break;

                }
            }
        }

        Object []bufArray = new Object[head];
        for(int i = 0; i < tail; i++){
            int counter  = 0;
            if(elements[i] !=null){
                bufArray[counter] = elements[i];
                counter++;
            }
        }
        clear();
        for(int i = 0; i< bufArray.length; i++){
            elements[i] = (T)bufArray[i];
        }
        if (count == c.size()) {
            return true;
        }
        return false;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        removeAll(c);
        return false;
    }

    @Override
    public void clear() {
        for (int i = 0; i < elements.length; i++) {
            elements[i] = null;
        }
        head = 0;

    }

    @Override
    public void push(T t) {
        add(t);
    }

    @Override
    public T pop() {
        if (elements[0] != null) {
            return elements[0];
        }
        throw new NoSuchElementException();
    }

    @Override
    public boolean remove(Object o) {
        boolean isHere = false;
        for (int i = 0; i < elements.length; i++) {
            if (elements[i] == o) {
                elements[i] = null;
                isHere =true;
                break;
            }
        }
        Object []bufArray = new Object[head];
        for(int i = 0; i < tail; i++){
            int counter  = 0;
            if(elements[i] !=null){
                bufArray[counter] = elements[i];
                counter++;
            }
        }
        clear();
        for(int i = 0; i< bufArray.length; i++){
            elements[i] = (T)bufArray[i];
        }
        if(isHere){
            return isHere;
        }
        else {
            return false;
        }

    }

    @Override
    public boolean containsAll(Collection<?> c) {
        int count = 0;
        Object[] collection = c.toArray();
        for (int i = 0; i < elements.length; i++) {
            for (int j = 0; j < c.size(); j++) {
                if (elements[i] == (T) collection[j]) {
                    count++;
                    break;

                }
            }
        }
        if (count == c.size()) {
            return true;
        }
        return false;
    }

    @Override
    public boolean contains(Object o) {
        boolean contain = false;
        for (int i = 0; i < elements.length; i++) {
            if (o == elements[i]) {
                contain = true;
                break;
            }
        }
        return contain;
    }

    @Override
    public int size() {

        return head;
    }

    @Override
    public boolean isEmpty() {
        if (elements.length == 0) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterators.Array<T>(elements);
    }

    @Override
    public Object[] toArray() {
        Object[] objects = new Object[elements.length];
        for (int i = 0; i < elements.length; i++) {
            objects[i] = elements[i];
        }

        return objects;
    }

    @Override
    public <T1> T1[] toArray(T1[] a) {
        for (int i = 0; i < elements.length; i++) {
            a[i] = (T1) elements[i];
        }
        return null;
    }

    @Override
    public Iterator<T> descendingIterator() {
        return new Iterators.Array<T>(elements);

    }

    public void verifyCapacity() {
        if (head > tail) {
            tail = tail + (tail / 2) + 1;
            Object[] bufElements = new Object[tail];
            for (int i = 0; i < elements.length; i++) {
                bufElements[i] = elements[i];
            }
            elements = (T[]) new Object[tail];
            for (int i = 0; i < bufElements.length; i++) {
                elements[i] = (T) bufElements[i];
            }
        }
    }
}

