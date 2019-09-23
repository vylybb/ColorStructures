package ch.pf.colorStructures;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;

import ch.pf.colorStructures.io.CellGenerator;
import ch.pf.colorStructures.io.OutputGenerator;
import ch.pf.colorStructures.model.Cell;
import ch.pf.colorStructures.model.Grid;
import ch.pf.colorStructures.model.validate.RuleValidationReport;
import ch.pf.colorStructures.model.rules.IRule;
import ch.pf.colorStructures.model.rules.R01GreenStructureHasAdjacentBlueStructureRule;
import ch.pf.colorStructures.model.rules.R02RedStructureHasNoMoreTheOneAdjacentStructureRule;
import ch.pf.colorStructures.model.rules.R03YellowStructureCannotHaveAAdjacentGreenStructureRule;
import ch.pf.colorStructures.model.rules.R04RedStructureCannotConsistOfMoreThan5CellsRule;
import ch.pf.colorStructures.model.rules.R05YellowAlwaysLinearRule;
import ch.pf.colorStructures.model.rules.R06NoMoreThan2BlueStructuresRule;
import ch.pf.colorStructures.model.rules.R07AvarageCellPerStuctureAndColorLess5Rule;

/**
 * This is the Main-Class
 * It is where the Application is started.
 */
public class Starter {

	/**
	 * This project is not using Unit-Tests but will perform tests (taken from the original task-sheet), when started without parameters
	 */
	public static void main(String[] args) {
		if(args.length != 0) {
			if(args.length != 2) {
				throw new IllegalArgumentException("Two or no Argument expected");
			} else {
				start(new File(args[0]), new File(args[1]));
			}
		}
		for(File f : new File("testdata/output").listFiles()) {
			if(!f.delete()) {
				throw new IllegalStateException("Unable to delete File " + f.getName());
			}
		}
		for(File f : new File("testdata/input").listFiles()) {
			start(f, new File("testdata/output/" + f.getName().replace("Test", "Result")));
		}
		System.out.println("fin");
	}

	/**
	 * 1. Creation of {@link Cell}s from a given File
	 * 2. Creation of a {@link Grid}, introducing these {@link Cell}s and a set of {@link Rules} to it.
	 * 3. Using the {@link Grid} to detect Structures
	 * 4. Validating the {@link Grid} to receive a {@link RuleValidationReport}
	 * 5. Passing this {@link RuleValidationReport} to a {@link OutputGenerator} to produce the desired output
	 * 
	 * @param args
	 */
	private static void start(File input, File output) {
		BufferedReader reader = null;
		FileWriter writer = null;
		try {
			try {
				System.out.println();
				System.out.println(input.getName());
				reader = new BufferedReader(new FileReader(input));
				Cell[] cells = CellGenerator.generateCells(reader);
				Grid g = new Grid();
				g.setCells(cells);
				g.setRules(generateRules());
				g.generateStructures();
				RuleValidationReport report = g.validate();
				String message = OutputGenerator.generateOutput(report);
				System.out.println(message);
				writer = new FileWriter(output);
				writer.write(message);
			} finally {
				try {
					if (writer != null) {
						writer.close();
					}
				} finally {
					if (reader != null) {
						reader.close();
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/**
	 * Generates the Rules or our {@link Grid}
	 * @return
	 */
	private static IRule[] generateRules() {
		return new IRule[] {
				new R01GreenStructureHasAdjacentBlueStructureRule(),
				new R02RedStructureHasNoMoreTheOneAdjacentStructureRule(),
				new R03YellowStructureCannotHaveAAdjacentGreenStructureRule(),
				new R04RedStructureCannotConsistOfMoreThan5CellsRule(),
				new R05YellowAlwaysLinearRule(),
				new R06NoMoreThan2BlueStructuresRule(),
				new R07AvarageCellPerStuctureAndColorLess5Rule()};
	}
}
