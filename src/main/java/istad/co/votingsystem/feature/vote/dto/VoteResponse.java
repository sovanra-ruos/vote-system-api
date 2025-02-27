package istad.co.votingsystem.feature.vote.dto;

public record VoteResponse(
        String uuid,
        ChoiceResponse selectedChoice // Changed from List<ChoiceResponse> to ChoiceResponse
) {
}
