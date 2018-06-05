import java.util.LinkedList;
import javafx.scene.effect.DropShadow;
import javafx.scene.shape.Circle;
import javafx.scene.paint.Color;
       
public class Vertice {
    public String cor;
    public String nome;
    public Circle circle;
    public DropShadow dropShadow;
    LinkedList <Aresta> adjacencias;
    
    Vertice() {}
    
    public Vertice(double x, double y){
        nome = "";
        circle = new Circle();
        circle.setCenterX(x);
        circle.setCenterY(y);
        circle.setRadius(20);
        circle.setFill(Color.RED);
        cor = circle.getFill().toString();
        this.adjacencias = new LinkedList();
    
        // Style
        dropShadow = new DropShadow();
        dropShadow.setOffsetX(0.0);
        dropShadow.setOffsetY(0.0);
        this.circle.setEffect(dropShadow);
    }
    
    
}
