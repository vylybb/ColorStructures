package ch.pf.colorStructures.io;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import ch.pf.colorStructures.model.Cell;

/**
 * A Tool to generate {@link Cell}s from a File
 * @author P.Flury@gmx.ch
 */

public class CellGenerator {

	/**
	 * Generates {@link Cells} from File
	 * The input consists of several, comma-seperated arguments
	 * 
	 * @param reader
	 * @return
	 * @throws IOException
	 */
	public static Cell[] generateCells(BufferedReader reader) throws IOException {
		if(reader == null) {
			throw new IllegalArgumentException("Reader must not be null");
		}
		List<Cell> result = new ArrayList<Cell>();
		String curLine = null;
		while ((curLine = reader.readLine()) != null) {
			String[] lineArguments = curLine.split(",");
			if (lineArguments.length == 1) {// ignore empty lines
				continue;
			}
			if (lineArguments[0].trim().startsWith("#")) {// pure comment-lines
				continue;
			}
			if (lineArguments.length < 3) { // a valid line must consist of at least 4 comma seperated arguments
				throw new IllegalStateException("invalid content");
			}
			result.add(new Cell(
					parseCoordinate(lineArguments[0]),
					parseCoordinate(lineArguments[1]), 
					parseColor(lineArguments[2]),
					parseComment(lineArguments.length == 4 ? lineArguments[3] : null)));
		}
		return result.toArray(new Cell[result.size()]);
	}

	/**
	 * Parses a Coordinate from a Argument
	 * @param s
	 * @return
	 */
	private static int parseCoordinate(String s) {
		if (s.isEmpty()) {
			throw new IllegalStateException("Coordinate Argument must not be empty");
		}
		for (Character c : s.toCharArray()) {
			if (!Character.isDigit(c)) {
				throw new IllegalStateException("Coordinate Argument: Digit expected " + c + " is not a digit");
			}
		}
		return Integer.parseInt(s);
	}

	/**
	 * Parses a {@link Cell.CellColor} from a Argument
	 * @param s
	 * @return
	 */
	private static Cell.CellColor parseColor(String s) {
		if (s.isEmpty()) {
			throw new IllegalStateException("Color Argument must not be empty");
		}
		if ("R".equals(s)) { return Cell.CellColor.RED;
		} else if ("Y".equals(s)) { return Cell.CellColor.YELLOW;
		} else if ("G".equals(s)) { return Cell.CellColor.GREEN;
		} else if ("B".equals(s)) { return Cell.CellColor.BLUE;
		} else { throw new IllegalStateException("Color Argument: Symbol " + s + " cannot be mapped to a known color"); }
	}

	/**
	 * Parses a Comment from a Argument
	 * @param s
	 * @return
	 */
	private static String parseComment(String s) {
		return s;
	}
}
