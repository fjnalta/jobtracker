package net.greenbeansit.jobtracker.server.data.transaction;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

/**
 * Repository for handling {@link TransactionEntity} and making requests to the
 * database.
 * 
 * @author Mike Hukiewitz
 *
 */
public interface TransactionEntityRepository
		extends CrudRepository<TransactionEntity, Integer>
{

	/**
	 * Deletes the given entity.
	 * 
	 * @param id
	 *            entity to delete
	 */
	@Modifying
	@Query("delete from TransactionEntity where id = ?1")
	void delete(Integer id);

	/**
	 * Saves an {@link TransactionEntity} to the database.
	 * 
	 * @param persisted
	 *            {@link TransactionEntity} to save
	 * @return the persisted {@link TransactionEntity}
	 */
	@Override
	@SuppressWarnings("unchecked")
	TransactionEntity save(TransactionEntity persisted);

	/**
	 * Returns all {@link TransactionEntity} from a single user in a single
	 * month.
	 * 
	 * @param author
	 *            ID of the author
	 * @param year
	 *            the year
	 * @param month
	 *            the month
	 * @return List of corresponding {@link TransactionEntity}
	 */
	@Query("select x from TransactionEntity x where x.author = :author and YEAR(x.date) = :year and MONTH(x.date) = :month")
	List<TransactionEntity> findByAuthorAndMonth(
			@Param("author") Integer author, @Param("year") Integer year,
			@Param("month") Integer month);

	/**
	 * Returns all {@link TransactionEntity} assigned to given job in a given
	 * month.
	 * 
	 * @param jobNo
	 *            3 to 6 digits
	 * @param posNo
	 *            up to 3 digits
	 * @param year
	 *            the year
	 * @param month
	 *            the month
	 * @return List of corresponding {@link TransactionEntity}
	 */
	@Query("select x from TransactionEntity x where x.job_no = :jobno and x.pos_no = :posno and YEAR(x.date) = :year and MONTH(x.date) = :month")
	List<TransactionEntity> findByJobAndMonth(@Param("jobno") Integer jobNo,
			@Param("posno") Integer posNo, @Param("year") Integer year,
			@Param("month") Integer month);

	/**
	 * Returns all {@link TransactionEntity} from a single user in a single
	 * year.
	 * 
	 * @param author
	 *            ID of the author
	 * @param year
	 *            the year
	 * @return List of corresponding {@link TransactionEntity}
	 */
	@Query("select x from TransactionEntity x where x.author = :author and YEAR(x.date) = :year")
	List<TransactionEntity> findByAuthorAndYear(@Param("author") Integer author,
			@Param("year") Integer year);

	/**
	 * Returns all {@link TransactionEntity} assigned to given job in a given
	 * year.
	 * 
	 * @param jobNo
	 *            3 to 6 digits
	 * @param posNo
	 *            up to 3 digits
	 * @param year
	 *            the year
	 * @return List of corresponding {@link TransactionEntity}
	 */
	@Query("select x from TransactionEntity x where x.job_no = :jobno and x.pos_no = :posno and YEAR(x.date) = :year")
	List<TransactionEntity> findByJobAndYear(@Param("jobno") Integer jobNo,
			@Param("posno") Integer posNo, @Param("year") Integer year);
}
