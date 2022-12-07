import java.util.Scanner;

public class interactiveTest {
    public static void main(String args[]) {
        System.out.println("Welcome to Jlisp, made by Gabe Kroeger, Riley Parker, Owen Russell");
        System.out.println("To interact with the program, type any lisp command that is specified in the readme as functionality");
        System.out.println("The program will return the value of each function you input in a list format");
        System.out.println("Enter exit at any point to quit the program\n");

        String input = "";
        Scanner s = new Scanner(System.in);
        Jlisp j = new Jlisp();
        System.out.print("jlisp> ");
        input = s.nextLine();
        while(!input.equals("exit")) {
            System.out.println(j.run(input));
            System.out.print("jlisp> ");
            input = s.nextLine();
        }
    }
}
