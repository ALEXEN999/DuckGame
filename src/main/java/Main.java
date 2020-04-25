import control.MainWindow;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Locale;
import java.util.ResourceBundle;

public class Main extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) {
        FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("fxml/mainWindow.fxml"));
        loader.setResources(ResourceBundle.getBundle("bundles.mybundle",new Locale("ca")));
        Parent root = null;
        try {
            root = loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Scene sc = new Scene(root);

        MainWindow window = loader.getController();
        window.setScene(sc);

        stage.setScene(sc);
        stage.setTitle("Rebota");
        stage.show();

    }
}
