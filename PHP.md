# PHP

## Tipos

### String

- ***Heredoc:*** After this operator, an identifier is provided, then a newline. The string itself follows, and then the same identifier again to close the quotation.

  ```php
  echo <<<EOT
  My name is "$name". I am printing some $foo->foo.
  Now, I am printing some {$foo->bar[1]}.
  This should print a capital 'A': \x41
  EOT;
  ```

- ***Nowdocs :***are to single-quoted strings what heredocs are to double-quoted strings. A nowdoc is specified similarly to a heredoc, but no parsing is done inside a nowdoc. The construct is ideal for embedding PHP code or other large blocks of text without the need for escaping.

```php
echo <<<'EOT'
My name is "$name". I am printing some $foo->foo.
Now, I am printing some {$foo->bar[1]}.
This should not print a capital 'A': \x41
EOT;
```

- ***valiable parse*** 

  - Simple syntax: If a dollar sign ($) is encountered

  ```php
  // Simple syntax
  echo "He drank some $juice juice.".PHP_EOL;
  echo "He drank some $juices[0] juice.".PHP_EOL;
  ```

  -  Complex: it allows for the use of complex expressions.

  ```php
  $great = 'fantastic';
  
  echo "This is { $great}";
  echo "This is {$great}";
  echo "This square is {$square->width}00 centimeters broad.";
  echo "This works: {$arr['key']}";
  ```

Array

you can also use the short array syntax, which replaces array() with [].

```php
<?php
$array = array(
    "foo" => "bar",
    "bar" => "foo",
);

// as of PHP 5.4
$array = [
    "foo" => "bar",
    "bar" => "foo",
];
?>
```

It is possible to specify the key only for some elements and leave it out for others:

```php
<?php
$array = array(
         "a",
         "b",
    6 => "c",
         "d",
);
var_dump($array);
?>

// result
array(4) {
  [0]=>
  string(1) "a"
  [1]=>
  string(1) "b"
  [6]=>
  string(1) "c"
  [7]=>
  string(1) "d"
}
```

Accessing array elements

```php
<?php
$array = array(
    "foo" => "bar",
    42    => 24,
    "multi" => array(
         "dimensional" => array(
             "array" => "foo"
         )
    )
);

var_dump($array["foo"]);
var_dump($array[42]);
var_dump($array["multi"]["dimensional"]["array"]);
?>
```

 it is possible to array dereference an array literal.

```php
<?php
function getArray() {
    return array(1, 2, 3);
}
// on PHP 5.4
$secondElement = getArray()[1];

// previously
$tmp = getArray();
$secondElement = $tmp[1];

// or
list(, $secondElement) = getArray();
?>
```

**Creating/modifying with square bracket syntax**

```php
$arr[key] = value;
$arr[] = value; 
// If $arr doesn't exist yet, it will be created, so this is also an alternative way to create an array

<?php
$arr = array(5 => 1, 12 => 2);

$arr[] = 56;    // This is the same as $arr[13] = 56;
                // at this point of the script

$arr["x"] = 42; // This adds a new element to
                // the array with key "x"
                
unset($arr[5]); // This removes the element from the array

unset($arr);    // This deletes the whole array
?>
```



### Object

**Converting to object**

```php
<?php
$obj = (object) array('1' => 'foo');
var_dump(isset($obj->{'1'})); // outputs 'bool(true)' as of PHP 7.2.0; 'bool(false)' previously
var_dump(key($obj)); // outputs 'string(1) "1"' as of PHP 7.2.0; 'int(1)' previously
?>
```

For any other value, a member variable named scalar will contain the value.

```php
<?php
$obj = (object) 'ciao';
echo $obj->scalar;  // outputs 'ciao'
?>
```



### Resources

A resource is a special variable, holding a reference to an external resource. Resources are created and used by special functions. See the appendix for a listing of all these functions and the corresponding resource types.

See also the `get_resource_type() ` function.



### Callbacks / Callables

Callbacks can be denoted by callable type hint as of PHP 5.4. This documentation used callback type information for the same purpose.

Some functions like `call_user_func() or usort()` accept user-defined callback functions as a parameter. Callback functions can not only be simple functions, but also object methods, including static class methods.

*Passing*

```php
// An example callback function
function my_callback_function() {
    echo 'hello world!';
}

// An example callback method
class MyClass {
    static function myCallbackMethod() {
        echo 'Hello World!';
    }
}

// Type 1: Simple callback
call_user_func('my_callback_function');

// Type 2: Static class method call
call_user_func(array('MyClass', 'myCallbackMethod'));

// Type 3: Object method call
$obj = new MyClass();
call_user_func(array($obj, 'myCallbackMethod'));

// Type 4: Static class method call (As of PHP 5.2.3)
call_user_func('MyClass::myCallbackMethod');

// Type 5: Relative static class method call (As of PHP 5.3.0)
class A {
    public static function who() {
        echo "A\n";
    }
}
```

