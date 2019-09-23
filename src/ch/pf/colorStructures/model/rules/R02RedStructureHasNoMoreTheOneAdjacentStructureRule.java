package ch.pf.colorStructures.model.rules;

import ch.pf.colorStructures.model.validate.RuleValidationResult;
import ch.pf.colorStructures.model.Cell;
import ch.pf.colorStructures.model.Grid;
import ch.pf.colorStructures.model.Structure;
import ch.pf.colorStructures.model.Cell.CellColor;

/**
 * Rule 02:
 * Red structure has no more than one another adjacent structure.

 */
public class R02RedStructureHasNoMoreTheOneAdjacentStructureRule implements IRule {

	@Override
	public RuleValidationResult validate(Cell cell, Grid grid) {
		if (cell.getColor() == CellColor.RED) {
			Structure redStructure = grid.getStructureOfCell(cell);
			return new RuleValidationResult(getClass(), grid.getAdjacentStructures(redStructure).length <= 1, cell);
		}
		return new RuleValidationResult(getClass(), true, cell);
	}
}
