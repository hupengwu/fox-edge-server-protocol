:: 在本地Maven的Settings.xml总，填写sonatype的账号密码
::	<server>
::      <id>oss</id>
::      <username>sonatype的账号</username>
::      <password>sonatype的密码</password>
::    </server>
::
	
::查看密钥
gpg --list-keys

::将公钥发布到 PGP 密钥服务器
gpg --keyserver hkp://pool.sks-keyservers.net --send-keys

::查询公钥是否发布成功
gpg --keyserver hkp://pool.sks-keyservers.net --recv-keys

::将代码发布到https://s01.oss.sonatype.org/content/repositories/snapshots
mvn clean deploy -P release 

:: 上面的编译/提交完成后，再去网站https://s01.oss.sonatype.org/ 手动审批代码，就会发布到中央仓库了
