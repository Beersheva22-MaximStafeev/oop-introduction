package telran.reqursion.tests;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.NoSuchElementException;

import org.junit.jupiter.api.*;
import telran.reqursion.*;

public class MdArrayTest {
	MdArray<Integer> arInt;
	int[] dimInt = {10, 5, 7};
	int intValue = 7;

	MdArray<String> arStr;
	int[] dimStr = {3, 2, 1};
	String strValue = "Tst";
	
	
	@BeforeEach
	void setUp() {
		 arInt = new MdArray<>(dimInt, intValue);
		 arStr = new MdArray<>(dimStr, strValue);
	}
	
	@Test
	void mdArrayEmptyTest() {
		int[] dimensions = {};
		MdArray<Integer> mdArray = new MdArray<>(dimensions, 20);
		assertEquals(20, mdArray.getValue(dimensions));
		mdArray.setValue(new int[] {}, 30);
		assertEquals(30, mdArray.getValue(dimensions));
		assertThrowsExactly(NoSuchElementException.class, () -> mdArray.getValue(new int[] {0}));
	}
	
	@Test
	void forEachTest() {
		Integer[] helpInt = {0, 1, 1}; 
		Arrays.stream(dimInt).forEach(num -> helpInt[1] *= num);
		Arrays.stream(dimStr).forEach(num -> helpInt[2] *= num);

		arInt.forEach(num -> helpInt[0] += num);
		assertEquals(helpInt[1] * intValue, helpInt[0]);
		helpInt[0] = 0;
		arStr.forEach(s -> helpInt[0] += s.length());
		assertEquals(helpInt[2] * strValue.length(), helpInt[0]);
	}
	
	@Test
	void getTest() {
		assertEquals(intValue, arInt.getValue(new int[]{3,4,6}));
		assertThrowsExactly(IllegalStateException.class, () -> arInt.getValue(new int[]{3,4}));
		assertThrowsExactly(NoSuchElementException.class, () -> arInt.getValue(new int[]{3,4,6,0}));
		assertThrowsExactly(ArrayIndexOutOfBoundsException.class, () -> arInt.getValue(new int[]{3,4,7}));
	}
	
	@Test
	void setTest() {
		arInt.setValue(new int[]{3,4,6}, 100);
		assertEquals(100, arInt.getValue(new int[]{3,4,6}));
		assertThrowsExactly(IllegalStateException.class, () -> arInt.setValue(new int[]{3,4}, 100));
		assertThrowsExactly(NoSuchElementException.class, () -> arInt.setValue(new int[]{3,4,6,0}, 100));
		assertThrowsExactly(ArrayIndexOutOfBoundsException.class, () -> arInt.setValue(new int[]{3,4,7}, 100));
	}
	
	@Test
	void toArrayTest() {
		Integer[] helpAr = {0, 1, 1}; 

		Arrays.stream(dimInt).forEach(num -> helpAr[1] *= num);
		assertEquals(350, helpAr[1]);
		Integer[] expectedInt = new Integer[helpAr[1]];
		Arrays.fill(expectedInt, intValue);
		assertArrayEquals(expectedInt, arInt.toArray(new Integer[0]));
		
		arInt.setValue(new int[] {0,0,2}, intValue + 100500);
		assertEquals(intValue + 100500, arInt.getValue(new int[] {0,0,2}));
		expectedInt[2] = intValue + 100500;
		assertArrayEquals(expectedInt, arInt.toArray(new Integer[0]));
		
		arInt.setValue(new int[] {0,1,2}, intValue + 100500);
		assertEquals(intValue + 100500, arInt.getValue(new int[] {0,1,2}));
		expectedInt[dimInt[2] + 2] = intValue + 100500;
		assertArrayEquals(expectedInt, arInt.toArray(new Integer[0]));
		
		Arrays.stream(dimStr).forEach(num -> helpAr[2] *= num);
		assertEquals(6, helpAr[2]);
		String[] expectedStr = new String[helpAr[2]]; 
		Arrays.fill(expectedStr, strValue);
		assertArrayEquals(expectedStr, arStr.toArray(new String[0]));
	}
}
