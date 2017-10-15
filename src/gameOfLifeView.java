
/**
 * Generates a new output to the user based on changes in the model
 * Buttons = start, pause, reset, next
 * Labels = programDescription, iterationCount
 * Graphics = grid
 */

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class gameOfLifeView extends JFrame {

    private JButton startButton;
    private JButton pauseButton;
    private JButton nextButton;
    private JButton resetButton;

    private JLabel iterationCountDescription = new JLabel();

    private int[][] gridCopy;
    
    private int iterationCount;

    //window size in pixels
    private int frameWidth = 900;
    private int frameHeight = 900;

    /**
     * Add the GUI elements to the frame
     */
    gameOfLifeView() {

        //Labels
        JLabel gameOfLifeDescription = new JLabel("yellow = alive    grey = dead");

        //Buttons
        startButton = new JButton("Start");
        pauseButton = new JButton("Pause");
        pauseButton.setEnabled(false);
        nextButton = new JButton("Next");
        resetButton = new JButton("Reset");
             
        //Create a new JPanel
        JPanel gameOfLifePanel = new JPanel();

        //Set the default operation for when the exit button is pressed
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //Set the location for where the window will open
        this.setLocation(100, 100);

        //Size of the window
        this.setSize(frameWidth, frameHeight);

        //Add the description label to the panel
        gameOfLifePanel.add(gameOfLifeDescription);

        //Add the buttons to the panel
        gameOfLifePanel.add(startButton);
        gameOfLifePanel.add(pauseButton);
        gameOfLifePanel.add(nextButton);
        gameOfLifePanel.add(resetButton);

        //Add the iteration labels to the panel
        gameOfLifePanel.add(iterationCountDescription);

        //Add the panel to the frame
        this.add(gameOfLifePanel);
    }

    /**
     * Paint the grid and the iteration count label on to the frame
     */
    @Override
    public void paint(Graphics g) {

        super.paint(g);

        //desired grid size in pixels
        int desiredGridSize = 700;

        //border padding
        int borderWidth = (frameWidth - desiredGridSize) / 2;
        int borderHeight = (frameHeight - desiredGridSize) / 2;
        
        //Width and height of the cell
        int cellSize = desiredGridSize / gridCopy.length;

        //Iteration count label
        iterationCountDescription.setText("Iteration: " + iterationCount);

        //If the grid is not empty iterate through each cell and paint red or black depending on whether it is alive or dead
        if (gridCopy != null) {
            for (int i = 0; i < gridCopy.length; ++i) {
                for (int j = 0; j < gridCopy.length; ++j) {

                    //Set the variable shading to the value in the cell
                    int shading = gridCopy[i][j];

                    //If shading is greater than 10 or less than -10 then set it to 10 and -10 respectively
                    if (shading > 10) {
                        shading = 10;
                    } else if (shading < -10) {
                        shading  = -10;
                    }

                    //If the cell is alive paint yellow, if the cell is dead paint grey else paint white
                    //Depending on how many iterations a cell has been alive the cell will be a lighter colour
                    if (shading > 0) {
                        g.setColor(new Color(200, 200, 0, (255 - (15 * shading)))); //yellow
                    } else if (shading < 0) {
                        g.setColor(new Color(0, 50, 50, (255 - (5 * Math.abs(shading))))); //grey
                    } else {
                        g.setColor(Color.white);
                    }

                    //Paint the rectangle in the grid
                    g.fillRect((borderWidth + (i * cellSize)), (borderHeight + (j * cellSize )), cellSize, cellSize);
                }
            }
        }
    }

    /**
     * Listen for the start button to be pressed
     */
    void startButtonListener(ActionListener listenForStartButton) {

        startButton.addActionListener(listenForStartButton);
    }

    /**
     * Listen for the pause button to be pressed
     */
    void pauseButtonListener(ActionListener listenForPauseButton) {

        pauseButton.addActionListener(listenForPauseButton);
    }

    /**
     * Listen for the next button to be pressed
     */
    void nextButtonListener(ActionListener listenForTheNextButton) {

        nextButton.addActionListener(listenForTheNextButton);
    }

    /**
     * Listen for the reset button to be pressed
     */
    void resetButtonListener(ActionListener listenForTheResetButton) {

        resetButton.addActionListener(listenForTheResetButton);
    }

    /**
     * Enable or disable the start button
     * @param enable    //true enables the button, false disables
     */
    public void disableStartButton(boolean enable) {
        if (enable) {
            startButton.setEnabled(true);
        } else {
            startButton.setEnabled(false);
        }
    }

    /**
     * Enable or disable the pause button
     * @param enable    //true enables the button, false disables
     */
    public void disablePauseButton(boolean enable) {
        if (enable) {
            pauseButton.setEnabled(true);
        } else {
            pauseButton.setEnabled(false);
        }
    }


    /**
     * Enable or disable the next button
     * @param enable    //true enables the button, false disables
     */
    public void disableNextButton(boolean enable) {
        if (enable) {
            nextButton.setEnabled(true);
        } else {
            nextButton.setEnabled(false);
        }
    }

    /**
     * Enable or disable the reset button
     * @param enable    //true enables the button, false disables
     */
    public void disableResetButton(boolean enable) {
        if (enable) {
            resetButton.setEnabled(true);
        } else {
            resetButton.setEnabled(false);
        }
    }

    /**
     * Setter for the grid to allow it to be painted
     * @param grid  //input grid to be painted
     */
    public void setGrid(int[][] grid) {
        gridCopy = grid;
    }

    /**
     * Setter for the iteration counter allowing it to be added to the JLabel and repainted
     * @param count //input for the iteration count variable
     */
    public void setIterationCount(int count) {
    	iterationCount = count;
    }
}