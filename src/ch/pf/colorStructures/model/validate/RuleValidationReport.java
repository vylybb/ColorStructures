package ch.pf.colorStructures.model.validate;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import ch.pf.colorStructures.model.Cell;
import ch.pf.colorStructures.model.Grid;

/**
 * Represents the Result of the validation of a {@link Grid}
 * and provides information about its outcome.
 * 
 * Consists of several {@link RuleValidationResult}s
 * **/
public class RuleValidationReport {

	private final Map<Cell, List<RuleValidationResult>> mapCellToSucessfullResults = new HashMap<Cell, List<RuleValidationResult>>();
	private final Cell[] cells;

	/**
	 * 
	 * @param cells must not be null
	 * @param results must not be null
	 */
	public RuleValidationReport(Cell[] cells, RuleValidationResult[] results) {
		if (cells == null) {
			throw new IllegalArgumentException("cells must not be null");
		}
		if (results == null) {
			throw new IllegalArgumentException("results must not be null");
		}
		this.cells = cells;
		for (RuleValidationResult r : results) {
			if (mapCellToSucessfullResults.get(r.cell) == null) {
				mapCellToSucessfullResults.put(r.cell, new ArrayList<RuleValidationResult>());
			}
			mapCellToSucessfullResults.get(r.cell).add(r);
		}
	}

	public Cell[] getAnalyzedCellCount() {
		return cells;
	}

	/**
	 * Returns all {@link RuleValidationResult}s that belong to a given {@link Cell}
	 * @param cell
	 * @return
	 */
	public RuleValidationResult[] getResultsOfcell(Cell cell) {
		return mapCellToSucessfullResults.get(cell).toArray(new RuleValidationResult[mapCellToSucessfullResults.size()]);
	}

	/**
	 * Returns all {@link RuleValidationResult}s that belong to a given {@link Cell}
	 * and have violated a {@link IRule}
	 * @param cell
	 * @return
	 */
	public RuleValidationResult[] getInsuccessfullResultsOfcell(Cell cell) {
		return getFilteredResultsOfCell(cell, false);
	}

	/**
	 * Returns all {@link RuleValidationResult}s that belong to a given {@link Cell}
	 * and have not violated a {@link IRule}
	 */
	public RuleValidationResult[] getSucessfullResultsOfcell(Cell cell) {
		return getFilteredResultsOfCell(cell, true);
	}

	
	private RuleValidationResult[] getFilteredResultsOfCell(Cell cell, boolean passed) {
		List<RuleValidationResult> result = new ArrayList<RuleValidationResult>();
		for (RuleValidationResult res : mapCellToSucessfullResults.get(cell)) {
			if (res.passed == passed) {
				result.add(res);
			}
		}
		return result.toArray(new RuleValidationResult[result.size()]);
	}
	/**
	 * Returns the number of {@link RuleValidationResult}
	 * @return
	 */
	public int getResultSize() {
		return mapCellToSucessfullResults.size();
	}
}
