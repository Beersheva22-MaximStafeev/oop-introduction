package telran.util;

import java.util.Objects;

public interface Map<K, V> {
	// for tree class Entry should be comparable by 'key'
	// in the tree should be comparator or key should be comparable
	class Entry<K, V> implements Comparable<Entry<K, V>> {
		private K key;
		private V value;

		@Override
		public int hashCode() {
			return Objects.hash(key);
		}

		@SuppressWarnings("rawtypes")
		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			Entry other = (Entry) obj;
			return Objects.equals(key, other.key);
		}
		
		public V getValue() {
			return value;
		}

		public void setValue(V value) {
			this.value = value;
		}

		public K getKey() {
			return key;
		}

		@SuppressWarnings({ "unchecked" })
		@Override
		public int compareTo(Entry<K, V> o) {
			return ((Comparable<K>)key).compareTo(o.key);
		}

		public Entry(K key, V value) {
			super();
			this.key = key;
			this.value = value;
		}
	}
	
	/**
	 * adds new or updates existing entry 
	 * @param key
	 * @param value
	 * @return null, if adding new returning 
	 * @return  old value (reference), if pair exists in map with this key, so, we changing value
	 *  
	 */
	V put(K key, V value);
	
	/**
	 * like put, but doesn't changing value, if exists
	 * @param key
	 * @param value
	 * @return null, if adding new returning
	 * @return reference to existing value, if entry exists
	 */
	V putIfAbsent(K key, V value);
	
	/********************************************************************************/
	/**
	 * 
	 * @param key
	 * @return value matching a given key or null, if key doesn't exists
	 */
	V get(K key);
	/**
	 * 
	 * @param key
	 * @param value
	 * @return value if key exists otherwise 
	 */
	V getOrDefault(K key, V value);
	/********************************************************************************/
	/**
	 * 
	 * @param key
	 * @return true if key exists
	 */
	boolean contanisKey(K key);
	/**
	 * 
	 * @param value
	 * @return true if there is an entry with a given value
	 */
	boolean containsValue(V value);
	/********************************************************************************/
	/**
	 * 
	 * @return collection of all values
	 */
	Collection<V> values();
	/**
	 * 
	 * @return Set of all keys
	 */
	Set<K> keySet();
	/**
	 * 
	 * @return
	 */
	Set<Entry<K, V>> entrySet();
	/********************************************************************************/
	/**
	 * remove entry with a given key
	 * @param key
	 * @return either reference to value of removed entry
	 * @return or null if key doesn't extists   
	 */
	V remove(K key);
}
