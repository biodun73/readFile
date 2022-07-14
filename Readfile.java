import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedOutputStream;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Objects;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ScrollPaneConstants;

public class Readfile extends JFrame implements ActionListener{
    JTextArea area;
    JButton save;
    JButton read;
    JPanel panel1;
    JPanel panel2;
    Container con;
    Path path;
    JScrollPane scroll;
    public Readfile(){
        super("How to write and read file");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(500,500);
        setLayout(new BorderLayout());
        setLocationRelativeTo(null);
        init();
  
    }
    private void init(){
        con  =  getContentPane();
        panel1 = new JPanel();
        panel2 = new JPanel();
        save = new JButton("Save");
        read = new JButton("Read");
        area = new JTextArea(40,40);
        area.setCaretPosition(area.getDocument().getLength());
        area.setFont(new Font("aria", Font.BOLD, 22));
        area.setLineWrap(true);
        area.setWrapStyleWord(true);
        scroll= new JScrollPane(area);
        scroll.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
        scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        //add panel1 to container
        con.add(panel1,BorderLayout.CENTER);
        //preferable size 
        panel1.setPreferredSize(new Dimension(480,350));
        panel1.setBorder(BorderFactory.createLoweredBevelBorder());
        con.add(scroll);
        //panel1.add(scroll);
        //add panel1 to container
        con.add(panel2,BorderLayout.SOUTH);
        //preferable size 
        panel2.setPreferredSize(new Dimension(500,50));
        panel2.setBorder(BorderFactory.createRaisedBevelBorder());
        panel2.add(save);
        panel2.add(read);
        //add button to actionlistener
        save.addActionListener(this);
        read.addActionListener(this);
    }
     
    public static void main(String[] args) {
        Readfile r = new Readfile();
        r.setVisible(true);
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        //create path
        Path path = Paths.get("C://xampp//htdocs//JAVA//Johnson.txt");
       
        try{
            //write text in the file
        OutputStream  output = new BufferedOutputStream(Files.newOutputStream(path));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(output));

        // write text

        Object source = e.getSource();
        if (source == save) {
            String a = area.getText();
            writer.write(a);
            writer.flush();
            writer.close();
            area.setText("");
        }
        if (source == read) {
            //read text
            area.read(new InputStreamReader(Objects.requireNonNull(getClass().getResourceAsStream("./johnson.txt"))),null);
        }

    } catch (Exception el) {
       
        System.out.println("Message: " + e);
    }

}
}
