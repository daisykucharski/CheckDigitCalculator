import java.util.HashMap;
import java.util.Map;

/**
 * A class to calculate the check digit given an entry number.
 */
public class Calculator {

  private final Map<Character, Integer> valueMapping;

  /**
   * Creates a Calculator object. Stores the mapping of letters to numbers used to calculate the
   * Numeric Base Value.
   */
  public Calculator() {
    valueMapping = new HashMap<>();
    valueMapping.put('A', 1);
    valueMapping.put('B', 2);
    valueMapping.put('C', 3);
    valueMapping.put('D', 4);
    valueMapping.put('E', 5);
    valueMapping.put('F', 6);
    valueMapping.put('G', 7);
    valueMapping.put('H', 8);
    valueMapping.put('I', 9);
    valueMapping.put('J', 1);
    valueMapping.put('K', 2);
    valueMapping.put('L', 3);
    valueMapping.put('M', 4);
    valueMapping.put('N', 5);
    valueMapping.put('O', 6);
    valueMapping.put('P', 7);
    valueMapping.put('Q', 8);
    valueMapping.put('R', 9);
    valueMapping.put('S', 2);
    valueMapping.put('T', 3);
    valueMapping.put('U', 4);
    valueMapping.put('V', 5);
    valueMapping.put('W', 6);
    valueMapping.put('X', 7);
    valueMapping.put('Y', 8);
    valueMapping.put('Z', 9);
  }

  /**
   * Calculates the check digit for the given entry number. A valid entry number is in the form of
   * "XXX-XXXXXXX". The first 3 digits can be letters/numbers and the last 7 digits must be numbers.
   * The check digit is returned with the rest of the entry number in the form of "XXX-XXXXXXX-X".
   *
   * @param entryNumber the entry number the check digit is being calculated for
   * @return The check digit in the form "XXX-XXXXXXX-X"
   * @throws IllegalArgumentException if the entry number is invalid (null or invalid form).
   */
  public String calculateCheckDigit(String entryNumber) throws IllegalArgumentException {
    String numericBaseValue = this.calculateNumericBaseValue(entryNumber);
    int checkDigitBaseValue = this.calculateAdjustedEvenSumValue(numericBaseValue) + this
        .calculateOddSumValue(numericBaseValue);
    int checkDigit = (10 - checkDigitBaseValue % 10) % 10;
    return entryNumber + "-" + checkDigit;
  }

  // calculates the numeric base value for the given entry number (used to find check digit).
  private String calculateNumericBaseValue(String entryNumber) throws IllegalArgumentException {
    if (entryNumber != null && entryNumber.length() == 11 && entryNumber.charAt(3) == '-') {
      StringBuilder result = new StringBuilder();

      for (int i = 0; i < 3; ++i) {
        result.append(this.getNumericValue(entryNumber.charAt(i)));
      }

      result.append(entryNumber.substring(4));
      return result.toString();
    } else {
      throw new IllegalArgumentException("Invalid entry number");
    }
  }


  // gets the numeric value associated with the given character
  private int getNumericValue(char ch) {
    return valueMapping.getOrDefault(ch, Character.getNumericValue(ch));
  }

  // calculates the adjusted even sum value from the given numeric base value
  private int calculateAdjustedEvenSumValue(String nbv) {
    int result = 0;

    for (int i = 1; i < 10; i += 2) {
      int product = this.getDigit(nbv, i);
      product *= 2;
      if (product > 9) {
        ++product;
      }

      result += product % 10;
    }

    return result;
  }

  // calculates the odd sum value from the given numeric base value
  private int calculateOddSumValue(String nbv) {
    return this.getDigit(nbv, 0) + this.getDigit(nbv, 2) + this.getDigit(nbv, 4) + this
        .getDigit(nbv, 6) + this.getDigit(nbv, 8);
  }

  // gets the digit in the pos position in the given numeric base value. In the NBV "9876543210", the
  // positions are self described.
  private int getDigit(String nbv, int pos) {
    return Character.getNumericValue(nbv.charAt(pos));
  }
}
