import java.util.HashMap;

public class UrlMapping {
    private final HashMap<String, String> shortToLong = new HashMap<>();
    private final HashMap<String, String> longToShort = new HashMap<>();

    public boolean containsLongUrl(String longUrl) {
        return longToShort.containsKey(longUrl);
    }

    public boolean containsShortUrl(String shortUrl) {
        return shortToLong.containsKey(shortUrl);
    }

    public String getShortUrl(String longUrl) {
        return longToShort.get(longUrl);
    }

    public String getLongUrl(String shortUrl) {
        return shortToLong.get(shortUrl);
    }

    public void addMapping(String longUrl, String shortUrl) {
        longToShort.put(longUrl, shortUrl);
        shortToLong.put(shortUrl, longUrl);
    }
}