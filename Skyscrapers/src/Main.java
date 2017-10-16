import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Scanner;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;


public class Main extends JPanel implements MouseListener{
	
	private BufferedImage base;
	
	private int[][] correct = new int[4][4];
	private int[][] board = new int[4][4];
	
	private int[][] hints = new int[4][4];
	
	private int[][] places1 = new int[][]{{106,210},{212,210},{318,210},{426,210},
										  {106,318},{212,318},{318,318},{426,318},
										  {106,426},{212,426},{318,426},{426,426},
										  {106,530},{212,530},{318,530},{426,530}};
	private int[][] places2 = new int[][]{{106,106},{212,106},{318,106},{424,106},
										  {530,210},{530,318},{530,426},{530,530},
										  {426,640},{318,640},{212,640},{106,640},
										  {0,530},{0,426},{0,318},{0,210}};
	
	Font font = new Font("Times New Roman", 0, 126);
	Font wonF = new Font("Times New Roman", 0, 126);
	
	private boolean won = false;
	
	public static void main(String[] args) {
		
		JFrame j = new JFrame();
		Main main = new Main();
		j.setResizable(false);
		j.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		j.add(main);
		j.pack();
		j.setLocationRelativeTo(null);
		j.setTitle("Towers Alpha vs. 0.0.143");
		
		j.setVisible(true);
		
	}
	
	public Main(){
		
		setPreferredSize(new Dimension(640,640));
		
		setFocusable(true);
		addMouseListener(this);
		
		try {
			base = ImageIO.read(ImageIO.class.getResourceAsStream("/Base.png"));
			System.out.println("images loaded");
		} catch (IOException e) {
			System.err.println("could not load images");
			e.printStackTrace();
		}
		
		loadMap("Level1");
		
		for(int x = 0; x < 4; x++){
			for(int y = 0; y < 4; y++){
				
				board[x][y] = 1;
				
			}
		}
		
	}
	
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		
		if(!won){
			g.setFont(font);
			g.drawImage(base, 0, 0, null);
			g.setColor(Color.red);
			for(int x = 0; x < 4;x++){
				for(int y = 0; y < 4; y++){
					g.drawString(String.valueOf(board[x][y]), places1[x+y*4][0] + 22, places1[x+y*4][1]-2);
				}
			}
			
			g.setColor(Color.blue);
			for(int x = 0; x < 4;x++){
				for(int y = 0; y < 4; y++){
					g.drawString(String.valueOf(hints[x][y]), places2[x+y*4][0] + 22, places2[x+y*4][1]-2);
				}
			}
		}else{
			
			g.setFont(wonF);
			g.setColor(Color.black);
			g.drawString("You Won", 60, 300);
			
		}
		
	}
	
	public void loadMap(String fileName){
		
		Scanner in = new Scanner(getClass().getResourceAsStream("Level1"));
		
		String fir = in.nextLine();
		String sec = in.nextLine();
		
		in.close();
		
		String[] firC = fir.split("");
		String[] secC = sec.split("");
		
		for(int x = 0; x < 4; x++){
			for(int y = 0; y < 4; y++){
				
				correct[x][y] = Integer.parseInt(firC[x + y*4]);
				hints[x][y] = Integer.parseInt(secC[x + y*4]);
				
			}
		}
		
	}

	public void mouseClicked(MouseEvent e) {
		
		int x = (int)(e.getX()/106)-1;
		int y = (int)(e.getY()/106)-1;
		
		if(!(x >= 0 && x <= 3 && y >= 0 && y <= 3))return;
		
		board[x][y]++;
		if(board[x][y] > 4)board[x][y] = 1;
		
		
		boolean w = true;
		
		for(int xc = 0; xc < 4; xc++){
			for(int yc = 0; yc < 4;yc++){
				if(board[xc][yc] != correct[xc][yc])w = false;
			}
		}
		
		if(w)won = true;
		repaint();
		
	}

	public void mouseEntered(MouseEvent e) {
		
	}

	public void mouseExited(MouseEvent e) {
		
	}

	public void mousePressed(MouseEvent e) {
		
	}

	public void mouseReleased(MouseEvent e) {
		
	}
	
	
	
}
