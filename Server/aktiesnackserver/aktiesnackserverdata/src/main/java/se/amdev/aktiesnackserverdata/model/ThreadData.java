package se.amdev.aktiesnackserverdata.model;

import javax.persistence.*;

import java.util.ArrayList;
import java.util.Collection;

@Entity(name = "Threads")
public final class ThreadData extends EntityModel {

	@Column(nullable = false, unique = true)
	private String threadName;

	@Column(nullable = false)
	private String description;
	
	@OneToMany(fetch = FetchType.EAGER, mappedBy = "thread")
	Collection<PostData> posts;
	
	private int numberOfPosts;

	protected ThreadData() {
		
	}

	public ThreadData(String threadNumber, String description) {
		this.threadName = threadNumber;
		this.description = description;
		this.posts = new ArrayList<>();
	}

	public String getDescription() {
		return description;
	}

	public String getThreadNumber() {
		return threadName;
	}

	@Override
	public int hashCode() {
		int result = 17;
		result += 17 * threadName.hashCode();
		result += 17 * description.hashCode();

		return result;
	}

	@Override
	public boolean equals(Object object) {
		if (object == this) {
			return true;
		}

		if (object instanceof PostData) {
			ThreadData otherObject = (ThreadData) object;

			return this.threadName.equals(otherObject.threadName) &&
					this.description.equals(otherObject.description);
		}
		return false;
	}

	public ThreadData setDescription(String description) {
		this.description = description;
		return this;
	}

	public Collection<PostData> getPosts() {
		return posts;
	}

	public ThreadData addPost(PostData postData) {
		posts.add(postData);
		this.numberOfPosts = numberOfPosts + 1;
		return this;
	}

	public void setThreadNumber(String threadNumber) {
		this.threadName = threadNumber;
	}
}