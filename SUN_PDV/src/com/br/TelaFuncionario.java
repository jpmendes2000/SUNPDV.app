/* package com.br;

import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.application.Application;
import javafx.geometry.Pos;

public class TelaFuncionario extends Application {
    public Usuario usuario;  

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    @Override 
    public void start(Stage stage) {
        ControleLogin controle = new ControleLogin(); 
       
        VBox layout = new VBox(20);
        layout.setAlignment(Pos.CENTER);
        layout.getStyleClass().add("grid-pane");

        Text bemVindo = new Text("Bem-vindo " + usuario.getNome());
        Text cargo = new Text("Seu cargo: " + usuario.getCargo());

        layout.getChildren().addAll(bemVindo, cargo);

        Scene scene = new Scene(layout);
        scene.getStylesheets().add(getClass().getResource("/com/Style/LoginGeral.css").toExternalForm());

        stage.setScene(scene);
        stage.setTitle("Workspace - " + usuario.getNome() + " - " + usuario.getCargo());    
        stage.setFullScreen(true);
        stage.setMaximized(true);
        stage.setResizable(false);
        stage.setFullScreenExitHint("");
        stage.show();
    }
} */