import java.sql.SQLOutput;
import java.util.Scanner;

public class Logika {

    PrisijungesVart vart = new PrisijungesVart();


    public void meniuPasirinkimai() {

        Scanner pasirinkimas = new Scanner(System.in);

        Meniu.pradinisMeniu();

        boolean isCorrectPath = false;

        while (!isCorrectPath) {
            switch (pasirinkimas.nextInt()) {

                case (1):
                    isCorrectPath = Prisijungimas.prisijungimas(vart);
                    break;
                case (2):
                    Registracija.registracija();
                    break;
                case (0):
                    System.out.println("Programa isjungta");
                    break;
                default:
                    System.err.println("Blogai nurodytas pasirinkimas");
                    break;
            }
        }


        isCorrectPath = false;

        while (!isCorrectPath) {

            switch (pasirinkimas.nextInt()) {

                case (1):
                    /*isCorrectPath*/
                    Bankomatas.prisijungtiPrieBankomato(vart);
                    break;
                case (2):
                    /*isCorrectPath = */
                    Meniu.elektroninisBankas();
                    break;
                case (0):
                    System.out.println("Programa isjungta");
                    break;
                default:
                    System.err.println("Blogai nurodytas pasirinkimas");
            }
        }
    }
}
