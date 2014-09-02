name := """xstore"""

version := "1.0.0"

libraryDependencies ++= Seq(
  javaCore, javaJdbc, javaEbean, cache,
  "postgresql" % "postgresql" % "9.1-901-1.jdbc4",
  "mysql" % "mysql-connector-java" % "5.1.32",
  "com.auth0" % "java-jwt" % "0.2",
  "org.apache.httpcomponents" % "httpclient" % "4.3.2",
  "com.fasterxml.jackson.core" % "jackson-databind" % "2.0.0",
  "commons-codec" % "commons-codec" % "1.4"
)

play.Project.playJavaSettings

resolvers += "release repository" at  "http://hakandilek.github.com/maven-repo/releases/"

resolvers += "snapshot repository" at "http://hakandilek.github.com/maven-repo/snapshots/"
