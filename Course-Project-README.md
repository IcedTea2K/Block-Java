# Block Java

## Description
Block Java is an application that uses *literal* code blocks to give visualization to programming. Similar to [scratch](https://scratch.mit.edu/about), it is a tool to help beginners approach programming without being overwhelmed by the syntax and language structure. Block Java lets the users drag-and-drop code blocks into the main view to build a simple game. Another feature that makes the application *java-centric* is that it "translates" the code blocks into Java in another view. It therefore allows the user learn the basics of Java syntax, while having fun building a simple game. 

This application is aimed toward high school students who want to get more experience in computer science. As the interface provides an easy way to learn programming, it also teaches the basics of a very practical programming language, Java, which allows the students to later start a project in the language without the application.

## Motivation
There are multiple factors that inspire the project. Firstly, scratch helped me get into programming when I was in high school. It let me build games which I'd always dreamed of doing. Though, scratch doesn't teach languages like Java which are necessary to build larger and more complex projects. So, I want to create a similar tool with some extension to help younger people to get into programming and gain practical experience at the same time. Next, I believe this project will help gain a better insight to how a programming language works. The current plan is to have a very simple interpreter that translate the code blocks into Java code for execution. But, it still requires the most necessary elements of a programming language (such as *grammars*, code generator, etc.). As such, I will hopefully gain more understanding about the backend of a coding language.

## User Stories
### Phase 0
- As a user, I want to be able to add commands to the translator, run them and get the results back.
- As a user, I want to be able to view all the commands that I have input
- As a user, I want to be able to remove a specific command (using index)
- As a user, I want to be able to clear all the input commands from the translator
- As a user, I want to view the list of commands the translator supports
- As a user, I want to get more information about a command
- As a user, I want to the translator to translate the commands into Java.
### Phase 2
- As a user, I want to have the option to save my program or not when I quit the Block Java
- As a user, I want to have the option to load my saved program or create a new one when I start Block Java
- As a user, I want to have the option to manually fix my saved program when the file is corrupted or discard it completely and create a new one. 
### Phase 3: Instructions for Grader
- The visual component is the splash screen. It can be seen when the program first starts
- To add a new Command to the Translator, drag one of the command label into the translator view.
- To remove a command label, press the delete button to enter delete mode (indicated by the red color). Then, click on any label in the translator view to delete them. Press the delete button again to exit the delete mode. 
- To add input to the command, first drag one of the label into the translator view. Then, add the input to the text fields. Pay attention to the terminal view for any error message.
- `Execute` button will execute the commands. The results (or error) will be shown in the terminal view.
- `Java` button will translate the commands into Java. It can be viewed in the Java view. Note that if any of the input is not valid, it will not run successfully.
- `Save` button will save the current program. Note that if any of the input is not valid, it will not save successfully. Also, the saved progress will be lost **without warning**.
- `Load` button will load the saved program. Note that any commands in the current program will be lost **without warning**.
### Phase 4: Task 2
Here is a sample of the logged events in the application: 
```text
Sat Apr 08 21:18:03 PDT 2023
New translator is ready.
Sat Apr 08 21:18:08 PDT 2023
3 and 2 have been added to SUB Command
Sat Apr 08 21:18:08 PDT 2023
4 and 3 have been added to ADD Command
Sat Apr 08 21:18:08 PDT 2023
52 and 1 have been added to DIV Command
Sat Apr 08 21:18:08 PDT 2023
1010 and 10 have been added to MUL Command
Sat Apr 08 21:18:08 PDT 2023
4 and 2 have been added to DIV Command
Sat Apr 08 21:18:11 PDT 2023
New translator is ready.
Sat Apr 08 21:18:11 PDT 2023
SUB Command has been added to the translator.
Sat Apr 08 21:18:11 PDT 2023
ADD Command has been added to the translator.
Sat Apr 08 21:18:11 PDT 2023
DIV Command has been added to the translator.
Sat Apr 08 21:18:11 PDT 2023
MUL Command has been added to the translator.
Sat Apr 08 21:18:11 PDT 2023
DIV Command has been added to the translator.
Sat Apr 08 21:18:11 PDT 2023
The stream has been translated to Java.
Sat Apr 08 21:18:13 PDT 2023
New translator is ready.
Sat Apr 08 21:18:13 PDT 2023
SUB Command has been added to the translator.
Sat Apr 08 21:18:13 PDT 2023
ADD Command has been added to the translator.
Sat Apr 08 21:18:13 PDT 2023
DIV Command has been added to the translator.
Sat Apr 08 21:18:13 PDT 2023
MUL Command has been added to the translator.
Sat Apr 08 21:18:13 PDT 2023
DIV Command has been added to the translator.
Sat Apr 08 21:18:13 PDT 2023
The stream has been executed.
Sat Apr 08 21:18:16 PDT 2023
New translator is ready.
Sat Apr 08 21:18:16 PDT 2023
SUB Command has been added to the translator.
Sat Apr 08 21:18:16 PDT 2023
ADD Command has been added to the translator.
Sat Apr 08 21:18:16 PDT 2023
DIV Command has been added to the translator.
Sat Apr 08 21:18:16 PDT 2023
MUL Command has been added to the translator.
Sat Apr 08 21:18:16 PDT 2023
DIV Command has been added to the translator.
Sat Apr 08 21:18:16 PDT 2023
The stream has been executed.
Sat Apr 08 21:18:16 PDT 2023
The result from the translator has been returned.
```
### Phase 4: Task 3
Having learned new design patterns in class, there are a few changes that I will make to the project if I had more time. Firstly, the structure of the commands and input. If I want to use the result of one command as input of another command, the current design will not work well because `Command` and `DataType` are two unrelated class. As such, I could refactor it into a Composite pattern where Command and DataType originate from the same class, say `Result` for example. This will allow the users to nest the commands together. <br>
Some other changes I would make will occur in the `GraphicalApplication` class. Currently, there is a lot of coupling between the buttons and the GUI, as a result of passing `gui` around. Instead, I could implement Singleton design to have a single access point for all the different components of the program. Or, I could also refactor the code base into Observer pattern, which will allow the GUI to keep track of the state of its components (buttons) and act accordingly when the state changes. Moreover, the current implementation of the buttons is too messy and unnecessary. Instead of having a separate class for each button, I could have an anonymous class for each functionalities. These anonymous classes can also be grouped together to allow easier future modification. It will also make the code base much more organized and better to work with.
## Credits
- To check whether the empty is empty, I took inspiration from a similar question asked on Stack Overflow
  - source: https://stackoverflow.com/questions/7190618/most-efficient-way-to-check-if-a-file-is-empty-in-java-on-windows
- Similarly, I took inspiration from Stack Overflow on how to override `Object.equals(obj)` and `Object.hasCode()`
  - source: https://stackoverflow.com/questions/8180430/how-to-override-equals-method-in-java
- Since the autograder does not support Java 7, I had to look for another way to read from the file other than Stream, and Paths. A post on Stack Overflow once again helped me with the process
  - source: https://stackoverflow.com/questions/4716503/reading-a-plain-text-file-in-java
- To write to the file, the example code from JsonSerializationDemo helped me get a general idea of the syntax and process.
  - To be more specific, the code snippet locates in `JsonWriter` which is in the persistence package of the project.
- The implementation of drag-and-drop operation takes great inspiration from a Stack Overflow thread. More specifically, how the drag handler and drop handler are separated are quite similar.
  - source: https://stackoverflow.com/questions/11201734/java-how-to-drag-and-drop-jpanel-with-its-components/11443501#11443501
- To update the user as they input, a DocumentListener needs to be implemented. As such, another Stack Overflow thread gives me inspiration on the process.
  - source: https://stackoverflow.com/questions/3953208/value-change-listener-to-jtextfield
- The SimpleDrawingPlayer starter from the course helps me implement the buttons in the program. Especially, how each button is separated into a class with a customized functionality. 
