package se.amdev.aktiesnackserverdata.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.UUID;

import javax.persistence.*;

@Entity(name = "Posts")
public class PostData extends EntityModel {

	@Column(nullable = false)
	private String postNumber;
	
	@Column(nullable = false)
	private String postId;

	@Column(nullable = false, length = 10000)
	private String text;

	@ManyToOne
	private UserData user;

	@ManyToOne
	private ThreadData thread;

	@Column(nullable = true)
	private int vote;
	
	@ElementCollection(fetch = FetchType.EAGER)
	private Collection<String> userVotes;

	protected PostData() {
	}

	public PostData(UserData user, ThreadData thread, String text) {
		this.user = user;
		this.thread = thread;
		this.postId = String.valueOf(UUID.randomUUID());
		this.text = text;
		this.postNumber = String.valueOf(thread.getPosts().size() + 1);
		this.vote = 0;
		this.userVotes = new ArrayList<>();
	}

	public String getText() {
		return text;
	}

	public String getPostNumber() {
		return postNumber;
	}
	
	public String getPostId() {
		return postId;
	}

	public UserData getUser() {
		return user;
	}

	public int getVote() {
		return vote;
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

	public void increaseVote() {
		this.vote = vote + 1;
	}

	public void decreaseVote() {
		this.vote = vote - 1;
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

	public Collection<String> getUserVotes() {
		return userVotes;
	}

	public void setUserVotes(String username) {
		this.userVotes.add(username);
	}
}
