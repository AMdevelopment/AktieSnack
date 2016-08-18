package se.amdev.aktiesnackserverweb.service;

import se.amdev.aktiesnackserverdata.model.PostData;
import se.amdev.aktiesnackserverdata.model.InquiryData;
import se.amdev.aktiesnackserverdata.model.StockData;
import se.amdev.aktiesnackserverdata.model.ThreadData;
import se.amdev.aktiesnackserverdata.model.UserData;
import se.amdev.aktiesnackserverdata.repository.PostRepository;
import se.amdev.aktiesnackserverdata.repository.InquiryRepository;
import se.amdev.aktiesnackserverdata.repository.StockRepository;
import se.amdev.aktiesnackserverdata.repository.ThreadRepository;
import se.amdev.aktiesnackserverdata.repository.UserRepository;

import static se.amdev.aktiesnackserverweb.Loader.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;

public class WebService {

	StockRepository stockRepository;
	ThreadRepository threadRepository;
	PostRepository postRepository;
	UserRepository userRepository;
	InquiryRepository inquiryRepository;
	static Collection<String> badWords;
	
	public WebService() {
		this.stockRepository = getBean(StockRepository.class);
		this.threadRepository = getBean(ThreadRepository.class);
		this.postRepository = getBean(PostRepository.class);
		this.userRepository = getBean(UserRepository.class);
		this.inquiryRepository = getBean(InquiryRepository.class);
		badWords = new ArrayList<>();
	}

	public UserData create(String username, String email, String firstName, String lastName, String password) {
		return new UserData(username, email, firstName, lastName, password);
	}

	public ThreadData create(String threadNumber, String description, String currency) {
		return new ThreadData(threadNumber, description, currency);
	}

	public PostData create(UserData user, ThreadData thread, String text) {
		return new PostData(user, thread, text);
	}

	public UserData save(UserData user) {
		return userRepository.save(user);
	}

	public ThreadData save(ThreadData thread) {
		return threadRepository.save(thread);
	}

	public PostData save(PostData post) {
		return postRepository.save(post);
	}

	public InquiryData registerEmail(InquiryData email) {
		return inquiryRepository.save(email);
	}

	public void saveThreads(Collection<ThreadData> threads) {
		for (ThreadData t : threads) {
			threadRepository.save(t);
		}
	}

	public HashMap<String, StockData> saveStocks(Collection<StockData> stocks) {
		HashMap<String, StockData> returnStocks = new HashMap<>();
		for (StockData s : stocks) {
			returnStocks.put(s.getStockName(), stockRepository.save(s));
		}
		return returnStocks;
	}

	public UserData findUserByUsername(String username) {
		return userRepository.findByUsername(username);
	}

	public StockData findStockByStockName(String stockName) {
		return stockRepository.findByStockName(stockName);
	}

	public ThreadData findThreadByName(String threadName) {
		return threadRepository.findByThreadName(threadName);
	}

	public PostData findPostByNumber(String postNumber) {
		return postRepository.findByPostNumber(postNumber);
	}
	
	public PostData findPostByPostId(String postId) {
		return postRepository.findByPostId(postId);
	}

	public PostData addPost(PostData postData) {
		postData = violationCheck(postData);
		postData = save(postData);
		save(postData.getThread().addPost(postData));

		return postData;
	}

	public Collection<PostData> findAllPosts() {
		return postRepository.findAll();
	}

	public Collection<UserData> findAllUsers() {
		return userRepository.findAll();
	}

	public Collection<StockData> findAllStocks() {
		return stockRepository.findAll();
	}

	public Collection<PostData> findAllPostsByThreadId(Long threadName) {
		return postRepository.findPostByThread(threadName);
	}

	public Collection<ThreadData> findAllThreads() {
		return threadRepository.findAll();
	}

	public Collection<ThreadData> findLastUpdatedThreads() {
		return threadRepository.findByLastUpdated();
	}

	public Collection<InquiryData> findAllInquirys() {
		return inquiryRepository.findAll();
	}
	
	public InquiryData findInquiryByEmail(String email){
		return inquiryRepository.findByEmail(email);
	}
	
	public void unsubscribe(String email){
		InquiryData inquiry = findInquiryByEmail(email);
		inquiry.setActive(0);
		registerEmail(inquiry);
	}

	public PostData violationCheck(PostData postData) {
		String text = postData.getText();
		StringBuilder replace = new StringBuilder();

		badWords.add("fitta");
		badWords.add("kuk");
		badWords.add("neger");
		badWords.add("hora");
		badWords.add("bög");

		for (String s : badWords) {
			if (text.contains(s)) {
				replace.append(s.substring(0, 1));
				for (int i = 0; i < s.length() - 2; i++) {
					replace.append("*");
				}
				replace.append(s.substring(s.length()-1, s.length()));
				text = text.replace(s, replace.toString());
				replace = new StringBuilder();
			}
		}

		return postData.setText(text);
	}
}
