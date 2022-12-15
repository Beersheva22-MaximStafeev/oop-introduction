package telran.shapes.test;

import telran.shapes.*;
import org.junit.jupiter.api.*;

public class ShapesTests {
	
	void canvasTest1() {
		Shape.setBackground(".");
		Rectangle rectangle = new Rectangle(50, 8);
		Square square = new Square(12);
		SquareLeftTriangle squareLeftTriangle = new SquareLeftTriangle(23);
		SquareRightTriangle squareRightTriangle = new SquareRightTriangle(70);

		Shape[] shapes = { rectangle, square, squareLeftTriangle, squareRightTriangle };

		Canvas canvas = new Canvas(10, 21, shapes);

		canvas.setDirection("column");
		canvas.setMargin(1);
		displayStrings(canvas.presentation(5));
	}

	
	void canvasTest2() {
		Shape.setBackground(".");
		Rectangle rectangle = new Rectangle(50, 8);
		Square square = new Square(12);
		SquareLeftTriangle squareLeftTriangle = new SquareLeftTriangle(23);
		SquareRightTriangle squareRightTriangle = new SquareRightTriangle(70);

		Shape[] shapes = { rectangle, square, squareLeftTriangle, squareRightTriangle };

		Canvas canvas = new Canvas(10, 21, shapes);

		canvas.setHeight(13);
		canvas.setDirection("row");
		canvas.setMargin(4);
		displayStrings(canvas.presentation(12));
	}
	
	@Test
	void canvasTest3() {
		Shape.setBackground(".");
		Rectangle rectangle = new Rectangle(50, 8);
		Square square = new Square(12);
		SquareLeftTriangle squareLeftTriangle = new SquareLeftTriangle(23);
		SquareRightTriangle squareRightTriangle = new SquareRightTriangle(70);
		Shape[] tris = {squareLeftTriangle, squareRightTriangle};
		Canvas trisCanvas = new Canvas(15, 16, tris);
		trisCanvas.setMargin(1);

		Shape[] shapes = { rectangle, square, trisCanvas };

		Canvas canvas = new Canvas(10, 21, shapes);

		canvas.setHeight(13);
		canvas.setDirection("row");
		canvas.setMargin(4);
		displayStrings(canvas.presentation(12));
		
		canvas.setDirection("column");
		canvas.setWidth(7);
		canvas.setMargin(2);
		displayStrings(canvas.presentation(12));
	}

	private void displayStrings(String[] strings) {
		for (String str: strings) {
			System.out.println(str);
		}
	}
}
