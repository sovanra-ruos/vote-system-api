package istad.co.votingsystem.feature.vote;

import istad.co.votingsystem.domain.Choice;
import istad.co.votingsystem.domain.Poll;
import istad.co.votingsystem.domain.Vote;
import istad.co.votingsystem.feature.poll.ChoiceRepository;
import istad.co.votingsystem.feature.poll.PollRepository;
import istad.co.votingsystem.feature.vote.dto.VoteRequest;
import istad.co.votingsystem.feature.vote.dto.VoteResponse;
import istad.co.votingsystem.utils.Validate;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class VoteServiceImpl implements VoteService{

    private final VoteRepository voteRepository;
    private final VoteMapper voteMapper;
    private final HttpServletRequest request;
    private final PollRepository pollRepository;
    private final ChoiceRepository choiceRepository;
    private final Validate validate;

    @Override
    public void createVote(VoteRequest voteRequest) {

        String ip = request.getRemoteAddr();

        Poll poll = pollRepository.findPollByUuid(voteRequest.pollUuid());

        Vote vote = voteMapper.mapRequestToEntity(voteRequest);

        Choice choice = choiceRepository.findChoiceByText(voteRequest.selectedChoiceName());

        vote.setPoll(poll);

        vote.setUuid(UUID.randomUUID().toString());

        vote.setSelectedChoice(choice);

        vote.setIpAddress(ip);

        voteRepository.save(vote);

    }

    @Override
    public List<VoteResponse> getAllVoteResults(String uuid) {

        List<Vote> votes = voteRepository.findAllByPoll_Uuid(uuid);

        return votes.stream()
                .map(voteMapper::mapEntityToResponse)
                .collect(Collectors.toList());
    }

    @Override
    public Integer countVoteResult(String uuid, String name) {
        var vote = voteRepository.countByPoll_UuidAndSelectedChoice_Text(uuid, name);
        return vote;
    }

    @Override
    public Integer countVotes(String uuid) {
        return voteRepository.countByPoll_Uuid(uuid);
    }
}
