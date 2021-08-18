/**
 * The main class for running the program
 */
public class Main {

  /**
   * Calls the GUI to start the program.
   * @param args not needed
   */
  public static void main(String[] args) {
    //new CalculatorGUI();
    Calculator calc = new Calculator();
    String entryNumber = "MZ5-0100857";
    String nbv = calc.calculateNumericBaseValue(entryNumber);
    System.out.println("odd: " + calc.calculateOddSumValue(nbv) + " even: " + calc.calculateAdjustedEvenSumValue(nbv));
  }
}
