import java.awt.geom.Line2D;
import java.util.LinkedList;
import java.util.Random;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Line;

public class Grafo extends Main {
    
    int sobrepostas;
    boolean movendo = false; 
    LinkedList <Aresta> arestas;
    LinkedList <Vertice> vertices;
    
    public Grafo() {
        sobrepostas = 0;
        arestas = new LinkedList();
        vertices = new LinkedList();
        this.inicia();
   }
    
    public void inicia() {
        this.geraVertices(this.numVertices());
    }
    
    public void limpa() {
        vertices.clear();
        arestas.clear();
    }           

    public void atualizaDados(Label l1, Label l2, Label l3) {
        l1.setText("Sobrepostas: "+this.sobrepostas);
        l2.setText("Vértices: "+this.vertices.size());
        l3.setText("Arestas: "+this.arestas.size());
    }
    
    public void geraVertices(int total) {
        
        double minX = 30;
        double maxX = 850;
        double minY = 30;
        double maxY = 500;
        
        for(int i=0; i<total; i++) {
            Random x = new Random();
            Random y = new Random();

            double randomValueX = minX + (maxX - minX) * x.nextDouble();
            double randomValueY = minY + (maxY - minY) * y.nextDouble();
            
            Vertice v = new Vertice(randomValueX,randomValueY);
            if (insereVertice(v)) {
                insereArestas(v);
            }
            ArestasSobrepostas();
        }
    }
    
    public int numVertices() {
        Random r = new Random();
        int r1 = r.nextInt(4) + 8;
        return r1;
    }
    
    public void ArestasSobrepostas() {
        int cont = 0;
        for(Aresta a1 : this.arestas) {
          for(Aresta a2: this.arestas) {
            if(!this.arestasIguais(a1,a2) && Line2D.linesIntersect(
                a1.inicio.circle.getCenterX(),
                a1.inicio.circle.getCenterY(),
                a1.fim.circle.getCenterX(),
                a1.fim.circle.getCenterY(),
                a2.inicio.circle.getCenterX(),
                a2.inicio.circle.getCenterY(),
                a2.fim.circle.getCenterX(),
                a2.fim.circle.getCenterY())) {
                    cont++;
                }
            }
        }
        sobrepostas = cont/2;
    }
    
    public final boolean insereVertice(Vertice v) {
        if(this.verificaVerticeValido(v)) {
            this.vertices.add(v);
            return true;
        }
        return false;
    }
    
    public final Aresta insereArestas(Vertice ultimo) {
        
        if(vertices.size()>1) {
            Vertice anterior = vertices.get(vertices.size()-2);
            
            Line line = new Line();
            line.setStartX(ultimo.circle.getCenterX());
            line.setStartY(ultimo.circle.getCenterY());
            line.setEndX(anterior.circle.getCenterX());
            line.setEndY(anterior.circle.getCenterY());
            
            Aresta a = new Aresta(line);
            this.arestas.add(a);
            a.inicio = ultimo;
            a.fim = anterior;

            ultimo.adjacencias.add(a);
            anterior.adjacencias.add(a);
            return a;
        }
        return null;
    }
    
    public boolean verificaVerticeValido(Vertice v) {
        if(vertices.isEmpty()) 
            return true;
        for(Vertice v1 : vertices) {        
            if(v1.circle.getLayoutBounds().intersects(v.circle.getLayoutBounds()))
                return false;
        }
        return true;
    }

    private boolean arestasIguais(Aresta a1, Aresta a2) {
        if(a1.inicio.equals(a2.inicio) || 
           a1.inicio.equals(a2.fim) || 
           a1.fim.equals(a2.inicio) || 
           a1.fim.equals(a2.fim))
           return true;
        else 
           return false;
    }
}
