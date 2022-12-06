package telran.shapes;

public class SquareTriangle extends Square {
	private boolean isLeftDiagonal;

	protected SquareTriangle(int size, boolean isLeftDiagonal) {
		super(size);
		this.isLeftDiagonal = isLeftDiagonal;
	}
	
	public String[] presentation(int offset) {
		String[] res = new String[getHeight()];
		int upOffset;
		int diagonalIncrement;
		if (isLeftDiagonal) {
			upOffset = 0;
			diagonalIncrement = 0;
		} else {
			upOffset = getWidth() - 1;
			diagonalIncrement = -1;
		}
		res[0] = getSpaces(offset + upOffset) + getSymbol() + getSpaces(getWidth() - upOffset - 1);
		res[res.length - 1] = getSpaces(offset) + getSymbol().repeat(getWidth());
		upOffset = upOffset + diagonalIncrement;
		for (int i = 1; i < res.length - 1; i++) {
			res[i] = getSpaces(offset + upOffset) + getSymbol() + getSpaces(i-1) + getSymbol() + getSpaces(getWidth() - upOffset - i - 1);
			upOffset = upOffset + diagonalIncrement;
		}
		return res;
	}
	
	private String getSpaces(int count) {
		return getBackground().repeat(count);
	}
}
