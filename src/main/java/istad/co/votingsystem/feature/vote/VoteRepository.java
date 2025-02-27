package istad.co.votingsystem.feature.vote;

import istad.co.votingsystem.domain.Vote;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface VoteRepository extends JpaRepository<Vote, Long> {

    Boolean existsByIpAddress (String ipAddress);

    List<Vote> findAllByPoll_Uuid (String uuid);

    Integer countByPoll_UuidAndSelectedChoice_Text (String uuid, String text);

    Integer countByPoll_Uuid (String uuid);
}
