package com.example.NQueenVis.Controller;

import com.example.NQueenVis.service.NQueenSolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/nqueen")
public class NQueenController {

    @Autowired
    private NQueenSolver solver;

    @PostMapping("/solve")
    public List<List<String>> solveNQueen(@RequestBody Map<String, Integer> request) {
        int n = request.get("n");
        return solver.solveNQueens(n);
    }
}