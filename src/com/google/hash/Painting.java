package com.google.hash;

import java.io.File;
import java.util.Scanner;

public class Painting {
	public static void main(String[] args) {
	        try {
	            File file = new File(args[0]);

	            Scanner input = new Scanner(file);
	            int N = input.nextInt();
	            int M = input.nextInt();
	            input.nextLine();
	            char[][] matrix = new char[N][M];
	            for (int i=0;i<N;i++) {
	            	String line = input.nextLine();
	            	for(int j=0;j<M;j++) 
	            		matrix[i][j] = line.charAt(j);
	            }
	            input.close();
	            
	            // Ya tenemos todo en matrix
	            System.out.println("Solucion");

	        } catch (Exception ex) {
	            ex.printStackTrace();
	        }
	}
}
