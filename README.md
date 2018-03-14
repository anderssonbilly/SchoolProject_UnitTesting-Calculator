# SchoolProject_UnitTesting-Calculator

Lab 2 - Calculator
Author: Billy Andersson (anderssonbilly@hotmail.com)


---Info

Calculator created for assigment laboration 2, course Testdriven Development.

It supports addition, substraction, multiplicatiom, division, modulus, power to, parentheseses and negative numbers.

I started with creating some initial tests and then built the basic calculator functions around the tests.
When I got the basics working I added more tests and implemented acordingly.

The calculator is based on postfix - Reverse Polish Notation.
It takes a string value input, evaluates it and creates an ArrayList for converting it into postfix easier.
The postfix conversion is based upon the Shunting Yard algorithm.
The Shunting Yard algorithm outputs an new ArrayList and feeds that into the calculator, evaluates it and outputs the result.

If the program finds any problems it will throw an exception.

---Instructions

When the calculator starts it will give some initial information.
You can write 'info' at any time to see this message again.
If you type 'cmd' you will see all avalible commands and a description of their functions.
Type 'rpn' to see your expression in Reverse Polish Notation before the result.
'exit' will terminate the program.

Write an arimethic expression in one line without any spaces and press enter to get the result.
If you want negativa numbers just write an - befor the number, ex: 1--1 will give result of 2.
Decimals can be written as either , or .
Power to is written as: 2^2

---Links

https://en.wikipedia.org/wiki/Reverse_Polish_notation
https://en.wikipedia.org/wiki/Shunting-yard_algorithm
