package telran.util;

public class AbstractMap<K, V> implements Map<K, V> {
	protected Set<Entry<K, V>> set;

	@Override
	public V put(K key, V value) {
		V res = null;
		Entry<K, V> entry = set.get(new Entry<>(key, null));
		if (entry != null) {
			res = entry.getValue();
			entry.setValue(value);
		} else {
			set.add(new Entry<>(key, value));
		}
		return res;
	}

	@Override
	public V get(K key) {
		V res = null;
		Entry<K, V> entry = set.get(new Entry<>(key, null));
		if (entry != null) {
			res = entry.getValue();
		}
		return res;
	}

	@Override
	public boolean contanisKey(K key) {
		return set.contains(new Entry<>(key, null)) ;
	}

	@Override
	public boolean containsValue(V value) {
		return set.stream().anyMatch(el -> isEqual(el.getValue(), value));
	}

	@Override
	public Collection<V> values() {
		List<V> res = new ArrayList<>();
		set.forEach(el -> res.add(el.getValue()));
		return res;
	}

	@Override
	public Set<K> keySet() {
		try {
			@SuppressWarnings("unchecked")
			Set<K> res = set.getClass().getConstructor().newInstance();
			set.forEach(el -> res.add(el.getKey()));
			return res;
		} catch (Exception e) {
			throw new IllegalStateException();
		}
	}

	@Override
	public Set<Entry<K, V>> entrySet() {
		try {
			@SuppressWarnings("unchecked")
			Set<Entry<K, V>> res = set.getClass().getConstructor().newInstance();
			set.forEach(res::add);
			return res;
		} catch (Exception e) {
			throw new IllegalStateException();
		}
	}

	@Override
	public V remove(K key) {
		Entry<K, V> res = set.get(new Entry<>(key, null));
		V obj = null;
		if (res != null) {
			obj = res.getValue(); 
			set.remove(res);
		}
		return obj;
	}

	private boolean isEqual(V element1, V element2) {
		return element1 == null ? element1 == element2 : element1.equals(element2);
	}
	
}
