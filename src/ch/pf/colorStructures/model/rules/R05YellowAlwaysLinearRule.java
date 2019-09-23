package ch.pf.colorStructures.model.rules;

import ch.pf.colorStructures.model.validate.RuleValidationResult;
import ch.pf.colorStructures.model.Cell;
import ch.pf.colorStructures.model.Grid;
import ch.pf.colorStructures.model.Structure;
import ch.pf.colorStructures.model.Cell.CellColor;

/**
 * Rule 05
 * Yellow structure is always linear, i.e. all cells that form the structure are on a single horizontal or vertical line.

 */
public class R05YellowAlwaysLinearRule implements IRule {

	@Override
	public RuleValidationResult validate(Cell cell, Grid grid) {
		if (cell.getColor() == CellColor.YELLOW) {
			Structure yellowStucture = grid.getStructureOfCell(cell);
			return new RuleValidationResult(getClass(), yellowStucture.isLinear(), cell);
		} else {
			return new RuleValidationResult(getClass(), true, cell);
		}
	}
}
