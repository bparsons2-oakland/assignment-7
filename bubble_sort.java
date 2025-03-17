import java.io.*;
import java.util.*;

public class bubble_sort {

    // 1. Create a random array of integers
    public static int[] createRandomArray(int arrayLength) {
        Random rand = new Random();
        int[] array = new int[arrayLength];
        for (int i = 0; i < arrayLength; i++) {
            array[i] = rand.nextInt(101); // Random integers between 0 and 100
        }
        return array;
    }

    // 2. Write the array to a file
    public static void writeArrayToFile(int[] array, String filename) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
            for (int num : array) {
                writer.write(num + "\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // 3. Read integers from a file into an array
    public static int[] readFileToArray(String filename) {
        ArrayList<Integer> list = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = reader.readLine()) != null) {
                list.add(Integer.parseInt(line));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        // Convert ArrayList to int[] array
        int[] array = new int[list.size()];
        for (int i = 0; i < list.size(); i++) {
            array[i] = list.get(i);
        }
        return array;
    }

    // 4. Bubble sort function to sort the array in-place
    public static void bubbleSort(int[] array) {
        int n = array.length;
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - 1 - i; j++) {
                if (array[j] > array[j + 1]) {
                    // Swap elements
                    int temp = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = temp;
                }
            }
        }
    }

    // Main function to interact with the user
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Select an option:");
        System.out.println("1. Generate a random array and store it in a file");
        System.out.println("2. Read an existing file, sort the array and store it in another file");

        int choice = scanner.nextInt();
        scanner.nextLine(); // Consume the newline character

        if (choice == 1) {
            System.out.println("Enter the length of the array:");
            int arrayLength = scanner.nextInt();
            int[] randomArray = createRandomArray(arrayLength);

            System.out.println("Enter the filename to save the array:");
            String filename = scanner.next();

            writeArrayToFile(randomArray, filename);
            System.out.println("Array written to file successfully!");

        } else if (choice == 2) {
            System.out.println("Enter the filename to read the array from:");
            String inputFile = scanner.next();

            int[] arrayFromFile = readFileToArray(inputFile);
            bubbleSort(arrayFromFile);

            System.out.println("Enter the filename to save the sorted array:");
            String outputFile = scanner.next();

            writeArrayToFile(arrayFromFile, outputFile);
            System.out.println("Sorted array written to file successfully!");
        } else {
            System.out.println("Invalid option!");
        }

        scanner.close();
    }
}
