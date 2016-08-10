package se.amdev.aktiesnackserverweb.model;

import se.amdev.aktiesnackserverdata.model.PostData;
import static se.amdev.aktiesnackserverweb.parser.ModelParser.*;

import java.util.Date;

public class PostWeb {

	private String postNumber;

	private String text;

	private UserWeb user;
	
	private ThreadWeb thread;
	
	private Date creationTime;

	private Date lastUpdatedTime;

	protected PostWeb() {
	}

	public PostWeb(String text) {
//		this.user = user;
//		this.thread = thread;
//		this.postNumber = postNumber;
		this.text = text;
	}
	
	public PostWeb(PostData postData){
		this.user = parseUser(postData.getUser());
//		this.thread = parseThread(postData.getThread());
		this.postNumber = postData.getPostNumber();
		this.text = postData.getText();
		this.creationTime = postData.getCreationTime();
		this.lastUpdatedTime = postData.getLastUpdatedTime();
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
		return creationTime.toString();
	}
	
	public String getLastUpdatedTime() {
		return lastUpdatedTime.toString();
	}
}
