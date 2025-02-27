package istad.co.votingsystem.feature.vote;

import istad.co.votingsystem.feature.vote.dto.VoteRequest;
import istad.co.votingsystem.feature.vote.dto.VoteResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/vote")
@RequiredArgsConstructor
public class VoteController {

    private final VoteService voteService;

    @PostMapping("/create-vote")
    public ResponseEntity<String > createVote(@Valid @RequestBody VoteRequest voteRequest){

        voteService.createVote(voteRequest);

        return ResponseEntity.ok("You have voted successful");
    }

    @GetMapping("/get-all/{uuid}")
    public ResponseEntity<List<VoteResponse>> getAllVotesByPollUuid(@PathVariable String uuid) {
        List<VoteResponse> voteResponses = voteService.getAllVoteResults(uuid);
        return ResponseEntity.ok(voteResponses);
    }

    @GetMapping("/count/{uuid}/{name}")
    public ResponseEntity<Integer> countVote(@PathVariable String uuid, @PathVariable String name){
        return ResponseEntity.ok(voteService.countVoteResult(uuid,name));
    }

    @GetMapping("/count/{uuid}")
    public ResponseEntity<Integer> countVotes(@PathVariable String uuid){
        return ResponseEntity.ok(voteService.countVotes(uuid));
    }


}
