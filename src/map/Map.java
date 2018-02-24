package map;
public interface Map<K, V> {
    static interface Entry<K, V> {
        K getKey();
        V getValue();
        V setValue(V value);
    }
    V get(Object key);
    boolean isEmpty();
    V put(K key, V value);
    V remove(Object key);
    int size();
}