package se.amdev.aktiesnackserverdata.model;

import javax.persistence.*;

import java.util.ArrayList;
import java.util.Collection;

@Entity(name = "Threads")
public final class ThreadData extends EntityModel {

	@Column(nullable = false)
	private String threadName;

	@Column(nullable = false, unique = true)
	private String description;

	@Column(nullable = false)
	private String currency;
	
	@OneToOne(fetch = FetchType.EAGER)
	private StockData stock;
	
	@OneToMany(fetch = FetchType.EAGER, mappedBy = "thread")
	Collection<PostData> posts;
	
	private int numberOfPosts;

	protected ThreadData() {
		
	}

	public ThreadData(String threadNumber, String description, String currency) {
		this.threadName = threadNumber;
		this.description = description;
		this.currency = currency;
		this.posts = new ArrayList<>();
	}

	public String getDescription() {
		return description;
	}

	public String getThreadName() {
		return threadName;
	}
	
	public StockData getStock() {
		return stock;
	}
	
	public String getCurrency() {
		return currency;
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
	
	public ThreadData addStock(StockData stockData){
		this.stock = stockData;
		return this;
	}

	public void setThreadName(String threadName) {
		this.threadName = threadName;
	}
}