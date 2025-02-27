package istad.co.votingsystem.utils;

import istad.co.votingsystem.feature.poll.PollRepository;
import istad.co.votingsystem.feature.vote.VoteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class Validate {

    private final PollRepository pollRepository;
    private final VoteRepository voteRepository;

    public void validatePollTile(String title) {
        if(pollRepository.existsByTitle(title)){
            throw new IllegalArgumentException("Poll with title: " + title + " already exist");
        }
    }

    public void validateVote (String ipAddress){
        if(voteRepository.existsByIpAddress(ipAddress)){
            throw new IllegalArgumentException("You have vote this already");
        }

    }


}
