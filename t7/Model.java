

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

/**
 *
 * @author nikolas
 */
public class Model {

    public List <List> dataList;
    public HttpJSONService http = new HttpJSONService();
    public ArrayList <TableData> dados = new ArrayList();

    public void set_ArrayDados() {
        if(dados!=null)
            dados.clear();
        for(List l : dataList)
        dados.add(new TableData(l));
    }
  
    public int get_TotalVeiculos(){
        return dados.size();
    }
  
    public String get_primeiraDataHora() {
        return dados.get(0).get_DATAHORA();
    }
  
    public String get_ultimaDataHora() {
        return dados.get(dados.size()-1).get_DATAHORA();
    }
  
    public String get_ultimaLeitura() { 
        DateFormat dateFormat = new SimpleDateFormat("MM-dd-yyyy HH:mm:ss"); 
        Date date = new Date(); 
        return dateFormat.format(date); 
    }

    public int get_VeiculosporLinha(String linha) {
        int total=0;
        for(TableData d: dados) {
            if(d.get_LINHA().equals(linha) && d.get_VELOCIDADE() != 0.0)
                total++; 
        }
        return total;
    }
  
    public int get_VeiculosParados() {
        int total=0;
        for(TableData d: dados)
            if(d.get_VELOCIDADE()==0.0)
                total++;
        return total;
    }

    ArrayList<String> get_TotalLinhas() {
        ArrayList<String> linhas = new ArrayList();
        for (TableData d : dados) {
            if(!linhas.contains(d.get_LINHA()))
                linhas.add(d.get_LINHA());
        }
        return linhas;
    }
}

class HttpJSONService {
    
    private final String USER_AGENT = "Mozilla/5.0";
    private final JSONParsing engine = new JSONParsing();

    // HTTP GET request
    public Map sendGet(String url) throws Exception {
        
        URL obj = new URL(url);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();
        con.setRequestMethod("GET");
        con.setRequestProperty("User-Agent", USER_AGENT);
    
        int responseCode = con.getResponseCode();
        System.out.println("\n'GET' request sent to URL : " + url);
        System.out.println("Response Code : " + responseCode);
    
        StringBuffer response;
        try (BufferedReader in = new BufferedReader(
            new InputStreamReader(con.getInputStream()))) {
                response = new StringBuffer();
                String inputLine;
                while ((inputLine = in.readLine()) != null) {
                    response.append(inputLine);
                }     
            }
    
        // Parse JSON result
        JSONParsing engine = new JSONParsing();
        return engine.parseJSON(response.toString());
    } 
}

class JSONParsing {
    
    private final ScriptEngine engine;
  
    public JSONParsing() {
        ScriptEngineManager sem = new ScriptEngineManager();
        this.engine = sem.getEngineByName("javascript");
    }
  
    public Map parseJSON(String json) throws IOException, ScriptException {
        String script = "Java.asJSONCompatible(" + json + ")";
        Object result = this.engine.eval(script);
        Map contents = (Map) result;
        return contents;
    }
}
