package istad.co.votingsystem.feature.vote;

import istad.co.votingsystem.feature.vote.dto.VoteRequest;
import istad.co.votingsystem.feature.vote.dto.VoteResponse;

import java.util.List;

public interface VoteService {

    void createVote(VoteRequest voteRequest);


    List<VoteResponse> getAllVoteResults(String uuid);

    Integer countVoteResult(String uuid,String name);

    Integer countVotes (String uuid);

}
