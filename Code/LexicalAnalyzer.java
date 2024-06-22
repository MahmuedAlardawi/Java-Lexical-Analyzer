/*
        ============================================
        CPCS-302 Project Phase 01 (Lexical Analyzer)
        Mahmued Alardawi    2135209    CS1
        ============================================
 */

import java.io.*;
import java.util.*;

public class LexicalAnalyzer {
    public static void main(String[] args) throws IOException {
        // Setup to read from an input file and write to an output file.
        BufferedReader input = new BufferedReader(new FileReader("input.txt"));
        PrintWriter output = new PrintWriter("output.txt");

        // Write headers to the output file for readability.
        output.println("Output:\n");
        output.printf("%-20s%-20s%n%n", "Lexemes", "Tokens");

        // Track whether we are inside a block comment.
        boolean inBlockComment = false;
        String line;
        while ((line = input.readLine()) != null) {
            // If we're inside a block comment, look for the end marker.
            if (inBlockComment) {
                if (line.contains("*/")) {
                    // If end marker is found, trim the line to exclude the comment and proceed.
                    line = line.substring(line.indexOf("*/") + 2);
                    inBlockComment = false;
                } else {
                    // If no end marker, skip the rest of the line.
                    continue;
                }
            }

            // Check for single-line comments and remove everything after "//"
            if (line.contains("//")) {
                line = line.substring(0, line.indexOf("//"));
            }

            // Check for the start of block comments and remove from the start marker.
            if (line.contains("/*")) {
                line = line.substring(0, line.indexOf("/*"));
                inBlockComment = true;
            }

            // Tokenize the current line based on specified delimiters.
            StringTokenizer tokenizer = new StringTokenizer(line,
                    " \t\n\r\f;,(){}[]+=-*/&|<>!%^", true);

            // Process each token in the line.
            String currentToken = null;
            String nextToken = null;
            if (tokenizer.hasMoreTokens()) {currentToken = tokenizer.nextToken();}

            while (currentToken != null) {
                if (tokenizer.hasMoreTokens()) {
                    nextToken = tokenizer.nextToken();
                    // Combine current and next token if they form a valid multi-character operator.
                    boolean isNextTokenPartOfOperator =
                            JavaTokens.isOperator(currentToken) && JavaTokens.isOperator(nextToken);

                    if (isNextTokenPartOfOperator) {
                        String combinedToken = currentToken + nextToken;
                        if (JavaTokens.isOperator(combinedToken)) {
                            currentToken = combinedToken;
                            nextToken = tokenizer.hasMoreTokens() ? tokenizer.nextToken() : null;
                        }
                    }
                }

                // Write non-empty tokens to the output file.
                if (!currentToken.trim().isEmpty()) {
                    output.printf("%-20s%-20s%n", currentToken, lexemesAnalyzer(currentToken));
                }

                // Prepare for the next iteration.
                currentToken = nextToken;
                nextToken = null;
            }
        }

        // Close the input and output streams.
        System.out.println("\n-   Lexical Analyzing Complete.");
        System.out.println(JavaTokens.description());
        input.close();
        output.close();
    }

    // Analyze each lexeme to determine its type.
    public static String lexemesAnalyzer(String token) {
        // Check the token against various categories.
        if (JavaTokens.isKeyword(token)) {
            return "Keyword (" + token + ")";
        } else if (JavaTokens.isOperator(token)) {
            return "Operator (" + JavaTokens.getOperatorsToken(token) + ")";
        } else if (JavaTokens.isPunctuation(token)) {
            return "Punctuation (" + JavaTokens.getPunctuationsToken(token) + ")";
        } else if (JavaTokens.isIntegerLiteral(token)) {
            return "Integer Literal";
        } else if (JavaTokens.isFloatingPointLiteral(token)) {
            return  "Floating Point Literal";
        } else if (JavaTokens.isStringLiteral(token)) {
            return "String Literal";
        } else if (JavaTokens.isCharacterLiteral(token)) {
            return "Character Literal";
        } else if (JavaTokens.isBooleanLiteral(token)) {
            return "Boolean Literal";
        } else if (JavaTokens.isIdentifier(token)) {
            return "Identifier (" + token + ")";
        } else {
            return "Unknown Token (Error)";
        }
    }
}
