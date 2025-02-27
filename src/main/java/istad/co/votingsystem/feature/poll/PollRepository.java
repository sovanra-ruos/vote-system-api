package istad.co.votingsystem.feature.poll;

import istad.co.votingsystem.domain.Poll;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PollRepository extends JpaRepository<Poll,Long> {

    boolean existsByTitle (String title);

    Poll findPollByUuid (String uuid);



}
