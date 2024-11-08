import java.util.*;

public class PageReplacement {
    public static void main(String[] args) {
        pr();
    }

    static void pr() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Page Replacement:");
        System.out.println("Enter 1 for FIFO");
        System.out.println("Enter 2 for LRU");
        System.out.print("Enter Choice: ");
        int x = sc.nextInt(); // To select either FIFO or LRU
        System.out.print("Length of String: ");
        int n = sc.nextInt(); // Length of reference string
        int fr = 3; // Number of frames (typically fixed to 3, can be changed)
        int ref[] = new int[n]; // Reference string
        System.out.println("Enter reference string: ");
        for (int i = 0; i < n; i++) {
            ref[i] = sc.nextInt(); // Input reference string
        }
        // FIFO
        HashMap<Integer, Integer> map = new HashMap<>();
        ArrayList<ArrayList<Integer>> arr = new ArrayList<>();
        for (int i = 0; i <= n; i++) {
            arr.add(new ArrayList<>());
        }
        for (int i = 0; i < fr; i++) {
            arr.get(0).add(-1);
        }
        int ct = 1; // Counter for time or order
        int hit = 0; // Count for page hits
        if (x == 1 && n > 0) {
            // FIFO Page Replacement
            int indx = 0;
            for (int i = 1; i <= n; i++) {
                int curr = ref[i - 1]; // Current page
                arr.get(i).addAll(arr.get(i - 1)); // Copy previous state
                if (!map.containsKey(curr)) { // If page not in memory
                    if (indx < fr) { // If there is an empty frame
                        arr.get(i).set(indx, ref[i - 1]); // Place page in the next frame
                    } else { // If all frames are full
                        int min = Integer.MAX_VALUE;
                        int temp = 0;
                        for (int j : map.keySet()) { // Find oldest page (FIFO)
                            if (map.get(j) < min) {
                                min = map.get(j);
                                temp = j;
                            }
                        }
                        for (int j = 0; j < fr; j++) {
                            if (arr.get(i).get(j) == temp) {
                                arr.get(i).set(j, curr); // Replace oldest page with new one
                                break;
                            }
                        }
                        map.remove(temp); // Remove the oldest page from the map
                    }
                    map.put(ref[i - 1], ct++); // Insert new page with time/order
                    indx++;
                } else {
                    hit++; // Page hit
                }
            }
            System.out.println("FIFO Page Replacement:");
        } else if (x == 2 && n > 0) {
            // LRU Page Replacement
            int indx = 0;
            for (int i = 1; i <= n; i++) {
                int curr = ref[i - 1]; // Current page
                arr.get(i).addAll(arr.get(i - 1)); // Copy previous state
                if (!map.containsKey(curr)) { // If page not in memory
                    if (indx < fr) { // If there is an empty frame
                        arr.get(i).set(indx, ref[i - 1]); // Place page in the next frame
                    } else { // If all frames are full
                        int min = Integer.MAX_VALUE;
                        int temp = 0;
                        for (int j : map.keySet()) { // Find least recently used page (LRU)
                            if (map.get(j) < min) {
                                min = map.get(j);
                                temp = j;
                            }
                        }
                        for (int j = 0; j < fr; j++) {
                            if (arr.get(i).get(j) == temp) {
                                arr.get(i).set(j, curr); // Replace LRU page with new one
                                break;
                            }
                        }
                        map.remove(temp); // Remove LRU page from the map
                    }
                    map.put(ref[i - 1], ct++); // Insert new page with time/order
                    indx++;
                } else {
                    hit++; // Page hit
                }
                map.put(ref[i - 1], ct++); // Update the time/order for the page
            }
            System.out.println("LRU Page Replacement:");
        }
        displayPageReplacement(arr, n, fr);
        System.out.println("Total Hits: " + hit);
        System.out.println("Total Misses: " + (n - hit));
    }

    static void displayPageReplacement(ArrayList<ArrayList<Integer>> arr, int n, int fr) {
        System.out.println("Frame Status during execution:");
        for (int i = 1; i <= n; i++) {
            System.out.print("Step " + i + ": ");
            for (int j = 0; j < fr; j++) {
                if (arr.get(i).get(j) != -1)
                    System.out.print(arr.get(i).get(j) + " ");
                else
                    System.out.print("E "); // E for Empty frame
            }
            System.out.println();
        }
    }
}