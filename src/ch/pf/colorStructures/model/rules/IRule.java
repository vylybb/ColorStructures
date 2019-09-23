package ch.pf.colorStructures.model.rules;

import ch.pf.colorStructures.model.validate.RuleValidationResult;
import ch.pf.colorStructures.model.Cell;
import ch.pf.colorStructures.model.Grid;

/**
 * A rule always validates one single cell (not a Structure) inside a Grid. 
 * (This makes the interface as powerfull as possible)
 * 
 * @author lianchu
 * 
 */
public interface IRule {
	public RuleValidationResult validate(Cell cell, Grid grid);
}
