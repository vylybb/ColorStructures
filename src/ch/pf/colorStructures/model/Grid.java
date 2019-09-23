package ch.pf.colorStructures.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import ch.pf.colorStructures.model.rules.IRule;
import ch.pf.colorStructures.model.validate.RuleValidationReport;
import ch.pf.colorStructures.model.validate.RuleValidationResult;

/**
 * A {@link Grid} consists of {@link Cell}s, {@link IRule}s and {@link Structure}s
 * 
 * It has slots that can be filled with {@link Cell}s or can remain empty
 * Theoretically a {@link Grid} is infinite in width and height
 * 
 * The {@link Cells} can be grouped into {@link Structure}s (See {@link Structure}s for details)
 * 
 * Its purpose is to provide a place, where its {@link Cell}s can be validated against its {@link IRule}s
 * To reach this, {@link Structure}s are used.
 * The {@link RuleValidationReport} represents the result of such a validation and can be generated via the {@link validate()} - method.
 * 
 * Typical usage:
 * 1. Set Rules and Cells
 * 2. create Structures
 * 3. validate
 * 
 * A {@link Grid} can be used several times
**/

public class Grid {

	private Cell[] cells;
	private IRule[] rules;
	private final List<Structure> structures = new ArrayList<Structure>();

	public Grid() {
		//nothing to do
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		for (Structure st : structures) {
			sb.append(st.toString() + "/");
		}
		return sb.toString();
	}

	public Cell[] getCells() { return cells; }
	
	/**
	 * Clears all already generated {@link Structure}s
	 * cells must not be null
	 * 
	 * @param cells
	 */
	public void setCells(Cell[] cells) {
		if (cells == null) {
			throw new IllegalArgumentException("Cells must not be null");
		}
		this.cells = cells;
		structures.clear();
	}
	
	/**
	 * Returns all {@link Cells} of the chosen {@link Cell.CellColor}
	 * @param cellColor must not be null
	 * @return
	 */
	public Cell[] getCellsByColor(Cell.CellColor cellColor) {
		List<Cell> result = new ArrayList<Cell>();
		for (Cell c : cells) {
			if (c.getColor().equals(cellColor)) {
				result.add(c);
			}
		}
		return result.toArray(new Cell[result.size()]);
	}

	public IRule[] getRules() { return rules; }
	
	/**
	 * Rules must not be null
	 * 
	 * @param rules must not be null
	 */
	public void setRules(IRule[] rules) {
		if (rules == null) {
			throw new IllegalArgumentException("Rules must not be null");
		}
		this.rules = rules;
	}

	public Structure[] getStructures() { return structures.toArray(new Structure[structures.size()]); }
	
	/**
	 * Returns the {@link Structure} that belongs to a {@link Cell}
	 * A Cell can only part of one @link Structure}
	 * @param cell
	 * @return
	 */
	public Structure getStructureOfCell(Cell cell) {
		for (Structure s : getStructures()) {
			if (s.containsCell(cell)) {
				return s;
			}
		}
		return null;
	}
	
	/**
	 * Returns all {@link Structure}s of a given {@link Cell.CellColor}
	 * @param color
	 * @return
	 */
	public Structure[] getStructuresByColor(Cell.CellColor color) {
		List<Structure> result = new ArrayList<Structure>();
		for (Structure s : getStructures()) {
			if (color == s.getColor()) {
				result.add(s);
			}
		}
		return result.toArray(new Structure[result.size()]);
	}
	
	/**
	 * Finds all adjacent {@link Structure} to a given {@link Structure}
	 * @param structure
	 * @return
	 */
	public Structure[] getAdjacentStructures(Structure structure) {
		List<Structure> result = new ArrayList<Structure>();

		for (Structure s : getStructures()) {
			if (s != structure && s.isAdjacentTo(structure.getCells())) {
				result.add(s);
			}
		}
		return result.toArray(new Structure[result.size()]);
	}

	/**
	 * Causes the {@link Grid} to build {@link Structure} out of its {@link Cell}s
	 */
	public void generateStructures() {
		this.structures.clear();
		List<Structure> result = new ArrayList<Structure>();
		for (Cell.CellColor cc : Cell.CellColor.values()) {
			Cell[] cellsOfColor = getCellsByColor(cc);
			result.addAll((Arrays.asList(StructureDetector.detect(cellsOfColor))));
		}
		this.structures.addAll(result);
	}

	/**
	 * Structures and Rules may be presente arlady
	 */
	public RuleValidationReport validate() {
		List<RuleValidationResult> results = new ArrayList<RuleValidationResult>();
		for (Cell c : cells) {
			for (IRule r : rules) {
				results.add(r.validate(c, this));
			}
		}
		return new RuleValidationReport(cells, results.toArray(new RuleValidationResult[results.size()]));
	}

}
