/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Kpop_Portfilio_System;

/**
 *
 * @author yemyat
 */
import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.InputMismatchException;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

public class KpopPortfolioSystem {

    private static List<KpopArtist> kpopArtists = new ArrayList<>();

    public void viewData() {
        if (kpopArtists.isEmpty()) {
            System.out.println("No data available.");
            return;
        }

        System.out.println("\n=== Kpop Artist Data ===");
        for (KpopArtist artist : kpopArtists) {
            System.out.println(artistToString(artist));
        }
    }

    public String artistToString(KpopArtist artist) {
        return "Artist Name: " + artist.getArtistName()
                + ", Group: " + artist.getGroup()
                + ", Age: " + artist.getAge()
                + ", Position: " + artist.getPosition()
                + ", Debut Year: " + artist.getDebutYear()
                + ", Famous Song: " + artist.getFamousSong();
    }

    public void addData(Scanner scanner) {
        System.out.println("Enter Artist Name: ");
        String artistName = scanner.nextLine();

        System.out.println("Enter Group: ");
        String group = scanner.nextLine();

        int age = 0;
        while (true) {
            try {
                System.out.println("Enter Age: ");
                age = scanner.nextInt();
                if (age >= 5 && age <= 99) {
                    break;
                } else {
                    System.out.println("Invalid input. Age should be between 5 and 99.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Age should be a number.");
                scanner.nextLine(); // Consume the invalid input
            }
        }
        scanner.nextLine(); // Consume the newline character after reading the age

        List<String> positionsList = new ArrayList<>();
        while (true) {
            System.out.println("Enter Position(s) (Leader, Center, Visual, Main Vocal, Lead Vocal, Sub Vocal, Main Dancer, Lead Dancer, Main Rapper): ");
            String positionsInput = scanner.nextLine();

            String[] positionsArray = positionsInput.split(",");
            for (String position : positionsArray) {
                String trimmedPosition = position.trim();
                if (trimmedPosition.matches("Leader|Center|Visual|Main Vocal|Lead Vocal|Sub Vocal|Main Dancer|Lead Dancer|Main Rapper")) {
                    positionsList.add(trimmedPosition);
                } else {
                    System.out.println("Invalid input. Position '" + trimmedPosition + "' is not valid. Ignored.");
                }
            }

            if (!positionsList.isEmpty()) {
                break;
            }
        }

        int debutYear = 0;
        while (true) {
            try {
                System.out.println("Enter Debut Year: ");
                debutYear = scanner.nextInt();
                if (debutYear >= 1990 && debutYear <= 2024) {
                    break;
                } else {
                    System.out.println("Invalid input. Debut Year should be between 1990 and 2024.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Debut Year should be a number.");
                scanner.nextLine(); // Consume the invalid input
            }
        }
        scanner.nextLine(); // Consume the newline character after reading the debut year

        System.out.println("Enter Famous Song: ");
        String famousSong = scanner.nextLine();

        KpopArtist newArtist = new KpopArtist(artistName, group, age, String.join(", ", positionsList), debutYear, famousSong);
        kpopArtists.add(newArtist);

        System.out.println("Data added successfully!");
    }

    public void removeData(Scanner scanner) {
        if (kpopArtists.isEmpty()) {
            System.out.println("No data available to remove.");
            return;
        }

        System.out.println("\n=== Remove Kpop Artist ===");
        System.out.println("Enter Artist Name or Group to remove: ");
        String searchTerm = scanner.nextLine();

        int numRemoved = 0;
        Iterator<KpopArtist> iterator = kpopArtists.iterator();
        while (iterator.hasNext()) {
            KpopArtist artist = iterator.next();
            if (artist.getArtistName().equalsIgnoreCase(searchTerm) || artist.getGroup().equalsIgnoreCase(searchTerm)) {
                iterator.remove();
                numRemoved++;
            }
        }

        if (numRemoved > 0) {
            System.out.println(numRemoved + " data entry(s) removed successfully.");
        } else {
            System.out.println("No matching data found.");
        }
    }

    public void updateData(Scanner scanner) {
        if (kpopArtists.isEmpty()) {
            System.out.println("No data available to update.");
            return;
        }

        System.out.println("\n=== Update Kpop Artist ===");
        System.out.println("Enter Artist Name to update: ");
        String searchTerm = scanner.nextLine();

        boolean found = false;
        for (KpopArtist artist : kpopArtists) {
            if (artist.getArtistName().equalsIgnoreCase(searchTerm)) {
                found = true;
                System.out.println("\nSelect the data to update:");
                System.out.println("1. Artist Name");
                System.out.println("2. Group");
                System.out.println("3. Age");
                System.out.println("4. Position");
                System.out.println("5. Debut Year");
                System.out.println("6. Famous Song");
                System.out.println("Enter your choice: ");
                int choice = scanner.nextInt();
                scanner.nextLine(); // Consume the newline character

                switch (choice) {
                    case 1:
                        System.out.println("Enter new Artist Name: ");
                        String newArtistName = scanner.nextLine();
                        // Add exception handling for Artist Name (only letters allowed)
                        artist.setArtistName(newArtistName);
                        break;
                    case 2:
                        System.out.println("Enter new Group: ");
                        String newGroup = scanner.nextLine();
                        artist.setGroup(newGroup);
                        break;
                    case 3:
                        while (true) {
                            try {
                                System.out.println("Enter new Age: ");
                                int newAge = Integer.parseInt(scanner.nextLine());
                                if (newAge < 5 || newAge > 99) {
                                    System.out.println("Invalid input. Age should be between 5 and 99.");
                                    continue;
                                }
                                artist.setAge(newAge);
                                break;
                            } catch (NumberFormatException e) {
                                System.out.println("Invalid input. Age should be a number.");
                            }
                        }
                        break;
                    case 4:
                        System.out.println("Enter new Position(s) (separated by ','): ");
                        String newPosition = scanner.nextLine();
                        List<String> validPositions = Arrays.asList(
                                "Leader", "Center", "Visual", "Main Vocal",
                                "Lead Vocal", "Sub Vocal", "Main Dancer",
                                "Lead Dancer", "Main Rapper"
                        );
                        // Add exception handling for Position (must be one of the predefined values)
                        if (Arrays.stream(newPosition.split(",")).allMatch(validPositions::contains)) {
                            artist.setPosition(newPosition);
                        } else {
                            System.out.println("Invalid input. Position should be one of the predefined values.");
                        }
                        break;
                    case 5:
                        while (true) {
                            try {
                                System.out.println("Enter new Debut Year: ");
                                int newDebutYear = Integer.parseInt(scanner.nextLine());
                                if (newDebutYear < 1990 || newDebutYear > 2024) {
                                    System.out.println("Invalid input. Debut Year should be between 1990 and 2024.");
                                    continue;
                                }
                                artist.setDebutYear(newDebutYear);
                                break;
                            } catch (NumberFormatException e) {
                                System.out.println("Invalid input. Debut Year should be a number.");
                            }
                        }
                        break;
                    case 6:
                        System.out.println("Enter new Famous Song: ");
                        String newFamousSong = scanner.nextLine();
                        artist.setFamousSong(newFamousSong);
                        break;
                    default:
                        System.out.println("Invalid choice. No data updated.");
                }

                System.out.println("Successfully Updated!");
                break; // We found the artist, no need to search further
            }
        }

        if (!found) {
            System.out.println("Data not found.");
        }
    }

    private static void mergeSort(List<KpopArtist> list, Comparator<KpopArtist> comparator) {
        if (list.size() <= 1) {
            return;
        }

        int mid = list.size() / 2;
        List<KpopArtist> left = new ArrayList<>(list.subList(0, mid));
        List<KpopArtist> right = new ArrayList<>(list.subList(mid, list.size()));

        mergeSort(left, comparator);
        mergeSort(right, comparator);

        merge(list, left, right, comparator);
    }

    private static void merge(List<KpopArtist> list, List<KpopArtist> left, List<KpopArtist> right, Comparator<KpopArtist> comparator) {
        int leftIndex = 0;
        int rightIndex = 0;
        int listIndex = 0;

        while (leftIndex < left.size() && rightIndex < right.size()) {
            KpopArtist leftArtist = left.get(leftIndex);
            KpopArtist rightArtist = right.get(rightIndex);

            if (comparator.compare(leftArtist, rightArtist) <= 0) {
                list.set(listIndex++, leftArtist);
                leftIndex++;
            } else {
                list.set(listIndex++, rightArtist);
                rightIndex++;
            }
        }

        while (leftIndex < left.size()) {
            list.set(listIndex++, left.get(leftIndex++));
        }

        while (rightIndex < right.size()) {
            list.set(listIndex++, right.get(rightIndex++));
        }
    }

    public void sortData(Scanner scanner) {
        if (kpopArtists.isEmpty()) {
            System.out.println("No data available to sort.");
            return;
        }

        System.out.println("\n=== Sort Kpop Artist Data ===");
        System.out.println("Choose the field to sort by:");
        System.out.println("1. Artist Name");
        System.out.println("2. Group");
        System.out.println("3. Age");
        System.out.println("4. Position");
        System.out.println("5. Debut Year");
        System.out.println("6. Famous Song");
        System.out.println("Enter your choice: ");
        int choice = scanner.nextInt();
        scanner.nextLine(); // Consume the newline character

        Comparator<KpopArtist> comparator = null;
        switch (choice) {
            case 1:
                System.out.println("Choose sorting order for Artist Name:");
                System.out.println("1. A-Z");
                System.out.println("2. Z-A");
                System.out.println("Enter your choice: ");
                int artistNameSortChoice = scanner.nextInt();
                scanner.nextLine(); // Consume the newline character

                comparator = Comparator.comparing(KpopArtist::getArtistName);
                if (artistNameSortChoice == 2) {
                    comparator = comparator.reversed();
                }
                break;
            case 2:
                System.out.println("Choose sorting order for Group:");
                System.out.println("1. Ascending Order");
                System.out.println("2. Descending Order");
                System.out.println("Enter your choice: ");
                int groupSortChoice = scanner.nextInt();
                scanner.nextLine(); // Consume the newline character

                comparator = Comparator.comparing(KpopArtist::getGroup);
                if (groupSortChoice == 2) {
                    comparator = comparator.reversed();
                }
                break;
            case 3:
                System.out.println("Choose sorting order for Age:");
                System.out.println("1. Youngest to Oldest");
                System.out.println("2. Oldest to Youngest");
                System.out.println("Enter your choice: ");
                int ageSortChoice = scanner.nextInt();
                scanner.nextLine(); // Consume the newline character

                comparator = Comparator.comparingInt(KpopArtist::getAge);
                if (ageSortChoice == 2) {
                    comparator = comparator.reversed();
                }
                break;
            case 4:
                // Sorting by Position - Use predefined order
                comparator = Comparator.comparing((KpopArtist artist) -> {
                    List<String> positions = Arrays.asList(
                            "Leader", "Center", "Visual", "Main Vocal",
                            "Lead Vocal", "Sub Vocal", "Main Dancer",
                            "Lead Dancer", "Main Rapper"
                    );
                    int positionIndex = positions.indexOf(artist.getPosition());
                    return positionIndex == -1 ? Integer.MAX_VALUE : positionIndex;
                });
                break;
            case 5:
                System.out.println("Choose sorting order for Debut Year:");
                System.out.println("1. Longest to Newest");
                System.out.println("2. Newest to Longest");
                System.out.println("Enter your choice: ");
                int debutYearSortChoice = scanner.nextInt();
                scanner.nextLine(); // Consume the newline character

                comparator = Comparator.comparingInt(KpopArtist::getDebutYear);
                if (debutYearSortChoice == 2) {
                    comparator = comparator.reversed();
                }
                break;
            case 6:
                System.out.println("Choose sorting order for Famous Song:");
                System.out.println("1. Ascending Order");
                System.out.println("2. Descending Order");
                System.out.println("Enter your choice: ");
                int famousSongSortChoice = scanner.nextInt();
                scanner.nextLine(); // Consume the newline character

                comparator = Comparator.comparing(KpopArtist::getFamousSong);
                if (famousSongSortChoice == 2) {
                    comparator = comparator.reversed();
                }
                break;
            default:
                System.out.println("Invalid choice. No sorting performed.");
                return;
        }

        mergeSort(kpopArtists, comparator);
        System.out.println("Data sorted successfully.");
        viewData();
    }

    public void searchData(Scanner scanner) {
        if (kpopArtists.isEmpty()) {
            System.out.println("No data available to search.");
            return;
        }

        System.out.println("\n=== Search Kpop Artist Data ===");
        System.out.println("Choose the field to search by:");
        System.out.println("1. Artist Name");
        System.out.println("2. Group");
        System.out.println("3. Age");
        System.out.println("4. Position");
        System.out.println("5. Debut Year");
        System.out.println("6. Famous Song");
        System.out.println("Enter your choice: ");
        int choice = scanner.nextInt();
        scanner.nextLine(); // Consume the newline character

        String searchTerm;
        switch (choice) {
            case 1:
                System.out.println("Enter Artist Name to search: ");
                searchTerm = scanner.nextLine();
                break;
            case 2:
                System.out.println("Enter Group to search: ");
                searchTerm = scanner.nextLine();
                break;
            case 3:
                while (true) {
                    try {
                        System.out.println("Enter Age to search: ");
                        searchTerm = scanner.nextLine();
                        int age = Integer.parseInt(searchTerm);
                        if (age < 5 || age > 99) {
                            System.out.println("Invalid input. Age should be between 5 and 99.");
                            continue;
                        }
                        break;
                    } catch (NumberFormatException e) {
                        System.out.println("Invalid input. Age should be a number.");
                    }
                }
                break;
            case 4:
                while (true) {
                    System.out.println("Enter Position to search: ");
                    searchTerm = scanner.nextLine();

                    List<String> validPositions = Arrays.asList(
                            "Leader", "Center", "Visual", "Main Vocal",
                            "Lead Vocal", "Sub Vocal", "Main Dancer",
                            "Lead Dancer", "Main Rapper"
                    );

                    if (validPositions.contains(searchTerm)) {
                        break;
                    } else {
                        System.out.println("Invalid input. Position should be one of the predefined values.");
                    }
                }
                break;
            case 5:
                while (true) {
                    try {
                        System.out.println("Enter Debut Year to search: ");
                        searchTerm = scanner.nextLine();
                        int debutYear = Integer.parseInt(searchTerm);
                        if (debutYear < 1990 || debutYear > 2024) {
                            System.out.println("Invalid input. Debut Year should be between 1990 and 2024.");
                            continue;
                        }
                        break;
                    } catch (NumberFormatException e) {
                        System.out.println("Invalid input. Debut Year should be a number.");
                    }
                }
                break;
            case 6:
                System.out.println("Enter Famous Song to search: ");
                searchTerm = scanner.nextLine();
                break;
            default:
                System.out.println("Invalid choice. No search performed.");
                return;
        }

        List<KpopArtist> searchResults = new ArrayList<>();
        for (KpopArtist artist : kpopArtists) {
            switch (choice) {
                case 1:
                    if (artist.getArtistName().equalsIgnoreCase(searchTerm)) {
                        searchResults.add(artist);
                    }
                    break;
                case 2:
                    if (artist.getGroup().equalsIgnoreCase(searchTerm)) {
                        searchResults.add(artist);
                    }
                    break;
                case 3:
                    if (artist.getAge() == Integer.parseInt(searchTerm)) {
                        searchResults.add(artist);
                    }
                    break;
                case 4:
                    if (artist.getPosition().contains(searchTerm)) {
                        searchResults.add(artist);
                    }
                    break;
                case 5:
                    if (artist.getDebutYear() == Integer.parseInt(searchTerm)) {
                        searchResults.add(artist);
                    }
                    break;
                case 6:
                    if (artist.getFamousSong().equalsIgnoreCase(searchTerm)) {
                        searchResults.add(artist);
                    }
                    break;
            }
        }

        if (!searchResults.isEmpty()) {
            System.out.println("Search results:");
            for (KpopArtist artist : searchResults) {
                System.out.println(artistToString(artist));
            }

            System.out.println("Do you want to save the search results to a file? (yes/no): ");
            String saveChoice = scanner.nextLine();
            if ("yes".equalsIgnoreCase(saveChoice)) {
                saveSearchResults(searchResults, scanner);
            }
        } else {
            System.out.println("No matching data found.");
        }
    }

    public void saveSearchResults(List<KpopArtist> searchResults, Scanner scanner) {
        String fileName;
        while (true) {
            System.out.println("Enter the file name to save the search results (.txt format): ");
            fileName = scanner.nextLine();

            if (!fileName.endsWith(".txt")) {
                System.out.println("Error: Only .txt file format is allowed. Please enter a valid file name.");
            } else {
                break;
            }
        }

        System.out.println("Enter the file path to save the search results: ");
        String filePath = scanner.nextLine();

        try {
            File file = new File(filePath, fileName);
            File parentDir = file.getParentFile();

            if (!parentDir.exists()) {
                System.out.println("Error: The specified directory does not exist.");
                return;
            }

            PrintWriter writer = new PrintWriter(file);

            for (KpopArtist artist : searchResults) {
                writer.println(artistToString(artist));
            }

            writer.close();
            System.out.println("Search results saved successfully to " + file.getAbsolutePath());
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public void exit() {
        saveDataToFile("kpop_data.txt");
        System.out.println("Exiting the program. Goodbye!");
    }

    public void loadDataFromFile(String fileName) {
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] data = line.split(",");
                if (data.length != 6) {
                    System.out.println("Error: Data in the file is not in the correct format. Each line should have 6 comma-separated values.");
                    return;
                }

                String artistName = data[0];
                String group = data[1];
                int age;
                try {
                    age = Integer.parseInt(data[2]);
                    if (age < 5 || age > 99) {
                        System.out.println("Error: Invalid age in the file. Age should be between 5 and 99.");
                        return;
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Error: Invalid age format in the file. Age should be an integer.");
                    return;
                }

                String position = data[3];
                List<String> validPositions = Arrays.asList(
                        "Leader", "Center", "Visual", "Main Vocal",
                        "Lead Vocal", "Sub Vocal", "Main Dancer",
                        "Lead Dancer", "Main Rapper"
                );
                if (!validPositions.contains(position)) {
                    System.out.println("Error: Invalid position in the file. Position should be one of the predefined values.");
                    return;
                }

                int debutYear;
                try {
                    debutYear = Integer.parseInt(data[4]);
                    if (debutYear < 1990 || debutYear > 2024) {
                        System.out.println("Error: Invalid debut year in the file. Debut year should be between 1990 and 2024.");
                        return;
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Error: Invalid debut year format in the file. Debut year should be an integer.");
                    return;
                }

                String famousSong = data[5];
                KpopArtist kpopArtist = new KpopArtist(artistName, group, age, position, debutYear, famousSong);
                kpopArtists.add(kpopArtist);
            }

            System.out.println("Data loaded successfully from the file.");
        } catch (IOException e) {
            System.out.println("Error loading data from the file: " + e.getMessage());
        }
    }

    public void saveDataToFile(String fileName) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(fileName))) {
            for (KpopArtist kpopArtist : kpopArtists) {
                bw.write(kpopArtist.getArtistName() + ","
                        + kpopArtist.getGroup() + ","
                        + kpopArtist.getAge() + ","
                        + kpopArtist.getPosition() + ","
                        + kpopArtist.getDebutYear() + ","
                        + kpopArtist.getFamousSong());
                bw.newLine();
            }
            System.out.println("Data saved successfully to the file.");
        } catch (IOException e) {
            System.out.println("Error saving data to the file: " + e.getMessage());
        }
    }

}
