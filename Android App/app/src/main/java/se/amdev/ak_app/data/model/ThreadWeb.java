package se.amdev.ak_app.data.model;

import java.util.ArrayList;
import java.util.Collection;

public class ThreadWeb {

	private String threadNumber;

	private String description;
	
	private Collection<PostWeb> posts;

	private String creationTime;

	private String lastUpdatedTime;

	protected ThreadWeb() {

	}

	public ThreadWeb(String threadNumber, String description, Collection<PostWeb> posts, String creationTime, String lastUpdatedTime) {
		this.threadNumber = threadNumber;
		this.description = description;
		this.posts = posts;
		this.creationTime = creationTime;
		this.lastUpdatedTime = lastUpdatedTime;
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
		return creationTime;
	}

	public String getLastUpdatedTime() {
		return lastUpdatedTime;
	}
}
