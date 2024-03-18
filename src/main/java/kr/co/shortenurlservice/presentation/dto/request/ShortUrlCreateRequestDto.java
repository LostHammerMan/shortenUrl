package kr.co.shortenurlservice.presentation.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.URL;

@Getter
@NoArgsConstructor
public class ShortUrlCreateRequestDto {

    @NotNull
    @URL(regexp = "https?:\\/\\/(www\\.)?[-a-zA-Z0-9@:%._\\+~#=]{2,256}\\.[a-z]{2,6}\\b([-a-zA-Z0-9@:%_\\+.~#()?&//=]*)")
    private String originUrl;

    @Builder
    public ShortUrlCreateRequestDto(String originUrl) {
        this.originUrl = originUrl;
    }
}
