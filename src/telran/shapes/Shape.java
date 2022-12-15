package telran.shapes;

public abstract class Shape {
	public static final String SYMBOL = "*"; // это константа, которая вида всем и в статике
	public static final String BACKGROUND = " "; // это константа, которая вида всем и в статике
	private int width;
	private int height;
	protected static String symbol = SYMBOL; 
	protected static String background = BACKGROUND; 
	// это статическая переменная, которую можно изменить и 
	// её использование в экземпляре объекта всегда одинаково для всех экземпляров 
	
	public static String getSymbol() {
		return symbol;
	}

	public static void setSymbol(String symbol) {
		Shape.symbol = symbol;
	}

	public static String getBackground() {
		return background;
	}

	public static void setBackground(String background) {
		Shape.background = background;
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

	public Shape(int width, int height) {
		this.width = width;
		this.height = height;
	}
	
	abstract public String[] presentation(int offset);
	
}
