import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;
import java.util.List;

class HttpFunction{
    private static HttpClient CLIENT = HttpClient.newHttpClient();
    private static final Gson GSON = new Gson();

    public static void get(String uri) throws IOException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(uri))
                .GET()
                .build();

        HttpResponse<String> response = CLIENT.send(request, HttpResponse.BodyHandlers.ofString());
        System.out.println(response.body());
    }
    public static List<UserJsonFilter> getList(String uri) throws Exception {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(uri))
                .GET()
                .build();

        HttpResponse<String> response = CLIENT.send(request, HttpResponse.BodyHandlers.ofString());
        return GSON.fromJson(response.body(), new TypeToken<List<UserJsonFilter>>(){}.getType());
    }

    public static int post(String uri, UserJsonFilter newUser) throws Exception {
        String gsonStr = GSON.toJson(newUser);
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(uri))
                .header("Content-type", "application/json")
                .timeout(Duration.ofSeconds(30))
                .POST(HttpRequest.BodyPublishers.ofString(gsonStr))
                .build();
        HttpResponse response = CLIENT.send(request, HttpResponse.BodyHandlers.ofString());
        return response.statusCode();

    }
    public static int delete(String uri, int byId) throws Exception {
        //final String gsonStr = GSON.toJson(String.format("%s?id=%d", uri, list.get(byID).getId()));
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(String.format("%s/%d", uri, byId)))
                .header("Content-type", "application/json")
                .DELETE()
                .build();
        //.method( "DELETE", HttpRequest.BodyPublishers.ofString(gsonStr))
        HttpResponse response = CLIENT.send(request, HttpResponse.BodyHandlers.ofString());
        return response.statusCode();
    }
    public static int put(String uri, UserJsonFilter userPut, int byId) throws Exception {
        String gsonStr = GSON.toJson(userPut);
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(String.format("%s/%d", uri, byId)))
                .header("Content-type", "application/json")
                .timeout(Duration.ofSeconds(30))
                .PUT(HttpRequest.BodyPublishers.ofString(gsonStr))
                .build();
        HttpResponse response = CLIENT.send(request, HttpResponse.BodyHandlers.ofString());
        return response.statusCode();
    }


}