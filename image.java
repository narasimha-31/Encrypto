import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.*;
import java.awt.Font;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.awt.*;
import java.awt.event.*;

public class image extends Frame implements ActionListener, KeyListener {

    JFrame f;
    JLabel l1;
    JButton button;
    JButton b2;
    JTextField textField;

    image() {
        f = new JFrame();
        l1 = new JLabel("Enter key below : ");
        button = new JButton();
        b2 = new JButton("Help");
        textField = new JTextField(10);

        Font font = new Font("Times New Roman", Font.BOLD, 17);
        Font font1 = new Font("Times New Roman", Font.BOLD, 15);

        JOptionPane.showMessageDialog(f, "Press ok to start testing", "Testing", JOptionPane.INFORMATION_MESSAGE);
        JOptionPane.showMessageDialog(f,
                "If you are doing decryption \nyou have to enter the same \nkey which you gave for \nencryption, if the key is \nnot matched the image will\nbe corrupted.",
                "Warning", JOptionPane.ERROR_MESSAGE);

        l1.setBounds(120, 100, 100, 30);

        textField.setFont(font);
        textField.setBounds(120, 150, 150, 30);
        textField.addKeyListener(this);

        button.setText("Open Image");
        button.setFont(font);
        button.setBounds(120, 230, 150, 30);
        button.addActionListener(this);
        b2.setFont(font1);
        b2.setBounds(280, 10, 70, 20);
        b2.addActionListener(this);

        f.setContentPane(new JLabel(new ImageIcon("background.png")));

        f.setTitle("IMAGE CRYPTOGRAPHY");
        f.setSize(410, 415);
        f.setLocationRelativeTo(null);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setResizable(false);
        f.setLayout(null);
        f.add(l1);
        f.add(button);
        f.add(b2);
        f.add(textField);
        f.setVisible(true);
    }

    public static void operate(int key) {

        JFileChooser fileChooser = new JFileChooser();
        fileChooser.showOpenDialog(null);
        File file = fileChooser.getSelectedFile();
        // file FileInputStream
        try {

            FileInputStream fis = new FileInputStream(file);

            byte[] data = new byte[fis.available()];
            fis.read(data);
            int i = 0;
            for (byte b : data) {
                System.out.println(b);
                data[i] = (byte) (b ^ key);
                i++;
            }

            FileOutputStream fos = new FileOutputStream(file);
            fos.write(data);
            fos.close();
            fis.close();
            try {
                audio();
            } catch (LineUnavailableException | UnsupportedAudioFileException | IOException e1) {
                e1.printStackTrace();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new image();

    }

    public void actionPerformed(ActionEvent q) {
        if (q.getSource() == button) {
            if (textField.getText().length() == 0) {
                JOptionPane.showMessageDialog(f, "Enter the key first", "Error", JOptionPane.WARNING_MESSAGE);
            }

            else {
                JOptionPane.showMessageDialog(f, "Press ok to open file manager", "Action taken",
                        JOptionPane.INFORMATION_MESSAGE);
                String text = textField.getText();
                int temp = Integer.parseInt(text);
                operate(temp);
            }
        } else if (q.getSource() == b2) {
            JOptionPane.showMessageDialog(f,
                    "\n1)	 Give the Key \n2) 	Choose the file from file Explorer\n3) 	Open that file\n4) 	Operation is performed\nNote: If you are doing decryption you have to enter the same key which you gave for\nencryption, if the key is not matched the image will be corrupted.",
                    "Help", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    private static void audio() throws LineUnavailableException, UnsupportedAudioFileException, IOException {
        File file;
        file = new File("voice.wav");
        AudioInputStream audioStream = AudioSystem.getAudioInputStream(file);
        Clip clip = AudioSystem.getClip();
        clip.open(audioStream);
        clip.start();
 
    }

    public void keyPressed(KeyEvent e) {
        String value1 = textField.getText();
        value1.length();
        if (e.getKeyChar() >= '0' && e.getKeyChar() <= '9' || e.getKeyChar() == KeyEvent.VK_BACK_SPACE) {
            textField.setEditable(true);
        } else {
            textField.setEditable(false);
            JOptionPane.showMessageDialog(this, "Characters not allowed", "Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }
}
