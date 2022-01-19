import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class Toolbar extends JPanel {

    Controller controller;
    JButton upload;
    JButton grid;
    JButton list;
    ImageCollectionModel icm;

    Toolbar(Controller c, ImageCollectionModel i){
        controller = c;
        icm = i;
        setPreferredSize(new Dimension(800,50));
        setLayout(new GridBagLayout());
        GridBagConstraints gc = new GridBagConstraints();

        setBackground(Color.WHITE);
        setBorder(BorderFactory.createLineBorder(Color.BLACK));

        ImageIcon upIcon = new ImageIcon(getClass().getResource("load.png"));
        ImageIcon grIcon = new ImageIcon(getClass().getResource("grid.png"));
        ImageIcon liIcon = new ImageIcon(getClass().getResource("list.png"));
        Image upicon = upIcon.getImage();
        Image gricon = grIcon.getImage();
        Image liicon = liIcon.getImage();
        Image uplo = upicon.getScaledInstance(20, 20, Image.SCALE_SMOOTH);
        Image gri = gricon.getScaledInstance(20,20,Image.SCALE_SMOOTH);
        Image lis = liicon.getScaledInstance(20,20,Image.SCALE_SMOOTH);
        upload = new JButton("Upload");
        upload.setIcon(new ImageIcon(uplo));
        upload.addActionListener(controller);
        gc.weightx = 0.5;
        gc.gridx = 0;
        gc.gridy = 0;
        add(upload, gc);

        gc.gridx = 1;
        grid = new JButton("Grid");
        grid.setIcon(new ImageIcon(gri));
        grid.addActionListener(controller);
        add(grid, gc);

        gc.gridx = 2;
        list = new JButton("List");
        list.setIcon(new ImageIcon(lis));
        list.addActionListener(controller);
        add(list, gc);

        JLabel name = new JLabel("");
        gc.gridx = 3;
        gc.weightx = 10;
        add(name, gc);

        JCheckBox filter = new JCheckBox("Filter: ");
        gc.gridx = 4;
        gc.weightx = 0.5;
        add(filter, gc);

        filter.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (filter.isSelected()) {
                    icm.setCheck_filter(true);
                } else {
                    icm.setCheck_filter(false);
                }
            }
        });

        JPanel star = new JPanel();
        JButton s1 = new JButton("");
        JButton s2 = new JButton("");
        JButton s3 = new JButton("");
        JButton s4 = new JButton("");
        JButton s5 = new JButton("");
        ImageIcon uIcon = new ImageIcon(getClass().getResource("unfilled.png"));
        ImageIcon fIcon = new ImageIcon(getClass().getResource("filled.png"));
        Image uicon = uIcon.getImage();
        Image ficon = fIcon.getImage();
        Image unfilled = uicon.getScaledInstance(20, 20, Image.SCALE_SMOOTH);
        Image filled = ficon.getScaledInstance(20,20,Image.SCALE_SMOOTH);

        s1.setIcon(new ImageIcon(unfilled));
        s2.setIcon(new ImageIcon(unfilled));
        s3.setIcon(new ImageIcon(unfilled));
        s4.setIcon(new ImageIcon(unfilled));
        s5.setIcon(new ImageIcon(unfilled));
        star.setBackground(Color.WHITE);
        star.setLayout(new GridLayout(1,5));
        s1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                icm.setFilter(1);
                icm.setCheck_filter(icm.getcheck_filter());
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
                icm.setFilter(2);
                icm.setCheck_filter(icm.getcheck_filter());
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
                icm.setFilter(3);
                icm.setCheck_filter(icm.getcheck_filter());
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
                icm.setFilter(4);
                icm.setCheck_filter(icm.getcheck_filter());
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
                icm.setFilter(5);
                icm.setCheck_filter(icm.getcheck_filter());
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

        gc.gridx = 5;
        add(star, gc);
    }
}
