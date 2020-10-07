import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;

public class main {
    
    
    public static void main(String[] args) {
        int ind_i=0;
        
        Scanner leer = new Scanner(System.in);
        System.out.println("La primera línea del archivo debe ser la dimensión del arreglo, ejemplo: para una matriz de 3X3 debes poner 3");
        System.out.println("Introduce la matriz con los valores separados con espacios, ejemplo:");
        System.out.println("3");
        System.out.println("1 1 1");
        System.out.println("2 2 2");
        System.out.println("3 3 3");
        System.out.println("Introduza la dirección del archivo txt, incluyendo el nombre del archivo");
        String direccion = leer.nextLine();

        File f = new File(direccion);

        try  (Scanner entrada = new Scanner(f)) {
            
            while (entrada.hasNextInt()) { //se obtiene el tamaño de la matriz
                ind_i = entrada.nextInt(); 
                break;
            }
            int[][] array = new int[ind_i][ind_i];//se crea el array con las dimensiones dadas
            System.out.print("El determinante de la matriz:");
            for(int i = 0; i<ind_i;i++){
                System.out.print("\n");
                for(int j = 0; j<ind_i;j++){
                    array[i][j]= entrada.nextInt();//se introducen e imprimen los valores del array dados.
                    System.out.print(array[i][j]+" ");
                }
            }
            int res = deter(array); //se obtiene el valor de la determinante y se imprime
            System.out.println("\nes= "+res);
           
        }catch (Exception e) {
            System.out.println(e.toString());
        }
    }
    
    public static int deter(int[][] array){
        
        if(array.length==1){ //cuando se llame a las funciones, en caso se que el tamaño de la matriz sea 1, significa que su determinante es el mismo valor de la matriz de 1x1
            return array[0][0];
        }
        int det =0; //se inicializa donde se almacenará el determinante
        int signo = 1;//el signo será positivo en la primera repetición y cambiará dependiendo de los ciclos
        
        int[][] nueva_mat = new int[array.length-1][array.length-1];//se crea el sub array de tamaño -1 del array dado
        
        for(int i= 0; i< array.length;i++){//se repetirá el proceso las veces del tamaño de la matriz, para multiplicar cada elemento superior de la matriz con lo demas [0][0],[0][1]....[0][n]
            for(int j = 0;j<array.length-1;j++){//con el próximo par de ciclos se recorrerá la matriz para encontrar las submatrices
                for(int k = 0;k<array.length-1;k++){
                    if(k<i){//si k es menor a i, significa vamos a obtener valores que se encuentran en el límite izquierdo de la matriz
                        nueva_mat[j][k]=array[j+1][k];//agregamos los valores en la primera iteración que se encuentran por debajo de el límite superior en j, pero pegados al límite izquierdo en k
                    }else if(k>=i){//en otro caso se tomarán los demas valores 
                        nueva_mat[j][k]=array[j+1][k+1];
                    }
                }
            }
            det = det+signo*array[0][i]*deter(nueva_mat);//asignamos el valor de la determinante a la suma del mismo determinante con el signo, que multiplica a la posición i de la fila límite superior de la matriz, con la determinante de la submatriz obtenida, con lo que se vuelve a llamar a la función deter para obtenerlo 
                signo = signo*-1;//el signo cambia a negativo y positivo en cada iteración de i
        }
        return det;//se retorna la determinante
        
    }
}
