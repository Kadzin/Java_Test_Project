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
	
	/* ������ ������ 16�16

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
	 U- ������� ��������
	 D- ������ ��������
	 E- �����/���� + ������
	 B- ������� ��������
	 7- � ���� �� ����
	 1- C ���� � ���
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
		
		//����� ��������� ���� � ������ ���
		VideoMode vm = new VideoMode(1024, 512);
		RenderWindow renderWindow = new RenderWindow(vm, "Test1", WindowStyle.CLOSE);
		
		//������� ������ ������ �����
		Player player = new Player();
		
		//������������� ������� �������
		//� ������� ����� ������������
		Clock clock = new Clock();
		float time;
		
		//������ ������ �����
		Texture mapTexture = new Texture();
		try {
			mapTexture.loadFromFile(Paths.get("Map2.png"));
		} catch(IOException ex) {
			ex.printStackTrace();
		}
		Sprite mapSprite = new Sprite(mapTexture);
		
		//������� ������ ��� ������� ����
		Vector2i vi = new Vector2i(0, 0);
		int mouseX, mouseY, workField = 0;
		boolean xFlag, yFlag;
		
		//�� ��� ����� �������� � ���� ����� � ���� �����
		while (renderWindow.isOpen()){			
			//������ ��������� ���������� ���������
			time = clock.getElapsedTime().asSeconds();
			clock.restart();
			time = time*500;
			
			//�������� ���� (���� ������ ����� wait, �� ��������� ���������!)
			for (Event myEvent : renderWindow.pollEvents()) {
				if (myEvent.type == Event.Type.CLOSED) {
					renderWindow.close();
				}
			}
			//��������� �������. ������� �������
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
			
			//������ ����, ������ �� � ����� � ������������� mapSprite ������� � 0
			renderWindow.clear(Color.WHITE);
			mapSprite.setPosition(0, 0);
			
			//"������" tilemap �� ��������� ���� �������
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
					
					//����� ������ �������� ��� ������� ���������� ��� � ����� �������
					//������ ���������, ����� ������, � ������ ������!!!
					mapSprite.setPosition(64*w, 64*h);
					renderWindow.draw(mapSprite);
				}
			}
			
			//����� � ������ ������� ����
			vi = Mouse.getPosition(renderWindow);
			//���������� �������� � �������� �����
			workField = 0;
			//������� ����������� ���� ��� ��������
			//+64 � -32 ��� ���� ������ ������ ��������� ( * �� ����� ������ ���������)
			if(vi.x<(player.xPos+64) & vi.x>(player.xPos-32)) workField+=1;
			if(vi.y<(player.yPos+64) & vi.y>(player.yPos-32)) workField+=1;
			if(workField==2) {		
				//"����������" ��� ����������� �������������
				mouseX = vi.x/32;
				mouseY = vi.y/32;
				//����� �� 64(������� �������� ��������)
				vi = Vector2i.div(vi, 64);
				//���� �� "�����" �� ������������ �������
				if (tileMap[vi.y][vi.x]==" ") {
					//����� ��� ����������� 1/4 ��������
					if (mouseX%2 != 0) xFlag = false;
					else xFlag = true;
					if (mouseY%2 != 0) yFlag = false;
					else yFlag = true;
					//���� ����
					if (xFlag & yFlag) mapSprite.setTextureRect(new IntRect(192,0,64,64));
					//���� �����
					if (!xFlag & yFlag) mapSprite.setTextureRect(new IntRect(256,0,-64,64));
					//��� ����
					if (xFlag & !yFlag) mapSprite.setTextureRect(new IntRect(192,64,64,-64));
					//��� �����
					if (!xFlag & !yFlag) mapSprite.setTextureRect(new IntRect(256,64,-64,-64));
					//���������� �������� �� ������� ������
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
			
			//���� ���������, ������ �� ��� �������� ����������
			renderWindow.draw(player.playerSkin);
			//������� ����������� ��������� ��� �� ���������� �� �� ��� ����� � �����
			renderWindow.display();
		}
	}
}