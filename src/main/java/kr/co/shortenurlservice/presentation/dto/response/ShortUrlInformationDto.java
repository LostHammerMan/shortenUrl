package kr.co.shortenurlservice.presentation.dto.response;

import kr.co.shortenurlservice.domain.entity.ShortenUrl;
import lombok.Getter;

@Getter
public class ShortUrlInformationDto {

    private String originUrl;
    private String shortenUrlKey;
    private Long redirectCount;

    public ShortUrlInformationDto(ShortenUrl shortenUrl) {
        this.originUrl = shortenUrl.getOriginUrl();
        this.shortenUrlKey = shortenUrl.getShortenUrl();
        this.redirectCount = shortenUrl.getRedirectCount();
    }
}
