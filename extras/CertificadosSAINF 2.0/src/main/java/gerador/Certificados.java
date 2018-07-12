package gerador;

import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfReader;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.kernel.utils.PdfMerger;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.property.TextAlignment;
import com.itextpdf.layout.property.VerticalAlignment;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import javafx.scene.control.Alert;
import javafx.scene.control.TextArea;

public class Certificados  {
    
    public String ORIGEM;
    public String DESTINO;
    public String LISTA;

    public static final String TMP = "src/main/java/gerador/Modelo.pdf" ;
    public static final String FONTE = "src/main/java/gerador/Arial.ttf";

    public ArrayList <String> nomes;
    
    public void setPathOrigem (File file) {
        ORIGEM = file.getPath();
        Alert alerta = new Alert(Alert.AlertType.INFORMATION);
        alerta.setHeaderText("Arquivo aberto com sucesso!");
        alerta.showAndWait();
    }
    
    public void setPathDestino (File file) {
        DESTINO = file.getPath();
        Alert alerta = new Alert(Alert.AlertType.INFORMATION);
        alerta.setHeaderText("Arquivo salvo com sucesso!");
        alerta.showAndWait();
    }
    
    public void setPathLista(File file) {
        LISTA = file.getPath();
        Alert alerta = new Alert(Alert.AlertType.INFORMATION);
        alerta.setHeaderText("Arquivo importado com sucesso!");
        alerta.showAndWait();
    }
    
    public void replicaArquivoModelo(int tamanho, String s1, String s2) throws IOException {
        try(PdfDocument pdfDoc = new PdfDocument(new PdfWriter(s1)); PdfDocument cover = new PdfDocument(new PdfReader(s2))) {
            PdfMerger merger = new PdfMerger(pdfDoc);
            for(int i=1; i<=tamanho;i++)
                merger.merge(cover, 1, cover.getNumberOfPages());
        }
    }
    
    public void criaCopias() throws IOException, Exception {
        
        nomes = new ArrayList<>();
        int tamanho = adicionaNomes(nomes);
        replicaArquivoModelo(tamanho,TMP,ORIGEM);
        manipulaPDF();
    }    
    
    public int adicionaNomes(ArrayList <String> nomes) throws FileNotFoundException {
        
        File file = new File(LISTA);
        Scanner input = new Scanner(file);
        while(input.hasNextLine())
            nomes.add(input.nextLine());
        return nomes.size();
    }
 
    public void manipulaPDF() throws Exception {
        
        int tamanho = nomes.size();
        
        PdfDocument pdfDoc = new PdfDocument(new PdfReader(TMP),new PdfWriter(DESTINO));
        PdfFont arial = PdfFontFactory.createFont(FONTE);
        try (Document doc = new Document(pdfDoc)) {
            float x=0, y=0;
            int j = 0;
            for (int i = 1; i <=tamanho; i++) {
                Paragraph header1 = new Paragraph(nomes.get(j))
                        .setFont(arial)
                        .setBold()
                        .setFontSize(18);
                
                Paragraph header2 = new Paragraph("CERTIFICAMOS QUE ")
                        .setFont(arial)
                        .setFontSize(18);
                
                pdfDoc.getPage(i).setIgnorePageRotationForContent(true);
                if (pdfDoc.getPage(i).getRotation() % 180 == 0) {
                    x = pdfDoc.getPage(i).getPageSize().getWidth() / 2;
                    y = 350;
                }
                
                doc.showTextAligned(header2.add(header1), x, y, i, TextAlignment.CENTER, VerticalAlignment.BOTTOM, 0);
                j++;
            }
        }
        File f = new File(TMP);
        f.delete();
    }

    void getTextArea(TextArea textArea) throws FileNotFoundException, IOException, Exception {
        if(!textArea.getText().isEmpty()) {
            if(nomes==null) {
                nomes = new ArrayList<>();
                String txt = textArea.getText();
                String [] arrayOfLines = txt.split("\n");
                for(String line: arrayOfLines){
                    if(!line.isEmpty())
                    nomes.add(line);
                }
                replicaArquivoModelo(nomes.size(),TMP,ORIGEM);
                manipulaPDF();
            }
        }
    }
}