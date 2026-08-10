public class Spreadsheet {

    int[][] spreadsheet;

    public Spreadsheet(int rows) {
        spreadsheet = new int[rows][26];
    }

    public int[] stringToCoords(String s) {
        int[] coords = new int[2];
        coords[1] = s.charAt(0) - 'A';
        s =  s.substring(1);
        coords[0] = Integer.parseInt(s) - 1;
        return coords;
    }

    public void setCell(String cell, int value) {
        int[] coords = stringToCoords(cell);
        spreadsheet[coords[0]][coords[1]] = value;
    }

    public void resetCell(String cell) {
        int[] coords = stringToCoords(cell);
        spreadsheet[coords[0]][coords[1]] = 0;
    }

    public int getValue(String formula) {
        String[] terms = formula.substring(1).split("\\+");
        int sum = 0;
        for (String term : terms) {
            if (Character.isDigit(term.charAt(0))) {
                sum += Integer.parseInt(term);
            } else {
                int[] coords = stringToCoords(term);
                sum += spreadsheet[coords[0]][coords[1]];
            }
        }
        return sum;
    }
}
