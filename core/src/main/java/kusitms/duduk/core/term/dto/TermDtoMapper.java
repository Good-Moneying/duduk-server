package kusitms.duduk.core.term.dto;

import kusitms.duduk.common.annotation.Mapper;
import kusitms.duduk.core.term.dto.request.CreateTermRequest;
import kusitms.duduk.core.term.dto.response.RetrieveTermResponse;
import kusitms.duduk.domain.global.Category;
import kusitms.duduk.domain.term.Term;
import kusitms.duduk.domain.term.vo.Description;
import kusitms.duduk.domain.term.vo.Name;
import kusitms.duduk.domain.term.vo.TermCategory;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Mapper
public class TermDtoMapper {

    public Term toDomain(CreateTermRequest request) {
        return Term.builder()
            .englishName(Name.from(request.englishName()))
            .koreanName(Name.from(request.koreanName()))
            .description(Description.from(request.description()))
            .category(Category.from(request.category()))
            .build();
    }

    public RetrieveTermResponse toDto(Term term) {
        return RetrieveTermResponse.builder()
            .termId(term.getId().getValue())
            .englishName(term.getEnglishName().getValue())
            .koreanName(term.getKoreanName().getValue())
            .description(term.getDescription().getValue())
            .category(term.getCategory().name())
            .build();
    }

    public RetrieveTermResponse toResponse(Term term, boolean isScrapped) {
        log.info("term category : {} ", term.getCategory());
        return RetrieveTermResponse.builder()
            .termId(term.getId().getValue())
            .englishName(term.getEnglishName().getValue())
            .koreanName(term.getKoreanName().getValue())
            .description(term.getDescription().getValue())
            .category(term.getCategory().getDescription())
            .isScrapped(isScrapped)
            .build();
    }
}
