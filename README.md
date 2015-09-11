# Description
Wrote by *George Yarish* for *PollToPay* as test assignment.
Project requirements [specification link](https://docs.google.com/document/d/1rwbn8AXpIQiz4GZt9iaF_BUVZPblOqztLSvGjyZDHLI/edit?pli=1).

## Technology stack
* [*Scala*](http://www.scala-lang.org/) - programming language
* [*Play framework*](https://www.playframework.com/) - MVC
* [*H2*](http://www.h2database.com/html/main.html) - in-memory SQL db
* [*Redis*](http://redis.io/) - NOSQL key-value store
* [*Slick*](http://slick.typesafe.com/) - ORM for RDBMS
* [*Akka*](http://akka.io/) - actor model for websocket handle
* [*Bootstrap3*](http://getbootstrap.com/) - for UI design
* [*Jquery*](https://jquery.com/) - for UI scripting
* [*Sbt*](http://www.scala-sbt.org/) - build system

## Project overview
* *build.sbt* - project build configuration
* *application.conf* - main configuration file
* *routes* - controllers paths
* *1.sql* - init SQL script
* *app* - source folder
* *client.html* - test client with random image loading

# Running

## Prerequisites
1. Installed [java 8* jdk](http://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html) (I use java version "1.8.0_45")
2. Installed [sbt launcher version 0.13.*](http://www.scala-sbt.org/download.html) (I use 0.13.8)
3. Run Redis Server 3.0.3 on standard port (6379)

## Build & Deploy
1. Move to project directory and execute:
```
sbt run
```
2. Open browser and navigate:
```
http://localhost:9000/
```

**Important!** Second operation will take some time. Sbt will compile and run project.

## Run in debug mode
1. Download [typesafe activator](https://www.typesafe.com/activator/download)
2. Move to project directory and execute:
```
activator -jvm-debug 9999 run
```

## Open project in intellij idea
1. Install *intellij idea* 14+
2. Install *scala plugin* > 1.5
3. Open project by *build.sbt* file
4. Call *Refresh All sbt projects* at the sbt window

# Testing

## Execute unit tests with coverage
1. In project directory run
```
sbt clean coverage test
```
2. After open in browser **$PATH_TO_PROJECT/banner-roulette/target/scala-2.11/scoverage-report/index.html**

## Benchmarking
**Command:** ```ab -n 10000 -c 20 http://localhost:9000/api/random```

**Result:** Percentage of the requests served within a certain time (ms)
  
  50%     15
  
  66%     18
  
  75%     20
  
  80%     21
  
  90%     26
  
  95%     30
  
  98%     36
  
  99%     40
  
 100%     73 (longest request)
 
 
**Command:** ```ab -n 100000 -c 20 http://localhost:9000/api/random```

**Result:** Percentage of the requests served within a certain time (ms)

  50%      9
  
  66%     11
  
  75%     13
  
  80%     14
  
  90%     18
  
  95%     22
  
  98%     27
  
  99%     31
  
 100%     78 (longest request)


# Known issues / Comments
* Sometimes new campaign not appear on main page. 
* Conversions calculating doesn't work.
* Test coverage less than 50% (actually its not but coverage tool include some system classes). 
Also current tests mostly useless, I need more time for them.
* Images resize only by width. This is not an error - I did it intentionally.
* I applied in-memory store but guess it is acceptable because it used only for rarely-change campaign data,
 for statistics used honest redis.
* Benchmarking counting only for **/api/random** - the most frequent used operation AFAIK.
* I don't think that store images as base64 in RDBMS good idea but was used as fast to implement solution. 



