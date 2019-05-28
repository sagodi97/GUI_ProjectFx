package model;

import javafx.scene.image.ImageView;

public class HuzzlePuzzlePiece {
    private ImageView imageView;
    private int row;
    private int col;
    private int solvedRow;
    private int solvedCol;

    public  HuzzlePuzzlePiece(){

    }

    public ImageView getImageView(){
        return this.imageView;
    }

    public void setImageView(ImageView imageView){
        this.imageView = imageView;
    }

    public void setPos(int col, int row){
        this.row = row;
        this.col = col;
    }

    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }

    public void setSolvedPos(int col, int row){
        this.solvedRow = row;
        this.solvedCol = col;
    }

    public int getSolvedCol() {
        return solvedCol;
    }

    public int getSolvedRow() {
        return solvedRow;
    }
}
