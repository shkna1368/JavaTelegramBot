package com.snacourse.tutorial;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ScienceDirect {


    Document doc;
    List<Article> articles;




   // private void connectToSite(String keyword){





   /* public   List<Article> run(String  keyword) {
        System.out.println("run thread kurdoress");
       // connectToSite(keyword);
        List<Article>  newsList= extractArticle();
        return newsList;
    }

*/
    public List<Article> extractArticle(String keyword){
      //  http://www.sciencedirect.com/search?qs=ai&authors=&pub=&volume=&issue=&page=&origin=home&zone=qSearch&show=50
            String url = "http://www.sciencedirect.com/search?qs="+keyword+"&authors=&pub=&volume=&issue=&page=&origin=home&zone=qSearch&show=50";


            try {
                doc = Jsoup.connect(url).timeout(15000).get();
            } catch (IOException e) {
                e.printStackTrace();
            }


        Elements uls = doc.getElementsByClass("ResultItem col-xs-24 push-m");


        System.out.println("uls="+uls.size());

        articles=new ArrayList<Article>(uls.size());

        for (Element element:uls){
Element tagTitle=element.getElementsByTag("h2").first();

            String title=  tagTitle.text();


         //   Authors hor undefined
           Element authors=element.getElementsByClass("Authors hor undefined").first();
          // Element orginalElement=element.getElementsByClass("OpenAccessArchive hor").first();
           Element detilsElement=element.getElementsByClass("SubType hor").first();
          //  System.out.println(authors.toString());
            String auts=null;
            String details=null;
            String orginal=null;
        /*    try {

                 auts=authors.text();
            }
            catch (Exception e){
                auts="";
            }
*/

  try {

                 details=detilsElement.text();
            }
            catch (Exception e){
                details="";
            }












            Element elementsURL=     tagTitle.select("a[href]").first();

            //
            String articleURL=elementsURL.attr("abs:href");




            System.out.println("title="+title);
            System.out.println("articleURL="+articleURL);
            //System.out.println("aut="+auts);
            System.out.println("details="+details);


Article article=new Article();

article.setTitle(title);
article.setDetails(details);
article.setLink(articleURL);

articles.add(article);


        }


        return articles;


    }

}
