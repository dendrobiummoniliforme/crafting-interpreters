# Implementation of JLox / Clox

Following the book [**Crafting Interpreters**](https://craftinginterpreters.com/).

This contains the `Java` and `C` implemetations as well as answers to the challenges presented at the end of each chapter 😄

## Java

### Prerequisites

* Made using [VSCode Extensions](https://marketplace.cursorapi.com/items?itemName=vscjava.vscode-java-pack)
* I am on macOS, so I installed `openjdk` through `brew install openjdk`.
* It is then linked through `CMD+Shift+P > Download, install and use JDK`.

### Compiling
* Compiled output files will be generated in the `bin` folder by default. This is in the `.gitignore`.

> If you want to customize the folder structure, open `.vscode/settings.json` and update the related settings there.

### Dependency Management

The `JAVA PROJECTS` view allows you to manage your dependencies. More details can be found [here](https://github.com/microsoft/vscode-java-dependency#manage-dependencies).

## C

### Prerequisites
* macOS comes with `clang` through `xcode`. Otherwise, choose a compiler of choice.
* I am using `Apple clang version 16.0.0 (clang-1600.0.26.4)`.

### Compiling
* `gcc -o bin/<name_of_binary> <path_of_source>/<name_of_file>.c`.
* This is so we do not upload random binaries, `.gitignore` ignores `bin/`
* Example `gcc -o bin/challenge challenges/Chapter_01/C/challenge.c`

### Running
* `./bin/<name_of_binary>`

## References
1. [github](https://github.com/munificent/craftinginterpreters/)
2. [Crafting Interpreters](https://craftinginterpreters.com/)

## License
[LICENSE](LICENSE)
* For more information and additional commentary from the original Author please read [craftinginterpreters/LICENSE](https://github.com/munificent/craftinginterpreters/blob/master/LICENSE)
