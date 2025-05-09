import javafx.application.Application;
import javafx.stage.Stage;
import com.br.TelaLogin;

public class App extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        TelaLogin telaLogin = new TelaLogin();
        telaLogin.start(primaryStage); 
    }

    public static void main(String[] args) {
        launch(args);
    }
}