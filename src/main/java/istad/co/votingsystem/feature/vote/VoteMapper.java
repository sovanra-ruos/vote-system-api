package istad.co.votingsystem.feature.vote;

import istad.co.votingsystem.domain.Choice;
import istad.co.votingsystem.domain.Vote;
import istad.co.votingsystem.feature.vote.dto.ChoiceResponse;
import istad.co.votingsystem.feature.vote.dto.VoteRequest;
import istad.co.votingsystem.feature.vote.dto.VoteResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;


@Mapper(componentModel = "spring")
public interface VoteMapper {

    @Mapping(source = "selectedChoice", target = "selectedChoice", qualifiedByName = "mapChoiceToResponse")
    VoteResponse mapEntityToResponse(Vote vote);

    Vote mapRequestToEntity(VoteRequest voteRequest);

    @Named("mapChoiceToResponse")
    default ChoiceResponse mapChoiceToResponse(Choice choice) {
        if (choice == null) {
            return null;
        }
        return ChoiceResponse.builder()
                .uuid(choice.getUuid())
                .text(choice.getText())
                .build();
    }
}
