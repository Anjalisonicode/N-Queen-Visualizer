package com.example.NQueenVis.service;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class NQueenSolver {

    public List<List<String>> solveNQueens(int n) {
        List<List<String>> solutions = new ArrayList<>();
        int[] queens = new int[n];
        Arrays.fill(queens, -1);
        solve(0, n, queens, solutions);
        return solutions;
    }

    private void solve(int row, int n, int[] queens, List<List<String>> solutions) {
        if (row == n) {
            solutions.add(generateBoard(queens, n));
            return;
        }
        for (int col = 0; col < n; col++) {
            if (isValid(row, col, queens)) {
                queens[row] = col;
                solve(row + 1, n, queens, solutions);
                queens[row] = -1;
            }
        }
    }

    private boolean isValid(int row, int col, int[] queens) {
        for (int i = 0; i < row; i++) {
            if (queens[i] == col || Math.abs(queens[i] - col) == row - i) {
                return false;
            }
        }
        return true;
    }

    private List<String> generateBoard(int[] queens, int n) {
        List<String> board = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            char[] row = new char[n];
            Arrays.fill(row, '-');
            if (queens[i] != -1) {
                row[queens[i]] = 'Q';
            }
            board.add(new String(row));
        }
        return board;
    }
}
