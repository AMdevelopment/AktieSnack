package se.amdev.stockdownloader;

import static se.amdev.stockdownloader.YahooStockApi.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.HashMap;
import java.util.TimeZone;

import javax.swing.Timer;

import se.amdev.aktiesnackserverdata.model.PostData;
import se.amdev.aktiesnackserverdata.model.StockData;
import se.amdev.aktiesnackserverdata.model.ThreadData;
import se.amdev.aktiesnackserverdata.model.UserData;
import se.amdev.aktiesnackserverweb.service.WebService;
import se.amdev.aktiesnackserverweb.web.StockWebApi;
import se.amdev.email.SendEmail;

public class StockDownloaderMain {

	public static boolean dailySent = false;

	public static void stockLoader() {

		StockWebApi.activated = 1;

		ActionListener action = new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e) {
				update();
				SendEmail.sendError("Server Started", new Exception());
			}
		};
		Timer onStartTimer = new Timer(3 * 60 * 1000, action);
		onStartTimer.setRepeats(false);
		onStartTimer.start();
	}

	public static void update() {
		ActionListener action = new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e) {
				stockUpdate();
				dailyUpdate();
			}
		};
		Timer updateTimer = new Timer(10 * 60 * 1000, action);
		updateTimer.start();
	}

	public static void stockUpdate() {
		String day = null;
		SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
		Calendar open = Calendar.getInstance(TimeZone.getTimeZone("Europe/Berlin"));
		Calendar close = Calendar.getInstance(TimeZone.getTimeZone("Europe/Berlin"));
		Calendar current = Calendar.getInstance(TimeZone.getTimeZone("Europe/Berlin"));
		try {
			day = current.getTime().toString().substring(0, 3);
			current.setTime(sdf.parse(current.getTime().toString().substring(11, 19)));
			close.setTime(sdf.parse("17:45:00"));
			open.setTime(sdf.parse("07:15:00"));
		}
		catch (ParseException ex) {
			// TODO Auto-generated catch block
			ex.printStackTrace();
		}

		if (current.before(close) && current.after(open) && !day.equals("Sat") && !day.equals("Sun")) {
			WebService service = new WebService();

			Collection<ThreadData> allThreads = new ArrayList<>();
			allThreads.addAll(service.findAllThreads());
			Collection<StockData> allStocks = new ArrayList<>();
			allStocks.addAll(service.findAllStocks());

			if (!allThreads.isEmpty()) {

				Collection<StockData> stocks = request(allThreads, allStocks);
				HashMap<String, StockData> updatedStocks = service.saveStocks(stocks);
				Collection<ThreadData> updatedThreads = new ArrayList<>();

				for (ThreadData t : allThreads) {

					if (t.getStock() == null) {
						updatedThreads.add(t.addStock(updatedStocks.get(t.getDescription())));
					}
				}
				if (!updatedThreads.isEmpty()) {
					service.saveThreads(updatedThreads);
				}
			}
		}
	}

	public static void dailyUpdate() {
		SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
		Calendar send = Calendar.getInstance(TimeZone.getTimeZone("Europe/Berlin"));
		Calendar dontSend = Calendar.getInstance(TimeZone.getTimeZone("Europe/Berlin"));
		Calendar doOverStart = Calendar.getInstance(TimeZone.getTimeZone("Europe/Berlin"));
		Calendar doOverStop = Calendar.getInstance(TimeZone.getTimeZone("Europe/Berlin"));
		Calendar current = Calendar.getInstance(TimeZone.getTimeZone("Europe/Berlin"));

		try {
			current.setTime(sdf.parse(current.getTime().toString().substring(11, 19)));
			dontSend.setTime(sdf.parse("23:30:00"));
			send.setTime(sdf.parse("23:00:00"));
			doOverStart.setTime(sdf.parse("23:30:01"));
			doOverStop.setTime(sdf.parse("00:00:00"));
		}
		catch (ParseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		if (current.after(doOverStart) && current.before(doOverStop)) {
			dailySent = false;
		}
		if (current.before(dontSend) && current.after(send) && !dailySent) {
			dailySent = true;

			WebService service = new WebService();

			Collection<ThreadData> allThreads = new ArrayList<>();
			allThreads.addAll(service.findAllThreads());
			Collection<StockData> allStocks = new ArrayList<>();
			allStocks.addAll(service.findAllStocks());
			Collection<UserData> allUsers = new ArrayList<>();
			allUsers.addAll(service.findAllUsers());
			Collection<PostData> allPosts = new ArrayList<>();
			allPosts.addAll(service.findAllPosts());
			System.out.println("SKICKAR");
			SendEmail.sendDaily(allThreads.size(), allStocks.size(), allUsers.size(), allPosts.size());
		}
	}

	public static void nasdaqCompanyUpdate() {
		WebService service = new WebService();
		Collection<ThreadData> newThreads = new ArrayList<>();

		Collection<ThreadData> allThreads = new ArrayList<>();
		allThreads.addAll(service.findAllThreads());

		if (allThreads.isEmpty()) {
			try {
				newThreads.addAll(CompanysApi.start());
			}
			catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
		else {
			try {
				newThreads.addAll(CompanysApi.start());
			}
			catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			for (ThreadData t : allThreads) {
				for (ThreadData nT : newThreads) {
					if (nT.getThreadName().equals(t.getThreadName())) {
						newThreads.remove(nT);
					}
				}
			}
		}
		service.saveThreads(newThreads);
	}
}
