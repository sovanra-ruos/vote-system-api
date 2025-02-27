package istad.co.votingsystem.feature.poll;

import istad.co.votingsystem.feature.poll.dto.PollRequestDto;
import istad.co.votingsystem.feature.poll.dto.PollResponseDto;
import istad.co.votingsystem.utils.CustomPage;

public interface PollService {

    void createPoll(PollRequestDto pollRequestDto);

    CustomPage<PollResponseDto> getAllPolls(int page, int size);

    void updatePoll(String uuid,PollRequestDto pollRequestDto);

    void enabledPoll(String uuid);

    void disabledPoll(String uuid);

    void deletePoll(String uuid);

    PollResponseDto getPollByUuid (String uuid);



}