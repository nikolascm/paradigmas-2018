

import java.util.ArrayList;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.PieChart;
import javafx.scene.layout.VBox;
import javafx.scene.layout.HBox;

public class View extends Application { 
  
  public BarChart<String,Integer> barChart;
  public PieChart pieChart;
  
  public Control control = new Control();
  public ArrayList <Label> labels = new ArrayList();
  public TableView<TableData> table = new TableView<>();
  
  public static void main(String[] args) {
    launch(args);
  }
  
  @Override
  public void start(Stage stage) {
    
    barChart = inicia_BarChart();
    pieChart = inicia_PieChart();

    ObservableList<TableData> data = FXCollections.observableArrayList();

    final Label label = new Label("Monitoramento de Ônibus Urbanos do Rio de Janeiro");
    label.setFont(new Font("Helvetica", 20));
    
    TableColumn<TableData,String> dataCol = new TableColumn<>("Data e Hora");
    dataCol.setCellValueFactory(cellData -> cellData.getValue().DATAHORA_Property());
    
    TableColumn<TableData,String> ordemCol = new TableColumn<>("Ordem");
    ordemCol.setCellValueFactory(cellData -> cellData.getValue().ORDEM_Property());
    
    TableColumn<TableData,String> linhaCol = new TableColumn<>("Linha");
    linhaCol.setCellValueFactory(cellData -> cellData.getValue().LINHA_Property());
    
    TableColumn<TableData,String> latCol = new TableColumn<>("Latitude");
    latCol.setCellValueFactory(cellData -> cellData.getValue().LATITUDE_Property());
    
    TableColumn<TableData,String> longCol = new TableColumn<>("Longitude");
    longCol.setCellValueFactory(cellData -> cellData.getValue().LONGITUDE_Property());
    
    TableColumn<TableData,String> velCol = new TableColumn<>("Velocidade");
    velCol.setCellValueFactory(cellData -> cellData.getValue().VELOCIDADE_Property());
    
    table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
    table.getColumns().add(dataCol);
    table.getColumns().add(ordemCol);
    table.getColumns().add(linhaCol);
    table.getColumns().add(latCol);
    table.getColumns().add(longCol);
    table.getColumns().add(velCol);
    table.setItems(data);
    
    labels.add(new Label("Última leitura: "));
    labels.add(new Label("Data-hora menos recente: "));
    labels.add(new Label("Data-hora mais recente: "));
    labels.add(new Label("Número de veículos: "));
    
    // Botões de ações - TableView
    Button update = new Button("Atualizar");
    control.buttonUpdate(update, table, labels, barChart, pieChart);
   
    // Informações (parte inferior)
    final HBox hbox = new HBox();
    hbox.setPadding(new Insets(10, 10, 25, 10));
    hbox.setAlignment(Pos.CENTER);
    hbox.setSpacing(40);
    for(Label l:labels)
    hbox.getChildren().add(l);
    
    // Tabela de Dados dos veículos
    final VBox tableView = new VBox();
    tableView.setSpacing(30);
    tableView.setPadding(new Insets(25, 10, 10, 10));
    tableView.getChildren().addAll(label, table, update);
    tableView.setAlignment(Pos.CENTER);
    tableView.applyCss();
    tableView.layout();
    
    // Gráficos de barra e pizza
    final HBox graficos = new HBox();
    graficos.setAlignment(Pos.CENTER);
    graficos.getChildren().add(barChart);
    graficos.getChildren().add(pieChart);
    
    // Todos juntos
    final VBox dashboard = new VBox();
    dashboard.getChildren().addAll(tableView,graficos,hbox);
    
    Scene scene = new Scene(new Group());
    stage.setScene(new Scene(dashboard, 1000, 1000));
    stage.show();
  }

  private BarChart<String,Integer> inicia_BarChart() {
        
    final CategoryAxis xAxis = new CategoryAxis();
    final NumberAxis yAxis = new NumberAxis();
    xAxis.setLabel("Linha");       
    yAxis.setLabel("Veículos");
        
    BarChart bc = new BarChart<>(xAxis,yAxis);
    bc.setTitle("Veículos por linha (em movimento)");
    return bc;
  }

  private PieChart inicia_PieChart() {
        
    final PieChart pc = new PieChart();
    pc.setTitle("Porcentagem de veículos");
    return pc;
  }
}
