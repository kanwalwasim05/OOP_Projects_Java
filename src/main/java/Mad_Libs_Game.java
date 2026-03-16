import java.util.Scanner;
public class Mad_Libs_Game {
    public static void main(String[] args) {

        //Scanner object for user input
        Scanner input = new Scanner(System.in);
        //Attributes of Grammar to be used

        String adjective1;
        String plural_noun1;
        String adjective2;
        String family_member;
        int number;
        String plural_noun2;
        String noun;
        String verb_with_ing_1;
        String verb_with_ing_2;
        String plural_noun3;
        System.out.print("Enter adjective 1 like today/event/festival: ");
        adjective1 = input.nextLine();
        System.out.print("Enter plural noun 1 for variety of food : ");
        plural_noun1 = input.nextLine();
        System.out.print("Enter adjective 2 for food :");
        adjective2 = input.nextLine();
        System.out.print("Enter family member 'name' who prepared the feast/food:");
        family_member = input.nextLine();
        System.out.print("Enter number of pieces/anount you ate: ");
        number = input.nextInt();
        input.nextLine();
        System.out.print("Enter noun for a thing like slave/chair/table: ");
        noun= input.nextLine();
        System.out.print("Enter a plural noun for snacks :");
        plural_noun2=input.nextLine();
        System.out.print("Enter a verb that ends with ing:");
        verb_with_ing_1 = input.nextLine();
        System.out.print("Enter a verb that ends with ing:");
        verb_with_ing_2 = input.nextLine();
        System.out.print("Enter a plural noun like hands/boxes:");
        plural_noun3 = input.nextLine();
        System.out.println("Thanks giving was finally here!,and my family " +
                " was ready for " + adjective1 + " feast!");
        System.out.println("The table was filled with all kind of "
                + "delicious, " + plural_noun1+".");
        System.out.println("First we ate some bread " + adjective2 + ",turkey"
                + " was preparated by my " + family_member+".");
        System.out.println("It tasted so good,I had " + number + " pieces,");
        System.out.println("but my cousin spilled all over " +noun+".");
        System.out.println("Next we passes the bowl of "+plural_noun2+" with each other,");
        System.out.println("everyone started " + verb_with_ing_1+".");
        System.out.println("It was the best Thanksgiving ever!,we all went to bed " + verb_with_ing_2
                + " with full stomach and " + plural_noun3 + " in our hands.");
    }
}
