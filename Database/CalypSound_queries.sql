USE lab2;

-- FILL THE DB WITH SOME ROWS TO TEST
INSERT
INTO users
VALUES
(4,'f0ck_your_racist_borders','Lil Carlos','xd', NULL, 'lilcarlos.official@gmail.com','Iloveg1lfs', 21/03/1999 , 'Yamato.png' ),
(0,'joseluis','Jose Luis', 'García', 'González', 'joseluis.garcia.gonzalez@gmail.com', 'Contraseña123', 28/05/2022, 'foto postu.png' ),
(2, 'Svennos', 'Sven', ' Edelenbosch', NULL,  'SvennossBusiness@outlook.com', 'stere0_love1994', 24/03/1994, 'svenny_icon.png'),
(3,'RosiQueen', 'Maria', 'Santos', 'Ferreira', 'uumakako23@hotmail.es', 'MuitoPrazer23', 06/11/2000, 'me.jpg' ),
(1, 'ahmed_14', 'Ahmed', 'Ashmawi', NULL, '234ngrid3@protonmail.com', 'qJg1%7uXJb!n', 17/10/1968, 'xd.png')
;

INSERT 
INTO followers
VALUES
(0,4),
(4,2),
(1,2),
(2,1),
(3,0)
;

INSERT
INTO tweets( `tweet_id`,`description`, `image`, `audio`, `nickname`, `likes`, `retweets`, `date`, `parent_id`, `user_id`) 
VALUES 
(0, 'Check out⚡','kawasaki.jpg','Deus.mp3','joseluis',8,0,12/04/2020, NULL, 0),
(1, 'Motomami mola mogollón😍',NULL,'Candy.mp3','RosiQueen',121,2,18/03/2022, NULL, 3),
(2, 'Go to sleep, tomorrow you have school😂 @ahmed_14','meme.png',NULL,'Svennos',549,2034,23/05/2022, NULL, 2),
(3,'127.38.118.119',NULL,NULL,'ahmed_14',1438,4059,23/05/2022, 2, 1),
(4, 'Cant get it outta my head🔥',NULL,'XO Tour Llif3.mp3', 'f0ck_your_racist_borders', 3409,13,09/07/2018, NULL,4)
;

-- SQL query to obtain the tweets from all the users a concrete user is currently following
SELECT t.tweet_id, `description`, t.image, t.audio, t.nickname, t.likes, t.retweets, `date`, t.parent_id, t.user_id  FROM tweets t 
JOIN users u 
JOIN followers f 
ON t.user_id = u.user_id AND u.user_id = f.user_id
WHERE f.follower_id =  0
;
