package kr.co.shortenurlservice.application.service;

import kr.co.shortenurlservice.domain.entity.ShortenUrl;
import kr.co.shortenurlservice.domain.exception.LackOfShortUrlKeyException;
import kr.co.shortenurlservice.domain.exception.NotFoundShortenUrlException;
import kr.co.shortenurlservice.domain.repositoryinterface.ShortUrlRepository;
import kr.co.shortenurlservice.presentation.dto.request.ShortUrlCreateRequestDto;
import kr.co.shortenurlservice.presentation.dto.response.ShortUrlCreateResponseDto;
import kr.co.shortenurlservice.presentation.dto.response.ShortUrlInformationDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class SimpleShortenUrlService {

    private final ShortUrlRepository shortUrlRepository;
    
    // 단축 url 생성
    public ShortUrlCreateResponseDto generateShortenUrl(ShortUrlCreateRequestDto requestDto){
        
        // 기능
        // 1. 단축 url 키 생성
        String originUrl = requestDto.getOriginUrl();
        String shortUrlKey = getUniqueShortenUrlKey();

        // 2. 원래의 url 과 단축 url 키를 통해 ShortenUrl 도메인 객체 생성
        ShortenUrl shortenUrl = new ShortenUrl(originUrl, shortUrlKey);

        // 3. 생성된 ShortenUrl 을 repository 를 통해 저장
        shortUrlRepository.urlSave(shortenUrl);
        // 4. ShortenUrl 을 dto 로 변환하여 반환
        ShortUrlCreateResponseDto responseDto = new ShortUrlCreateResponseDto(shortenUrl);

        return responseDto;
    }

    public ShortUrlInformationDto getShortenUrlInformationByShortenUrlKey(String key){
        log.info("service - getShortenUrlInformationByShortenUrlKey called....");


        ShortenUrl findShorterUrlKey = shortUrlRepository.findByShorterUrlKey(key);
        if (findShorterUrlKey == null){
            throw new NotFoundShortenUrlException();
        }


        ShortUrlInformationDto shortUrlInformationDto = new ShortUrlInformationDto(findShorterUrlKey);
        return shortUrlInformationDto;
    }

    //
    public String getOriginalUrlByShortenUrlKey(String shortenUrlKey){

        ShortenUrl shortenUrl = shortUrlRepository.findByShorterUrlKey(shortenUrlKey);

        if (shortenUrl == null){
            throw new NotFoundShortenUrlException();
        }

        shortenUrl.increaseRedirectCount();
        shortUrlRepository.urlSave(shortenUrl);

        String originalUrl = shortenUrl.getOriginUrl();

        return originalUrl;
    }

    //
    private String getUniqueShortenUrlKey(){

        final int MAX_RETRY_COUNT = 5;
        int count = 0;

        while (count++ < MAX_RETRY_COUNT){
            String shortUrlKey = ShortenUrl.generateShortUrlKey();
            ShortenUrl shortenUrl = shortUrlRepository.findByShorterUrlKey(shortUrlKey);

            if (shortenUrl == null){
                return shortUrlKey;
            }
        }

        throw new LackOfShortUrlKeyException();
    }
}
