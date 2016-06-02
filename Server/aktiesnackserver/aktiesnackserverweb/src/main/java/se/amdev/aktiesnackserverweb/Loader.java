package se.amdev.aktiesnackserverweb;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

@ApplicationPath("/*")
public class Loader extends Application {

	private static AnnotationConfigApplicationContext context;

	public static <T> T getBean(Class<T> type) {
		return context.getBean(type);
	}

	@PostConstruct
	public void contextInitialized() {
		context = new AnnotationConfigApplicationContext();
		context.scan("se.amdev.aktiesnackserverdata");
		context.refresh();
	}

	@PreDestroy
	public void contextDestroyed() {
		context.destroy();
	}
}