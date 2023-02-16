import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.awt.event.*;

//package assignment5;

public class Assignment5 extends JFrame{

    public Assignment5()
    {
        super("Assignment 5 Jensen Bromm");

        MyPanel myPanel=new MyPanel();

        Container c=this.getContentPane();
        c.setLayout(new BorderLayout());

        JButton saveImage=new JButton("Save Image");
        saveImage.addActionListener(e->{
            myPanel.saveImage();
        });
        c.add(saveImage,BorderLayout.NORTH);
        c.add(myPanel,BorderLayout.CENTER);

        addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent ev) {
                
                if(myPanel.timer.isRunning()== true){
                    myPanel.timer.stop();
                }
                else{
                    myPanel.timer.start();
                }
            }
        });
    }
    public static void main(String[] args) throws Exception {
        Assignment5 frame=new Assignment5();
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Program stops when closed
            frame.setBounds(0, 0, 1500, 1000);// Set size and bounds of window

        //Set the visibility of the window
            frame.setVisible(true);
    }
}

class MyPanel extends JPanel implements ActionListener{
    public Timer timer;
    private double angle;
    public MyPanel()
    {
        timer=new Timer(1,this);
        timer.start();
        setPreferredSize(new Dimension(500,500));
        this.setBackground(Color.WHITE);
    }

    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        Graphics2D g2=(Graphics2D) g;
        g2.rotate(angle,450,475);
        Ellipse2D circle=new Ellipse2D.Double(150,175,600,600);
        Ellipse2D gold=new Ellipse2D.Double(25,400,700,600);
        g2.setClip(circle);
        g2.setColor(new Color(1,30,65));
        g2.fill(circle);
        g2.setColor(new Color(135,113,77));
        g2.fill(gold);
        g2.setColor(Color.WHITE);
        g2.fill(new Eagle());

        
        
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // TODO Auto-generated method stub

        if(angle<360)
        {
            angle+=0.1;
        }
        else
        {
            angle=0;
        }
        
        repaint();
    }

    public void saveImage()
    {
        BufferedImage image=new BufferedImage(getWidth(),getHeight(),BufferedImage.TYPE_INT_RGB);
        Graphics2D g2=image.createGraphics();
        paint(g2);
        try{
            ImageIO.write(image,"png",new File("Eagle.png"));
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

}