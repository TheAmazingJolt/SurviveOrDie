package ui;

import java.awt.Color;
import java.awt.Graphics;

import gfx.Assets;
import gfx.Text;
import main.Handler;

public class Slider {

	private int x;
	private int y;
	private int width;
	private int height;
	
	private int titleX;
	private int titleY;
	private int upperX;
	private int upperY;
	private int lowerX;
	private int lowerY;
	private int middleX;
	private int middleY;
	private int mid1X;
	private int mid1Y;
	private int mid2X;
	private int mid2Y;
	private int sliderX;
	private int sliderY;
	private int lineX;
	private int lineY;
	private int lineWidth;
	private float lineLength;
	private int lineHeight;
	
	private int lowerLimitX;
	
	private int mouseX;
	private int mouseY;

	@SuppressWarnings("unused")
	private boolean centered;
	private boolean clicked;
	private boolean active = true;
	private boolean loaded = false;
	
	private String title;
	private int upper;
	private int lower;
	private int middle;
	private int mid1;
	private int mid2;
	
	private float currentSliderValue = 1.0f;
	
	private Handler handler;
	
	public Slider(int x, int y, int width, int height, String title, int upper, 
			int lower, int middle, int mid1, int mid2, boolean centered, Handler handler) {
		this.x = x;
		this.y = y;
		this.setWidth(width);
		this.height = height;
		this.centered = centered;
		this.handler = handler;
		this.title = title;
		this.upper = upper;
		this.lower = lower;
		this.middle = middle;
		this.mid1 = mid1;
		this.mid2 = mid2;
		if(centered) {
			this.x = x - width / 2;
		}
		upperX = this.x + width;
		upperY = y;
		lowerX = this.x;
		lowerY = y;
		middleX = this.x + (width/2);
		middleY = y + (height/4);
		mid1X = this.x + (width/4);
		mid1Y = y + (height/3);
		mid2X = this.x + 3*(width/4);
		mid2Y = y + (height/3);
		sliderX = this.x + width;
		sliderY = y + height / 2 - 12;
		lineX = this.x - 10;
		lineY = y + (height / 2) - 1;
		lineWidth = width + 25;
		lineHeight = 6;
		lineLength = width - 2;
		lowerLimitX = this.x + 2;
		titleX = this.x + width / 2;
		titleY = y - 10;
	}
	
	public void tick() {
		mouseX = handler.getMouseManager().getMouseX();
		mouseY = handler.getMouseManager().getMouseY();
		if(title.contains("Ambi") && !loaded) {
			float a = handler.getAudioManager().getAmbientVolume() * lineLength;
			sliderX = (int) (a + lowerLimitX);
			loaded = true;
		}if(title.contains("Effe") && !loaded) {
			float a = handler.getAudioManager().getEffectVolume() * lineLength;
			sliderX = (int) (a + lowerLimitX);
			loaded = true;
		}
		if(handler.getMouseManager().isLeftPressed()) {
			if(mouseX >= lineX && mouseX <= lineX + lineWidth) {
				if(mouseY >= lineY && mouseY <= lineY + lineHeight) {
					sliderX = mouseX;
					if(sliderX > upperX + 2)
						sliderX = upperX;
					else if(sliderX < lowerX)
						sliderX = lowerX;
				}
			}
		}
		if(sliderX == lowerX) {
			currentSliderValue = 0.0f;
		}else if(sliderX == upperX) {
			currentSliderValue = 1.0f;
		}else {
			currentSliderValue = (sliderX - lowerLimitX) / lineLength;
		}
	}
	
	public void render(Graphics g) {
		if(!active) {
			return;
		}
		g.setColor(Color.WHITE);
		Text.drawString(g, title, titleX, titleY, true, Color.WHITE, Assets.font28);
		//lower limit
		g.fillRect(lowerX, lowerY, 5, height);
		Text.drawString(g, Integer.toString(lower), lowerX + 2, y + height + 7, true, Color.WHITE, Assets.font20);
		//mid1 limit
		g.fillRect(mid1X, mid1Y, 5, 2*(height/4)-8);
		Text.drawString(g, Integer.toString(mid1), mid1X + 2, y + height + 7, true, Color.WHITE, Assets.font20);
		//middle limit
		g.fillRect(middleX, middleY, 5, 2*(height/3)-8);
		Text.drawString(g, Integer.toString(middle), middleX + 2, y + height + 7, true, Color.WHITE, Assets.font20);
		//mid2 limit
		g.fillRect(mid2X, mid2Y, 5, 2*(height/4)-8);
		Text.drawString(g, Integer.toString(mid2), mid2X + 2, y + height + 7, true, Color.WHITE, Assets.font20);
		//upper limit
		g.fillRect(upperX, upperY, 5, height);
		Text.drawString(g, Integer.toString(upper), upperX + 2, y + height + 7, true, Color.WHITE, Assets.font20);
		//line
		g.fillRect(lineX, lineY, lineWidth, lineHeight);
		//slider
		g.setColor(Color.GREEN);
		g.fillRect(sliderX, sliderY, 5, 25);
	}
	
	public boolean isClicked() {
		return clicked;
	}

	public void setX(int x) {
		this.x = x;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public float getCurrentSliderValue() {
		return currentSliderValue;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}
	
}
