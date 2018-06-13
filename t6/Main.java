import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ToolBar;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.stage.Stage;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;

public class Main extends Application {
 
    public Circle c;
    public Line line;
    public Grafo grafo;
    Pane central = new Pane();
    
    public static void main(String[] args) {
        launch(args);
    }
 
    @Override
    public void start(Stage stage) {
        
        grafo = new Grafo();
        Button check = new Button("Verificar");
        ToolBar toolBar = new ToolBar();
        toolBar.getItems().add(check);
        
        Label label1 = new Label("Sobrepostas: "+grafo.sobrepostas);
        Label label2 = new Label("Vértices: "+grafo.vertices.size());
        Label label3 = new Label("Arestas: "+grafo.arestas.size());

        HBox status = new HBox();
        status.setSpacing(20);
        status.setPadding(new Insets(10,10,10,10));
        status.getChildren().addAll(label1,label2,label3);
        
        for(Vertice v : grafo.vertices) {
            v.circle.setOnMousePressed((MouseEvent e) -> {
                grafo.movendo = true;
            });
            
            v.circle.setOnMouseDragged((MouseEvent e) -> {
                if(grafo.movendo) {
                    v.circle.setCenterX(e.getX());
                    v.circle.setCenterY(e.getY());
                    v.verificaAresta();
                    grafo.ArestasSobrepostas();
                }
                grafo.atualizaDados(label1, label2, label3);
            });
            
            v.circle.setOnMouseReleased((MouseEvent e) -> {
                if(grafo.sobrepostas==0) {
                    Alert alerta = new Alert(Alert.AlertType.INFORMATION);
                    alerta.setTitle("Aviso");
                    alerta.setHeaderText("Você passou de fase!");
                    alerta.showAndWait();
                    
                    // Reseta
                    central.getChildren().clear();
                    grafo.limpa();
                    grafo.inicia();
                    this.atualizaTela();
                    grafo.atualizaDados(label1, label2, label3);
                }
                grafo.movendo = false;
            });
        }
        
        check.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if(grafo.sobrepostas>0) {
                    Alert alerta = new Alert(Alert.AlertType.INFORMATION);
                    alerta.setTitle("Aviso");
                    alerta.setHeaderText("Existem arestas sobrepostas!");
                    alerta.showAndWait();
                }
            }
        });
        
        atualizaTela();
        
        BorderPane border = new BorderPane();
        border.setCenter(central);
        border.setBottom(status);
        border.setTop(toolBar);
        
        StackPane root = new StackPane();        
        root.getChildren().addAll(border);

        Scene scene = new Scene(root, 900, 600);
        stage.setTitle("My Planarity");
        stage.setScene(scene);
        stage.show();
    }
    
    public void atualizaTela() {
        
        for(Aresta a: grafo.arestas)
        central.getChildren().addAll(a.line);
        
        for(Vertice v: grafo.vertices)
        central.getChildren().addAll(v.circle);
    }
}
