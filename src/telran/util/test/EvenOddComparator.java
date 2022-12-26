package telran.util.test;

import java.util.Comparator;

public class EvenOddComparator implements Comparator<Integer> {

	@Override
	public int compare(Integer o1, Integer o2) {
		//  {-8, 2, 10, 100, 47, 13, 7}
		int variant = Math.abs(o1 % 2) * 10 + Math.abs(o2 % 2);
		int res = 0;
		switch (variant) {
			case 0:  res = Integer.compare(o1,o2); break;
			case 1:  res = -1; break;
			case 10: res = 1; break;
			case 11: res = Integer.compare(o2, o1); break;
		}
		return res;
	}

}
