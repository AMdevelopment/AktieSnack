package se.amdev.ak_app.data.model;

import java.util.ArrayList;
import java.util.Collection;

public class ThreadWeb {

	private String threadNumber;

	private String description;
	
	private Collection<PostWeb> posts;

	private String numberOfPosts;

	private String creationTime;

	private String lastUpdatedTime;

	private String currency;

	private StockWeb stock;

	protected ThreadWeb() {

	}

	public ThreadWeb(String threadNumber, String description, Collection<PostWeb> posts, String currency, String creationTime, String lastUpdatedTime, String numberOfPosts) {
		this.threadNumber = threadNumber;
		this.description = description;
		this.posts = posts;
		this.numberOfPosts = numberOfPosts;
		this.creationTime = creationTime;
		this.lastUpdatedTime = lastUpdatedTime;
		this.currency = currency;
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

	public void setStock(StockWeb stock) {
		this.stock = stock;
	}

	public StockWeb getStock() {
		return stock;
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

	public String getCurrency() {
		return currency;
	}

	public String getLastUpdatedTime() {
		return lastUpdatedTime;
	}

	public String getNumberOfPosts() {
		return numberOfPosts;
	}
}
