import java.util.Scanner;
import java.util.Vector;

/**
 * Created by msrabon on 12-Oct-16.
 * Project Name: K-MAP Minimizer SOP & POS.
 */
public class Karnaugh_map {

    final String[] bit_string_3 = {"000", "001", "010", "011", "100", "101", "110", "111"};
//    final String[] bit_string_4 = {"0000", "0001", "0010", "0011", "0100", "0101", "0110", "0111", "1000", "1001", "1010", "1011", "1100", "1101", "1110", "1111"};

    boolean[] map = new boolean[16];
    boolean[] map_clone;

    final int[][] kmap_3 = new int[2][4];
//    final int[][] kmap_4 = new int[4][4];

    Vector solutions = new Vector();

    public void test_run() {
        map[1] =true;
        map[2] =true;
//        map[3] =true;
        map[4] =true;
        map[7] =true;
        map_clone = map.clone();
    }

    void initiate_Kmap_three() {
        Scanner scan = new Scanner(System.in);
        int x;
        int index = 0;
        System.out.println("Insert '-1' to stop input");
        System.out.print("Enter minterm's(1's) Location : ");
        while ((x = scan.nextInt()) != -1) {
            if (x > 3 && index == 0) {
                index++;
            }
            if (x <= 3 && index == 1) {
                index--;
            }
            if (x < 8) {
                map[x] = true;
                kmap_3[index][x % 4] = 1;
            } else {
                System.out.println("Invalid location.");
            }
            System.out.print("Enter 1's Location (insert '-1' to stop input): ");
        }

//        index = 0;
//        System.out.print("Enter Don't care Location (insert '-1' to stop input): ");
//        while ((x = scan.nextInt()) != -1) {
//            if (x > 3 && index == 0) {
//                index++;
//            }
//            if (x <= 3 && index == 1) {
//                index--;
//            }
//            if (kmap_3[index][x % 4] == 1 && x < 8) {
//                System.out.println("This location is occupied by 1's.\nTry again.");
//            }
//            if (x < 8) {
//                kmap_3[index][x % 4] = 2;
//            } else {
//                System.out.println("Invalid location.");
//            }
//            System.out.print("Enter Don't care Location (insert '-1' to stop input): ");
//        }

        map_clone = map.clone();
    }

//    void initiate_Kmap_4() {
//        Scanner scan = new Scanner(System.in);
//        int x;
//        int index = 0;
//        System.out.print("Enter 1's Location (insert '-1' to stop input): ");
//        while ((x = scan.nextInt()) != -1) {
//            if (x > 3 && index == 0) {
//                index++;
//            }
//            if (x <= 3 && index == 1) {
//                index--;
//            }
//            if (x < 8) {
//                map[x] = true;
//                kmap_3[index][x % 4] = 1;
//            } else {
//                System.out.println("Invalid location.");
//            }
//            System.out.print("Enter 1's Location (insert '-1' to stop input): ");
//        }
//
//        index = 0;
//        System.out.print("Enter Don't care Location (insert '-1' to stop input): ");
//        while ((x = scan.nextInt()) != -1) {
//            if (x > 3 && index == 0) {
//                index++;
//            }
//            if (x <= 3 && index == 1) {
//                index--;
//            }
//            if (kmap_3[index][x % 4] == 1 && x < 8) {
//                System.out.println("This location is occupied by 1's.\nTry again.");
//            }
//            if (x < 8) {
//                kmap_3[index][x % 4] = 2;
//            } else {
//                System.out.println("Invalid location.");
//            }
//            System.out.print("Enter Don't care Location (insert '-1' to stop input): ");
//        }
//
//    }

