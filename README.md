<h1>🖥️☕ Simple Interpreter ☕🖥️</h1>

<p>
  This is an interpreter written in Java for my custom programming language, which I named "Simple." My Simple programming language contains variables, if statements, loops and more.
</p>

<h2>Demo 📹:</h2>

<a href="https://youtu.be/2GUfb232a9I">
  Click here for a video demo 📹
</a>

<h2>How You Can Run It 🏃:</h2>

Inside the executable folders, you will find SimpleInterpreter.jar. This jar can be used to run my interpeter. This interpreter was built in java, so you need Java installed in order to use this interpreter. After downloading SimpleInterpreter.java, open a terminal in the same folder as the jar. In that terminal run the following command: 
```
java -jar SimpleInterpreter.java
```

The interpreter will ask you for the name of the .simple file you want to interpret. This repository has the following example .simple programs that you can run: main.simple, greeting.simple, wordGuesser.simple, and hangman.simple. 

<h2>Simple Programming Language Features 🖥️: </h2>

<h3>Variables: </h3>

There are three types of variables in the Simple programming language:
- num
- text
- bool

num is Number; text is text or a string; bool is a boolean (TRUE or FALSE).

Variables are declared by writing the variable type, the name of the variable, an equals sign, and then a expression.

Below are example of each variable: 
```
text hello = ( "hi" );
bool yes = ( TRUE );
num age = ( 18 );
```

⚠️ Note: It is important to know that the parenthesis are required when expressing data. The parathensis indicate to the interpreter that the following is an expression of information that may need to be evaluated. The spaces are also required.

When writing text, be sure to wrap it within quotation marks (ex: "hi"). 

Every statement is ended with a semicolon (;).

<h3>If Statements: </h3>

Below is an example of an if statement:

```
num age = ( 30 );
if ( age > 65 ); then;
  printLine ( "Hello elderly person!" );
  end;
else; then;
  printLine ( "Hello young person!" );
  end;
```

Again, please be aware that the paranthesis after the if statement is required. Again, the spaces are required.

After the "if" keyword and expression, there should be "then;" and "end;" statements. Anything between these statements is within the if statement. After the "end;" statement you can use the "else;" statement followed by a "then;" statement to create an else clause in the if statement. Be sure to close the else with a "end;" statement.

<h3>Repeat Loops: </h3>

Below is an example of a repeat loop:

```
repeat ( 10 ) i; then;
  printLine ( i );
  end;
```

After the repeat statement and an expression describing the number of times the loop should repeat, you must declare an index variable. This index variable holds the number of times the loop as run. Anything wrapped within the "then;" and "end;" statements is within the repeat loop.

<h3>Print Statements: </h3>

"print" and "printLine" both allow you to print to the console. "print" prints the given text without creating a new line. "printLine" prints the given text and creates a new line.

Below are examples of "print" and "printLine":

```
print ( "hello" );
printLine ( "Hi" );
```

<h3>User Input: </h3>

You can get user input from the console by using "input." After the "input" keyword, provide a text variable whose contents will be set to the user input.

Below is an example:

```
text name = ( "" );

print ( "Type your name: " );
input name;

printLine ( "Hello " + name + "!" );
```

<h3>Characters Within String: </h3>

You can get a specific character within a string using "characterAt." There is three parts to characterAt. After the "characterAt" keyword, provide a text variable whose content will be replaced with the character. After that, provide an expression which describes the index of the character you want to get. Finally, provide an expression of the text you want to select the character from. 

Below is an example:

```
text name ( "Daniel" );
text character = ( "" );

characterAt character ( 0 ) ( name );

printLine ( "The first letter of your name is: " + character );
```

<h3>Exiting: </h3>

The "exit" key word allows you to immediately terminate the program.

In the below example, "Hello" will not be printed since "exit" stops the program before the print statement.

```
exit;

printLine ( "Hello" );
```
