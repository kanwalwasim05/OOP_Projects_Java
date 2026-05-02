// ============================================================
//         RPG CHARACTER BATTLE SYSTEM
//   Concepts: Inheritance | Polymorphism | Method Overriding
// ============================================================

import java.util.Random;
import java.util.Scanner;

// ─────────────────────────────────────────────
//  BASE CLASS — Inheritance starts here
// ─────────────────────────────────────────────
//Character Class
abstract class Character {
    //Attributes to be inherited
    protected String name;
    protected int health;
    protected int maxHealth;
    protected int attackPower;
    protected int defense;
    protected boolean isAlive;

    public Character(String name, int health, int attackPower, int defense) {
        this.name        = name;
        this.health      = health;
        this.maxHealth   = health;
        this.attackPower = attackPower;
        this.defense     = defense;
        this.isAlive     = true;
    }

    // These methods will be OVERRIDDEN by each subclass
    public abstract void attack(Character opponent);
    public abstract void specialMove(Character opponent);
    public abstract void getInfo();

    // Shared method — same for all characters (NOT overridden)
    public int takeDamage(int damage) {
        int actualDamage = Math.max(0, damage - this.defense);
        this.health -= actualDamage;
        if (this.health <= 0) {
            this.health  = 0;
            this.isAlive = false;
        }
        return actualDamage;
    }

    public void showHealthBar() {
        int barLength = 20;
        int filled    = (int) ((double) health / maxHealth * barLength);
        StringBuilder bar = new StringBuilder();
        for (int i = 0; i < barLength; i++) {
            bar.append(i < filled ? "█" : "░");
        }
        System.out.printf("  %s: [%s] %d/%d HP%n", name, bar, health, maxHealth);
    }
}


// ─────────────────────────────────────────────
//  WARRIOR — Inherits from Character
// ─────────────────────────────────────────────
class Warrior extends Character {

    private int rage;
    Random rand = new Random();

    public Warrior(String name) {
        super(name, 150, 35, 15);  // High HP & Defense
        this.rage = 0;
    }

    // OVERRIDING attack()
    @Override
    public void attack(Character opponent) {
        this.rage += 10;
        int damage = attackPower + rand.nextInt(10);
        int actual = opponent.takeDamage(damage);
        System.out.println("  ⚔️  " + name + " slashes " + opponent.name + " for " + actual + " damage!");
    }

    // OVERRIDING specialMove()
    @Override
    public void specialMove(Character opponent) {
        if (this.rage >= 30) {
            this.rage -= 30;
            int damage = attackPower * 2 + rand.nextInt(20) + 10;
            int actual = opponent.takeDamage(damage);
            System.out.println("  🔥 " + name + " uses BERSERKER RAGE! Deals " + actual + " massive damage!");
        } else {
            System.out.println("  ⚠️  " + name + " needs more rage! (" + rage + "/30)");
        }
    }

    // OVERRIDING getInfo()
    @Override
    public void getInfo() {
        System.out.println("\n  ⚔️  WARRIOR | " + name);
        System.out.println("     HP: " + health + " | ATK: " + attackPower + " | DEF: " + defense + " | Rage: " + rage + "/30");
    }
}


// ─────────────────────────────────────────────
//  MAGE — Inherits from Character
// ─────────────────────────────────────────────
class Mage extends Character {

    private int mana;
    Random rand = new Random();

    public Mage(String name) {
        super(name, 90, 50, 5);  // High Attack, Low HP
        this.mana = 100;
    }

    // OVERRIDING attack()
    @Override
    public void attack(Character opponent) {
        this.mana += 5;
        int damage = attackPower + rand.nextInt(15) - 5;
        int actual = opponent.takeDamage(damage);
        System.out.println("  🔮 " + name + " casts FIREBALL at " + opponent.name + " for " + actual + " damage!");
    }

    // OVERRIDING specialMove()
    @Override
    public void specialMove(Character opponent) {
        if (this.mana >= 40) {
            this.mana -= 40;
            int damage = attackPower * 2 + rand.nextInt(35) + 20;
            int actual = opponent.takeDamage(damage);
            System.out.println("  ⚡ " + name + " unleashes THUNDERSTORM! Deals " + actual + " damage!");
        } else {
            System.out.println("  ⚠️  " + name + " has low mana! (" + mana + "/40)");
        }
    }

    // OVERRIDING getInfo()
    @Override
    public void getInfo() {
        System.out.println("\n  🔮 MAGE | " + name);
        System.out.println("     HP: " + health + " | ATK: " + attackPower + " | DEF: " + defense + " | Mana: " + mana + "/100");
    }
}


// ─────────────────────────────────────────────
//  ARCHER — Inherits from Character
// ─────────────────────────────────────────────
class Archer extends Character {

    private int arrows;
    Random rand = new Random();

    public Archer(String name) {
        super(name, 110, 40, 8);  // Balanced stats
        this.arrows = 10;
    }

