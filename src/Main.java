import java.util.Random;

public class Main {
    public static int bossHealth = 1000;
    public static int bossDamage = 50;
    public static int bossDefaultDamage = bossDamage;
    public static String bossDefenceType;
    public static int[] heroesHealth = {280, 270, 250,240,360,200,500,300};
    public static int[] heroesDamage = {10, 15, 20,0,20,25,30,15};
    public static String[] heroesAttackType = {"Physical", "Magical", "Kinetic","Medik","Lucky",
            "Berserk","Thor","Golem"};
    public static int roundNumber;
    static Random random=new Random();


    public static void main(String[] args) {
        printStatistics();

        while (!isGameFinished()) {
            round();
        }
    }

    public static void chooseBossDefence() {
        Random random = new Random();
        int randomIndex = random.nextInt(heroesAttackType.length); // 0,1,2
        bossDefenceType = heroesAttackType[randomIndex];
    }

    public static void round() {
        roundNumber++;
        chooseBossDefence();
        bossHits();
        heroesHit();
        Thor();
        Lucky();
        Berserk();
        Golem();
        Medik();
        printStatistics();



    }

    public static void Medik(){
            for (int i = 0; i < heroesHealth.length; i++) {
                if (i == 3) {
                    continue;
                }
                if (heroesHealth[i] > 0 && heroesHealth[i] < 100 && heroesHealth[i] > 0) {
                    heroesHealth[i] += 50;
                }
                System.out.println(" Medic helped to " + heroesAttackType[i]);
                break;
            }
        }
        public static void Lucky() {
            Random random = new Random();
            boolean choose = random.nextBoolean();
            if (heroesHealth[4] > 0 && choose) {
                heroesHealth[4] += bossDamage;
                System.out.println(" Lucky dodged boss damage" );
            }
        }
    public static void Berserk() {
        for (int i = 0; i < heroesHealth.length; i++) {
            if (heroesHealth[5] > 0) {
                heroesHealth[5] += bossDamage / 5 * 2;
                bossHealth -= bossDamage / 5* 2;
                break;
            }
        }
    }
    public static void Thor() {
        if (bossDamage == 0) {
            bossDamage = bossDefaultDamage;
        }

        boolean chanceThor = random.nextBoolean();
        int thorIndex = 0;
        for (int i = 0; i < heroesAttackType.length; i++) {
            if (heroesAttackType[i].equals("Thor")) {
                thorIndex = i;
            }
        }

        if (chanceThor && heroesHealth[thorIndex] > 0) {
            bossDamage = 0;
            System.out.println("***Thor stunned the boss");
        }

    }

    public static void Golem(){
        int GolemDamage =bossDamage/5;
        int GolemIndex =0;
        for (int i = 0; i < heroesHealth.length; i++) {
            if (i ==7){
                continue;
            }else if (heroesHealth[i] >0 ){
                GolemIndex++;
                heroesHealth[i] += GolemDamage;
            }
            heroesHealth[7] -= GolemDamage * GolemIndex;
            System.out.println("Golem took damage:" +GolemDamage * GolemIndex);
            break;
        }
    }







    public static void bossHits() {
        for (int i = 0; i < heroesHealth.length; i++) {
            if (heroesHealth[i] > 0) {
                if (heroesHealth[i] - bossDamage < 0) {
                    heroesHealth[i] = 0;
                } else {
                    heroesHealth[i] = heroesHealth[i] - bossDamage;
                }
            }
        }
    }

    public static void heroesHit() {
        for (int i = 0; i < heroesDamage.length; i++) {
            if (heroesHealth[i] > 0 && bossHealth > 0) {
                int damage = heroesDamage[i];
                if (bossDefenceType == heroesAttackType[i]) {
                    Random random = new Random();
                    int coeff = random.nextInt(9) + 2; // 2,3,4,5,6,7,8,9,10
                    damage = heroesDamage[i] * coeff;
                    System.out.println("Critical damage: " + damage);
                }
                if (bossHealth - damage < 0) {
                    bossHealth = 0;
                } else {
                    bossHealth = bossHealth - damage;
                }
            }
        }
    }

    public static void printStatistics() {
        System.out.println("ROUND " + roundNumber + " ----------------");
        /*String defence;
        if (bossDefenceType == null) {
            defence = "No defence";
        } else {
            defence = bossDefenceType;
        }*/
        System.out.println("Boss health: " + bossHealth + " damage: " + bossDamage
                + " defence: "
                + (bossDefenceType == null ? "No defence" : bossDefenceType));
        for (int i = 0; i < heroesHealth.length; i++) {
            System.out.println(heroesAttackType[i] + " health: "
                    + heroesHealth[i] + " damage: " + heroesDamage[i]);
        }
    }

    public static boolean isGameFinished() {
        if (bossHealth <= 0) {
            System.out.println("Heroes won!");
            return true;
        }
        /*if (heroesHealth[0] <= 0 && heroesHealth[1] <= 0 && heroesHealth[2] <= 0) {
            System.out.println("Boss won!");
            return true;
        }
        return false;*/
        boolean allHeroesDead = true;
        for (int i = 0; i < heroesHealth.length; i++) {
            if (heroesHealth[i] > 0) {
                allHeroesDead = false;
                break;
            }
        }
        if (allHeroesDead) {
            System.out.println("Boss won!");
        }
        return allHeroesDead;
    }
}

