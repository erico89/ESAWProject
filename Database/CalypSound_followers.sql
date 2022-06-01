CREATE TABLE IF NOT EXISTS `followers` (
  `user_id` INT NOT NULL,
  `follower_id` INT NOT NULL,
  PRIMARY KEY (`user_id`, `follower_id`),
  CONSTRAINT `user_id` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`),
  CONSTRAINT `follower_id` FOREIGN KEY (`follower_id`) REFERENCES `users` (`id`)
)
ENGINE = InnoDB