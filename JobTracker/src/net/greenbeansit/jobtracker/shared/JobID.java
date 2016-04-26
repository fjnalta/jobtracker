package net.greenbeansit.jobtracker.shared;

/**
 * Result of parsing the job number string. A job number is read-only
 * accessible. "jobNr-posNr-payMode | clientID | desc"
 * 
 * @author Mike Hukiewitz
 */
public class JobID {

	enum PayMode {
		TM, FP, NF
	};

	PayMode payMode;
	int jobNr, posNr;
	String clientID;
	String desc;

	public JobID(int jobNr, int posNr, PayMode payMode, String clientID, String desc) {
		this.jobNr = jobNr;
		this.posNr = posNr;
		this.payMode = payMode;
		this.clientID = clientID;
		this.desc = desc;
	}

	public String getDesc() {
		return desc;
	}

	public int getJobNr() {
		return jobNr;
	}

	public int getPosNr() {
		return posNr;
	}

	public PayMode getPayMode() {
		return payMode;
	}

	public String getClientID() {
		return clientID;
	}

}