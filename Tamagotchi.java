import java.util.Random;

//Declaration des attributx prives
public class Tamagotchi {
    private int age;
    private int dureeDeVie;
    private int maxEnergie;
    private int energie;
    private String nom;
    private int bonheur;
    private int discipline;

    public String getNom() {
        return nom;
    }

    public int getAge() {
        return age;
    }

    public int getEnergie() {
        return energie;
    }

    // Constructeur/Initialiser les attributs prives
    public Tamagotchi(String nom) {
        this.nom = nom;
        this.age = 0;
        Random random = new Random();
        this.dureeDeVie = random.nextInt(9, 15);
        this.maxEnergie = random.nextInt(5, 10);
        this.energie = random.nextInt(3, maxEnergie + 1);
        this.bonheur = random.nextInt(3, 11);
        this.discipline = random.nextInt(3, 11);
    }

    // Methode parler()
    public void parler() {
        System.out.println(nom + ":");
        if (energie > 5) {
            System.out.println("j'ai bien manger, je suis heureux!!");
        } else {
            System.out.println("je suis affamé!!");
        }
        if (bonheur <= 3) {
            System.out.println("comme je m'ennuie!!");
        }
    }

    // Methode manger(int aliment) que j'ai aussi ajouter dans le code qui donne un
    // nombre d'energie en fonction de l'aliment choisi
    public void manger(int aliment) {
        if (energie >= maxEnergie) {
            System.out.println(nom + ":j'ai pas faim!!");
            return;
        }
        String nomAliment = "";
        int energiePlus = 0;
        switch (aliment) {
            case 1:
                nomAliment = "une sucette";
                energiePlus = 1;
                break;
            case 2:
                nomAliment = "du riz";
                energiePlus = 2;
                break;
            case 3:
                nomAliment = "des tacos";
                energiePlus = 3;
                break;
            default:
                System.out.println("l'aliment n'est pas disponible");
                return;

        }

        energie = (Math.min(energie + energiePlus, maxEnergie));
        System.out.println(nom + " mange " + nomAliment + " : Merci !");
    }

    // Methode manger()
    public void manger() {
        Random random = new Random();
        manger(random.nextInt(1, 4));
    }

    // Fonctions ajouter au td
    // Methode jouer(int choix) permet aux tamagochi de pouvoir jouer avec
    // l'utilisateur
    public void jouer(int choix) {

        if (energie <= 1) {
            System.out.println(nom + " : je suis trop fatigué pour jouer !");
            return;
        }

        if (bonheur >= 10) {
            System.out.println(nom + " : je suis déjà très heureux, pas envie de jouer !");
            return;
        }

        Random random = new Random();
        int secret = random.nextInt(1, 4);

        if (choix == secret) {
            bonheur = Math.min(bonheur + 2, 10);
            System.out.println(nom + " : bravo, tu as gagné !");
        } else {
            bonheur = Math.min(bonheur + 1, 10);
            System.out.println(nom + " : perdu, c'était " + secret + " !");
        }

        // jouer prend de l'energie donc on décremente
        energie--;
    }

    public void gronder() {
        if (discipline >= 10) {
            System.out.println(nom + " : je suis déjà sage !");
            return;
        }

        discipline = Math.min(discipline + 1, 10);
        bonheur = Math.max(bonheur - 1, 0);
        System.out.println(nom + " : d'accord, je serai sage...");
    }

    // Methode ageLimite()
    public boolean ageLimite() {
        return age >= dureeDeVie;
    }

    // methode vivre()
    public boolean vivre() {
        if (age == dureeDeVie) {
            return true;
        }
        if (energie <= 0) {
            System.out.println(nom + " : je meurs... Arrrggh !");
            return false;
        }
        age++;
        energie--;
        bonheur = Math.max(bonheur - 1, 0);

        // permet de verifier si il a fait une betise ou pas
        Random random = new Random();
        if (random.nextInt(1, 4) == 1) {
            discipline = Math.max(discipline - 1, 0);
            System.out.println(nom + " : j'ai fait une bêtise !");
        }

        if (ageLimite()) {
            System.out.println(nom + " : c'est gagné pour moi !");
        } else {
            parler();
        }
        return true;
    }

}
