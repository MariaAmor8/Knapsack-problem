

public class DynamicProgrammingKnapsackAlgorithm implements KnapsackAlgorithm {

	@Override
	public boolean[] solveKnapsack(int[] utilities, int[] weights, int maxWeight) {
		// TODO: Step one find max utility
		int[][] matriz = new int[utilities.length+1][maxWeight+1];
		calcularUtilidades2(matriz, maxWeight,utilities, weights, utilities.length);
		String fila = "";
		for(int i=0; i<matriz.length;i++) {
			for(int j=0; j < matriz[0].length;j++) {
				fila = fila + matriz[i][j]+", ";
			}
			//System.out.println(fila);
		}
		
		boolean[] objetos = new boolean[utilities.length];
		// TODO: Step two: traceback to find subset
		int j = maxWeight;
		for(int i = utilities.length; i>0;i--) {
			
				if(matriz[i-1][j] != matriz[i][j]) {
					objetos[i-1] = true;
					j = j - weights[i-1];
				}
			
		}
		for(int i = 0; i<utilities.length;i++) {
			System.out.println("objeto " + i+ " -> " + objetos[i]);
		}
		
		return objetos;
	}
	
	public void calcularUtilidades(int[][]matriz, int pesoMax, int[]utilities, int [] weights,int iElementos){
		
		for(int i = 1;i<=iElementos;i++) {
			
			int peso = weights[i-1];
			int utilidad = utilities[i-1];
			
			for(int j = 1; j<= pesoMax; j++) {
				matriz[i][j] = matriz[i-1][j];
				
				if(j>= peso && matriz[i-1][j-peso] + utilidad > matriz[i][j]) {
					matriz[i][j] = matriz[i-1][j-peso] + utilidad;
				}
			}
		}
	}
	
	public void calcularUtilidades2(int[][]matriz, int pesoMax, int[]utilities, int [] weights,int iElementos){

		for(int i = 1;i<iElementos+1;i++) {

			int peso = weights[i-1];
			int utilidad = utilities[i-1];

			for(int j = 1; j< pesoMax+1; j++) {
				matriz[i][j] = matriz[i-1][j];

				if(j> peso+1 && matriz[i-1][j-peso] + utilidad > matriz[i][j]) {
					matriz[i][j] = matriz[i-1][j-peso] + utilidad;
				}
			}
		}
	}

}
