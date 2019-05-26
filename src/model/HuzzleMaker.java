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

        System.out.println(originalW + "  x  " + originalH + "\n\n");
        for(int r = 0; r < size; r++){
            for(int c = 0; c < size; c++){
                ImageView pedazo = new ImageView(aMi);
                Rectangle2D moldeParaCortar = new Rectangle2D(pedazoW * r , pedazoH * c ,pedazoW,pedazoH);
                pedazo.setViewport(moldeParaCortar);
                HuzzlePuzzlePiece puzzlePiece = new HuzzlePuzzlePiece(pedazo);
                cuadricula.add(puzzlePiece);
            }
        }
        return cuadricula;
    }
}
