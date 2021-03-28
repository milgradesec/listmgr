package listmgr;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;

public class DataFetcher {
    private static HttpClient client = HttpClient.newHttpClient();

    public static String fetch(final String url) {
        final HttpRequest request = HttpRequest.newBuilder().uri(URI.create(url)).build();

        try {
            System.out.println("Loading data from " + "[" + url + "]");

            final HttpResponse<String> response = client.send(request, BodyHandlers.ofString());
            if (response.statusCode() == 200) {
                return response.body().toString();
            }
            System.out.println("error: got status code !=200");

        } catch (final IOException e) {
            e.printStackTrace();
        } catch (final InterruptedException e) {
            e.printStackTrace();
        }
        return "";
    }
}
