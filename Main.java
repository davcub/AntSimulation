package sim;

public class Main {
	public static void main(String[] args) {
		result();
	}
	//diagnol: no, move to prev: yes 
	static int runSimulation1() {
		Simulation sim1 = new Simulation(8, 8, false, false);
		while(sim1.sameSquare() == false) { //stop when sameSquare
			sim1.doMove();
		}
		return sim1.getTime();
	}
	
	//diagnol: yes, move to prev: yes
	static int runSimulation2() {
		Simulation sim2 = new Simulation(8, 8, true, false);
		while(sim2.sameSquare() == false) { //stop when sameSquare
			sim2.doMove();
		}
		return sim2.getTime();
	}
	
	static int runSimulation3() {
		Simulation sim3 = new Simulation(8, 8, false, false);
		while(sim3.crossPath() == false) { //stop when cross path
			if(sim3.sameSquare() == true) { //stop if reach sameSquare first
				return sim3.getTime(); 
			}
			sim3.doMove();
		}
		return sim3.getTime();
	}
	
	static int runSimulation4() {
		Simulation sim4 = new Simulation(8, 8, true, false);
		while(sim4.crossPath() == false) { //stop when cross path
			if(sim4.sameSquare() == true) { //stop if reach sameSquare first
				return sim4.getTime(); 
			}
			sim4.doMove();
		}
		return sim4.getTime();
	}
	
	static void result() {	
		int totalTime1 = 0;
		for (int i = 0; i < 10000; i++) {
			totalTime1 += runSimulation1();
		}
		System.out.println(totalTime1/10000 + " seconds to reach same square (no diagonol)");
		
		int totalTime2 = 0;
		for (int i = 0; i < 10000; i++) {
			totalTime2 += runSimulation2();
		}
		System.out.println(totalTime2/10000 + " seconds to reach same square (diagonol)");
		
		int totalTime3 = 0;
		for (int i = 0; i < 10000; i++) {
			totalTime3 += runSimulation3();
		}
		System.out.println(totalTime3/10000 + " seconds to cross path (no diagonol)");
		
		int totalTime4 = 0;
		for (int i = 0; i < 10000; i++) {
			totalTime4 += runSimulation3();
		}
		System.out.println(totalTime4/10000 + " seconds to cross path (diagonol)");
	}
}




