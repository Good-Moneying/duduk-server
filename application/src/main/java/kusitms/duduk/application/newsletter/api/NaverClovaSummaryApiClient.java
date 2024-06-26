package kusitms.duduk.application.newsletter.api;

import io.jsonwebtoken.lang.Assert;
import jakarta.annotation.PostConstruct;
import kusitms.duduk.core.newsletter.dto.request.NaverClovaSummaryRequest;
import kusitms.duduk.core.newsletter.dto.response.NaverClovaSummaryResponse;
import kusitms.duduk.core.newsletter.port.output.NaverClovaClientPort;
import kusitms.duduk.domain.newsletter.NewsLetter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;

@Slf4j
@RequiredArgsConstructor
@Component
public class NaverClovaSummaryApiClient implements
    NaverClovaClientPort<NewsLetter, NaverClovaSummaryResponse> {

    @Value("${naver.client-id}")
    private String NAVER_CLIENT_ID;

    @Value("${naver.client-secret}")
    private String NAVER_CLIENT_SECRET;

    @Value("${naver.base-url}")
    private String NAVER_CLOVA_BASE_URL;

    private WebClient webClient;

    @PostConstruct
    private void init() {
        this.webClient = WebClient.builder()
            .baseUrl(NAVER_CLOVA_BASE_URL)
            .defaultHeader("Content-Type", MediaType.APPLICATION_JSON_VALUE)
            .defaultHeader("X-NCP-APIGW-API-KEY-ID", NAVER_CLIENT_ID)
            .defaultHeader("X-NCP-APIGW-API-KEY", NAVER_CLIENT_SECRET)
            .build();
    }

    public NaverClovaSummaryResponse execute(NewsLetter newsLetter) {
        log.debug("Naver Clova Summarize NewsLetter Start : {}", newsLetter.getTitle());

        Assert.notNull(newsLetter.getTitle(), "제목은 null 일 수 없습니다.");
        Assert.notNull(newsLetter.getContent(), "내용은 null 일 수 없습니다.");

        NaverClovaSummaryRequest request = NaverClovaSummaryRequest.of(
            newsLetter.getTitle().getTitle(), newsLetter.getContent().getContent());

        return webClient.post()
            .uri("/text-summary/v1/summarize")
            .body(BodyInserters.fromValue(request))
            .retrieve()
            .bodyToMono(NaverClovaSummaryResponse.class)
            .block();
    }
}


