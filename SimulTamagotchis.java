import java.util.ArrayList;
import java.util.Scanner;

public class SimulTamagotchis {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ArrayList<Tamagotchi> liste = new ArrayList<>();

        // création des tamagotchis
        System.out.print("Combien de tamagotchis ? ");
        int nbT = sc.nextInt();
        sc.nextLine();

        for (int i = 0; i < nbT; i++) {
            System.out.print("Quel nom pour le nouveau tamagotchi : ");
            String nom = sc.nextLine();
            liste.add(new Tamagotchi(nom));
        }

        int cycle = 1;
        boolean perdu = false;

        // boucle principale : un tour = un cycle
        while (!liste.isEmpty() && !perdu) {
            System.out.println("-------Cycle no " + cycle + " -------");

            // 1) tous les tamagotchis vivent
            for (int i = 0; i < liste.size() && !perdu; i++) {
                Tamagotchi t = liste.get(i);

                if (!t.vivre()) {
                    perdu = true; // mort de faim
                } else if (t.ageLimite()) {
                    liste.remove(i); // il a gagné : il sort du jeu
                    i--; // on revient sur le numéro décalé
                }
            }

            for (int i = 0; i < liste.size(); i++) {
                Tamagotchi t = liste.get(i);
                System.out.println(t.getNom() + " : âge = " + t.getAge() + ", énergie = " + t.getEnergie());
            }

            // 2) le joueur agit (sauf si c'est perdu ou déjà gagné)
            if (!perdu && !liste.isEmpty()) {
                for (int i = 0; i < liste.size(); i++) {
                    System.out.print("(" + i + ") " + liste.get(i).getNom() + " ");
                }
                System.out.print("\nQuel tamagotchi ? ");
                Tamagotchi t = liste.get(sc.nextInt());

                System.out.print("(1) Nourrir (2) Jouer (3) Gronder : ");
                int action = sc.nextInt();
                if (action == 1) {
                    // on appel t.manger(); si on veut la version du td sans amelioration
                    System.out.print("(1) Sucette (2) Riz (3) Tacos : ");
                    t.manger(sc.nextInt());
                } else if (action == 2) {
                    System.out.print("Devine un nombre entre 1 et 3 : ");
                    t.jouer(sc.nextInt());
                } else if (action == 3) {
                    t.gronder();
                } else {
                    System.out.println("Action inconnue.");
                }
            }

            cycle++;
        }

        // message final
        if (perdu) {
            System.out.println("PERDU !!");
        } else {
            System.out.println("GAGNÉ !! Tous les tamagotchis ont atteint leur âge limite.");
        }
        sc.close();
    }

}