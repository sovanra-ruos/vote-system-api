package istad.co.votingsystem.feature.poll.dto;

import lombok.Builder;

import java.time.LocalDateTime;
import java.util.List;

@Builder
public record PollRequestDto(
        String title,
        String description,
        Boolean multipleChoice,
        Boolean isPrivate,
        LocalDateTime startDate,
        LocalDateTime endDate,
        Boolean active,
        List<String> choices
) {
}