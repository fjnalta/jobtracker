package net.greenbeansit.jobtracker.shared;

import net.greenbeansit.jobtracker.shared.JobID.PayMode;

/**
 * Parses internal and external job numbers as {@code JobNr} objects.
 * 
 * @author Mike Hukiewitz
 */
public class JobIDParser
{

	public static JobID parse(String input)
	{
		// Minimum length to even try
		if (input.length() <= 18)
			throw (new IllegalArgumentException());
		int jobNr, posNr;
		PayMode payMode;
		String clientID = "";
		String desc = "";
		int i = 0;
		int k = 0;

		// get jobNr
		while (input.charAt(i) != '-')
			i++;
		jobNr = Integer.parseInt(input.substring(0, i));
		k = i;
		// get posNr
		while (input.charAt(i) != '-')
			i++;
		posNr = Integer.parseInt(input.substring(k, i));
		k = i;
		// get payMode
		switch (input.substring(i, i + 1))
		{
		case "TM":
			payMode = PayMode.TM;
			break;
		case "FP":
			payMode = PayMode.FP;
			break;
		case "NF":
		default:
			payMode = PayMode.NF;
			break;
		}
		// get clientID
		i += 4;
		while (input.charAt(i) != ' ')
		{
			clientID += input.charAt(i);
			i++;
		}
		// get desc
		desc = input.substring(i + 3);

		return new JobID(jobNr, posNr, payMode, clientID, desc);

	}
}
