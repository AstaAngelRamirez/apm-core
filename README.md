# APM. Module: Core
![Core](https://img.shields.io/badge/core-1.0.5-blue.svg) ![Platform](https://img.shields.io/badge/platform-Android-green.svg) ![Language](https://img.shields.io/badge/language-Java-red.svg)

Android module that has a lot of utilities that a developer needs.


# Download

Because we are using a private GitHub repository, we need to configure the company repository access into project for use this module:

In `gradle.properties` add your GitHub collaborator credeantials:

```groovy
  gitUser = GitHubUsername
  gitPass = GitHubPassword
```
*Note: is important that you are an active collaborator of project for make use of this module.*

Next, add the following into `build.gradle`:

```groovy
  maven {
    credentials {
      username gitUser
      password gitPass
    }
    url "https://raw.githubusercontent.com/astadevelopers/apm-core-android/master/aar"
    authentication {
      basic(BasicAuthentication)
    }
  }
```

And, the next steps are the usual:

With Gradle:

```groovy
implementation 'com.apm:core:X.X.X'
```

or Maven:

```xml
<dependency>
  <groupId>com.apm</groupId>
  <artifactId>core</artifactId>
  <version>X.X.X</version>
  <type>pom</type>
</dependency>
```

Where **X.X.X** is the current version of each library

Usage
--------

Please visit [JavaDoc](https://astadevelopers.github.io/apm-core-android/) to see documentation.

Author
--------

[Ing. Oscar G. Medina Cruz](https://github.com/astaoscarmedina)

**Android Developer**
