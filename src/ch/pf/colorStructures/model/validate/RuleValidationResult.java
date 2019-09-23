package ch.pf.colorStructures.model.validate;

import ch.pf.colorStructures.model.Cell;
import ch.pf.colorStructures.model.rules.IRule;

/**
 * Represents the Result of a {@link Cell} that was validated against one {@link IRule}

 */
public class RuleValidationResult {

	public final Class<? extends IRule> origin;
	public final boolean passed;
	public final Cell cell;

	/**
	 * @param origin The {@link IRule} that was used to produce this Result
	 * @param passed true if the {@link IRule} was not violated
	 * @param cell the {@link Cell} that was validated
	 */
	public RuleValidationResult(Class<? extends IRule> origin, boolean passed,
			Cell cell) {
		if (origin == null) {
			throw new IllegalArgumentException( RuleValidationResult.class.getName() + " must have a origin");
		}
		if (cell == null) {
			throw new IllegalArgumentException(RuleValidationResult.class.getName() + " must have a cell");
		}
		this.origin = origin;
		this.passed = passed;
		this.cell = cell;
	}
}
