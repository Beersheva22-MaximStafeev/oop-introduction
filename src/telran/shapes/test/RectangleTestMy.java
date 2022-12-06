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
	void testLeft() {
		Rectangle.setBackground("`");
		int offset = 3;
		int width = 8;
		displayStrings(new String[] {String.format("Left (width: %s, offset: %s):", "" + width, "" + offset)});
		SquareLeftTriangle squareLeftTriangle = new SquareLeftTriangle(width);
		displayStrings(squareLeftTriangle.presentation(offset));
	}
	
	@Test
	void testRight() {
		Rectangle.setBackground("`");
		int offset = 3;
		int width = 6;
		displayStrings(new String[] {String.format("Right (width: %s, offset: %s):", "" + width, "" + offset)});
		SquareRightTriangle squareRightTriangle = new SquareRightTriangle(width);
		displayStrings(squareRightTriangle.presentation(offset));
	}
}
