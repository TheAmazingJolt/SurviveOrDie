package guis;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

import gfx.Assets;
import gfx.Text;

public class GuiManager {

	private ArrayList<Gui> guis = new ArrayList<Gui>();
	
	public GuiManager() {
		
	}
	
	public void render(Graphics g) {
		for(Gui gui : guis) {
			g.drawImage(gui.getTexture(), gui.getX(), gui.getY(), gui.getWidth(), gui.getHeight(), null);
			if(gui.getText2() == null) {
				Text.drawString(g, gui.getText(), gui.getX() + (gui.getWidth() / 2), 
						gui.getY() + (gui.getHeight() / 2), true, Color.BLACK, Assets.font28);
			}else if(gui.getText2() != null) {
				Text.drawString(g, gui.getText(), gui.getX() + (gui.getWidth() / 2), 
						gui.getY() + (gui.getHeight() / 3), true, Color.BLACK, Assets.font28);
				Text.drawString(g, gui.getText2(), gui.getX() + (gui.getWidth() / 2), 
						gui.getY() + (gui.getHeight() / 3) * 2, true, Color.BLACK, Assets.font28);
			}
			if(gui.getExtra() != null) {
				g.drawImage(gui.getExtra(), gui.getX() - 32, gui.getY() - 48, 96, 96, null);
			}
			if(gui.getSecondaryExtra() != null) {
				g.drawImage(gui.getSecondaryExtra(), gui.getX() + gui.getWidth() - 48, gui.getY() - 48, 96, 96, null);
			}
		}
	}
	
	public void addGui(Gui gui) {
		guis.add(gui);
	}

	public ArrayList<Gui> getGuis() {
		return guis;
	}
	
}
