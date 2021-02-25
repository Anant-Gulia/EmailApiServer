package email.app.respository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import email.app.entity.EmailOutbox;

@Repository
public interface OutboxRepository extends JpaRepository<EmailOutbox, Integer> {
	
	@Query(value = "SELECT * FROM email_inbox WHERE SENDER_ID = :SENDER_ID", nativeQuery = true)
	public List<EmailOutbox> getEmails(@Param(value = "SENDER_ID") int senderId);
	
	@Modifying
	@Query(value = "INSERT INTO email_outbox(RECIEVER_ID,SENDER_ID,MESSAGE,TIME) VALUES(:RECIEVER_ID,:SENDER_ID,:MESSAGE,:TIME)", nativeQuery = true)
	public void saveEmail(@Param(value = "RECIEVER_ID") int receiverId, @Param(value = "SENDER_ID") int senderId, @Param(value = "MESSAGE") String message, @Param(value = "TIME") LocalDateTime time);
}