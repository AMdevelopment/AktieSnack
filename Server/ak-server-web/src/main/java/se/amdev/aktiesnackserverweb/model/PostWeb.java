package se.amdev.aktiesnackserverweb.model;

import se.amdev.aktiesnackserverdata.model.PostData;
import static se.amdev.aktiesnackserverweb.parser.ModelParser.*;

import java.util.Date;
import java.util.UUID;

public class PostWeb {

	private String postNumber;
	
	private String postId;

	private String text;

	private UserWeb user;
	
	private ThreadWeb thread;
	
	private Date creationTime;

	private Date lastUpdatedTime;
	
	private int vote;

	protected PostWeb() {
	}

	public PostWeb(String text) {
		this.text = text;
		this.vote = 0;
	}
	
	public PostWeb(PostData postData){
		this.user = parseUser(postData.getUser());
		this.postNumber = postData.getPostNumber();
		this.postId = postData.getPostId();
		this.text = postData.getText();
		this.creationTime = postData.getCreationTime();
		this.lastUpdatedTime = postData.getLastUpdatedTime();
		this.vote = postData.getVote();
	}
	
	public int getVote() {
		return vote;
	}

	public String getPostNumber() {
		return postNumber;
	}
	
	public String getPostId() {
		return postId;
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
		return creationTime.toString();
	}
	
	public String getLastUpdatedTime() {
		return lastUpdatedTime.toString();
	}
}
