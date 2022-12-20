package telran.util;

import java.util.Comparator;

public class MyArrays {
	public static void sortUniversal(Object[] objects) {
		// but we cannot pass here int[]
	}

	public static <T> int binarySearch(T[] arraySorted, T number, Comparator<T> comparator) {
		int left = 0;
		int right = arraySorted.length - 1;
		int middle = right / 2;
		while (left <= right && arraySorted[left] != number) {
			if (comparator.compare(number, arraySorted[middle]) <= 0) {
				right = middle - 1;
			} else {
				left = middle + 1;
			}
			middle = (right + left) / 2;
		}
		int res;
		if (left >= arraySorted.length) {
			res = -arraySorted.length - 1;
		} else {
			if (comparator.compare(arraySorted[left], number) == 0) {
				res = left;
			} else {
				res = -left - 1;
			}
		}
		return res;
	}

	public static <T> void sort(T[] objects, Comparator<T> comparator) {
		int length = objects.length;
		do {
			length--;
		} while (moveMaxAtEnd(objects, length, comparator));
	}
	
	private static <T> boolean moveMaxAtEnd(T[] objects, int length, Comparator<T> comparator) {
		boolean wasSwaps = false;
		for (int i = 0; i < length; i++) {
			if (comparator.compare(objects[i], objects[i + 1]) > 0) {
				wasSwaps = true;
				swapElements(objects, i, i+1);
			}
		}
		return wasSwaps;
	}

	public static <T> void sortMy(T[] objects, Comparator<T> comparator) {
		int i = 0; 
		boolean wasSwaps = true;
		while (i + 1 < objects.length && wasSwaps) {
			wasSwaps = false;
			for (int j = 0; j + 1 < objects.length - i; j++) {
				if (comparator.compare(objects[j], objects[j + 1]) > 0) {
					wasSwaps = true;
					swapElements(objects, j, j+1);
				}
			}
			i++;
		}
	}

	private static <T> void swapElements(T[] array, int index1, int index2) {
		if (index1 >= 0 && index2 >= 0 && index1 < array.length && index2 < array.length) {
			T swap = array[index1];
			array[index1] = array[index2];
			array[index2] = swap;			
		}
	}

	public static void bubbleSortIntArray(int[] array) {
		int i = 0; 
		boolean wasSwaps = true;
		while (i + 1 < array.length && wasSwaps) {
			wasSwaps = false;
			for (int j = 0; j + 1 < array.length - i; j++) {
				if (array[j+1] < array[j]) {
					wasSwaps = true;
					swapElementsInIntArray(array, j, j+1);
				}
			}
			i++;
		}
	}

	private static void swapElementsInIntArray(int[] array, int index1, int index2) {
		if (index1 >= 0 && index2 >= 0 && index1 < array.length && index2 < array.length) {
			int swap = array[index1];
			array[index1] = array[index2];
			array[index2] = swap;			
		}
	}
}