    // OVERRIDING attack()
    @Override
    public void attack(Character opponent) {
        if (arrows > 0) {
            arrows--;
            boolean crit   = rand.nextDouble() < 0.25; // 25% crit chance
            int damage     = attackPower * (crit ? 2 : 1) + rand.nextInt(12);
            int actual     = opponent.takeDamage(damage);
            if (crit) {
                System.out.println("  🎯 " + name + " lands a CRITICAL SHOT on " + opponent.name + " for " + actual + " damage!");
            } else {
                System.out.println("  🏹 " + name + " shoots " + opponent.name + " for " + actual + " damage! (" + arrows + " arrows left)");
            }
        } else {
            System.out.println("  ⚠️  " + name + " has no arrows! Using melee attack.");
            opponent.takeDamage(10);
        }
    }

    // OVERRIDING specialMove()
    @Override
    public void specialMove(Character opponent) {
        if (arrows >= 3) {
            arrows -= 3;
            int totalDamage = 0;
            System.out.println("  🌪️  " + name + " uses ARROW STORM!");
            for (int i = 1; i <= 3; i++) {
                int damage = attackPower + rand.nextInt(15) + 5;
                int actual = opponent.takeDamage(damage);
                totalDamage += actual;
                System.out.println("       Arrow " + i + " hits for " + actual + " damage!");
            }
            System.out.println("       Total: " + totalDamage + " damage!");
        } else {
            System.out.println("  ⚠️  " + name + " needs 3 arrows! (has " + arrows + ")");
        }
    }

    // OVERRIDING getInfo()
    @Override
    public void getInfo() {
        System.out.println("\n  🏹 ARCHER | " + name);
        System.out.println("     HP: " + health + " | ATK: " + attackPower + " | DEF: " + defense + " | Arrows: " + arrows);
    }
}


// ─────────────────────────────────────────────
//  MAIN CLASS
// ─────────────────────────────────────────────
public class RPGGame {

    // ─────────────────────────────────────────
    //  POLYMORPHISM DEMO
    //  All treated as Character objects in array
    // ─────────────────────────────────────────
    static void showAllCharacters(Character[] characters) {
        System.out.println("\n" + "=".repeat(50));
        System.out.println("        ALL CHARACTERS — POLYMORPHISM DEMO");
        System.out.println("=".repeat(50));
        System.out.println("  Calling getInfo() on each — same method call,");
        System.out.println("  different output based on class (Polymorphism!)\n");
        for (Character c : characters) {  // All treated as 'Character'
            c.getInfo();                  // But each behaves differently!
        }
        System.out.println();
    }

    // ─────────────────────────────────────────
    //  BATTLE SYSTEM
    // ─────────────────────────────────────────
    static void battle(Character fighter1, Character fighter2) {
        System.out.println("\n" + "=".repeat(50));
        System.out.println("  ⚔️  BATTLE: " + fighter1.name + " VS " + fighter2.name);
        System.out.println("=".repeat(50));

        int round = 1;

        while (fighter1.isAlive && fighter2.isAlive) {
            System.out.println("\n  --- ROUND " + round + " ---");
            fighter1.showHealthBar();
            fighter2.showHealthBar();

            // Fighter 1 attacks
            System.out.println("\n  " + fighter1.name + "'s turn:");
            if (round % 3 == 0) fighter1.specialMove(fighter2);
            else                 fighter1.attack(fighter2);

            if (!fighter2.isAlive) break;

            // Fighter 2 attacks
            System.out.println("\n  " + fighter2.name + "'s turn:");
            if (round % 3 == 0) fighter2.specialMove(fighter1);
            else                 fighter2.attack(fighter1);

            round++;

            try { Thread.sleep(500); } catch (InterruptedException e) {}
        }

        System.out.println("\n" + "=".repeat(50));
        if (fighter1.isAlive)
            System.out.println("  🏆 " + fighter1.name + " WINS the battle!");
        else
            System.out.println("  🏆 " + fighter2.name + " WINS the battle!");
        System.out.println("=".repeat(50));
    }

    // ─────────────────────────────────────────
    //  ENTRY POINT
    // ─────────────────────────────────────────
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("\n" + "=".repeat(50));
        System.out.println("       🎮 RPG CHARACTER BATTLE SYSTEM 🎮");
        System.out.println("  Concepts: Inheritance | Polymorphism | Overriding");
        System.out.println("=".repeat(50));

        // Creating characters (Inheritance in action)
        Warrior warrior = new Warrior("Aragorn");
        Mage    mage    = new Mage("Gandalf");
        Archer  archer  = new Archer("Legolas");

        // POLYMORPHISM — all stored as Character array
        Character[] allCharacters = { warrior, mage, archer };
        showAllCharacters(allCharacters);

        // Battle menu
        System.out.println("  Choose a battle:");
        System.out.println("  1. Warrior vs Mage");
        System.out.println("  2. Mage vs Archer");
        System.out.println("  3. Warrior vs Archer");
        System.out.print("\n  Enter choice (1/2/3): ");

        String choice = scanner.nextLine().trim();

        switch (choice) {
            case "1" -> battle(new Warrior("Aragorn"), new Mage("Gandalf"));
            case "2" -> battle(new Mage("Gandalf"),   new Archer("Legolas"));
            case "3" -> battle(new Warrior("Aragorn"), new Archer("Legolas"));
            default  -> {
                System.out.println("  Invalid choice. Running default: Warrior vs Mage");
                battle(new Warrior("Aragorn"), new Mage("Gandalf"));
            }
        }

        scanner.close();
    }
}
