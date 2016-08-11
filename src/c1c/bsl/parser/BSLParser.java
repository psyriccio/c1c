/*  
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package c1c.bsl.parser;

import org.parboiled.BaseParser;
import org.parboiled.Rule;
import org.parboiled.annotations.*;

/**
 *
 * @author psyriccio
 */
@SuppressWarnings("InfiniteRecursion")
@BuildParseTree
public class BSLParser extends BaseParser<Object> {

    public Rule CompilationUnit() {
        return Sequence(
                Spacing(),
                ZeroOrMore(VariableDeclaration()),
                UnitMembers(),
                EOI
        );
    }

    Rule VariableDeclaration() {
        return Sequence(VAR, Identifier(), Optional(EXPORT), SEMI);
    }

    Rule UnitMembers() {
        return ZeroOrMore(
                FirstOf(
                        ProcesureDeclaration(),
                        FunctionDeclaration()
                )
        );
    }

    Rule ProcesureDeclaration() {
        return Sequence(
                PROC,
                Identifier(),
                FormalParameters(),
                Optional(EXPORT),
                Block(),
                ENDPROC
        );
    }

    Rule FunctionDeclaration() {
        return Sequence(
                FUNC,
                Identifier(),
                FormalParameters(),
                Optional(EXPORT),
                Block(),
                ENDFUNC
        );
    }

    Rule FormalParameters() {
        return Sequence(LPAR, Optional(FormalParametersDecl()), RPAR);
    }

    Rule FormalParametersDecl() {
        return OneOrMore(
                Sequence(
                        Identifier(),
                        Optional(COMMA, FormalParametersDecl())));
    }

    Rule Block() {
        return BlockStatements();

    }

    Rule BlockStatements() {
        return ZeroOrMore(BlockStatement());
    }

    Rule BlockStatement() {
        return FirstOf(
                Statement(),
                LocalVariableDeclarationStatement()
        );
    }

    Rule LocalVariableDeclarationStatement() {
        return Sequence(VariableDeclarators(), SEMI);
    }

    Rule VariableDeclarators() {
        return Sequence(VariableDeclarator(), ZeroOrMore(SEMI, VariableDeclarator()));
    }

    Rule VariableDeclarator() {
        return Sequence(Identifier(), Optional(Sequence(EQU, Expression())));//,VariableInitializer());
    }

    Rule VariableInitializer() {
        return null;
    }

    Rule Statement() {
        return FirstOf(
                Sequence(Sequence(Identifier(), COLON), Statement()),
                Sequence(RETURN, Optional(Expression()), SEMI)
        );
    }

    Rule StatementExpression() {
        return Expression();
    }

    Rule ConstantExpression() {
        return Expression();
    }

    Rule Expression() {
        return Sequence(
                ConditionalExpression(),
                ZeroOrMore(AssignmentOperator(), ConditionalExpression())
        );
    }

    Rule ConditionalExpression() {
        return Sequence(
                ConditionalOrExpression(),
                ZeroOrMore(QUERY, Expression(), COLON, ConditionalOrExpression())
        );
    }

    Rule ConditionalOrExpression() {
        return Sequence(
                ConditionalAndExpression(),
                ZeroOrMore(OR, ConditionalAndExpression())
        );
    }

    Rule ConditionalAndExpression() {
        return Sequence(
                InclusiveOrExpression(),
                ZeroOrMore(AND, InclusiveOrExpression())
        );
    }

    Rule InclusiveOrExpression() {
        return Sequence(
                ExclusiveOrExpression(),
                ZeroOrMore(OR, ExclusiveOrExpression())
        );
    }

    Rule ExclusiveOrExpression() {
        return Sequence(
                AndExpression(),
                ZeroOrMore(HAT, AndExpression())
        );
    }

    Rule AndExpression() {
        return Sequence(
                EqualityExpression(),
                ZeroOrMore(AND, EqualityExpression())
        );
    }

    Rule AssignmentOperator() {
        return FirstOf(EQU, MINUSEQU, DIVEQU, HATEQU);
    }

    Rule EqualityExpression() {
        return Sequence(
                RelationalExpression(),
                ZeroOrMore(FirstOf(EQU, NOTEQUAL), RelationalExpression())
        );
    }

    Rule RelationalExpression() {
        return ZeroOrMore(
                FirstOf(LE, GE, LT, GT)
        );
    }

    Rule AdditiveExpression() {
        return Sequence(
                MultiplicativeExpression(),
                ZeroOrMore(FirstOf(PLUS, MINUS), MultiplicativeExpression())
        );
    }

    Rule MultiplicativeExpression() {
        return Sequence(
                UnaryExpression(),
                ZeroOrMore(FirstOf(STAR, DIV, MOD), UnaryExpression())
        );
    }

    Rule UnaryExpression() {
        return FirstOf(
                Sequence(PrefixOp(), UnaryExpression()),
                Sequence(Primary(), ZeroOrMore(Selector()))
        );
    }

