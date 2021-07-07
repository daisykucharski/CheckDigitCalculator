import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class CalculatorTest {
  private final Calculator calculator = new Calculator();

  @Test(expected = IllegalArgumentException.class)
  public void testNullEntryNumber() {
    calculator.calculateCheckDigit(null);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testNoDash() {
    calculator.calculateCheckDigit("MZ50100857");
  }

  @Test(expected = IllegalArgumentException.class)
  public void testTooManyDigits() {
    calculator.calculateCheckDigit("MZ5-0100859-0");
  }

  @Test(expected = IllegalArgumentException.class)
  public void testTooFewDigits() {
    calculator.calculateCheckDigit("MZ5-01059");
  }

  @Test
  public void testOutputCorrectEntryNumber() {
    assertEquals("MZ5-0100857-4", calculator.calculateCheckDigit("MZ5-0100857"));
    assertEquals("MZ5-0100858-2", calculator.calculateCheckDigit("MZ5-0100858"));
    assertEquals("MZ5-0100859-0", calculator.calculateCheckDigit("MZ5-0100859"));
    assertEquals("MZ5-0100861-6", calculator.calculateCheckDigit("MZ5-0100861"));
    assertEquals("MZ5-0100862-4", calculator.calculateCheckDigit("MZ5-0100862"));
    assertEquals("MZ5-0100863-2", calculator.calculateCheckDigit("MZ5-0100863"));
    assertEquals("MZ5-0100864-0", calculator.calculateCheckDigit("MZ5-0100864"));
    assertEquals("MZ5-0100865-7", calculator.calculateCheckDigit("MZ5-0100865"));
    assertEquals("MZ5-0100866-5", calculator.calculateCheckDigit("MZ5-0100866"));
    assertEquals("MZ5-0100867-3", calculator.calculateCheckDigit("MZ5-0100867"));
    assertEquals("MZ5-0100868-1", calculator.calculateCheckDigit("MZ5-0100868"));
    assertEquals("MZ5-0100869-9", calculator.calculateCheckDigit("MZ5-0100869"));
    assertEquals("MZ5-0100870-7", calculator.calculateCheckDigit("MZ5-0100870"));
  }
}