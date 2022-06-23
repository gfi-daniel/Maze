package de.die_gfi.maze;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class App extends Application
{
    public static void main(String[] args)
    {
        launch();
    }

    @Override
    public void start(Stage stage) throws Exception
    {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("configuration.fxml"));
        Scene scene = new Scene(fxmlLoader.load());

        stage.setTitle( "Labyrinth Generator" );
        stage.setScene(scene);
        stage.show();



//        Label l = new Label("something");
//        VBox vbox = new VBox(l);
//        Scene scene = new Scene(vbox);
//        primaryStage.setScene(scene);
//
//        primaryStage.show();
    }
}
