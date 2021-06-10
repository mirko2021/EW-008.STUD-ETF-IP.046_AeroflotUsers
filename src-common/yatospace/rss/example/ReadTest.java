// net.etfbl.ip.rss //

package yatospace.rss.example; 

public class ReadTest {
	public static void main(String[] args) {
		RSSFeedParser parser = new RSSFeedParser("http://www.b92.net/info/rss/kultura.xml");
		Feed feed = parser.readFeed();
		System.out.println(feed);
		for (FeedMessage message : feed.getMessages()) {
			System.out.println(message);

		}
	}
}
