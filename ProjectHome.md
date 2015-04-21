# AIS Toolkit #
Welcome to our _AIS Toolkit_ page.

The base of our toolkit is a very simple to use parser for AIS messages. Currently only the most common AIS message types (1, 2, 3 and 5) are supported. Look at the [AIS Parser Wiki Site](https://code.google.com/p/ais-toolkit/wiki/AISParser) for a quick usage introduction. For detailed AIS protocol information look at [BerliOS](http://gpsd.berlios.de/AIVDM.html).

In addition you should have a look at our [AIS Receiver](http://code.google.com/p/ais-toolkit/wiki/AISReceiver). It is able to retrieve AIS messages from multiple AIS antennas and visualize them on a [OpenSeaMap](http://openseamap.org/).

## News ##
2013-02-08:
  * AIS Parser 2.0.0 released
2012-29-08:
  * AIS Parser 2.0.0 Snapshot released
  * Complete rewrite
  * API changed - sorry for any circumstances ([changelog](http://code.google.com/p/ais-toolkit/source/detail?r=48))
2012-27-01:
  * AIS Parser 1.0.0 released

## Maven Usage ##
Include Maven Repository (stable)
```
<repository>
	<id>ais-toolkit-releases</id>
	<url>http://ais-toolkit.googlecode.com/svn/maven/releases</url>
	<releases><enabled>true</enabled></releases>
	<snapshots><enabled>false</enabled></snapshots>
</repository>
```

Include Maven Repository (snapshot)
```
<repository>
	<id>ais-toolkit-snapshots</id>
	<url>http://ais-toolkit.googlecode.com/svn/maven/snapshots</url>
	<releases><enabled>false</enabled></releases>
	<snapshots><enabled>true</enabled></snapshots>
</repository>
```

Add AIS Parser dependency (replace {version} by the desired version)
```
<dependency>
	<groupId>de.baderjene.aistoolkit</groupId>
	<artifactId>aisparser</artifactId>
	<version>{version}</version>
</dependency>
```