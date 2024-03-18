package kr.co.shortenurlservice.application.service;

import kr.co.shortenurlservice.domain.entity.ShortenUrl;
import kr.co.shortenurlservice.domain.exception.LackOfShortUrlKeyException;
import kr.co.shortenurlservice.domain.repositoryinterface.ShortUrlRepository;
import kr.co.shortenurlservice.presentation.dto.request.ShortUrlCreateRequestDto;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class SimpleShortenUrlServiceUnitTest {

    @Mock
    private ShortUrlRepository shortUrlRepository;

    @InjectMocks
    private SimpleShortenUrlService simpleShortenUrlService;

    @Test
    @DisplayName("단축 URL 이 중복되면 LackOfShortenUrlKeyException 발생")
    void throwLackOfShortenUrlKeyExceptionTest(){

        ShortUrlCreateRequestDto request = ShortUrlCreateRequestDto.builder()
                .originUrl(null).build();

        Mockito.when(shortUrlRepository.findByShorterUrlKey(Mockito.any())).thenReturn(new ShortenUrl(null, null));

        Assertions.assertThrows(LackOfShortUrlKeyException.class, () ->{
            simpleShortenUrlService.generateShortenUrl(request);
        });

    }

}