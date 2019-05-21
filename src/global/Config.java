package global;

import javafx.scene.text.Font;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class Config {
    public final String FONTPATH = "src/model/resources/Font/UbuntuMono-Regular.ttf";

    public Font FONT(){
        Font font = Font.font("monospace",23);
        try {
            font = Font.loadFont(new FileInputStream(FONTPATH),23);
        }catch (FileNotFoundException e){
            System.out.println(e);
        }finally {
            return font;
        }
    }
}
