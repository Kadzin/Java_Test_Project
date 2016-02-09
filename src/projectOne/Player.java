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
	}
	
	void update(float time) {
		//����� ������� ��� �������� ����������� ������
		xPos = xPos + (xSpeed*time);//�� ��� �
		//��������� ������������ �� ��� �
		collision(1);
		yPos = yPos + (ySpeed*time);//�� ��� �
		//��������� ������������ �� ��� �
		collision(0);
		playerSkin.setPosition(xPos, yPos);//�, �����, ���������� ��� ����
		//�������� ��������, ����� ����� �� �������� �����!
		xSpeed = ySpeed = 0;
	}
	//E;t
	void collision(int collisionType) {
		//���������� tileMap � ���� "�����������" ���������, �.�. ���� ������������
		for(int j= (int)xPos/64; j<(((int)xPos+32)/64)+1; j++) {
			for(int i=(int)yPos/64; i<(((int)yPos+32)/64)+1; i++) {	
				//���� ����� �� ����������� �����
				if(Main.tileMap[i][j]==" ");
				else
				{
					if((ySpeed<0) & (collisionType==0)) yPos = i*64 + 65;
					if((ySpeed>0) & (collisionType==0)) yPos = i*64 - 33;//31 ��� ������ ���������!!!
					if((xSpeed<0) & (collisionType==1)) xPos = j*64 + 65;
					if((xSpeed>0) & (collisionType==1)) xPos = j*64 - 33;//31 ��� ������ ���������!!!	
				}
				//���� ������� tileMap ����� S, �� �������� ��� �� ������(��� �������� �������)
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
