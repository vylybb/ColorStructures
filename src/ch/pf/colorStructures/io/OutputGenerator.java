package ch.pf.colorStructures.io;

import ch.pf.colorStructures.model.Cell;
import ch.pf.colorStructures.model.validate.RuleValidationReport;
import ch.pf.colorStructures.model.validate.RuleValidationResult;
import ch.pf.colorStructures.model.rules.R01GreenStructureHasAdjacentBlueStructureRule;
import ch.pf.colorStructures.model.rules.R02RedStructureHasNoMoreTheOneAdjacentStructureRule;
import ch.pf.colorStructures.model.rules.R03YellowStructureCannotHaveAAdjacentGreenStructureRule;
import ch.pf.colorStructures.model.rules.R04RedStructureCannotConsistOfMoreThan5CellsRule;
import ch.pf.colorStructures.model.rules.R05YellowAlwaysLinearRule;
import ch.pf.colorStructures.model.rules.R06NoMoreThan2BlueStructuresRule;
import ch.pf.colorStructures.model.rules.R07AvarageCellPerStuctureAndColorLess5Rule;

/**
 * A Tool to convert a {@link RuleValidationReport} into a String
 * 
 * @author Philipp.Flury@gmx.ch
 */
public class OutputGenerator implements OutputStringprovider {

	/**
	 * Converts a {@link RuleValidationReport} into a String
	 * The String consists of several comma-seperated Arguments
	 * A Example might be "7,22,G,No adjacent blue structure, Green structure #1"
	 * @param report
	 * @return
	 */
	public static String generateOutput(RuleValidationReport report) {
		StringBuilder sb = new StringBuilder();
		if (report.getResultSize() == 0) {
			sb.append(NO_RESULTS);
		} else {
			Cell[] cells = report.getAnalyzedCellCount();
			for (int i = 0; i < cells.length; i++) {
				Cell cell = cells[i];
				appendCellMessageArguments(sb, cell);
				sb.append(", ");
				appendStatusArgument(sb, cell, report.getInsuccessfullResultsOfcell(cell));
				sb.append(", ");
				appendCommentArgument(sb, cell);
				if (i != cells.length - 1) {
					sb.append("\n");
				}
			}
		}
		return sb.toString();
	}

	/**
	 * Appends a CellMessageArgument
	 * An Example might be "7,22,G"
	 * @param sb
	 * @param cell
	 */
	private static void appendCellMessageArguments(StringBuilder sb, Cell cell) {
		sb.append(Integer.toString(cell.getXCoordinate()));
		sb.append(",");
		sb.append(Integer.toString(cell.getYCoordinate()));
		sb.append(",");
		sb.append(cell.getColor().toString());
	}

	/**
	 * Appends a StatusArgument
	 * The StatusArguments gives information if a {@link Cell} did pass all rules and if not, which rules were violated.
	 * 
	 * An example might be "No adjacent blue structure"
	 * @param sb
	 * @param cell
	 * @param insuccessfullResults may be empty
	 */
	private static void appendStatusArgument(StringBuilder sb, Cell cell, RuleValidationResult[] insuccessfullResults) {
		if (insuccessfullResults.length == 0) {
			sb.append(OK);
		} else {
			for (int i = 0; i < insuccessfullResults.length; i++) {
				sb.append(getMessageOfInsuccessfullResult(insuccessfullResults[i]));
				if (i != insuccessfullResults.length - 1) {
					sb.append(" / ");
				}
			}
		}
	}

	/**
	 * Returns the text that belongs to a inscuessfull {@link RuleValidationResult}
	 * @param result
	 * @return
	 */
	private static String getMessageOfInsuccessfullResult(RuleValidationResult result) {
		if (result.origin == R01GreenStructureHasAdjacentBlueStructureRule.class) {
			return R01_NO_ADJACENT_BLUE_STRUCTURE;
		} else if (result.origin == R02RedStructureHasNoMoreTheOneAdjacentStructureRule.class) {
			return R02_MORE_THAN_ONE_OTHER_ADJACENT_STRUCTURE;
		} else if (result.origin == R03YellowStructureCannotHaveAAdjacentGreenStructureRule.class) {
			return R03_ADJACENT_GREEN_STRUCTURE_FOUND;
		} else if (result.origin == R04RedStructureCannotConsistOfMoreThan5CellsRule.class) {
			return R04_CANNOT_CONSIST_OF_MORE_THAN_5_CELLS;
		} else if (result.origin == R05YellowAlwaysLinearRule.class) {
			return R05_NOT_LINEAR;
		} else if (result.origin == R06NoMoreThan2BlueStructuresRule.class) {
			return R06_MORE_THAN_TWO_BLUE_STRUCTURES_IN_THE_GRID;
		} else if (result.origin == R07AvarageCellPerStuctureAndColorLess5Rule.class) {
			return R07_AVERAGE_NUMBER_OF_CELLS_PER_STRUCTURE_IS_NOT_LESS_THAN_5;
		} else throw new IllegalArgumentException("No matching Message found for class " + result.origin.getName());
	}
	
	/**
	 * Returns the comment of a Cell
	 * 
	 * One example might be: "No adjacent blue structure, Green structure #1"
	 */
	private static void appendCommentArgument(StringBuilder sb, Cell cell) {
		sb.append(cell.getComment());
	}
}
