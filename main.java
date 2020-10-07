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
            

            while (entrada.hasNextInt()) { 
                ind_i = entrada.nextInt(); 
                break;
            }
            int[][] array = new int[ind_i][ind_i];
            System.out.print("El determinante de la matriz:");
            for(int i = 0; i<ind_i;i++){
                System.out.print("\n");
                for(int j = 0; j<ind_i;j++){
                    array[i][j]= entrada.nextInt();
                    System.out.print(array[i][j]+" ");
                }
            }
            int res = deter(array);
            System.out.println("\nes= "+res);
           
        }catch (Exception e) {
            System.out.println(e.toString());
        }
    }
    
    public static int deter(int[][] array){
        
        if(array.length==1){
            return array[0][0];
        }
        int det =0;
        int signo = 1;
        
        int[][] nueva_mat = new int[array.length-1][array.length-1];
        for(int i= 0; i< array.length;i++){
            for(int j = 0;j<array.length-1;j++){
                for(int k = 0;k<array.length-1;k++){
                    if(k<i){
                        nueva_mat[j][k]=array[j+1][k];
                    }else if(k>=i){
                        nueva_mat[j][k]=array[j+1][k+1];
                    }
                }
            }
            det = det+signo*array[0][i]*deter(nueva_mat);
                signo = signo*-1;
        }
        return det;
        
    }
}
