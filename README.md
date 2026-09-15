# java-truck-dealership-oop

A Java Swing application for managing a truck dealership, developed as an extra assignment for an Object-Oriented Programming course.

## Features

- Add flatbed trucks with a platform length in meters.
- Add car carriers with a capacity measured in number of cars.
- Assign an automatically generated ID to each truck.
- Remove trucks by ID and view their information.
- Store up to five trucks in the yard.
- Load sample data: three car carriers and two flatbed trucks, when the yard is empty.

All data is stored in memory and is lost when the application closes.

## Object-oriented concepts

- **Abstraction:** `Truck` defines shared truck data and an abstract `getInfo()` method.
- **Inheritance:** `FlatbedTruck` and `CarCarrier` extend `Truck`.
- **Polymorphism:** the yard stores different truck types as `Truck` objects and calls their overridden `getInfo()` methods.
- **Encapsulation:** truck attributes are private and exposed through getters.
- **Exceptions:** `TruckYardException` represents errors such as a full or empty yard.

## Project structure

```text
truck-dealership-oop/
├── .vscode/
│   └── settings.json
├── src/
│   ├── Main.java
│   ├── Truck.java
│   ├── FlatbedTruck.java
│   ├── CarCarrier.java
│   ├── TruckYard.java
│   └── TruckYardException.java
├── .gitignore
└── README.md
```

The empty `lib/` directory is not tracked by Git. No external dependencies or build tools are required.

## Requirements

- A Java Development Kit (JDK); compilation verified with JDK 21.
- A graphical desktop environment to run the Swing interface.

## Build and run

From the project root:

```sh
javac -encoding UTF-8 -d bin src/*.java
java -cp bin Main
```

In VS Code, open the project with Java language support installed and run `Main.java`. The included settings use `src/` for source files and `bin/` for compiled classes.

## Usage

1. Select **Add Truck**, choose a truck type, and enter its details.
2. Select **View Information** to display the stored trucks.
3. Select **Remove Truck** and enter an ID from the table.
4. Select **Load Sample Data** on an empty yard to fill it with five example trucks.

## Current limitations

This coursework project has basic input handling. Negative values are not rejected, and canceling some input dialogs can display an error. Yard removal errors are caught and printed inside `TruckYard`, and its `printTrucks()` method currently discards the returned information. The Swing interface is created on the main thread.
