import java.util.Scanner;

public class Logika {

    public void pradzia() {

        Scanner pasirinkimas = new Scanner(System.in);

        Meniu.pradinisMeniu();

        switch (pasirinkimas.nextInt()) {

            case (1):
                Prisijungimas.prisijungimas();
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

}
