package email.app.respository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import email.app.entity.EmailInbox;

@Repository
public interface InboxRepository extends JpaRepository<EmailInbox, Integer> {
	
	@Query(value = "SELECT * FROM email_inbox WHERE RECIEVER_ID = :RECIEVER_ID", nativeQuery = true)
	public List<EmailInbox> getEmails(@Param(value = "RECIEVER_ID") int receiverId);
	
	@Modifying
	@Query(value = "INSERT INTO email_inbox(RECIEVER_ID,SENDER_ID,MESSAGE,TIME) VALUES(:RECIEVER_ID,:SENDER_ID,:MESSAGE,:TIME)", nativeQuery = true)
	public void saveEmail(@Param(value = "RECIEVER_ID") int receiverId, @Param(value = "SENDER_ID") int senderId, @Param(value = "MESSAGE") String message, @Param(value = "TIME") LocalDateTime time);
}