import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.*;
import javax.swing.*;
import java.awt.Image;


public class ImageView extends JPanel implements Observer {

    private ImageModel model;
    private Controller controller;

    public ImageView(ImageModel m, Controller c) {
        controller = c;
        model = m;
        model.addObserver(this);
        drawImage();
    }

    public void drawImage() {
        int mode = model.getIcm().getMode();
        int rating = model.getRating();
        if (mode == 0) {
            setPreferredSize(new Dimension(250, 250));
        } else {
            setPreferredSize(new Dimension(430, 200));
        }
        setBackground(Color.WHITE);
        setBorder(BorderFactory.createLineBorder(Color.GRAY));
        setLayout(new GridBagLayout());
        GridBagConstraints gc = new GridBagConstraints();

        Image i = model.getImage().getScaledInstance(200, 130, Image.SCALE_SMOOTH);
        String imagePath = model.getImagePath();
        String imageName = model.getImageName();
        String lastModifieddate = model.getLastModifiedDate();

        JLabel image = new JLabel();
        image.addMouseListener(controller);
        image.setIcon(new ImageIcon(i));
        image.setName(imagePath);
        gc.gridx = 0;
        gc.gridy = 0;
        add(image, gc);

        JPanel container = new JPanel();
        container.setBackground(Color.WHITE);
        container.setLayout(new GridBagLayout());
        container.setPreferredSize(new Dimension(200 ,110));
        GridBagConstraints gcc = new GridBagConstraints();

        gcc.gridx = 0;
        gcc.gridy = 0;
        JLabel name = new JLabel(imageName, SwingConstants.LEFT);
        container.add(name, gcc);


        gcc.gridy = 1;
        JLabel date = new JLabel(lastModifieddate, SwingConstants.LEFT);
        container.add(date, gcc);

        gcc.gridy = 2;
        JButton reset = new JButton("Reset");

        ImageIcon uIcon = new ImageIcon(getClass().getResource("unfilled.png"));
        ImageIcon fIcon = new ImageIcon(getClass().getResource("filled.png"));
        Image uicon = uIcon.getImage();
        Image ficon = fIcon.getImage();
        Image unfilled = uicon.getScaledInstance(20, 20, Image.SCALE_SMOOTH);
        Image filled = ficon.getScaledInstance(20,20,Image.SCALE_SMOOTH);

        JPanel star = new JPanel();
        JButton s1 = new JButton();
        JButton s2 = new JButton();
        JButton s3 = new JButton();
        JButton s4 = new JButton();
        JButton s5 = new JButton();

        reset.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                model.setRating(0);
                s1.setIcon(new ImageIcon(unfilled));
                s2.setIcon(new ImageIcon(unfilled));
                s3.setIcon(new ImageIcon(unfilled));
                s4.setIcon(new ImageIcon(unfilled));
                s5.setIcon(new ImageIcon(unfilled));
            }
        });
        container.add(reset, gcc);

        gcc.gridy = 3;
        s1.setIcon(new ImageIcon(unfilled));
        s2.setIcon(new ImageIcon(unfilled));
        s3.setIcon(new ImageIcon(unfilled));
        s4.setIcon(new ImageIcon(unfilled));
        s5.setIcon(new ImageIcon(unfilled));
        if (rating == 1) {
            s1.setIcon(new ImageIcon(filled));
        } else if (rating == 2) {
            s1.setIcon(new ImageIcon(filled));
            s2.setIcon(new ImageIcon(filled));
        } else if (rating == 3) {
            s1.setIcon(new ImageIcon(filled));
            s2.setIcon(new ImageIcon(filled));
            s3.setIcon(new ImageIcon(filled));
        } else if (rating == 4) {
            s1.setIcon(new ImageIcon(filled));
            s2.setIcon(new ImageIcon(filled));
            s3.setIcon(new ImageIcon(filled));
            s4.setIcon(new ImageIcon(filled));

        } else if (rating == 5) {
            s1.setIcon(new ImageIcon(filled));
            s2.setIcon(new ImageIcon(filled));
            s3.setIcon(new ImageIcon(filled));
            s4.setIcon(new ImageIcon(filled));
            s5.setIcon(new ImageIcon(filled));
        }
        star.setBackground(Color.WHITE);
        star.setLayout(new GridLayout(1,5));
        s1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                model.setRating(1);
                s1.setIcon(new ImageIcon(filled));
                s2.setIcon(new ImageIcon(unfilled));
                s3.setIcon(new ImageIcon(unfilled));
                s4.setIcon(new ImageIcon(unfilled));
                s5.setIcon(new ImageIcon(unfilled));
            }
        });
        s2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                model.setRating(2);
                s1.setIcon(new ImageIcon(filled));
                s2.setIcon(new ImageIcon(filled));
                s3.setIcon(new ImageIcon(unfilled));
                s4.setIcon(new ImageIcon(unfilled));
                s5.setIcon(new ImageIcon(unfilled));
            }
        });
        s3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                model.setRating(3);
                s1.setIcon(new ImageIcon(filled));
                s2.setIcon(new ImageIcon(filled));
                s3.setIcon(new ImageIcon(filled));
                s4.setIcon(new ImageIcon(unfilled));
                s5.setIcon(new ImageIcon(unfilled));
            }
        });
        s4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                model.setRating(4);
                s1.setIcon(new ImageIcon(filled));
                s2.setIcon(new ImageIcon(filled));
                s3.setIcon(new ImageIcon(filled));
                s4.setIcon(new ImageIcon(filled));
                s5.setIcon(new ImageIcon(unfilled));
            }
        });
        s5.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                model.setRating(5);
                s1.setIcon(new ImageIcon(filled));
                s2.setIcon(new ImageIcon(filled));
                s3.setIcon(new ImageIcon(filled));
                s4.setIcon(new ImageIcon(filled));
                s5.setIcon(new ImageIcon(filled));
            }
        });
        star.add(s1);
        star.add(s2);
        star.add(s3);
        star.add(s4);
        star.add(s5);
        gcc.gridy = 4;
        container.add(star, gcc);

        if (mode == 0) {
            gc.gridy = 1;
        } else {
            gc.gridx = 1;
        }
        add(container, gc);
        revalidate();
        repaint();
    }

    public void update(Object observable) {
        // XXX Fill this in with the logic for updating the view when the model
        // changes.
        removeAll();
        drawImage();
    }
}
