package telran.util.test;

import java.util.Comparator;

public class EvenOddComparator implements Comparator<Integer> {

	@Override
	public int compare(Integer o1, Integer o2) {
		//  {-8, 2, 10, 100, 47, 13, 7}
		int variant = (o1 % 2) * 10 + o2 % 2;
		int res = 0;
		switch (variant) {
			case 0:  res = o1 - o2; break;
			case 1:  res = -1; break;
			case 10: res = 1; break;
			case 11: res = o2 - o1; break;
		}
		return res;
	}

}