    Rule Primary() {
        return FirstOf(
                ParExpression(),
                Literal(),
                Sequence(NEW, Creator())
        );
    }

    Rule Creator() {
        return CreatedName();
    }

    Rule CreatedName() {
        return Sequence(
                Identifier(),
                ZeroOrMore(DOT, Identifier())
        );
    }

    Rule Selector() {
        return FirstOf(
                Sequence(DOT, Identifier(), Optional(Arguments())),
                DimExpr()
        );
    }

    Rule DimExpr() {
        return Sequence(LBRK, Expression(), RBRK);
    }

    Rule Arguments() {
        return Sequence(
                LPAR,
                Optional(Expression(), ZeroOrMore(COMMA, Expression())),
                RPAR
        );
    }

    Rule ClassType() {
        return Sequence(
                Identifier(),
                ZeroOrMore(DOT, Identifier())
        );
    }

    Rule ParExpression() {
        return Sequence(LPAR, Expression(), RPAR);
    }

    Rule PrefixOp() {
        return FirstOf(PLUS, MINUS);
    }

    @SuppressNode
    Rule Spacing() {
        return ZeroOrMore(FirstOf(
                // whitespace
                OneOrMore(AnyOf(" \t\r\n\f").label("Whitespace")),
                // traditional comment
                Sequence("/*", ZeroOrMore(TestNot("*/"), ANY), "*/"),
                // end of line comment
                Sequence(
                        "//",
                        ZeroOrMore(TestNot(AnyOf("\r\n")), ANY),
                        FirstOf("\r\n", '\r', '\n', EOI)
                )
        ));
    }

    @SuppressSubnodes
    @MemoMismatches
    Rule Identifier() {
        return Sequence(TestNot(Keyword()), Letter(), ZeroOrMore(LetterOrDigit()), Spacing());
    }

    // JLS defines letters and digits as Unicode characters recognized
    // as such by special Java procedures.
    Rule Letter() {
        // switch to this "reduced" character space version for a ~10% parser performance speedup
        //return FirstOf(CharRange('a', 'z'), CharRange('A', 'Z'), '_', '$');
        return FirstOf(Sequence('\\', UnicodeEscape()), new JavaLetterMatcher());
    }

    @MemoMismatches
    Rule LetterOrDigit() {
        // switch to this "reduced" character space version for a ~10% parser performance speedup
        //return FirstOf(CharRange('a', 'z'), CharRange('A', 'Z'), CharRange('0', '9'), '_', '$');
        return FirstOf(Sequence('\\', UnicodeEscape()), new JavaLetterOrDigitMatcher());
    }

    Rule Literal() {
        return Sequence(
                FirstOf(
                        IntegerLiteral(),
                        CharLiteral(),
                        StringLiteral(),
                        Sequence("истина", TestNot(LetterOrDigit())),
                        Sequence("ложь", TestNot(LetterOrDigit())),
                        Sequence("null", TestNot(LetterOrDigit()))
                ),
                Spacing()
        );
    }

    @SuppressSubnodes
    Rule IntegerLiteral() {
        return Sequence(FirstOf(HexNumeral(), DecimalNumeral()), Optional(AnyOf("lL")));
    }

    @SuppressSubnodes
    Rule DecimalNumeral() {
        return FirstOf('0', Sequence(CharRange('1', '9'), ZeroOrMore(Digit())));
    }

    @SuppressSubnodes

    @MemoMismatches
    Rule HexNumeral() {
        return Sequence('0', IgnoreCase('x'), OneOrMore(HexDigit()));
    }

    Rule HexDigit() {
        return FirstOf(CharRange('a', 'f'), CharRange('A', 'F'), CharRange('0', '9'));
    }

    Rule Digit() {
        return CharRange('0', '9');
    }

    Rule HexSignificant() {
        return FirstOf(
                Sequence(FirstOf("0x", "0X"), ZeroOrMore(HexDigit()), '.', OneOrMore(HexDigit())),
                Sequence(HexNumeral(), Optional('.'))
        );
    }

    Rule CharLiteral() {
        return Sequence(
                '\'',
                FirstOf(Escape(), Sequence(TestNot(AnyOf("'\\")), ANY)).suppressSubnodes(),
                '\''
        );
    }

    Rule StringLiteral() {
        return Sequence(
                '"',
                ZeroOrMore(
                        FirstOf(
                                Escape(),
                                Sequence(TestNot(AnyOf("\r\n\"\\")), ANY)
                        )
                ).suppressSubnodes(),
                '"'
        );
    }

    Rule Escape() {
        return Sequence('\\', FirstOf(AnyOf("btnfr\"\'\\"), UnicodeEscape()));
    }

    Rule UnicodeEscape() {
        return Sequence(OneOrMore('u'), HexDigit(), HexDigit(), HexDigit(), HexDigit());
    }

