package ch.pf.colorStructures.io;

import ch.pf.colorStructures.model.validate.RuleValidationReport;

/**
 * Holds all Messages that are needed while producing a output for a {@link RuleValidationReport}
 * @author P.Flury@gmx.ch
 */
public interface OutputStringprovider {
	public static String NO_RESULTS = "# no Validation-Results were produced";
	
	public static String R01_NO_ADJACENT_BLUE_STRUCTURE = "No adjacent blue structure";
	public static String R02_MORE_THAN_ONE_OTHER_ADJACENT_STRUCTURE = "More than one another adjacent structure to a red structure";
	public static String R03_ADJACENT_GREEN_STRUCTURE_FOUND = "Adjacent green structure found to a yellow structure";
	public static String R04_CANNOT_CONSIST_OF_MORE_THAN_5_CELLS = "Can not consist of more than 5 cells";
	public static String R05_NOT_LINEAR = "Not linear";
	public static String R06_MORE_THAN_TWO_BLUE_STRUCTURES_IN_THE_GRID = "More than two blue structures in the grid";
	public static String R07_AVERAGE_NUMBER_OF_CELLS_PER_STRUCTURE_IS_NOT_LESS_THAN_5 = "Average number of cells per structure is not less than 5";
	
	public static String OK = "OK";
	
	public static String STRUCTURE = "structure";
	
	public static String BLUE = "Blue";
	public static String YELLOW = "Yellow";
	public static String GREEN = "Green";
	public static String RED = "Red";
}
