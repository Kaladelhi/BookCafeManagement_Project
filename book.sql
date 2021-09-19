-- MySQL dump 10.13  Distrib 8.0.20, for Win64 (x86_64)
--
-- Host: localhost    Database: bookcafe
-- ------------------------------------------------------
-- Server version	8.0.20

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `books`
--

DROP TABLE IF EXISTS `books`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `books` (
  `bid` varchar(5) NOT NULL,
  `title` varchar(120) DEFAULT NULL,
  `author` varchar(70) DEFAULT NULL,
  `genre` varchar(20) DEFAULT NULL,
  `publisher` varchar(100) DEFAULT NULL,
  `mrp` float DEFAULT NULL,
  `qty` int DEFAULT NULL,
  `shelfloc` varchar(3) DEFAULT NULL,
  `language` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`bid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `books`
--

LOCK TABLES `books` WRITE;
/*!40000 ALTER TABLE `books` DISABLE KEYS */;
INSERT INTO `books` VALUES ('001','To Kill a Mockingbird','Harper Lee','Classic','Harper Perennial',150,6,'A1','English'),('002','Little Women','Louisa May Alcott','Classic','Vintage Classics',199,6,'A1','English'),('003','Wuthering Heights','Emily Bronte','Classic','Vintage Classics',199,14,'A1','English'),('004','Gone with the Wind','Margaret Mitchell','Classic','Vintage Classics',499,13,'A1','English'),('005','Jane Eyre','Charlotte Bronte','Classic','Vintage Classics',195,9,'A1','English'),('006','The Time Machine','H. G. Wells','Sci-Fi','Fingerprint Publishing',125,5,'A2','English'),('007','The Invisible Man','H. G. Wells','Sci-Fi','Fingerprint Publishing',175,9,'A2','English'),('008','Journey to the Center of Earth','Jules Verne','Sci-Fi','Fingerprint Publishing',150,9,'A2','English'),('009','1984','George Orwell','Sci-Fi','Fingerprint Publishing',299,8,'A2','English'),('010','The War of the Worlds','H. G. Wells','Sci-Fi','Fingerprint Publishing',199,6,'A2','English'),('011','Switched','Amanda Hocking','Fantasy','Pan Macmillan',299,9,'A3','English'),('012','Torn','Amanda Hocking','Fantasy','Pan Macmillan',299,12,'A3','English'),('013','Ascend','Amanda Hocking','Fantasy','Pan Macmillan',299,12,'A3','English'),('014','Harry Potter and the Prisoner of Azkaban','J. K. Rowling','Fantasy','Bloomsbury Publishing',399,8,'A3','English'),('015','Harry Potter and the Goblet of Fire','J. K. Rowling','Fantasy','Bloomsbury Publishing',399,7,'A3','English'),('016','Life of Pi','Yann Martel','Adventure','Bloomsbury Publishing',199,5,'A4','English'),('017','The Hunger Games','Suzanne Collins','Adventure','Scholastic',299,6,'A4','English'),('018','Catching Fire','Suzanne Collins','Adventure','Scholastic',299,8,'A4','English'),('019','The Maze Runner','James Dashner','Adventure','Scholastic',299,6,'A4','English'),('020','The Scorch Trials','James Dashner','Adventure','Scholastic',399,7,'A4','English'),('021','The Adventures of Sherlock Holmes','Sir Arthur Conan Doyle','Mystery','Bloomsbury Publishing',399,11,'B2','English'),('022','The Night Fire','Michael Connelly','Mystery','Bloomsbury Publishing',299,11,'B2','English'),('023','And Then There Were None','Agatha Christie','Mystery','Bloomsbury Publishing',299,10,'B2','English'),('024','Nancy Drew and the Hidden Staircase','Carolyn Keene','Mystery','Scholastic',299,10,'B2','English'),('025','The Woman in White','Wylkie Collins','Mystery','All The Year Round',299,5,'B2','English'),('026','The Girl with the Dragon Tattoo','Stieg Larsson','Thriller','Quercus',499,7,'B3','English'),('027','The Girl on the Train','Paula Hawkins','Thriller','Random House',399,6,'B3','English'),('028','Gone Girl','Gillian Flynn','Thriller','Orion',399,8,'B3','English'),('029','The Da Vinci Code','Dan Brown','Thriller','Orion',399,11,'B3','English'),('030','Angels and Demons','Dan Brown','Thriller','Orion',399,15,'B3','English'),('031','Carrie','Stephen King','Horror','Orion',399,6,'B1','English'),('032','It','Stephen King','Horror','Orion',399,9,'B1','English'),('033','The Haunting of Hill House','Shirley Jackson','Horror','Penguin Classics',399,7,'B1','English'),('034','Bird Box','Josh Malerman','Horror','Harper',299,5,'B1','English'),('035','Dracula','Bram Stoker','Horror','Harper',299,10,'B1','English'),('036','Safe Haven','Nicholas Sparks','Romance','Harper',199,10,'C1','English'),('037','The Notebook','Nicholas Sparks','Romance','Harper',199,17,'C1','English'),('038','Brazen and the Beast','Sarah MacLean','Romance','Harper',250,6,'C1','English'),('039','P.S. I Love You','Cecilia Ahern','Romance','Harper',250,8,'C1','English'),('040','Royal Holiday','Jasmine Guillory','Romance','Harper',299,8,'C1','English'),('041','Sylvia Plath:The Collected Poems','Sylvia Plath','Poetry','Harper Perennial',499,9,'C2','English'),('042','The Sun and Her Flowers','Rupi Kaur','Poetry','Harper Perennial',399,18,'C2','English'),('043','Milk and Honey','Rupi Kaur','Poetry','Harper Perennial',399,20,'C2','English'),('044','There Are More Beautiful Things Than Beyonce','Morgan Park','Poetry','Random House',599,14,'C2','English'),('045','Your Soul Is A River','Nikita Gill','Poetry','Random House',499,16,'C2','English'),('046','The Immortals of Meluha','Amish Tripathi','Mythological Fiction','Westland',199,16,'B4','English'),('047','The Secret of the Nagas','Amish Tripathi','Mythological Fiction','Westland',299,14,'B4','English'),('048','Scion of Ikshvaku','Amish Tripathi','Mythological Fiction','Westland',399,14,'B4','English'),('049','The Odyssey','Homer','Mythological Fiction','Penguin Classics',399,10,'B4','English'),('050','The Illiad','Homer','Mythological Fiction','Penguin Classics',399,12,'B4','English');
/*!40000 ALTER TABLE `books` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2020-12-16 20:37:21
