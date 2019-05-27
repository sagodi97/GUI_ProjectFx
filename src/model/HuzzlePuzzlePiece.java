package model;

import javafx.scene.image.ImageView;

public class HuzzlePuzzlePiece {
    private ImageView imageView;
    private int row;
    private int col;

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
}
