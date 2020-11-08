import java.io.File;
import java.io.FileNotFoundException;
import java.net.URI;
import java.util.*;
import java.awt.*;

public class Main {

    public static void main(String[] args) {
        ArrayList<String> names = new ArrayList<>();
        ArrayList<String> days = new ArrayList<>();
        ArrayList<String> times = new ArrayList<>();
        ArrayList<String> links = new ArrayList<>();


        Calendar calendar = Calendar.getInstance();
        System.out.println(calendar.getTime());

        URI url = null;
        Desktop d = Desktop.getDesktop();

        Scanner scanner = null;

        try {
            scanner = new Scanner(new File("src/Schedule.txt"));
        } catch (FileNotFoundException e) {
            System.out.println("File not found.");
            e.printStackTrace();
        }
        scanner.nextLine();

        StringTokenizer st = new StringTokenizer(scanner.nextLine());
        while(st.hasMoreTokens()){
            names.add(st.nextToken());
            days.add(st.nextToken());
            times.add(st.nextToken().substring(0, 5));
            links.add(st.nextToken());
            if(scanner.hasNextLine()) {
                st = new StringTokenizer(scanner.nextLine());
            }
            else{
                break;
            }
        }

        String currentTime;

        while(true) {
            calendar = Calendar.getInstance();
            currentTime = calendar.getTime().toString();
            for (int i = 0; i < names.size(); i++) {
                if (days.get(i).equals(currentTime.substring(0, 3))
                        && times.get(i).equals(currentTime.substring(11, 16))) {
                    System.out.println("Joining " + names.get(i) + "'s Zoom Meeting.");
                    try {
                        url = new URI(links.get(i));
                        d.browse(url);
                        Thread.sleep(60000); // pauses thread so meeting will only be launched once
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }



    }
}
