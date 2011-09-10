
import java.util.*;

public class Frequencias<N, A> {
    
	private class Node <T> {
		private T agente;
		private int freq = 1;
		
		public Node(T n) {
                    this.agente = n;
                    freq = 1;
		}
		public T getElem() {
                    return agente;
		}
                public int getFreq(){
                    return freq;
                }
		public void setAgente(T agente) {
                    this.agente = agente;
		}
                public void setFreq(int f){
                    this.freq = f;
                }
	}
	
	private static final int MAX = 1000;
	private List<Node<N>> nodeList = new ArrayList<Node<N>>();
	private A [][] matAdj = (A[][]) new Object[MAX][MAX];
	
	public int indexOfNode(N elem) {
		int res = -1;		
		for (int i = 0; i < nodeList.size() && res == -1; i++)
			if (nodeList.get(i).getElem().equals(elem))
				res = i;
		return res;
	}
	
	public void addNode(N elem) {
		Node<N> novo = new Node<N>(elem);                
		if (nodeList.size() < MAX)
			if (this.indexOfNode(elem) == -1) 
				nodeList.add(novo);
			else
				throw new IllegalArgumentException();
		else
			throw new OutOfMemoryError();
	}
	
	public void addEdge(N orig, N dest, A rot) {
		int posOrig = this.indexOfNode(orig);
		int posDest = this.indexOfNode(dest);
		
		if (posOrig == -1 || posDest == -1)
			throw new IllegalArgumentException("InvNode");
		else if (matAdj[posOrig][posDest] == null){
				 matAdj[posOrig][posDest] = rot;
                                 matAdj[posDest][posOrig] = rot;
                        }
		else 
			throw new IllegalArgumentException("InvLabel");
	}
	
	public List<Node<N>> getAllNeighbours(N elem){		
		List<Node<N>> res = new ArrayList<Node<N>>();
		int index = this.indexOfNode(elem);
		
		if (index < 0)
			throw new IllegalArgumentException("InvNode");
		else {
			for (int i = 0; i < nodeList.size(); i++)
				if (matAdj[index][i] != null)
					res.add(nodeList.get(i));
		}				
		return(res);	
	}
        
        public void distrFreq(){        
            for (int i = 0; i < nodeList.size(); i++){
                for (int j = 0; j < nodeList.size(); j++){
                    if (matAdj[i][j] != null){
                        if(nodeList.get(i).getFreq() == nodeList.get(j).getFreq()){
                            nodeList.get(j).setFreq(nodeList.get(j).getFreq()+1);
                        }
                    }
                }
            }
        }
        
        public List<Integer> exibeNodos(){        		
            List<Integer> res = new ArrayList<Integer>();
            for (int i = 0; i < nodeList.size(); i++)
                res.add(nodeList.get(i).getFreq());			
            return(res);
        }
        
	public static void main(String [] argc) {
		Frequencias<String, Integer> mapa = new Frequencias<String, Integer>();
		List<Integer> caminho = new ArrayList<Integer>();
		
		mapa.addNode("A");
		mapa.addNode("B");
		mapa.addNode("C");
		mapa.addNode("D");
                mapa.addNode("E");
		
		mapa.addEdge("A", "B", 1);
		mapa.addEdge("A", "C", 1);
		mapa.addEdge("B", "C", 1);
		mapa.addEdge("C", "D", 1);
                mapa.addEdge("A", "D", 1);
                mapa.addEdge("D", "E", 1);
                mapa.addEdge("C", "E", 1);
                
                mapa.distrFreq();
                
                caminho = mapa.exibeNodos();
                
		System.out.println(caminho);
		
	}
}
