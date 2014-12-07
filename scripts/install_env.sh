echo "=========== install chinese support =========="
yum -y install "@Chinese Support"

echo "=========== install open jdk... ==========="
yum -y install java-1.7.0-openjdk

echo "=========== set mongoDB repo for yum ==========="
cp /scripts/MongoDB.repo /etc/yum.repos.d/MongoDB.repo

echo "=========== install mongoDB ==========="
yum -y install mongo-10gen mongo-10gen-server

echo "=========== create db data folders ==========="
cd /
mkdir db
mkdir db/data
mkdir db/log

echo "=========== start mongoDB ==========="
export LC_ALL=C
mongod --dbpath /db/data --logpath /db/log/db.log --fork


echo "=========== import city data to mongoDB ==========="
mongoimport --db hr_app --collection user --file /scripts/first_user.json

echo "=========== install tomcat 8 ==========="
cd /tmp
wget http://mirror.nus.edu.sg/apache/tomcat/tomcat-8/v8.0.15/bin/apache-tomcat-8.0.15.tar.gz
tar zxvf apache-tomcat-8.0.15.tar.gz
mv apache-tomcat-8.0.15 /usr/local/tomcat8

echo "=========== clean webapp folder ==========="
cd /usr/local/tomcat8/webapps
\rm -R *

echo "=========== restore server settings ==========="
\cp -rf /scripts/tomcat/* /usr/local/tomcat8/conf/

echo "=========== start tomcat 8 ==========="
cd /usr/local/tomcat8/bin
./startup.sh

echo "=========== firewall setup ==========="
/sbin/iptables -P INPUT DROP
/sbin/iptables -I INPUT -p tcp --dport 80 -j ACCEPT
/sbin/iptables -I INPUT -p tcp --dport 22 -j ACCEPT
/sbin/iptables -I INPUT -m state --state RELATED,ESTABLISHED -j ACCEPT
/sbin/iptables -A INPUT -i lo -j ACCEPT
/sbin/iptables -A INPUT -i eth0 -j ACCEPT

/etc/rc.d/init.d/iptables save
service iptables restart


