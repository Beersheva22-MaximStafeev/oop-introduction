package telran.util;

import java.util.Arrays;
import java.util.Iterator;
import java.util.function.*;

//import telran.util.LinkedList.Node;

public interface Collection<T>  extends Iterable<T> {
	
	boolean add (T element);
	boolean remove(T pattern);
	boolean isEmpty();
	int size();
	boolean contains(T pattern);
	
	default boolean removeIf(Predicate<T> predicate) {
		Iterator<T> it = iterator();
		int oldSize = size();
		while (it.hasNext()) {
			T obj = it.next();
			if (predicate.test(obj)) {
				it.remove();
			}
		}
		return oldSize > size();
	}
	
	/*
	 * @param array
	 * @return array containing elements of a Collection
	 * if ar refers to memory that enough for all elements of Collection 
	 * 		then new array array not created, fills in ar
	 * otherwise created new array 
	 * 
	 * if ar refers to memory that is greater than required for all elements of Collection
	 * 	than all elements of Collection will be put in the array and rest of memory will be null
	 */
//	T[] toArray(T[] ar);
	default T[] toArray(T[] ar) {
		if (ar.length < size()) {
			ar = Arrays.copyOf(ar, size());
		}
		Iterator<T> it = iterator();
		int index = 0;
		while (it.hasNext()) {
			ar[index++] = it.next();
		}
		Arrays.fill(ar, size(), ar.length, null);
		return ar;
	}
	
}
