
package TesteWebScraping;

import java.io.IOException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import java.io.File;
import java.net.URL;
import org.apache.commons.io.FileUtils;

public class TesteWebScraping {
    
    public static void main(String[] args) throws IOException {
        //Get para primeira página
        String baseSite = "http://www.ans.gov.br";
        Document response = Jsoup.connect("http://www.ans.gov.br/prestadores/tiss-troca-de-informacao-de-saude-suplementar").get();
        Element link = response.select(".alert a[href]").first();
        String linkTiss = link.attr("href");
        
        String linkTissFull = baseSite + linkTiss;
        
        Document response2 = Jsoup.connect(linkTissFull).get();
        Element link2 = response2.select("table a[href]").first();
        String linkTissPdf = link2.attr("href");
        
        String linkTissPdfFinal = baseSite + linkTissPdf;
        salvaPDF(linkTissPdfFinal);
    }
    
    public static void salvaPDF(String url) throws IOException {
        URL urlPdf = new URL(url);
        File destination = new File("linkTiss.pdf");
        FileUtils.copyURLToFile(urlPdf, destination);
    }
    
    
}