    /**
     * Method : k_map_solver_three
     * Details: Karnaugh Map solver for Three Variable's
     *
     * Currently this method does not take any input parameter as Variable "map" is declared as global variable.
     *
     */
    void k_mapSolver_three() { // K-map 3 variable soluion.

        if (map[0] && map[1] && map[2] && map[3]) { // {0,1,2,3}
            solutions.addElement(convertToVar(bit_string_3[0], bit_string_3[3]));
            map_clone[0] = false;
            map_clone[1] = false;
            map_clone[2] = false;
            map_clone[3] = false;
        } else if (map[4] && map[5] && map[6] && map[7]) { // {4,5,6,7}
            solutions.addElement(convertToVar(bit_string_3[4], bit_string_3[7]));
            map_clone[4] = false;
            map_clone[5] = false;
            map_clone[6] = false;
            map_clone[7] = false;
        }

        for (int i = 0; i < 3; i++) {
            if (i == 0 || i == 1) {
                if (map[i] && map[i + 2] && map[i + 4] && map[i + 6]) { // {0,2,4,6} and {1,3,5,7}
                    solutions.addElement(convertToVar(bit_string_3[i], bit_string_3[i + 6]));
                    map_clone[i] = false;
                    map_clone[i + 2] = false;
                    map_clone[i + 4] = false;
                    map_clone[i + 6] = false;

                }
            }
            if (i != 1 && map[i] && map[i + 1] && map[i + 4] && map[i + 5]) { //{0,1,4,5} , {2,3,6,7}
                solutions.addElement(convertToVar(bit_string_3[i], bit_string_3[i + 5]));
                map_clone[i] = false;
                map_clone[i + 1] = false;
                map_clone[i + 4] = false;
                map_clone[i + 5] = false;
            }
        }

        for (int i = 0; i < 8; i++) {
            if (i == 0 || i == 4) {
                if (map_clone[i] && !map[i + 1] && !map[i + 2] && !map[i + 4]) {
                    solutions.addElement(convertToVar(bit_string_3[i], bit_string_3[i]));
                } else {
                    if (map_clone[i] && map[i + 1]) {
                        solutions.addElement(convertToVar(bit_string_3[i], bit_string_3[i + 1]));
//                        map_clone[i] = false;
//                        map_clone[i + 1] = false;
                    }

                    if (map_clone[i] && map[i + 2]) {
                        solutions.addElement(convertToVar(bit_string_3[i], bit_string_3[i + 2]));
//                        map_clone[i] = false;
//                        map_clone[i + 2] = false;
                    }

                    if (map_clone[i] && map[i + 4] && i < 3) {
                        solutions.addElement(convertToVar(bit_string_3[i], bit_string_3[i + 4]));
//                        map_clone[i] = false;
//                        map_clone[i + 4] = false;
                    }
                }
            } else if (i == 1 || i == 5) {
                if(map_clone[i] && !map[i+2] && !map[i+4]){
                    solutions.addElement(convertToVar(bit_string_3[i], bit_string_3[i]));
                }else {
                    if (map_clone[i] && map[i + 2]) {
                        solutions.addElement(convertToVar(bit_string_3[i], bit_string_3[i + 2]));
//                        map_clone[i] = false;
                        map_clone[i + 2] = false;
                    }
                    if (map_clone[i] && map_clone[i + 4] && i < 4) {
                        solutions.addElement(convertToVar(bit_string_3[i], bit_string_3[i + 4]));
//                        map_clone[i] = false;
//                        map_clone[i + 4] = false;
                    }
                }
            } else {
                if(map_clone[i] && !map[i+1] && !map[i+4] && i!=3){
                    solutions.addElement(convertToVar(bit_string_3[i], bit_string_3[i]));
                }else {
                    if (map_clone[i] && map[i + 1] && i!=3) {
                        solutions.addElement(convertToVar(bit_string_3[i], bit_string_3[i + 1]));
                    }
                    if (i < 4 && map_clone[i] && map_clone[i + 4]) {
                        solutions.addElement(convertToVar(bit_string_3[i], bit_string_3[i + 4]));
//                        map_clone[i] = false;
//                        map_clone[i + 4] = false;
                    }
                }
            }
        }
    }

    /**
     * Shows the generated solution.
     */
    protected void showResult() {
        boolean i =false;
        System.out.print("\n\n Solution (SOP): ");
        for (Object solution : solutions) {
            System.out.print((i) ? " + " + solution.toString() : solution.toString());
            i = true;
        }
    }

    /**
     * Shows Karnaugh Map as truth table
     */
    public void showKmap(){
        System.out.println("");
        for (int i = 0; i < map.length/2 ; i++) {
            if (i==4){
                System.out.println("");
            }
            if (i == 2 || i== 6){
                System.out.print(map[i+1] + " ");
            }else if(i==3 || i==7){
                System.out.print(map[i-1] + " ");
            }else {
                System.out.print(map[i] + " ");
            }
        }
    }

    /**
     * Method : ConverttToString
     *
     * This method takes two string's value as parameter.
     * parameter Strings contains binary digits for comparison.
     * this method will compare all the bit's one by one
     * if the bit matches then will be convert to Variable form like A,B,C
     * this method also checks if the common bit is '0' Or '1'
     * if common bit is '1' then solution will be A OR B OR C
     * if common bit is '0' then solution will be A' OR B' OR C'
     * here ['] means not. like A' = !A = A(not)
     *
     *
     * There is 3 case scenerio's :
     *                              1. at least 1 bit is common where group of 4 minterm's is present.
     *                              2. at least 2 bit's are common where pair of 2 minterm's is present.
     *                              3. all 3 bit's are common where any pair or group is not present.
     *
     * @param s
     * @param s3
     * @return solution
     */
    private String convertToVar(String s, String s3) {
        String solution = "";

        if (String.valueOf(s.charAt(0)).equals(String.valueOf(s3.charAt(0)))) {
            if (String.valueOf(s.charAt(0)).equals("0")) {
                solution += "A'";
            } else {
                solution += "A";
            }
        }
        if (String.valueOf(s.charAt(1)).equals(String.valueOf(s3.charAt(1)))) {
            if (String.valueOf(s.charAt(1)).equals("0")) {
                solution += "B'";
            } else {
                solution += "B";
            }
        }
        if (String.valueOf(s.charAt(2)).equals(String.valueOf(s3.charAt(2)))) {
            if (String.valueOf(s.charAt(2)).equals("0")) {
                solution += "C'";
            } else {
                solution += "C";
            }
        }
        if (s.length() > 3) {
            if (String.valueOf(s.charAt(3)).equals(String.valueOf(s3.charAt(3)))) {
                if (String.valueOf(s.charAt(2)).equals("0")) {
                    solution += "D'";
                } else {
                    solution += "D";
                }
            }
        }
        return solution;
    }

}

