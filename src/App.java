import java.util.*;

public class App {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        DatabazeStudentu databaze = new DatabazeStudentu();

        try {
            SQLManager sql = new SQLManager();
            databaze.studenti = sql.nactiStudenty();

            while (true) {
                System.out.println("1 - Přidat studenta");
                System.out.println("2 - Přidat známku");
                System.out.println("3 - Odebrat studenta");
                System.out.println("4 - Vypsat studenty");
                System.out.println("5 - Spustit dovednost");
                System.out.println("6 - Uložit a konec");

                int volba = sc.nextInt();
                sc.nextLine();

                if (volba == 1) {
                    System.out.println("Zadejte typ (1-telekomunikace, 2-kyberbezpecnost):");
                    int typ = sc.nextInt(); sc.nextLine();
                    System.out.println("Jméno:"); String jmeno = sc.nextLine();
                    System.out.println("Příjmení:"); String prijmeni = sc.nextLine();
                    System.out.println("Datum narození (dd.MM.yyyy):"); String datum = sc.nextLine();
                    int id = databaze.generujId();
                    Student s = (typ == 1) ? new StudentTelekomunikace(id, jmeno, prijmeni, datum) : new StudentKyberbezpecnost(id, jmeno, prijmeni, datum);
                    databaze.pridejStudenta(s);
                } else if (volba == 2) {
                    System.out.println("ID studenta:");
                    int id = sc.nextInt();
                    System.out.println("Známka:");
                    int znamka = sc.nextInt();
                    Student s = databaze.najdiStudenta(id);
                    if (s != null) s.pridejZnamku(znamka);
                } else if (volba == 3) {
                    System.out.println("ID studenta:");
                    int id = sc.nextInt();
                    databaze.odeberStudenta(id);
                } else if (volba == 4) {
                    for (Student s : databaze.getVsechnyStudenty()) {
                        System.out.println(s);
                    }
                } else if (volba == 5) {
                    System.out.println("ID studenta:");
                    int id = sc.nextInt();
                    Student s = databaze.najdiStudenta(id);
                    if (s != null) System.out.println(s.specialSkill());
                } else if (volba == 6) {
                    sql.ulozStudenty(databaze.studenti);
                    sql.close();
                    System.out.println("Uloženo. Konec.");
                    break;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        sc.close();
    }
}