Callback example using a Closure

```php
// Our closure
$double = function($a) {
    return $a * 2;
};

// This is our range of numbers
$numbers = range(1, 5);

// Use the closure as a callback here to
// double the size of each element in our
// range
$new_numbers = array_map($double, $numbers);

print implode(' ', $new_numbers);
```



### Pseudo-types and variables used in this documentation

Pseudo-types are keywords used in the PHP documentation to specify the types or values an argument can have. Please note that they are not primitives of the PHP language. So you cannot use pseudo-types as typehints in your own custom functions.

***mixed***

mixed indicates that a parameter may accept multiple (but not necessarily all) types.

gettype() for example will accept all PHP types, while str_replace() will accept strings and arrays.

***number***

number indicates that a parameter can be either integer or float.

***callback***

callback pseudo-types was used in this documentation before callable type hint was introduced by PHP 5.4. It means exactly the same.

***array|object***

array|object indicates that a parameter can be either array or object.

***void***

void as a return type means that the return value is useless. void in a parameter list means that the function doesn't accept any parameters. As of PHP 7.1 void is accepted as a function return type hint.

***...***

$... in function prototypes means and so on. This variable name is used when a function can take an endless number of arguments.



### Type Juggling

PHP does not require (or support) explicit type definition in variable declaration; a variable's type is determined by the context in which the variable is used. That is to say, if a string value is assigned to variable $var, $var becomes a string. If an integer value is then assigned to $var, it becomes an integer.

***Type Casting***

Type casting in PHP works much as it does in C: the name of the desired type is written in parentheses before the variable which is to be cast.

***The casts allowed are:***

- (int), (integer) - cast to integer
- (bool), (boolean) - cast to boolean
- (float), (double), (real) - cast to float
- (string) - cast to string
- (array) - cast to array
- (object) - cast to object
- (unset) - cast to NULL

The (unset) cast has been deprecated as of PHP 7.2.0.

ejmplos:

```php
$foo = 10;   // $foo is an integer
$bar = (boolean) $foo;   // $bar is a boolean

$binary = (binary) $string;
$binary = b"binary string";

$foo = 10;            // $foo is an integer
$str = "$foo";        // $str is a string
$fst = (string) $foo; // $fst is also a string

// This prints out that "they are the same"
if ($fst === $str) {
    echo "they are the same";
}
```

## Variables

### Basic

Variables in PHP are represented by a dollar sign followed by the name of the variable. The variable name is case-sensitive.

```php
$var = 'Bob';
$Var = 'Joe';
echo "$var, $Var";      // outputs "Bob, Joe"

$4site = 'not yet';     // invalid; starts with a number
$_4site = 'not yet';    // valid; starts with an underscore
$täyte = 'mansikka';    // valid; 'ä' is (Extended) ASCII 228.
```

PHP also offers another way to assign values to variables: assign by reference. This means that the new variable simply references (in other words, "becomes an alias for" or "points to") the original variable. Changes to the new variable affect the original, and vice versa.

To assign by reference, simply prepend an ampersand (&)  to the beginning of the variable which is being assigned

```php
$foo = 'Bob';              // Assign the value 'Bob' to $foo
$bar = &$foo;              // Reference $foo via $bar.
$bar = "My name is $bar";  // Alter $bar...
echo $bar;
echo $foo;                 // $foo is altered too.
```



### Variable scope

The scope of a variable is the context within which it is defined. For the most part all PHP variables only have a single scope. This single scope spans included and required files as well.

***Global***

The above script will output 3. By declaring $a and $b global within the function, all references to either variable will refer to the global version. There is no limit to the number of global variables that can be manipulated by a function.

```php
$a = 1;
$b = 2;

function Sum()
{
    global $a, $b;

    $b = $a + $b;
} 
```

A second way to access variables from the global scope is to use the special PHP-defined $GLOBALS array

```php
$a = 1;
$b = 2;

function Sum()
{
    $GLOBALS['b'] = $GLOBALS['a'] + $GLOBALS['b'];
} 

Sum();
echo $b;
```

***Using static variables***

Another important feature of variable scoping is the static variable. A static variable exists only in a local function scope, but it does not lose its value when program execution leaves this scope. Consider the following example:

```php
function test()
{
    $a = 0;
    echo $a;
    $a++;
}
```

This function is quite useless since every time it is called it sets $a to 0 and prints 0. The $a++ which increments the variable serves no purpose since as soon as the function exits the $a variable disappears. To make a useful counting function which will not lose track of the current count, the $a variable is declared static:

