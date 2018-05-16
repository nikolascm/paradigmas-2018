import java.util.ArrayList;

abstract class Bloco {
    public abstract Bloco drop();
}
class Madeira extends Bloco {
    public Bloco drop(){
      Bloco m = new Madeira();
      System.out.println("Madeira quebrada");
      return m;
    }
}
class Grama extends Bloco {
    public Bloco drop(){
      Bloco t = new Terra();
      String className = this.getClass().getName();
      String variableName = t.getClass().getName();
      System.out.println(className+" quebrada em "+variableName);
      return t;
    }
}
class Terra extends Bloco {
    public Bloco drop(){
      Bloco t = new Terra();
      System.out.println("Terra quebrada");
      return t;
    }
}
class Pedra extends Bloco {
    public Bloco drop(){
      Bloco p = new Pedregulho();
      System.out.println("Pedra quebrada");
      return p;
    }
}
class Pedregulho extends Bloco {
    public Bloco drop(){
      Bloco p = new Pedregulho();
      System.out.println("Pedregulho quebrado");
      return p;
    }
}

class Main {
    public static void main(String[] args) {
        ArrayList<Bloco>blocs = new ArrayList();
        blocs.add(new Madeira());
        blocs.add(new Grama());
        blocs.add(new Terra());
        blocs.add(new Pedra());
        blocs.add(new Pedregulho());        
        for(Bloco b: blocs)
            b.drop();
    }    
}
