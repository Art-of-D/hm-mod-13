import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;

public class HttpComm {
    private static HttpClient CLIENT = HttpClient.newHttpClient();
    public static List<UserCommFilter> getList(String uri) throws Exception {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(uri))
                .GET()
                .build();

        HttpResponse<String> response = CLIENT.send(request, HttpResponse.BodyHandlers.ofString());
        List <UserCommFilter> list = new Gson().fromJson(response.body(), new TypeToken<List<UserCommFilter>>(){}.getType());
        return list;
    }

}
