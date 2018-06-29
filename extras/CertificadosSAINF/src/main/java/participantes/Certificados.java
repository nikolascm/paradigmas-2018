package participantes;

import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfReader;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.property.TextAlignment;
import com.itextpdf.layout.property.VerticalAlignment;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Certificados  {
    
    public static final String ORIGEM = "Modelo.pdf";
    public static final String DESTINO = "Participantes.pdf";
    public static final String PATH = Certificados.class.getProtectionDomain().getCodeSource().getLocation().getPath();

    public static void main(String[] args) throws Exception {
        File file = new File(PATH+DESTINO);
        file.getParentFile().mkdirs();
        new Certificados().manipulatePdf(PATH+DESTINO);
    }
    
    public int getNames(ArrayList <String> nomes) throws FileNotFoundException {

        String lista = PATH+"participantes.txt";
        File file = new File(lista);
        Scanner input = new Scanner(file);
            nomes.add(input.nextLine());
        return nomes.size();
    }
 
    public void manipulatePdf(String dest) throws Exception {
        PdfDocument pdfDoc = new PdfDocument(new PdfReader(PATH+ORIGEM), new PdfWriter(PATH+DESTINO));
        PdfFont arial = PdfFontFactory.createFont(PATH+"Arial.ttf");
        try (Document doc = new Document(pdfDoc)) {
            ArrayList <String> nomes = new ArrayList();
            int tamanho = getNames(nomes);
            float x=0, y=0;
            int j = 0;
            for (int i = 1; i <= tamanho; i++) {
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
    }
}