package com.snacourse.tutorial;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
public class CookingCrawler {

    public List<Food> getFoods(String food){

        List<Food> foods=null;

        String userAgent=  ChangeBrowser.getInstance().selectRandomFromBrowser();
        try {


            //entry one-fourth recipe-item
String url="https://ashpazbashy.com/?s="+food+"&post_type=any";
            System.out.println(url);
          //  https://ashpazbashy.com/?s=%D8%A2%D8%A8%DA%AF%D9%88%D8%B4%D8%AA&post_type=any
            Document doc=  Jsoup.connect(url)
                    .userAgent(userAgent)
                    .timeout(25000)
                    .get();




/*<section class="col--small-12 col--medium-8 col--large-9">*/

            //  Elements elSelection= doc.select("section.section--movie");
          //  Elements elSelection= doc.select("div.entry one-fourth recipe-item");
           // Elements elSelection= doc.select("div.entries row");
            Elements elSelection= doc.getElementsByClass("entries row");
          //  System.out.println(elSelection.toString());
             foods=new ArrayList<Food>(elSelection.size());

             //System.out.println(foods.size());
            for (Element el:elSelection){



            Elements elementsURL=     el.select("a[href]");


            //
               String foodLink=elementsURL.get(0).attr("abs:href");
                System.out.println("food link="+foodLink);

                Element image = el.getElementsByTag("img").first();
                String urlImage = image.absUrl("data-lazy-src");

                System.out.println("image url="+urlImage);

       String title=   el.getElementsByTag("h2").text();


                Food food1=new Food();
                food1.setFoodName(title);


                food1.setImageURL(urlImage);
            food1.setLink(foodLink);
               foods.add(food1);


            }


        } catch (IOException e) {
            e.printStackTrace();
        }

return foods;
    }
}
