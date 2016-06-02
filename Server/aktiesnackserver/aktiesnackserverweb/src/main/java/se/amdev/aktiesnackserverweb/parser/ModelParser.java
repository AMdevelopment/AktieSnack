package se.amdev.aktiesnackserverweb.parser;

import java.util.ArrayList;
import java.util.Collection;

import se.amdev.aktiesnackserverdata.model.PostData;
import se.amdev.aktiesnackserverdata.model.ThreadData;
import se.amdev.aktiesnackserverdata.model.UserData;
import se.amdev.aktiesnackserverweb.model.PostWeb;
import se.amdev.aktiesnackserverweb.model.ThreadWeb;
import se.amdev.aktiesnackserverweb.model.UserWeb;

/**
 * Created by Martin on 26/05/16.
 */
public class ModelParser {

	public static UserData parseUser(UserWeb userWeb) {
		if (userWeb == null) {
			return null;
		}
		return new UserData(userWeb.getUsername(), userWeb.getEmail(), userWeb.getFirstName(), userWeb.getLastName());
	}

	public static UserWeb parseUser(UserData userData) {
		if (userData == null) {
			return null;
		}
		return new UserWeb(userData.getUsername(), userData.getEmail(), userData.getFirstName(), userData.getLastName());
	}

	public static ThreadWeb parseThread(ThreadData threadData) {
		if (threadData == null) {
			return null;
		}

		return new ThreadWeb(threadData.getThreadNumber(), threadData.getDescription());
	}

	public static ThreadData parseThread(ThreadWeb threadWeb) {
		if (threadWeb == null) {
			return null;
		}

		return new ThreadData(threadWeb.getThreadNumber(), threadWeb.getDescription());
	}

	public static Collection<ThreadWeb> parseCollectionThread(Collection<ThreadData> threads) {
		if(threads.isEmpty())
		{
			return null;
		}
		Collection<ThreadWeb> threadsToReturn = new ArrayList<>();
		threads.forEach(t -> threadsToReturn.add(new ThreadWeb(t)));
		
		return threadsToReturn;
	}

	public static Collection<PostWeb> parseCollectionPost(Collection<PostData> posts) {
		if(posts.isEmpty())
		{
			return null;
		}
		Collection<PostWeb> postsToReturn = new ArrayList<>();
		posts.forEach(p -> postsToReturn.add(new PostWeb(p)));
		
		return postsToReturn;
	}
}
