public class LinkShortener {
    private static final String DOMAIN = "https://short.ly/";
    private final UrlMapping urlMapping = new UrlMapping();

    public String shortenUrl(String longUrl) {
        if (urlMapping.containsLongUrl(longUrl)) {
            return DOMAIN + urlMapping.getShortUrl(longUrl);
        }

        String shortKey;
        do {
            shortKey = ShortUrlGenerator.generate();
        } while (urlMapping.containsShortUrl(shortKey));

        urlMapping.addMapping(longUrl, shortKey);
        return DOMAIN + shortKey;
    }

    public String expandUrl(String shortUrl) {
        if (!shortUrl.startsWith(DOMAIN)) {
            return "Invalid short URL!";
        }

        String shortKey = shortUrl.substring(DOMAIN.length());

        if (!urlMapping.containsShortUrl(shortKey)) {
            return "Short URL not found!";
        }

        return urlMapping.getLongUrl(shortKey);
    }
}