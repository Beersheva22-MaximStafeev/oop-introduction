package telran.shapes.test;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

import telran.shapes.*;

class RectangleTestMy {

	@Test
	@Disabled
	void rectangleTest() {
		Rectangle rectangle = new Rectangle(10, 5);
		assertEquals(10,  rectangle.getWidth());
		assertEquals(5,  rectangle.getHeight());
		displayStrings(rectangle.presentation(20));
		Rectangle.setSymbol("#");
		displayStrings(rectangle.presentation(20));
	}
	
	@Test
	@Disabled
	void test1() {
		System.out.println("test1: " + Rectangle.getSymbol());
	}

	@Test
	@Disabled
	void test2() {
		Rectangle.setSymbol("0");
		System.out.println("test2: " + Rectangle.getSymbol());
	}

	@Test
	@Disabled
	void test3() {
//		Rectangle rectangle = new Rectangle(10, 5);
		Rectangle.setSymbol("$");
		System.out.println("test3: " + Rectangle.getSymbol());
	}

	private void displayStrings(String[] strings) {
		for (String str: strings) {
			System.out.println(str);
		}
	}
	
	@Test
	@Disabled
	void testLeft() {
		SquareLeftTriangle squareLeftTriangle = new SquareLeftTriangle(10);
		displayStrings(squareLeftTriangle.presentation(3));
	}
	
	@Test
	void testRight() {
		SquareRightTriangle squareRightTriangle = new SquareRightTriangle(10);
		displayStrings(squareRightTriangle.presentation(0));
	}
}
