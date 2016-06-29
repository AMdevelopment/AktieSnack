package se.amdev.stockdownloader;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Collection;

import se.amdev.aktiesnackserverdata.model.ThreadData;

public class CompanysApi {
	
	public static ArrayList<ThreadData> start() throws IOException {
		return getUrlContents("https://raw.githubusercontent.com/AMdevelopment/test/master/aktier%20nasda.rtf");
	}

	private static ArrayList<ThreadData> getUrlContents(String theUrl) {
		StringBuilder content = new StringBuilder();
		try {
			URL url = new URL(theUrl);

			URLConnection urlConnection = url.openConnection();

			BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));

			String line;
			while ((line = bufferedReader.readLine()) != null) {
				content.append(line + "\n");
			}
			bufferedReader.close();
		}
		catch (Exception e) {
			e.printStackTrace();
		}

		ArrayList<String> test = new ArrayList<String>();
		String[] t = content.toString().split("</tr>");
		
		for (int i = 0; i < t.length; i++) {
			test.add(t[i]);
		}

		return parse(test);
	}

	private static ArrayList<ThreadData> parse(Collection<String> companys) {
		ArrayList<ThreadData> threads = new ArrayList<ThreadData>();
		StringBuilder value = new StringBuilder();
		StringBuilder cur = new StringBuilder();
		ArrayList<String> name = new ArrayList<String>();
		ArrayList<String> stock = new ArrayList<String>();
		ArrayList<String> curre = new ArrayList<String>();
		int i = 0;

		for (String s : companys) {
			if (i == companys.size() - 1) {
				break;
			}
			value.append(s.substring(s.indexOf("title="), s.indexOf(" class=")) + ",,");

			String etc = value.substring(value.indexOf("- "), value.indexOf("\",,"));
			String name1 = etc.substring(2, etc.length());

			if (name1.contains("\\'f8") || name1.contains("\\'d8") || name1.contains("\\'f6") || name1.contains("\\'d6") || name1.contains("\\'f0")
					|| name1.contains("\\'f3") || name1.contains("\\'e6") || name1.contains("\\'e4") || name1.contains("\\'c5") || name1.contains("\\'e5")
					|| name1.contains("\\'e9") || name1.contains("\\'e1") || name1.contains("\\'cd") || name1.contains("\\'b4")) {
				
				String str = name1;
				if (str.contains("\\'f8")) {
					str = str.replace("\\'f8", "ø");
				}
				if (str.contains("\\'d8")) {
					str = str.replace("\\'d8", "Ø");
				}
				if (str.contains("\\'f6")) {
					str = str.replace("\\'f6", "ö");
				}
				if (str.contains("\\'d6")) {
					str = str.replace("\\'d6", "Ö");
				}
				if (str.contains("\\'f0")) {
					str = str.replace("\\'f0", "ð");
				}
				if (str.contains("\\'f3")) {
					str = str.replace("\\'f3", "ó");
				}
				if (str.contains("\\'e6")) {
					str = str.replace("\\'e6", "æ");
				}
				if (str.contains("\\'e4")) {
					str = str.replace("\\'e4", "ä");
				}
				if (str.contains("\\'c5")) {
					str = str.replace("\\'c5", "Å");
				}
				if (str.contains("\\'e5")) {
					str = str.replace("\\'e5", "å");
				}
				if (str.contains("\\'e9")) {
					str = str.replace("\\'e9", "é");
				}
				if (str.contains("\\'e1")) {
					str = str.replace("\\'e1", "á");
				}
				if (str.contains("\\'cd")) {
					str = str.replace("\\'cd", "í");
				}
				if (str.contains("\\'b4")) {
					str = str.replace("\\'b4", "s'");
				}
				name.add(str);
			}
			else {
				name.add(name1);
			}
			 
			int index = s.indexOf("BaseCurrencyId=");
			cur.append(s.substring(index, index + 18));
			String currrr = cur.toString().substring(cur.length() - 3, cur.length());
			curre.add(currrr);
			if (currrr.equals("SEK")) {
				stock.add(value.substring(7, value.indexOf(" - ")).replace(" ", "-") + ".ST");
			}
			if (currrr.equals("EUR")) {
				stock.add(value.substring(7, value.indexOf(" - ")).replace(" ", "-") + ".HE");
			}
			if (currrr.equals("NOK")) {
				stock.add(value.substring(7, value.indexOf(" - ")).replace(" ", "-") + ".OS");
			}
			if (currrr.equals("DKK")) {
				stock.add(value.substring(7, value.indexOf(" - ")).replace(" ", "-") + ".CO");
			}
			if (currrr.equals("ISK")) {
				stock.add(value.substring(7, value.indexOf(" - ")).replace(" ", "-") + ".IC");
			}
			// System.out.println(currency);
			value = new StringBuilder();
			cur = new StringBuilder();
			i++;
		}

		for (int j = 0; j < name.size(); j++) {
			threads.add(new ThreadData(name.get(j), stock.get(j), curre.get(j)));
		}

		return threads;
	}
}
