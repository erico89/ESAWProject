USE lab2;

-- SQL query to obtain the tweets from all the users a concrete user is currently following
SELECT t.tweet_id, `description`, t.image, t.audio, t.nickname, t.likes, t.retweets, `date`, t.parent_id, t.user_id  FROM tweets t 
JOIN users u 
JOIN followers f 
ON t.user_id = u.user_id AND u.user_id = f.user_id
WHERE f.follower_id =  1
;
