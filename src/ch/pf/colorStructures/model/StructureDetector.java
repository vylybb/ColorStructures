package ch.pf.colorStructures.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * A Tool to detect {@link Structure}s in a Set of {@link Cell}s

 */
public class StructureDetector {

	/**
	 * Detects and returns {@link Structure}s in {@link Cell}s
	 * @param cells
	 * @return
	 */
	public static Structure[] detect(Cell[] cells) {
		if (cells == null) {
			throw new IllegalArgumentException("cells must not be null");
		}
		if (cells.length == 0) {
			return new Structure[0];
		}

		for (int i = 1; i < cells.length; i++) {
			if (cells[i].getColor() != cells[0].getColor()) {
				throw new IllegalArgumentException("All Cells given to a " + StructureDetector.class.getName() + " must be the same");
			}
		}

		Stack<Structure> structureStack = new Stack<Structure>();
		for (Cell c : cells) {
			structureStack.add(new Structure(c));
		}
		List<Structure> result = doDetectStructures(structureStack);
		return result.toArray(new Structure[result.size()]);
	}

	/*
	 * The Idea behind goes as follows: 
	 * At the beginning, every cell exists inside its own {@link Structure}
	 * We merge these {@link Structure}s together until there is nothing left to be merged.
	 * 
	 * In more detail:
	 * 1. We take one {@link Structure} after another from the Pendent-Stack.
	 * 2. We compare this {@link Structure} with every other {@link Structure} inside the Pendent-Stack
	 * 3. If one of these {@link Structure} is adjacent to the {@link Structure} we analize, we merge it together and update the analize-{@link Structure}.
	 * 4. If this is not the case, we push the {@link Structure} we look at to the Processed-Stack
	 * 5. If our Pendent-stack does not contain any {@link Structure}s and the {@link Structure} was never merged, we add it the Result-List
	 * 6. If our Pendent-stack does not contain any {@link Structure}s and the {@link Structure} merged, we add it the Processed-Stack
	 * 7. We exchange Processed-Stack and Pendent-Stack and start from a new untill the Processed-stack is empty at the end of a Cycle
	 */
	private static List<Structure> doDetectStructures(Stack<Structure> input) {
		if (input.size() == 0) {
			return input;
		}

		ArrayList<Structure> result = new ArrayList<Structure>();
		Stack<Structure> pendent = input;
		Stack<Structure> processed = new Stack<Structure>();

		while (pendent.size() != 0) {
			Structure analize = pendent.pop();
			boolean mergeOccured = false;
			while (pendent.size() != 0) {
				Structure lookedAt = pendent.pop();
				if (analize.isAdjacentTo(lookedAt.getCells())) {
					mergeOccured = true;
					analize = analize.merge(lookedAt);
				} else {
					processed.push(lookedAt);
				}
			}
			if (!mergeOccured) {
				result.add(analize);
			} else {
				processed.add(analize);
			}
			Stack<Structure> tmp = pendent;
			pendent = processed;
			processed = tmp;
		}

		return result;
	}
}
