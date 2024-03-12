package kr.co.shortenurlservice.domain.entity;


import lombok.Builder;
import lombok.NoArgsConstructor;

import java.util.Random;

@NoArgsConstructor
public class ShortenUrl {

    private String originUrl;
    private String shortenUrlKey;
    private Long redirectCount;

    public String getOriginUrl() {
        return originUrl;
    }

    public String getShortenUrl() {
        return shortenUrlKey;
    }

    public Long getRedirectCount() {
        return redirectCount;
    }

    // 키 생성 메서드(Base 56 활용)
    public static String generateShortUrlKey(){
        String base56Characters = "23456789abcdefghijkmnpqrstuvwxyzABCDEFGHJKLMNPQRSTUVWXYZ";

        Random random = new Random();
        StringBuilder shortUrlKey = new StringBuilder();

        for (int count = 0; count < 8; count++){
            int index = random.nextInt(0, base56Characters.length());

            char base56Character = base56Characters.charAt(index);

            shortUrlKey.append(base56Character);

        }
        return shortUrlKey.toString();

    }

    // 리다이렉트 시 count 증가
    public void increaseRedirectCount(){
        this.redirectCount = redirectCount + 1;
    }



    @Builder
    public ShortenUrl(String originUrl, String shortenUrl) {
        this.originUrl = originUrl;
        this.shortenUrlKey = shortenUrl;
        this.redirectCount = 0L;
    }
}
