import java.io.*;
import java.util.ArrayList;

public class Programa{
    public static void main(String[] args) {
        Algoritmo a = new Algoritmo();
        String arquivo = "teste100d.txt";
        String texto = a.leTxt(arquivo);
        
        int qtdAgentes;        
        String linha[] = texto.split("\n");        
        String aux[] = linha[0].split(" ");        
        qtdAgentes = Integer.parseInt(aux[0]);
        int[][] vetor = new int[qtdAgentes+1][qtdAgentes+1];
        int[] nodo = new int[qtdAgentes+1];
        
        //Monta vetor
        for(int i=1; i<linha.length; i++){
        	String aux2[] = linha[i].split(" ");
        	for(int j=1; j<aux2.length; j++){
        		for(int k=1; k<aux2.length; k++){
        			if(aux2[j] != aux2[k]){
        				vetor[Integer.parseInt(aux2[j])][Integer.parseInt(aux2[k])] = 1;
        			}
        		}
        	}
        }
        
        //Insere Cores
        for(int i=1; i<vetor.length; i++){
        	for(int j=1; j<vetor.length; j++){
        		if(vetor[i][j] == 1){
        			if(nodo[i] == nodo[j]){
        				nodo[j]++;
        			}
        		}
        	}
        }
        
        /*for(int i=1; i<nodo.length; i++){
        	System.out.println("Nodo " + i + ": " + nodo[i]);
        }*/
        
        //Monta Lista de Cores
        ArrayList cores = new ArrayList();
        cores.add(0);
        for(int i=1; i<nodo.length; i++){
        	if(cores.contains(nodo[i]) == false){
        		cores.add(nodo[i]);
        	}
        }
        
        //Mostra resultado
        System.out.println(arquivo);
        System.out.println("Freqüências: " + cores.size());
        for(int i=0; i<cores.size(); i++){
        	System.out.print("Freqüência " + (i+1) + ": ");
        	for(int j=1; j<nodo.length; j++){
        		if(i == nodo[j]){
        			System.out.print(j + " ");
        		}
        	}
        	System.out.print("\n");
        }
        
        /*a.bubbleSort(nodo);
        for(int n=1; n<nodo.length; n++){
        	if(n != 1){
	        	if(nodo[n] != nodo[n-1]){
	        		System.out.println("");
	        	}
        	}
        	System.out.print(nodo[n] + " ");
        }*/
        
    }
}