Example use of static variables

```php
function test()
{
    static $a = 0;
    echo $a;
    $a++;
}
```

Now, $a is initialized only in first call of function and every time the test() function is called it will print the value of $a and increment it.

Static variables also provide one way to deal with recursive functions. A recursive function is one which calls itself. Care must be taken when writing a recursive function because it is possible to make it recurse indefinitely. You must make sure you have an adequate way of terminating the recursion. The following simple function recursively counts to 10, using the static variable $count to know when to stop:

Example #6 Static variables with recursive function

```php
function test()
{
    static $count = 0;

    $count++;
    echo $count;
    if ($count < 10) {
        test();
    }
    $count--;
}
```

***References with global and static variables***

PHP implements the static and global modifier for variables in terms of references. For example, a true global variable imported inside a function scope with the global statement actually creates a reference to the global variable. This can lead to unexpected behaviour which the following example addresses:



### Variable variables

Sometimes it is convenient to be able to have variable variable names. That is, a variable name which can be set and used dynamically. A normal variable is set with a statement such as:

```php
$a = 'hello';
```

A variable variable takes the value of a variable and treats that as the name of a variable. In the above example, hello, can be used as the name of a variable by using two dollar signs. i.e.

```php
$$a = 'world';
```

At this point two variables have been defined and stored in the PHP symbol tree: $a with contents "hello" and $hello with contents "world". Therefore, this statement:

`echo "$a ${$a}";`

`echo "$a $hello";`



### Variables From External Sources

***HTML Forms (GET and POST)***

When a form is submitted to a PHP script, the information from that form is automatically made available to the script. There are few ways to access this information, for example:

```html
<form action="foo.php" method="post">
    Name:  <input type="text" name="username" /><br />
    Email: <input type="text" name="email" /><br />
    <input type="submit" name="submit" value="Submit me!" />
</form>
```

As of PHP 5.4.0, there are only two ways to access data from your HTML forms. Currently available methods are listed below:

```php
echo $_POST['username'];
echo $_REQUEST['username'];
```

***HTTP Cookies***

PHP transparently supports HTTP cookies as defined by » RFC 6265. Cookies are a mechanism for storing data in the remote browser and thus tracking or identifying return users. You can set cookies using the setcookie() function. Cookies are part of the HTTP header, so the SetCookie function must be called before any output is sent to the browser. Cookie data is then available in the appropriate cookie data arrays, such as $_COOKIE

If you wish to assign multiple values to a single cookie variable, you may assign it as an array. For example:

```php
 setcookie("MyCookie[foo]", 'Testing 1', time()+3600);
 setcookie("MyCookie[bar]", 'Testing 2', time()+3600);
```

```php
if (isset($_COOKIE['count'])) {
    $count = $_COOKIE['count'] + 1;
} else {
    $count = 1;
}
setcookie('count', $count, time()+3600);
setcookie("Cart[$count]", $item, time()+3600);
```

***Determining variable types***

Because PHP determines the types of variables and converts them (generally) as needed, it is not always obvious what type a given variable is at any one time. PHP includes several functions which find out what type a variable is, such as: gettype(), is_array(), is_float(), is_int(), is_object(), and is_string(). See also the chapter on Types.

HTTP being a text protocol, most, if not all, content that comes in Superglobal arrays, like $_POST and $_GET will remain as strings. PHP will not try to convert values to a specific type. In the example below, $_GET["var1"] will contain the string "null" and $_GET["var2"], the string "123".

/index.php?var1=null&var2=123



## Constants

