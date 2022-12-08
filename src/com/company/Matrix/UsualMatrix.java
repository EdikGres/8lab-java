package com.company.Matrix;

import java.util.Arrays;
import java.util.Random;

public class UsualMatrix {
    protected final Integer[][] arr;
    protected final int rows;
    protected final int columns;

    public UsualMatrix(int rows, int columns, int value) {
        this.rows = rows;
        this.columns = columns;
        arr = new Integer[rows][columns];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                arr[i][j] = value;
            }
        }
    }

    public UsualMatrix(int rows, int columns) {
        this(rows, columns, 0);
    }

    public int getRows() {
        return rows;
    }

    public int getColumns() {
        return columns;
    }

    public int getElement(int row, int column){
        if(row > this.rows || column > this.columns || row < 0 || column < 0)
            throw new RuntimeException("invalid value");

        return arr[row][column];
    }

    public void setElement(int row, int column, int value){
        if(row > this.rows || column > this.columns || row < 0 || column < 0)
            throw new RuntimeException("invalid value");
        arr[row][column] = value;
    }

    public UsualMatrix product(UsualMatrix b) {
        if (b == null)
            throw new RuntimeException("Matrix NULL parameter. NPE!");
        if (this.columns != b.rows) {
            throw new RuntimeException("Matrices of different sizes!");
        }
        UsualMatrix res = new UsualMatrix(this.rows, b.columns);
        for (int i = 0; i < this.rows; i++)
            for (int j = 0; j < b.columns; j++) {
                res.arr[i][j] = 0;
                for (int k = 0; k < this.columns; k++)
                    res.arr[i][j] += this.arr[i][k] * b.arr[k][j];
            }
        return res;
    }

    public static void GenerateRandom(UsualMatrix matrix, int bound){
        Random random = new Random();
        for (int i = 0; i < matrix.rows; i++){
            for (int j = 0; j < matrix.columns; j++){
                matrix.setElement(i,j, random.nextInt(bound));
            }
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder(); //not synchro and mutable
        for (int i = 0; i < rows; i++) {
            sb.append("[");
            for (int j = 0; j < columns; j++) {
                sb.append(arr[i][j]);
                if (j != columns - 1) //fix last space
                    sb.append("\t");
            }
            sb.append("\t]");
            if (i != rows - 1) //fix last new row
                sb.append("\n");
        }
        return sb.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof UsualMatrix)) return false;
        UsualMatrix matrix = (UsualMatrix) o;
        return rows == matrix.rows && columns == matrix.columns && Arrays.deepEquals(arr, matrix.arr);
    }
}
