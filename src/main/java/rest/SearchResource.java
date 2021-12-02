package rest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import dtos.BookSearchResultsDTO;
import facades.SearchFacade;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.io.IOException;

@Path("/search")
public class SearchResource {

    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();
    private static final SearchFacade searchFacade = SearchFacade.getSearchFacade();

    @GET
    @Produces("text/plain")
    public String hello() {
        return "Welcome to the search engine.";
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("{search}")
    public String searchBasic(@PathParam("search") @Encoded String search) throws IOException {
        /* TODO: Maybe add pagination parameters to the endpoint. */
        int limit = 25;
        BookSearchResultsDTO result = searchFacade.getBookSearchResult(search, limit);
        return GSON.toJson(result);
    }
}