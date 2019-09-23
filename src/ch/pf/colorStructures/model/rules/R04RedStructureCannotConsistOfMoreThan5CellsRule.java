package ch.pf.colorStructures.model.rules;

import ch.pf.colorStructures.model.validate.RuleValidationResult;
import ch.pf.colorStructures.model.Cell;
import ch.pf.colorStructures.model.Grid;
import ch.pf.colorStructures.model.Structure;
import ch.pf.colorStructures.model.Cell.CellColor;

/**
 * Rule 04:
 * Red structure cannot consist from more than 5 cells.

 */
public class R04RedStructureCannotConsistOfMoreThan5CellsRule implements IRule {

	@Override
	public RuleValidationResult validate(Cell cell, Grid grid) {
		if (cell.getColor() == CellColor.RED) {
			Structure redStructure = grid.getStructureOfCell(cell);
			return new RuleValidationResult(this.getClass(), redStructure.countCells() <= 5, cell);
		}
		return new RuleValidationResult( R04RedStructureCannotConsistOfMoreThan5CellsRule.class, true, cell);
	}
}
