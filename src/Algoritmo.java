import java.io.*;
import java.util.ArrayList;
public class Algoritmo {
    public static void gravaTxt(String texto, String arquivo) {
        File arq;
        arq = new File(arquivo);
        try {
            FileOutputStream fos = new FileOutputStream(arq);
            fos.write(texto.getBytes());
            fos.close();
        }catch(FileNotFoundException e) {
            System.out.println(e);
        }catch(IOException e) {
            System.out.println(e);
        }
    }

    public static String leTxt(String arquivo) {
        int c;
        StringBuffer texto = new StringBuffer();
        try {
            FileReader arq = new FileReader(arquivo);
            while((c = arq.read()) != -1) {
                if (c == 13) {
                }
                else {
                    texto.append((char) c);
                    //texto = new StringBuffer(texto).append((char)c);
                }
            }
        } catch (IOException e) {
            System.out.println(e);
        }
        //System.out.println("Arquivo lido:\n" + texto + "\n\n");
        return texto.toString();
    }
    
    public int maiorGrau(int[][] vetor){
    	int[] maior = new int[2];
    	int cont = 0;
    	for(int i=0; i<vetor.length; i++){
    		cont = 0;
    		for(int j=0; j<vetor.length; j++){
    			if(vetor[i][j] == 1){
    				cont++;
    			}
    		}
    		if(cont > 0){
    			if(maior[0] == 0){
    				maior[0] = i;
    				maior[1] = cont;
    			}else{
    				if(cont > maior[1]){
    					maior[0] = i;
        				maior[1] = cont;
    				}
    			}
    		}
    	}
    	return maior[0];
    }
    
    public static void bubbleSort(int[] a) {
		  //Percorre todos os elementos do vetor (leght-1)
		  for(int i = 0; i < a.length-1; i++) {
			 //Percorre todos os elementos do vetor, do maior ao menor (9, 8, 7,...)
		     for(int j = 0; j < a.length-i-1; j++) {
		    	//Se o elemento do vetor atual for maior do que o do próximo, então troca o valor dos elementos
		        if(a[j] > a[j+1]){	
		           int temp = a[j];
		 		   a[j] = a[j+1];
		 		   a[j+1] = temp;
		        }
		     }
		  }
	}
    
    public void coloreVertice(int agente, int[][] vetor, int[] nodo){
    	ArrayList<Integer> cores = new ArrayList();
    	ArrayList<Integer> coresAdj = new ArrayList(); //Guarda cores existentes nos nodos adjacentes
    	ArrayList<Integer> coresPossiveis = new ArrayList(); //Guarda cores possíveis
    	ArrayList<Integer> nodosAdj = new ArrayList(); //Guarda Nodos Adj.
    	int valor = 0;
    	
    	/* Colore Vértice */
    	//Se ainda não foi criada uma cor, cria e insere ela no Agente...
    	if(cores.size() == 0){
			valor=1;
    		cores.add(valor);
			nodo[agente] = cores.get(0);
		}
    	//Caso contrário...
    	else{
    		//Procura pelos Nodos Adjacentes e guarda a cor desses em uma nova Lista... 
			for(int i=0; i<vetor.length; i++){
				if(vetor[agente][i] == 1){
					if(nodo[i] != 0){
						coresAdj.add(nodo[i]);
					}
				}
			}
			//Se não encontrar nenhum Nodo Adjacente, colore o Agente com a primeira Cor...
			if(coresAdj.size() == 0){
				nodo[agente] = cores.get(0);
			}
			//Caso contrário...
			else{
				//Compara as Cores dos Nodos Adjacentes com as Cores...
				//Se encontrar uma Cor diferentes das dos Nodos Adjacentes, guarda a Cor numa nova Lista...
				for(int j=0; j<coresAdj.size(); j++){
					for(int k=0; k<cores.size(); k++){
						if(cores.get(k) != coresAdj.get(j)){
							coresPossiveis.get(cores.get(k));
						}
					}
				}
				
				//Compara todas as cores dos Nodos Adjacentes com as da Cores Possíveis...
				//Se encontrar cores iguais nas duas Listas, remove essa cor da Cores Possíveis...
				if(coresPossiveis.size() > 0){
					for(int l=0; l<coresAdj.size(); l++){
						for(int m=0; m<coresPossiveis.size(); m++){
							if(coresAdj.get(l) == coresPossiveis.get(m)){
								coresPossiveis.remove(m);
							}
						}
					}
				}
				//Se Cores Possíveis tiver 1 ou mais cores, põe a primeira no Agente
				if(coresPossiveis.size() > 0){
					nodo[agente] = coresPossiveis.get(0);
				}
				//Caso contrário, cria uma nova cor e põe no Agente
				else{
					valor++;
					cores.add(valor);
					nodo[agente] = valor;
				}
				//Limpa Cores Possíveis e Cores Adj.
				coresPossiveis.removeAll(coresPossiveis);
				coresAdj.removeAll(coresAdj);
			}
		}
    	//Passa p/ próximo Nodo Adjacente
    	for(int n=0; n<vetor.length; n++){
    		if(vetor[agente][n] == 1){
    			if(nodo[n] == 0){
    				coloreVertice(n, vetor, nodo);
    			}
    		}
    	}
    	//return nodo[3];
    }
}