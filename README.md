Used libs:
play, scala, bootstrap3, slick (versions)

Running:
- run redis
- sbt run
- http://localhost:9000/ (first time compile)
- client test page

Run in debug: 
activator -jvm-debug 9999 run

Open project in idea:
- idea 14
- install scala plugin > 1.5
- open by "build.sbt"
* refresh mb needed

Project requirements specification:
https://docs.google.com/document/d/1rwbn8AXpIQiz4GZt9iaF_BUVZPblOqztLSvGjyZDHLI/edit?pli=1

Different:
1.sql
routes

Test coverage:
sbt clean coverage test
open in browser $PATH_TO_PROJECT/banner-roulette/target/scala-2.11/scoverage-report/index.html

Known issues:
- new campaigns not appear on main page 



