package map;

public class SimpleHashMap<K,V>  implements Map<K,V> {

    private Entry<K, V>[] m_table;
    private int m_size = 0;

    public static class Entry<K,V> implements Map.Entry<K, V> {
        private K m_key;
        private V m_value;
        private Entry<K,V> m_next;

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
        m_table = (Entry<K,V>[]) new Entry[2];
    }
    /** Constructs an empty hashmap with the specified initial capacity
     and the default load factor (0.75). */
    public SimpleHashMap(int capacity){
        m_table = (Entry<K,V>[]) new Entry[capacity];
    }

    String show(){
        StringBuilder result = new StringBuilder();

        for (Entry<K, V> aTable : m_table) {
            Entry entry = aTable;
            while (entry != null) {
                result.append(entry);
                result.append(", ");
                entry = entry.m_next;
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
        return m_size == 0;
    }

    @Override
    public V put(K key, V value) {

        if (m_size >= m_table.length * 3 / 4.0) rehash();

        int index = index(key);
        Entry<K,V> oldEntry = find(index, key);

        if (oldEntry == null){
            m_size++;

            if (m_table[index] == null) {
                m_table[index] = new Entry<>(key, value);
                return null;
            }

            Entry<K, V> last = m_table[index];
            while (last.m_next != null){
                last = last.m_next;
            }
            last.m_next = new Entry<>(key, value);
            return null;
        }
        else{
            V old_value = oldEntry.m_value;
            oldEntry.m_value = value;
            return old_value;
        }
    }

    private void rehash(){
        Entry<K,V>[] oldTable = m_table;

        m_table = (Entry<K,V>[]) new Entry[(m_table.length * 2)];
        m_size = 0;

        for (Entry<K, V> list : oldTable) {
            Entry<K, V> entry = list;
            while (entry != null) {
                put(entry.m_key, entry.m_value);
                entry = entry.m_next;
            }
        }
    }

    @Override
    public V remove(Object key) {
        int index = index(key);

        Entry<K,V> cur = m_table[index];

        while (cur != null){
            if (cur.m_key.equals(key)){
                V value = cur.m_value;
                if(m_table[index] == cur) m_table[index] = cur.m_next;
                m_size--;
                return value;
            }
            cur = cur.m_next;
        }

        return null;
    }

    @Override
    public int size() {
        return m_size;
    }


    private int index(Object key){
        int hashCode = key.hashCode();
        return Math.abs(hashCode) % m_table.length;
    }

    private Entry<K,V> find(int index, K key){
        Entry<K,V> entry = m_table[index];
        while (entry != null && !entry.m_key.equals(key)) entry = entry.m_next;
        return entry;
    }
}
