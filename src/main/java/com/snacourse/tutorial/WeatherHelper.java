package com.snacourse.tutorial;

import com.google.gson.JsonObject;
import org.apache.http.client.ClientProtocolException;

import java.io.IOException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.json.JSONObject;

public class WeatherHelper {


    public Weather getWeatherDarta(int cityId)throws ClientProtocolException, IOException {

        HttpClient client = HttpClientBuilder.create().build();
        String url="http://api.openweathermap.org/data/2.5/forecast?id="+cityId+"&APPID=28ef43c3a635c012c76538672d738a19&&units=metric";
        System.out.println("url="+url);
        HttpGet request = new HttpGet("http://api.openweathermap.org/data/2.5/forecast?id="+cityId+"&APPID=28ef43c3a635c012c76538672d738a19&&units=metric");

        HttpResponse response = client.execute(request);
        BufferedReader rd = new BufferedReader (new InputStreamReader(response.getEntity().getContent()));
        StringBuffer result = new StringBuffer();
        String line = "";
        while ((line = rd.readLine()) != null) {
            System.out.println(line);
            result.append(line);
        }
        JSONObject o = new JSONObject(result.toString());
JSONObject jsonObject=o.getJSONArray("list").getJSONObject(0);
//long date=jsonObject.getLong("dt");
String dates=jsonObject.getString("dt_txt");
        JSONObject   jsonTemp=jsonObject.getJSONObject("main");
        JSONObject   jsonWeather=jsonObject.getJSONArray("weather").getJSONObject(0);
        JSONObject   jsonWind=jsonObject.getJSONObject("wind");


   //  System.out.println("date="+date);
        System.out.println("dates="+dates);
        System.out.println("temp="+jsonTemp);
        System.out.println("weather="+jsonWeather);
        System.out.println("jsonWind="+jsonWind);



        System.out.println("json object="+jsonObject);


        Weather wheater=new Weather();
        wheater.setDate(dates);
        wheater.setTemp(jsonTemp.getDouble("temp"));
        wheater.setTempMin(jsonTemp.getDouble("temp_min"));
        wheater.setTempMax(jsonTemp.getDouble("temp_max"));
        wheater.setGrandLevel(jsonTemp.getDouble("grnd_level"));
        wheater.setSeaLevel(jsonTemp.getDouble("sea_level"));
        wheater.setHumidity(jsonTemp.getInt("humidity"));
        wheater.setPressure(jsonTemp.getDouble("pressure"));
        wheater.setDescryption(jsonWeather.getString("description"));
        wheater.setWindDegree(jsonWind.getDouble("deg"));
        wheater.setWindSpeed(jsonWind.getDouble("speed"));

        return wheater;

    }



    }


