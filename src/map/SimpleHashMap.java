package map;

public class SimpleHashMap<K,V>  implements Map<K,V> {

    Entry<K, V>[] Table;


    public static class Entry<K,V> implements Map.Entry<K, V> {
        private K m_key;
        private V m_value;

        Entry (K key, V value){
            m_key = key;
            m_value = value;
        }

        @Override
        public String toString() {
            return m_key + " " + m_value;
        }

        @Override
        public K getKey() {
            return m_key;
        }

        @Override
        public V getValue() {
            return m_value;
        }

        @Override
        public V setValue(V value) {
            V tmp = m_value;
            m_value = value;
            return tmp;
        }
    }


    /** Constructs an empty hashmap with the default initial capacity (16)
     and the default load factor (0.75). */
    SimpleHashMap(){

    }
    /** Constructs an empty hashmap with the specified initial capacity
     and the default load factor (0.75). */
    SimpleHashMap(int capacity){
        Table = (Entry<K,V>[]) new Entry[capacity];
    }


    @Override
    public V get(Object arg0) {
        return null;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public V put(K arg0, V arg1) {
        return null;
    }

    @Override
    public V remove(Object arg0) {
        return null;
    }

    @Override
    public int size() {
        return 0;
    }
}
