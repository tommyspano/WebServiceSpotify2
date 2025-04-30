package com.spotypackage;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/search")
public class SpotifyService {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response searchSongs(@QueryParam("query") String query) {
        SpotifyClient client = new SpotifyClient();
        List<Song> songs = client.search(query);
        return Response.ok(songs).build();
    }
}
