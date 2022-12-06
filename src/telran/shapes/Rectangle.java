package telran.shapes;

public class Rectangle {
	public static final String SYMBOL = "*"; // это константа, которая вида всем и в статике
	public static final String BACKGROUND = " "; // это константа, которая вида всем и в статике
	private int width;
	private int height;
	private static String symbol = SYMBOL; 
	private static String background = BACKGROUND; 
	// это статическая переменная, которую можно изменить и 
	// её использование в экземпляре объекта всегда одинаково для всех экземпляров 
	
	public static String getSymbol() {
		return symbol;
	}

	public static void setSymbol(String symbol) {
		Rectangle.symbol = symbol;
	}

	public static String getBackground() {
		return background;
	}

	public static void setBackground(String background) {
		Rectangle.background = background;
	}

	public Rectangle(int width, int height) {
		this.width = width;
		this.height = height;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}
	
	public String[] presentation(int offset) {
		String[] res = new String[height];
		String line = getLine(offset);
		int lastLine = height - 1;
		res[0] = line;
		res[lastLine] = line;
		for (int i = 1; i < lastLine; i++) {
			res[i] = getMiddleLine(offset);
		}
		return res;
	}
	
	private String getMiddleLine(int offset) {
		return getOffset(offset) + symbol + getOffset(width - 2) + symbol;
	}

	private String getLine(int offset) {
		return getOffset(offset) + symbol.repeat(width);
	}

	private String getOffset(int offset) {
		return background.repeat(offset);
	}
}
