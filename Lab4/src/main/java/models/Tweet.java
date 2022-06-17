package models;
import java.sql.Timestamp;

public class Tweet implements java.io.Serializable {

	 private static final long serialVersionUID = 1L;

	 private int tweet_id;
	 private String description;
	 private String image = null;
	 private String audio = null;
	 private String nickname;
	 private int likes = 0;
	 private int retweets = 0;
	 private Timestamp postDateTime;
	 private Integer parent_id = null;
	 private Integer user_id = null;

	 public Tweet() {
	 }

	 public Integer getTweet_id() {
		 return this.tweet_id;
	 }
	 
	 public void setTweet_id(Integer tweet_id_) {
		 this.tweet_id = tweet_id_;
	 }
	 
	 public String getDescription() {
		 return this.description;
	 }
	 public void setDescription(String description_) {
		 this.description = description_;
	 }
	 
	 public String getImage() {
		 return this.image;
	 }
	 public void setImage(String image_) {
		 this.image = image_;
	 }
	 
	 public String getAudio() {
		 return this.audio;
	 }
	 public void setAudio(String audio_) {
		 this.audio = audio_;
	 }
	 
	 public String getNickname() {
		 return this.nickname;
	 }
	 public void setNickname(String nickname_) {
		 this.nickname = nickname_;
	 }
	 
	 public Timestamp getPostDateTime() {
		 return this.postDateTime;
	 }
	 public void setPostDateTime(Timestamp postDateTime) {
		 this.postDateTime = postDateTime;
	 }

	public int getLikes() {
		return likes;
	}

	public void setLikes(int likes) {
		this.likes = likes;
	}

	public Integer getParent_id() {
		return parent_id;
	}

	public void setParent_id(int parent_id) {
		this.parent_id = parent_id;
	}

	public int getRetweets() {
		return retweets;
	}

	public void setRetweets(int retweets) {
		this.retweets = retweets;
	}

	public Integer getUser_id() {
		return user_id;
	}

	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}
	 
}