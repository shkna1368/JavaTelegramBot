package com.snacourse.tutorial;

public class Product {
    private String name;
    private String descryption;
    private String price;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescryption() {
        return descryption;
    }

    public void setDescryption(String descryption) {
        this.descryption = descryption;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    @Override
    public String toString() {
        String styledText="نام محصول:"+name+'\n'+'\n'+
                " توضیحات:"+descryption+'\n'+'\n'+
                " قیمت:"+price+'\n'+'\n';
        return styledText;
    }
}