    @Override
    protected Rule fromCharLiteral(char c) {
        // turn of creation of parse tree nodes for single characters
        return super.fromCharLiteral(c).suppressNode();
    }

    @SuppressNode
    @DontLabel
    Rule Terminal(String string) {
        return Sequence(string, Spacing()).label('\'' + string + '\'');
    }

    @SuppressNode
    @DontLabel
    Rule TerminalIgnoreCase(String string) {
        return Sequence(IgnoreCase(string), Spacing()).label('\'' + string + '\'');
    }

    @SuppressNode
    @DontLabel
    Rule Terminal(String string, Rule mustNotFollow) {
        return Sequence(string, TestNot(mustNotFollow), Spacing()).label('\'' + string + '\'');
    }

    @SuppressNode
    @DontLabel
    Rule TerminalIgnoreCase(String string, Rule mustNotFollow) {
        return Sequence(IgnoreCase(string), TestNot(mustNotFollow), Spacing()).label('\'' + string + '\'');
    }

    @MemoMismatches
    Rule Keyword() {
        return Sequence(
                FirstOf("перем", "прервать", "экспорт", "исключение", "попытка", "вызватьисключение", "продолжить", "функция", "процедура", "для каждого",
                        "конецесли", "цикл", "конеццикла", "иначеесли", "для", "если", "перейти", "выполнить", "новый", "возврат",
                        "пока", "#если", "#конецесли", "#иначе", "конецпроцедуры", "конецфункции"),
                TestNot(LetterOrDigit())
        );
    }

    @SuppressNode
    @DontLabel
    Rule Keyword(String keyword) {
        return TerminalIgnoreCase(keyword, LetterOrDigit());
    }

    public final Rule VAR = Keyword("перем");
    public final Rule BREAK = Keyword("прервать");
    public final Rule EXPORT = Keyword("экспорт");
    public final Rule EXCEPTION = Keyword("исключение");
    public final Rule TRY = Keyword("попытка");
    public final Rule TROW = Keyword("вызватьисключение");
    public final Rule CONTINUE = Keyword("продолжить");
    public final Rule FUNC = Keyword("функция");
    public final Rule ENDFUNC = Keyword("конецфункции");
    public final Rule PROC = Keyword("процедура");
    public final Rule ENDPROC = Keyword("конецпроцедуры");
    public final Rule FOREACH = Keyword("для каждого");
    public final Rule ENDIF = Keyword("конецесли");
    public final Rule LOOP = Keyword("цикл");
    public final Rule ENDLOOP = Keyword("конеццикла");
    public final Rule ELSEIF = Keyword("иначеесли");
    public final Rule FOR = Keyword("для");
    public final Rule GOTO = Keyword("перейти");
    public final Rule IF = Keyword("если");
    public final Rule EXECUTE = Keyword("выполнить");
    public final Rule NEW = Keyword("новый");
    public final Rule RETURN = Keyword("возврат");
    public final Rule WHILE = Keyword("пока");
    public final Rule PPRCIF = Keyword("#если");
    public final Rule PPRCENDIF = Keyword("#конецесли");
    public final Rule PPRCELSE = Keyword("#иначе");

    public final Rule OR = TerminalIgnoreCase("ИЛИ");
    public final Rule AND = TerminalIgnoreCase("И");
    public final Rule AT = Terminal("@");
    public final Rule AMP = Terminal("&");
    public final Rule COLON = Terminal(":");
    public final Rule COMMA = Terminal(",");
    public final Rule DIV = Terminal("/", Ch('='));
    public final Rule DIVEQU = Terminal("/=");
    public final Rule DOT = Terminal(".");
    public final Rule ELLIPSIS = Terminal("...");
    public final Rule EQU = Terminal("=", Ch('='));
    public final Rule GE = Terminal(">=");
    public final Rule GT = Terminal(">", AnyOf("=>"));
    public final Rule HAT = Terminal("^", Ch('='));
    public final Rule HATEQU = Terminal("^=");
    public final Rule LBRK = Terminal("[");
    public final Rule LE = Terminal("<=");
    public final Rule LPAR = Terminal("(");
    public final Rule LPOINT = Terminal("<");
    public final Rule LT = Terminal("<", AnyOf("=<"));
    public final Rule LWING = Terminal("{");
    public final Rule MINUS = Terminal("-", AnyOf("=-"));
    public final Rule MINUSEQU = Terminal("-=");
    public final Rule MOD = Terminal("%", Ch('='));
    public final Rule NOTEQUAL = Terminal("<>");
    public final Rule PLUS = Terminal("+");
    public final Rule QUERY = Terminal("?");
    public final Rule RBRK = Terminal("]");
    public final Rule RPAR = Terminal(")");
    public final Rule RPOINT = Terminal(">");
    public final Rule RWING = Terminal("}");
    public final Rule SEMI = Terminal(";");
    public final Rule STAR = Terminal("*", Ch('='));
    public final Rule TILDA = Terminal("~");

}
