package model;

import javafx.geometry.Rectangle2D;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;


public class HuzzleMaker {
    public HuzzleMaker(){}
    public ImageView[][] hacemeCuadritos(Image aMi, int rows, int cols, Pane pan){
        ImageView[][] cuadricula = new ImageView[rows][cols];
        double originalH = aMi.getHeight();
        double originalW = aMi.getWidth();

        double pedazoH = originalH / rows;
        double pedazoW = originalW / cols;

        double starterX = 0;
        double starterY = 0;

        System.out.println(originalW + "  x  " + originalH + "\n\n");
        for(int r = 0; r < rows; r++){
            starterX = 0;
            for(int c = 0; c < cols; c++){
                ImageView pedazo = new ImageView(aMi);
                Rectangle2D moldeParaCortar = new Rectangle2D(starterX,starterY,pedazoW,pedazoH);
                pedazo.setCache(true);
                pedazo.setViewport(moldeParaCortar);
                cuadricula[r][c] = pedazo;
                /*pedazo.setLayoutX(starterX);
                pedazo.setLayoutY(starterY);
                pan.getChildren().add(pedazo);*/
                starterX =+ pedazoW;
            }
            starterY += pedazoH;
        }
        return cuadricula;
    }

}
