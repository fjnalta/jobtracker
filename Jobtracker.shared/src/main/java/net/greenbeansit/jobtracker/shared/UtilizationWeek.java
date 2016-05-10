package net.greenbeansit.jobtracker.shared;

import java.io.Serializable;
import java.sql.Date;

public class UtilizationWeek implements Serializable
{
	/**
	 * 
	 */
	private static final long	serialVersionUID	= 2259434726922682948L;

	private Integer				id;
	private Integer				author;
	private Date				begin;
	private Integer				daysFree;
	private Integer				daysWork;
	private Integer				daysHoliday;
	private Integer				possibilty;
	private Integer				pseudoJobId;

	public UtilizationWeek()
	{

	}
}
