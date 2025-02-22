package sim;
//coordinate system 8 by 8, 64 squares
// (0,7) bottom left corner
// (0,0) top left corner
// (7,0) top right corner
// (7,7) bottom right corner

public class Ant {
	private int row;
	private int column;
	private boolean corner;
	private boolean justEdge;
	private int moveCount;
	
	private boolean diagnol;
	private boolean noPrev;
	private int prevRow;
	private int prevColumn;
	
	public Ant(int startX, int startY, boolean d, boolean p) {
		row = startX;
		column = startY;
		corner = true;
		justEdge = true;
		diagnol = d;
		noPrev = p;
	}
	
	public int getRow() {
		return row;
	}
	
	public int getColumn() {
		return column;
	}
	
	public int getMoveCount() {
		return moveCount;
	}
	
	private void up() {
		row--;
	}
	
	private void down() {
		row++;
	}
	
	private void left() {
		column--;
	}
	
	private void right() {
		column++;
	}
	
	///////////////////////////////////
	public void move(int maxRow, int maxColumn) {
		double moveChance = Math.random();
		if (diagnol) {
			// allow diagonol moves
			if (corner) {
				//cornerDmoves
				cornerDMoves(moveChance, maxRow - 1, maxColumn - 1);
			} else if (justEdge) {
				//edgeDmoves
				edgeDMoves(moveChance, maxRow - 1, maxColumn - 1);
			} else {
				//midDmoves
				midDMoves(moveChance);
			}
		} else {
			// no diagonol moves
			if (corner) {
				//corner moves
				cornerMoves(moveChance, maxRow - 1, maxColumn - 1);
			} else if (justEdge) {
				//edge moves
				edgeMoves(moveChance, maxRow - 1, maxColumn - 1);
			} else {
				//mid moves
				midMoves(moveChance);
			}
		}
		checkNewPosition(row,column, maxRow - 1, maxColumn - 1);
		moveCount++;
	}
	//////////////////////////////////
	
	// straight moves
	private void cornerMoves(double chance, int maxRow, int maxColumn) {
		if (row == 0 && column == 0) { //case 1 on topLeft => down, right
			if (chance <= 0.5) {
				down();
			} else if (chance > 0.5) {
				right();
			}
		} else if (column == maxColumn && row == 0) { //case 2 on topRight => down, left
			if (chance <= 0.5) {
				down();
			} else if (chance > 0.5){
				left();
			}
		} else if (row == maxRow && column == 0) { //case 3 on bottomLeft => up, right
			if (chance <= 0.5) {
				up();
			} else if (chance > 0.5) {
				right();
			}
		} else if (row == maxRow && column == maxColumn) { //case 4 on bottomRight => up, left
			if (chance <= 0.5) {
				up();
			} else if (chance > 0.5){
				left();
			}
		}
		corner = false;
	}
	
	private void edgeMoves(double chance, int maxRow, int maxColumn) {
		if (column == 0) { //case5 leftEdge => up, down, right
			if (chance <= 0.33) {
				up();
			} else if (chance > 0.33 && chance < 0.67) {
				down();
			} else if (chance >= 0.67) {
				right();
			}
		} else if (column == maxColumn) { //case6 rightEdge => up, down, left
			if (chance <= 0.33) {
				up();
			} else if (chance > 0.33 && chance < 0.67) {
				down();
			} else if (chance >= 0.67) {
				left();
			}
		} else if (row == maxRow) { //case7 bottomEdge => up, left, right
			if (chance <= 0.33) {
				up();
			} else if (chance > 0.33 && chance < 0.67) {
				left();
			} else if (chance >= 0.67){
				right();
			}
		} else if (row == 0) { //case8 topEdge => down, left, right
			if (chance <= 0.33) {
				down();
			} else if (chance > 0.33 && chance < 0.67) {
				left();
			} else if (chance >= 0.67) {
				right();
			}
		}
		justEdge = false;
	}
	
	private void midMoves(double chance) {
		if (chance <= 0.25) {
			up();
		} else if (chance > 0.25 && chance <= 0.5) {
			down();
		} else if (chance > 0.5 && chance <= 0.75) {
			right();
		} else if (chance > 0.75){
			left();
		}
	}
	
