package kr.co.shortenurlservice.domain.repositoryinterface;

import kr.co.shortenurlservice.domain.entity.ShortenUrl;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface ShortUrlRepository {

   void urlSave(ShortenUrl shortenUrl);
   ShortenUrl findByShorterUrlKey(String key);
}
