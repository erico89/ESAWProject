package managers;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import models.Tweet;
import utils.DB;


public class ManageTweets {
	
	private DB db = null ;
	
	public ManageTweets() {
		try {
			db = new DB();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void finalize() {
		try {
			db.disconnectBD();
		} catch (Throwable e) {
			e.printStackTrace();
		}
	}
	
	/* Add a tweet */
	public void addTweet(Tweet tweet) {
		String query = "INSERT INTO tweets(description, image, audio, nickname, likes, retweets, date, parent_id, user_id)"
				+ " VALUES (?,?,?,?,?,?,?,?,?)";
		
		PreparedStatement statement = null;
		try {
			statement = db.prepareStatement(query);
			statement.setString(1,tweet.getDescription());
			statement.setString(2,tweet.getImage());
			statement.setString(3,tweet.getAudio());
			statement.setString(4,tweet.getNickname());
			statement.setInt(5,tweet.getLikes());
			statement.setInt(6,tweet.getRetweets());
			statement.setTimestamp(7,tweet.getPostDateTime());
			statement.setInt(8,tweet.getParent_id());
			statement.setInt(9,tweet.getUser_id());
			statement.executeUpdate();
			statement.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	/* Delete existing tweet */
	public void deleteTweet(Integer tweet_id) {
		String query = "DELETE FROM tweets WHERE tweet_id = ?";
		PreparedStatement statement = null;
		try {
			statement = db.prepareStatement(query);
			statement.setInt(1,tweet_id);
			statement.executeUpdate();
			statement.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	
	/* Get tweets from a user given start and end*/
	public List<Tweet> getUserTweets(Integer user_id,Integer start, Integer end) {
		 String query = "SELECT tweets.tweet_id,tweets.description,tweets.image,tweets.audio,tweets.nickname,tweets.likes,tweets.retweets,tweets.date, tweets.parent_id, tweets.user_id "
		 		+ "FROM tweets "
		 		+ "INNER JOIN users ON tweets.user_id = users.user_id "
		 		+ "where tweets.user_id = ? "
		 		+ "ORDER BY tweets.date DESC LIMIT ?,? ;";
		 PreparedStatement statement = null;
		 List<Tweet> l = new ArrayList<Tweet>();
		 try {
			 statement = db.prepareStatement(query);
			 statement.setInt(1,user_id);
			 statement.setInt(2,start);
			 statement.setInt(3,end);
			 ResultSet rs = statement.executeQuery();
			 while (rs.next()) {
				 Tweet tweet = new Tweet();
				 tweet.setTweet_id(rs.getInt("tweet_id"));
				 tweet.setDescription(rs.getString("description"));
				 tweet.setImage(rs.getString("image"));
				 tweet.setAudio(rs.getString("audio"));
				 tweet.setNickname(rs.getString("nickname"));
				 tweet.setLikes(rs.getInt("likes"));
				 tweet.setRetweets(rs.getInt("retweets"));				 
				 tweet.setPostDateTime(rs.getTimestamp("date"));;
				 tweet.setParent_id(rs.getInt("parent_id"));
				 tweet.setUser_id(rs.getInt("user_id"));
				 l.add(tweet);
			 }
			 rs.close();
			 statement.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} 
		return  l;
	}
	
	
}