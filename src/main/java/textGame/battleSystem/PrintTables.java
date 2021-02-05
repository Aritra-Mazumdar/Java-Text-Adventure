package textGame.battleSystem;

import java.util.ArrayList;
import java.util.Scanner;

import textGame.characterCreation.Character;
import textGame.characterCreation.EnemySpreadsheet;
import textGame.characterCreation.PlayerSpreadsheet;
import textGame.itemSystem.Item;
import textGame.magicSystem.Magic;
import textGame.magicSystem.MagicType;

public class PrintTables {
  private static int attackActionBtn = 1;
  private static int magicActionBtn = 0;
  private static int itemActionBtn = 0;

  static Scanner in = new Scanner(System.in);

  public static void printActionsTable(PlayerSpreadsheet mainCharacter, EnemySpreadsheet enemy) {
    int tableNumber = 1;
    System.out.println("\tYour HP: " + mainCharacter.getHealth());
    System.out.println("\t" + enemy.getName() + "'s HP: " + enemy.getHealth());
    System.out.println("\n\tWhat would you like to do?");
    System.out.println("\t" + attackActionBtn + ". Attack");
    if (mainCharacter.getKnowMagics().size() > 0) {
      tableNumber++;
      magicActionBtn = tableNumber;
      System.out.println("\t" + magicActionBtn + ". Use magic");
    }
    if (mainCharacter.getItems().size() > 0) {
      tableNumber++;
      itemActionBtn = tableNumber;
      System.out.println("\t" + itemActionBtn + ". Use item");
    }
  }

  public static void printEffectText(Character character, int effect) {
    if (effect >= 0) {
      System.out.println("\t> " + character.getName() + " receives " + effect + " of damage.");
    } else {
      System.out.println("\t> " + character.getName() + " is cured of " + -effect + " of damage.");
    }
  }

  public static void printMagicTable(ArrayList<Magic> magics, Character player, Character enemy) {
    int i = 0;
    for (Magic magic : magics) {
      i++;
      System.out.println("\t" + i + ". " + magic.getName());
    }
    System.out.println("\n\tWhat magic do you want to cast?");
    String input = in.nextLine();

    Magic choosenMagic = magics.get(Integer.parseInt(input) - 1);
    if (choosenMagic.getType() == MagicType.Cure) {
      Actions.useMagic(choosenMagic, player);
    } else {
      Actions.useMagic(choosenMagic, enemy);
    }
  }

  public static void printItemsTable(ArrayList<Item> items, Character player, Character enemy) {
    int i = 0;
    for (Item item : items) {
      i++;
      System.out.println("\t" + i + ". " + item.getName());
    }
    System.out.println("\n\tWhat magic do you want to cast?");
    String input = in.nextLine();

    Item choosenItem = items.get(Integer.parseInt(input) - 1);
    if (choosenItem.getMagic().getType() == MagicType.Cure) {
      Actions.useItem(choosenItem, player);
    } else {
      Actions.useItem(choosenItem, enemy);
    }
    items.remove(Integer.parseInt(input) - 1);
  }

  public static int getAttackActionBtn() {
    return attackActionBtn;
  }

  public static int getItemActionBtn() {
    return itemActionBtn;
  }

  public static int getMagicActionBtn() {
    return magicActionBtn;
  }

}
