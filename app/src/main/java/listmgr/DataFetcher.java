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
            final HttpResponse<String> response = client.send(request, BodyHandlers.ofString());
            if (response.statusCode() == 200) {
                return response.body().toString();
            }
            System.out.printf("error: failed to fetch [%s]: got status code != 200: %d\n", url, response.statusCode());

        } catch (final IOException e) {
            System.out.printf("error: failed to fetch [%s]: %s\n", url, e.toString());
        } catch (final InterruptedException e) {
            System.out.printf("error: failed to fetch [%s]: %s\n", url, e.toString());
        }
        return "";
    }
}
