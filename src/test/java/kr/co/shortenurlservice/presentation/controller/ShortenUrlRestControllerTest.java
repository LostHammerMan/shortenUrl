package kr.co.shortenurlservice.presentation.controller;

import kr.co.shortenurlservice.application.service.SimpleShortenUrlService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.junit.jupiter.api.Assertions.*;

@WebMvcTest(controllers = ShortenUrlRestController.class)
class ShortenUrlRestControllerTest {

    @MockBean
    private SimpleShortenUrlService simpleShortenUrlService;

    @Autowired
    private MockMvc mockMvc;

    @Test
    @DisplayName("원래의 URL 로 리다이렉트 되어야 함")
    void redirectTest() throws Exception {
        String expectedOriginalUrl = "https://www.google.co.kr";

        Mockito.when(simpleShortenUrlService.getOriginalUrlByShortenUrlKey(Mockito.any())).thenReturn(expectedOriginalUrl);

        mockMvc.perform(MockMvcRequestBuilders.get("/shorten-url/any-key"))
                .andExpect(MockMvcResultMatchers.status().isMovedPermanently())
                .andExpect(MockMvcResultMatchers.header().string("Location", expectedOriginalUrl));
    }
}