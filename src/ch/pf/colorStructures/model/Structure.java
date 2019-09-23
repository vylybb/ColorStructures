package ch.pf.colorStructures.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * A {@link Structure} consists one or more {@link Cell}s that have the same {@link Cell.CellColor}
 * Each {@link Cell} of the {@link Structure} shares at least one edge with another {@link Cell} of the same {@link Structure}
 *      
 * @author P.Flury@gmx.ch
 *
 */
public class Structure implements IShape {

	private final Cell.CellColor color;
	private final Cell[] cells;

	public Structure(Cell... cells) {
		if (cells == null || cells.length < 1) {
			throw new IllegalArgumentException("A Structure must at least contain one Cell");
		}
		for (Cell c : cells) {
			if (c.getColor() != cells[0].getColor()) {
				throw new IllegalArgumentException("All colors of cells of a Structure must be the same");
			}
		}
		this.cells = cells;
		this.color = cells[0].getColor();
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("Structure [");
		for (Cell c : getCells()) {
			sb.append("(" + c.toString() + ")");
		}
		sb.append("]");
		return sb.toString();
	}

	public Cell[] getCells() { return cells; }
	
	public Cell.CellColor getColor() { return color; }

	public int countCells() { return cells.length; }
	
	public boolean containsCell(Cell cell) {
		for (Cell c : cells) {
			if (c.equals(cell)) {
				return true;
			}
		}
		return false;
	}
	
	/**
	 * Returns if the {@link Structure} is vertical or hoicontal
	 * @return
	 */
	public boolean isLinear() {
		return isVertical() || isHorizontal();
	}
	
	public boolean isVertical() {
		for (int i = 1; i < cells.length; i++) {// we do know that we got at least one cell
			if (cells[0].getXCoordinate() != cells[i].getXCoordinate()) {
				return false;
			}
		}
		return true;
	}
	
	public boolean isHorizontal() {
		for (int i = 1; i < cells.length; i++) {// we do know that we got at least one cell
			if (cells[0].getYCoordinate() != cells[i].getYCoordinate()) {
				return false;
			}
		}
		return true;
	}

	@Override
	public boolean isAdjacentTo(Cell[] cells) {
		for (Cell innerCell : this.cells) {
			if (innerCell.isAdjacentTo(cells)) {
				return true;
			}
		}
		return false;
	}

	/**
	 * Fuses two {@link Structure} together. They must be adjacent to each other
	 * @param s
	 * @return
	 */
	public Structure merge(Structure s) {
		if(!s.isAdjacentTo(this.getCells())) {
			throw new IllegalStateException("An attempt was made to merge a structure with another that is not adjacent to it");
		}
		List<Cell> newStructureCells = new ArrayList<Cell>();
		Collections.addAll(newStructureCells, this.getCells());
		Collections.addAll(newStructureCells, s.getCells());
		return new Structure(newStructureCells.toArray(new Cell[newStructureCells.size()])); // constructor handles possible errors
	}

}
