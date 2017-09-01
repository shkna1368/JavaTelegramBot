package com.snacourse.tutorial;

public class Food {

    private String foodName;
    private String imageURL;
    private String link;


    public String getFoodName() {
        return foodName;
    }

    public void setFoodName(String foodName) {
        this.foodName = foodName;
    }

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }


    @Override
    public String toString() {
      String styledText=foodName+'\n'+imageURL+'\n'+"توضیحات کامل در لینک زیر:"+'\n'+link;
      //  String styledText=foodName+'\n'+'\n'+"توضیحات کامل در لینک زیر:"+'\n'+link;
        return  styledText;
    }
}
