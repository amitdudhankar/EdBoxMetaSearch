package com.company;
import java.util.Scanner;

public class area_rectangle {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int length, breadth;
        System.out.println("This Application prints the area of the rectangle and takes the length and the breadth from the user");
        System.out.print("Enter the length of the rectangle: ");
        length = scan.nextInt();
        System.out.print("Enter the breadth of the rectangle: ");
        breadth = scan.nextInt();
        int area = length * breadth;
        System.out.println("The area of the rectangle is "+area+" cmsq.");
    }
}
