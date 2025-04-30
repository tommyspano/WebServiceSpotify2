/*package com.spotypackage;

public class Main {

    public static void main(String[] args) {
        // Esegui il web service (incluso Jetti o simili)
    }
}
*/
package com.spotypackage;

import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Scrivi il titolo di una canzone:");/*da vedere di fare una ricerca più complessa*/
        String userInput = scanner.nextLine();

        SpotifyClient client = new SpotifyClient();
        List<Song> songs = client.search(userInput);

        if (songs != null && !songs.isEmpty()) {
            for (Song song : songs) {
                System.out.println(song);  // Mostra la canzone: nome canzone - artista
            }
        } else {
            System.out.println("Nessun risultato trovato.");
        }
    }
}

