package email.app.respository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import email.app.entity.EmailCredentials;

@Repository
@Transactional
public interface EmailCredentialsRepository extends JpaRepository<EmailCredentials, Integer> {

	@Query(value = "SELECT * FROM EMAIL_CREDENTIALS WHERE EMAIL = :EMAIL", nativeQuery = true)
	public EmailCredentials getCredentialsUsingEmail(@Param(value = "EMAIL") String email);

	@Modifying
	@Query(value = "UPDATE EMAIL_CREDENTIALS SET APIKEY = :APIKEY WHERE EMAIL_CREDENTIALS_ID = :EMAIL_CREDENTIALS_ID", nativeQuery = true)
	public void saveApiKey(@Param(value = "APIKEY") String apikey, @Param(value = "EMAIL_CREDENTIALS_ID") int emailCredentialsId);

	@Modifying
	@Query(value = "UPDATE EMAIL_CREDENTIALS SET APIKEY = NULL WHERE EMAIL_CREDENTIALS_ID = :EMAIL_CREDENTIALS_ID", nativeQuery = true)
	public void deleteApiKey(@Param(value = "EMAIL_CREDENTIALS_ID") int emailCredentialsId);
}