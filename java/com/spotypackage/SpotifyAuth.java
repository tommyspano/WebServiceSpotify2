

package com.spotypackage;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.client.HttpClient;
import org.apache.http.HttpResponse;

import java.util.Base64;

public class SpotifyAuth {

    private static final String CLIENT_ID = "e4e21225e22640baaab0a60b2b02b9a3";
    private static final String CLIENT_SECRET = "4895080488f447c88ae803d222155aee";
    private static final String TOKEN_URL = "https://accounts.spotify.com/api/token";

    public static String getAccessToken() {
        try {
            HttpClient client = HttpClients.createDefault();
            HttpPost post = new HttpPost(TOKEN_URL);

            String auth = CLIENT_ID + ":" + CLIENT_SECRET;
            String encodedAuth = Base64.getEncoder().encodeToString(auth.getBytes());

            post.setHeader("Authorization", "Basic " + encodedAuth);
            post.setHeader("Content-Type", "application/x-www-form-urlencoded");
            post.setEntity(new StringEntity("grant_type=client_credentials"));

            HttpResponse response = client.execute(post);

            ObjectMapper mapper = new ObjectMapper();
            JsonNode node = mapper.readTree(response.getEntity().getContent());

            return node.get("access_token").asText();

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
