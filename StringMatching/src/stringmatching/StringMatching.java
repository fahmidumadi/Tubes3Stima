/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package stringmatching;

/**
 *
 * @author Fahmi
 */
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import twitter4j.Query;
import twitter4j.QueryResult;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.conf.ConfigurationBuilder;

public class StringMatching {
    public static void setup() throws IOException {

    ConfigurationBuilder cb = new ConfigurationBuilder();
    cb.setOAuthConsumerKey("yKBADWgPxGYtPhxVunyKvA4Xh");
    cb.setOAuthConsumerSecret("BGc6ls5NEy4lJ3eBnV6OloSoDeiG2g7uoD6u639zCvcv6ppo0z");
    cb.setOAuthAccessToken("1483524210-Pd59evwgG9ZsdxjWxwpvvoiNp7dzEVZXr3ooYFM");
    cb.setOAuthAccessTokenSecret("cO9rHOcJs8MAhQcduwZwauJHp7DaATZP3s1k44DlBHHH3");

    Twitter twitter = new TwitterFactory(cb.build()).getInstance();
    String token= "#lalinBDG";
    Query query = new Query(token);
    FileWriter outFile = new FileWriter(token.replaceAll("^#","").concat(".txt"), false);
    int numberOfTweets = 100;
    long lastID = Long.MAX_VALUE;
    ArrayList<Status> tweets = new ArrayList<>();
    query.setCount(100);
    try {
        QueryResult result = twitter.search(query);
        tweets.addAll(result.getTweets());
        System.out.println("Gathered " + tweets.size() + " tweets");
        for (Status t: tweets) 
            if(t.getId() < lastID) 
            lastID = t.getId();  
        }
    catch (TwitterException te) {
        System.out.println("Couldn't connect: " + te);  } 
    query.setMaxId(lastID-1);

    try (PrintWriter output = new PrintWriter(outFile)) {
        for (int i = 1; i <= tweets.size(); i++) {
            Status t = tweets.get(i-1);
            //GeoLocation loc = t.getGeoLocation();
              
            String user = t.getUser().getScreenName();
            String msg = t.getText();
            /*if (loc!=null) {
            Double lat = t.getGeoLocation().getLatitude();
            Double lon = t.getGeoLocation().getLongitude();
            
            System.out.println(i + " USER: " + user + " wrote: " + msg + " located at " + lat +  ", " + lon);
            }
            else*/
            output.println(i + ".  USER: " + user + " wrote: "  + msg.replaceAll("\n", " "));
        }
          output.close();
      }
    System.out.println("tweet yang terambil : " + tweets.size());
    System.out.println("file write succesfully");
    }

    public static void main(String[] args) {
      /*try {
          setup();
      } catch (IOException ex) {
          Logger.getLogger(StringMatching.class.getName()).log(Level.SEVERE, null, ex);
      }*/
      String output = "lalinBDG.txt";
      try {
          Categorizing Kategori = new Categorizing(output);
          Kategori.UseBM();
      } catch (IOException ex) {
          Logger.getLogger(StringMatching.class.getName()).log(Level.SEVERE, null, ex);
      }
          /*String text;
          String pattern;
          Scanner read = new Scanner(System.in);
          text = read.nextLine();
          pattern = read.next();
          System.out.println("Text: " + text);
          System.out.println("Pattern: " + pattern);
          text = text.toLowerCase();
          pattern = pattern.toLowerCase();
          int posn = BoyerMoore.indexOf(text.toCharArray(), pattern.toCharArray());
          if(posn == -1)
          System.out.println("Pattern not found");
          else
          System.out.println("Pattern starts at posn " + posn);*/
    }
}
