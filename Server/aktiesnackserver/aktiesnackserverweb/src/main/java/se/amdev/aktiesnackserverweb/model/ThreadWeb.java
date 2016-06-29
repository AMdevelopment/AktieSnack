package se.amdev.aktiesnackserverweb.model;

import java.util.Date;

import se.amdev.aktiesnackserverdata.model.ThreadData;

public class ThreadWeb {

	private String threadName;

	private String description;

	private String numberOfPosts;

	private String currency;
//
	private Date lastUpdatedTime;
	
	private Date creationTime;

	protected ThreadWeb() {

	}

	public ThreadWeb(String threadName, String description, String currency) {
		this.threadName = threadName;
		this.description = description;
		this.currency = currency;
	}

	public ThreadWeb(ThreadData threadData) {
		this.threadName = threadData.getThreadName();
		this.description = threadData.getDescription();
		this.currency = threadData.getCurrency();
		this.numberOfPosts = String.valueOf(threadData.getPosts().size());
		this.creationTime = threadData.getCreationTime();
		this.lastUpdatedTime = threadData.getLastUpdatedTime();
	}

	public String getThreadName() {
		return threadName;
	}

	public void setThreadName(String threadName) {
		this.threadName = threadName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getNumberOfPosts() {
		return numberOfPosts;
	}

	public void setNumberOfPosts(String numberOfPosts) {
		this.numberOfPosts = numberOfPosts;
	}
	
	public String getCurrency() {
		return currency;
	}
	
	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public String getCreationTime() {
		return creationTime.toString();
	}

	public String getLastUpdatedTime() {
		return lastUpdatedTime.toString();
	}
}
