docker run -d -p 33061:3306 --name membersDB -e MYSQL_ROOT_PASSWORD=foo mysql:5.7 --character-set-server=utf8mb4 --collation-server=utf8mb4_unicode_ci
