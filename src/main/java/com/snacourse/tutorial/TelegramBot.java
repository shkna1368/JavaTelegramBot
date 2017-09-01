package com.snacourse.tutorial;

import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.objects.Update;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.exceptions.TelegramApiException;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Hashtable;
import java.util.List;

public class TelegramBot extends TelegramLongPollingBot {
Hashtable <String,Integer> cityHashtable;
Hashtable <Long,String> userCommands;




    public String getBotUsername() {
        return "cheishtkhana_bot";
    }
WeatherHelper weatherHelper;
    ExcelReader excelReader;

    public String getBotToken() {
        return "229873366:AAH6qgnQuy4ZsAKFfZmt8_QhMbuRe1dY1Uw";
    }



    private void initCity(){
        cityHashtable=new Hashtable<String, Integer>(5);
        cityHashtable.put("/mahabad",125446);
        cityHashtable.put("/kobani",173711);

        cityHashtable.put("/hawlir",95446);
        cityHashtable.put("/sanandaj",117574);
        cityHashtable.put("/diarbakr",316541);
        cityHashtable.put("/kermanshah",128226);
        cityHashtable.put("/solaymania",98463);
        cityHashtable.put("/diarbakr",316541);



    }




    public TelegramBot() {

initCity();
weatherHelper=new WeatherHelper();
         excelReader=new ExcelReader();
         userCommands=new Hashtable<Long, String>();

    }

    public void onUpdateReceived(Update update) {
        if (update.hasMessage() && update.getMessage().hasText()) {
            SendMessage message = new SendMessage() // Create a SendMessage object with mandatory fields
                    .setChatId(update.getMessage().getChatId())
                    .setText(update.getMessage().getText());
            String messages = update.getMessage().getText();

            System.out.println("message=" + update.getMessage().getText());
            try {
                handleMessage(update);
                //sendMessage(message); // Call method to send the message
            } catch (TelegramApiException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }



    private void handleMessage(Update update) throws TelegramApiException, IOException {

        String userMessage = update.getMessage().getText();
        SendMessage serverMessage = new SendMessage();

        //  .setChatId(update.getMessage().getChatId())
        //.setText(update.getMessage().getText());

        long senderId = update.getMessage().getChatId();
        serverMessage.setChatId(senderId);
        if (userMessage.equals("/start_cooking")||userMessage.equals("/cheisht")) {
            serverMessage.setText("به خیر بین-خوش آمدید"+'\n'+"تکایه ناوی چیشتی هه لبژارده ی خوت بنوسیت-لطفا نام غذای خود را وارد نمایید");
          //  serverMessage.setText("ناو چیشته که ت بنوسه");

            saveUserCommand(senderId,userMessage);
            sendMessage(serverMessage);

            return;
        }
        if (userMessage.equals("/sanandaj")||userMessage.equals("/mahabad")||userMessage.equals("/kermanshah")||userMessage.equals("/hawlir")||userMessage.equals("/solaymania")||userMessage.equals("/diarbakr")||userMessage.equals("/kobani") ) {
           // serverMessage.setText("https://t.me/hamiapplication/9");
          //  sendMessage(serverMessage);
            handleWether(userMessage, serverMessage);
            return;
        }

        if (userMessage.equals("/inventory")) {
            handleInventory(serverMessage);
            return;
        }

       if (userMessage.equals("/article")) {
           serverMessage.setText(
                   "تکایه ناوی گوتاری هه لبزارده ی خوت بنوسیت"+'\n'+

                   "لطفا نام مقاله ی خود را وارد نمایید."+'\n');
           //  serverMessage.setText("ناو چیشته که ت بنوسه");
saveUserCommand(senderId,userMessage);
           saveUserCommand(senderId,userMessage);
           sendMessage(serverMessage);
            return;
        }


        if (userMessage.equals("/kurdestan_news_kurdi")){

handleNews(serverMessage,1);
return;
        }





          if (userMessage.equals("/kurdestan_news_english")){

              handleNews(serverMessage,2);
              return;

        }



String lastCommand=userCommands.get(senderId);
       // System.out.println(lastCommand);

          if (null==lastCommand){

              serverMessage.setText("تکایه فه رمانی دروس له منوی خوار هه لبژیریت و دوایی تکستی خوت بنوسیت      "+'\n'+


                      "لطفا از منوی زیر دستور مناسب را انتخاب نمایید و سپس متن خود را وارد نمایید.");

              sendMessage(serverMessage);
return;
          }


          if (lastCommand.equals("/start_cooking")||lastCommand.equals("/cheisht")){

            handleCooking( userMessage, serverMessage);
          }
          else if(lastCommand.equals("/article")){
              handleArticle(userMessage,serverMessage);

        }

    }

    private void saveUserCommand(long userId,String currentCommand){
userCommands.put(userId,currentCommand);

    }

    private String[] splitByNumber(String s, int chunkSize){
        int chunkCount = (s.length() / chunkSize) + (s.length() % chunkSize == 0 ? 0 : 1);
        String[] returnVal = new String[chunkCount];
        for(int i=0;i<chunkCount;i++){
            returnVal[i] = s.substring(i*chunkSize, Math.min((i+1)*chunkSize, s.length()));
        }
        return returnVal;
    }

private void    handleArticle(String keyword,SendMessage serverMessage) throws TelegramApiException {
   // serverMessage.setParseMode("HTML");
        ScienceDirect scienceDirect=new ScienceDirect();
      List<Article> articles=  scienceDirect.extractArticle(keyword);

      if (articles.size()>0){


          for (Article article:articles){
              serverMessage.setText(article.toString());
              sendMessage(serverMessage);
          }
      }
      else {
          serverMessage.setText("به داخه وه گوتاریکم نه دوزیه و-متاسفانه مقاله ای یافت نشد");
          sendMessage(serverMessage);

      }


    }

    private  void handleInventory(SendMessage serverMessage) throws TelegramApiException, IOException {

       List<Product> productList= excelReader.getAllProduct();
        for (Product product:productList){
             String prdcts=product.toString();



            if(prdcts.length()>=3000){
            String[] productParts= splitByNumber(prdcts,3000);
final int charLength=productParts.length;
                for (int i=0;i<charLength;i++){
                    serverMessage.setText(productParts[i]);
                    sendMessage(serverMessage);


                }


            }
else {
                serverMessage.setText(prdcts);
                sendMessage(serverMessage);

            }


      }

    }

    private  void handleNews(SendMessage serverMessage,int lang) throws TelegramApiException, IOException {


        Kurdpress kurdpress=new Kurdpress();
     List<News>  newsList=  kurdpress.run(lang);

     for (News news:newsList){
         serverMessage.setText(news.toString());
         sendMessage(serverMessage);
     }




    }

    private  void handleWether(String userMessage,SendMessage serverMessage) throws TelegramApiException, IOException {

     int cityId=  cityHashtable.get(userMessage) ;
Weather wheater=weatherHelper.getWeatherDarta(cityId);
        System.out.println("data is="+wheater);
        serverMessage.setText(wheater.toString());
        sendMessage(serverMessage);

    }

    private  void handleCooking(String userMessage,SendMessage serverMessage) throws TelegramApiException {

        CookingCrawler cookingCrawler=new CookingCrawler();
       List<Food> foodList=cookingCrawler.getFoods(userMessage);
if(foodList.size()>0) {
    for (Food food : foodList) {
        serverMessage.setText(food.toString());
        sendMessage(serverMessage);

    }

}
else {
    serverMessage.setText("به داخه وه چیشتیکم نه دوزیه و-متاسفانه  غذایی یافت نشد");
    sendMessage(serverMessage);

}


    }


}