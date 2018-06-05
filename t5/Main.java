import java.util.ArrayList;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Accordion;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToolBar;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.stage.Stage;

public class Main extends Application {
 
    public Circle c;
    public Line line;
    public Grafo grafo;
    private double varX, varY;
    Pane central = new Pane();
    Accordion accordion = new Accordion ();           
    Propriedades grid_vertice = new Propriedades(new Vertice());
    Propriedades grid_aresta = new Propriedades(new Aresta());
    
    public static void main(String[] args) {
        launch(args);
    }
 
    @Override
    public void start(Stage stage) {
        
        ArrayList <Button> botoes = new ArrayList<Button>();
        botoes.add(new Button("Novo", new ImageView("new.png")));
        botoes.add(new Button("Salvar", new ImageView("save.png")));
        botoes.add(new Button("Sair", new ImageView("close.png")));
        botoes.add(new Button("Dados", new ImageView("about.png")));
        
        ToolBar toolBar = new ToolBar();
        for(Button b:botoes)
        toolBar.getItems().add(b);
        
        ToggleButton dados = new ToggleButton("Selecionar");
        ToggleButton toggleButtonAresta = new ToggleButton("Aresta");
        ToggleButton toggleButtonVertice = new ToggleButton("Vértice");
       
 
        dados.setGraphic(new ImageView("cursor.png"));
        toggleButtonAresta.setGraphic(new ImageView("aresta.png"));
        toggleButtonVertice.setGraphic(new ImageView("vertice.png"));

        toolBar.getItems().add(toggleButtonVertice);
        toolBar.getItems().add(toggleButtonAresta);
        toolBar.getItems().add(dados);

        // Criar grafo
        botoes.get(0).setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                grafo = new Grafo();
                System.out.println("Grafo criado!");
            }
        });
        
        // Salvar grafo
        botoes.get(1).setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if(grafo!=null) {
                    grafo.criarSVG();
                    grafo.grafoSalvo=true;
                } else {
                    Alert alerta = new Alert(Alert.AlertType.INFORMATION);
                    alerta.setTitle("Aviso");
                    alerta.setHeaderText("O grafo está vazio!");
                    alerta.showAndWait();
                }
            }            
        });
        
        // Sair do programa
        botoes.get(2).setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if(grafo!=null && !grafo.grafoSalvo()) {
                    Alert alerta = new Alert(Alert.AlertType.INFORMATION);
                    alerta.setTitle("Aviso");
                    alerta.setHeaderText("Você possui um grafo não salvo!");
                    alerta.setContentText("Salve o grafo primeiro");
                    alerta.showAndWait();
                } else {
                    stage.close();
                }
            }            
        });
        
        // Mostrar informações sobre o grafo
        botoes.get(3).setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if(grafo!=null) {
                    Alert info = new Alert(Alert.AlertType.INFORMATION);
                    info.setTitle("Informações");
                    info.setHeaderText("Número de Vértices: "+grafo.vertices.size()+
                    "\n"+"Número de Arestas: "+grafo.arestas.size()+"\n"+
                    "Arestas sobrepostas: "+grafo.ArestasSobrepostas);
                    info.showAndWait();
                }
            }            
        });
        
        // Desenhar vértices
        toggleButtonVertice.setOnAction((ActionEvent event) -> {
            toggleButtonVertice.setSelected(true);
            toggleButtonAresta.setSelected(false);
            dados.setSelected(false);
            central.setOnMousePressed((MouseEvent e) -> {
                varX = e.getX();
                varY = e.getY();
                Vertice v = new Vertice(varX, varY);
                if(grafo.insereVertice(v))
                    central.getChildren().add(v.circle);
            });
        });

        // Desenhar arestas
        toggleButtonAresta.setOnAction((ActionEvent event) -> {
            toggleButtonAresta.setSelected(true);
            toggleButtonVertice.setSelected(false);
            dados.setSelected(false);
            central.setOnMousePressed((MouseEvent e) -> {
                line = new Line();
                line.setStartX(e.getX());
                line.setStartY(e.getY());
            });
            
            central.setOnMouseDragged((MouseEvent e) -> {
                line.setEndX(e.getX());
                line.setEndY(e.getY());
            });
            
            central.setOnMouseReleased((MouseEvent e) -> {
                Aresta a = new Aresta(line);
                if(grafo.insereAresta(a))
                    central.getChildren().add(a.line);
            });
        });
        
        // Dados sobre vértices e arestas
        accordion.getPanes().add(grid_vertice.gridTitlePane);
        accordion.getPanes().add(grid_aresta.gridTitlePane);
        accordion.setMaxHeight(200);
        
        // Mostrar e modificar informações de vértices e arestas
        dados.setOnAction((ActionEvent event) -> {
          toggleButtonVertice.setSelected(false);
          toggleButtonAresta.setSelected(false);
          dados.setSelected(true);
          // Se for selecionado vértice
          for(Vertice v : grafo.vertices) {
            v.circle.setOnMousePressed((MouseEvent m) -> {
              grid_vertice.setValue(v);
              grid_vertice.setColor(v);
              grid_vertice.setDropShadow(v);
              accordion.setExpandedPane(grid_vertice.gridTitlePane);
            });
          }
          // Se for selecionada aresta
          for(Aresta a : grafo.arestas) {
            a.line.setOnMousePressed((MouseEvent n) -> {
              grid_aresta.setValue(a);
              grid_aresta.setColor(a);
              grid_aresta.setDropShadow(a);
              accordion.setExpandedPane(grid_aresta.gridTitlePane);
            });
          }
        });
        
        BorderPane border = new BorderPane();
        border.setCenter(central);
        border.setTop(toolBar);
        border.setLeft(accordion);
        
        StackPane root = new StackPane();        
        root.getChildren().addAll(border);

        Scene scene = new Scene(root, 900, 600);
        stage.setTitle("Editor de Grafos");
        stage.setScene(scene);
        stage.show();
    }    
}
