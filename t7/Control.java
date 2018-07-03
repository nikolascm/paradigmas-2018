

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;

/**
 *
 * @author nikolas
 */
public class Control {

    Model model = new Model();
    
    public void atualiza_Labels(ArrayList <Label> labels) {
        labels.get(0).setText("Última leitura: "+model.get_ultimaLeitura());
        labels.get(1).setText("Data-hora menos recente: "+model.get_primeiraDataHora());
        labels.get(2).setText("Data-hora mais recente: "+model.get_ultimaDataHora());
        labels.get(3).setText("Número de veículos: "+model.get_TotalVeiculos());
    }
    
    public void atualiza_PieChart(PieChart pc) {
        int parados = model.get_VeiculosParados();
        int movimento = model.get_TotalVeiculos()-parados;
        
        ObservableList<PieChart.Data> pieChartData =
                FXCollections.observableArrayList(
                new PieChart.Data("Parados = "+String.valueOf(parados), parados),
                new PieChart.Data("Em movimento = "+String.valueOf(movimento), movimento));
        
        pc.setData(pieChartData);
        
    }
    
    public void atualiza_BarChart(BarChart bc) {
        
        ArrayList <String> linhas = model.get_TotalLinhas();
        XYChart.Series<String,Integer> series = new XYChart.Series<>();
        series.setName("Veículos por linha");
        for(String s: linhas) {
            int movimento = model.get_VeiculosporLinha(s);
            if(movimento>0)
                series.getData().add(new XYChart.Data(s,movimento));
        }
        bc.getData().clear();
        bc.getData().add(series);
    }
    
    public void obterPosicoes(Map json, TableView<TableData> table) {
        
        ObservableList<TableData> data = FXCollections.observableArrayList();
        model.dataList = (List) json.get("DATA");
        for(List l : model.dataList)
            data.add(new TableData(l));
        set_ArrayDados();
        table.setItems(data);
    }
    
    public void set_ArrayDados() {
        
        if(model.dados!=null)
          model.dados.clear();
        for(List l : model.dataList)
        model.dados.add(new TableData(l));
    } 
    
    public void buttonUpdate(Button update, TableView<TableData> table, ArrayList <Label> labels, BarChart bc, PieChart pc) {
        update.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Map json = null;
                try {
                    json = model.http.sendGet("http://dadosabertos.rio.rj.gov.br/apiTransporte/apresentacao/rest/index.cfm/obterTodasPosicoes");
                } catch (Exception e) {
                    Alert alert = new Alert(Alert.AlertType.WARNING);
                    alert.setHeaderText("Falha de conexão");
                    alert.setContentText("Verifique sua conexão com a internet!");
                    alert.showAndWait();
                }
                if (json != null) {
                    obterPosicoes(json, table);
                    atualiza_BarChart(bc);
                    atualiza_PieChart(pc);
                    atualiza_Labels(labels);
                }
            }
        });
    }
}
