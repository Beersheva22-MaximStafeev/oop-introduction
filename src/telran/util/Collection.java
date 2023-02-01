package telran.util;

import java.util.Arrays;
import java.util.Iterator;
import java.util.Random;
import java.util.function.*;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

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
	
	default Stream<T> stream() {
		return StreamSupport.stream(this.spliterator(), false);
	}
	
	default Stream<T> parappelStream() {
		return StreamSupport.stream(this.spliterator(), true);
	}
	
	default T[] toArrayShufling(T[] array) {
		// TO DO
		// return array with collection elements in shuffled order
		T[] ar = toArray(array);
		return new Random().ints(0, size()).distinct().limit(size()).mapToObj(num -> ar[num]).toArray(n -> ar);
	}
}
