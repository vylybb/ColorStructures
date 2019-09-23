package ch.pf.colorStructures.model.rules;

import ch.pf.colorStructures.model.validate.RuleValidationResult;
import ch.pf.colorStructures.model.Cell;
import ch.pf.colorStructures.model.Grid;
import ch.pf.colorStructures.model.Structure;

/**
 * Rule 07:
 * For each color, average number of cells per structure is less than 5.

 */
public class R07AvarageCellPerStuctureAndColorLess5Rule implements IRule {

	@Override
	public RuleValidationResult validate(Cell cell, Grid grid) {
		Structure[] structures = grid.getStructuresByColor(cell.getColor());
		double sum = 0;
		for (Structure str : structures) {
			sum += str.countCells();
		}
		return new RuleValidationResult(getClass(), (sum / structures.length) < 5, cell);
	}
}
