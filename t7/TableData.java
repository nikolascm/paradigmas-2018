

import java.util.List;
import javafx.beans.property.SimpleStringProperty;

/**
 *
 * @author nikolas
 */
public class TableData {
    private final SimpleStringProperty data_hora;
    private final SimpleStringProperty ordem;
    private final SimpleStringProperty linha;
    private final SimpleStringProperty latitude;
    private final SimpleStringProperty longitude;
    private final SimpleStringProperty velocidade;
    
    public TableData(List lista) {
      this.data_hora = new SimpleStringProperty((String) lista.get(0));
      this.ordem = new SimpleStringProperty((String) lista.get(1));
      this.linha = new SimpleStringProperty(String.valueOf(lista.get(2)));
      this.latitude = new SimpleStringProperty(String.valueOf(lista.get(3)));
      this.longitude = new SimpleStringProperty(String.valueOf(lista.get(4)));
      this.velocidade = new SimpleStringProperty(String.valueOf(lista.get(5)));
    }

  
    public SimpleStringProperty DATAHORA_Property() {
      return this.data_hora;
    }
    public String get_DATAHORA() {
      return this.data_hora.get();
    }
    public void set_DATAHORA(String id) {
      this.data_hora.set(id);
    }
    
    public SimpleStringProperty ORDEM_Property() {
      return this.ordem;
    }
    public String get_ORDEM() {
      return this.ordem.get();
    }
    public void set_ORDEM(String id) {
      this.ordem.set(id);
    }
    
    public SimpleStringProperty LINHA_Property() {
      return this.linha;
    }
    public String get_LINHA() {
      return this.linha.get();
    }
    public void set_LINHA(String id) {
      this.linha.set(id);
    }
    
    public SimpleStringProperty LATITUDE_Property() {
      return this.latitude;
    }
    public String get_LATITUDE() {
      return this.latitude.get();
    }
    public void set_LATITUDE(String id) {
      this.latitude.set(id);
    }
    
    public SimpleStringProperty LONGITUDE_Property() {
      return this.longitude;
    }
    public String get_LONGITUDE() {
      return this.longitude.get();
    }
    public void set_LONGITUDE(String id) {
      this.longitude.set(id);
    }
    
    public SimpleStringProperty VELOCIDADE_Property() {
      return this.velocidade;
    }
    public double get_VELOCIDADE() {
      return Double.parseDouble(this.velocidade.get());
    }
    public void set_VELOCIDADE(String id) {
      this.velocidade.set(id);
    }
}

