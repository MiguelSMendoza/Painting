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
	            
	            char[][] matrix = new char[N][M];
	            System.out.println("N:"+N+" M:"+M);
	            while (input.hasNextLine()) {
	                String line = input.nextLine();
	                System.out.println(line);
	            }
	            input.close();

	        } catch (Exception ex) {
	            ex.printStackTrace();
	        }
	}
}
