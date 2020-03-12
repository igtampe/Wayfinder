package input;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;

/**
 * Created by AlexVR on 7/1/2018.
 * Suplemented by Chopo on 3/11/2020
 */
public class MouseManager implements MouseListener,MouseMotionListener, MouseWheelListener{

    private boolean leftPressed,rightPressed;
    private int mouseX,mouseY;
    private Window MainWindowManager;

    public MouseManager(Window windowmanager){
    	if (windowmanager==null) throw new IllegalArgumentException("windowManager canot be null");
    	this.MainWindowManager=windowmanager;
    }

    public void setUimanager(Window windowmanager){
    	if (windowmanager==null) throw new IllegalArgumentException("windowManager canot be null");
    	this.MainWindowManager=windowmanager;
    }

    public boolean isLeftPressed() {return leftPressed;}
    public boolean isRightPressed() {return rightPressed;}
    public int getMouseX() {return mouseX;}
    public int getMouseY() {return mouseY;}

    @Override
    @Deprecated
    public void mouseClicked(MouseEvent e) {}

    @Override
    public void mousePressed(MouseEvent e) {
        if(e.getButton()==MouseEvent.BUTTON1){leftPressed=true;}
        else if(e.getButton()==MouseEvent.BUTTON3){rightPressed=true;}
        MainWindowManager.onMousePressed(e);
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        if(e.getButton()==MouseEvent.BUTTON1){leftPressed=false;}
        else if(e.getButton()==MouseEvent.BUTTON3){rightPressed=false;}
        MainWindowManager.onMouseRelease(e);
    }

    @Override
    public void mouseEntered(MouseEvent e) {}

    @Override
    public void mouseExited(MouseEvent e) {}

    @Override
    public void mouseDragged(MouseEvent e) {
    	 mouseX = e.getX();
         mouseY = e.getY();
         MainWindowManager.onMouseDragged(e);
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        mouseX = e.getX();
        mouseY = e.getY();
        MainWindowManager.onMouseMove(e);
    }

	@Override
	public void mouseWheelMoved(MouseWheelEvent e) {MainWindowManager.onMouseWheelMove(e);}
}
