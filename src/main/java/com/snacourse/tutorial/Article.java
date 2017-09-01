package com.snacourse.tutorial;

public class Article {
  private String title;
  private String details;
  private String link;


    public String getTitle() {
        return title;
    }



    public void setTitle(String title) {
        this.title = title;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }





    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    @Override
    public String toString() {

        String styledText=title+'\n'+'\n'+
                details+'\n'+'\n'+
                link;
        return styledText;
    }
}
