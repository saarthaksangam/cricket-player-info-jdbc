package playerdetails;

import java.util.List;

import javax.swing.table.AbstractTableModel;

import playerdetails.PlayerDetails;

class PlayerDetailsTableModel extends AbstractTableModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public static final int OBJECT_COL = -1;
	public static final int NAME_COL = 0;
	public static final int MATCHES_COL = 1;
	public static final int RUNS_COL = 2;
	public static final int WICKETS_COL = 3;
	public static final int CENTURIES_COL = 4;
	public static final int FIFTIES_COL = 5;
	
	private String[] columnNames = { "Name", "Matches", "Runs Scored",
			"Wickets", "Centuries", "Fifties" };
	private List<PlayerDetails> PlayerDetails;

	public PlayerDetailsTableModel(List<PlayerDetails> thePlayerDetails) {
		PlayerDetails = thePlayerDetails;
	}

	@Override
	public int getColumnCount() {
		return columnNames.length;
	}

	@Override
	public int getRowCount() {
		return PlayerDetails.size();
	}

	@Override
	public String getColumnName(int col) {
		return columnNames[col];
	}

	@Override
	public Object getValueAt(int row, int col) {

		PlayerDetails tempPlayerDetails = PlayerDetails.get(row);

		switch (col) {
		case NAME_COL:
			return tempPlayerDetails.getName();
		case MATCHES_COL:
			return tempPlayerDetails.getMatches();
		case RUNS_COL:
			return tempPlayerDetails.getRuns();
		case WICKETS_COL:
			return tempPlayerDetails.getWickets();
		case CENTURIES_COL:
			return tempPlayerDetails.getCenturies();
		case FIFTIES_COL:
			return tempPlayerDetails.getFifties();
						
		default:
			return tempPlayerDetails.getName();
		}
	}

	@Override
	public Class<? extends Object> getColumnClass(int c) {
		return getValueAt(0, c).getClass();
	}
}
