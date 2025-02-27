package istad.co.votingsystem.feature.poll;

import istad.co.votingsystem.feature.poll.dto.PollRequestDto;
import istad.co.votingsystem.feature.poll.dto.PollResponseDto;
import istad.co.votingsystem.utils.CustomPage;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/poll")
@RequiredArgsConstructor
public class PollController {

    private final PollService pollService;

    @GetMapping
    public ResponseEntity<CustomPage<PollResponseDto>> getAllPolls(@RequestParam(defaultValue = "0") int page,
                                                                   @RequestParam(defaultValue = "10") int size){

        CustomPage<PollResponseDto> pollPageResponse = pollService.getAllPolls(page,size);

        return ResponseEntity.ok(pollPageResponse);
    }

    @GetMapping("/{uuid}")
    public ResponseEntity<PollResponseDto> getPollByUuid(@PathVariable String uuid){
        return ResponseEntity.ok(pollService.getPollByUuid(uuid));
    }

    @PostMapping("/create-poll")
    public ResponseEntity<String> createPoll(@Valid @RequestBody PollRequestDto pollRequestDto){

        pollService.createPoll(pollRequestDto);

        return ResponseEntity.ok("Poll has been created Successful");

    }

    @PutMapping("/poll/{uuid}")
    public ResponseEntity<String> updatePoll(@PathVariable String uuid, @RequestBody PollRequestDto pollRequestDto){

        pollService.updatePoll(uuid,pollRequestDto);

        return ResponseEntity.ok("Poll has been updated Successful");
    }

    @PatchMapping("/enable/{uuid}")
    public ResponseEntity<String> enablePoll(@PathVariable String uuid){

        pollService.enabledPoll(uuid);

        return ResponseEntity.ok("Poll has been enabled Successful");
    }

    @PatchMapping("/disable/{uuid}")
    public ResponseEntity<String> disablePoll(@PathVariable String uuid){

        pollService.disabledPoll(uuid);

        return ResponseEntity.ok("Poll has been disable Successful");
    }

    @DeleteMapping("/delete/{uuid}")
    public ResponseEntity<String> deletePoll(@PathVariable String uuid){
        pollService.deletePoll(uuid);

        return ResponseEntity.ok("Poll has been Deleted Successful");
    }

}
