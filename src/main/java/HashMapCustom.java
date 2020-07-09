import java.util.Collection;
import java.util.Map;
import java.util.Set;

public class HashMapCustom<K,V> implements Map<K, V> {
    private int size = 0;

    private EntryCustom<K, V>[] entries;


    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {

        if (size == 0){
            return true;
        }
        return false;
    }

    @Override
    public boolean containsKey(Object key) {
        return false;
    }

    @Override
    public boolean containsValue(Object value) {
        return false;
    }

    @Override
    public V get(Object key) {
        return null;
    }

    @Override
    public V put(K key, V value) {
        return null;
    }

    @Override
    public V remove(Object key) {
        return null;
    }

    @Override
    public void putAll(Map<? extends K, ? extends V> m) {

    }

    @Override
    public void clear() {

    }

    @Override
    public Set<K> keySet() {
        return null;
    }

    @Override
    public Collection<V> values() {
        return null;
    }

    @Override
    public Set<Entry<K, V>> entrySet() {
        throw new UnsupportedOperationException();
    }
}
