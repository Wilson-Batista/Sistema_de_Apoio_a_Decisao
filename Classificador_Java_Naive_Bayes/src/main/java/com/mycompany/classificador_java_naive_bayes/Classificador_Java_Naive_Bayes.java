/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Project/Maven2/JavaApp/src/main/java/${packagePath}/${mainClassName}.java to edit this template
 */
package com.mycompany.classificador_java_naive_bayes;

import de.daslaboratorium.machinelearning.classifier.Classifier;
import de.daslaboratorium.machinelearning.classifier.bayes.BayesClassifier;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

/**
 *
 * @author wilso
 */
public class Classificador_Java_Naive_Bayes {

    public static void main(String[] args) {
        Classifier<String, String> bayes = new BayesClassifier<String, String>();
        ArrayList<String> positivos = lerArquivo("positivo.txt");
        ArrayList<String> negativos = lerArquivo("negativa.txt");
        ArrayList<String> teste = lerArquivo("teste.txt");
        for (String valor : positivos) {
            String[] positiveText = valor.split("\\s");
            bayes.learn("positivo", Arrays.asList(positiveText));
        }
        for (String valor : negativos) {
            String[] negativoText = valor.split("\\s");
            bayes.learn("negativo", Arrays.asList(negativoText));
        }
        for (String valor : teste) {
            String[] unknownText1 = valor.split("\\s");
            System.out.println( // will output "positive"
                    bayes.classify(Arrays.asList(unknownText1)).getCategory());
            System.out.println(((BayesClassifier<String, String>) bayes).classifyDetailed(
                    Arrays.asList(unknownText1)));
        }

    }

    public static ArrayList<String> lerArquivo(String Caminho) {
        ArrayList<String> conteudo = new ArrayList();
        try {
            FileReader arq = new FileReader(Caminho);
            BufferedReader lerArq = new BufferedReader(arq);
            String linha = "";
            try {
                linha = lerArq.readLine();
                while (linha != null) {
                    //conteudo += linha+"\n";                    
                    conteudo.add(linha);
                    linha = lerArq.readLine();
                }
                arq.close();
                return conteudo;
            } catch (IOException ex) {
                System.out.println("Erro: Não foi possível ler o arquivo!");
                return null;
            }
        } catch (FileNotFoundException ex) {
            System.out.println("Erro: Arquivo não encontrado!");
            return null;
        }
    }

}