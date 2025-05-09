package com.br;

import javafx.animation.FadeTransition;
import javafx.animation.ScaleTransition;
import javafx.animation.TranslateTransition;
import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class TelaLogin extends Application {

    @Override
    public void start(Stage stage) {
        // Layout
        GridPane gridPane = new GridPane();
        gridPane.setMinSize(600, 300);
        gridPane.setPadding(new Insets(20));
        gridPane.setVgap(15);
        gridPane.setHgap(10);
        gridPane.setAlignment(Pos.CENTER);

        Scene scene = new Scene(gridPane);
        scene.getStylesheets().add(getClass().getResource("/com/Style/LoginGeral.css").toExternalForm());

        // Mensagem de erro
        Text mensagemERRO = new Text();
        mensagemERRO.setFill(Color.RED);
        gridPane.add(mensagemERRO, 0, 3, 2, 1);

        // Campos
        TextField inputEmail = new TextField();
        PasswordField inputSenha = new PasswordField();

        gridPane.add(new Text("Email"), 0, 0);
        gridPane.add(inputEmail, 1, 0);
        gridPane.add(new Text("Senha"), 0, 1);
        gridPane.add(inputSenha, 1, 1);

        // Botões
        Button buttonEnviar = new Button("ENVIAR");
        Button buttonLimpar = new Button("APAGAR");
        gridPane.add(buttonEnviar, 0, 2);
        gridPane.add(buttonLimpar, 1, 2);
        

        // Ação ENVIAR
        buttonEnviar.setOnAction(event -> {
            mensagemERRO.setText("");

            if (inputEmail.getText().isEmpty() || inputSenha.getText().isEmpty()) {
                mensagemERRO.setText("Preencha todos os campos!");
                TranslateTransition shake = new TranslateTransition(Duration.millis(70), mensagemERRO);
                shake.setFromX(0);
                shake.setByX(10);
                shake.setCycleCount(4);
                shake.setAutoReverse(true);
                shake.play();
                return;
            }

            try (Connection conexao = SQLServerConnection.conectar();
                 PreparedStatement pstmt = conexao.prepareStatement(
                         "SELECT ID_Cargo FROM login_sistema WHERE Email = ? AND Senha = ?")) {

                pstmt.setString(1, inputEmail.getText());
                pstmt.setString(2, inputSenha.getText());

                ResultSet rs = pstmt.executeQuery();
                if (rs.next()) {
                    int ID_Cargo = rs.getInt("ID_Cargo");

                    switch (ID_Cargo) {
                        case 1 -> System.out.println("Administrador logado");
                        case 2 -> System.out.println("Moderador logado");
                        case 3 -> System.out.println("Funcionário logado");
                        default -> mensagemERRO.setText("Cargo inválido.");
                    }
                } else {
                    mensagemERRO.setText("Usuário ou senha incorretos.");
                }

            } catch (Exception e) {
                e.printStackTrace();
                mensagemERRO.setText("Erro ao conectar ao banco de dados.");
            }
        });

        // Ação APAGAR
        buttonLimpar.setOnAction(event -> {
            FadeTransition fadeEmail = new FadeTransition(Duration.millis(200), inputEmail);
            fadeEmail.setFromValue(1.0);
            fadeEmail.setToValue(0.3);
            fadeEmail.setOnFinished(e -> {
                inputEmail.clear();
                FadeTransition fadeIn = new FadeTransition(Duration.millis(150), inputEmail);
                fadeIn.setToValue(1.0);
                fadeIn.play();
            });

            FadeTransition fadeSenha = new FadeTransition(Duration.millis(200), inputSenha);
            fadeSenha.setFromValue(1.0);
            fadeSenha.setToValue(0.3);
            fadeSenha.setOnFinished(e -> {
                inputSenha.clear();
                FadeTransition fadeIn = new FadeTransition(Duration.millis(150), inputSenha);
                fadeIn.setToValue(1.0);
                fadeIn.play();
            });

            fadeEmail.play();
            fadeSenha.play();
            mensagemERRO.setText("");
            inputEmail.requestFocus();
        });

        // Efeitos ao pressionar os botões
        buttonEnviar.setOnMousePressed(e -> {
            ScaleTransition scaleDown = new ScaleTransition(Duration.millis(100), buttonEnviar);
            scaleDown.setToX(0.9);
            scaleDown.setToY(0.9);
            scaleDown.play();
        });
        buttonEnviar.setOnMouseReleased(e -> {
            ScaleTransition scaleUp = new ScaleTransition(Duration.millis(150), buttonEnviar);
            scaleUp.setToX(1);
            scaleUp.setToY(1);
            scaleUp.play();
        });

        buttonLimpar.setOnMousePressed(e -> {
            ScaleTransition scaleDown = new ScaleTransition(Duration.millis(100), buttonLimpar);
            scaleDown.setToX(0.9);
            scaleDown.setToY(0.9);
            scaleDown.play();
        });
        buttonLimpar.setOnMouseReleased(e -> {
            ScaleTransition scaleUp = new ScaleTransition(Duration.millis(150), buttonLimpar);
            scaleUp.setToX(1);
            scaleUp.setToY(1);
            scaleUp.play();
        });

        // Desabilita botão limpar se campos estiverem vazios
        ChangeListener<String> camposVaziosListener = (obs, oldVal, newVal) -> {
            boolean vazio = inputEmail.getText().isEmpty() && inputSenha.getText().isEmpty();
            buttonLimpar.setDisable(vazio);
        };
        inputEmail.textProperty().addListener(camposVaziosListener);
        inputSenha.textProperty().addListener(camposVaziosListener);

        // Finaliza a cena
        stage.setScene(scene);
        stage.setTitle("Tela de Login");
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

    public class SQLServerConnection {

    public static Connection conectar() throws SQLException, ClassNotFoundException {
        String url = "jdbc:mysql://localhost:3306/SunPDV";
        String user = "seunome";    
        String pass = "suasenha";   

        Class.forName("com.mysql.cj.jdbc.Driver");
        return DriverManager.getConnection(url, user, pass);
    }

    public static void main(String[] args) {
        try (Connection conexao = conectar()) {
            System.out.println("Conexão bem-sucedida!");
        } catch (Exception e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }
}

}
