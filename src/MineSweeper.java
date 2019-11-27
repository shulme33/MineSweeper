
import javax.swing.*;


/**
 * In class TicTacToe example
 * 
 * @author yaw (adapted from mumey)
 * @version 13 Jan 2015
 */
public class MineSweeper extends JFrame {
    //constructor
    public MineSweeper() {
        super("MineSweeper");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getContentPane().add(new MPanel());
        pack();
        setLocationRelativeTo(null);    //put the gui in the center of the screen
        setVisible(true);
    }

    public static void main(String[] args) {
        MineSweeper newGame = new MineSweeper(); 
    }
}
