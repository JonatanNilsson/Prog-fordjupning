package map;

public class SimpleHashMap<K,V>  implements Map<K,V> {

    Entry<K, V>[] Table;
    private int size = 0;

    public static class Entry<K,V> implements Map.Entry<K, V> {
        private K m_key;
        private V m_value;
        private Entry next;

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
    public SimpleHashMap(){
        Table = (Entry<K,V>[]) new Entry[100];
    }
    /** Constructs an empty hashmap with the specified initial capacity
     and the default load factor (0.75). */
    public SimpleHashMap(int capacity){
        Table = (Entry<K,V>[]) new Entry[capacity];
    }

    String show(){
        StringBuilder result = new StringBuilder();

        for (Entry<K, V> aTable : Table) {
            Entry entry = aTable;
            while (entry != null) {
                result.append(entry);
                entry = entry.next;
            }
            result.append('\n');
        }

        return result.toString();
    }

    @Override
    public V get(Object arg0) {
        K key = (K) arg0;
        Entry<K,V> entry = find(index(key), key);
        if (entry == null) return null;
        return entry.m_value;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public V put(K key, V value) {

        int index = index(key);
        Entry<K,V> oldEntry = find(index, key);

        if (oldEntry == null){
            size++;

            if (Table[index] == null) {
                Table[index] = new Entry<>(key, value);
                return null;
            }

            Entry<K, V> last = Table[index];
            while (last.next != null){
                last = last.next;
            }
            last.next = new Entry<>(key, value);
            return null;
        }
        else{
            V old_value = oldEntry.m_value;
            oldEntry.m_value = value;
            return old_value;
        }
    }

    @Override
    public V remove(Object arg0) {
        return null;
    }

    @Override
    public int size() {
        return size;
    }


    private int index(K key){
        int hashCode = key.hashCode();
        return (hashCode < 0 ? -hashCode : hashCode) % Table.length;
    }

    private Entry<K,V> find(int index, K key){
        Entry entry = Table[index];
        while (entry != null && !entry.m_key.equals(key)) entry = entry.next;
        return entry;
    }
}
