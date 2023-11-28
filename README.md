# Autoconfiguration of the [Routes](https://github.com/MoodMinds/routes) in [Spring Boot](https://spring.io/projects/spring-boot)

The ability to auto-configure Routes implementation - [Routes Traverse](https://github.com/MoodMinds/routes-traverse) or
[Routes Reactive](https://github.com/MoodMinds/routes-reactive), depending on the environment in Spring Boot application.

## The Concept

The idea is to have only one version of the codebase that will work both in synchronous Servlet and Reactive application
contexts. Since the `Emittable` defines both `TraverseSupport` and `SubscribeSupport` (although only one of them implements
correctly), it can be used in both types of runtime.

## Usage

Just include the dependencies in your assembly and use the injected `Routes` interface implementation to return `Emittable`
instances in your `@Component`:

```java
import org.moodminds.emission.Emittable;
import org.moodminds.route.Routes;
import org.moodminds.route.Stream;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.MediaType.TEXT_EVENT_STREAM_VALUE;

@Service
public class StreamService {

    private static final Stream<String, Exception> STRINGS = $ -> $
            .source("AA", "aa", "bb", "BB", "CC");

    private static final Stream<String, Exception> UPPERS = $ -> $
            .map(() -> $.filter(STRINGS, "aa"::equalsIgnoreCase), String::toUpperCase);

    @Autowired
    private Routes routes;

    @Transactional
    public Emittable<String, Exception> uppers() {
        return routes.stream(UPPERS);
    }
}

@RestController
public class StreamController {

    @Autowired
    private StreamService streamService;

    @GetMapping(path = "/uppers", produces = TEXT_EVENT_STREAM_VALUE)
    public Emittable<String, Exception> uppers() {
        return streamService.uppers();
    }
}
```

## Maven configuration

Artifacts can be found on [Maven Central](https://search.maven.org/) after publication.

Add this required dependency to you classpath:

```xml
<dependency>
    <groupId>org.moodminds.routes.spring.boot</groupId>
    <artifactId>routes-spring-boot</artifactId>
    <version>${version}</version>
</dependency>
```

Depending on the execution environment, include one of these to your classpath:

```xml
<dependency>
    <groupId>org.moodminds.routes</groupId>
    <artifactId>routes-traverse</artifactId>
    <version>${version}</version>
</dependency>
```

or:

```xml
<dependency>
    <groupId>org.moodminds.routes</groupId>
    <artifactId>routes-reactive</artifactId>
    <version>${version}</version>
</dependency>
```

## Building from Source

You may need to build from source to use **Routes Spring Boot** (until it is in Maven Central) with Maven and JDK 1.9 at least.

## License
This project is going to be released under version 2.0 of the [Apache License][l].

[l]: https://www.apache.org/licenses/LICENSE-2.0
