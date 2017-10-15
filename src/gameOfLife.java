
/**
 * Creates new object of the view and model class then inputs them into the controller
 * The frame is then made visible
 */

import java.awt.*;
import javax.swing.*;

public class gameOfLife extends JFrame{

    public static void main(String[] args) {

    	EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {

                    gameOfLifeView theView = new gameOfLifeView();
                    gameOfLifeModel theModel = new gameOfLifeModel();

                    gameOfLifeController theController = new gameOfLifeController(theView, theModel);

                    theView.setVisible(true);
                    theView.setResizable(false);
            }
        });
    }
}
