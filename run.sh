#!/bin/bash
# Compile and run the Huzzle game.
# JavaFX 21 is not bundled with JDK 17+, so we point at jars in ~/.javafx/lib.
# Re-fetch them if that folder is ever missing:
#   mkdir -p ~/.javafx/lib && cd ~/.javafx/lib && \
#   for a in base graphics controls; do curl -sO "https://repo1.maven.org/maven2/org/openjfx/javafx-$a/21.0.5/javafx-$a-21.0.5-mac-aarch64.jar"; done

set -e
cd "$(dirname "$0")"

JFX="$HOME/.javafx/lib"
mkdir -p out

javac -d out \
  --module-path "$JFX" \
  --add-modules javafx.controls \
  $(find src -name "*.java")

# src is on the classpath so images/fonts/sounds load by their relative paths,
# and we run from the project root so records.txt is found.
java --module-path "$JFX" \
  --add-modules javafx.controls \
  -cp "out:src" \
  app.Main