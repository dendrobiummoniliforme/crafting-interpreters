## Chaper 2 Challenges: A Map of the Territory

## Pick an open source implementation of a language you like. Download the source code and poke around in it. Try to find the code that implements the scanner and parser. Are they handwritten, or generated using tools like Lex and Yacc? (.l or .y files usually imply the latter.)

I picked Rust! Their `parser` and `lexer` are part of [`rust_parse`](https://github.com/rust-lang/rust/tree/master/compiler/rustc_parse/src). The files are hand generated. More information is contained in their [`rustc`](https://doc.rust-lang.org/rustc/index.html) book. They have a handy additional resource that explains the intricacies of developing against `rustc` in the [`rustc dev guide`](https://rustc-dev-guide.rust-lang.org/overview.html).

I decided to dig a bit deeper into the dev guide. Rust was originally implemented using `OCaml`:
* [`parser`](https://github.com/rust-lang/rust/blob/ef75860a0a72f79f97216f8aaa5b388d98da6480/src/boot/fe/parser.ml)
* [`lexer`](https://github.com/rust-lang/rust/blob/ef75860a0a72f79f97216f8aaa5b388d98da6480/src/boot/fe/lexer.mll)

The compiler for Rust follows a process known as `bootstrapping` which effectively builds the next compiler using a previous version of the compiler. In both instances of the `compiler` it does not appear to have ever used `.y` or `.l` files to generate their `parser` and `lexer` source files.

## Just-in-time compilation tends to be the fastest way to implement dynamically typed languages, but not all of them use it. What reasons are there to not JIT?

I doubt I understand all of the underlying reasons behind these choices, as I lack quite a bit of historical information, and understanding in general. But, if I were to make a few guesses for this choice:
* Start-up Cost. JIT compilation saves on performance in the long term. That is, with modern JIT compilation, programs are able to optimize as they run. This means that they become more performant over time.
  * A positive reason to use something like this would be a `server` or hosted `services`. You would expect these to be long lived, and thus would benefit the most from dynamic optimization.
  * The opposite would then also hold true, for programs that are started and stopped rapidly, would not be served best by a compiler that benefits from longer runtimes.
* A more direct discussion about this can be found in [PEP 3146 – Merging Unladen Swallow into CPython](https://peps.python.org/pep-3146/). This is a demonstration of a `dynamic language`, `cpython`, which attempted to introduce JIT compilation to their already existing ecosystem. It was widthdrawn, there is a really well written explanation within the document, however, for this exercise I can lean on the fact that they mention at least two primary reasons:
* [Performance over existing implementations, and LLVM.](https://peps.python.org/pep-3146/#performance-retrospective).