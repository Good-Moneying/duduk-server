package kusitms.duduk.application.newsletter.persistence.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import kusitms.duduk.domain.global.Category;
import kusitms.duduk.domain.newsletter.vo.Type;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Getter
@Entity
@Table(name = "news_letter")
@Builder(toBuilder = true)
public class NewsLetterJpaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "editor_id")
    private Long editorId;

    @Column(name = "thumbnail")
    private String thumbnail;

    @Column(name = "title")
    private String title;

    @Column(name = "content")
    private String content;

    @Column(name = "keywords")
    private String keywords;

    @Enumerated(EnumType.STRING)
    private Category category;

    @Enumerated(EnumType.STRING)
    private Type type;

    @Column(name = "summary")
    private String summary;

    @Column(name = "view_count")
    private Integer viewCount;

    @Column(name = "scrap_count")
    private Integer scrapCount;
}