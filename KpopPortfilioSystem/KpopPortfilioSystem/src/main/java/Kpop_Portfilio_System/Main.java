/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Kpop_Portfilio_System;

/**
 *
 * @author yemyat
 */
import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {

    private static KpopPortfolioSystem PortfolioSystem = new KpopPortfolioSystem();

    public static void main(String[] args) {
        PortfolioSystem.loadDataFromFile("kpop_data.txt");

        Scanner scanner = new Scanner(System.in);
        int choice;

        do {
            System.out.println("\n=== Kpop Portfolio System ===");
            System.out.println("1. View Data");
            System.out.println("2. Add Data");
            System.out.println("3. Remove Data");
            System.out.println("4. Update Data");
            System.out.println("5. Sort Data");
            System.out.println("6. Searching Data");
            System.out.println("7. Exit");
            System.out.println("Enter your choice: ");

            try {
                choice = scanner.nextInt();
                scanner.nextLine(); // Consume the newline character

                switch (choice) {
                    case 1:
                        PortfolioSystem.viewData();
                        break;
                    case 2:
                        PortfolioSystem.addData(scanner);
                        break;
                    case 3:
                        PortfolioSystem.removeData(scanner);
                        break;
                    case 4:
                        PortfolioSystem.updateData(scanner);
                        break;
                    case 5:
                        PortfolioSystem.sortData(scanner);
                        break;
                    case 6:
                        PortfolioSystem.searchData(scanner);
                        break;
                    case 7:
                        PortfolioSystem.exit();
                        break;
                    default:
                        System.out.println("Error!! Choose correctly.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a valid number.");
                scanner.nextLine(); // Consume the invalid input
                choice = 0; // Set choice to 0 to force the loop to continue
            }
        } while (choice != 7);

        scanner.close();

    }
}
