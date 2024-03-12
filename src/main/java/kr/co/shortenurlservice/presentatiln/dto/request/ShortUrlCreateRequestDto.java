package kr.co.shortenurlservice.presentatiln.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import org.hibernate.validator.constraints.URL;

@Getter
public class ShortUrlCreateRequestDto {

    @NotNull
    @URL(regexp = "https?:\\/\\/(www\\.)?[-a-zA-Z0-9@:%._\\+~#=]{2,256}\\.[a-z]{2,6}\\b([-a-zA-Z0-9@:%_\\+.~#()?&//=]*)")
    private String originUrl;

}
