package model;

import javafx.geometry.Rectangle2D;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;


import java.util.ArrayList;
import java.util.List;

public class HuzzleMaker {

    public HuzzleMaker(){}


    public List<HuzzlePuzzlePiece> hacemeCuadritos(Image aMi, int size){
        List<HuzzlePuzzlePiece> cuadricula = new ArrayList<>();
        double originalH = aMi.getHeight();
        double originalW = aMi.getWidth();

        double pedazoH = originalH / size;
        double pedazoW = originalW / size;

        for(int r = 0; r < size; r++){
            for(int c = 0; c < size; c++){

                //SKIP LAST PIECE, CREATE EMPTY PUZZLE PIECE
                if (r == size -1 && c == size -1){
                    HuzzlePuzzlePiece puzzlePiece = new HuzzlePuzzlePiece();
                    cuadricula.add(puzzlePiece);
                    continue;
                }

                HuzzlePuzzlePiece puzzlePiece = new HuzzlePuzzlePiece();
                ImageView pedazo = new ImageView(aMi);
                Rectangle2D moldeParaCortar = new Rectangle2D(pedazoW * r , pedazoH * c ,pedazoW,pedazoH);
                pedazo.setViewport(moldeParaCortar);
                puzzlePiece.setImageView(pedazo);
                cuadricula.add(puzzlePiece);
            }
        }
        return cuadricula;
    }
}
