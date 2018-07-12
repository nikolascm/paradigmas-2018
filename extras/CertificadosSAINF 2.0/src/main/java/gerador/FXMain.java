
package gerador;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

/**
 *
 * @author nikolas
 */
public class FXMain extends Application {
    
    Image image;
    ImageView page = new ImageView();
    TextArea textArea = new TextArea();
    Certificados certificados = new Certificados();
    
    @Override
    public void start(Stage primaryStage) {
     
        image = new Image("file:example.jpg");
        
        ArrayList <Button> botoes = iniciaBotoes();
        botoes.get(0).setOnAction(new abrirButton());
        botoes.get(1).setOnAction(new importarButton());
        botoes.get(2).setOnAction(new salvarButton());
        
        Label titulo = new Label("Gerador de Certificados");
        titulo.setFont(Font.font("Arial", FontWeight.BOLD, 25));
        titulo.setAlignment(Pos.CENTER);
        
        textArea.setMaxSize(300, 500);
        
        page.setImage(image);
        page.setSmooth(true);
        page.setFitWidth(300);
        page.setPreserveRatio(true);
        page.setOnMouseClicked(new imageHandler());
        
        HBox center = new HBox();
        center.setSpacing(30);
        center.setAlignment(Pos.CENTER);
        center.getChildren().addAll(textArea,page);
        
        HBox exibirBotoes = new HBox();
        exibirBotoes.setSpacing(15);
        exibirBotoes.setAlignment(Pos.CENTER);
        exibirBotoes.setPadding(new Insets(20, 20, 20, 20));
        
        exibirBotoes.getChildren().addAll(
            botoes.get(0),
            botoes.get(1),
            botoes.get(2)
        );
        
        VBox all = new VBox();
        all.setSpacing(40);
        all.setAlignment(Pos.CENTER);
        all.getChildren().addAll(titulo,center,exibirBotoes);
        
        Scene scene = new Scene(new Group());
        primaryStage.setTitle("Gerador de Certificados");
        primaryStage.setScene(new Scene(all,800,500));
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

    private ArrayList<Button> iniciaBotoes() {

        ArrayList <Button> botoes = new ArrayList<>();
        botoes.add(new Button("Abrir arquivo modelo"));
        botoes.add(new Button("Importar lista de nomes"));
        botoes.add(new Button("Salvar certificados"));
        
        return botoes;
    }

    private class abrirButton implements EventHandler<ActionEvent> {
        @Override
        public void handle(ActionEvent event) {
            FileChooser fileChooser = new FileChooser();
            configureFileChooser(fileChooser,"PDF Document","*.pdf");
            try {
                validarArquivo(fileChooser.showOpenDialog(new Stage()));
            } catch (IOException ex) {
                tratamentoExcecao();
            }
        }

        private void validarArquivo(File f) throws IOException{
            if(f.canRead()) {
                certificados.setPathOrigem(f);
                configuraImagem(f);
            }
        }
    }
    
    private class salvarButton implements EventHandler<ActionEvent> {
        @Override
        public void handle(ActionEvent event) {
            
            FileChooser fileChooser = new FileChooser();
            configureFileChooser(fileChooser,"PDF Document","*.pdf");
            
            File f = fileChooser.showSaveDialog(new Stage());
            certificados.setPathDestino(f);
            
            if(certificados.LISTA != null) {
                try {
                    certificados.criaCopias();
                } catch (Exception ex) {
                    tratamentoExcecao();
                }
            } else {
            
                try {
                    certificados.getTextArea(textArea);
                } catch (Exception ex) {
                    tratamentoExcecao();
                }
            }
            try {
                configuraImagem(f);
            } catch (IOException ex) {
                tratamentoExcecao();
            }
        }
    }
    
    private class importarButton implements EventHandler<ActionEvent> {
        @Override
        public void handle(ActionEvent event) {
            
            FileChooser fileChooser = new FileChooser();
            configureFileChooser(fileChooser,"TXT Document","*.txt");
            validarArquivo(fileChooser.showOpenDialog(new Stage()));
        }
        
        private void validarArquivo(File f) {
            if(f.canRead()) {
                certificados.setPathLista(f);
            }
        }
    }
    
    private class imageHandler implements EventHandler<MouseEvent> {

        @Override
        public void handle(MouseEvent event) {
            ImageView page = new ImageView();
            
            page.setImage(image);
            page.setPreserveRatio(true);
            page.setSmooth(true);
            
            Group group = new Group();
            group.getChildren().addAll(page);
            
            double x = image.getWidth();
            double y = image.getHeight();

            Stage stage = new Stage();
            stage.setTitle("Visualizador de Imagem");
            stage.setScene(new Scene(group,x,y));
            stage.show();
        }
    }
    
    public void tratamentoExcecao() {
        Alert alerta = new Alert(Alert.AlertType.INFORMATION);
        alerta.setHeaderText("Erro ao efetuar leitura de arquivo!");
        alerta.showAndWait();
    }
    
    public void configureFileChooser(final FileChooser fileChooser, String typeOfDocument, String extension){                           
        fileChooser.setInitialDirectory(
            new File(System.getProperty("user.home"))
        );
        fileChooser.getExtensionFilters().addAll(
            new FileChooser.ExtensionFilter(typeOfDocument, extension)
        );
    }
    
    public void configuraImagem(File f) throws IOException {
        CustomPageDrawer customPage = new CustomPageDrawer(f);
        image = new Image("file:page.png");
        page.setImage(image);
    }
}
