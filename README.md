# Library Management System

This project is a Java-based library management system designed for academic purposes. It allows you to manage a library’s collection, users, and loan operations with extensibility in mind.

## Features
- Register and manage users (with behavior tracking)
- Register and manage works (books, DVDs, etc.)
- Handle loans, returns, and fines
- Notification system for users
- Search and inventory management

## Technologies
- Java
- PO-uilib (UI library, required one directory above project root)

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

## Testing

- Passed all public tests
- Passed 96.17% of private tests

