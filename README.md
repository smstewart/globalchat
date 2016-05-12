# full_test

A test project</br>
web server</br></br>

<h1>Setup</h1>

<ul>
	<li>Install Postgres 9.3</li>
	<li>Install Maven 3</li>
</ul>
<h3>Setup Commands From Postgres</h3>
<ul>
	<li>CREATE DATABASE globalchatdb;</li>
	<li>CREATE DATABASE globalchat_test_db;</li>
	<li>CREATE USER globalchat WITH PASSWORD 'globalchatpassword';</li>
	<li>GRANT ALL PRIVILEGES ON DATABASE globalchat TO globalchat;</li>
	<li>GRANT ALL PRIVILEGES ON DATABASE globalchat_test_db TO globalchat;</li>
</ul>
<h3>Maven Commands</h3>
<ul>
	<li>mvn compile</li>
	<li>mvn test</li>
	<li>mvn jetty:run</li>
</ul>