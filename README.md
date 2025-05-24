<h1>🖥️☕ Simple Interpreter ☕🖥️</h1>

<p>
This is an interpreter written in Java for my custom programming language, which I named "Simple." The Simple language supports variables, if statements, loops, and more.
</p>

<h2>Demo 📹:</h2>

<a href="https://youtu.be/2GUfb232a9I">
  Click here for a video demo 📹
</a>

<h2>How You Can Run It 🏃:</h2>

Inside the executable folder, you’ll find `SimpleInterpreter.jar`. This JAR file can be used to run my interpreter. Since the interpreter is built in Java, you’ll need Java installed on your system.

After downloading `SimpleInterpreter.jar`, open a terminal in the same folder as the JAR file and run the following command:
```
java -jar SimpleInterpreter.jar
```

The interpreter will prompt you for the name of the `.simple` file you want to run. This repository includes several example programs you can try: `main.simple`, `greeting.simple`, `wordGuesser.simple`, and `hangman.simple`.

<h2>Simple Programming Language Features 🖥️: </h2>

<h3>Variables: </h3>

There are three types of variables in Simple:
- `num` – a number
- `text` – a string of text
- `bool` – a boolean value (`TRUE` or `FALSE`)

Variables are declared by writing the type, the variable name, an equals sign, and an expression.

Here are some examples:
```
text hello = ( "hi" );
bool yes = ( TRUE );
num age = ( 18 );
```

⚠️ Note: Parentheses are required around expressions. They tell the interpreter that the value should or could be evaluated. Spaces are also required.

Text values must be wrapped in quotation marks (e.g., `"hi"`).

Every statement ends with a semicolon (`;`).

<h3>If Statements: </h3>

Here’s an example of an if statement:

```
num age = ( 30 );
if ( age > 65 ); then;
  printLine ( "Hello elderly person!" );
  end;
else; then;
  printLine ( "Hello young person!" );
  end;
```

⚠️ Be sure to include the parentheses in the expression after the `if` keyword, as well as spaces.

Use `then;` to begin the if block and `end;` to close it. Optionally, you can include an `else; then;` block, which must also be closed with `end;`.

<h3>Repeat Loops: </h3>

Here’s an example of a repeat loop:

```
repeat ( 10 ) i; then;
  printLine ( i );
  end;
```

After `repeat` and an expression defining the number of times to loop, you define an index variable (e.g., `i`) that keeps track of the iteration count. Everything between `then;` and `end;` is inside the loop.

<h3>Print Statements: </h3>

Use `print` and `printLine` to display text in the console. `print` does not add a new line, `while` printLine does.

Example:

```
print ( "hello" );
printLine ( "Hi" );
```

<h3>User Input: </h3>

Use `input` to get user input from the console. Assign the input to a text variable. The content within the given text variable will be replaced with the user input.

Example:

```
text name = ( "" );

print ( "Type your name: " );
input name;

printLine ( "Hello " + name + "!" );
```

<h3>Characters Within Strings: </h3>

Use `characterAt` to get a specific character from a string. Provide:
- The variable to store the result
- The index of the character as an expression
- The text to extract from as an expression

Example:
```
text name = ( "Daniel" );
text character = ( "" );

characterAt character ( 0 ) ( name );

printLine ( "The first letter of your name is: " + character );
```

<h3>Exiting: </h3>

Use the `exit` keyword to immediately terminate the program.

In this example, "Hello" will not be printed as the program is terminated before the print statement:

```
exit;

printLine ( "Hello" );
```
