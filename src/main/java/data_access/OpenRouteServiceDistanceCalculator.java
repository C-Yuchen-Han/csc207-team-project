package data_access;

import use_case.browse_food.DistanceCalculator;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonArray;

public class OpenRouteServiceDistanceCalculator implements DistanceCalculator {
  private final HttpClient client = HttpClient.newHttpClient();
  private static final String BASE_URL =
      "https://api.openrouteservice.org/v2/directions/foot-walking";


  @Override
  public double calculateDistance(double startLatitude, double startLongitude, double endLatitude, double endLongitude) {
    // create url for request
    String start = startLongitude + "," + startLatitude;
    String end = endLongitude + "," + endLatitude;
    String url = BASE_URL + "?start=" + start + "&end=" + end;

    // create request object:
    String apiKey = System.getenv("ORS_API_KEY");
    if (apiKey == null || apiKey.isBlank()) {
      throw new IllegalStateException("ORS_API_KEY is not configured");
    }

    HttpRequest request = HttpRequest.newBuilder()
        .uri(URI.create(url))
        .header("Authorization", apiKey)
        .GET()
        .build();

    try {
      HttpResponse<String> response = client.send(
          request,
          HttpResponse.BodyHandlers.ofString()
      );

      if (response.statusCode() != 200) {
        throw new IllegalStateException(
            "API request failed: " + response.statusCode()
        );
      }
      String json = response.body();

      // parse the returned response body as Json & return required distance
      JsonObject root = JsonParser.parseString(json).getAsJsonObject();

      JsonArray features = root.getAsJsonArray("features");
      JsonObject firstFeature = features.get(0).getAsJsonObject();
      JsonObject properties = firstFeature.getAsJsonObject("properties");
      JsonObject summary = properties.getAsJsonObject("summary");
      double distance = summary.get("distance").getAsDouble();
      return distance;

    } catch (IOException e) {
      throw new IllegalStateException("Network error", e);
    } catch (InterruptedException e) {
      Thread.currentThread().interrupt();
      throw new IllegalStateException("Request interrupted", e);
    }

  }
}
