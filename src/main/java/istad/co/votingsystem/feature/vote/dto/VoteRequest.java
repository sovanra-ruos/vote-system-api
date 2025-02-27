package istad.co.votingsystem.feature.vote.dto;

public record VoteRequest(
        String pollUuid,
        String selectedChoiceName
) {
}
