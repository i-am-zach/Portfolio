import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

public class IntroScreen extends JFrame{

    public static JButton submit;
    private JComboBox nameComboBox;
    private String name;
    private String[] data = getData();
    private String[] names = getNames(data);

    public static void main(String[] args) {
        new IntroScreen();
    }

    /**
     * Default constructor for an introscreen
     */
    public IntroScreen(){
        super("Welcome to BlackJack");
        setLayout(new BorderLayout());
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JPanel container = new JPanel();
        container.setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);

        //ComboBox ////////////////////////////
        String[] data = getData();
        String[] names = getNames(data);
        nameComboBox = new JComboBox(names);
        nameComboBox.setEditable(true);

        JLabel nameLabel = new JLabel("Name:");
        nameLabel.setForeground(Color.WHITE);
        gbc.gridx = 0;
        gbc.gridy = 0;
        container.add(nameLabel, gbc);
        gbc.gridx = 1;
        container.add(nameComboBox, gbc);
        //////////////////////////////////////////

        /*//Bet Slider////////////////////////////////
        JSlider betSlider = new JSlider(JSlider.HORIZONTAL, 0, 1000, 100);
        betSlider.setMinorTickSpacing(100);
        betSlider.setMajorTickSpacing(200);
        betSlider.setPaintTicks(true);
        betSlider.setPaintLabels(true);
        JLabel betLabel2 = new JLabel();
        betSlider.addChangeListener(e->{
            betLabel2.setText("$" + Integer.toString(betSlider.getValue()));
        });


        JLabel betLabel = new JLabel("Bet:");

        gbc.gridx = 0;
        gbc.gridy = 1;
        frame.add(betLabel, gbc);
        gbc.gridx = 1;
        frame.add(betSlider, gbc);
        gbc.gridy = 2;
        frame.add(betLabel2, gbc);
        /////////////////////////////////////////////*/

        //Balance Label ////////////////////////////////
        JLabel balanceLabel = new JLabel("Balance: $1000");
        balanceLabel.setForeground(Color.WHITE);
        gbc.gridx = 2;
        gbc.gridy = 0;
        gbc.gridwidth = 1;
        container.add(balanceLabel, gbc);
        ///////////////////////////////////////////////

        //Submit button ///////////////////////////////
        submit = new JButton("Submit");
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 3;
        container.add(submit, gbc);

        nameComboBox.addItemListener(e->{
            int index = nameComboBox.getSelectedIndex();
            if(index == -1){
                balanceLabel.setText("Balance: $1000.0");
            } else {
                Double balance = 1000.0;
                for (int i = 0; i < data[index].length(); i++) {
                    if (data[index].substring(i, i + 1).matches("[0-9]")) {
                        balance = Double.parseDouble(data[index].substring(i, data[index].length()));
                        break;
                    }
                }
                balanceLabel.setText("Balance: $" + Double.toString(balance));
            }
        });
        submit.setBackground(new Color(100, 0,0));
        submit.setForeground(new Color(255,255,255));
        container.setBackground(new Color(4, 120, 0));
        add(container, BorderLayout.CENTER);

        submit.addActionListener(e->{
            super.validate();
            if(nameComboBox.getSelectedIndex() > 0) {
                System.out.println("Submit button pressed");
                int index = nameComboBox.getSelectedIndex();
                System.out.println("Index: " + index);
                name = nameComboBox.getSelectedItem().toString();
                System.out.println(name);
                String currData = data[index];
                Double balance = Double.parseDouble(currData.substring(name.length()));
                System.out.println(balance);

                BettingScreen bs = new BettingScreen(name, balance);
                dispose();
                //createGame(name, balance);
            } else {
                name = nameComboBox.getSelectedItem().toString();
                System.out.println(name);
                if(name.equalsIgnoreCase("New Player") || name.equalsIgnoreCase("Enter your name in this box")){
                    JOptionPane.showMessageDialog(null, "I doubt your name is \"New Player\", enter something else in the box. It's editable", "Enter name", JOptionPane.ERROR_MESSAGE);
                } else {
                    BettingScreen bettingScreen = new BettingScreen(name, 1000.0);
                    dispose();
                    //createGame(name, 1000.0);
                }
            }
        });

        setVisible(true);
    }

    /**
     * Method to get the array of text from the data.txt file
     *
     * @return the array of strings
     */

    public String[] getData(){
        ArrayList<String> lines = new ArrayList<String>();
        try {
            Scanner sc = new Scanner(new File("src/data.txt"));
            while (sc.hasNextLine()) {
                lines.add(sc.nextLine());
            }
            System.out.println(lines);
        } catch (Exception e){

        }
        lines.add(0, "New Player");
        String[] arr = lines.toArray(new String[0]);
        return arr;
    }

    /**
     * Method to get the names from an array of data
     *
     * @param data the array of strings to get the names from
     * @return
     */

    public String[] getNames(String[] data) {
        ArrayList<String> list = new ArrayList<String>();
        for (String str : data) {
            for (int i = 0; i < str.length(); i++) {
                if (str.substring(i, i + 1).matches("[0-9]")) {
                    list.add(str.substring(0, i));
                    break;
                }
            }
        }
        list.add(0, "New Player");
        return list.toArray(new String[0]);
    }

    /**
     * Method to create a game when the submit button is pressed
     *
     * @param name the player's name
     * @param balance the player's balance
     */

    public void createGame(String name, double balance){
        Game game = new Game(name, balance, 100);
        game.setBounds(200, 100, 800, 400);
        game.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        game.setExtendedState(JFrame.MAXIMIZED_BOTH);
        game.setUndecorated(true);
        game.setTitle("BlackJack");
        game.setVisible(true);

        dispose();
    }

}
