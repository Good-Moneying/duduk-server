package kusitms.duduk.application.user.service;

import kusitms.duduk.core.newsletter.dto.response.NewsLetterThumbnailResponse;
import kusitms.duduk.core.newsletter.port.input.RetrieveNewsLetterQuery;
import kusitms.duduk.core.user.dto.response.RetriveHomeResponse;
import kusitms.duduk.core.user.port.input.RetrieveUserQuery;
import kusitms.duduk.core.user.port.output.LoadUserPort;
import kusitms.duduk.domain.user.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class RetrieveUserCommand implements RetrieveUserQuery {

    private final LoadUserPort loadUserPort;
    private final RetrieveNewsLetterQuery retrieveNewsLetterQuery;

    @Override
    public boolean isUserRegisteredByEmail(String email) {
        return loadUserPort.existsUserByEmail(email);
    }

    @Override
    public RetriveHomeResponse home(String email) {
        // 오늘의 뉴스레터를 가져온다
        User user = loadUserPort.findByEmail(email)
            .orElseThrow(() -> new IllegalArgumentException("해당 유저를 찾을 수 없습니다."));

        // 오늘의 뉴스를 가져온다
        NewsLetterThumbnailResponse todayNewsLetter = retrieveNewsLetterQuery.retriveLatestNewsLetter(
            user);

        return RetriveHomeResponse.builder()
            .todayNewsLetter(todayNewsLetter)
            .build();
    }
}
