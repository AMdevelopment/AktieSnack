package se.amdev.aktiesnackserverweb.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import se.amdev.aktiesnackserverdata.model.ThreadData;
import static se.amdev.aktiesnackserverweb.parser.ModelParser.*;

public class ThreadWeb {

	private String threadNumber;

	private String description;

	private Collection<PostWeb> posts;
	
	private Date creationTime;

	private Date lastUpdatedTime;

	protected ThreadWeb() {

	}

	public ThreadWeb(String threadNumber, String description) {
		this.threadNumber = threadNumber;
		this.description = description;
		this.posts = new ArrayList<>();
	}

	public ThreadWeb(ThreadData threadData) {
		this.threadNumber = threadData.getThreadNumber();
		this.description = threadData.getDescription();
		this.posts = parseCollectionPost(threadData.getPosts());
		this.creationTime = threadData.getCreationTime();
		this.lastUpdatedTime = threadData.getLastUpdatedTime();
	}

	public String getThreadNumber() {
		return threadNumber;
	}

	public void setThreadNumber(String threadNumber) {
		this.threadNumber = threadNumber;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Collection<PostWeb> getPosts() {
		return posts;
	}

	public void setPosts(PostWeb postWeb) {
		posts.add(postWeb);
	}
	
	public String getCreationTime() {
		return creationTime.toString();
	}
	
	public String getLastUpdatedTime() {
		return lastUpdatedTime.toString();
	}
}
