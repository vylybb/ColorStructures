package ch.pf.colorStructures.model.rules;

import ch.pf.colorStructures.model.validate.RuleValidationResult;
import ch.pf.colorStructures.model.Cell;
import ch.pf.colorStructures.model.Grid;
import ch.pf.colorStructures.model.Structure;
import ch.pf.colorStructures.model.Cell.CellColor;

/**
 * Rule 01:
 * Green structure always has an adjacent blue structure.

 */
public class R01GreenStructureHasAdjacentBlueStructureRule implements IRule {

	@Override
	public RuleValidationResult validate(Cell cell, Grid grid) {
		if (cell.getColor() == CellColor.GREEN) {
			Structure greenStructure = grid.getStructureOfCell(cell);
			for (Structure s : grid.getAdjacentStructures(greenStructure)) {
				if (s.getColor() == CellColor.BLUE) {
					return new RuleValidationResult(getClass(), true, cell);
				}
			}
			return new RuleValidationResult(getClass(), false, cell);
		}
		return new RuleValidationResult(getClass(), true, cell);
	}
}
