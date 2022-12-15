package telran.shapes;

public class Canvas extends Shape {
	private Shape[] shapes;
	private String direction = "row"; // can be "column"
	private int margin = 5; // interval between shapes 
	// if in row - height should be equals
	// if in column - width should be equals
	
	public Canvas(int width, int height, Shape[] shapes) {
		super(width, height);
		this.shapes = shapes;                
	}
	
	public String getDirection() {
		return direction;
	}



	public void setDirection(String direction) {
		this.direction = direction;
	}



	public int getMargin() {
		return margin;
	}



	public void setMargin(int margin) {
		this.margin = margin;
	}



	@Override
	public String[] presentation(int offset) {
		int height = getHeight();
		int width = getWidth();
		String[] res = new String[0];
		switch (direction) {
			case "row":
				res = new String[height];
				for (int i = 0; i < res.length; i++) {
					res[i] = "";
				}
				for (int i = 0; i < shapes.length; i++) {
					shapes[i].setHeight(height);
					if (shapes[i] instanceof Canvas) {
						((Canvas)shapes[i]).setDirection(direction);
					}
					res = concatRight(res, shapes[i].presentation(i == 0 ? offset : margin));
				}
				break;
			case "column":
				res = new String[0];
				String[] marginPresentation = getHeightOffset(offset);
				for (int i = 0; i < shapes.length; i++) {
					shapes[i].setWidth(width);
					if (shapes[i] instanceof Canvas) {
						((Canvas)shapes[i]).setDirection(direction);
					}
					res = concatDown(res, shapes[i].presentation(offset));
					if (i != shapes.length - 1) {
						res = concatDown(res, marginPresentation);
					}
				}
				break;
		}
		return res;
	}

	private String[] getHeightOffset(int offset) {
		String[] res = new String[margin];
		String background = getBackground();
		int backgroudWidth = offset + getWidth();
		for (int i = 0; i < res.length; i++) {
			res[i] = background.repeat(backgroudWidth);
		}
		return res;
	}

	private String[] concatDown(String[] up, String[] down) {
		String[] res = new String[up.length + down.length];
		System.arraycopy(up, 0, res, 0, up.length);
		System.arraycopy(down, 0, res, up.length, down.length);
		return res;
	}

	private String[] concatRight(String[] left, String[] right) {
		String[] res = new String[left.length];
		for (int i = 0; i < left.length; i++) {
			res[i] = left[i] + right[i];
		}
		return res;
	}
	
}
