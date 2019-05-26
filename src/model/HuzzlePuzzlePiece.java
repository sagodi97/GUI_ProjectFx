package model;

import javafx.scene.image.ImageView;

public class HuzzlePuzzlePiece {
    private ImageView imageView;
    private int row;
    private int col;

    public  HuzzlePuzzlePiece(ImageView imageView, int row, int col){
        this.imageView = imageView;
        this.row = row;
        this.col = col;
    }

    public HuzzlePuzzlePiece(ImageView imageView){
        this.imageView = imageView;
    }

    public ImageView getImageView(){
        return this.imageView;
    }

    public void setImageView(ImageView imageView){
        this.imageView = imageView;
    }

    public void setPos(int row, int col){
        this.row = row;
        this.col = col;
    }
}
