package projectOne;

import org.jsfml.window.*;
import org.jsfml.window.Keyboard.Key;
import org.jsfml.window.Mouse.Button;
import org.jsfml.window.event.*;
import java.io.IOException;
import java.nio.file.Paths;
import org.jsfml.graphics.*;
import org.jsfml.system.*;
import projectOne.Player;

public class Main {
	//private static int heightMap = 16;
	private static int widthMap = 16;
	
	/* Чистый шаблон 16х16

	 	{" "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "},
		{" "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "},
		{" "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "},
		{" "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "},
		{" "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "},
		{" "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "},
		{" "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "},
		{" "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "},
		{" "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "},
		{" "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "},
		{" "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "},
		{" "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "},
		{" "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "},
		{" "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "},
		{" "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "},
		{" "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "}
		
	 */
	
	/*
	 U- Верхняя текстура
	 D- Нижняя текстура
	 E- Выход/Вход + пещера
	 B- Боковая текстура
	 7- С бока на верх
	 1- C низу в бок
	 */
 
	protected static String[][] tileMap = new String[][] {
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

	
	public static void main(String[] args) {		
		
		//Задаём параметры окна и создаём его
		VideoMode vm = new VideoMode(1024, 512);
		RenderWindow renderWindow = new RenderWindow(vm, "Test1", WindowStyle.CLOSE);
		
		//Создаем объект класса игрок
		Player player = new Player();
		
		//Устанавливаем счётчки времени
		//В будущем может понадобиться
		Clock clock = new Clock();
		float time;
		
		//Задаем спрайт карты
		Texture mapTexture = new Texture();
		try {
			mapTexture.loadFromFile(Paths.get("Map2.png"));
		} catch(IOException ex) {
			ex.printStackTrace();
		}
		Sprite mapSprite = new Sprite(mapTexture);
		
		//Создаем вектор для позиции мыши
		Vector2i vi = new Vector2i(0, 0);
		int mouseX, mouseY, workField = 0;
		boolean xFlag, yFlag;
		
		//Всё что будет показано в окне будет в этом цикле
		while (renderWindow.isOpen()){			
			//Задаем параметры временного интервала
			time = clock.getElapsedTime().asSeconds();
			clock.restart();
			time = time*500;
			
			//Закрытие окна (если делать через wait, то программа стопается!)
			for (Event myEvent : renderWindow.pollEvents()) {
				if (myEvent.type == Event.Type.CLOSED) {
					renderWindow.close();
				}
			}
			//Ожидаемые события. Клавиши указаны
			if (Keyboard.isKeyPressed(Key.ESCAPE)) {
				renderWindow.close();
			}
			if (Keyboard.isKeyPressed(Key.LEFT)) {
				player.xSpeed = (float) -1;
			}
			if (Keyboard.isKeyPressed(Key.RIGHT)) {
				player.xSpeed = (float) 1;
			}
			if (Keyboard.isKeyPressed(Key.UP)) {
				player.ySpeed = (float) -1;
			}
			if (Keyboard.isKeyPressed(Key.DOWN)) {
				player.ySpeed = (float) 1;
			}
			
			//Чистим окно, красим всё в белый и устанавливаем mapSprite позицию в 0
			renderWindow.clear(Color.WHITE);
			mapSprite.setPosition(0, 0);
			
			//"Строим" tilemap по заданному выше шаблону
			for (int h=0; h</*heightMap*/8; h++) {
				for (int w=0; w<widthMap; w++) {
					if (tileMap[h][w]=="U") mapSprite.setTextureRect(new IntRect(64,0,64,64));
					if (tileMap[h][w]=="D") mapSprite.setTextureRect(new IntRect(64,128,64,64));
					if (tileMap[h][w]=="B") mapSprite.setTextureRect(new IntRect(0,64,64,64));
					if (tileMap[h][w]=="b") mapSprite.setTextureRect(new IntRect(64,64,-64,64));
					if (tileMap[h][w]=="E") mapSprite.setTextureRect(new IntRect(64,64,64,64));
					if (tileMap[h][w]=="1") mapSprite.setTextureRect(new IntRect(0,128,64,64));
					if (tileMap[h][w]=="7") mapSprite.setTextureRect(new IntRect(0,0,64,64));
					if (tileMap[h][w]=="9") mapSprite.setTextureRect(new IntRect(128,128,64,64));
					if (tileMap[h][w]=="3") mapSprite.setTextureRect(new IntRect(128,64,64,64));
					if (tileMap[h][w]=="2") mapSprite.setTextureRect(new IntRect(64,0,-64,64));
					if (tileMap[h][w]=="4") mapSprite.setTextureRect(new IntRect(64,128,-64,64));
					if (tileMap[h][w]==" ") mapSprite.setTextureRect(new IntRect(128,0,64,64));
					
					//После выбора текстура для спрайта перемещаем его в новую позицию
					//Важное уточнение, слева ширина, а справа высота!!!
					mapSprite.setPosition(64*w, 64*h);
					renderWindow.draw(mapSprite);
				}
			}
			
			//Кладём в вектор позицию мыши
			vi = Mouse.getPosition(renderWindow);
			//Переменная работает в качестве флага
			workField = 0;
			//Условия отображения поля под курсором
			//+64 и -32 это одна клетка вокруг персонажа ( * на скилл ренджа персонажа)
			if(vi.x<(player.xPos+64) & vi.x>(player.xPos-32)) workField+=1;
			if(vi.y<(player.yPos+64) & vi.y>(player.yPos-32)) workField+=1;
			if(workField==2) {		
				//"Запоминаем" для дальнейшего импользования
				mouseX = vi.x/32;
				mouseY = vi.y/32;
				//Делим на 64(размера квадрата текстуры)
				vi = Vector2i.div(vi, 64);
				//Если на "земле" то отобразиться красным
				if (tileMap[vi.y][vi.x]==" ") {
					//Метод для отображения 1/4 текстуры
					if (mouseX%2 != 0) xFlag = false;
					else xFlag = true;
					if (mouseY%2 != 0) yFlag = false;
					else yFlag = true;
					//Верх лево
					if (xFlag & yFlag) mapSprite.setTextureRect(new IntRect(192,0,64,64));
					//Верх право
					if (!xFlag & yFlag) mapSprite.setTextureRect(new IntRect(256,0,-64,64));
					//Низ лево
					if (xFlag & !yFlag) mapSprite.setTextureRect(new IntRect(192,64,64,-64));
					//Низ право
					if (!xFlag & !yFlag) mapSprite.setTextureRect(new IntRect(256,64,-64,-64));
					//Обработчик движений по нажатию мышкой
					if (Mouse.isButtonPressed(Button.LEFT)) {
						player.xPos = mouseX*32;
						player.yPos = mouseY*32;
						player.update();
					}
					mapSprite.setPosition(vi.x*64, vi.y*64);
					renderWindow.draw(mapSprite);
					workField=0;
				}
			}
			
			//Блок отрисовки, рисуем всё что осталось нарисовать
			renderWindow.draw(player.playerSkin);
			//Дисплей обязательно указывать что бы отобразить всё то что будет в цикле
			renderWindow.display();
		}
	}
}