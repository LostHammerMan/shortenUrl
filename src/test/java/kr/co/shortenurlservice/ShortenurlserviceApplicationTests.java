package kr.co.shortenurlservice;

import kr.co.shortenurlservice.application.service.SimpleShortenUrlService;
import kr.co.shortenurlservice.presentation.dto.request.ShortUrlCreateRequestDto;
import kr.co.shortenurlservice.presentation.dto.response.ShortUrlCreateResponseDto;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ShortenUrlServiceApplicationTests {

    @Autowired
    SimpleShortenUrlService service;

    @Test
    @DisplayName("URL 을 단축한 후 단축된 URL 키로 조회하면 원래 URL 조회되어야 함")
    void shortenUrlAddTest(){


        String expectedOriginalUrl = "https://www.google.com";
        ShortUrlCreateRequestDto request = ShortUrlCreateRequestDto.builder()
                .originUrl(expectedOriginalUrl).build();

        ShortUrlCreateResponseDto response = service.generateShortenUrl(request);

        String shortenUrlKey = response.getShortenUrlKey();

        String originalUrl = service.getOriginalUrlByShortenUrlKey(shortenUrlKey);

        Assertions.assertTrue(originalUrl.equals(expectedOriginalUrl));
    }

}
