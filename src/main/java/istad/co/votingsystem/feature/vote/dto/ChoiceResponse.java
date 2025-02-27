package istad.co.votingsystem.feature.vote.dto;

import lombok.Builder;

@Builder
public record ChoiceResponse(
        String uuid,
        String text
) {
}
