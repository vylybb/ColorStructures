package ch.pf.colorStructures.model;

/**
 * Defindes a Object that consists of at least one {@link Cell}
 * as being able to share a edge with other {@link IShape}s
 * @author P.Flury@gmx.ch
 *
 */
public interface IShape {
	
	/**
	 * Returns true if it shares a edge with at least one of the given {@link Cell}s
	 * @param cells
	 * @return
	 */
	public boolean isAdjacentTo(Cell[] cells);
}
