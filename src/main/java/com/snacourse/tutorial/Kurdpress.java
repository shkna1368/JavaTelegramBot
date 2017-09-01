package com.snacourse.tutorial;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Kurdpress  {
    Document doc;
    List<News> newsList;


    public Kurdpress() {


    }

    private void connectToSite(int lang){

        String url=null;
if (lang==1) {

     url = "http://www.kurdpress.com/";
}

else if (lang==2) {
    url = "http://www.kurdpress.com/en/";
}

        try {
            doc = Jsoup.connect(url).timeout(15000).get();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }



    public   List<News> run(int lang) {
        System.out.println("run thread kurdoress");
        connectToSite(lang);
      List<News>  newsList= extractNews();
return newsList;
    }


    private List<News> extractNews(){

        // div with class=masthead
        //    Elements divs = doc.select("div.itemsHolder");
        Elements uls = doc.select("ul.dNon");
        // System.out.println("divs="+divs.size());
        System.out.println("uls="+uls.size());
        //  Elements spans = divs.select("span.flL curP mR5 mL5 alC oHid dBlk");
        //System.out.println("span="+ spans.size());
newsList=new ArrayList<News>(uls.size());
        for (Element element:uls){
            //   Element ul = element.select("ul.dNon").first();

            String imageUrl=  element.select("li.img").first().text();
            String title=  element.select("li.titr").first().text();
            String summary=  element.select("li.sotitr").first().text();
            String newsUrl=  element.select("li.url").first().text();

            System.out.println("in kurdpress thread");
            System.out.println('\n'+" in kurdpress title:"+title);
            System.out.println('\n'+" in kurdpress image url:"+imageUrl);
            System.out.println('\n'+"in kurdpress summary:"+summary);
            System.out.println('\n'+" in kurdpress news url:"+newsUrl);

            News news=new News();
            news.setImageUrl(imageUrl);
            news.setSummary(summary);
            news.setTitle(title);
            news.setNewsUrl(newsUrl);

            newsList.add(news);



        }


        return newsList;


    }
}
