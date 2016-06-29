package se.amdev.aktiesnackserverdata.model;

import javax.persistence.*;

@Entity(name = "Posts")
public class PostData extends EntityModel {

	@Column(nullable = false)
	private String postNumber;

	@Column(nullable = false, length = 10000)
	private String text;

	@ManyToOne
	private UserData user;

	@ManyToOne
	private ThreadData thread;

	protected PostData() {
	}

	public PostData(UserData user, ThreadData thread, String text) {
		this.user = user;
		this.thread = thread;
		this.text = text;
		this.postNumber = String.valueOf(thread.getPosts().size() + 1);
	}

	public String getText() {
		return text;
	}

	public String getPostNumber() {
		return postNumber;
	}

	public UserData getUser() {
		return user;
	}

	public PostData setUser(UserData user) {
		this.user = user;
		return this;
	}

	public PostData setThread(ThreadData threadData) {
		this.thread = threadData;
		this.postNumber = String.valueOf(thread.getPosts().size() + 1);
		return this;
	}

	public ThreadData getThread() {
		return thread;
	}
	
	public PostData setText(String text) {
		this.text = text;
		return this;
	}

	@Override
	public int hashCode() {
		int result = 17;
		result += 17 * postNumber.hashCode();
		result += 17 * text.hashCode();

		return result;
	}

	@Override
	public boolean equals(Object object) {
		if (object == this) {
			return true;
		}

		if (object instanceof PostData) {
			PostData otherObject = (PostData) object;

			return this.text.equals(otherObject.text) &&
					this.postNumber.equals(otherObject.postNumber);
		}
		return false;
	}
}
