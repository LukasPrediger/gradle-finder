# GradleFinder

## Prerequisites
- Requires Java 17 or newer to build and run

## Usage
When executed will find a gradlew and build.gradle or build.gradle.kts in the file tree above or in the current directory

Pass `--info`to get debug output

## How to Install

Build distribution
```bash
./gradlew install
```

Copy distribution to some path
``` bash
cp build/install/GradleFinder <your_path>
```

Add `/bin/GradleFinder` to your path, for example by linking it to `/usr/local/bin`
``` bash
ln -s <your_path>/GradleFinder/bin/GradleFinder /usr/local/bin/gradlefinder
```