package com.company.Matrix;

import com.company.Matrix.UsualMatrix;

public class RowWorker implements Runnable{

    private UsualMatrix res;
    private UsualMatrix matrixA;
    private UsualMatrix matrixB;
    private final int row;

    public RowWorker(UsualMatrix res, UsualMatrix matrixA, UsualMatrix matrixB, int row) {
        this.res = res;
        this.matrixA = matrixA;
        this.matrixB = matrixB;
        this.row = row;
    }

    @Override
    public void run() {
        for (int i = 0; i < matrixB.columns; i++){
            res.setElement(row, i, 0);
            for (int j = 0; j < matrixA.columns; j++){
                res.setElement(row, i, res.getElement(row, i) + matrixA.getElement(row, j) * matrixB.getElement(j, i));
            }
        }
    }
}
