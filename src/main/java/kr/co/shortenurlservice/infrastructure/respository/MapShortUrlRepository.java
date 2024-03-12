package kr.co.shortenurlservice.infrastructure.respository;

import kr.co.shortenurlservice.domain.entity.ShortenUrl;
import kr.co.shortenurlservice.domain.repositoryinterface.ShortUrlRepository;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

@Repository
public class MapShortUrlRepository implements ShortUrlRepository {

    private Map<String, ShortenUrl> shortenUrls = new HashMap<>();

    // shortedUrlKey 사용해 단축 Url 찾음
    @Override
    public void urlSave(ShortenUrl shortenUrl) {
        shortenUrls.put(shortenUrl.getShortenUrl(), shortenUrl);
    }

    @Override
    public ShortenUrl findByShorterUrlKey(String key) {
        ShortenUrl findShortenUrl = shortenUrls.get(key);

        return findShortenUrl;
    }
}
