import javafx.scene.effect.DropShadow;
import javafx.scene.shape.Line;
import javafx.scene.shape.StrokeLineCap;

public class Aresta {
    public Line line;
    public Integer peso;
    public DropShadow dropShadow;
    
    Aresta() {}
    
    public Aresta(Line l) {
        this.peso = 0;
        line = new Line();
        line.setStartX(l.getStartX());
        line.setStartY(l.getStartY());
        line.setEndX(l.getEndX());
        line.setEndY(l.getEndY());
        
        // Style
        dropShadow = new DropShadow();
        dropShadow.setOffsetX(0.0);
        dropShadow.setOffsetY(0.0);
        this.line.setEffect(dropShadow);
        line.setStrokeWidth(4);
        line.setStrokeLineCap(StrokeLineCap.ROUND);
    }
}
