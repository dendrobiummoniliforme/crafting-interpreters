# Definitions from Crafting Interpreters
Created: 2432417753

WIP - Filling this in as best I can.

## Chapter 1 - Introduction
* **Domain Specific Language** - languages crafted to handle specific tasks, an example is `markdown` or `json`.
* **compiler-compiler** - A compiler that produces a compiler, an example is `yacc`.
* **self-hosting** - the act of compiling a compiler in the language it compiles.
* **bootstrapping** - writing a compiler in another language such that you can compile your self-hosted compiler, discarding the other compiler once this step is complete.
## Chapter 2 - A Tree Walk Scanner
* **scanning** - The act of chunking raw source code by grouping series of characters into `tokens`.
* **lexing** -  Formally known as lexical analysis, this is the same as scanning.
* **scanner** - Also known as a lexer, is the code that performs the act of scanning.
* **token** - A series of characters grouped in a meaningful way.
* **REPL** - Read, Eval, Print, Loop; a general way of referring to an interactive prompt for programming languages.
* **lexeme** - The smallest sequence of letters that still represents something. For example `var language = "lox";`, `var` is a lexeme.
* grammar - 
* parser - 
* abstract syntax tree - 
* syntax errors - 
* binding - 
* resolution - 
* identifier - 
* scope - 
* type error - 
* attributes - 
* symbol table - 
* front end - 
* back end -
* middle end -
* intermediate representation - 
* optimize -
* constant folding - 
* generating code - 
* p-code -
* bytecode - 
* virtual machine -
* runtime - 
* single pass compilers - 
* tree walk interpreter -
* transpiler - 
* just in time compilation -
* compiling - 
* interpreter - 

## Chapter  3 - The Lox Language
* **reference counter** - a way of managing memory, it is a method where you store the number of references to a resource.
* **tracing garbage collection**- a way of managing memory, determines which objects are garbage collected by tracing down a path of references from a root object. This chain of references then determines if an object should be collected. For example `let x = new ObjectToBeCollected` and `let y = new Object()` then say `x = new Object()` then, the original initialization and declaration of `x` will be cleaned up, as `new ObjectToReference()` is not used anywhere.
* **booleans** - `true/false`.
* **strings** -  `"this is a string literal"`.
* **numbers** - can have many different representations. Integers `(1)`, and decimals `(1.0)` are examples.
* **Nil** - represents the abscence of a value.
* **expressions** - A combination of values, variables, operators, and function calls that can be evaluated (computed/reduced) to produce a single value. Expressions are the building blocks of computations in programming. When we say an expression "evaluates to" something, we mean it can be reduced to a single concrete value. Examples:
  - `2 + 3` evaluates to `5`
  - `x * y` evaluates to the product of x and y's values
  - `sqrt(16)` evaluates to `4`
  
  The expression `x = y + 1` actually consists of multiple sub-expressions:
  1. `y + 1` is an expression that evaluates to a number
  2. `x = (y + 1)` is an assignment expression that evaluates to the assigned value
  
  A statement, like `x = y + 1;`, is a complete instruction that performs an action. The semicolon (`;`) turns the expression into a statement by telling the computer "execute this". While expressions produce values, statements perform actions (like storing values in memory). Think of expressions as questions ("what is x + y?") and statements as commands ("store this result in x").
* **operands** -
* binary operators - two sub-expressions on either side of an `operand` for example, `1 + 1` the `+` is a binary operator takes in exactly two sub-expressions.
* infix operators - `+ 1 1`.
* postfix operators - `1++`.
* short-circuit - a way of evaluating control flow, if a statement consists of the operands like `z = true; x == z || y == !z` then we would short circuit this operand on the left hand side of the `x == z || y == !z` expression. The `x == z => x == true ==> true || y == !z` since we already have `true` we do not need to check for the evaluation of `y == !z`.
* expression statement - Modifies the world in some way, produces output, reads input, modifies state.
* block - `{ //do logic }` is a block.
* argument - a value you pass to a function when you call it, `example(1)` where `1` is our argument.
* actual parameter - another name for an argument.
* parameter - a variable that holds the value of the argument inside the body of a function `example(a)` where `a` is our parameter we say then that if `let exampleFunction = example(1)` then `example(1) { // a = 1 }`.
* formal parameter - same as a parameter.
* closures - a function who encloses variables that sit outside of it; memory belongs to the enclosing function not the inner function.
* object oriented programming -
* static dispatch -
* dynamic dispatch - 
* derived class -
* subclass -
* base class -
* superclass -

# Chapter 5 Representing Code
* **context-free grammar** - a formal grammar whose production rules can be applied to a nonterminal symbol regardless of its context.
* **productions** - rules that produce strings in the grammar `breakfast  → protein "with" breakfast "on the side" ;`.
* **derivations** - to derive a string from the rules of a grammar `"scrambled eggs with toast on the side"`.
* **head** - the name of a production, `breakfast  →`.
* **body** - the description of what a production generates `→ protein "with" breakfast "on the side" ;`
* **terminal** - using an alphabet analogy, a terminal is a letter from the grammar's alphabet. For interpreters, this cawn be something like `if` or `1234`. Individual lexemes.
* `nonterminal` - a named reference to another rule in the grammar `breakfast  → protein`.
* Backus-Naur Form - (BNF) a method of describing grammars.
* Syntax Tree - a type of tree that represents the syntax of a grammar.
* Parse Tree - a type of Syntax Tree where all productions are represented as nodes in the tree.
* Abstract Syntax Tree - a type of Syntax Tree where only needed productions are represented as nodes in the tree. [What's the difference between parse trees and abstract syntax trees (ASTs)?](https://stackoverflow.com/questions/5026517/whats-the-difference-between-parse-trees-and-abstract-syntax-trees-asts)
* Visitor Pattern - 


## References
1.