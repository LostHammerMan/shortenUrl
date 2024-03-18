package kr.co.shortenurlservice.presentation.controller;

import jakarta.validation.Valid;
import kr.co.shortenurlservice.application.service.SimpleShortenUrlService;
import kr.co.shortenurlservice.presentation.dto.request.ShortUrlCreateRequestDto;
import kr.co.shortenurlservice.presentation.dto.response.ShortUrlInformationDto;
import kr.co.shortenurlservice.presentation.dto.response.ShortUrlCreateResponseDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;

@RestController
@Slf4j
@RequestMapping("/shorten-url")
@RequiredArgsConstructor
public class ShortenUrlRestController {

    private final SimpleShortenUrlService simpleShortenUrlService;

    // 단축 url 생성 api
    @PostMapping("/generate")
    public ResponseEntity<ShortUrlCreateResponseDto> createShortUrl(@RequestBody @Valid ShortUrlCreateRequestDto request){

        ShortUrlCreateResponseDto response = simpleShortenUrlService.generateShortenUrl(request);
//        return ResponseEntity.ok().body(null);
        return ResponseEntity.ok(response);
    }

    // 단축 url 리다이렉트 apu
    @GetMapping("/{shortenUrlKey}")
    public ResponseEntity<?> redirectShortUrl(@PathVariable(name = "shortenUrlKey") String shortenUrlKey) throws URISyntaxException {

        String originalUrl = simpleShortenUrlService.getOriginalUrlByShortenUrlKey(shortenUrlKey);

        URI redirectUri = new URI(originalUrl);
        HttpHeaders headers = new HttpHeaders();

        headers.setLocation(redirectUri);

        return new ResponseEntity<>(headers, HttpStatus.PERMANENT_REDIRECT);
    }

    // 단축 url 정보 조회 api
    @GetMapping("/check/{shortenUrlKey}")
    public ResponseEntity<ShortUrlInformationDto> getShortenUrlInformation(@PathVariable(name = "shortenUrlKey") String shortenUrlKey){
        log.info("controller - getShortenUrlInformation called...");
        ShortUrlInformationDto shortUrlInformationDto =
                simpleShortenUrlService.getShortenUrlInformationByShortenUrlKey(shortenUrlKey);
        log.info("\t shortUrlInformationDto = {}", shortUrlInformationDto);
        return ResponseEntity.ok().body(shortUrlInformationDto);
    }

}
