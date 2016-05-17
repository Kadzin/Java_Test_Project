package projectOne;

import java.util.Random;

public class TileMap {
	
	private static String mapDetailRet() {
		String retTexturLetter;
		Random random = new Random();
		int randNum = random.nextInt(5);
		if (randNum == 0) {
			retTexturLetter = "D";
		}
		else {
			retTexturLetter = " ";
		}
		return retTexturLetter;
	}
	static String n = mapDetailRet();
	
	String[][] update() {
		
		String[][] tileMapClass = new String[][] {
			{"7","U","U","U","U","U","U","E","U","U","U","U","U","U","U","2"},
			{"B"," "," "," "," "," "," "," "," "," "," "," "," "," "," ","b"},
			{"B"," "," "," "," "," "," "," "," "," "," "," "," "," "," ","b"},
			{"B"," "," "," "," "," "," "," "," "," "," "," "," "," "," ","b"},
			{"B"," "," "," "," "," "," "," "," "," "," "," "," "," "," ","b"},
			{"B"," "," "," "," "," "," "," "," "," "," "," "," "," "," ","b"},
			{"B"," "," "," "," "," "," "," "," "," "," "," "," "," "," ","b"},
			{"1","D","D","D","D","D","D","D","D","D","D","D","D","D","D","4"}
			/*{"7","U","U","U","U","3"," "," "," "," "," "," "," "," "," ","b"},
			{"B"," "," "," "," "," "," "," "," "," "," "," "," "," "," ","b"},
			{"B"," "," "," "," "," "," "," "," "," "," "," "," "," "," ","b"},
			{"B"," "," "," "," "," "," "," "," "," "," "," "," "," "," ","b"},
			{"B"," "," "," "," "," "," "," "," "," "," "," "," "," "," ","b"},
			{"B"," "," "," "," "," "," "," "," "," "," "," "," "," "," ","b"},
			{"B"," "," "," "," "," "," "," "," "," "," "," "," "," "," ","b"},
			{"1","D","D","D","D","D","D","D","D","D","D","D","D","D","D","4"}*/
		};
		
		for (int x=1; x<15; x++) {
			for (int y=1; y<7; y++) {
				tileMapClass[y][x] = mapDetailRet();
			}
		}
		
		return tileMapClass;
	}	
}
