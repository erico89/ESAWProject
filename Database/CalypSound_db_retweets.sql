CREATE TABLE IF NOT EXISTS `retweets` (
  `tweet_id` INT NOT NULL,
  `user_id` INT NOT NULL,
  `date` DATE NOT NULL,
  PRIMARY KEY (`tweet_id`, `user_id`),
  CONSTRAINT `retweets_ibfk_1` FOREIGN KEY (`tweet_id`) REFERENCES `tweets` (`tweet_id`),
  CONSTRAINT `retweets_ibfk_2` FOREIGN KEY (`user_id`) REFERENCES `users` (`user_id`)
  )
ENGINE = InnoDB
