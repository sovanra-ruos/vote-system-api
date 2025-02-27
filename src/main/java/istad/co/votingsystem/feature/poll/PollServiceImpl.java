package istad.co.votingsystem.feature.poll;

import istad.co.votingsystem.domain.Choice;
import istad.co.votingsystem.domain.Poll;
import istad.co.votingsystem.feature.poll.dto.PollRequestDto;
import istad.co.votingsystem.feature.poll.dto.PollResponseDto;
import istad.co.votingsystem.utils.CustomPage;
import istad.co.votingsystem.utils.PageCustom;
import istad.co.votingsystem.utils.Validate;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class PollServiceImpl implements PollService{

    private final PollRepository pollRepository;
    private final PollMapper pollMapper;
    private final Validate validate;

    @Override
    public void createPoll(PollRequestDto pollRequestDto) {

        log.info("Creating poll: {}", pollRequestDto);

        // Check if the poll title already exists
        validate.validatePollTile(pollRequestDto.title());

        // Map DTO to entity
        Poll poll = pollMapper.mapFromRequestToEntity(pollRequestDto);
        poll.setUuid(UUID.randomUUID().toString());

        // Convert choices from DTO and set them to poll
        List<Choice> choices = pollRequestDto.choices().stream().map(choiceText -> {
            Choice choice = new Choice();
            choice.setUuid(UUID.randomUUID().toString());
            choice.setText(choiceText);
            choice.setPoll(poll); // Set bidirectional relationship
            return choice;
        }).toList();

        poll.setChoices(choices);

        // Save the poll (choices will be saved due to cascade)
        pollRepository.save(poll);
    }


    @Override
    public CustomPage<PollResponseDto> getAllPolls(int page, int size) {

        Page<Poll> polls = pollRepository.findAll(PageRequest.of(page, size, Sort.by("id").descending()));


        return PageCustom.customPage(polls.map(pollMapper::mapFromEntityToResponse));
    }

    @Override
    public void updatePoll(String uuid, PollRequestDto pollRequestDto) {

        Poll poll = pollRepository.findPollByUuid(uuid);

        poll.setDescription(pollRequestDto.description());
        poll.setTitle(pollRequestDto.title());
        poll.setActive(pollRequestDto.active());

        // Update choices
        List<Choice> existingChoices = poll.getChoices();
        List<String> newChoiceTexts = pollRequestDto.choices();

        // Remove choices that are not in the new list
        existingChoices.removeIf(choice -> !newChoiceTexts.contains(choice.getText()));

        // Add new choices
        for (String choiceText : newChoiceTexts) {
            if (existingChoices.stream().noneMatch(choice -> choice.getText().equals(choiceText))) {
                Choice newChoice = new Choice();
                newChoice.setUuid(UUID.randomUUID().toString());
                newChoice.setText(choiceText);
                newChoice.setPoll(poll);
                existingChoices.add(newChoice);
            }
        }

        // Save the updated poll
        pollRepository.save(poll);
    }

    @Override
    public void enabledPoll(String uuid) {

        Poll poll = pollRepository.findPollByUuid(uuid);

        poll.setActive(true);

        pollRepository.save(poll);

    }

    @Override
    public void disabledPoll(String uuid) {

        Poll poll = pollRepository.findPollByUuid(uuid);

        poll.setActive(false);

        pollRepository.save(poll);

    }

    @Override
    public void deletePoll(String uuid) {

        Poll poll = pollRepository.findPollByUuid(uuid);

        pollRepository.delete(poll);

    }

    @Override
    public PollResponseDto getPollByUuid(String uuid) {

        Poll poll = pollRepository.findPollByUuid(uuid);

        return pollMapper.mapFromEntityToResponse(poll) ;
    }


}