	private void checkNewPosition(int newRow,int newColumn, int maxRow, int maxColumn) {
		//checks new position if it's on a corner and/or edge
		if (newRow == 0 || newColumn == 0 || newRow == maxRow || newColumn == maxColumn) {// if on edge
			if ((newRow == 0 && newColumn == 0) || (newRow == 0 && newColumn == maxColumn) ||
					(newRow == maxRow && newColumn == 0) || (newRow == maxRow && newColumn == maxColumn)) { // if on corner
				corner = true;
			} 
			justEdge = true;
		}
	} 
	
	// diagonol moves
	private void cornerDMoves(double chance, int maxRow, int maxColumn) {
		if (row == 0 && column == 0) { //case 1 on topLeft => down, right, DiagnoDownRight
			if (chance <= 0.33) {
				down();
			} else if (chance > 0.33 && chance < 0.67) {
				right();
			} else if (chance >= 0.67) {
				down();
				right();
			}
		} else if (column == maxColumn && row == 0) { //case 2 on topRight => down, left, DiagnolDownLeft
			if (chance <= 0.33) {
				down();
			} else if (chance > 0.33 && chance < 0.67) {
				left();
			} else if (chance >= 0.67) {
				down();
				left();
			}
		} else if (row == maxRow && column == 0) { //case 3 on bottomLeft => up, right, DiagnolUpRight
			if (chance <= 0.33) {
				up();
			} else if (chance > 0.33 && chance < 0.67) {
				right();
			} else if (chance >= 0.67) {
				up();
				right();
			}
		} else if (row == maxRow && column == maxColumn) { //case 4 on bottomRight => up, left, DiagnolUpLeft
			if (chance <= 0.33) {
				up();
			} else if (chance > 0.33 && chance < 0.67) {
				left();
			} else if (chance >= 0.67) {
				up();
				left();
			}
		}
		corner = false;
	}

	private void edgeDMoves(double chance, int maxRow, int maxColumn) {
		if (column == 0) { //case5 leftEdge => up, down, right, upRight, downRight
			if (chance <= 0.2) {
				up();
			} else if (chance > 0.2 && chance <= 0.4) {
				down();
			} else if (chance > 0.4 && chance <= 0.6) {
				right();
			} else if (chance > 0.6 && chance <= 0.8) {
				up();
				right();
			} else if (chance > 0.8 && chance <= 1) {
				down();
				right();
			}
		} else if (column == maxColumn) { //case6 rightEdge => up, down, left, upLeft, downLeft
			if (chance <= 0.2) {
				up();
			} else if (chance > 0.2 && chance <= 0.4) {
				down();
			} else if (chance > 0.4 && chance <= 0.6) {
				left();
			} else if (chance > 0.6 && chance <= 0.8) {
				up();
				left();
			} else if (chance > 0.8 && chance <= 1) {
				down();
				left();
			}
		} else if (row == maxRow) { //case7 bottomEdge => up, left, right, upLeft, upRight
			if (chance <= 0.2) {
				up();
			} else if (chance > 0.2 && chance <= 0.4) {
				left();
			} else if (chance > 0.4 && chance <= 0.6) {
				right();
			} else if (chance > 0.6 && chance <= 0.8) {
				up();
				left();
			} else if (chance > 0.8 && chance <= 1) {
				up();
				right();
			}
		} else if (row == 0) { //case8 topEdge => down, left, right, downLeft, downRight
			if (chance <= 0.2) {
				down();
			} else if (chance > 0.2 && chance <= 0.4) {
				left();
			} else if (chance > 0.4 && chance <= 0.6) {
				right();
			} else if (chance > 0.6 && chance <= 0.8) {
				down();
				left();
			} else if (chance > 0.8 && chance <= 1) {
				down();
				right();
			}
		}
		justEdge = false;
	}
	
	private void midDMoves(double chance) {
		if (chance <= 0.125) {
			up();
		} else if (chance > 0.125 && chance <= 0.25) {
			down();
		} else if (chance > 0.25 && chance <= 0.375) {
			right();
		} else if (chance > 0.375 && chance <= 0.5){
			left();
		} else if (chance > 0.5 && chance <= 0.625) {
			up();
			right();
		} else if (chance > 0.625 && chance <= 0.75) {
			down();
			right();
		} else if (chance > 0.75 && chance <= 0.875) {
			up();
			left();
		} else if (chance > 0.875){
			down();
			left();
		}
	}
	
	// noPrev 
}



