
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
        
        //Utilizando a biblioteca do "jsoup" e o método "get" para acessar a página principal. 
        String baseSite = "http://www.ans.gov.br";
        Document response = Jsoup.connect("http://www.ans.gov.br/prestadores/tiss-troca-de-informacao-de-saude-suplementar").get();
        
        //Através do código html da página referenciei/filtrei apenas o que tinha por objetivo.
        Element link = response.select(".alert a[href]").first();
        String linkTiss = link.attr("href");
        
        //Concatenando as informações já coletadas, fiz esta concatenação porque os hrefs não são URLs completas.
        String linkTissFull = baseSite + linkTiss;
        
        //Através do código html da página referenciei/filtrei apenas o que tinha por objetivo.
        Document response2 = Jsoup.connect(linkTissFull).get();
        Element link2 = response2.select("table a[href]").first();
        String linkTissPdf = link2.attr("href");
        
        //Concatenando endereços
        String linkTissPdfFinal = baseSite + linkTissPdf;
        salvaPDF(linkTissPdfFinal);
    }
    
    //Salvando o arquivo mais atual da tabela padrao tiss componente_organizaciona.
    public static void salvaPDF(String url) throws IOException {
        URL urlPdf = new URL(url);
        File destination = new File("linkTiss.pdf");
        FileUtils.copyURLToFile(urlPdf, destination);
    }
    
    
}
