package net.greenbeansit.jobtracker.server.data.transaction;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.greenbeansit.jobtracker.shared.Transaction;

/**
 * Implements {@link TransactionDataService}
 * 
 * @author Mike Hukiewitz
 *
 */
@Service("transactionService")
public class TransactionServiceJpa implements TransactionDataService
{

	@Autowired
	private TransactionEntityRepository repository;

	@Override
	@SuppressWarnings("deprecation")
	public List<Integer> getJobMonthView(Integer jobNr, Integer posNr,
			Integer year, Integer month)
	{
		List<Integer> values = new ArrayList<Integer>();
		for (Integer i = 0; i < 32; i++)
			values.add(0);
		for (TransactionEntity entity : repository.findByJobAndMonth(jobNr,
				posNr, year, month))
		{
			Integer index = entity.getDate().getDate();
			values.set(index, values.get(index) + entity.getUsedBudget());
		}
		return stackValues(values);
	}

	@Override
	@SuppressWarnings("deprecation")
	public List<Integer> getJobYearView(Integer jobNr, Integer posNr,
			Integer year)
	{
		List<Integer> values = new ArrayList<Integer>();
		for (Integer i = 0; i < 13; i++)
			values.add(0);
		for (TransactionEntity entity : repository.findByJobAndYear(jobNr,
				posNr, year))
		{
			Integer index = entity.getDate().getMonth() + 1;
			values.set(index, values.get(index) + entity.getUsedBudget());
		}
		return stackValues(values);
	}

	@Override
	public boolean save(Transaction transaction)
	{
		return repository.save(convert(transaction)) != null;
	}

	@Override
	public void delete(Integer transactionId)
	{
		repository.delete(transactionId);
	}

	/**
	 * Transforms an array of budget used per day/month into an array of total
	 * budget used for each day/month.
	 * 
	 * @param values
	 *            the array to stack
	 * @return the stacked array
	 */
	private List<Integer> stackValues(List<Integer> values)
	{
		if (values.size() < 2)
			return values;
		return stackValues(values, 1);
	}

	/**
	 * Transforms an array of budget used per day/month into an array of total
	 * budget used for each day/month beginning at given index.
	 * 
	 * @param values
	 *            the array to stack
	 * @param index
	 *            index at which to start stacking
	 * @return the stacked array
	 */
	private List<Integer> stackValues(List<Integer> values, Integer index)
	{
		values.set(index, values.get(index - 1) + values.get(index));
		if (values.size() > index + 1)
			return stackValues(values, index + 1);
		else
			return values;
	}

	/**
	 * Converts a shared transaction object into a database entity.
	 * 
	 * @param transaction
	 *            transaction to be converted
	 * @return database entity
	 */
	private TransactionEntity convert(Transaction transaction)
	{
		if (transaction == null)
			return null;
		return new TransactionEntity(transaction.getAuthor(),
				transaction.getDate(), transaction.getJobNo(),
				transaction.getPosNo(), transaction.getUsedBudget());
	}

	/**
	 * Converts a database entity into a transaction object.
	 * 
	 * @param entity
	 *            entity to be converted
	 * @return transaction object
	 */
	private Transaction convert(TransactionEntity entity)
	{
		if (entity == null)
			return null;
		return new Transaction(entity.getAuthor(), entity.getDate(),
				entity.getJobNo(), entity.getPosNo(), entity.getUsedBudget());
	}
}