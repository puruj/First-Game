package com.tutorial.main;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Random;

import com.tutorial.main.Game.STATE;

public class Menu extends MouseAdapter{
	
	private Game game;
	private Handler handler;
	private Random r = new Random();
	private HUD hud;
	
	public Menu (Game game, Handler handler, HUD hud){
		this.game= game;
		this.handler= handler;
		this.hud = hud;
	}
	
	public void mousePressed(MouseEvent e){
		int mx = e.getX();
		int my = e.getY();
		
		if (Game.gameState == STATE.Menu){
			//play button
			if (mouseOver(mx, my, 215, 100, 200, 64)){
				game.gameState = STATE.Game;
				handler.addObject(new Player(Game.WIDTH/2-32,Game.HEIGHT/2-32, ID.Player, handler));
				handler.clearEnemys();
				handler.addObject(new BasicEnemy(r.nextInt(Game.WIDTH-50),r.nextInt(Game.HEIGHT-50), ID.BasicEnemy, handler ));
				
				AudioPlayer.getSound("menu_sound").play();
			}
			//help button
			if (mouseOver(mx, my, 215,180, 200, 64)){
				game.gameState = STATE.Help;
				AudioPlayer.getSound("menu_sound").play();
			}

			//quit button
			if (mouseOver(mx, my, 215,260, 200, 64)){
				System.exit(0);
				
			}
		}

		

		// back to Menu
		if (game.gameState == STATE.Help){
			if (mouseOver(mx, my, 215,330, 200, 64)){
				game.gameState = STATE.Menu;
				AudioPlayer.getSound("menu_sound").play();
				return;
			}
		}
		// back to Game
		if (game.gameState == STATE.End){
			if (mouseOver(mx, my, 215,330, 200, 64)){
				game.gameState = STATE.Game;
				hud.setLevel(1);
				hud.setScore(0);
				handler.addObject(new Player(Game.WIDTH/2-32,Game.HEIGHT/2-32, ID.Player, handler));
				handler.clearEnemys();
				handler.addObject(new BasicEnemy(r.nextInt(Game.WIDTH-50),r.nextInt(Game.HEIGHT-50), ID.BasicEnemy, handler ));
				AudioPlayer.getSound("menu_sound").play();
			}
			}
		}
		
	
	
	public void mouseReleased (MouseEvent e){
		
	}
	
	private boolean mouseOver(int mx, int my, int x, int y, int width, int height){
		if(mx > x && mx < x + width){
			if (my > y && my < y + height){
				return true;
			}else return false;
		}else return false;
	}

	public void tick(){
		
	}
	public void render(Graphics g){
		if (Game.gameState == STATE.Menu){
		Font fnt = new Font("arial", 1, 50);
		
		g.setFont(fnt);
		g.setColor(Color.white);
		g.drawString("LINER", 247, 70);
		
		g.setFont(fnt);
		g.setColor(Color.white);
		g.drawString("PLAY", 250, 150);
		
		g.setFont(fnt);
		g.setColor(Color.white);
		g.drawString("HELP", 250, 230);
		
		g.setFont(fnt);
		g.setColor(Color.white);
		g.drawString("QUIT", 255, 310);
		
		g.setColor(Color.white);
		g.drawRect(215,100, 200, 64);
		
		g.setColor(Color.white);
		g.drawRect(215,180, 200, 64);
		
		g.setColor(Color.white);
		g.drawRect(215,260, 200, 64);
		}else if (game.gameState == STATE.Help){
			Font fnt = new Font("arial", 1, 50);
			Font fnt2 = new Font("arial", 1, 30);

			g.setFont(fnt);
			g.setColor(Color.white);
			g.drawString("HELP", 250, 70);
			
			g.drawString("MENU", 245, 380);
			g.drawRect(215,330, 200, 64);
			
			g.setFont(fnt2);
			g.drawString("Use W,A,S,D keys to move box", 100, 200);
			
		}else if (game.gameState == STATE.End){
			Font fnt = new Font("arial", 1, 50);
			Font fnt2 = new Font("arial", 1, 30);
			Font fnt3 = new Font("arial", 1, 20);

			g.setFont(fnt);
			g.setColor(Color.white);
			g.drawString("Game Over", 190, 70);
			
			g.setFont(fnt2);
			g.drawString("Try Again", 245, 370);
			g.drawRect(215,330, 200, 64);
			
			g.setFont(fnt3);
			g.drawString("You lost with a score of: " + hud.getScore(), 180, 200);
			
		}
		
	}
	
}
