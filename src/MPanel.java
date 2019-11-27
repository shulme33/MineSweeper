import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.*;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author SamHulme
 */
public class MPanel extends JPanel{
    
    
    private String[][] board;
    private String[][] board2;
    private boolean[][] board3;
    private int dist = 15;
    private int size = dist*50;
    private int numMines = 16;
    private String value = " ";
    private boolean allClear = true;
    
    
    public MPanel(){
        setPreferredSize(new Dimension(size,size));
        addMouseListener(new MineSweeperMouseListener());
        board2 = new String[dist][dist];
        for (int i = 0; i < dist; i++) {
            for (int j = 0; j < dist; j++) {
                board2[i][j] = " ";
            }
        }
        
        board3 = new boolean[dist+2][dist+2];
            for (int i = 0; i < dist+2; i++) {
                for (int j = 0; j < dist+2; j++) {
                    board3[i][j] = false;
                }
            }
            
        board3 = new boolean[dist+2][dist+2];
            for (int i = 1; i < dist+1; i++) {
                for (int j = 1; j < dist+1; j++) {
                    board3[i][j] = true;
                }
            }
            
        board = new String[dist][dist];
        for (int i = 0; i < dist; i++) {
            for (int j = 0; j < dist; j++) {
                board[i][j] = "O";
            }
        }
        for(int i = 0; i < numMines; i++){
            boolean goodMine = false;
            int ranMine1 = 0;
            int ranMine2 = 0;
            while(!goodMine){
                ranMine1 = (int)(Math.random()*dist);
                ranMine2 = (int)(Math.random()*dist);
                if(board[ranMine1][ranMine2] != "M"){
                    goodMine = true;
                }
            }
                board[ranMine1][ranMine2] = "M";
        }
        setMineNumbers();
    }
    
    
    
    public void setMineNumbers(){
        for (int i = 0; i < dist; i++) {
            for (int j = 0; j < dist; j++) {
               if(board[i][j] == "M"){
                   setNumVals(i,j+1);
                   setNumVals(i+1,j);
                   setNumVals(i+1,j+1);
                   setNumVals(i+1,j-1);
                   
                   setNumVals(i,j-1);
                   setNumVals(i-1,j);
                   setNumVals(i-1,j+1);
                   setNumVals(i-1,j-1);
               }
            }
        }    
    }
    
    
    public void gameOver(String spot){
        if(spot == "M"){
            JOptionPane.showMessageDialog(this, "You Hit A Mine! You Lose!");
            System.exit(0);
        }
        if(checkFullBoard()){
            JOptionPane.showMessageDialog(this, "You Win!");
            System.exit(0);
        }
    }
    
    public boolean checkFullBoard(){
        int numChangedSpaces = 0;
        for(int i = 0; i <= dist-1; i++){
            for(int j = 0; j <= dist-1; j++){
                if(board2[i][j] != " " && board2[i][j] != "F")
                {
                    numChangedSpaces++;
                }
            }
        }
        if(numChangedSpaces >= ((dist*dist)-(numMines))){
            return true;
        }
        else{
            return false;
        }
    }
      
    public void setNumVals(int row, int col){
        if(board3[row+1][col+1]){
            if(board[row][col] == "O" && board[row][col] != "M"){
                board[row][col] = "1";
            }
            else if(board[row][col] == "1"){
                board[row][col] = "2";
            }
            else if(board[row][col] == "2"){
                board[row][col] = "3";
            }
            else if(board[row][col] == "4"){
                board[row][col] = "5";
            }
            else if(board[row][col] == "5"){
                board[row][col] = "6";
            }
            else if(board[row][col] == "6"){
                board[row][col] = "7";
            }
            else if(board[row][col] == "7"){
                board[row][col] = "8";
            }
        }
    }

    
    
