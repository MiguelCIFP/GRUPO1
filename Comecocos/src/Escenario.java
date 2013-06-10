import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.KeyStroke;

public class Escenario extends JPanel implements Runnable,ActionListener{
	private PacMan pacman;
	private CtrlJuego cJ;
	private Laberinto l=Laberinto.ORIGINAL;
	Thread t;
	public Escenario() {
		cJ=new CtrlJuego();
		pacman= cJ.getPacman();
		setPreferredSize(new Dimension(l.getImagen().getWidth(),l.getImagen().getHeight()));
		registerKeyboardAction(this, "iniciar",
				  KeyStroke.getKeyStroke(KeyEvent.VK_I, 0),
				  JComponent.WHEN_IN_FOCUSED_WINDOW);
		registerKeyboardAction(this, "arriba",
				  KeyStroke.getKeyStroke(KeyEvent.VK_UP, 0),
				  JComponent.WHEN_IN_FOCUSED_WINDOW);
		registerKeyboardAction(this, "abajo",
				  KeyStroke.getKeyStroke(KeyEvent.VK_DOWN, 0),
				  JComponent.WHEN_IN_FOCUSED_WINDOW);
		registerKeyboardAction(this, "izquierda",
				  KeyStroke.getKeyStroke(KeyEvent.VK_LEFT, 0),
				  JComponent.WHEN_IN_FOCUSED_WINDOW);
		registerKeyboardAction(this, "derecha",
				  KeyStroke.getKeyStroke(KeyEvent.VK_RIGHT, 0),
				  JComponent.WHEN_IN_FOCUSED_WINDOW);
	}
	@Override
	protected void paintComponent(Graphics g) {
		 
		g.drawImage(l.getImagen(), 0, 0, this);
		int x = (int) pacman.getPosicion().x;
		int y = (int) pacman.getPosicion().y;
		int xs=pacman.getDsx();
		int ys=pacman.getDsy();
		g.drawImage(pacman.getImg(), x, y, x+35, y+35, xs, ys, xs+35, ys+35, this);
		
	}
	public void iniciar(){
		cJ.inicioJuego();
		t=new Thread(this);
		t.start();
	}
	@Override
	public void run() {
		while(true){
			repaint();
			try {
				Thread.sleep(16);
			} catch (InterruptedException e) {
				
			}
		}
		
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getActionCommand().equals("iniciar"))
				iniciar();
		else if(e.getActionCommand().equals("arriba"))
			cJ.cambiarDireccion(Direccion.ARRIBA);
		else if(e.getActionCommand().equals("abajo"))
			cJ.cambiarDireccion(Direccion.ABAJO);
		else if(e.getActionCommand().equals("izquierda"))
			cJ.cambiarDireccion(Direccion.IZDA);
		else if(e.getActionCommand().equals("derecha"))
			cJ.cambiarDireccion(Direccion.DERECHA);
	}
	
}
