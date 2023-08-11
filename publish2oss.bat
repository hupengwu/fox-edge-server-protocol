::查看密钥
gpg --list-keys

::将公钥发布到 PGP 密钥服务器
gpg --keyserver hkp://pool.sks-keyservers.net --send-keys

::查询公钥是否发布成功
gpg --keyserver hkp://pool.sks-keyservers.net --recv-keys

::将代码发布到https://s01.oss.sonatype.org/content/repositories/snapshots
mvn clean deploy -P release 
