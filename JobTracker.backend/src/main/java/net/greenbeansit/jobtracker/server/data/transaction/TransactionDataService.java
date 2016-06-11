package net.greenbeansit.jobtracker.server.data.transaction;

import java.util.List;

import net.greenbeansit.jobtracker.shared.Transaction;

/**
 * Database interface for {@link Transaction}s.
 * 
 * @author Mike Hukiewitz
 *
 */
public interface TransactionDataService
{
	/**
	 * Retrieves a list of used budget values for each day of the given month
	 * for a given job.
	 * 
	 * @param jobNr
	 *            3 to 6 digits
	 * @param posNr
	 *            3 digits
	 * @param year
	 *            the year
	 * @param month
	 *            the month
	 * @return List of Integer values
	 */
	List<Integer> getJobMonthView(Integer jobNr, Integer posNr, Integer year,
			Integer month);

	/**
	 * Retrieves a list of used budget values for each month of a given year.
	 * 
	 * @param jobNr
	 *            3 to 6 digits
	 * @param posNr
	 *            3 digits
	 * @param year
	 *            the year
	 * @return List of Integer values
	 */
	List<Integer> getJobYearView(Integer jobNr, Integer posNr, Integer year);

	/**
	 * Saves a transaction to the database.
	 * 
	 * @param transaction
	 *            transaction to be saved
	 * @return true if successful
	 */
	boolean save(Transaction transaction);

	/**
	 * Deletes a transaction from the database.
	 * 
	 * @param transactionId
	 *            transaction to be deleted
	 */
	void delete(Integer transactionId);
}
