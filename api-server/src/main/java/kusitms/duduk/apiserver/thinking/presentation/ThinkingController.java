package kusitms.duduk.apiserver.thinking.presentation;

import kusitms.duduk.apiserver.security.infrastructure.CustomUserDetails;
import kusitms.duduk.core.openai.dto.request.OpenAiSummaryCommentRequest;
import kusitms.duduk.core.openai.port.output.OpenAiClientPort;
import kusitms.duduk.core.thinking.dto.request.CreateThinkingCloudRequest;
import kusitms.duduk.core.thinking.dto.response.RetrieveThinkingDetailResponse;
import kusitms.duduk.core.thinking.dto.response.RetrieveThinkingHomeResponse;
import kusitms.duduk.core.thinking.port.input.CreateThinkingUseCase;
import kusitms.duduk.core.thinking.port.input.RetrieveThinkingQuery;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/thinkings")
public class ThinkingController implements ThinkingControllerDocs {

    private final RetrieveThinkingQuery retrieveThinkingQuery;
    private final CreateThinkingUseCase createThinkingUseCase;
    private final OpenAiClientPort<OpenAiSummaryCommentRequest, String> openAiClientPort;

    @GetMapping("/home")
    public ResponseEntity<RetrieveThinkingHomeResponse> retrieveThinkingHome(
        @AuthenticationPrincipal CustomUserDetails customUserDetails
    ) {
        return ResponseEntity.ok(
            retrieveThinkingQuery.retrieveThinkingHome(customUserDetails.getEmail()));
    }

    @GetMapping("/{thinkingId}")
    public ResponseEntity<RetrieveThinkingDetailResponse> retrieveThinkingDetail(
        @PathVariable(name = "thinkingId") final Long thinkingId) {
        return new ResponseEntity<>(retrieveThinkingQuery.retrieveThinkingDetail(thinkingId),
            HttpStatus.OK);
    }

    @PostMapping("/summary")
    public ResponseEntity<String> summaryThinking(
        @RequestBody final OpenAiSummaryCommentRequest request
    ) {
        String summary = openAiClientPort.chat(request);
        log.info("summaryThinking() summary: {}\n", summary);
        return ResponseEntity.ok(summary);
    }

    @PostMapping("/{thinkingId}")
    public ResponseEntity<Void> createThinkingCloud(
        @PathVariable(name = "thinkingId") final Long thinkingId,
        @RequestBody final CreateThinkingCloudRequest request
    ) {
        createThinkingUseCase.createThinkingCloud(thinkingId, request);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
