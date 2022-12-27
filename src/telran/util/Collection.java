package telran.util;

import java.util.function.*;

public interface Collection<T> {
	
	boolean add (T element);
	boolean remove(T pattern);
	boolean removeIf(Predicate<T> predicate);
	boolean isEmpty();
	int size();
	boolean contains(T pattern);
	
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
	T[] toArray(T[] ar);
}
