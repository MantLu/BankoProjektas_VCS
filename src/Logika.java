import java.util.Scanner;

public class Logika {

    PrisijungesVart vart = new PrisijungesVart();
    PrisijungesVart slap = new PrisijungesVart();


    public void meniuPasirinkimai() {

        Scanner pasirinkimas = new Scanner(System.in);

        Meniu.pradinisMeniu();

        boolean isCorrectPath = false;

        while (!isCorrectPath) {

            switch (pasirinkimas.nextInt()) {

                case (1):
                    isCorrectPath = Prisijungimas.prisijungimas(vart, slap);
                    break;
                case (2):
                    Registracija.registracija();
                    break;
                case (0):
                    System.out.println("Programa išjungta");
                    System.exit(0);
                    break;
                default:
                    System.err.println("Blogai nurodytas pasirinkimas");
                    break;
            }
        }

        prisijungimoPasirinkimas(pasirinkimas);

        isCorrectPath = false;

        while (!isCorrectPath) {

            switch (pasirinkimas.nextInt()) {

                case (1):
                    isCorrectPath = Bankomatas.saskaitosLikutis(vart, slap);
                    break;
                case (2):
                    isCorrectPath = Bankomatas.piniguInesimas(vart, slap);
                    break;
                case (3):
                    isCorrectPath = Bankomatas.pasiimtiPinigu(vart, slap);
                    break;
                case (4):
                    isCorrectPath = Bankomatas.pakeistiPIN(vart, slap);
                    break;
                case (5):
                    Meniu.bankomArElbankMeniu();
                    prisijungimoPasirinkimas(pasirinkimas);
                    break;
                case (0):
                    System.out.println("Programa išjungta");
                    System.exit(0);
                    break;
                default:
                    System.err.println("Blogai nurodytas pasirinkimas");
            }
        }

        /* prisijungimoPasirinkimas(pasirinkimas);

        isCorrectPath = false;

        while (!isCorrectPath) {

            switch (pasirinkimas.nextInt()) {

                case (1):
                    isCorrectPath = ElBankas.saskLikutisElBanke(vart, slap);
                    break;
                case (0):
                    System.out.println("Programa išjungta");
                    System.exit(0);
                    break;
                default:
                    System.err.println("Blogai nurodytas pasirinkimas");
            }
        } */
    }

    private void prisijungimoPasirinkimas(Scanner pasirinkimas) {
        boolean isCorrectPath;
        isCorrectPath = false;

        while (!isCorrectPath) {

            switch (pasirinkimas.nextInt()) {

                case (1):
                    isCorrectPath = Bankomatas.prisijungtiPrieBankomato(vart);
                    break;
                case (2):
                    isCorrectPath = ElBankas.prisijungtiPrieElBanko(vart);
                    break;
                case (0):
                    System.out.println("Programa išjungta");
                    System.exit(0);
                    break;
                default:
                    System.err.println("Blogai nurodytas pasirinkimas");
            }
        }
    }
}
