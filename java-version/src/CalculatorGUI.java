import java.awt.Component;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 * The Swing-based GUI for the check digit calculator.
 */
public class CalculatorGUI extends JFrame {

  private final JTextField entryNumber;
  private final JLabel resultNumber;

  /**
   * Creates and sets up the frame for the GUI.
   */
  public CalculatorGUI() {
    super();
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    this.setPreferredSize(new Dimension(500, 400));
    this.setTitle("Check Digit Calculator");

    this.setLayout(new BoxLayout(getContentPane(), BoxLayout.PAGE_AXIS));
    JLabel entryLabel = new JLabel("Enter Entry Number:");
    entryLabel.setFont(entryLabel.getFont().deriveFont(16f));
    entryLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
    this.add(entryLabel);
    entryNumber = new JTextField();
    entryNumber.setAlignmentX(Component.CENTER_ALIGNMENT);
    entryNumber.setFont(entryNumber.getFont().deriveFont(32f));
    this.add(entryNumber);
    JLabel result = new JLabel("Result");
    result.setAlignmentX(Component.CENTER_ALIGNMENT);
    result.setFont(result.getFont().deriveFont(16f));
    this.add(result);
    resultNumber = new JLabel();
    resultNumber.setAlignmentX(Component.CENTER_ALIGNMENT);
    resultNumber.setFont(resultNumber.getFont().deriveFont(32f));
    this.add(resultNumber);

    this.setupButtons();
    this.pack();
    this.setVisible(true);
  }

  /**
   * Sets up the buttons at the bottom of the frame and their actions.
   */
  private void setupButtons() {
    JPanel buttonPanel = new JPanel();
    buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.LINE_AXIS));
    JButton computeButton = new JButton("Compute Result and Copy");
    computeButton.addActionListener(e -> {
      computeResult();
      copyToClipboard();
    });
    buttonPanel.add(computeButton);
    JButton clearEntryNumber = new JButton("Clear Entry Number Field");
    clearEntryNumber.addActionListener(evt -> this.entryNumber.setText(""));
    buttonPanel.add(clearEntryNumber);
    this.add(buttonPanel);
  }

  /**
   * Calculates the appropriate checkdigit from the entry field and outputs the result to the result
   * field. If the entry number is invalid, a popup will indicate that.
   */
  private void computeResult() {
    try {
      String checkDigit = new Calculator().calculateCheckDigit(entryNumber.getText());
      resultNumber.setText(checkDigit);
    } catch (IllegalArgumentException exception) {
      JOptionPane.showMessageDialog(null, "Invalid Entry Number, try again", "Error",
          JOptionPane.ERROR_MESSAGE);
    }
  }

  /**
   * Copies the text from the result field to the user's clipboard
   */
  private void copyToClipboard() {
    String ctc = resultNumber.getText();
    StringSelection stringSelection = new StringSelection(ctc);
    Clipboard clpbrd = Toolkit.getDefaultToolkit().getSystemClipboard();
    clpbrd.setContents(stringSelection, null);
  }
}
