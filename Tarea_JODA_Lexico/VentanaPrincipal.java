import java.awt.*;
import java.io.StringReader;
import javax.swing.*;

public class VentanaPrincipal extends JFrame {
    private JTextField barraToken; 
    private JTextArea cuadroResultado; 

    public VentanaPrincipal() {
        setTitle("Analizador Léxico JODA - UAEMex");
        setSize(500, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(10, 10));

        JPanel p = new JPanel();
        p.add(new JLabel("Token:"));
        barraToken = new JTextField(15);
        p.add(barraToken);
        JButton btn = new JButton("Analizar");
        btn.addActionListener(e -> analizar());
        p.add(btn);
        add(p, BorderLayout.NORTH);

        cuadroResultado = new JTextArea();
        cuadroResultado.setFont(new Font("Consolas", Font.BOLD, 16));
        add(new JScrollPane(cuadroResultado), BorderLayout.CENTER);
    }
private void analizar() {
        try {
            Lexer lexer = new Lexer(new StringReader(barraToken.getText().trim()));
            Tokens res = lexer.yylex();
            String t = barraToken.getText() + ": ";
            
            if (res == null) { 
                t = "Análisis terminado"; 
            } else {
                switch(res) {
                    case T_RESERVED: t += "Es una Palabra Reservada"; break;
                    case T_DATA_TYPE: t += "Es un Tipo de Dato"; break;
                    case T_IDENTIFIER: t += "Es un Identificador"; break;
                    case T_NUMBER: t += "Es un Número"; break;
                    case T_STRING: t += "Es una Cadena de texto"; break;
                    case T_ASSIGN: t += "Es un Operador de Asignación"; break;
                    case T_ARITHMETIC: t += "Es un Operador Aritmético"; break;
                    case T_LOGIC: t += "Es un Operador de comparación lógica"; break;
                    case T_DELIMITER: t += "Es un Delimitador de fin de sentencia"; break;
                    case T_BRACKET: t += "Es un Delimitador de Bloque"; break;
                    case T_PAREN: t += "Es un Simbolo de Agrupación"; break;
                    case ERROR: t += "Símbolo no definido"; break;
                }
            }
            cuadroResultado.setText(t);
            cuadroResultado.setForeground(new Color(0, 100, 0));
        } catch (Exception e) {
            cuadroResultado.setText("Error en el análisis");
        }
    }
}