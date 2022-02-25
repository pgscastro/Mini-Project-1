import java.util.ArrayList;
import java.util.Locale;
import java.util.Random;
import java.util.Scanner;

public class mini_project1 {
    public static final String[] WORDS = {
            "Chongqing",
            "Shanghai",
            "Beijing",
            "Lagos",
            "Mumbai",
            "Dhaka",
            "Chengdu",
            "Karachi",
            "Guangzhou",
            "Istanbul",
            "Tokyo",
            "Tianjin",
            "Moscow",
            "Sao Paulo",
            "Kinshasa",
            "Delhi",
            "Baoding",
            "Lahore",
            "Wuhan",
            "Cairo",
            "Seoul",
            "Jakarta",
            "Wenzhou",
            "Lima",
            "Mexico City",
            "London",
            "Bangkok",
            "Xian",
            "Chennai",
            "Bangalore",
            "New York",
            "Ho Chi Minh City",
            "Hyderabad",
            "Shenzhen",
            "Suzhou",
            "Nanjing",
            "Dongguan",
            "Tehran",
            "Quanzhou",
            "Shenyang",
            "Bogota",
            "Hong Kong",
            "Baghdad",
            "Fuzhou",
            "Changsha",
            "Hanoi",
            "Rio de Janeiro",
            "Qingdao",
            "Foshan",
            "Zunyi",
            "Santiago",
            "Riyadh",
            "Ahmedabad",
            "Singapore",
            "Shantou",
            "Ankara",
            "Yangon",
            "Saint Petersburg",
            "Sydney",
            "Casablanca",
            "Melbourne",
            "Abidjan",
            "Alexandria",
            "Kolkata",
            "Surat",
            "Johannesburg",
            "Dar es Salaam",
            "Shijiazhuang",
            "Harbin",
            "Giza",
            "İzmir",
            "Zhengzhou",
            "Taipei",
            "Los Angeles",
            "Berlin",
            "Busan",
            "Madrid",
            "Buenos Aires",
            "Toronto",
            "Chicago",
            "Osaka",
            "Paris",
            "Vancouver",
            "Montreal",
            "San Francisco",
            "Boston",
            "Seattle",
            "Washington DC",
            "Philadelphia",
            "Portland",
            "Ottawa",
            "Miami",
            "Dallas",
            "Calgary",
            "Denver",
            "Detroit",
            "Houston",
            "Edmonton",
            "Las Vegas",
            "Atlanta"
    };

    public static final Random RANDOM = new Random();
    private String city;
    private char[] lettersFound;
    private int errors;
    public static final int MAX_ERRORS = 10;
    private ArrayList<String> letters = new ArrayList<>();

    private String cityString() {
        return WORDS[RANDOM.nextInt(WORDS.length)].toLowerCase(Locale.ROOT);
    }

    private void userInput(String letter) {
        if (!letters.contains(letter)) {
            if (city.contains(letter)) {

                int index = city.indexOf(letter);

                while (index >= 0) {

                    lettersFound[index] = letter.charAt(0);
                    index = city.indexOf(letter, index + 1);
                }
            } else {
                errors++;
            }

            letters.add(letter);
        }
    }

    public boolean wordFound() {
        return city.contentEquals(new String(lettersFound));
    }

    private String lettersFound() {
        StringBuilder builder = new StringBuilder();

        for (int i = 0; i < lettersFound.length; i++) {
            builder.append(lettersFound[i]);

            if (i < lettersFound.length - 1) {
                builder.append(" ");
            }
        }

        return builder.toString();
    }

    public void play() {
        try (Scanner input = new Scanner(System.in)) {

            while (errors < MAX_ERRORS) {
                System.out.println("\nEnter a letter : ");
                String str = input.next();

                if (str.length() > 1) {
                    str = str.substring(0, 1);
                }
                userInput(str);
                System.out.println("\n" + lettersFound());


                if (wordFound()) {
                    System.out.println("\nYou win! Congratulations");
                    break;
                } else {

                    System.out.println("\nTries remaining : " + (MAX_ERRORS - errors));
                }
            }

            if (errors == MAX_ERRORS) {

                System.out.println("\nYou lose! Better luck next time.");
                System.out.println("The city you were looking for is: " + city);
            }
        }

    }

    public void newGame() {
        errors = 0;
        letters.clear();
        city = cityString();
        lettersFound = new char[city.length()];

        for (int i = 0; i < lettersFound.length; i++) {
            lettersFound[i] = '_';
        }
    }

    public static void main(String[] args) {
        System.out.println("\nThe game will start, find the city name! ");

        mini_project1 mini_project = new mini_project1();
        mini_project.newGame();
        mini_project.play();
    }

}