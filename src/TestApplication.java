import javax.imageio.IIOException;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.Date;

public class TestApplication extends JFrame {
    private JPanel mainPanel;
    private JPanel JPanel1;
    private JPanel JPanel2;
    private JButton SubmitBtn;
    private JTextArea TxtContent;
    public String data = "recourses\\data.txt";

    public static void main(String[] args) {
        TestApplication Page = new TestApplication("Loading from File");
        Page.setVisible(true);
    }

    public TestApplication(String loading_from_file) {
        setContentPane(mainPanel);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setPreferredSize(new Dimension(500, 500));
        pack();
        TxtContent.setLineWrap(true);
        SubmitBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                /*System.out.print(event.getSource());*/
                System.out.println("UI THREAD: " + Thread.currentThread().getName());
                SwingMethod();
            }

            public void SwingMethod(){
                new SwingWorker<Object, Object>() {

                    @Override
                    protected Object doInBackground() throws Exception {
                        for (int i = 0; i < 999999999; i++) {
                            new Date();
                        }
                        File file = new File("recourses\\data.txt");
                        BufferedReader reader;
                        try {
                            reader = new BufferedReader(new FileReader(file));
                            String text;
                            String savetext = "";

                            while ((text = reader.readLine()) != null) {
                                savetext += text;
                            }
                            TxtContent.setText(savetext);
                        } catch (IOException f) {
                            f.printStackTrace();
                        }
                        System.out.println("Swing Method:" + Thread.currentThread().getName());
                        return null;
                    }
                }.execute();
            }
        });
        }
}