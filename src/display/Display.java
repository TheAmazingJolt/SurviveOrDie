package display;

import java.awt.Canvas;
import java.awt.Dimension;
import javax.swing.JFrame;

import javafx.embed.swing.JFXPanel;

public class Display {

	public static JFrame frame;
    private Canvas canvas;
    private String title;
    private int width;
    private int height;
	
    public Display(String title, int width, int height) {
        this.title = title;
        this.width = width;
        this.height = height;
        createDisplay();
    }

    private void createDisplay() {
    	@SuppressWarnings("unused")
		JFXPanel fxPanel = new JFXPanel();
        frame = new JFrame(title);
        frame.setSize(width, height);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        canvas = new Canvas();
        canvas.setPreferredSize(new Dimension(width, height));
        canvas.setMaximumSize(new Dimension(width, height));
        canvas.setMinimumSize(new Dimension(width, height));
        canvas.setFocusable(false);
        frame.add(canvas);
        frame.pack();
    }

    public Canvas getCanvas() {
        return canvas;
    }

    public JFrame getFrame() {
        return frame;
    }

    
}
