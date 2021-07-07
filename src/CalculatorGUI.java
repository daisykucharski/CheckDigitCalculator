import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.Border;

public class CalculatorGUI extends JFrame {
  private final JTextField entryNumber;
  private final JLabel resultNumber;

  public CalculatorGUI() {
    super();
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    this.setPreferredSize(new Dimension(500, 400));

    this.setLayout(new BoxLayout(getContentPane(), BoxLayout.PAGE_AXIS));
    JLabel entryLabel = new JLabel("Enter Entry Number:");
    this.add(entryLabel);
    entryNumber = new JTextField();
    entryNumber.setPreferredSize(new Dimension(30, 100));
    this.add(entryNumber);
    JLabel result = new JLabel("Result");
    this.add(result);
    resultNumber = new JLabel("Check digit will appear here");
    this.add(resultNumber);

    JPanel buttonPanel = new JPanel();
    buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.LINE_AXIS));
    JButton computeButton = new JButton("Compute Result");
    computeButton.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        try {
          String checkDigit = new Calculator().calculateCheckDigit(entryNumber.getText());
          resultNumber.setText(checkDigit);
        } catch (IllegalArgumentException exception) {
          // popup panel
        }
      }
    });
    buttonPanel.add(computeButton);
    JButton copyButton = new JButton("Copy Result to Clipboard");
    copyButton.addActionListener(evt -> this.copyToClipboard());
    buttonPanel.add(copyButton);
    JButton clearEntryNumber = new JButton("Clear Entry Number Field");
    clearEntryNumber.addActionListener(evt -> this.entryNumber.setText(""));
    buttonPanel.add(clearEntryNumber);
    this.add(buttonPanel);
    this.pack();
    this.setVisible(true);
  }

  private void copyToClipboard() {
    String ctc = resultNumber.getText();
    StringSelection stringSelection = new StringSelection(ctc);
    Clipboard clpbrd = Toolkit.getDefaultToolkit().getSystemClipboard();
    clpbrd.setContents(stringSelection, null);
  }
}
