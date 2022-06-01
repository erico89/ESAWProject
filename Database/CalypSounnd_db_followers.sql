CREATE TABLE IF NOT EXISTS `followers` (
  `user_id` INT NOT NULL,
  `follower_id` INT NOT NULL,
  PRIMARY KEY (`user_id`, `follower_id`),
  CONSTRAINT `followers_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `users` (`user_id`),
  CONSTRAINT `followers_ibfk_2` FOREIGN KEY (`follower_id`) REFERENCES `users` (`user_id`)
)

