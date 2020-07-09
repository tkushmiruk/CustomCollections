import java.util.*;

public class LinkedListCustom<T> implements List<T> {

    private int size = 0;
    private Node<T> first;
    private Node<T> last;
    private Node<T> element;

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        if (size == 0) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean contains(Object o) {
        Node<T> check = first;
        if (check.getItem() == o) {
            return true;
        }
        for (int i = 1; i < size; i++) {
            check = check.getNext();
            if (check.getItem() == o) {
                return true;
            }
        }

        return false;
    }

    @Override
    public Iterator<T> iterator() {
        throw new UnsupportedOperationException("Dont need to Do this");
    }

    @Override
    public Object[] toArray() {
        Object[] array = new Object[size];
        Node<T> buf = first;
        array[0] = buf.getItem();
        for (int i = 1; i < size; i++) {
            buf = buf.getNext();
            array[i] = buf.getItem();
        }
        return array;
    }

    @Override
    public <T1> T1[] toArray(T1[] a) {
        return null;
    }

    @Override
    public boolean add(T t) {
        if (size == 0) {
            first = new Node<T>(first, t, first);
            size++;
            return true;
        } else if (size == 1) {
            element = new Node<T>(first, t, first);
            first.setNext(element);
            first.setPrev(element);
            size++;
        } else {
            last = element;
            element = new Node<T>(last, t, first);
            last.setNext(element);
            first.setPrev(element);
            size++;
        }


        return true;
    }

    @Override
    public boolean remove(Object o) {
        Node<T> removedElement = first;
        Node<T> nextElement = removedElement.getNext();
        Node<T> previosElement = removedElement.getPrev();

        for (int i = 0; i < size; i++) {
            if (removedElement == o) {
                previosElement.setNext(nextElement);
                nextElement.setPrev(previosElement);
                return true;
            }
            removedElement = removedElement.getNext();
            nextElement = removedElement.getNext();
            previosElement = removedElement.getPrev();

        }
        return false;
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        int counter = 0;
        Node<T> currentElement = first;
        Object[] arra = c.toArray();
        for(int i =0; i < c.size(); i++){
            Object suchElement = arra[i];
            for(int j = 0; j < size; j++){
                if(currentElement.getItem() == suchElement){
                    counter++;
                }
                currentElement = currentElement.getNext();
            }
        }
        if(counter == c.size()){
            return true;
        }
        return false;
    }

    @Override
    public boolean addAll(Collection<? extends T> c) {
        Object[] array = c.toArray();
        for (int i = 0; i < c.size(); i++) {
            add((T) array[i]);
        }
        size += c.size();
        return true;
    }

    @Override
    public boolean addAll(int index, Collection<? extends T> c) {
        Node<T> currentElement = first;
        Node<T> nextElement = currentElement.getNext();
        Node<T> addedElement ;
        Object[] objects = c.toArray();
        for(int i = 0; i < index; i ++){
            currentElement = currentElement.getNext();
            nextElement = currentElement.getNext();
        }
        for(int i = 0; i < c.size(); i++){
            addedElement = new Node<T>(currentElement,(T)objects[i], nextElement);
            currentElement = addedElement;
            nextElement = currentElement.getNext();
            size++;

        }

        return true;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        Node<T> currentElement = first;
        Node<T> nextElement = currentElement.getNext();
        Node<T> previosElement = currentElement.getPrev();
        Object[]objects = c.toArray();
        for(int  i  =0 ;i< c.size(); i++){
            Object ob = objects[i];
            for(int j = 0; j < size; j++){
                if(ob == currentElement.getItem()){
                    nextElement.setPrev(previosElement);
                    previosElement.setNext(nextElement);
                    size--;
                    break;
                }
                currentElement = currentElement.getNext();
                nextElement = currentElement.getNext();
                previosElement = currentElement.getPrev();

            }
        }
        return true;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        Node<T> currentElement = first;
        Node<T> nextElement = currentElement.getNext();
        Node<T> previosElement = currentElement.getPrev();
        Object[]objects = c.toArray();
        for(int  i  =0 ;i< c.size(); i++){
            Object ob = objects[i];
            for(int j = 0; j < size; j++){
                if(ob == currentElement.getItem()){
                    nextElement.setPrev(previosElement);
                    previosElement.setNext(nextElement);
                    size--;
                    break;
                }
                currentElement = currentElement.getNext();
                nextElement = currentElement.getNext();
                previosElement = currentElement.getPrev();

            }
        }
        return true;
    }

    @Override
    public void clear() {
        first = new Node<T>(null, null, null);
        last = new Node<T>(null, null, null);
        size = 0;

    }

    @Override
    public T get(int index) {
        Node<T> currentElement = first;
        if(index == 0){
            return currentElement.getItem();
        }
        for(int i = 1; i <  size; i++){
            currentElement = currentElement.getNext();
            if(index == i){
                return  currentElement.getItem();
            }
        }

        return null;
    }

    @Override
    public T set(int index, T element) {
        return null;
    }

    @Override
    public void add(int index, T element) {
        Node<T> currentElement = first;
        Node<T> nextElement = first.getNext();
        Node<T> newElement;
        if (index == 0) {
            size++;
            newElement = new Node<T>(currentElement, element, nextElement);
        }
        for (int i = 1; i < size; i++) {
            currentElement = currentElement.getNext();
            nextElement = currentElement.getNext();
            if (index == i) {
                size++;
                newElement = new Node<T>(currentElement, element, nextElement);
            }

        }

    }


    @Override
    public T remove(int index) {
        Node<T> removedElement = first;
        Node<T> nextElement;
        Node<T> previosElement;
        nextElement = first.getNext();
        previosElement = first.getPrev();
        if(index == 0 ){
            nextElement.setPrev(previosElement);
            previosElement.setNext(nextElement);
        }
        for(int i = 1; i <  size; i++){
            previosElement = removedElement;
            removedElement = removedElement.getNext();
            nextElement = removedElement.getNext();
            if(index == i){
                nextElement.setPrev(previosElement);
                previosElement.setNext(nextElement);
            }

        }
        return null;
    }

    @Override
    public int indexOf(Object o) {
        Node<T> currentElement = first;
        for(int i = 0; i < size; i++){
            if(currentElement.getItem() == o){
                return i;
            }
            currentElement = currentElement.getNext();
        }
        return 0;
    }

    @Override
    public int lastIndexOf(Object o) {
        Node<T> currentElement = first;
        int index = 0;
        for (int i = 0; i < size; i++) {
            if (currentElement.getItem() == o) {
                index = i;
            }
            currentElement = currentElement.getNext();

            return index;
        }
         throw new NoSuchElementException();
    }

    @Override
    public ListIterator<T> listIterator() {
        throw new UnsupportedOperationException("Dont need to Do this");
    }

    @Override
    public ListIterator<T> listIterator(int index) {
        throw new UnsupportedOperationException("Dont need to Do this");
    }

    @Override
    public List<T> subList(int fromIndex, int toIndex) {
        List<T> list = new ArrayList<T>();
        Node<T> currentElement = first;
        for(int  i = 0; i < fromIndex; i++){
            currentElement = currentElement.getNext();
        }
        for (int i = fromIndex; i < toIndex; i++){
            list.add(currentElement.getItem());
            currentElement = currentElement.getNext();
        }
        return list;
    }
}
