package com.snacourse.tutorial;

import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.TelegramBotsApi;
import org.telegram.telegrambots.exceptions.TelegramApiException;

import java.util.Calendar;

public class Main {


    public static void main(String[] args) {

        ApiContextInitializer.init();


        TelegramBotsApi botsApi = new TelegramBotsApi();

        try {
            botsApi.registerBot(new TelegramBot());
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }

       /* CookingCrawler cookingCrawler=new CookingCrawler();
        cookingCrawler.getFoods("آبگوشت");*/

    }

}

