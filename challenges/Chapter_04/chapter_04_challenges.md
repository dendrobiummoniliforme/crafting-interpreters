## Chaper 4 Challenges: Scanning

## The lexical grammars of Python and Haskell are not regular. What does that mean, and why aren’t they?

## Aside from separating tokens—distinguishing print foo from printfoo—spaces aren’t used for much in most languages. However, in a couple of dark corners, a space does affect how code is parsed in CoffeeScript, Ruby, and the C preprocessor. Where and what effect does it have in each of those languages?

For C we have macros. Specifically

```C
#define ITEM  42 // function(ITEM) -> function(42)
#define ITEM_2(x)  x * 2 // ITEM_2(1) --> 1 * 2 --> 2
```

This is further discussed in https://mailund.dk/posts/macro-metaprogramming/.

## Our scanner here, like most, discards comments and whitespace since those aren’t needed by the parser. Why might you want to write a scanner that does not discard those? What would it be useful for?

An example of a comment you would not want to discard would be something like `JSDocs` which provide `type annotations` for `JavaScript` functions. They can also act as documentation for libraries. That is, `function headers`. Stripping them away would remove the ability to read their documentation if served through a `binary`.

The same can be said for `rustdocs` which are even more verbose and can include compilation **within the `function header` itself** which is pretty cool.

## Add support to Lox’s scanner for C-style /* ... */ block comments. Make sure to handle newlines in them. Consider allowing them to nest. Is adding support for nesting more work than you expected? Why?
