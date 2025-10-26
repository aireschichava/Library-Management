# Library Management System
> **Note:** This project is a Java-based library management system designed for academic purposes. It was developed in a group of 2 for the Subject *Object-Oriented Programming (OOP)*  at Instituto Superior Técnico, University of Lisbon. It allows you to manage a library’s collection, users, and loan operations with extensibility in mind.

## Features
- Register and manage users (with behavior tracking)
- Register and manage works (books, DVDs, etc.)
- Handle loans, returns, and fines
- Notification system for users
- Search and inventory management


## Technologies

- Java (JDK 22)
- PO-uilib (UI library)
- Makefile (for build automation)


### PO-uilib Dependency (JAR Extraction)

This project requires the PO-uilib library for its user interface.

**How to set up PO-uilib:**
1. Download or obtain `po-uilib.tar` from this repository.
2. Extract the JAR to the parent directory of the project:
  ```sh
  tar -xvf po-uilib.tar -C ..
  ```
  This will place `po-uilib.jar` one directory above the project root, as required.
3. Ensure your `CLASSPATH` includes the path to `po-uilib.jar` when compiling and running the project.

**Example:**
```sh
# Compile (from inside the project root):
javac -cp ../po-uilib.jar:. bci-core/src/bci/*.java

# Run:
java -cp ../po-uilib.jar:. bci.app.App
```
## Getting Started
1. Ensure PO-uilib is available one directory above the project root.
2. Build the project using the provided Makefile or your preferred build tool.
3. Run the application as described in your course or project instructions.

## Importing Data
- You can start the application with a text file containing initial data using the Java property:
  ```
  java -Dimport=yourfile.import bci.app.App
  ```
- The import file supports users, books, and DVDs in the following formats:
  - `USER:name:email`
  - `BOOK:title:authors:price:category:ISBN:copies`
  - `DVD:title:director:price:category:IGAC:copies`

## Project Structure
- `bci-core/` — Core logic and models
- `bci-app/` — Application and UI
- `auto-tests/` — Automated test cases

## Testing
- Passed all public tests
- Passed 96.17% of private tests


## License
This project is for educational purposes.


## Authors
- Aires Nélio Chichava
- Alexandre Miguel Aires

