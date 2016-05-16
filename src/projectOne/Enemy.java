package projectOne;

import org.jsfml.graphics.*;
import java.io.IOException;
import java.nio.file.Paths;

public class Enemy {
	
	//����� �������� ������, � ��� �� ��� ��������� � ��������
	Sprite enemySkin;
	float xPos = 512, yPos = 512;
	IntRect intRect = new IntRect(0, 0, 32, 32);
		
	//���������� �� ������� ������ 
	//float xSpeed = 0, ySpeed = 0;
		
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
	void update(float playerXpos, float playerYpos) {
		xPos/=32;
		yPos/=32;
		playerXpos/=32;
		playerYpos/=32;
		float xx, yy;
		xx = playerXpos - xPos;
		yy = playerYpos - yPos;
		if (xx == 0 ) {
			xPos*=32;			
			}
		if (xx > 0 ) {
			xPos+=1;
			xPos*=32;
			}
		if (xx < 0 ) {
			xPos-=1;
			xPos*=32;
		}
		if (yy == 0 ) {
			yPos*=32;
		}
		if (yy > 0 ) {
			yPos+=1;
			yPos*=32;
		}
		if (yy < 0 ) {
			yPos-=1;
			yPos*=32;
		}
		enemySkin.setPosition(xPos, yPos);
	}
}
