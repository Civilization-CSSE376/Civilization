import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.ButtonGroup;
import javax.swing.JOptionPane;
import javax.swing.JPopupMenu;
import javax.swing.JRadioButtonMenuItem;

public class TestBoard extends Board {

	public String choice = null;

	public TestBoard(String p1Civ, String p2Civ) {
		super(p1Civ, p2Civ);
	}

	@Override
	public void makeMovementWindow(final ArrayList<Figure> figures) {
		if (figures.get(0).getUsedThisTurn()
				|| figures.get(0).getNumberOfMoves() == 0) {
			return;
		}
		currentMovementFigure = figures.get(0);
		return;
	}

	@Override
	public Boolean makeNewCityWindow() {
		return true;
	}

	@Override
	public void makeChoice(String[] choices, ActionListener handler, Point point) {
		ActionEvent event = new ActionEvent(new JRadioButtonMenuItem(
				this.choice), 0, null);
		handler.actionPerformed(event);
	}
}
