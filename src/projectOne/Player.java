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
	}
	
	void update(float time) {
		//Супер формула для скорости передвижния игрока
		xPos = xPos + (xSpeed*time);//По оси х
		//Обработка столконвений по оси х
		collision(1);
		yPos = yPos + (ySpeed*time);//По оси у
		//Обработка столкновений по оси у
		collision(0);
		playerSkin.setPosition(xPos, yPos);//И, собсн, перемещаем его туда
		//Обнуляем скорости, чтобы игрок не двигался вечно!
		xSpeed = ySpeed = 0;
	}
	//E;t
	void collision(int collisionType) {
		//Перебираем tileMap и ищем "пересечение" координат, т.е. ищем столкновения
		for(int j= (int)xPos/64; j<(((int)xPos+32)/64)+1; j++) {
			for(int i=(int)yPos/64; i<(((int)yPos+32)/64)+1; i++) {	
				//Если стена то отбрасывает назад
				if(Main.tileMap[i][j]==" ");
				else
				{
					if((ySpeed<0) & (collisionType==0)) yPos = i*64 + 65;
					if((ySpeed>0) & (collisionType==0)) yPos = i*64 - 33;//31 это ширина персонажа!!!
					if((xSpeed<0) & (collisionType==1)) xPos = j*64 + 65;
					if((xSpeed>0) & (collisionType==1)) xPos = j*64 - 33;//31 это ширина персонажа!!!	
				}
				//Если элемент tileMap равен S, то заменяем его на пробел(это прообраз монетки)
				if(Main.tileMap[i][j]=="S") Main.tileMap[i][j]=" ";
			}
		}
	}
	/*
	void mousePosition() {
		for(int i=0;i<1;i++) {
			for(int j=0;j<1;j++) {
				Vector2i.div(Mouse.getPosition(),32);
			}
		}
	}
	*/
}
