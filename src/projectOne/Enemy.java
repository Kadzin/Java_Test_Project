package projectOne;

import org.jsfml.graphics.*;
import java.io.IOException;
import java.nio.file.Paths;
import projectOne.Main;
import java.util.Random;

public class Enemy {
	
	//Задаём текстуру игрока, а так же его положение и скорость
	Sprite enemySkin;
	private float xPos = 128, yPos = 128;
	IntRect intRect = new IntRect(0, 0, 32, 32);
	private String[][] tileMapClass = Main.tileMap;
	private int n1, n2, count=1;
	private Random rand = new Random();
		
	Enemy() {
		Texture enemyTexture = new Texture();
			
		//Как оказалось, чтобы загрузить картинку нужно всё это писать, по-другому ещё сложнее!
		try {
		    //Загружаем картинку и смотрим что получилось, если всё хорошо, то гуд
			enemyTexture.loadFromFile(Paths.get("enemySkin1.png"));
		} catch(IOException ex) {
		    //А если нет, то не очень
			   ex.printStackTrace();
		}
			
		//Загружаем картинку-текстура игрока в его спрайт
		enemySkin = new Sprite(enemyTexture);
		enemySkin.setTextureRect(intRect);
		enemySkin.setPosition(xPos, yPos);
	}
	//Метод движения врага по карте
	void update(float playerXpos, float playerYpos) {
		//Загружаем текущую карту
		tileMapClass = Main.tileMap;
		//Если на растояни 2ух клеток нет игрока, то двигается рандомно
		if (xPos - playerXpos < -64 | xPos - playerXpos > 64 | yPos - playerYpos < -64 | yPos - playerYpos > 64) {
			count-=1;
			if (count==0) {
				n1 = rand.nextInt(2);
				n2 = rand.nextInt(2);
				count=rand.nextInt(3)+2;
			}
			if (n1 == 1) {
				if (n2 == 0) {
					yPos-=32;
					if (tileMapClass[(int) (yPos/64)][(int) (xPos/64)] != " ") yPos+=32;
				}
				else {
					yPos+=32;
					if (tileMapClass[(int) (yPos/64)][(int) (xPos/64)] != " ") yPos-=32;
				}
			}
			else {
				if (n2 == 1) {
					xPos-=32;
					if (tileMapClass[(int) (yPos/64)][(int) (xPos/64)] != " ") xPos+=32;
				}
				else {
					xPos+=32;
					if (tileMapClass[(int) (yPos/64)][(int) (xPos/64)] != " ") xPos-=32;
				}
			}
			//Если на растоянии 2ух клеток есть игрок то двигаться за ним но не "наступать на него.
		} else {
			if (xPos - playerXpos < -32 | xPos - playerXpos > 32 | yPos - playerYpos < -32 | yPos - playerYpos > 32) {
				if (playerXpos - xPos > 0 ) {
					xPos+=32;
					if (tileMapClass[(int) (yPos/64)][(int) (xPos/64)] != " ") xPos-=32;
				}
				if (playerXpos - xPos < 0 ) {
					xPos-=32;
					if (tileMapClass[(int) (yPos/64)][(int) (xPos/64)] != " ") xPos+=32;
				}
				if (playerYpos - yPos > 0 ) {
					yPos+=32;
					if (tileMapClass[(int) (yPos/64)][(int) (xPos/64)] != " ") yPos-=32;
				}
				if (playerYpos - yPos < 0 ) {
					yPos-=32;
					if (tileMapClass[(int) (yPos/64)][(int) (xPos/64)] != " ") yPos+=32;
				}
			}
		}
		enemySkin.setPosition(xPos, yPos);
	}
}
