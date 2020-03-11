package input;

import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;

/**
 * Window interface to use with AlexVR's Key and Mouse managers
 * @author igtampe
 *
 */
public interface Window {
	public void onKeyPress(KeyEvent e);
	public void onMousePressed(MouseEvent e);
	public void onMouseRelease(MouseEvent e);
	public void onMouseEntered(MouseEvent e);
	public void onMouseExited(MouseEvent e);
	public void onMouseDragged(MouseEvent e);
	public void onMouseMove(MouseEvent e);
	public void onMouseWheelMove(MouseWheelEvent e);
	
}
