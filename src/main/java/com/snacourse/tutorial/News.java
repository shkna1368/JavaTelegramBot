package com.snacourse.tutorial;

public class News {
    private String title;
    private String summary;
    private String imageUrl;
    private String newsUrl;

    public String getNewsUrl() {
        return newsUrl;
    }

    public void setNewsUrl(String newsUrl) {
        this.newsUrl = newsUrl;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }


    @Override
    public String toString() {
        String styledText=
                imageUrl+'\n'+'\n'+
                        title+'\n'+'\n'+
                summary+'\n'+'\n'+
                        "لینک خبر:"+'\n'+newsUrl;



        return styledText;
    }
}
