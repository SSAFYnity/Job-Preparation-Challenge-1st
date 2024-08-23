import java.util.*;

class Solution {
    
    String[][] chart = new String[51][51];
    int[][] cells = new int[51][51];
    List<String> answer = new LinkedList<>();
    
    public List<String> solution(String[] commands) {
        
        for (int i = 1; i < chart.length; i++) {
            for (int j = 1; j < chart.length; j++) {
                chart[i][j] = "";
                cells[i][j] = (i - 1) * 50 + j;
            }
        }
        for (String command : commands) {
            String query = command.replaceAll("[\\s,0-9,a-z]", "");
            String rawArgs = command.replaceAll("[A-Z]", "");
            String[] args = rawArgs.substring(1, rawArgs.length()).split(" ");
            switch(query) {
                case "UPDATE" -> {
                    if (args.length == 3) {
                        update(args);
                    } else {
                        updateAll(args);
                    }
                }
                case "MERGE" -> merge(args);
                case "UNMERGE" -> unmerge(args);
                case "PRINT" -> print(args);
            }
        }
        return answer;
    }
    
    private void update(String[] args){
        
        int r = Integer.parseInt(args[0]);
        int c = Integer.parseInt(args[1]);
        String value = args[2];
        
        int targetCellNum = cells[r][c];
        
        for (int i = 1; i < chart.length; i++) {
            for (int j = 1; j < chart.length; j++) {
                if (cells[i][j] == targetCellNum) {
                    chart[i][j] = value;
                }
            }
        }
    }
    
    private void updateAll(String[] args) {
        
        String target = args[0];
        String value = args[1];
        
        for (int i = 1; i < chart.length; i++) {
            for (int j = 1; j < chart.length; j++) {
                if (chart[i][j].equals(target)) {
                    chart[i][j] = value;
                }
            }
        }
    }
    
    private void merge(String[] args) {
        
        int r1 = Integer.parseInt(args[0]);
        int c1 = Integer.parseInt(args[1]);
        int r2 = Integer.parseInt(args[2]);
        int c2 = Integer.parseInt(args[3]);
        
        if (!(chart[r1][c1].isBlank() || chart[r2][c2].isBlank()) ||
           !chart[r1][c1].isBlank()) { // 두 셀 모두 값 가지거나, (r1, c1) 셀만 값 가짐
            updateMergedCell(r2, c2, r1, c1);
        } else {  // (r2, c2) 셀만 값 가지거나, 두 셀 모두 값 가지지 않음
            updateMergedCell(r1, c1, r2, c2);
        }
    }
    
    // (r1, c1)에 (r2, c2) 병합
    private void updateMergedCell(int r1, int c1, int r2, int c2) {
        
        int targetCellNum = cells[r1][c1];
        for (int i = 1; i < chart.length; i++) {
            for (int j = 1; j < chart.length; j++) {
                if (cells[i][j] == targetCellNum) {
                    cells[i][j] = cells[r2][c2];
                    chart[i][j] = chart[r2][c2];
                }
            }
        }
    }
    
    private void unmerge(String[] args) {
        
        int r = Integer.parseInt(args[0]);
        int c = Integer.parseInt(args[1]);
        
        int targetCellNum = cells[r][c];
        String value = chart[r][c];
        
        for (int i = 1; i < chart.length; i++) {
            for (int j = 1; j < chart.length; j++) {
                if (cells[i][j] == targetCellNum) {
                    cells[i][j] = (i - 1) * 50 + j;
                    chart[i][j] = "";
                }
            }
        }
        chart[r][c] = value;
    }
    
    private void print(String[] args) {
        
        int r = Integer.parseInt(args[0]);
        int c = Integer.parseInt(args[1]);
        
        if (chart[r][c].isBlank()) {
            answer.add("EMPTY");
        } else {
            answer.add(chart[r][c]);
        }
    }
}