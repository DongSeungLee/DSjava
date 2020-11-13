package com.company;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

public class Main {
    public static void main(String[] args) throws Exception {
        URL url = new URL("https://dv30lu3a9suxj.cloudfront.net/fgweek202008/looks-we-love.json");
        HttpURLConnection httpConnection = (HttpURLConnection) url.openConnection();
        httpConnection.setDoOutput(true);

        httpConnection.setRequestMethod("GET");
        httpConnection.setRequestProperty("Content-Type", "application/json");
        httpConnection.setRequestProperty("Accept", "application/json");
        if(httpConnection.getResponseCode() >299){
            throw new Exception("error");
        }

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(httpConnection.getInputStream()));
        StringBuilder content = new StringBuilder();
        String line;
        while ((line = bufferedReader.readLine()) != null) {
            content.append(line).append("\n");
        }
        bufferedReader.close();
        ObjectMapper mapper = new ObjectMapper();
        List<fgweekData> list = mapper.readValue(content.toString(), mapper.getTypeFactory().constructCollectionType(List.class, fgweekData.class));
        System.out.println(list);
    }
}
