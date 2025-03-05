## Chaper 4 Challenges: Scanning

## The lexical grammars of Python and Haskell are not regular. What does that mean, and why aren’t they?

## Aside from separating tokens—distinguishing print foo from printfoo—spaces aren’t used for much in most languages. However, in a couple of dark corners, a space does affect how code is parsed in CoffeeScript, Ruby, and the C preprocessor. Where and what effect does it have in each of those languages?

For C we have macros. Specifically

```C
#define ITEM  42 // function(ITEM) -> function(42)
#define ITEM_2(x)  x * 2 // ITEM_2(1) --> 1 * 2 --> 2
```

This is further discussed in https://mailund.dk/posts/macro-metaprogramming/.

For `CoffeeScript` I found https://www.reddit.com/r/Compilers/comments/1b6u8yx/question_how_does_spaces_affect_code_is_parsed_in/ which is directly related to this question. It's possible that https://stackoverflow.com/questions/9014970/why-does-coffeescript-require-whitespace-after-map represents the parsing of spaces in `CoffeeScript` where

```CoffeeScript
nums = [1..10].map (i) -> i*2 => nums = [1..10].map((i) -> i*2) // [0]
```

is parsed differently from

```CoffeeScript
nums = [1..10].map(i) -> i*2 => nums = [1..10].map(i)(-> i*2) // [1]
```

then, we can see that `[0]` behaves like a `JavaScript` `closure` and `[1]` behaves like a callback or more explicitly `map(i) = map(() -> i*2)` where `i` is a `function` with `signature` `() -> i*2`.

## Our scanner here, like most, discards comments and whitespace since those aren’t needed by the parser. Why might you want to write a scanner that does not discard those? What would it be useful for?

An example of a comment you would not want to discard would be something like `JSDocs` which provide `type annotations` for `JavaScript` functions. They can also act as documentation for libraries. That is, `function headers`. Stripping them away would remove the ability to read their documentation if served through a `binary`.

The same can be said for `rustdocs` which are even more verbose and can include compilation **within the `function header` itself** which is pretty cool.

## Add support to Lox’s scanner for C-style /* ... */ block comments. Make sure to handle newlines in them. Consider allowing them to nest. Is adding support for nesting more work than you expected? Why?

We need to support

```Java
/*
 * 
 */
 ```
 Style comments. From my exploration so far. The predicate is `/*` and the terminator is `*/`. It seems that any `*` inbetween does not actually matter. A block comment can be

 ```Java
 // Same line
 /* */

// Multi-line zero spaces.
 /*
 */

// Multi-line with a space.
 /*
  */

// Multiline with predicate on newline.
/*
asdfasd /*
*/

// Mutiline "nested"
// Note that (from left to right, top to bottom): 1 is closed by 3, and 4 is closed by 5.
/*
asdfasd /* */ /* 
*/
 ```
Among other likely combinations.

Starting the implementation, we can notice immediately that if I want to use the switch statement I will likely have to rewrite the `'/'` case. Which is fine, as it is the original `comment` case.

I think I will start by first doing a check for `*` after. This might not play nicely with the existing match for a `/n` and/or another `/`.

My solution is as follows:

```Java
case '/': 
    if (match('*')) {
        boolean exit = false;
        do {
            if (!isAtEnd()) {
                char currentChar = advance();
                if (currentChar == '*' && !isAtEnd()) {
                    char nextChar = advance();
                    if (nextChar == '/') {
                        exit = true;
                    }
                }
            }
            if (isAtEnd()) {
                exit = true;
            }
        } while (exit != true);

        // Exit early to prevent the other match from activating post-comment.
        // There is likely a cleaner way to handle this so we only have one break.
        break;
    }

    if (match('/')) {
        // Check if the next character is a newline or a newline.
        while (peek() != '\n' && !isAtEnd()) {
            advance();
        }
    } else {
        addToken(SLASH);
    }

    break;
```
I have not implemented nested comments.