    public void paint(Graphics g){
        
        g.setColor(Color.LIGHT_GRAY);
        g.fillRect(0, 0, getWidth(), getHeight());
        
        g.setColor(Color.BLACK);
        //set horizontal lines
        for(int i = 1; i <= dist-1; i++){
            g.drawLine(0, i*50, size, i*50);
        }
        
        //set vertical lines
        for(int i = 1; i <= dist-1; i++){
            g.drawLine(i*50, 0, i*50, size);
        }
        
        
        
        Font f = new Font("Times", Font.PLAIN, 30);
        g.setFont(f);
        
        for(int i = 0; i<= dist-1; i++){
            for(int j = 0; j<= dist-1; j++){
                if(board2[i][j] == "O"){
                    g.setColor(Color.DARK_GRAY);
                }
                else if(board2[i][j] == "1"){
                    g.setColor(Color.BLUE);
                }
                else if(board2[i][j] == "2"){
                    g.setColor(Color.MAGENTA);
                }
                else if(board2[i][j] == "3"){
                    g.setColor(Color.RED);
                }
                else if(board2[i][j] == "M" || board2[i][j] == "F"){
                    g.setColor(Color.ORANGE);
                }
                else{
                    g.setColor(Color.GREEN);
                }
                g.drawString(board2[i][j],(i*50)+15,(j*50)+35);
            }
        }
    }
    
    
    public void killAllZeroes(){
        int goodMove = 0;
        for(int i = 0; i <= dist-1; i++){
            for(int j = 0; j <= dist-1; j++){
                killZeroes(i,j);
                if(allClear == false){
                    goodMove++;
                }
            }
        }
        
        
    }
    public void killZeroes(int i, int j){
        int goodMove = 0;
        if(board2[i][j] == "O"){
            if(board3[i+1][j+2]){
                //if(board2[i][j+1] == " "){
                board2[i][j+1] = board[i][j+1];
                goodMove++;
            }
            if(board3[i+2][j+1]){
                //if(board2[i+1][j] == " "){
                board2[i+1][j] = board[i+1][j];
                goodMove++;
            }
            if(board3[i+2][j+2]){
                //if(board2[i+1][j+1] == " "){
                board2[i+1][j+1] = board[i+1][j+1];
                goodMove++;
            }
            if(board3[i+2][j]){
                //if(board2[i+1][j-1] == " ")
                board2[i+1][j-1] = board[i+1][j-1];
                goodMove++;
            }
            if(board3[i+1][j]){
                //if(board2[i][j-1] == " "){
                board2[i][j-1] = board[i][j-1];
                goodMove++;
            }
            if(board3[i][j+1]){
                //if(board2[i-1][j] == " "){
                board2[i-1][j] = board[i-1][j];
                goodMove++;
            }
            if(board3[i][j]){
               // if(board2[i-1][j-1] == " "){
                board2[i-1][j-1] = board[i-1][j-1];
                goodMove++;
            }
            if(board3[i][j+2]){
                //if(board2[i-1][j+1] == " "){
                board2[i-1][j+1] = board[i-1][j+1];
                goodMove++;
            }
        }
    }
    

    private class MineSweeperMouseListener implements MouseListener {

        @Override
        public void mousePressed(MouseEvent e) {
            //System.out.println("press");
        }

        public void mouseReleased(MouseEvent e) {
            //System.out.println("release");
        }

        public void mouseEntered(MouseEvent e) {
            //System.out.println("mouse entered");
        }

        public void mouseExited(MouseEvent e) {
            //System.out.println("mouse exited");
        }

        //front-end to back-end.
        public void mouseClicked(MouseEvent e) {
            if(SwingUtilities.isLeftMouseButton(e)){
                //get click data from the GUI and convert to back end board spot reference
                allClear = false;
                int blankSpace = 0;
                int x = e.getX() / 50;
                int y = e.getY() / 50;
                if(board2[x][y]!= " "){
                    blankSpace = 1;
                }
                    board2[x][y] = board[x][y];
                if(board2[x][y] == "O" && blankSpace == 0){
                    killZeroes(x,y);
                    for(int i = 0; i <= 10; i++){
                        killAllZeroes();
                    }
                    
                }
                repaint();
                gameOver(board[x][y]);
                }
            else if(SwingUtilities.isRightMouseButton(e)){
                int x = e.getX() / 50;
                int y = e.getY() / 50;
                
                if(board2[x][y] == " "){
                    board2[x][y] = "F";
                }
                else if(board2[x][y] == "F"){
                    board2[x][y] = " ";
                }
            repaint();
            }
        }
    }
}
