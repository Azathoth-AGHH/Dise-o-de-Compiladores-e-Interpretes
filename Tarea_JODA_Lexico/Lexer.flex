%%
%class Lexer
%public
%type Tokens
%unicode
L = [a-z]
D = [0-9]
WHITE = [ \t\r\n]+
%%
{WHITE} { /* Ignorar */ }

"define"|"entry"|"loop"|"if"|"out"|"object"|"method"|"select"|"else"|"input" { return Tokens.T_RESERVED; }
"int"|"string"|"dec"|"bool"|"void" { return Tokens.T_DATA_TYPE; }
"=" { return Tokens.T_ASSIGN; }
"+"|"-"|"*"|"/"|"%" { return Tokens.T_ARITHMETIC; }
"=="|"!="|">"|"<"|"&&"|"||"|"!" { return Tokens.T_LOGIC; }
";" { return Tokens.T_DELIMITER; }
"{"|"}" { return Tokens.T_BRACKET; }
"("|")" { return Tokens.T_PAREN; }
\"[^\"]*\" { return Tokens.T_STRING; }
{L}({L}|{D}|_)* { return Tokens.T_IDENTIFIER; }
{D}+(\.{D}+)? { return Tokens.T_NUMBER; }
. { return Tokens.ERROR; }