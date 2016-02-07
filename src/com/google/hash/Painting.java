package com.google.hash;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.Scanner;

public class Painting {
	static int N, M;
	static int R = 0, C = 1, S = 2;
	static char[][] matrix;

	public static void write(String str, boolean append) {
		if (str == "")
			return;
		PrintWriter writer;
		try {
			writer = new PrintWriter(new FileOutputStream(new File("commands.out"), append));
			writer.print(str);
			writer.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		try {
			File file = new File("right_angle.in");

			Scanner input = new Scanner(file);
			N = input.nextInt();
			M = input.nextInt();
			input.nextLine();
			matrix = new char[N][M];
			for (int i = 0; i < N; i++) {
				String line = input.nextLine();
				for (int j = 0; j < M; j++)
					matrix[i][j] = line.charAt(j);
			}
			input.close();
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < M; j++) {
					int[] square = checkSquare(i, j);
					write(paintSquare(square[R], square[C], square[S]), true);
				}
			}
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < M; j++)
					if (checkCell(matrix[i][j])) {
						int[] line = checkLine(i, j);
						write(paintLine(i, j, line[R], line[C]), true);
					}
			}
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < M; j++)
					if (matrix[i][j] == '-') {
						write("ERASE_CELL " + i + " " + j + "\n", true);
					}
			}
			writeResults();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	// public static int[] checkLine(int r, int c) {
	// int y=1;
	// while ((r + y) < N && matrix[r + y][c] == '#')
	// y++;
	// int row = r + y - 1;
	// int[] vector = { row, c };
	// return vector;
	// }

	// public static int[] checkLine(int r, int c) {
	// int x=1;
	// while ((c + x) < M && matrix[r][c + x] == '#')
	// x++;
	// int col = c + x - 1;
	// int[] vector = { r, col };
	// return vector;
	// }

	public static int[] checkLine(int r, int c) {
		int x = 1;
		int y = 1;
		while ((c + x) < M && matrix[r][c + x] == '#')
			x++;
		while ((r + y) < N && matrix[r + y][c] == '#')
			y++;
		int max = Math.max(x - 1, y - 1);
		if (max == x - 1) {
			int col = c + x - 1;
			int[] vector = { r, col };
			return vector;
		} else {
			int row = r + y - 1;
			int[] vector = { row, c };
			return vector;
		}
	}

	public static void printMatrix() {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++)
				System.out.print(matrix[i][j]);
			System.out.println();
		}
	}

	public static int[] checkSquare(int r, int c) {
		int s = 1;
		int area = 1;
		int centerR = -1;
		int centerC = -1;
		int S = 1;
		int lado = 2 * s + 1;
		while (r + lado < N && c + lado < M) {
			area = 0;
			for (int i = r; i < r + lado; i++) {
				for (int j = c; j < c + lado; j++) {
					if (matrix[i][j] == '#')
						area++;
				}
			}
			int completo = lado * lado;
			if (completo - area < 2) {
				centerR = r + s;
				centerC = c + s;
				S = s;
			}
			s++;
			lado = 2 * s + 1;
		}
		int[] value = { centerR, centerC, S };
		return value;
	}

	public static String paintSquare(int r, int c, int s) {
		if (r == -1 || c == -1)
			return "";
		int lado = 2 * s + 1;
		int inicioR = r - s;
		int inicioC = c - s;
		for (int i = inicioR; i < inicioR + lado; i++)
			for (int j = inicioC; j < inicioC + lado; j++) {
				if (matrix[i][j] == '#')
					matrix[i][j] = '.';
				else
					matrix[i][j] = '-';
			}
		return "PAINT_SQUARE " + r + " " + c + " " + s + "\n";
	}

	public static String paintLine(int r1, int c1, int r2, int c2) {
		if (r1 == r2) {
			int length = Math.abs(c2 - c1);
			for (int i = 0; i <= length; i++)
				matrix[r1][c1 + i] = '.';
		} else if (c1 == c2) {
			int length = Math.abs(r2 - r1);
			for (int i = 0; i <= length; i++)
				matrix[r1 + i][c1] = '.';
		}
		return "PAINT_LINE " + r1 + " " + c1 + " " + r2 + " " + c2 + "\n";
	}

	public static boolean checkCell(char c) {
		if (c == '#')
			return true;
		else
			return false;
	}

	public static boolean writeResults() {
		try {
			int lines = countLines("commands.out");
			File file = new File("commands.out");
			Scanner input = new Scanner(file);
			PrintWriter writer = new PrintWriter("solution.out");
			writer.println(lines);
			while (input.hasNextLine()) {
				writer.println(input.nextLine());
			}
			writer.close();
			input.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return true;
	}

	public static int countLines(String filename) throws IOException {
		InputStream is = new BufferedInputStream(new FileInputStream(filename));
		try {
			byte[] c = new byte[1024];
			int count = 0;
			int readChars = 0;
			boolean empty = true;
			while ((readChars = is.read(c)) != -1) {
				empty = false;
				for (int i = 0; i < readChars; ++i) {
					if (c[i] == '\n') {
						++count;
					}
				}
			}
			return (count == 0 && !empty) ? 1 : count;
		} finally {
			is.close();
		}
	}

}
