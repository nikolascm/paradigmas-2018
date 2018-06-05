import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.LinkedList;

public class Grafo extends Main {

    Integer ArestasSobrepostas;
    LinkedList <Aresta> arestas;
    LinkedList <Vertice> vertices;
    boolean grafoSalvo = false;
    
    public Grafo() {
        ArestasSobrepostas = 0;
        arestas = new LinkedList();
        vertices = new LinkedList();
    }
    
    public Integer ArestasSobrepostas(Aresta a) {
        for(Aresta x : this.arestas) {
            if(x.line.getLayoutBounds().intersects(a.line.getLayoutBounds())) {
               ArestasSobrepostas++;
            }
        }
        return ArestasSobrepostas;
    }
    
    public boolean insereVertice(Vertice v) {
        if(this.verificaVerticeValido(v)) {
            this.vertices.add(v);
            return true;
        }
        return false;
    }
    
    public boolean insereAresta(Aresta a) {
        if(this.verificaArestaValida(a)) {
            this.arestas.add(a);
            return true;
        }
        return false;
    }
    
    public boolean verificaArestaValida(Aresta a) {
        Vertice origem = null;
        Vertice destino = null;
        if(vertices.size()<2)
            return false;
        
        for(Vertice v1 : vertices) {
            if((Math.pow(a.line.getStartX() - v1.circle.getCenterX(),2) +
                Math.pow(a.line.getStartY() - v1.circle.getCenterY(),2)) < 
                Math.pow(v1.circle.getRadius(),2)) {
                origem = v1;           
            }
        }
        for(Vertice v2 : vertices) {
            if((Math.pow(a.line.getEndX() - v2.circle.getCenterX(),2) +
                Math.pow(a.line.getEndY() - v2.circle.getCenterY(),2)) < 
                Math.pow(v2.circle.getRadius(),2)) {
                destino = v2;
            }
        }
        
        if(origem!=null && destino!=null && (destino!=origem)) {
            a.line.setStartX(origem.circle.getCenterX());
            a.line.setStartY(origem.circle.getCenterY());
            a.line.setEndX(destino.circle.getCenterX());
            a.line.setEndY(destino.circle.getCenterY());
            
            // Verifica se já existe uma aresta entre origem e destino
            for(Aresta x : origem.adjacencias) {
              for(Aresta y : destino.adjacencias) {
                if(x.equals(y))
                  return false;
              }
            }
            // Adiciona no grafo
            this.ArestasSobrepostas(a);
            origem.adjacencias.add(a);            
            destino.adjacencias.add(a);
            return true;
        }
        else 
            return false;
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

    public boolean grafoVazio() {
        if(this.arestas.isEmpty() && this.vertices.isEmpty())
            return true;
        return false;
    }

    public void criarSVG() {
        try {
            PrintWriter writer = new PrintWriter(new Date().getTime() + ".svg", "UTF-8");
            writer.println("<html>");
            writer.println("<body>");
            writer.println("<svg width=\"800\" height=\"600\">");
            for (Aresta a : this.arestas){
                writer.println("<line x1=\""+a.line.getStartX()+"\" y1=\""+a.line.getStartY()+"\" x2=\""+a.line.getEndX()+"\" y2=\""+a.line.getEndY()+"\" style=\"stroke: rgb(0,0,0);stroke-width:"+a.line.getStrokeWidth()+"\" />");
            }
            for (Vertice v: this.vertices){
                writer.println("<circle cx=\""+v.circle.getCenterX()+"\" cy=\""+v.circle.getCenterY()+"\" r=\""+v.circle.getRadius()+"\" stroke=\"blue\" stroke-width=\""+v.circle.getStrokeWidth()+"\" fill=\"\" />");
            }
            writer.println("</svg>");
            writer.println("</body>");
            writer.println("</html>");
            writer.close();
        } catch (IndexOutOfBoundsException e) {
            System.err.println("IndexOutOfBoundsException: " + e.getMessage());
        } catch (IOException e) {
            System.err.println("Caught IOException: " + e.getMessage());
        }
    }
    public boolean grafoSalvo(){
        return grafoSalvo;
    }
}
