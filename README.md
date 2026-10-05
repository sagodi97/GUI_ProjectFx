# Huzzle

A sliding puzzle game built with JavaFX.

## Requirements

- **JDK 17 or newer.** JavaFX is not bundled with the JDK, so Maven downloads it
  for you. JDK 11+ works in principle; the build is tested on 17.
- Nothing else. Maven itself is not needed — the repo ships the Maven wrapper.

Check your version with `java -version`.

## Run it

```bash
./mvnw javafx:run
```

On Windows:

```
mvnw.cmd javafx:run
```

That's the whole thing. `mvnw` downloads Maven and the JavaFX libraries on first
run (a minute or two, mostly network), then starts the game window. Later runs
start instantly.

Maven resolves the correct JavaFX native libraries for whatever OS it is running
on — Windows, Linux, or macOS on either Intel or Apple Silicon. There is nothing
to configure per platform.

## How it works

`pom.xml` does three things that matter:

- Sets `<sourceDirectory>` to `src`, since this project does not use the
  `src/main/java` Maven convention.
- Copies everything in `src` except `.java` files into the build output, because
  images, fonts, and sounds are loaded by relative path at runtime
  (`view/resources/img/logo.png`, and so on).
- Uses `javafx-maven-plugin` to build the module path and launch
  `app.Main`.

Scores are written to `records.txt` in the project directory. That file is
gitignored, so every player starts with an empty scoreboard.

## Development

```bash
./mvnw compile     # compile only
./mvnw clean       # remove build output
```

## IntelliJ IDEA

Open the project and let it import the Maven `pom.xml`. The JavaFX SDK is
resolved by Maven, so no manual library setup is needed.

`GUI_ProjectFx.iml` and `.vscode/launch.json` predate the Maven setup. They are
kept for reference but the Maven build is the source of truth.