package org.example.lyukutan;
import java.io.PrintStream;
import java.util.Scanner;
public class task1 {
    public static void main(String[] args ){
        Scanner console = new Scanner(System.in);
        int first = console.nextInt();
        int second = console.nextInt();
        int third = console.nextInt();
        PrintStream result = first == second && first == third ?
                System.out.printf("%d, %d, %d", first,second,third) : first == second  ?
                System.out.printf("%d, %d",first,second) : first == third ?  System.out.printf("%d, %d",first,third) : second == third ?
                System.out.printf("%d, %d",second, third): System.out.printf("Нет дубликатов");
        System.out.println(result);
    }
}
