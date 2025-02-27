package istad.co.votingsystem.feature.poll;

import istad.co.votingsystem.domain.Choice;
import istad.co.votingsystem.domain.Poll;
import istad.co.votingsystem.feature.poll.dto.PollRequestDto;
import istad.co.votingsystem.feature.poll.dto.PollResponseDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface PollMapper {

    @Mapping(target = "choices", source = "poll.choices", qualifiedByName = "mapChoicesToResponse")
    PollResponseDto mapFromEntityToResponse(Poll poll);

    @Mapping(target = "choices", source = "pollRequestDto.choices", qualifiedByName = "mapChoicesToEntity")
    Poll mapFromRequestToEntity(PollRequestDto pollRequestDto);

    @Named("mapChoicesToResponse")
    default List<String> mapChoicesToResponse(List<Choice> choices) {
        return choices.stream()
                .map(Choice::getText) // Assuming Choice has a getText() method
                .collect(Collectors.toList());
    }

    @Named("mapChoicesToEntity")
    default List<Choice> mapChoicesToEntity(List<String> choices) {
        return choices.stream()
                .map(choiceText -> {
                    Choice choice = new Choice();
                    choice.setText(choiceText); // Assuming Choice has a setText() method
                    return choice;
                })
                .collect(Collectors.toList());
    }
}