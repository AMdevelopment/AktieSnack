package se.amdev.aktiesnackserverweb.service;

import se.amdev.aktiesnackserverdata.model.PostData;
import se.amdev.aktiesnackserverdata.model.ThreadData;
import se.amdev.aktiesnackserverdata.model.UserData;
import se.amdev.aktiesnackserverdata.repository.PostRepository;
import se.amdev.aktiesnackserverdata.repository.StockRepository;
import se.amdev.aktiesnackserverdata.repository.ThreadRepository;
import se.amdev.aktiesnackserverdata.repository.UserRepository;

import static se.amdev.aktiesnackserverweb.Loader.*;

import java.util.Collection;

public class WebService {

    StockRepository stockRepository;
    ThreadRepository threadRepository;
    PostRepository postRepository;
    UserRepository userRepository;

    public WebService() {
        this.stockRepository = getBean(StockRepository.class);
        this.threadRepository = getBean(ThreadRepository.class);
        this.postRepository = getBean(PostRepository.class);
        this.userRepository = getBean(UserRepository.class);
    }

    public UserData create(String username, String email, String firstName, String lastName) {
        return new UserData(username, email, firstName, lastName);
    }

    public ThreadData create(String threadNumber, String description) {
        return new ThreadData(threadNumber, description);
    }

    public PostData create(UserData user, ThreadData thread, String text) {
        return new PostData(user, thread, text);
    }
//
//    public StockData create(String stockName) {
//        return new StockData(stockName);
//    }

    public UserData save(UserData user) {
        return userRepository.save(user);
    }

    public ThreadData save(ThreadData thread) {
        return threadRepository.save(thread);
    }

    public PostData save(PostData post) {
        return postRepository.save(post);
    }

    public UserData findUserByUsername(String username){
        return userRepository.findByUsername(username);
    }

    public ThreadData findThreadByNumber(String threadNumber){
       return threadRepository.findByThreadName(threadNumber);
    }

    public PostData findPostByNumber(String postNumber){
        return postRepository.findByPostNumber(postNumber);
    }
    
    public PostData addPost(PostData postData){
    	ThreadData threadData = findThreadByNumber(postData.getThread().getThreadNumber());
    	UserData userData = findUserByUsername(postData.getUser().getUsername());
    	
    	postData.setUser(userData);
    	postData.setThread(threadData);
    	postData = save(postData);
    	
    	save(threadData.addPost(postData));
    	
    	return postData;
    }
    
    public Collection<PostData> findAllPosts(){
    	return postRepository.findAll();
    }
    
    public Collection<ThreadData> findAllThreads(){
    	return threadRepository.findAll();
    }
    
    public Collection<ThreadData> findLastUpdatedThreads(){
    	return threadRepository.findByLastUpdated();
    }
}
