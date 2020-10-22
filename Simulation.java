package sim;

public class Simulation {
	private int[][] board;
	private int row;
	private int column;
	private Ant ant1;
	private Ant ant2;
	private boolean sameSquare;
	private boolean crossPath;
	private int time;
	
	public Simulation(int r, int c, boolean diagnolly, boolean previous) {
		row = r;
		column = c;
		board = new int[r][c];
		ant1 = new Ant(0,0,diagnolly, previous);
		ant2 = new Ant(row - 1, column - 1,diagnolly,previous);
		sameSquare = false;
		crossPath = false;
		time = 0;
	}
	
	public void doMove() {
		ant1.move(row, column);
		ant2.move(row, column);
	
		if (ant1.getRow() == ant2.getRow() && ant1.getColumn() == ant2.getColumn()) {
			sameSquare = true;
		} else {
			updateBoard();
		}
	}
	
	/////////////////////////////////////
	private void updateBoard() {
		if (board[ant1.getRow()][ant1.getColumn()] == 2 || board[ant2.getRow()][ant2.getColumn()] == 1) {
			crossPath = true;
		} else {
			board[ant1.getRow()][ant1.getColumn()] = 1;
			board[ant2.getRow()][ant2.getColumn()] = 2;
		}
	}
		
	public boolean sameSquare() {
		return sameSquare;
	}
	
	public boolean crossPath() {
		return crossPath;
	}
	
	public int getTime() {
		time = ant1.getMoveCount() * 10;
		return time;
	}
}
