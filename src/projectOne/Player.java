package projectOne;

import java.io.IOException;
import java.nio.file.Paths;
import org.jsfml.graphics.IntRect;
import org.jsfml.graphics.Sprite;
import org.jsfml.graphics.Texture;

class Player {
	//����� �������� ������, � ��� �� ��� ��������� � ��������
	Sprite playerSkin;
	float xPos = 288, yPos = 256;
	IntRect intRect = new IntRect(0, 0, 32, 32);
	
	//���������� �� ������� ������ 
	float xSpeed = 0, ySpeed = 0;
	
	Player() {
		Texture playerTexture = new Texture();
		
		//��� ���������, ����� ��������� �������� ����� �� ��� ������, ��-������� ��� �������!
		try {
		    //��������� �������� � ������� ��� ����������, ���� �� ������, �� ���
			playerTexture.loadFromFile(Paths.get("playerSkin1.png"));
		} catch(IOException ex) {
		    //� ���� ���, �� �� �����
		    ex.printStackTrace();
		}
		
		//��������� ��������-�������� ������ � ��� ������
		playerSkin = new Sprite(playerTexture);
		playerSkin.setTextureRect(intRect);
		playerSkin.setPosition(xPos, yPos);
	}
	void update() {
		playerSkin.setPosition(xPos, yPos);
	}
}
