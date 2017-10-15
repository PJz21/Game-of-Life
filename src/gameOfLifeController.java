
/**
 * Can send commands to the model to update the grid's state. It can also send commands to the view to change the view's presentation of the grid
 */

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class gameOfLifeController{

    private gameOfLifeView theView;
    private gameOfLifeModel theModel;
    private int[][] grid;
    private Timer timer;
    private int iterationCount = 0; 

    public gameOfLifeController(gameOfLifeView theView, gameOfLifeModel theModel) {

        //Instances of view and model class respectively
        this.theView = theView;
        this.theModel = theModel;

        //Run the respective class when a button is pressed
        this.theView.startButtonListener(new startListener());
        this.theView.pauseButtonListener(new pauseListener());
        this.theView.nextButtonListener(new nextListener());
        this.theView.resetButtonListener(new resetListener());

        //Start the timer, delay in ms
        timer = new Timer(300, new timerListener());

        //Create the grid with a size of the dimension variable
        int dimension = 100;
        grid = new int[dimension][dimension];

        //Generate grid
        grid = theModel.getGenerateGrid(grid);
        
        //Update the grid array in the view class and repaint the panel
        theView.setGrid(grid);
        theView.repaint();
    }

    /**
     * Runs when the start button is pressed
     */
    class startListener implements ActionListener {

        /**
         * When the start button start the timer and change which buttons are enabled and disabled
         */
        public void actionPerformed(ActionEvent e) {

            //Disable the next, start and reset button and enable the pause button
            changeButtonState(false, true, false, false);

            //Start the timer
            timer.start();
        }
    }

    /**
     * Runs when the pause button is pressed
     */
    class pauseListener implements ActionListener {

        /**
         * When the pause button is pressed stop the timer and which buttons are disabled and enabled
         */
        public void actionPerformed(ActionEvent e) {

            //Stop the timer
            timer.stop();

            //Enable the next, start and reset button and disable the pause button
            changeButtonState(true, false, true, true);
        }
    }

    /**
     * Runs when the next button is pressed
     */
    class nextListener implements ActionListener {

        /**
         * When the next button is pressed update the grid
         * If all of the cells in the grid have died then disable buttons
         */
        public void actionPerformed(ActionEvent e) {

            //Update the grid, iteration the repaint
            updateIteration();

            //If the whole grid is dead then disable all the buttons excluding reset
            if (theModel.getDead(grid)) {
                changeButtonState(false, false, false, true);
            }
        }
    }

    /**
     * Runs when the reset button is pressed
     */
    class resetListener implements ActionListener {

        /**
         * When the reset button is pressed stop the timer, generate a new grid, set the grid in the view,
         * reset the iteration count, change which buttons are enabled and disabled and repaint the frame
         */
        public void actionPerformed(ActionEvent e) {

            //Stop the timer, generate a new grid, pass it into the view class and repaint
            timer.stop();
            grid = theModel.getGenerateGrid(grid);
            theView.setGrid(grid);
            
            //Reset the iterationCount
            iterationCount = 0;
            theView.setIterationCount(iterationCount);

            //Enable the next, start and reset button and disable the pause button
            changeButtonState(true, false, true, true);

            //Repaint
            theView.repaint();
        }
    }

    /**
     * Runs when the timer reaches the delay
     */
    class timerListener implements ActionListener {

        /**
         * When the timer fires check whether all the cells are dead, and call the updateIteration method
         */
        public void actionPerformed(ActionEvent e) {

            //If the whole grid is dead stop iterating and disable all the buttons excluding reset
            if (theModel.getDead(grid)) {
                timer.stop();
                changeButtonState(false, false, false, true);
            }

            //Update the grid, iteration then repaint
            updateIteration();
        }
    }

    /**
     * Updates the grid, iteration number and repaints the frame
     */
    private void updateIteration() {

        //Update the grid and pass it into the view class
        grid = theModel.getUpdateGrid(grid);
        theView.setGrid(grid);

        //Increase iterationCount and pass it into the view class
        iterationCount++;
        theView.setIterationCount(iterationCount);

        //repaint
        theView.repaint();
    }

    /**
     * Enables and disable the buttons in the view class
     * @param start     //true enables the button, false disables
     * @param pause     //true enables the button, false disables
     * @param next      //true enables the button, false disables
     * @param reset     //true enables the button, false disables
     */
    private void changeButtonState(boolean start, boolean pause, boolean next, boolean reset) {
        if (start) {
            theView.disableStartButton(true);
        } else if (!start) {
            theView.disableStartButton(false);
        }

        if (pause) {
            theView.disablePauseButton(true);
        } else if (!pause) {
            theView.disablePauseButton(false);
        }

        if (next) {
            theView.disableNextButton(true);
        } else if (!next) {
            theView.disableNextButton(false);
        }

        if (reset) {
            theView.disableResetButton(true);
        } else if (!reset) {
            theView.disableResetButton(false);
        }
    }
}
