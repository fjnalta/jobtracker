DROP DATABASE IF EXISTS jobtracker;
CREATE DATABASE jobtracker;
GRANT ALL ON jobtracker.* to 'greenbeansit'@'localhost' IDENTIFIED BY 'databasepw';
FLUSH PRIVILEGES;