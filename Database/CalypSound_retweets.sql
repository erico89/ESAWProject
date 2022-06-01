CREATE TABLE IF NOT EXISTS `retweets` (
  `tweet_id` INT NOT NULL,
  `user_id` INT NOT NULL,
  `rdate` DATE NOT NULL,
  PRIMARY KEY (`tweet_id`, `user_id`),
  CONSTRAINT `tweet_id` FOREIGN KEY (`tweet_id`) REFERENCES `tweets` (`tweet_id`),
  CONSTRAINT `user_id` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`)
  )
ENGINE = InnoDB