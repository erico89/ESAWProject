package managers;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import models.Tweet;
import models.User;
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
		String query = "INSERT INTO tweets(description, image, profile_photo, nickname, likes, retweets, date, parent_id, user_id)"
				+ " VALUES (?,?,?,?,?,?,?,?,?)";
		
		PreparedStatement statement = null;
		try {
			statement = db.prepareStatement(query);
			statement.setString(1,tweet.getDescription());
			statement.setString(2,tweet.getImage());
			statement.setString(3,tweet.getProfilePhoto());
			statement.setString(4,tweet.getNickname());
			statement.setInt(5,tweet.getLikes());
			statement.setInt(6,tweet.getRetweets());
			statement.setTimestamp(7,tweet.getPostDateTime());
			Integer parend_id = tweet.getParent_id();
			if (parend_id == null) {
				statement.setNull(8, Types.INTEGER);
			} else {
				statement.setInt(8, parend_id);
			}
			Integer user_id = tweet.getUser_id();
			if (user_id == null) {
				statement.setNull(9, Types.INTEGER);
			} else {
				statement.setInt(9,user_id);								
			}
			statement.executeUpdate();
			statement.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	/* Delete existing tweet */
	public void deleteTweet(Integer tweet_id) {

		// Select all retweeted tweets and delete the relation FK.
		PreparedStatement statement = null;
		String allRetweets = "SELECT * FROM tweets WHERE parent_id = ?";
		String queryLikes = "DELETE FROM likes WHERE tweet_id = ?";
		PreparedStatement likeStatement = null;
		String queryRetweets = "DELETE FROM retweets WHERE tweet_id = ?";
		PreparedStatement rtStatement = null;

		try {
			statement = db.prepareStatement(allRetweets);
			statement.setInt(1,tweet_id);
			ResultSet rs = statement.executeQuery();
			while (rs.next()) {
				int id = rs.getInt("tweet_id");
				
				// Remove all retweeted's likes
				likeStatement = db.prepareStatement(queryLikes);
				likeStatement.setInt(1,id);
				likeStatement.executeUpdate();
				likeStatement.close();
				
				// Remove all retweeted's retweets
				rtStatement = db.prepareStatement(queryRetweets);
				rtStatement.setInt(1,id);
				rtStatement.executeUpdate();
				rtStatement.close();
			}
			statement.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		// Remove main tweets likes and retweets
		try {
			likeStatement = db.prepareStatement(queryLikes);
			likeStatement.setInt(1,tweet_id);
			likeStatement.executeUpdate();
			likeStatement.close();
			
			rtStatement = db.prepareStatement(queryRetweets);
			rtStatement.setInt(1,tweet_id);
			rtStatement.executeUpdate();
			rtStatement.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		// Remove main tweet
		String query = "DELETE FROM tweets WHERE tweet_id = ?";
		try {
			statement = db.prepareStatement(query);
			statement.setInt(1,tweet_id);
			statement.executeUpdate();
			statement.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	/*** Get tweet by id ***/
	public Tweet getTweetById(Integer tweet_id) {
		String query = "SELECT * FROM tweets WHERE tweet_id = ?";
		PreparedStatement statement = null;
		 Tweet tweet = new Tweet();

		 try {
			 statement = db.prepareStatement(query);
			 statement.setInt(1,tweet_id);
			 ResultSet rs = statement.executeQuery();
			 while (rs.next()) {
				 tweet.setTweet_id(rs.getInt("tweet_id"));
				 tweet.setDescription(rs.getString("description"));
				 tweet.setImage(rs.getString("image"));
				 tweet.setProfilePhoto(rs.getString("profile_photo"));
				 tweet.setNickname(rs.getString("nickname"));
				 tweet.setLikes(rs.getInt("likes"));
				 tweet.setRetweets(rs.getInt("retweets"));				 
				 //tweet.setPostDateTime(rs.getTimestamp("date"));;
				 tweet.setParent_id(rs.getInt("parent_id"));
				 tweet.setUser_id(rs.getInt("user_id"));
			 }
			 rs.close();
			 statement.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} 
		
		return tweet;
	}
	
	/*** Get public tweets order by desc.***/
	public List<Tweet> getTweets(Integer start, Integer end) {
		 String query = "SELECT * FROM tweets t ORDER BY t.date LIMIT ?,? ;";
		 PreparedStatement statement = null;
		 List<Tweet> l = new ArrayList<Tweet>();
		 try {
			 statement = db.prepareStatement(query);
			 statement.setInt(1,start);
			 statement.setInt(2,end);
			 ResultSet rs = statement.executeQuery();
			 while (rs.next()) {
				 Tweet tweet = new Tweet();
				 tweet.setTweet_id(rs.getInt("tweet_id"));
				 tweet.setDescription(rs.getString("description"));
				 tweet.setImage(rs.getString("image"));
				 tweet.setProfilePhoto(rs.getString("profile_photo"));
				 tweet.setNickname(rs.getString("nickname"));
				 tweet.setLikes(rs.getInt("likes"));
				 tweet.setRetweets(rs.getInt("retweets"));				 
				 //tweet.setPostDateTime(rs.getTimestamp("date"));;
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
	
	/*** Get public tweets with keyWord order by desc.***/
	public List<Tweet> getTweetsSearch(String keyWord, Integer start, Integer end) {
		 String query = "SELECT * FROM tweets t WHERE (t.description LIKE ? OR t.nickname LIKE ? ) ORDER BY t.date LIMIT ?,? ;";
		 PreparedStatement statement = null;
		 List<Tweet> l = new ArrayList<Tweet>();
		 try {
			 String words = "%" + keyWord + "%";
			 statement = db.prepareStatement(query);
			 statement.setString(1,words);
			 statement.setString(2,words);
			 statement.setInt(3,start);
			 statement.setInt(4,end);
			 ResultSet rs = statement.executeQuery();
			 while (rs.next()) {
				 Tweet tweet = new Tweet();
				 tweet.setTweet_id(rs.getInt("tweet_id"));
				 tweet.setDescription(rs.getString("description"));
				 tweet.setImage(rs.getString("image"));
				 tweet.setProfilePhoto(rs.getString("profile_photo"));
				 tweet.setNickname(rs.getString("nickname"));
				 tweet.setLikes(rs.getInt("likes"));
				 tweet.setRetweets(rs.getInt("retweets"));				 
				 //tweet.setPostDateTime(rs.getTimestamp("date"));;
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
	
	// Get followed users' tweets
	public List<Tweet> getFollowedTweets(Integer u_id, Integer start, Integer end) {
		 String query = "SELECT * FROM tweets t "
		 		+ "JOIN users as u ON t.user_id = u.user_id "
		 		+ "JOIN followers as f on u.user_id = f.user_id "
		 		+ "WHERE f.follower_id = ? "
		 		+ "ORDER BY t.likes LIMIT ?,?;";
		 PreparedStatement statement = null;
		 List<Tweet> l = new ArrayList<Tweet>();
		 try {
			 statement = db.prepareStatement(query);
			 statement.setInt(1,u_id);
			 statement.setInt(2,start);
			 statement.setInt(3,end);
			 ResultSet rs = statement.executeQuery();
			 while (rs.next()) {
				 Tweet tweet = new Tweet();
				 tweet.setTweet_id(rs.getInt("tweet_id"));
				 tweet.setDescription(rs.getString("description"));
				 tweet.setImage(rs.getString("image"));
				 tweet.setProfilePhoto(rs.getString("profile_photo"));
				 tweet.setNickname(rs.getString("nickname"));
				 tweet.setLikes(rs.getInt("likes"));
				 tweet.setRetweets(rs.getInt("retweets"));				 
				 //tweet.setPostDateTime(rs.getTimestamp("date"));;
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
	
	/* Get tweets from a user given start and end*/
	public List<Tweet> getUserTweets(Integer user_id,Integer start, Integer end) {
		 String query = "SELECT tweets.tweet_id,tweets.description,tweets.image,tweets.profile_photo,tweets.nickname,tweets.likes,tweets.retweets,tweets.date, tweets.parent_id, tweets.user_id "
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
				 tweet.setProfilePhoto(rs.getString("profile_photo"));
				 tweet.setNickname(rs.getString("nickname"));
				 tweet.setLikes(rs.getInt("likes"));
				 tweet.setRetweets(rs.getInt("retweets"));				 
				 //tweet.setPostDateTime(rs.getTimestamp("date"));;
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
	
	//check likes
	public boolean checkLikes(Tweet tweet, User user) {
		String query = "SELECT * FROM likes WHERE tweet_id = ? AND user_id = ?";
		PreparedStatement statement = null;
		boolean returns = false;
		try {
			statement = db.prepareStatement(query);
			statement.setInt(1, tweet.getTweet_id());
			statement.setInt(2, user.getId());
			ResultSet rs = statement.executeQuery();
			returns = rs.next();
			rs.close();
			statement.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return returns;
	}
	
	//check retweets
		public boolean checkRetweets(Tweet tweet, User user) {
			String query = "SELECT * FROM retweets WHERE tweet_id = ? AND user_id = ?";
			PreparedStatement statement = null;
			boolean returns = false;
			try {
				statement = db.prepareStatement(query);
				statement.setInt(1, tweet.getTweet_id());
				statement.setInt(2, user.getId());
				ResultSet rs = statement.executeQuery();
				returns = rs.next();
				rs.close();
				statement.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return returns;
		}

	public void addLikes(Tweet tweet, User user) {	
		String query = "INSERT INTO likes VALUE (?, ?);";
		
		String query2 = "UPDATE tweets"
				+ " SET likes = likes + 1"
				+ " WHERE tweet_id = ?";
		
		 PreparedStatement statement = null;
		 try {
			 statement = db.prepareStatement(query);
			 statement.setInt(1, tweet.getTweet_id());
			 statement.setInt(2, user.getId());
			 statement.executeUpdate();
			 
			 statement = null;
			 statement = db.prepareStatement(query2);
			 statement.setInt(1,tweet.getTweet_id());
			 statement.executeUpdate();
			 statement.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}		
	}
	
	//removes likes 
	public void removeLikes(Tweet tweet, User user) {	
		String query = "DELETE FROM likes WHERE tweet_id = ? AND user_id = ?";
		
		String query2 = "UPDATE tweets"
				+ " SET likes = likes - 1"
				+ " WHERE tweet_id = ?";
		
		 PreparedStatement statement = null;
		 try {
			 statement = db.prepareStatement(query);
			 statement.setInt(1, tweet.getTweet_id());
			 statement.setInt(2, user.getId());
			 statement.executeUpdate();
			 
			 statement = null;
			 statement = db.prepareStatement(query2);
			 statement.setInt(1,tweet.getTweet_id());
			 statement.executeUpdate();
			 statement.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}		
	}

	public void addRetweet(Tweet tweet, User user) {
		// TODO Auto-generated method stub
		String query = "INSERT INTO retweets VALUE (?, ?, ?);";
		
		String query2 = "UPDATE tweets"
				+ " SET retweets = retweets + 1"
				+ " WHERE tweet_id = ?";
		
		 PreparedStatement statement = null;
		 try {
			 statement = db.prepareStatement(query);
			 statement.setInt(1, tweet.getTweet_id());
			 statement.setInt(2, user.getId());
			 statement.setTimestamp(3, new Timestamp(System.currentTimeMillis()));
			 statement.executeUpdate();
			 
			 statement = null;
			 statement = db.prepareStatement(query2);
			 statement.setInt(1,tweet.getTweet_id());
			 statement.executeUpdate();
			 statement.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}	
	}
	
	public void removeRetweet(Tweet tweet, User user) {
		String query = "DELETE FROM retweets WHERE tweet_id = ? AND user_id = ?";
		
		String query2 = "UPDATE tweets"
				+ " SET retweets = retweets - 1"
				+ " WHERE tweet_id = ?";
		
		 PreparedStatement statement = null;
		 try {
			 statement = db.prepareStatement(query);
			 statement.setInt(1, tweet.getTweet_id());
			 statement.setInt(2, user.getId());
			 statement.executeUpdate();
			 
			 statement = null;
			 statement = db.prepareStatement(query2);
			 statement.setInt(1,tweet.getTweet_id());
			 statement.executeUpdate();
			 statement.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}	
	}
	
	
}