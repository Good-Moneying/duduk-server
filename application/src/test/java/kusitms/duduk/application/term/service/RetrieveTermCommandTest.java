package kusitms.duduk.application.term.service;

import static org.junit.jupiter.api.Assertions.*;

import kusitms.duduk.core.term.dto.TermDtoMapper;
import kusitms.duduk.core.term.dto.request.CreateTermRequest;
import kusitms.duduk.core.term.dto.request.RetrieveTermRequest;
import kusitms.duduk.core.term.dto.response.RetrieveTermResponse;
import kusitms.duduk.core.term.port.input.RetrieveTermQuery;
import kusitms.duduk.core.term.port.output.SaveTermPort;
import kusitms.duduk.domain.term.Term;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@DisplayName("RetrieveTermCommand 테스트")
class RetrieveTermCommandTest {

    @Autowired
    private SaveTermPort saveTermPort;

    @Autowired
    private TermDtoMapper termDtoMapper;

    @Autowired
    private RetrieveTermQuery retrieveTermQuery;

    @Test
    void 단어를_조회한다() {
        // given
        Term term = TermSteps.단어_생성();
        Term savedTerm = saveTermPort.save(term);

        // when
        RetrieveTermResponse response = retrieveTermQuery.retrieve(
            new RetrieveTermRequest(savedTerm.getId().getValue()));

        // then
        assertEquals(savedTerm.getId().getValue(), response.termId());
        assertEquals(savedTerm.getKoreanName().getValue(), response.koreanName());
        assertEquals(savedTerm.getEnglishName().getValue(), response.englishName());
        assertEquals(savedTerm.getDescription().getValue(), response.description());
        assertEquals(savedTerm.getTermCategory().name(), response.category());
    }
}