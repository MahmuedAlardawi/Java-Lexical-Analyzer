/*
        ============================================
        CPCS-302 Project Phase 01 (Lexical Analyzer)
        Mahmued Alardawi    2135209    CS1
        ============================================
 */

/*
 * JavaTokens class for Lexical Analyzer Project.
 * This class defines sets and maps for Java language tokens such as keywords, operators, and punctuations.
 * It also includes methods to check token types and retrieve specific token representations.
 */

import java.util.*;

public class JavaTokens {
    // Set of Java language keywords
    private static final Set<String> keywords = Set.of(
        "abstract", "assert", "boolean", "break", "byte", "case", "catch", "char", "class",
        "const", "continue", "default", "do", "double", "else", "enum", "extends", "final",
        "finally", "float", "for", "goto", "if", "implements", "import", "instanceof", "int",
        "interface", "long", "native", "new", "package", "private", "protected", "public",
        "return", "short", "static", "strictfp", "super", "switch", "synchronized", "this",
        "throw", "throws", "transient", "try", "void", "volatile", "while", "module",
        "requires", "exports", "open", "opens", "uses", "provides", "with",
        "var", "yield", "record", "sealed", "non-sealed", "permits"
    );

    // Map of Java language operators to their textual representations
    private static final Map<String, String> operators = Map.ofEntries(
            Map.entry("+", "Addition"),
            Map.entry("-", "Subtraction"),
            Map.entry("*", "Multiplication"),
            Map.entry("/", "Division"),
            Map.entry("%", "Modulus"),
            Map.entry("++", "Increment"),
            Map.entry("--", "Decrement"),
            Map.entry("=", "Assignment"),
            Map.entry("==", "Equal to"),
            Map.entry("!=", "Not Equal"),
            Map.entry(">", "Greater Than"),
            Map.entry("<", "Less Than"),
            Map.entry(">=", "Greater Than or Equal to"),
            Map.entry("<=", "Less Than or Equal to"),
            Map.entry("&&", "Logical AND"),
            Map.entry("||", "Logical OR"),
            Map.entry("!", "Logical NOT"),
            Map.entry("&", "Bitwise AND"),
            Map.entry("|", "Bitwise OR"),
            Map.entry("^", "Bitwise XOR"),
            Map.entry("~", "Bitwise Complement"),
            Map.entry("<<", "Left Shift"),
            Map.entry(">>", "Right Shift"),
            Map.entry(">>>", "Unsigned Right Shift"),
            Map.entry("+=", "Addition Assignment"),
            Map.entry("-=", "Subtraction Assignment"),
            Map.entry("*=", "Multiplication Assignment"),
            Map.entry("/=", "Division Assignment"),
            Map.entry("%=", "Modulus Assignment"),
            Map.entry("&=", "Bitwise AND Assignment"),
            Map.entry("|=", "Bitwise OR Assignment"),
            Map.entry("^=", "Bitwise XOR Assignment"),
            Map.entry("<<=", "Left Shift Assignment"),
            Map.entry(">>=", "Right Shift Assignment"),
            Map.entry(">>>=", "Unsigned Right Shift Assignment")
    );

    // Map of Java language punctuations to their textual representations
    private static final Map<String, String> punctuations = Map.ofEntries(
            Map.entry("(", "Left Parenthesis"),
            Map.entry(")", "Right Parenthesis"),
            Map.entry("{", "Left Curly Brace"),
            Map.entry("}", "Right Curly Brace"),
            Map.entry("[", "Left Square Bracket"),
            Map.entry("]", "Right Square Bracket"),
            Map.entry(";", "Semicolon"),
            Map.entry(",", "Comma"),
            Map.entry(".", "Dot")
    );

    // Getters for operators and punctuations
    public static String getOperatorsToken(String token) {
        return operators.get(token);
    }

    public static String getPunctuationsToken(String token) {
        return punctuations.get(token);
    }

    public static boolean isKeyword(String token) {
        return keywords.contains(token);
    }

    public static boolean isOperator(String token) {
        return operators.containsKey(token);
    }

    public static boolean isPunctuation(String token) {
        return punctuations.containsKey(token);
    }

    public static boolean isIntegerLiteral(String token) {
        return token.matches("\\d+");
    }

    public static boolean isFloatingPointLiteral(String token) {
        return token.matches("\\d+\\.\\d+([eE][+-]?\\d+)?");
    }

    public static boolean isStringLiteral(String token) {
        return token.matches("\".*\"");
    }

    public static boolean isCharacterLiteral(String token) {
        return token.matches("'.'");
    }

    public static boolean isBooleanLiteral(String token) {
        return "true".equals(token) || "false".equals(token);
    }

    public static boolean isIdentifier(String token) {
        return token.matches("[a-zA-Z_$][a-zA-Z\\d_$]*");
    }

    // Description method providing a description of the JavaTokens class
    public static String description() {
        return "JavaTokens class provides sets and maps for identifying and categorizing " +
                "Java language tokens such as keywords, operators, and punctuations. It includes " +
                "methods to check whether a given token matches a keyword, operator, punctuation, " +
                "literal, or identifier, and methods to retrieve textual representations for operators " +
                "and punctuations.";
    }
}
