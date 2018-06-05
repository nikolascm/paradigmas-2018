import java.awt.Paint;
import java.util.ArrayList;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.control.TitledPane;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import static javafx.scene.paint.Color.BLACK;
import static javafx.scene.paint.Color.BLUE;
import static javafx.scene.paint.Color.GRAY;
import static javafx.scene.paint.Color.GREEN;
import static javafx.scene.paint.Color.RED;
import static javafx.scene.paint.Color.YELLOW;
import javafx.scene.shape.Circle;

public class Propriedades {
    
    Button ok = new Button("OK");
    HBox hbox_cor = new HBox();
    Slider slider = new Slider();
    GridPane grid = new GridPane();    
    TextField nome = new TextField();
    ArrayList <Paint> paint = new ArrayList();
    TitledPane gridTitlePane = new TitledPane();
    
    Propriedades(Vertice v) {      
        grid();
        slider();
        hbox_cor.setSpacing(10);
        hbox_cor.getChildren().add(new Circle(10,RED));
        hbox_cor.getChildren().add(new Circle(10,GREEN));
        hbox_cor.getChildren().add(new Circle(10,BLUE));
        hbox_cor.getChildren().add(new Circle(10,YELLOW));
        hbox_cor.getChildren().add(new Circle(10,GRAY));
        
        grid.add(new Label("Nome: "), 0, 0);
        gridTitlePane.setText("Vértices");
    }
    
    Propriedades(Aresta a) {
        grid();
        slider();
        hbox_cor.setSpacing(10);
        hbox_cor.getChildren().add(new Circle(10,GRAY));
        hbox_cor.getChildren().add(new Circle(10,BLACK));

        grid.add(new Label("Peso: "), 0, 0);
        gridTitlePane.setText("Arestas");
    }
    
    public void grid() {
        grid.setVgap(20);
        grid.add(new Label("Cor: "), 0, 1);
        grid.add(new Label("Transp.: "), 0, 2);
        grid.setPadding(new Insets(5, 5, 5, 5));
        
        grid.add(nome, 1, 0);
        grid.add(ok, 2, 0);
        grid.add(hbox_cor, 1, 1);
        grid.add(slider, 1, 2);
        gridTitlePane.setContent(grid);
    }
    
    public void slider() {
        slider.setMin(0);
        slider.setMax(100);
        slider.setValue(0);
        slider.setShowTickLabels(true);
        slider.setShowTickMarks(true);
        slider.setMajorTickUnit(50);
        slider.setMinorTickCount(5);
        slider.setBlockIncrement(10);
    }   
    
    public void setValue(Vertice v) {
        nome.setText(v.nome);
        ok.setOnAction((ActionEvent event) -> {
            v.nome = nome.getText();
        });
    }
    
    public void setValue(Aresta a) {
        nome.setText(a.peso.toString());
        ok.setOnAction((ActionEvent event) -> {
            a.peso = Integer.parseInt(nome.getText());
        });
    }
    
    public void setColor(Vertice v) {
        for(int i=0;i<5;i++) {
            Circle c1 = (Circle) hbox_cor.getChildren().get(i);
            c1.setOnMousePressed((MouseEvent e) -> {
                v.circle.setFill(c1.getFill());
            });                
        }
    }
    
    public void setColor(Aresta a) {
        for(int i=0;i<2;i++) {
            Circle c1 = (Circle) hbox_cor.getChildren().get(i);
            c1.setOnMousePressed((MouseEvent e) -> {
                a.line.setStroke(c1.getFill());
            });                
        }
    }
    
    public void setDropShadow(Vertice v) {
        slider.valueProperty().addListener((ObservableValue<? extends Number> ov, Number old_val, Number new_val) -> {
            v.dropShadow.setSpread(new_val.doubleValue()/100);
        });
    }
    
    public void setDropShadow(Aresta a) {
        slider.valueProperty().addListener((ObservableValue<? extends Number> ov, Number old_val, Number new_val) -> {
            a.dropShadow.setSpread(new_val.doubleValue()/100);
        });
    }
}
