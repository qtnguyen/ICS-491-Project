#ICS491-LulzSec-Secure Development Lifecycle Project (Version 3.0 08/9/2015)

Our project allows you to access a list of Playstation 4 video games released in North America after a succesfull login attempt has been made.

##USAGE INSTRUCTIONS

After starting the program you will be prompted to log in. If you have proper credentials you will be given access to the database of PlayStation 4 games. You can now scroll through all the games ever released in North America. When you are finished looking through the database, closing the database will close all connections to any databases and you will have to log back in to reconnect. The login and PS4 information in the database has been secured using MD5 hash through an option we have chosen in phpMyAdmin.

##OPEN SOURCE PROGRAMMING INSTRUCTIONS

If you decide to edit our code for your own purposes please keep these things in mind: we have worked very hard to put together this program and we are continually working on making it better (so if you have any questions or comments please contact us) and there are a few things you must have to allow this program to succesfully run in your IDE. Two main things you must have installed in your IDE are the jxl.jar file and the mysql-connector-java-5.1.24-bin.jar file. These files are necessary for the program to run as one connects to the database and the other callows the program to read and write from/to an excel file.

##CONTRIBUTORS

Andy Omori, Quynh-Tram Nguyen, and Spencer Bishop have worked collaboratively on this project. Each of the individuals have put in a fairly equal amount of work on the program, as well as research on the necessary tools to develop and run the program.

-Andy Omori worked on the PS4 database and population of game list in Login.java

-Quynh-Tram Nguyen worked on DBConnection2A.java to connect to JDBC and updated Login.java (renamed as Login2A.java) to ensure the login window could validate user login data. Also worked on the code that populates the database and the game list.

-Spencer Bishop worked on and edited Login2A.java, DBConnection2A.java, and the code that populates the database and the game list.

All of the contributors took part in the development and testing of each section of the program, and the current layout and function of the program reflects the group’s ideas as a whole.

##CLOSING THOUGHTS

CLOSING THOUGHTS

Overall, our group did exceptionally well communicating and meeting deadlines. There were some early problems connecting to the database but that was taken care of. The search function was a nightmare, the code seemed correct, but the results weren’t displaying. If given some more time in the future, this problem can be fixed. Since this is a security project, we wanted to emphasize the security of our program which took away some functionalities we wanted in the final product. So we focused highly on the security of our program and worked very diligently on making sure we had all avenues of attack and any possible vulnerabilities in our incident response plan.

The software we used was very efficient and we would recommend the use of these programs for any coding projects you might have. Eclipse is a very diverse, very simple IDE that can be used at almost any level of knowledge. We used WampServer to help us connect to phpMyAdmin locally. WampServer didn’t give us any issues, it was easy to download and very straightforward in use. phpMyAdmin gave us a little trouble at times. More so tedious to use than any real issues. An example would be when setting up a database you would have to rename the database and each column and you must specify a language. We had an issue early on in the use of phpMyAdmin through WampServer where our error messages were being printed in French, and since we didn’t have a French character set all of our error messages were symbols. If it is possible to strictly use the online version of phpMyAdmin we would recommend doing that. Also, we had some trouble installing Apache. We needed WampServer to use Apache for phpMyAdmin but we encountered some issues during installation and had to take extra steps to make sure Apache was installed correctly.

##HISTORY

Version 0.1 07/20/2015 -- Simple mockup of program. Working but with much lower production than wanted. Functionality and database connection issues.

Version 0.5 07/22/2015 --GUI working, original database issues solved, new database issues have arisen.

Version 0.7 07/25/2015 --Program functional, database issues solved. Not ready for use yet, simple bug fixes needed. No login screen yet, decided we needed a login screen.

Version 1.0 07/26/2015 --Login screen not operational, database connectivity issues with it has hindered progress. Program functional otherwise and will do what we intended.

Version 2.0 07/30/2015 --Login window is operational. Database connectivity issue resolved, able to grab information from the database but cannot verify user input with what is stores in the database.

Version 2.0 07/31/2015 --Login window is operational. Able to connect to database and verify user input. Login information in the database is not yet encrypted.

Version 2.2 07/31/2015 --Database is now being host locally instead of online for legal and ethical concerns. Login.java doesn’t execute SQL query proprerly to extract data from database. Data is not encrypted.

Version 2.2 08/01/2015 --Login window is operational; able to connect to local database. SQL query issue solved. Working on encrypting data.

Version 2.2 08/2/2015 --Heavily edited the PlayStation file to slim down the code, working on minor tweaks, Has a minor error that can be eventually fixed. DBConnection2A.java is running. Login2A.java runs and locates the correct database but when a succesfull login is made, a welcome window pops up instead of the Playstation.java file.

Version 2.2 08/4/2015 --Created the wiki page.

Version 3.0 08/5/2015 – Changed how the program works due to instability. Revised program and took out search feature. Combined Login and Playstation so that connection opens up database. Search feature will be implemented as part of program improvement in the future.  

Version 3.1 08/6/2015 – Made code more efficient and secure. Fixed a bug with the Login window. 

Version 3.5 08/8/2015 – Fixed a bug with the population of PS4 game database into game list.

Version Final 08/9/2015 - Removed Login.java, Login2A.java, and DBConnection2A.  Replaced with Login4.java and DBConnection3.java. Release date column, function removed because it isn't important. 

##CREDIT

Andy Omori - https://github.com/andyo777; Quynh-Tram Nguyen - https://github.com/qtnguyen; Spencer Bishop - https://github.com/BraddahLii
