package jlox;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import static jlox.TokenType.*;

class Scanner {
    private final String source;
    private final List<Token> tokens = new ArrayList<>();
    private int start = 0;
    private int current = 0;
    private int line = 1;
    private static final Map<String, TokenType> keywords;

    // Identifiers.
    static {
        keywords = new HashMap<>();
        keywords.put("and", AND);
        keywords.put("class", CLASS);
        keywords.put("else", ELSE);
        keywords.put("false", FALSE);
        keywords.put("for", FOR);
        keywords.put("fun", FUN);
        keywords.put("if", IF);
        keywords.put("nil", NIL);
        keywords.put("and", AND);
        keywords.put("or", OR);
        keywords.put("print", PRINT);
        keywords.put("return", RETURN);
        keywords.put("super", SUPER);
        keywords.put("this", THIS);
        keywords.put("true", TRUE);
        keywords.put("var", VAR);
        keywords.put("while", WHILE);
    }

    Scanner(String source) {
        this.source = source;
    }

    List<Token> scanTokens() {
        while (!isAtEnd()) {
            start = current;
            scanToken();
        }

        tokens.add(new Token(EOF, "", null, line));
        return tokens;
    }

    private void scanToken() {
        char c = advance();

        // TODO Language Server wants me to use a
        // `rule switch` instead.
        // I will look this up.
        switch (c) {
            // Single character Tokens.
            case '(': addToken(LEFT_PAREN); break;
            case ')': addToken(RIGHT_PAREN); break;
            case '}': addToken(RIGHT_BRACE); break;
            case '{': addToken(LEFT_BRACE); break;
            case ',': addToken(COMMA); break;
            case '.': addToken(DOT); break;
            case '-': addToken(MINUS); break;
            case '+': addToken(PLUS); break;
            case ';': addToken(SEMICOLON); break;
            case '*': addToken(STAR); break;
            // Multi-character Tokens.
            case '!': 
                addToken(match('=') ? BANG_EQUAL : BANG);
                break;
            case '=': 
                addToken(match('=') ? EQUAL_EQUAL : EQUAL);
                break;
            case '<': 
                addToken(match('=') ? LESS_EQUAL : LESS);
                break;
            case '>': 
                addToken(match('=') ? GREATER_EQUAL : GREATER);
                break;
            case '/':
                if (match('/')) {
                    // A comment goes until the end of the line.
                    while (peek() != '\n' && !isAtEnd()) {
                        advance();
                    }
                } else {
                    addToken(SLASH);
                }
                break;
            // Whitespace Tokens.
            case ' ':
            case '\r':
            case '\t':
                // Ignore whitespace.
                break;
            case '\n':
                line++;
                break;
            // Literal Tokens.
            case '"': string(); break;
            // Keyword Tokens.
            case 'o': 
                if (match('r')) {
                    addToken(OR);
                }
            default:
                // Check for a digit in the default case.
                if (isDigit(c)) {
                    number();
                } else if (isAlpha(c)) {
                    // Performing a Maximal Munch.
                    identifier();
                } else {
                    // Example, @#^ are not Tokens we define
                    // in Lox. We should handle these.
                    Lox.error(line, "Unexpected character.");
                }
                break;
        }
    }

    private void identifier() {
        while (isAlphaNumeric(peek())) {
            advance();
        }

        String text = source.substring(start, current);
        TokenType type = keywords.get(text);
        if (type == null) {
            type = IDENTIFIER;
        }
        addToken(type);
    }

    /**
     * ASCII comparison of a char to determine if it is
     * an Alpha value.
     * @param c
     * @return boolean
     */
    private boolean isAlpha(char c) {
        return (c >= 'a' && c <= 'z') ||
               (c >= 'A' && c <= 'Z')  ||
               c == '_';
    }

    /**
     * Check if a char is AlphaNumeric.
     * @param c
     * @return boolean
     */
    private boolean isAlphaNumeric(char c) {
        return isAlpha(c) || isDigit(c);
    }

    private boolean isDigit(char c) {
        return c >= '0' && c <= '9';
    }

    private void number() {
        while(isDigit(peek())) {
            advance();
        }

        // Look for a fractional part.
        if (peek() == '.' && isDigit(peekNext())) {
            // Consume the '.'
            advance();

            while (isDigit(peek())) {
                advance();
            }
        }

        // TODO language server recommending that I provide Double.parseDouble(source, start, current, null)
        // instead, as doing this method we would remove the introduction of a temporary string.
        addToken(NUMBER, Double.parseDouble(source.substring(start, current)));
    }

    private void string() {
        while (peek() != '"' && !isAtEnd()) {
            if (peek() == '\n') {
                line++;
            }
            advance();
        }

        if (isAtEnd()) {
            Lox.error(line, "Unterminated string");
            return;
        }

        // The closing ".
        advance();

        // Trim the surrounding quotes.
        String value = source.substring(start + 1, current - 1);
        addToken(STRING, value);
    }

    /**
     * Check if a character is equal to what we expect.
     * @param expected char we are matching
     * @return boolean
     */
    private boolean match(char expected) {
        if (isAtEnd()) {
            return false;
        }
        if (source.charAt(current) != expected) {
            return false;
        }

        current++;
        return true;
    }

    /**
     * Lookahead for our next char.
     * @return a non-consumed char at the next index.
     */
    private char peek() {
        if (isAtEnd()) {
            return '\0';
        }
        return source.charAt(current);
    }

    /**
     * Lookahead for the next next char.
     * @return a non-consumed char at the next next index.
     */
    private char peekNext() {
        if (current + 1 >= source.length()) {
            return '\0';
        }

        return source.charAt(current + 1);
    }

    private boolean isAtEnd() {
        return current >= source.length();
    }

    /**
     * Move along the array of chars by 1.
     * @return char - the next char.
     */
    private char advance() {
        return source.charAt(current++);
    }

    // We are doing Method overloading for the double addToken methods.
    // https://www.w3schools.com/java/java_methods_overloading.asp
    // New to me, I come from JavaScript / TypeScript.
    // TODO learn about it, why is it used or not used.

    /**
     * @param type type of a non-literal, example STAR
     */
    private void addToken(TokenType type) {
        addToken(type, null);
    }
    
    /**
     * @param type type of the literal, example NUMBER
     * @param literal actual value of the type, example 123
     */
    private void addToken(TokenType type, Object literal) {
        String text = source.substring(start, current);
        tokens.add(new Token(type, text, literal, line));
    }
}