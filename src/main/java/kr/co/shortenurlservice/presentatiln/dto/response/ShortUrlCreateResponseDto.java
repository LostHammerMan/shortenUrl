package kr.co.shortenurlservice.presentatiln.dto.response;

import kr.co.shortenurlservice.domain.entity.ShortenUrl;
import lombok.Builder;
import lombok.Getter;

@Getter
public class ShortUrlCreateResponseDto {

    private String originUrl;
    private String shortenUrlKey;

    @Builder
    public ShortUrlCreateResponseDto(ShortenUrl shortenUrl) {
        this.originUrl = shortenUrl.getOriginUrl();
        this.shortenUrlKey = shortenUrl.getShortenUrl();
    }
}
