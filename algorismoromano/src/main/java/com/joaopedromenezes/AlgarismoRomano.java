package com.joaopedromenezes;

public class AlgarismoRomano {
    String[] romanos = { "M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I" };
    int[] inteiros = { 1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1 }; // Ensine as representações IV, IX, XL...

    public String inteiroParaRomano(int numero) {
        if (numero <= 0) {
            throw new IllegalArgumentException("O menor valor inteiro que pode ser convertido é 1!");
        } else if (numero > 3999) {
            throw new IllegalArgumentException("O maior valor inteiro que pode ser convertido é 3999!");
        }
        StringBuilder simbolo = new StringBuilder();
        for (int i = 0; i < romanos.length; i++) {
            if (numero != 0) { // Quebra a condição para poupar verificação de 0
                while (numero >= inteiros[i]) {
                    simbolo.append(romanos[i]); // IF and While percorrendo os 2 Arrays
                    numero -= inteiros[i]; // Subtrai e recebe
                    // numero = numero - i
                }
            } else {
                break;
            }
        }
        return simbolo.toString();
    }

    public int romanoParaInteiro(String romano) {
        romano.toUpperCase();
        if (romano == null || romano.isEmpty()) {
            throw new IllegalArgumentException("A representação romana não pode ser nula ou vazia.");
        }
    
        if (contemSequenciaInvalida(romano)) {
            throw new IllegalArgumentException("Sequência ou repetição inválida: " + romano);
        }
    
        int resultado = 0;
        int index = 0;
    
        while (index < romano.length()) {
            char atual = romano.charAt(index);
            int valorAtual = valorDoSimbolo(atual);
    
            if (index < romano.length() - 1) {
                char proximo = romano.charAt(index + 1);
                int valorProximo = valorDoSimbolo(proximo);
    
                if (valorProximo > valorAtual) {
                    // Caso de subtração
                    if (podeSubtrair(atual, proximo)) {
                        resultado += valorProximo - valorAtual;
                        index += 2; // Pula dois caracteres
                    } else {
                        throw new IllegalArgumentException("Sequência inválida: " + atual + proximo);
                    }
                } else {
                    // Caso de soma
                    resultado += valorAtual;
                    index++;
                }
            } else {
                // Último caractere
                resultado += valorAtual;
                index++;
            }
        }
    
        return resultado;
    }
    
    private boolean contemSequenciaInvalida(String romano) {
        String regex = "^(M{0,3}(CM|CD|D?C{0,3})(XC|XL|L?X{0,3})(IX|IV|V?I{0,3})|IV|IX|XL|XC|CD|CM|D?C{0,3}|L?X{0,3}|V?I{0,3})$";
        return !romano.matches(regex);
    }
    
    private boolean podeSubtrair(char simboloAtual, char simboloProximo) {
        return simboloAtual == 'I' && (simboloProximo == 'V' || simboloProximo == 'X') ||
               simboloAtual == 'X' && (simboloProximo == 'L' || simboloProximo == 'C') ||
               simboloAtual == 'C' && (simboloProximo == 'D' || simboloProximo == 'M');
    }
    
    private int valorDoSimbolo(char simbolo) {
        switch (simbolo) {
            case 'I':
                return 1;
            case 'V':
                return 5;
            case 'X':
                return 10;
            case 'L':
                return 50;
            case 'C':
                return 100;
            case 'D':
                return 500;
            case 'M':
                return 1000;
            default:
                throw new IllegalArgumentException("Caractere romano inválido: " + simbolo);
        }
    }

    public static void main(String[] args) {
        AlgarismoRomano algorismoRomano = new AlgarismoRomano();
        System.out.println("O número é: " + algorismoRomano.inteiroParaRomano(3999));
        AlgarismoRomano algarismoRomano2 = new AlgarismoRomano();
        System.out.println(algarismoRomano2.romanoParaInteiro("MMMCMXCIX"));
    }

}
