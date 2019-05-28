package view;

import javafx.concurrent.Task;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import model.HERO;
import model.HuzzleLabel;
import model.HuzzleMaker;
import model.HuzzlePuzzlePiece;


import java.util.Collections;
import java.util.List;
import java.util.Optional;

class GameViewManager {

    private BorderPane borderPane;
    private Pane headerPane;
    private GridPane gamePane;
    private Scene gameScene;
    private Stage gameStage;
    private Stage menuStage;
    private HuzzleLabel counter;
    private HuzzleLabel info;
    private HBox panelBar;
    private HERO chosenHero;

    private int puzzleSize;
    private int blankCol;
    private int blankRow;
    private boolean gameOn;
    private int seconds;
    private int minutes;


    private String imageUrl = "https://scontent-waw1-1.cdninstagram.com/vp/5c3b5abb323beef9bfc4e3297cf74b60/5D7DFE60/t51.2885-15/e35/56669865_297095761190054_3170013358027075306_n.jpg?_nc_ht=scontent-waw1-1.cdninstagram.com";
    private String tmpUrl = "https://www.esa.int/var/esa/storage/images/esa_multimedia/images/2018/03/italy_and_mediterranean/17402074-1-eng-GB/Italy_and_Mediterranean_node_full_image_2.jpg";
    private Image pepe= new Image(tmpUrl,600,600,false,true);
    private List<HuzzlePuzzlePiece> puzzleBoard;


    private HuzzleMaker arquitecto;
    private  Pane testingG;

    private static final int HEIGHT =800;
    private static final int WIDTH = 1300;

    GameViewManager(){
        arquitecto = new HuzzleMaker();

        borderPane = new BorderPane();
        borderPane.setStyle("-fx-background-color: #FFFFFF;");

        gamePane = new GridPane();
        gamePane.setGridLinesVisible(true);
        gamePane.setPadding(new Insets(10,10,10,10));
        gamePane.setHgap(5);
        gamePane.setVgap(5);

        testingG = new AnchorPane();

        headerPane = new AnchorPane();
        panelBar = new HBox();
        counter = new HuzzleLabel("TIEMPO", 18);
        panelBar.setLayoutX(50);
        panelBar.setSpacing(600);
        panelBar.getChildren().add(counter);

        headerPane.getChildren().add(panelBar);

        HBox puzzleArea = new HBox();
        puzzleArea.setSpacing(50);
        puzzleArea.getChildren().add(gamePane);
        puzzleArea.getChildren().add(testingG);

        borderPane.setTop(headerPane);
        borderPane.setCenter(puzzleArea);


        gameScene = new Scene(borderPane, WIDTH, HEIGHT);
        gameStage = new Stage();
        gameStage.setScene(gameScene);
    }

    void createNewGame(HERO theOneAndOnly, Stage menuStage){
        this.menuStage = menuStage;
        this.menuStage.hide();
        this.gameStage.show();
        this.chosenHero = theOneAndOnly;
        this.puzzleSize = 3;

        info = new HuzzleLabel(chosenHero.heroName,20);
        Image heroImg = new Image(chosenHero.heroUrl,40,40,true,true);
        info.setGraphic(new ImageView(heroImg));
        panelBar.getChildren().add(info);
        gameOn = true;
        initReloj();


        ImageView solution = new ImageView(pepe);
        solution.setLayoutY(10);
        testingG.getChildren().add(solution);
        puzzleBoard = arquitecto.hacemeCuadritos(pepe, puzzleSize);
        //shuffle();

        int row = 0;
        int col = 0;
        for (int i = 0; i < puzzleBoard.size() ; i++){

            HuzzlePuzzlePiece piece = puzzleBoard.get(i);

            if(piece.getImageView() == null){
                this.blankCol = col;
                this.blankRow = row;
                continue;
            }

            setPuzzlePiecePosition(piece,col, row);
            piece.setSolvedPos(col,row);



            gamePane.getChildren().add(piece.getImageView());


            piece.getImageView().setOnMouseClicked(event -> {
                int distanceX = Math.abs(piece.getCol() - blankCol);
                int distanceY = Math.abs(piece.getRow() - blankRow);


                if ((distanceX == 0 && distanceY == 1) || (distanceX == 1 && distanceY == 0)){
                    int[] tmp = {piece.getCol(),piece.getRow()};
                    setPuzzlePiecePosition(piece,blankCol,blankRow);
                    blankRow = tmp[1];
                    blankCol = tmp[0];

                    if(userWon()){
                        gameOn = false;
                        gameOverDialog();

                    }

                }
            });
            if ((i + 1) % puzzleSize == 0){
                col++;
                row = 0;
            }else {
                row++;
            }
        }

    }

    private void setPuzzlePiecePosition(HuzzlePuzzlePiece pieza,int col, int row){
        GridPane.setConstraints(pieza.getImageView(),col,row);
        pieza.setPos(col,row);
    }

    private void shuffle(){
        Collections.shuffle(puzzleBoard);
        System.out.println("Shuffled Puzzle");
    }

    private boolean userWon(){
        boolean good = true;
         for (int i = 0; i<(puzzleSize*puzzleSize)-1; i++) {
            if(!(puzzleBoard.get(i).getRow() == puzzleBoard.get(i).getSolvedRow()) || !(puzzleBoard.get(i).getCol() == puzzleBoard.get(i).getSolvedCol())){
                good = false;
            }
         }
        return good;
    }

    void initReloj() {

        Task stopWatch = new Task() {
            @Override
            protected Integer call() throws Exception {
                seconds = 0;
                minutes = 0;
                while (gameOn) {
                    if (seconds == 60){
                        minutes++;
                        seconds =0;
                    }
                    updateMessage("Time Elapsed " + minutes + ":" +seconds);
                    seconds++;
                    Thread.sleep(1000);
                }
                return seconds;
            }
        };

        counter.textProperty().bind(stopWatch.messageProperty());

        Thread thread = new Thread(stopWatch);
        thread.setDaemon(true);
        thread.start();

    }

    void  gameOverDialog(){
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Cool project right?");
        alert.setHeaderText("Congrats! You can solve a puzzle ;)");
        alert.setContentText("What now?");

        ButtonType goBack = new ButtonType("Main Menu");
        ButtonType getDaHelOut = new ButtonType("Exit");

        alert.getButtonTypes().setAll(goBack, getDaHelOut);

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == goBack) {
            gameStage.close();
            menuStage.show();
        } else if (result.get() == getDaHelOut) {
            gameStage.close();
        } else {
            gameStage.close();
        }
    }

}
