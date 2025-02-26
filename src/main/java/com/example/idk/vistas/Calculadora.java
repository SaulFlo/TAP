package com.example.idk.vistas;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.Stack;

public class Calculadora extends Stage {

    private Scene scene;
    private TextField txtPanel;
    private VBox vBox;
    private HBox hBox;
    private Button[][] arBtnTeclado;
    private Button btnBorrar;
    private GridPane gdpTeclado;
    private String teclas = "789*456/123+.0=-";

    public void crearUI() {
        CrearKeyBoard();
        txtPanel = new TextField("");
        txtPanel.setPrefSize(300,50);
        txtPanel.setStyle("-fx-font-size: 18px;");
        txtPanel.setEditable(false);
        txtPanel.setAlignment(Pos.BASELINE_RIGHT);

        btnBorrar = new Button("AC");
        btnBorrar.setOnAction(e -> txtPanel.setText(""));
        btnBorrar.setPrefSize(65,50);

        hBox = new HBox(txtPanel,btnBorrar);
        hBox.setSpacing(8);

        vBox = new VBox(hBox, gdpTeclado);
        vBox.setSpacing(10);
        vBox.setPadding(new Insets(10));
        scene = new Scene(vBox, 380, 350);
        scene.getStylesheets().add(getClass().getResource("/styles/calcu.css").toString());

    }


    public void CrearKeyBoard(){

        arBtnTeclado = new Button[4][4];
        gdpTeclado = new GridPane();
        gdpTeclado.setHgap(5);
        gdpTeclado.setVgap(5);

        int pos = 0;

        for(int i = 0; i < 4; i++){
            for(int j = 0; j < 4; j++){
                String signo = "" + teclas.charAt(pos++);
                arBtnTeclado[i][j] = new Button(signo);


                if(("*/+-".indexOf(signo)) >= 0 ) {
                    arBtnTeclado[i][j].getStyleClass().add("operatorButton");
                    //arBtnTeclado[i][j].setStyle("-fx-background-color: rgba(31,107,45,0.72);");
                }


                arBtnTeclado[i][j].setOnAction(e -> addText(signo));
                arBtnTeclado[i][j].setPrefSize(85,65);
                gdpTeclado.add(arBtnTeclado[i][j], j, i);

            }

        }
    }

    private String operadores = "*/+-";
    private boolean flag = false;
    private boolean point = false;

    private void addText(String c) {

        if(flag){
            txtPanel.setText("");
            flag = false;
        }

        if(evaluate(c))
            txtPanel.appendText(c);
    }

    private boolean evaluate(String c) {
        boolean res = true;
        String txt = txtPanel.getText();

        if(c.equals("="))
            res = false;

        if(!txt.isEmpty()) {
            char cFinal = txt.charAt(txt.length() - 1);
            if(c.equals("=")){
                txtPanel.setText(calcular(txt));
                point= false;
            }
            else if (esOperador(cFinal)){
                if(esOperador(c))
                    txtPanel.setText(txt.substring(0, txt.length() - 1));
                else if (c.equals(".")) {
                    txtPanel.appendText("0");
                    point = true;
                }
            }

            else{
                if(c.equals(".")){
                    if(point) res = false;
                    else point = true;
                }
                else if(esOperador(c))
                    point = false;
            }
        }
        else {
            if (c.equals(".")) {
                txtPanel.appendText("0");
                point = true;
            }
            else if(esOperador(c))
                res = false;
        }
        return res;
    }

    private boolean esOperador(String c) {
        return operadores.indexOf(c) >= 0;
    }
    private boolean esOperador(char c) {
        return operadores.indexOf(c) >= 0;
    }



    private String calcular(String expresion) {

        Stack<Double> operandos = new Stack<>();
        Stack<Character> operadores = new Stack<>();

        if(!Character.isDigit(expresion.charAt(expresion.length() - 1))){
            flag = true;
            return "Formato invalido";
        }

        for(int i = 0; i < expresion.length(); i++){

            char c = expresion.charAt(i);

            if(Character.isDigit(c))
                i = leerNum(i, c, expresion, operandos);

            else{
                if(c == '+' || c == '-')
                    operadores.push(c);
                else{
                    char o = c;
                    c = expresion.charAt(++i);
                    i = leerNum(i, c, expresion, operandos);
                    if(o == '*')
                        operandos.push(operandos.pop()*operandos.pop());
                    else if(o == '/'){
                        double num2 = operandos.pop();
                        if(num2 == 0){
                            flag = true;
                            return "No es posible dividir entre cero";
                        }
                        else
                            operandos.push(operandos.pop() / num2);
                    }

                }
            }
        }
        while(!operadores.isEmpty()){
            char operador = operadores.pop();
            double num2 = operandos.pop();
            double num1 = operandos.pop();
            if(operador == '+')
                operandos.push(num1 + num2);
            else if (operador == '-')
                operandos.push(num1 - num2);
        }

        return String.valueOf(operandos.pop());
    }

    public int leerNum(int i, char c, String expresion, Stack<Double> operandos){
        String num = "";
        while (i < expresion.length() && (Character.isDigit(c) || c == '.' || c == 'E')) {
            num += c;
            if(++i < expresion.length())c = expresion.charAt(i);
        }
        i--;
        operandos.push(Double.parseDouble(num));
        return i;
    }

    public Calculadora(){
        crearUI();
        this.setTitle("Calculadora");
        this.setScene(scene);
        this.show();
    }
}
