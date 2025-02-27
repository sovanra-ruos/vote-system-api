package istad.co.votingsystem.feature.poll;

import istad.co.votingsystem.domain.Choice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ChoiceRepository extends JpaRepository<Choice,Long> {

    Choice findChoiceByUuid(String uuid);

    Choice findChoiceByText(String text);

}
