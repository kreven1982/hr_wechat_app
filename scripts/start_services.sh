export LC_ALL="en_US.UTF-8"
mongod --dbpath /db/data --logpath /db/log/db.log --fork
cd /usr/local/tomcat7/bin
./startup.sh