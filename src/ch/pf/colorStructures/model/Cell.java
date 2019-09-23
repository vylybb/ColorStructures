package ch.pf.colorStructures.model;

/**
 * A {@link Cell} is to be found inside a {@link Grid}
 * It consists of Coordinates (2D), a color and a comment.
 * 
 * This class is immutable

 */
public final class Cell implements IShape {

	public enum CellColor {
		RED, YELLOW, GREEN, BLUE;

		public String toString() {
			switch (this) {
			case RED: return "R";
			case YELLOW: return "Y";
			case GREEN: return "G";
			case BLUE: return "B";
			default: throw new IllegalStateException("not tostring implemented for color " + this.name());
			}
		};
	};

	private final int xCoordinate;
	private final int yCoordinate;
	private final CellColor color;
	private final String comment; // may be null

	public Cell(int xCoordinate, int yCoordinate, CellColor color, String comment) {
		if (color == null) {
			throw new IllegalArgumentException("Color must not be null");
		}
		this.xCoordinate = xCoordinate;
		this.yCoordinate = yCoordinate;
		this.color = color;
		this.comment = comment; // may be null
	}

	@Override
	public String toString() {
		return "X:" + xCoordinate + "  Y: " + yCoordinate + "  C:" + color;
	}

	public int getXCoordinate() { return xCoordinate; }

	public int getYCoordinate() { return yCoordinate; }

	public CellColor getColor() { return color; }

	public String getComment() { return comment; }

	@Override
	public boolean isAdjacentTo(Cell[] cells) {
		for (Cell c : cells) {
			if (	getXCoordinate() == c.getXCoordinate() && 
					(getYCoordinate() + 1 == c.getYCoordinate() || getYCoordinate() - 1 == c.getYCoordinate())) {
				return true;
			}
			if (	getYCoordinate() == c.getYCoordinate() && 
					(getXCoordinate() + 1 == c.getXCoordinate() || getXCoordinate() - 1 == c.getXCoordinate())) {
				return true;
			}
		}
		return false;
	}
}