A constant is an identifier (name) for a simple value. As the name suggests, that value cannot change during the execution of the script (except for magic constants, which aren't actually constants). A constant is case-sensitive by default. By convention, constant identifiers are always uppercase.

It is possible to define() constants with reserved or even invalid names

```php
// Valid constant names
define("FOO",     "something");
define("FOO2",    "something else");
define("FOO_BAR", "something more");

// Invalid constant names
define("2FOO",    "something");

// This is valid, but should be avoided:
// PHP may one day provide a magical constant
// that will break your script
define("__FOO__", "something"); 
```

```php
<?php
// Works as of PHP 5.3.0
const CONSTANT = 'Hello World';

echo CONSTANT;

// Works as of PHP 5.6.0
const ANOTHER_CONST = CONSTANT.'; Goodbye World';
echo ANOTHER_CONST;

const ANIMALS = array('dog', 'cat', 'bird');
echo ANIMALS[1]; // outputs "cat"

// Works as of PHP 7
define('ANIMALS', array(
    'dog',
    'cat',
    'bird'
));
echo ANIMALS[1]; // outputs "cat"
?>
```

### Magic constants

PHP provides a large number of predefined constants to any script which it runs. Many of these constants, however, are created by various extensions, and will only be present when those extensions are available, either via dynamic loading or because they have been compiled in.

`__LINE__`	The current line number of the file.

`__FILE__`	The full path and filename of the file with symlinks resolved. If used inside an include, the name of the included file is returned.



## Operators

***Arithmetic Operators***

Example	Name	Result
+$a	Identity	Conversion of $a to int or float as appropriate.
-$a	Negation	Opposite of $a.
$a + $b	Addition	Sum of $a and $b.
$a - $b	Subtraction	Difference of $a and $b.
$a * $b	Multiplication	Product of $a and $b.
$a / $b	Division	Quotient of $a and $b.
$a % $b	Modulo	Remainder of $a divided by $b.
$a ** $b	Exponentiation	Result of raising $a to the $b'th power. Introduced in PHP 5.6.

***Error Control Operators***

PHP supports one error control operator: the at sign (@). When prepended to an expression in PHP, any error messages that might be generated by that expression will be ignored.

```php
/* Intentional file error */
$my_file = @file ('non_existent_file') or
    die ("Failed opening file: error was '$php_errormsg'");

// this works for any expression, not just functions:
$value = @$cache[$key];
// will not issue a notice if the index $key doesn't exist.
```

***Execution Operators***

PHP supports one execution operator: backticks (``). Note that these are not single-quotes! PHP will attempt to execute the contents of the backticks as a shell command; the output will be returned (i.e., it won't simply be dumped to output; it can be assigned to a variable). Use of the backtick operator is identical to shell_exec().

```php
<?php
$output = `ls -al`;
echo "<pre>$output</pre>";
?>
```

***Incrementing/Decrementing Operators***

PHP supports C-style pre- and post-increment and decrement operators.

```html
Example	Name			Effect
++$a	Pre-increment	Increments $a by one, then returns $a.
$a++	Post-increment	Returns $a, then increments $a by one.
--$a	Pre-decrement	Decrements $a by one, then returns $a.
$a--	Post-decrement	Returns $a, then decrements $a by one.
```

Here's a simple example script:

```php
<?php
echo "<h3>Postincrement</h3>";
$a = 5;
echo "Should be 5: " . $a++ . "<br />\n";
echo "Should be 6: " . $a . "<br />\n";

echo "<h3>Preincrement</h3>";
$a = 5;
echo "Should be 6: " . ++$a . "<br />\n";
echo "Should be 6: " . $a . "<br />\n";

echo "<h3>Postdecrement</h3>";
$a = 5;
echo "Should be 5: " . $a-- . "<br />\n";
echo "Should be 4: " . $a . "<br />\n";

echo "<h3>Predecrement</h3>";
$a = 5;
echo "Should be 4: " . --$a . "<br />\n";
echo "Should be 4: " . $a . "<br />\n";
?>
```

***Type Operators***

instanceof is used to determine whether a PHP variable is an instantiated object of a certain class:

`var_dump($a instanceof MyClass);`

## Control Structures

### Alternative syntax for control structures

PHP offers an alternative syntax for some of its control structures; namely, if, while, for, foreach, and switch. In each case, the basic form of the alternate syntax is to change the opening brace to a colon (:) and the closing brace to endif;, endwhile;, endfor;, endforeach;, or endswitch;, respectively.

```php
<?php if ($a == 5): ?>
A is equal to 5
<?php endif; ?>

if ($a == 5):
    echo "a equals 5";
    echo "...";
elseif ($a == 6):
    echo "a equals 6";
    echo "!!!";
else:
    echo "a is neither 5 nor 6";
endif;
```

### FOR

Each of the expressions can be empty or contain multiple expressions separated by commas. 

In `expr2`, all expressions separated by a comma are evaluated but the result is taken from the last part. expr2 being empty means the loop should be run indefinitely (PHP implicitly considers it as TRUE, like C). This may not be as useless as you might think, since often you'd want to end the loop using a conditional break statement instead of using the for truth expression.

```php
<?php
/* example 1 */
for ($i = 1; $i <= 10; $i++) {
    echo $i;
}

/* example 2 */
for ($i = 1; ; $i++) {
    if ($i > 10) {
        break;
    }
    echo $i;
}

/* example 3 */
$i = 1;
for (; ; ) {
    if ($i > 10) {
        break;
    }
    echo $i;
    $i++;
}

/* example 4 */	
for ($i = 1, $j = 0; $i <= 10; $j += $i, print $i, $i++);

$people = array( array('name' => 'Kalle', 'salt' => 856412),
    array('name' => 'Pierre', 'salt' => 215863) );

for($i = 0, $size = count($people); $i < $size; ++$i) {
    $people[$i]['salt'] = mt_rand(000000, 999999);
}
?>
```

### break

break ends execution of the current for, foreach, while, do-while or switch structure.

### continue

continue is used within looping structures to skip the rest of the current loop iteration and continue execution at the condition evaluation and then the beginning of the next iteration.

### include, include_once, require

The *include* statement includes and evaluates the specified file. The include construct will emit a warning if it cannot find a file; this is different behavior from *require*, which will emit a fatal error.

```php
vars.php
<?php

$color = 'green';
$fruit = 'apple';

?>

test.php
<?php

echo "A $color $fruit"; // A

include 'vars.php';

echo "A $color $fruit"; // A green apple


// local filesystem.
include 'file.php?foo=1&bar=2';

// Works.
include 'http://www.example.com/file.php?foo=1&bar=2';
include 'file.txt';  // Works.
include 'file.php';  // Works.

// works
if ((include 'vars.php') == TRUE) {
    echo 'OK';
}

//first
$config = include_once("config.php");
var_dump($config);
```

## Function 

### Function arguments

PHP supports passing arguments by value (the default), passing by reference, and default argument values. Variable-length argument lists are also supported.

***Passing arguments by reference***

To have an argument to a function always passed by reference, prepend an ampersand (&) to the argument name in the function definition:

```php
function add_some_extra(&$string)
{
    $string .= 'and something extra.';
}
$str = 'This is a string, ';
add_some_extra($str);
echo $str;    // outputs 'This is a string, and something extra.'
```

***Default argument values***

A function may define C++-style default values for scalar arguments as follows:

```php
function makecoffee($type = "cappuccino")
{
    return "Making a cup of $type.\n";
}
echo makecoffee();
echo makecoffee(null);
echo makecoffee("espresso");
```

***Type declarations***

Type declarations allow functions to require that parameters are of a certain type at call time. If the given value is of the incorrect type, then an error is generated

```php
function f(C $c) {
    echo get_class($c)."\n";
}
function test(boolean $param) {}
function array_baz(array &$param)
{
    $param = 1;
}
```

***Variable-length argument lists***

PHP has support for variable-length argument lists in user-defined functions. This is implemented using the ... token in PHP 5.6 and later, and using the func_num_args(), func_get_arg(), and func_get_args() functions in PHP 5.5 and earlier.

```php
function sum(...$numbers) {
    $acc = 0;
    foreach ($numbers as $n) {
        $acc += $n;
    }
    return $acc;
}

echo sum(1, 2, 3, 4);

// ejmplo 2
function add($a, $b) {
    return $a + $b;
}

echo add(...[1, 2])."\n";

$a = [1, 2];
echo add(...$a);

// ejemplo 3
function total_intervals($unit, DateInterval ...$intervals) {
    $time = 0;
    foreach ($intervals as $interval) {
        $time += $interval->$unit;
    }
    return $time;
}

$a = new DateInterval('P1D');
$b = new DateInterval('P2D');
echo total_intervals('d', $a, $b).' days';

// This will fail, since null isn't a DateInterval object.
echo total_intervals('d', null);
```

### Return type declarations

 return type declarations specify the type of the value that will be returned from a function. The same types are available for return type declarations as are available for argument type declarations.

return values can be marked as nullable by prefixing the type name with a question mark (?). This signifies that the function returns either the specified type or NULL.

```php
function sum($a, $b): float {
    return $a + $b;
}

function sum($a, $b): int {
    return $a + $b;
}

class C {}

function getC(): C {
    return new C;
}

function get_item(): ?string {
    if (isset($_GET['item'])) {
        return $_GET['item'];
    } else {
        return null;
    }
}
```

### Variable functions

PHP supports the concept of variable functions. This means that if a variable name has parentheses appended to it, PHP will look for a function with the same name as whatever the variable evaluates to, and will attempt to execute it. Among other things, this can be used to implement callbacks, function tables, and so forth.

ejemplos

```php
function foo() {    echo "In foo()<br />\n";}

function bar($arg = ''){    echo "In bar(); argument was '$arg'.<br />\n";}

// This is a wrapper function around echo
function echoit($string){    echo $string;}

$func = 'foo';
$func();        // This calls foo()

$func = 'bar';
$func('test');  // This calls bar()

$func = 'echoit';
$func('test');  // This calls echoit()
```

```php
class Foo
{
    function Variable()
    {
        $name = 'Bar';
        $this->$name(); // This calls the Bar() method
    }
    
    function Bar()
    {
        echo "This is Bar";
    }
}

$foo = new Foo();
$funcname = "Variable";
$foo->$funcname();  // This calls $foo->Variable()
```

```php
class Foo
{
    static $variable = 'static property';
    static function Variable()
    {
        echo 'Method Variable called';
    }
}

echo Foo::$variable; // This prints 'static property'. It does need a $variable in this scope.
$variable = "Variable";
Foo::$variable();  // This calls $foo->Variable() reading $variable in this scope.
```

### Anonymous functions

Anonymous functions, also known as closures, allow the creation of functions which have no specified name. They are most useful as the value of callback parameters, but they have many other uses.

```php
echo preg_replace_callback('~-([a-z])~', function ($match) {
    return strtoupper($match[1]);
}, 'hello-world');
// outputs helloWorld
```

Example #2 Anonymous function variable assignment example

```php
$greet = function($name)
{
    printf("Hello %s\r\n", $name);
};

$greet('World');
$greet('PHP');
```

Example #3 Inheriting variables from the parent scope

```php
$message = 'hello';

// No "use"
$example = function () {
    var_dump($message);
};
$example();

// Inherit $message
$example = function () use ($message) {
    var_dump($message);
};
$example();
```

***Static anonymous functions***

anonymous functions may be declared statically. This prevents them from having the current class automatically bound to them. Objects may also not be bound to them at runtime.

```php
class Foo
{
    function __construct()
    {
        $func = static function() {
            var_dump($this);
        };
        $func();
    }
};
new Foo();

```



## Classes and Objects

### Class Constants

 Constants differ from normal variables in that you don't use the $ symbol to declare or use them. The default visibility of class constants is public.

```php
class MyClass
{
    const CONSTANT = 'constant value';

    function showConstant() {
        echo  self::CONSTANT . "\n";
    }
}

echo MyClass::CONSTANT . "\n";

$classname = "MyClass";
echo $classname::CONSTANT . "\n"; // As of PHP 5.3.0

$class = new MyClass();
$class->showConstant();

echo $class::CONSTANT."\n"; // As of PHP 5.3.0
```

### Scope Resolution Operator (::)

The Scope Resolution Operator (also called Paamayim Nekudotayim) or in simpler terms, the double colon, is a token that allows access to static, constant, and overridden properties or methods of a class.

```php
class MyClass {
    const CONST_VALUE = 'A constant value';
    parent::myFunc();
}
$classname = 'MyClass';
echo $classname::CONST_VALUE; // As of PHP 5.3.0
echo MyClass::CONST_VALUE;
```

### Static Keyword

Declaring class properties or methods as static makes them accessible without needing an instantiation of the class. A property declared as static cannot be accessed with an instantiated class object (though a static method can).

```php
class Foo {
    public static function aStaticMethod() {
        // ...
    }
}

Foo::aStaticMethod();
$classname = 'Foo';
$classname::aStaticMethod(); // As of PHP 5.3.0
```

***Static properties***

Static properties cannot be accessed through the object using the arrow operator ->.

Like any other PHP static variable, static properties may only be initialized using a literal or constant before PHP 5.6; expressions are not allowed. In PHP 5.6 and later, the same rules apply as const expressions:

```php
public static $my_static = 'foo';

    public function staticValue() {
        return self::$my_static;
    }
```

### Traits

Traits are a mechanism for code reuse in single inheritance languages such as PHP. A Trait is intended to reduce some limitations of single inheritance by enabling a developer to reuse sets of methods freely in several independent classes living in different class hierarchies. The semantics of the combination of Traits and classes is defined in a way which reduces complexity, and avoids the typical problems associated with multiple inheritance and Mixins.

A Trait is similar to a class, but only intended to group functionality in a fine-grained and consistent way. It is not possible to instantiate a Trait on its own. It is an addition to traditional inheritance and enables horizontal composition of behavior; that is, the application of class members without requiring inheritance.

Utilidas como los Mixins, son pedazos de códigos reutilizables pero no son clases

```php
<?php
trait ezcReflectionReturnInfo {
    function getReturnType() { /*1*/ }
    function getReturnDescription() { /*2*/ }
}

class ezcReflectionMethod extends ReflectionMethod {
    use ezcReflectionReturnInfo;
    /* ... */
}

class ezcReflectionFunction extends ReflectionFunction {
    use ezcReflectionReturnInfo;
    /* ... */
}
?>
```

Example #4 Multiple Traits Usage

```php
trait Hello {
    public function sayHello() {
        echo 'Hello ';
    }
}

trait World {
    public function sayWorld() {
        echo 'World';
    }
}

class MyHelloWorld {
    use Hello, World;
    public function sayExclamationMark() {
        echo '!';
    }
}

$o = new MyHelloWorld();
$o->sayHello();
$o->sayWorld();
$o->sayExclamationMark();
```

### Overloading

Overloading in PHP provides means to dynamically create properties and methods. These dynamic entities are processed via magic methods one can establish in a class for various action types.

The overloading methods are invoked when interacting with properties or methods that have not been declared or are not visible in the current scope. The rest of this section will use the terms inaccessible properties and inaccessible methods to refer to this combination of declaration and visibility

***Property overloading***

__set() is run when writing data to inaccessible (protected or private) or non-existing properties.

__get() is utilized for reading data from inaccessible (protected or private) or non-existing properties.

__isset() is triggered by calling isset() or empty() on inaccessible (protected or private) or non-existing properties.



### Magic Methods

__unset() is invoked when unset() is used on inaccessible (protected or private) or non-existing properties.

The function names `__construct()`, `__destruct()`, `__call()`, `__callStatic()`, `__get()`, `__set()`, `__isset()`, `__unset()`, `__sleep()`, `__wakeup()`, `__toString()`, `__invoke()`, `__set_state()`, `__clone()` and `__debugInfo()` are magical in PHP classes. You cannot have functions with these names in any of your classes unless you want the magic functionality associated with them.



### Object Serialization

serialize() returns a string containing a byte-stream representation of any value that can be stored in PHP. unserialize() can use this string to recreate the original variable values. Using serialize to save an object will save all variables in an object. The methods in an object will not be saved, only the name of the class.

In order to be able to unserialize() an object, the class of that object needs to be defined. That is, if you have an object of class A and serialize this, you'll get a string that refers to class A and contains all values of variables contained in it.

```php
<?php
// classa.inc:
  
  class A {
      public $one = 1;
    
      public function show_one() {
          echo $this->one;
      }
  }
  
// page1.php:

  include("classa.inc");
  
  $a = new A;
  $s = serialize($a);
  // store $s somewhere where page2.php can find it.
  file_put_contents('store', $s);

// page2.php:
  
  // this is needed for the unserialize to work properly.
  include("classa.inc");

  $s = file_get_contents('store');
  $a = unserialize($s);

  // now use the function show_one() of the $a object.  
  $a->show_one();
?>
```

## Namespaces

***Defining namespaces***

```php
<?php
namespace MyProject;

const CONNECT_OK = 1;
class Connection { /* ... */ }
function connect() { /* ... */ }

?>
```

****

***using***

```php
/* Fully qualified name */
\Foo\Bar\foo(); // resolves to function Foo\Bar\foo
\Foo\Bar\foo::staticmethod(); // resolves to class Foo\Bar\foo, method staticmethod
echo \Foo\Bar\FOO; // resolves to constant Foo\Bar\FOO

namespace MyProject;

use blah\blah as mine; // see "Using namespaces: Aliasing/Importing"

blah\mine(); // calls function MyProject\blah\mine()
namespace\blah\mine(); // calls function MyProject\blah\mine()

namespace\func(); // calls function MyProject\func()
namespace\sub\func(); // calls function MyProject\sub\func()
namespace\cname::method(); // calls static method "method" of class MyProject\cname
$a = new namespace\sub\cname(); // instantiates object of class MyProject\sub\cname
$b = namespace\CONSTANT; // assigns value of constant MyProject\CONSTANT to $b
```



## Errors

PHP reports errors in response to a number of internal error conditions. These may be used to signal a number of different conditions, and can be displayed and/or logged as required.

Every error that PHP generates includes a type. A list of these types is available, along with a short description of their behaviour and how they can be caused

## Exceptions

PHP has an exception model similar to that of other programming languages. An exception can be thrown, and caught ("catched") within PHP. Code may be surrounded in a try block, to facilitate the catching of potential exceptions. Each try must have at least one corresponding catch or finally block.

```php
<?php
function inverse($x) {
    if (!$x) {
        throw new Exception('Division by zero.');
    }
    return 1/$x;
}

try {
    echo inverse(5) . "\n";
    echo inverse(0) . "\n";
} catch (Exception $e) {
    echo 'Caught exception: ',  $e->getMessage(), "\n";
}

// Continue execution
echo "Hello World\n";
?>
```

***Extending Exceptions***

A User defined Exception class can be defined by extending the built-in Exception class. The members and properties below, show what is accessible within the child class that derives from the built-in Exception class.

```php
<?php
class Exception extends Throwable
{
    protected $message = 'Unknown exception';   // exception message
    private   $string;                          // __toString cache
    protected $code = 0;                        // user defined exception code
    protected $file;                            // source filename of exception
    protected $line;                            // source line of exception
    private   $trace;                           // backtrace
    private   $previous;                        // previous exception if nested exception

    public function __construct($message = null, $code = 0, Exception $previous = null);

    final private function __clone();           // Inhibits cloning of exceptions.

    final public  function getMessage();        // message of exception
    final public  function getCode();           // code of exception
    final public  function getFile();           // source filename
    final public  function getLine();           // source line
    final public  function getTrace();          // an array of the backtrace()
    final public  function getPrevious();       // previous exception
    final public  function getTraceAsString();  // formatted string of trace

    // Overrideable
    public function __toString();               // formatted string for display
}
?>
```



## References Explained

References in PHP are a means to access the same variable content by different names. They are not like C pointers; for instance, you cannot perform pointer arithmetic using them, they are not actual memory addresses, and so on. See What References Are Not for more information. Instead, they are symbol table aliases. Note that in PHP, variable name and variable content are different, so the same content can have different names. The closest analogy is with Unix filenames and files - variable names are directory entries, while variable content is the file itself. References can be likened to hardlinking in Unix filesystem

***Asign by reference***

```php
<?php
$a =& $b;
$foo =& find_var($bar);
?>
```

***Pass By Reference***

```php
<?php
function foo(&$var)
{
    $var++;
}

$a=5;
foo($a);
?>
```

***Returning References***

```php
<?php
class foo {
    public $value = 42;

    public function &getValue() {
        return $this->value;
    }
}

$obj = new foo;
$myValue = &$obj->getValue(); // $myValue is a reference to $obj->value, which is 42.
$obj->value = 2;
echo $myValue;                // prints the new value of $obj->value, i.e. 2.
?>
```



## Predefined Variables

***Superglobals***

Superglobals — Superglobals are built-in variables that are always available in all scopes

These superglobal variables are:

$GLOBALS
$_SERVER
$_GET
$_POST
$_FILES
$_COOKIE
$_SESSION
$_REQUEST
$_ENV

***$GLOBALS***

An associative array containing references to all variables which are currently defined in the global scope of the script. The variable names are the keys of the array.

```php
<?php

function test() {
    $foo = "local variable";

    echo '$foo in global scope: ' . $GLOBALS["foo"] . "\n";
    echo '$foo in current scope: ' . $foo . "\n";
}

$foo = "Example content";
test();
?>
```

***$_SERVER***

$_SERVER is an array containing information such as headers, paths, and script locations. The entries in this array are created by the web server. There is no guarantee that every web server will provide any of these; servers may omit some, or provide others not listed here

***$_GET***

An associative array of variables passed to the current script via the URL parameters (aka. query string). Note that the array is not only populated for GET requests, but rather for all requests with a query string

```php
<?php
echo 'Hello ' . htmlspecialchars($_GET["name"]) . '!';
?>
```

***$_POST***

An associative array of variables passed to the current script via the HTTP POST method when using application/x-www-form-urlencoded or multipart/form-data as the HTTP Content-Type in the request.

```php
<?php
echo 'Hello ' . htmlspecialchars($_POST["name"]) . '!';
?>
```

***$_FILES***

An associative array of items uploaded to the current script via the HTTP POST method. The structure of this array is outlined in the POST method uploads section.

```

```

***$_REQUEST***

An associative array that by default contains the contents of $_GET, $_POST and $_COOKIE.

***$_SESSION***

An associative array containing session variables available to the current script. See the Session functions documentation for more information on how this is used.

***$_ENV***

These variables are imported into PHP's global namespace from the environment under which the PHP parser is running. Many are provided by the shell under which PHP is running and different systems are likely running different kinds of shells, a definitive list is impossible. Please see your shell's documentation for a list of defined environment variables.



## Features

### Cookies

Cookies are a mechanism for storing data in the remote browser and thus tracking or identifying return users. You can set cookies using the setcookie() or setrawcookie() function. Cookies are part of the HTTP header, so setcookie() must be called before any output is sent to the browser.

## Session

Session support in PHP consists of a way to preserve certain data across subsequent accesses.

A visitor accessing your web site is assigned a unique id, the so-called session id. This is either stored in a cookie on the user side or is propagated in the URL.

***session_start()***

session_start() creates a session or resumes the current one based on a session identifier passed via a GET or POST request, or passed via a cookie

***Passing the Session ID***

There are two methods to propagate a session id:

- Cookies
- URL parameter

The session module supports both methods. Cookies are optimal, but because they are not always available, we also provide an alternative way. The second method embeds the session id directly into URLs.

PHP is capable of transforming links transparently. If the run-time option session.use_trans_sid is enabled, relative URIs will be changed to contain the session id automatically.

Alternatively, you can use the constant SID which is defined if the session started. If the client did not send an appropriate session cookie, it has the form session_name=session_id. Otherwise, it expands to an empty string. Thus, you can embed it unconditionally into URLs.