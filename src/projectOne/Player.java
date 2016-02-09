package projectOne;

import java.io.IOException;
import java.nio.file.Paths;
import org.jsfml.graphics.IntRect;
import org.jsfml.graphics.Sprite;
import org.jsfml.graphics.Texture;

class Player {
	//Задаём текстуру игрока, а так же его положение и скорость
	Sprite playerSkin;
	float xPos = 288, yPos = 256;
	IntRect intRect = new IntRect(0, 0, 32, 32);
	
	//переменные из прошлой версии 
	float xSpeed = 0, ySpeed = 0;
	
	Player() {
		Texture playerTexture = new Texture();
		
		//Как оказалось, чтобы загрузить картинку нужно всё это писать, по-другому ещё сложнее!
		try {
		    //Загружаем картинку и смотрим что получилось, если всё хорошо, то гуд
			playerTexture.loadFromFile(Paths.get("playerSkin1.png"));
		} catch(IOException ex) {
		    //А если нет, то не очень
		    ex.printStackTrace();
		}
		
		//Загружаем картинку-текстура игрока в его спрайт
		playerSkin = new Sprite(playerTexture);
		playerSkin.setTextureRect(intRect);
		playerSkin.setPosition(xPos, yPos);
	}
	void update() {
		playerSkin.setPosition(xPos, yPos);
	}
}
