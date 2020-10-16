package br.com.farmaciasocialapi.service;

import java.io.IOException;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class MapQuestService {

    @Value("${geoLocation.key}")
    private String apiKey;
    private final String geoLocationUrl = "https://www.mapquestapi.com/geocoding/v1/reverse?key=API_KEY&location=LATITUDE,LONGITUDE&outFormat=json&thumbMaps=false";

    public String getGeoLocationData(String latitude, String longitude) {
        
        String url = geoLocationUrl.replace("API_KEY", apiKey)
            .replace("LATITUDE", latitude)
            .replace("LONGITUDE", longitude);

        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpGet httpGet = new HttpGet(url);
        
        String responseBody = null;

        // Create a custom response handler
        ResponseHandler<String> responseHandler = new ResponseHandler<String>() {

            @Override
            public String handleResponse(
                    final HttpResponse response) throws ClientProtocolException, IOException {
                int status = response.getStatusLine().getStatusCode();
                if (status >= 200 && status < 300) {
                    HttpEntity entity = response.getEntity();
                    return entity != null ? EntityUtils.toString(entity) : null;
                } else {
                    throw new ClientProtocolException("Unexpected response status: " + status);
                }
            }

        };
        try {
            responseBody = httpClient.execute(httpGet, responseHandler);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return responseBody;
    }
}
