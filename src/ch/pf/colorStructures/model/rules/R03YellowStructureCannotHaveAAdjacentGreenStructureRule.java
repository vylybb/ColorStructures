package ch.pf.colorStructures.model.rules;

import ch.pf.colorStructures.model.validate.RuleValidationResult;
import ch.pf.colorStructures.model.Cell;
import ch.pf.colorStructures.model.Grid;
import ch.pf.colorStructures.model.Structure;
import ch.pf.colorStructures.model.Cell.CellColor;

/**
 * Rule 03
 * Yellow structure cannot have an adjacent green structure.

 */
public class R03YellowStructureCannotHaveAAdjacentGreenStructureRule implements IRule {

	@Override
	public RuleValidationResult validate(Cell cell, Grid grid) {
		if (cell.getColor() == CellColor.YELLOW) {
			Structure yellowStructure = grid.getStructureOfCell(cell);
			for (Structure st : grid.getAdjacentStructures(yellowStructure)) {
				if (st.getColor() == CellColor.GREEN) {
					return new RuleValidationResult(getClass(), false, cell);
				}
			}
		}
		return new RuleValidationResult(getClass(), true, cell);
	}
}
