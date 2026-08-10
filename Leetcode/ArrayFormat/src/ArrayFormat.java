public class ArrayFormat {
    public static String convertToCurly(String input) {
        // Replace [ with { and ] with }
        return input.replace('[', '{').replace(']', '}').replace('\"', '\'');
    }

    public static void main(String[] args) {
        String input = "[[1,2,2,3,5],[3,2,3,4,4],[2,4,5,3,1],[6,7,1,4,5],[5,1,1,2,4]]";
        String output = convertToCurly(input);
        System.out.println(output);
    }
}
