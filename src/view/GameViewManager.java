package view;

import javafx.concurrent.Task;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextInputDialog;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import model.*;


import java.io.*;
import java.net.MalformedURLException;
import java.util.*;

class GameViewManager {

    private BorderPane borderPane;
    private Pane headerPane;
    private Pane footerPane;
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
    private Stack<Integer[]> coordinates;
    private boolean gameOn;
    private int seconds;
    private int minutes;

    private String pepeUrl;
    private Image pepe;
    private List<HuzzlePuzzlePiece> puzzleBoard;


    private HuzzleMaker arquitecto;
    private  Pane solutionArea;

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

        solutionArea = new AnchorPane();

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
        puzzleArea.getChildren().add(solutionArea);

        footerPane = new AnchorPane();
        HBox bottomArea = new HBox();
        HuzzleButton goBack = new HuzzleButton("Go Back");
        goBack.setOnAction(event -> {
            gameStage.close();
            menuStage.show();
        });
//        HuzzleButton reShuffle = new HuzzleButton("Shuffle Again!");
//        reShuffle.setOnAction(event -> {
//            shuffle();
//        });
        bottomArea.getChildren().addAll(goBack);
        bottomArea.setLayoutX(200);
        bottomArea.setSpacing(200);

        borderPane.setTop(headerPane);
        borderPane.setCenter(puzzleArea);
        borderPane.setBottom(bottomArea);


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

        //TOP PANEL SET UP
        info = new HuzzleLabel(chosenHero.heroName,20);
        Image heroImg = new Image(chosenHero.heroUrl,40,40,true,true);
        info.setGraphic(new ImageView(heroImg));
        panelBar.getChildren().add(info);
        gameOn = true;
        showImageChooser();
        if(pepeUrl != null) {
            pepe = new Image(pepeUrl, 600, 600, false, true); //TODO replace empty string with filechooser method.
        }else{
            gameStage.close();
            menuStage.show();
            return;
        }
        initReloj();

        //PUT SOLUTION UP IN THE PANE
        ImageView solution = new ImageView(pepe);
        solution.setLayoutY(10);
        solutionArea.getChildren().add(solution);

        //CROP IMAGE INTO PIECES, CREATE A LIST OF PUZZLE PIECE OBJECTS AND SHUFFLE THEIR POSITION
        puzzleBoard = arquitecto.hacemeCuadritos(pepe, puzzleSize);

        //FILL THE GRIDPANE WITH THE PIECES
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

            //ADD EVENT LISTENER TO EACH PUZZLE PIECE
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
                        writeRecord();
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

        shuffle();

    }

    private void setPuzzlePiecePosition(HuzzlePuzzlePiece pieza,int col, int row){
        GridPane.setConstraints(pieza.getImageView(),col,row);
        pieza.setPos(col,row);
    }

    private void shuffle(){
        //CREATE A LIST OF POSSIBLE PAIRS;
        coordinates = new Stack<>();
        for (int i = 0; i < puzzleSize;i++){
            for (int j = 0; j < puzzleSize;j++){
                if (i == puzzleSize-1 && j == puzzleSize-1){
                    break;
                }
                Integer[] pair = {i,j};
                coordinates.add(pair);
            }
        }

        //
        int cnt = 0;
        for (int i = 0; i < puzzleBoard.size()-1;i++) {
            if (cnt % puzzleSize == 0){cnt = 0;}
            if (cnt == puzzleSize-1 && i > (puzzleSize/100*80) ){cnt = 0;}

            Collections.shuffle(coordinates);

            int thiscol = coordinates.peek()[0];
            int thisrow = coordinates.pop()[1];
            setPuzzlePiecePosition(puzzleBoard.get(i),thiscol,thisrow);
            System.out.println("COL " + thiscol + " x ROW " + thisrow);
            cnt++;
        }
        System.out.println("Shuffled Puzzle");
        System.out.println("BLANK PIECE ORIGINAL POSITION -> " + puzzleBoard.get(0).getImageView() + " x " + puzzleBoard.get(0).getSolvedCol() );
        System.out.println("BLANK PIECE CURRENT POSITION -> " + puzzleBoard.get(0).getRow() + " x " + puzzleBoard.get(0).getCol() );
        System.out.println(blankRow + " x " + blankCol);

//        for (int i: rows
//        ) {
//            System.out.println(i);
//        }
//        System.out.println("==================");
//        for (int i: cols
//        ) {
//            System.out.println(i);
//        }
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
        alert.setHeaderText("What do you want to do now?");
        alert.setContentText("Please choose an option");

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

    String playerName(){
        TextInputDialog dialog = new TextInputDialog("walter");
        dialog.setTitle("Congratulations!");
        dialog.setHeaderText("Let's save your record");
        dialog.setContentText("Please enter your name:");
        String tmp;
        Optional<String> result = dialog.showAndWait();
        if (result.isPresent()){
            return result.get();
        }else{
            return "DefaultPlayer";
        }
    }

    void writeRecord(){
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter("records.txt",true));
            writer.write(seconds + "," + minutes + "," + chosenHero.heroUrl + "," + playerName()+"\n");
            writer.close();
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    private void showImageChooser(){
        FileChooser imageChooser = new FileChooser();
        imageChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Supported images", "*.bmp","*.gif","*.jpeg","*.png","*.jpg"));
        File imgFile = imageChooser.showOpenDialog(gameStage);
        try {
            String absPath = imgFile.getAbsolutePath();
            pepeUrl = new File(absPath).toURI().toURL().toString();
            } catch (Exception e) {}

    }

}
