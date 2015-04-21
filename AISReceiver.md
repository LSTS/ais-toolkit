## Installation ##
Before you can use the AIS Receiver you have to install a [Tomcat application server](http://tomcat.apache.org/download-60.cgi) (tested with version 6).

After that just visit the [download site](http://code.google.com/p/ais-toolkit/downloads/list), grab the latest _aisreceiver-_version_.war_, rename it to _aisreceiver.war_ and place it in your Tomcats _webapps_ directory.

Without further configuration it uses an embedded Apache Derby database to store the received data. To use a PostgreSQL server you have to create a file called _aisreceiver.properties_ in yout Tomcats _conf_ directory. You can find a self-explaining example [here](http://code.google.com/p/ais-toolkit/source/browse/aisreceiver/trunk/aisreceiver.properties).

## Usage ##
After you have started Tomcat the receiver opens TCP Port 8000 to retrieve AIS data. We have successfully tested the receiver with the AIS antenna [easyAIS-LAN A046](http://www.easyais.de/en/product_page.php?prodid=30) from Weatherdock.

As soon as your receiver retrieves position data you are able to watch it on _YourServer_:8080/aisreceiver. We use OpenSeaMaps to display the received position report data.

## No data? ##
If you do not receive data or encounter other problems please check the _aisreceiver.log_ in your Tomcats _logs_ directory.