import javafx.scene.shape.Line;
import javafx.scene.shape.StrokeLineCap;

public class Aresta {
    
    public Line line;
    public Vertice inicio;
    public Vertice fim;
    
    public Aresta(Line l) {
        line = new Line();
        line.setStartX(l.getStartX());
        line.setStartY(l.getStartY());
        line.setEndX(l.getEndX());
        line.setEndY(l.getEndY());
        
        // Style
        line.setStrokeWidth(1);
        line.setStrokeLineCap(StrokeLineCap.ROUND);
    }
}