PA165 Dungeons & Dragons: Troops
============================
Spuštění aplikace
-----------------------

Pro spuštění aplikace je nutný běžící databázový server na adrese 'localhost:1527/pa165'

Výchozí uživatelské jméno a heslo pro správce je pa165

Webová aplikace
-----------------------

Webová aplikace lze spustit pomocí 'mvn tomcat7:run' na modulu DDtroops-web. Samotná aplikace pak běží na adrese http://localhost:8080/pa165/

REST API
--------------

REST API je součástí webového modulu. Spouští se tedy současně při spuštění webové aplikace.

Klientskou aplikaci, která využívá výše uvedeného REST API, je konzolová aplikace, která se spouští pomocí exec maven pluginu.

Spuštění klientské aplikace:

Aplikace se spouští na modulu DDtroops-console-client příkazem: 
mvn exec:java -Dexec.mainClass="com.pa165.ddtroops.console.client.Application" -Dexec.args="arg1 arg2"
Pro zobrazení nápovědy o používání aplikace stačí napsat příkaz:
mvn exec:java -Dexec.mainClass="com.pa165.ddtroops.console.client.Application" -Dexec.args="help"

Examples

Get hero with id 10
mvn exec:java -Dexec.mainClass="com.pa165.ddtroops.console.client.Application" -Dexec.args="getHero 10"

Create new hero with name Kraven, race Orc and xp 2000
mvn exec:java -Dexec.mainClass="com.pa165.ddtroops.console.client.Application" -Dexec.args="createHero Kraven Orc 2000"