package projectOne;

import org.jsfml.graphics.*;
import java.io.IOException;
import java.nio.file.Paths;
import projectOne.Main;
import java.util.Random;

public class Enemy {
	
	//����� �������� ������, � ��� �� ��� ��������� � ��������
	Sprite enemySkin;
	private float xPos = 128, yPos = 128;
	IntRect intRect = new IntRect(0, 0, 32, 32);
	private String[][] tileMapClass = Main.tileMap;
	private int n1, n2, count=1;
	private Random rand = new Random();
		
	Enemy() {
		Texture enemyTexture = new Texture();
			
		//��� ���������, ����� ��������� �������� ����� �� ��� ������, ��-������� ��� �������!
		try {
		    //��������� �������� � ������� ��� ����������, ���� �� ������, �� ���
			enemyTexture.loadFromFile(Paths.get("enemySkin1.png"));
		} catch(IOException ex) {
		    //� ���� ���, �� �� �����
			   ex.printStackTrace();
		}
			
		//��������� ��������-�������� ������ � ��� ������
		enemySkin = new Sprite(enemyTexture);
		enemySkin.setTextureRect(intRect);
		enemySkin.setPosition(xPos, yPos);
	}
	//����� �������� ����� �� �����
	void update(float playerXpos, float playerYpos) {
		//��������� ������� �����
		tileMapClass = Main.tileMap;
		//���� �� �������� 2�� ������ ��� ������, �� ��������� ��������
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
			//���� �� ��������� 2�� ������ ���� ����� �� ��������� �� ��� �� �� "��������� �� ����.
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
