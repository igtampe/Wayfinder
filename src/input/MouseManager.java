package input;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import Wayfinder.TheCenterOfAttention;

/**
 * Created by AlexVR on 7/1/2018.
 */
public class MouseManager implements MouseListener,MouseMotionListener{

    private boolean leftPressed,rightPressed;
    private int mouseX,mouseY;
    private TheCenterOfAttention MainWindowManager;

    public MouseManager(TheCenterOfAttention windowmanager){
    	this.MainWindowManager=windowmanager;
    }

    public void setUimanager(TheCenterOfAttention windowmanager){
        this.MainWindowManager=windowmanager;
    }

    public boolean isLeftPressed() {
        return leftPressed;
    }

    public boolean isRightPressed() {
        return rightPressed;
    }

    public int getMouseX() {
        return mouseX;
    }

    public int getMouseY() {
        return mouseY;
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
        if(e.getButton()==MouseEvent.BUTTON1){
            leftPressed=true;
        }
        else if(e.getButton()==MouseEvent.BUTTON3){
            rightPressed=true;
        }
        if(MainWindowManager !=null){
            MainWindowManager.onMousePressed(e);
        }

    }

    @Override
    public void mouseReleased(MouseEvent e) {
        if(e.getButton()==MouseEvent.BUTTON1){
            leftPressed=false;
        }
        else if(e.getButton()==MouseEvent.BUTTON3){
            rightPressed=false;
        }
        if(MainWindowManager !=null){
            MainWindowManager.onMouseRelease(e);
        }
    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void mouseDragged(MouseEvent e) {
    	 mouseX = e.getX();
         mouseY = e.getY();
         if(MainWindowManager !=null){
             MainWindowManager.onMouseDragged(e);
         }
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        mouseX = e.getX();
        mouseY = e.getY();
        if(MainWindowManager !=null){
            MainWindowManager.onMouseMove(e);
        }

    }
}
