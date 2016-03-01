BEGIN TRANSACTION;
CREATE TABLE "TRACKS" (
	`TRACKID`	INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT UNIQUE,
	`TRACKNAME`	TEXT,
	`ARTIST`	TEXT,
	`DURATION`	TEXT,
	`GENRE`	TEXT,
	`ALBUM`	TEXT
);
INSERT INTO `TRACKS` VALUES (1,'Mad','Neyo','04:10','R&B','Year of the Gentleman');
INSERT INTO `TRACKS` VALUES (2,'Hotline Bling','Drake','04:27','R&B','Views from the 6');
INSERT INTO `TRACKS` VALUES (3,'You','Chris Brown','03:10','R&B','Exclusive');
INSERT INTO `TRACKS` VALUES (4,'With You','Chris Brown','04:07','R&B','Exclusive');
INSERT INTO `TRACKS` VALUES (5,'Stay','Jay Sean','03:12','R&B','All or Nothing');
INSERT INTO `TRACKS` VALUES (6,'Lights Off','Jay Sean','04:45','R&B','All or Nothing');
INSERT INTO `TRACKS` VALUES (7,'Do You Remember','Jay Sean','02:54','R&B','All or Nothing');
INSERT INTO `TRACKS` VALUES (8,'If I Ain''t Got You','Jay Sean','03:23','R&B','All or Nothing');
INSERT INTO `TRACKS` VALUES (9,'Swim Good','Frank Ocean','03:34','R&B','Nostalgia Ultra');
INSERT INTO `TRACKS` VALUES (10,'We All Try','Frank Ocean','04:22','R&B','Nostalgia Ultra');
INSERT INTO `TRACKS` VALUES (11,'Love Crimes','Frank Ocean','03:46','R&B','Nostalgia Ultra');
INSERT INTO `TRACKS` VALUES (12,'Dust','Frank Ocean','04:07','R&B','Nostalgia Ultra');
INSERT INTO `TRACKS` VALUES (13,'Goldeneye','Frank Ocean','04:10','R&B','Nostalgia Ultra');
INSERT INTO `TRACKS` VALUES (14,'American Wedding','Frank Ocean','02:37','R&B','Nostalgia Ultra');
INSERT INTO `TRACKS` VALUES (15,'Closer','Neyo','00:31','R&B','Year of the Gentleman');
INSERT INTO `TRACKS` VALUES (16,'Single','Neyo','03:48','R&B','Year of the Gentleman');
INSERT INTO `TRACKS` VALUES (17,'Nobody','Neyo','03:11','R&B','Year of the Gentleman');
INSERT INTO `TRACKS` VALUES (18,'Miss Independent','Neyo','04:14','R&B','Year of the Gentleman');
INSERT INTO `TRACKS` VALUES (19,'Lie to Me','Neyo','05:08','R&B','Year of the Gentleman');
INSERT INTO `TRACKS` VALUES (20,'Stop This World','Neyo','04:22','R&B','Year of the Gentleman');
INSERT INTO `TRACKS` VALUES (21,'She Got Her Own','Neyo','02:58','R&B','Year of the Gentleman');
INSERT INTO `TRACKS` VALUES (22,'Part of the List','Neyo','03:47','R&B','Year of the Gentleman');
INSERT INTO `TRACKS` VALUES (23,'Fade into the Background','Neyo','03:45','R&B','Year of the Gentleman');
INSERT INTO `TRACKS` VALUES (24,'Why Does She Stay','Neyo','05:00','R&B','Year of the Gentleman');
INSERT INTO `TRACKS` VALUES (25,'Crazy In Love','Beyonce','03:58','R&B','Dangerously In Love');
INSERT INTO `TRACKS` VALUES (26,'Dangerously In Love 2','Beyonce','03:32','R&B','Dangerously In Love');
INSERT INTO `TRACKS` VALUES (27,'Say Aah','Trey Songz','03:56','R&B','Ready');
INSERT INTO `TRACKS` VALUES (28,'LOL','Trey Songz','03:26','R&B','Ready');
INSERT INTO `TRACKS` VALUES (29,'Jupiter Love','Trey Songz','04:47','R&B','Ready');
INSERT INTO `TRACKS` VALUES (30,'Be Where You Are','Trey Songz','03:54','R&B','Ready');
INSERT INTO `TRACKS` VALUES (31,'Black Roses','Trey Songz','03:36','R&B','Ready');
INSERT INTO `TRACKS` VALUES (32,'Love Lost','Trey Songz','03:36','R&B','Ready');
INSERT INTO `TRACKS` VALUES (33,'Successful','Trey Songz','05:10','R&B','Ready');
INSERT INTO `TRACKS` VALUES (34,'One Love','Trey Songz','05:21','R&B','Ready');
INSERT INTO `TRACKS` VALUES (35,'I Need a Girl','Trey Songz','04:24','R&B','Ready');
INSERT INTO `TRACKS` VALUES (36,'Hollalude','Trey Songz','04:26','R&B','Ready');
INSERT INTO `TRACKS` VALUES (37,'Neighbors Know My Name','Trey Songz','04:27','R&B','Ready');
INSERT INTO `TRACKS` VALUES (38,'Can''t Help But Wait','Trey Songz','04:35','R&B','Can''t Help But Wait');
INSERT INTO `TRACKS` VALUES (39,'Can''t Help Instrumental','Trey Songz','04:36','R&B','Can''t Help But Wait');
INSERT INTO `TRACKS` VALUES (40,'Classic','MKTO','04:37','POP','MKTO');
INSERT INTO `TRACKS` VALUES (41,'Thank You','MKTO','04:37','POP','MKTO');
INSERT INTO `TRACKS` VALUES (42,'Forever Until Tomorrow','MKTO','04:46','POP','MKTO');
INSERT INTO `TRACKS` VALUES (43,'Nowhere','MKTO','04:54','POP','MKTO');
INSERT INTO `TRACKS` VALUES (44,'No More Second Chances','MKTO','04:59','POP','MKTO');
INSERT INTO `TRACKS` VALUES (45,'Goodbye Song','MKTO','05:02','POP','MKTO');
INSERT INTO `TRACKS` VALUES (46,'Red','Taylor Swift','05:06','POP','Red');
INSERT INTO `TRACKS` VALUES (47,'State of Grace','Taylor Swift','05:09','POP','Red');
INSERT INTO `TRACKS` VALUES (48,'Stay Stay Stay','Taylor Swift','00:33','POP','Red');
INSERT INTO `TRACKS` VALUES (49,'I Knew You Were Trouble','Taylor Swift','00:58','POP','Red');
INSERT INTO `TRACKS` VALUES (50,'The Last Time','Taylor Swift','01:06','POP','Red');
INSERT INTO `TRACKS` VALUES (51,'Everything Has Changed','Taylor Swift','01:09','POP','Red');
INSERT INTO `TRACKS` VALUES (52,'Begin Again','Taylor Swift','01:17','POP','Red');
INSERT INTO `TRACKS` VALUES (53,'22','Taylor Swift','03:18','POP','Red');
INSERT INTO `TRACKS` VALUES (54,'All Too Well','Taylor Swift','03:53','POP','Red');
INSERT INTO `TRACKS` VALUES (55,'I Almost Do','Taylor Swift','01:23','POP','Red');
INSERT INTO `TRACKS` VALUES (56,'Holy Ground','Taylor Swift','01:30','POP','Red');
INSERT INTO `TRACKS` VALUES (57,'Wildest Dreams','Taylor Swift','02:00','POP','1989');
INSERT INTO `TRACKS` VALUES (58,'Shake It Off','Taylor Swift','02:01','POP','1989');
INSERT INTO `TRACKS` VALUES (59,'Bad Blood','Taylor Swift','02:08','POP','1989');
INSERT INTO `TRACKS` VALUES (60,'Blank Space','Taylor Swift','02:08','POP','1989');
INSERT INTO `TRACKS` VALUES (61,'Wide Awake','Katy Perry','02:01','POP','Teenage Dream');
INSERT INTO `TRACKS` VALUES (62,'Last Friday Night','Katy Perry','02:03','POP','Teenage Dream');
INSERT INTO `TRACKS` VALUES (63,'Firework','Katy Perry','02:03','POP','Teenage Dream');
INSERT INTO `TRACKS` VALUES (64,'Part Of Me','Katy Perry','02:05','POP','Teenage Dream');
INSERT INTO `TRACKS` VALUES (65,'Roar','Katy Perry','02:06','POP','Prism');
INSERT INTO `TRACKS` VALUES (66,'Unconditionally','Katy Perry','02:07','POP','Prism');
INSERT INTO `TRACKS` VALUES (67,'This Is How We Do','Katy Perry','02:46','POP','Prism');
INSERT INTO `TRACKS` VALUES (68,'The One That Got Away','Katy Perry','02:47','POP','Teenage Dream');
INSERT INTO `TRACKS` VALUES (69,'Hot N Cold','Katy Perry','02:47','POP','One Of The Boys');
INSERT INTO `TRACKS` VALUES (70,'Waking Up In Vegas','Katy Perry','02:47','POP','One Of The Boys');
INSERT INTO `TRACKS` VALUES (71,'One Of The Boys','Katy Perry','02:48','POP','One Of The Boys');
INSERT INTO `TRACKS` VALUES (72,'ET','Katy Perry','02:48','POP','Teenage Dream');
INSERT INTO `TRACKS` VALUES (73,'Birthday','Katy Perry','02:49','POP','Prism');
INSERT INTO `TRACKS` VALUES (74,'Ghost','Katy Perry','02:49','POP','Prism');
INSERT INTO `TRACKS` VALUES (75,'Freakshow','Britney Spears','02:49','POP','Blackout');
INSERT INTO `TRACKS` VALUES (76,'Piece of Me','Britney Spears','02:50','POP','Blackout');
INSERT INTO `TRACKS` VALUES (77,'Toy Soldier','Britney Spears','02:51','POP','Blackout');
INSERT INTO `TRACKS` VALUES (78,'Heaven on Earth','Britney Spears','03:06','POP','Blackout');
INSERT INTO `TRACKS` VALUES (79,'Break the Ice','Britney Spears','03:07','POP','Blackout');
INSERT INTO `TRACKS` VALUES (80,'Radar','Britney Spears','03:07','POP','Blackout');
INSERT INTO `TRACKS` VALUES (81,'Hot as Ice','Britney Spears','03:07','POP','Blackout');
INSERT INTO `TRACKS` VALUES (82,'Get Back','Britney Spears','03:07','POP','Blackout');
INSERT INTO `TRACKS` VALUES (83,'Everybody','Britney Spears','03:08','POP','Blackout');
INSERT INTO `TRACKS` VALUES (84,'White Dress','Parachute','03:08','ROCK','The Way It Was');
INSERT INTO `TRACKS` VALUES (85,'You and Me','Parachute','03:09','ROCK','The Way It Was');
INSERT INTO `TRACKS` VALUES (86,'Something to Believe In','Parachute','03:09','ROCK','The Way It Was');
INSERT INTO `TRACKS` VALUES (87,'Forever And Always','Parachute','03:10','ROCK','The Way It Was');
INSERT INTO `TRACKS` VALUES (88,'What I Know','Parachute','03:13','ROCK','The Way It Was');
INSERT INTO `TRACKS` VALUES (89,'American Secrets','Parachute','03:13','ROCK','The Way It Was');
INSERT INTO `TRACKS` VALUES (90,'Halfway','Parachute','03:14','ROCK','The Way It Was');
INSERT INTO `TRACKS` VALUES (91,'Philadelphia','Parachute','03:14','ROCK','The Way It Was');
INSERT INTO `TRACKS` VALUES (92,'Overnight','Parachute','03:14','ROCK','Overnight');
INSERT INTO `TRACKS` VALUES (93,'Hurrican','Parachute','03:14','ROCK','Overnight');
INSERT INTO `TRACKS` VALUES (94,'The Only One','Parachute','03:18','ROCK','Overnight');
INSERT INTO `TRACKS` VALUES (95,'Disappear','Parachute','04:37','ROCK','Overnight');
INSERT INTO `TRACKS` VALUES (96,'Higher','Parachute','04:46','ROCK','Overnight');
INSERT INTO `TRACKS` VALUES (97,'Can''t Help','Parachute','04:54','ROCK','Overnight');
INSERT INTO `TRACKS` VALUES (98,'Back Again','Parachute','04:59','ROCK','Losing Sleep');
INSERT INTO `TRACKS` VALUES (99,'All That I Am','Parachute','05:02','ROCK','Losing Sleep');
INSERT INTO `TRACKS` VALUES (100,'The New Year','Parachute','05:06','ROCK','Losing Sleep');
INSERT INTO `TRACKS` VALUES (101,'Be Here','Parachute','05:09','ROCK','Losing Sleep');
INSERT INTO `TRACKS` VALUES (102,'She Is Love','Parachute','05:21','ROCK','Losing Sleep');
INSERT INTO `TRACKS` VALUES (103,'Stuck In The Middle','Parachute','05:47','ROCK','Losing Sleep');
INSERT INTO `TRACKS` VALUES (104,'Strange World','Parachute','06:10','ROCK','Losing Sleep');
INSERT INTO `TRACKS` VALUES (105,'Words Meet Heartbeats','Parachute','03:09','ROCK','Losing Sleep');
INSERT INTO `TRACKS` VALUES (106,'Under Control','Parachute','03:09','ROCK','Losing Sleep');
INSERT INTO `TRACKS` VALUES (107,'The Mess I Made','Parachute','03:10','ROCK','Losing Sleep');
INSERT INTO `TRACKS` VALUES (108,'She','Parachute','03:13','ROCK','Losing Sleep');
INSERT INTO `TRACKS` VALUES (109,'Losing Sleep','Parachute','03:13','ROCK','Losing Sleep');
INSERT INTO `TRACKS` VALUES (110,'Didn''t See It Coming','Parachute','03:14','ROCK','Overnight');
INSERT INTO `TRACKS` VALUES (111,'The Other Side','Parachute','03:14','ROCK','Overnight');
INSERT INTO `TRACKS` VALUES (112,'Drive You Home','Parachute','03:14','ROCK','Overnight');
INSERT INTO `TRACKS` VALUES (113,'By The Way','Red Hot Chili Peppers','03:14','ROCK','By The Way');
INSERT INTO `TRACKS` VALUES (114,'The Zephyr Song','Red Hot Chili Peppers','03:18','ROCK','By The Way');
INSERT INTO `TRACKS` VALUES (115,'Can''t Stop','Red Hot Chili Peppers','03:18','ROCK','By The Way');
INSERT INTO `TRACKS` VALUES (116,'Scar Tissue','Red Hot Chili Peppers','03:23','ROCK','Californication');
INSERT INTO `TRACKS` VALUES (117,'Californication','Red Hot Chili Peppers','03:24','ROCK','Californication');
INSERT INTO `TRACKS` VALUES (118,'Around The World','Red Hot Chili Peppers','03:24','ROCK','Californication');
INSERT INTO `TRACKS` VALUES (119,'Under The Bridge','Red Hot Chili Peppers','03:24','ROCK','By The Way');
INSERT INTO `TRACKS` VALUES (120,'Other Side','Red Hot Chili Peppers','02:26','ROCK','By The Way');
INSERT INTO `TRACKS` VALUES (121,'Danny California','Red Hot Chili Peppers','03:24','ROCK','Californication');
INSERT INTO `TRACKS` VALUES (122,'Spinning','Jacks Mannequin','02:24','ROCK','The Glass Passenger');
INSERT INTO `TRACKS` VALUES (123,'Swim','Jacks Mannequin','05:21','ROCK','The Glass Passenger');
INSERT INTO `TRACKS` VALUES (124,'Bruised','Jacks Mannequin','05:09','ROCK','Everything In Transit');
INSERT INTO `TRACKS` VALUES (125,'I''m Ready','Jacks Mannequin','02:54','ROCK','Everything In Transit');
INSERT INTO `TRACKS` VALUES (126,'Dark Blue','Jacks Mannequin','02:26','ROCK','Everything In Transit');
INSERT INTO `TRACKS` VALUES (127,'Kill The Messenger','Jacks Mannequin','03:24','ROCK','Everything In Transit');
INSERT INTO `TRACKS` VALUES (128,'Rescued','Jacks Mannequin','02:24','ROCK','Everything In Transit');
INSERT INTO `TRACKS` VALUES (129,'Holiday from Real','Jacks Mannequin','05:21','ROCK','Everything In Transit');
INSERT INTO `TRACKS` VALUES (130,'La La Lie','Jacks Mannequin','05:09','ROCK','Everything In Transit');
INSERT INTO `TRACKS` VALUES (131,'The Resolution','Jacks Mannequin','02:54','ROCK','The Glass Passenger');
INSERT INTO `TRACKS` VALUES (132,'Caves','Jacks Mannequin','02:47','ROCK','The Glass Passenger');
INSERT INTO `TRACKS` VALUES (133,'Orphans','Jacks Mannequin','02:49','ROCK','The Glass Passenger');
INSERT INTO `TRACKS` VALUES (134,'Viva La Vida','Cold Play','05:06','ROCK','Viva La Vida');
INSERT INTO `TRACKS` VALUES (135,'Yes','Cold Play','03:43','ROCK','Viva La Vida');
INSERT INTO `TRACKS` VALUES (136,'Violet Hill','Cold Play','02:54','ROCK','Viva La Vida');
INSERT INTO `TRACKS` VALUES (137,'Cruise','Florida George Line','02:20','COUNTRY','To The Good Times');
INSERT INTO `TRACKS` VALUES (138,'Round Here','Florida George Line','02:49','COUNTRY','To The Good Times');
INSERT INTO `TRACKS` VALUES (139,'House Party','Sam Hunt','03:04','COUNTRY','Montevallo');
INSERT INTO `TRACKS` VALUES (140,'Never Again','Justin Timberlake','03:40','POP','Justified');
INSERT INTO `TRACKS` VALUES (141,'Last Night','Justin Timberlake','02:20','POP','Justified');
INSERT INTO `TRACKS` VALUES (142,'Cry Me A River','Justin Timberlake','03:03','POP','Justified');
INSERT INTO `TRACKS` VALUES (143,'Nothin Else','Justin Timberlake','05:02','POP','Justified');
INSERT INTO `TRACKS` VALUES (144,'Take It From Here','Justin Timberlake','03:18','POP','Justified');
INSERT INTO `TRACKS` VALUES (145,'What You Got','Justin Timberlake','02:06','POP','Justified');
INSERT INTO `TRACKS` VALUES (146,'ROCK Your Body','Justin Timberlake','03:06','POP','Justified');
INSERT INTO `TRACKS` VALUES (147,'Like I Love You','Justin Timberlake','04:21','POP','Justified');
INSERT INTO `TRACKS` VALUES (148,'Still On My Brain','Justin Timberlake','03:36','POP','Justified');
INSERT INTO `TRACKS` VALUES (149,'Right For Me','Justin Timberlake','02:51','POP','Justified');
INSERT INTO `TRACKS` VALUES (150,'Take Now','Justin Timberlake','04:09','POP','Justified');
INSERT INTO `TRACKS` VALUES (151,'In The End','Linkin Park','03:24','ROCK','Hybrid Theory');
INSERT INTO `TRACKS` VALUES (152,'Crawling','Linkin Park','02:38','ROCK','Hybrid Theory');
INSERT INTO `TRACKS` VALUES (153,'Step Up','Linkin Park','03:03','ROCK','Hybrid Theory');
INSERT INTO `TRACKS` VALUES (154,'Papercut','Linkin Park','01:46','ROCK','Hybrid Theory');
INSERT INTO `TRACKS` VALUES (155,'High Voltage','Linkin Park','04:36','ROCK','Hybrid Theory');
INSERT INTO `TRACKS` VALUES (156,'Place For My Head','Linkin Park','01:06','ROCK','Hybrid Theory');
INSERT INTO `TRACKS` VALUES (157,'My December','Linkin Park','03:31','ROCK','Hybrid Theory');
INSERT INTO `TRACKS` VALUES (158,'Points of Authority','Linkin Park','02:14','ROCK','Hybrid Theory');
INSERT INTO `TRACKS` VALUES (159,'Yellow','Cold Play','02:00','ROCK','Parachutes');
INSERT INTO `TRACKS` VALUES (160,'Trouble','Cold Play','01:51','ROCK','Parachutes');
INSERT INTO `TRACKS` VALUES (161,'Parachutes','Cold Play','02:07','ROCK','Parachutes');
INSERT INTO `TRACKS` VALUES (162,'We Never Change','Cold Play','01:23','ROCK','Parachutes');
INSERT INTO `TRACKS` VALUES (163,'Spies','Cold Play','00:58','ROCK','Parachutes');
INSERT INTO `TRACKS` VALUES (164,'Sparks','Cold Play','02:55','ROCK','Parachutes');
INSERT INTO `TRACKS` VALUES (165,'Shiver','Cold Play','02:28','ROCK','Parachutes');
INSERT INTO `TRACKS` VALUES (166,'Panic','Cold Play','04:17','ROCK','Parachutes');
INSERT INTO `TRACKS` VALUES (167,'We Cry','The Script','04:37','POP','The Script');
INSERT INTO `TRACKS` VALUES (168,'Before The Worst','The Script','03:07','POP','The Script');
INSERT INTO `TRACKS` VALUES (169,'Man Who Can''t Be Moved','The Script','02:26','POP','The Script');
INSERT INTO `TRACKS` VALUES (170,'If You See Kay','The Script','02:08','POP','The Script');
INSERT INTO `TRACKS` VALUES (171,'Breakeven','The Script','03:44','POP','The Script');
INSERT INTO `TRACKS` VALUES (172,'Talk You Down','The Script','02:03','POP','The Script');
INSERT INTO `TRACKS` VALUES (173,'The End Where I Begin','The Script','02:16','POP','The Script');
INSERT INTO `TRACKS` VALUES (174,'Rolling In The Deep','Adele','02:54','POP','21');
INSERT INTO `TRACKS` VALUES (175,'Someone Like You','Adele','03:04','POP','21');
INSERT INTO `TRACKS` VALUES (176,'Set Fire To The Rain','Adele','01:41','POP','21');
INSERT INTO `TRACKS` VALUES (177,'Hello','Adele','01:09','POP','25');
INSERT INTO `TRACKS` VALUES (178,'Skyfall','Adele','02:01','POP','James Bond Themes');
INSERT INTO `TRACKS` VALUES (179,'One and Only','Adele','03:31','POP','19');
INSERT INTO `TRACKS` VALUES (180,'Hometown Glory','Adele','02:45','POP','19');
INSERT INTO `TRACKS` VALUES (181,'Water Under the Bridge','Adele','03:04','POP','25');
INSERT INTO `TRACKS` VALUES (182,'Smells Like Teen Spirit','Nirvana','03:31','ROCK','Nevermind');
INSERT INTO `TRACKS` VALUES (183,'Come as You Are','Nirvana','03:48','ROCK','Nevermind');
INSERT INTO `TRACKS` VALUES (184,'Lithium','Nirvana','03:14','ROCK','Nevermind');
INSERT INTO `TRACKS` VALUES (185,'In Bloom','Nirvana','04:46','ROCK','Nevermind');
INSERT INTO `TRACKS` VALUES (186,'Something in the Way','Nirvana','03:45','ROCK','Nevermind');
INSERT INTO `TRACKS` VALUES (187,'Drain you','Nirvana','02:45','ROCK','Nevermind');
INSERT INTO `TRACKS` VALUES (188,'On a Plain','Nirvana','02:32','ROCK','Nevermind');
INSERT INTO `TRACKS` VALUES (189,'Mark My Words','Justin Bieber','03:43','POP','Purpose');
INSERT INTO `TRACKS` VALUES (190,'I''ll Show You','Justin Bieber','03:31','POP','Purpose');
INSERT INTO `TRACKS` VALUES (191,'Sorry','Justin Bieber','03:45','POP','Purpose');
INSERT INTO `TRACKS` VALUES (192,'What Do You Mean','Justin Bieber','02:13','POP','Purpose');
INSERT INTO `TRACKS` VALUES (193,'Love Yourself','Justin Bieber','04:12','POP','Purpose');
INSERT INTO `TRACKS` VALUES (194,'Company','Justin Bieber','02:58','POP','Purpose');
INSERT INTO `TRACKS` VALUES (195,'No Pressure','Justin Bieber','03:24','POP','Purpose');
INSERT INTO `TRACKS` VALUES (196,'Life Is Worth Living','Justin Bieber','02:44','POP','Purpose');
INSERT INTO `TRACKS` VALUES (197,'Purpose','Justin Bieber','02:38','POP','Purpose');
INSERT INTO `TRACKS` VALUES (198,'Where Are You Now','Justin Bieber','03:03','POP','Purpose');
INSERT INTO `TRACKS` VALUES (199,'Children','Justin Bieber','01:46','POP','Purpose');
INSERT INTO `TRACKS` VALUES (200,'The Feeling','Justin Bieber','04:36','POP','Purpose');
CREATE TABLE "SUBSCRIPTION" (
	`USERID`	INTEGER NOT NULL UNIQUE,
	`PREMIUM`	INTEGER NOT NULL DEFAULT 0,
	`SUBSCRIPTION TYPE`	INTEGER NOT NULL DEFAULT 0,
	`DUEDATE`	TEXT,
	`PAID`	INTEGER,
	FOREIGN KEY(`USERID`) REFERENCES `ID`
);
INSERT INTO `SUBSCRIPTION` VALUES (1,0,0,NULL,NULL);
INSERT INTO `SUBSCRIPTION` VALUES (2,0,0,NULL,NULL);
INSERT INTO `SUBSCRIPTION` VALUES (3,1,1,'2016-02-21',0);
INSERT INTO `SUBSCRIPTION` VALUES (4,1,1,'2016-03-20',0);
INSERT INTO `SUBSCRIPTION` VALUES (5,0,0,NULL,NULL);
CREATE TABLE "PLAYLIST-TRACK" (
	`PLAYLIST`	INTEGER NOT NULL,
	`TRACK`	INTEGER NOT NULL,
	FOREIGN KEY(`PLAYLIST`) REFERENCES PLAYLISTID,
	FOREIGN KEY(`TRACK`) REFERENCES `TRACKID`
);
CREATE TABLE "PLAYLIST" (
	`PLAYLISTID`	INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT UNIQUE,
	`PLAYLISTOWNER`	INTEGER NOT NULL,
	FOREIGN KEY(`PLAYLISTOWNER`) REFERENCES `ID`
);
CREATE TABLE "FRIENDLIST" (
	`OWNERID`	INTEGER NOT NULL,
	`FRIENDID`	INTEGER NOT NULL,
	FOREIGN KEY(`OWNERID`) REFERENCES `ID`,
	FOREIGN KEY(`FRIENDID`) REFERENCES ID
);
CREATE TABLE "DEVICE" (
	`DEVICEID`	INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,
	`DEVICENAME`	TEXT NOT NULL,
	`DEVICETYPE`	TEXT NOT NULL,
	`DATE`	TEXT NOT NULL,
	`DEVICEOWNER`	INTEGER NOT NULL,
	FOREIGN KEY(`DEVICEOWNER`) REFERENCES `ID`
);
CREATE TABLE "ACCOUNT" (
	`ID`	INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,
	`USERNAME`	TEXT NOT NULL,
	`PASSWORD`	TEXT NOT NULL,
	`FIRSTNAME`	TEXT NOT NULL,
	`LASTNAME`	TEXT NOT NULL,
	`DOORNO`	TEXT NOT NULL,
	`STREET`	TEXT NOT NULL,
	`CITY`	TEXT NOT NULL,
	`COUNTY`	TEXT NOT NULL DEFAULT 'N/A',
	`POSTCODE`	TEXT NOT NULL,
	`CONTACTNO`	INTEGER NOT NULL
);
INSERT INTO `ACCOUNT` VALUES (1,'pp307','password','Prarthana','Prakash','1','Mile End Road','London','Greater London','E2 101',7799283847);
INSERT INTO `ACCOUNT` VALUES (2,'er303','password','Edgar','Rinkevicius','2','Mile End Road','Glasgow','Scotland','S4 100',7983729173);
INSERT INTO `ACCOUNT` VALUES (3,'hq300','password','Hamza','Qureashi','3','Mile End Road','London','Greater London','E2 102',7873612839);
INSERT INTO `ACCOUNT` VALUES (4,'mas36','password','Mohammed','Samad','4','Mile End Road','Glasgow','Scotland','S4 500',7762517282);
INSERT INTO `ACCOUNT` VALUES (5,'jll30','password','Jerle','Leow','5','Mile End Road','London','Greater London','E2 103',7972715368);
COMMIT;
