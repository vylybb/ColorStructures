package ch.pf.colorStructures.model.rules;

import ch.pf.colorStructures.model.validate.RuleValidationResult;
import ch.pf.colorStructures.model.Cell;
import ch.pf.colorStructures.model.Grid;
import ch.pf.colorStructures.model.Cell.CellColor;

/**
 * Rule 06:
 * There are no more than two blue structures in the grid.

 */
public class R06NoMoreThan2BlueStructuresRule implements IRule {

	@Override
	public RuleValidationResult validate(Cell cell, Grid grid) {
		if (cell.getColor() == CellColor.BLUE) {
			return new RuleValidationResult(getClass(), grid.getStructuresByColor(CellColor.BLUE).length <= 2, cell);
		}
		return new RuleValidationResult(getClass(), true, cell);
	}
}
