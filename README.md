## Getting Started

An Java based assembler program for the Pep9 assembly language. Compiles Pep9 instructions to machine code to be run in Pep9 application (Download here: https://computersystemsbook.com/5th-edition/pep9/). Based on "Computer Systems" courses and book by J. Stanley Warford at Peperdine University. For learning proposes only.

## Folder Structure
- `docs`: documenation for program
  - `diagrams`: UML diagram docs   
- `lib`: maintains dependencies
- `src`: maintains sources
  - `main`: maintains non-testing sources
    - `controller`: source for GUI controller 
    - `model`: source for program backend.
      - `lexicalanalyzer`: source for tokenization stage
      -  `parser`: source for instruction parsing stage
      -  `utility`: source for static utitily functions and variables for use across project.
    - `view`: source for GUI UI
  -  `test`: maintains unit testing 

Compiled output files will be generated in the `bin` folder by default.

## Dependency Management

- `JavaFx`: Framework for java based GUI applications. Download here https://gluonhq.com/products/javafx/
- `Junit`: Unit testing framework for java projects. Download here https://search.maven.org/search?q=g:org.junit.platform%20AND%20v:1.7.2
