package telran.util;

import java.util.Arrays;
import java.util.Comparator;
import java.util.function.Predicate;

public class MyArrays {
	public static void sortUniversal(Object[] objects) {
		// but we cannot pass here int[]
	}

	public static <T> int binarySearch(T[] arraySorted, T number, Comparator<T> comparator) {
		int left = 0;
		int right = arraySorted.length - 1;
		int middle = right / 2;
		while (left <= right && comparator.compare(arraySorted[left], number)  != 0) {
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

	public static<T> T[] filter(T[] array, Predicate<T> predicate) {
		int countPredicate = getCountPredicate(array, predicate);
		T[] res = Arrays.copyOf(array, countPredicate);

		int index = 0;
		for(T element: array) {
			if(predicate.test(element)) {
				res[index++] = element;
			}
		}
		return res;
	}
	
	private static <T> int getCountPredicate(T[] array, Predicate<T> predicate) {
		int res = 0;

		for(T element: array) {
			if(predicate.test(element)) {
				res++;
			}
		}
		return res;
	}
	
	public static <T> T[] removeIf(T[] array, Predicate<T> predicate) {
		//one code line with no additional methods
		return filter(array, predicate.negate());
	}
	
	public static <T> T[] removeRepeated(T[] array) {
		//try to write this method based on removeIf
		T[] res = Arrays.copyOf(array, array.length);
		T[] middle = null;
		int finalLength = array.length;
		for (int index = 0; index < finalLength - 1; index++) {
			T el = res[index];
			middle = Arrays.copyOfRange(res, index + 1, finalLength);
			middle = removeIf(middle, a -> a.equals(el));
			System.arraycopy(middle, 0, res, index + 1, middle.length);
			finalLength = index + middle.length + 1;
		}
		return Arrays.copyOf(res, finalLength);
	}
	
	public static <T> T[] removeRepeatedNew(T[] array) {
		final Object[] helper = new Object[array.length];
		final int index[] = {0};
		return removeIf(array, element -> {
			boolean res = true;
			if (!contains(helper, element)) {
				helper[index[0]++] = element;
				res = false;
			}
			return res;
		});
	}

	public static <T> boolean contains(T[] array, T pattern) {
		// returns true if element equaled to pattern exists in array
		boolean res = false;
		int index = 0;
		while (index < array.length && !res) {
			res = pattern == null ? array[index] == null : pattern.equals(array[index]);
			index++;
		}
		return res;
	}

	public static <T> boolean containsNew(T[] array, T pattern) {
		// returns true if element equaled to pattern exists in array
		int index = 0;
		while (index < array.length && isEqual(pattern, array[index])) {
			index++;
		}
		return index < array.length;
	}

	private static <T> boolean isEqual(T el1, T el2) {
		// TO DO Auto-generated method stub
		return el1 == null ? el1 == el2: el1.equals(el2);
	}
	
	public static <T> String join(T[] array, String delimeter) {
		String res = "";
		if (array.length > 0) {
			StringBuilder builder = new StringBuilder(array[0].toString());
			for (int index = 1; index < array.length; index++) {
				builder.append(delimeter).append(array[index]);
			}
			res = builder.toString();
		}
		return res;
	}

	public static <T> String joinString(T[] array, String delimeter) {
		String res = "";
		if (array.length > 0) {
			res = array[0].toString();
			for (int index = 1; index < array.length; index++) {
				res += delimeter + array[index].toString();
			}
		}
		return res;
	}

}
