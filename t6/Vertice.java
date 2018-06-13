import java.util.LinkedList;
import javafx.scene.shape.Circle;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
       
public class Vertice {
    public Circle circle;
    LinkedList <Aresta> adjacencias;
    
    Vertice() {}
    
    public Vertice(double x, double y){
        circle = new Circle();
        circle.setCenterX(x);
        circle.setCenterY(y);
        circle.setRadius(15);
        circle.setFill(Paint.valueOf("#008B8B"));
        this.adjacencias = new LinkedList();
    
        // Style
        this.circle.setStrokeWidth(1);
        this.circle.setStroke(Color.BLACK);
    }
    
    public void verificaAresta() {
        for (Aresta a: this.adjacencias) {
            if (a.fim == this) {
                a.line.setEndX(this.circle.getCenterX());
                a.line.setEndY(this.circle.getCenterY());

            }
            else if (a.inicio == this) {
                a.line.setStartX(this.circle.getCenterX());
                a.line.setStartY(this.circle.getCenterY());
            }
        }
    }
    
}
