package com.spotypackage;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.client.HttpClient;
import org.apache.http.HttpResponse;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.List;

public class SpotifyClient {

    private static final String SPOTIFY_API_URL = "https://api.spotify.com/v1/search?q={query}&type=track";

    // Funzione per determinare il tipo di ricerca e costruire la query corretta
    public List<Song> search(String query) {
        try {
            String formattedQuery = formatQuery(query);

            // Codifica la query per rimuovere caratteri non validi
            String encodedQuery = URLEncoder.encode(formattedQuery, StandardCharsets.UTF_8.toString());

            // Costruisci la richiesta con la query codificata
            HttpClient client = HttpClients.createDefault();
            HttpGet request = new HttpGet(SPOTIFY_API_URL.replace("{query}", encodedQuery));
            request.setHeader("Authorization", "Bearer " + SpotifyAuth.getAccessToken());

            // Esegui la richiesta
            HttpResponse response = client.execute(request);

            // Deserializza la risposta
            ObjectMapper mapper = new ObjectMapper();
            SpotifyApiResponse apiResponse = mapper.readValue(response.getEntity().getContent(), SpotifyApiResponse.class);

            return apiResponse.getTracks().getItems();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    // Funzione per formattare correttamente la query
    private String formatQuery(String query) {
        // Se il termine contiene "genere", cerchiamo per genere
        if (query.toLowerCase().contains("genere")) {
            String genre = query.split("genere")[1].trim();
            return "genre:" + genre;
        }
        // Se il termine contiene "anno", cerchiamo per anno
        else if (query.toLowerCase().contains("anno")) {
            String year = query.split("anno")[1].trim();
            return "year:" + year;
        }
        // Altrimenti, trattiamo la query come titolo di canzone
        else {
            return "track:" + query;
        }
    }
}
