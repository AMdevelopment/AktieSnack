package se.amdev.ak_app.data.model;

public class PostWeb {

	private String postNumber;

	private String text;

	private UserWeb user;
	
	private ThreadWeb thread;

	private String creationTime;

	private String lastUpdatedTime;

	protected PostWeb() {
	}

	public PostWeb(UserWeb user, ThreadWeb thread, String postNumber, String text, String creationTime, String lastUpdatedTime) {
		this.user = user;
		this.thread = thread;
		this.postNumber = postNumber;
		this.text = text;
		this.creationTime = creationTime;
		this.lastUpdatedTime = lastUpdatedTime;
	}

	public String getPostNumber() {
		return postNumber;
	}

	public void setPostNumber(String postNumber) {
		this.postNumber = postNumber;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public UserWeb getUser() {
		return user;
	}

	public void setUser(UserWeb user) {
		this.user = user;
	}

	public ThreadWeb getThread() {
		return thread;
	}

	public void setThread(ThreadWeb thread) {
		this.thread = thread;
	}

	public String getCreationTime() {
		return creationTime;
	}

	public String getLastUpdatedTime() {
		return lastUpdatedTime;
	}
}
