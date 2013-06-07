import java.awt.geom.Point2D;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;


public class PacMan extends Personaje{
	
	private BufferedImage img;	// pacman que se tiene que diburar en un instante determinado.
	
	public PacMan(Laberinto l, String fichero) {
		super(NombresPersonaje.PACMAN, l, true, 6, 5);
		try {
			img = ImageIO.read(PacMan.class.getResource(fichero));
		} catch (IOException e) {}
	}

	public BufferedImage getImg() {
		return img;
	}
	
	
	public void mover(double velocidad, Direccion nuevaDireccion){
		// El parámetro nuevaDirección representa la dirección que tiene que tomar si fuese posible.
		super.mover(velocidad);
		if (getDireccion() != nuevaDireccion && puedeCambiarDireccion(nuevaDireccion))
			setDireccion(nuevaDireccion);
	}
}