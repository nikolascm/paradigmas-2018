
package gerador;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.rendering.PDFRenderer;

public class CustomPageDrawer {
    
    CustomPageDrawer(File file) throws IOException {

        try (PDDocument doc = PDDocument.load(file))
        {
            PDFRenderer renderer = new PDFRenderer(doc);
            BufferedImage image = renderer.renderImage(0);
            ImageIO.write(image, "PNG", new File("page.png"));
        }
    }
}