package guis;

import java.awt.image.BufferedImage;

import main.Handler;

public class Gui {

	private BufferedImage texture;
	private BufferedImage extra;
	private BufferedImage secondaryExtra;
	
	private int x;
	private int y;
	private int width;
	private int height;
	
	private String text;
	private String text2;
	
	public Gui(BufferedImage texture, int x, int y, int width, int height, Handler handler) {
		this.texture = texture;
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		handler.getGuiManager().addGui(this);
	}
	
	public Gui(BufferedImage texture, int x, int y, int width, int height, String text, Handler handler) {
		this.texture = texture;
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.text = text;
		handler.getGuiManager().addGui(this);
	}
	
	public Gui(BufferedImage texture, BufferedImage extra, BufferedImage secondaryExtra ,int x, int y, int width, int height, String text, Handler handler) {
		this.texture = texture;
		this.extra = extra;
		this.secondaryExtra = secondaryExtra;
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.text = text;
		handler.getGuiManager().addGui(this);
	}
	
	public Gui(BufferedImage texture, BufferedImage extra, BufferedImage secondaryExtra ,int x, int y, int width, int height, String text, String text2, Handler handler) {
		this.texture = texture;
		this.extra = extra;
		this.secondaryExtra = secondaryExtra;
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.text = text;
		this.text2 = text2;
		handler.getGuiManager().addGui(this);
	}

	public BufferedImage getTexture() {
		return texture;
	}

	public BufferedImage getExtra() {
		return extra;
	}

	public BufferedImage getSecondaryExtra() {
		return secondaryExtra;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}
	
	public void setExtra(BufferedImage extra) {
		this.extra = extra;
	}

	public void setSecondaryExtra(BufferedImage secondaryExtra) {
		this.secondaryExtra = secondaryExtra;
	}

	public String getText2() {
		return text2;
	}

	public void setText2(String text) {
		this.text2 = text;
	}
	
}
