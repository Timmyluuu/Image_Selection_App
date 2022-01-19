import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;


public class Main {
    public static void main(String[] args) {
        // Set up the window.
        JFrame frame = new JFrame("Fotag!");
        frame.setMinimumSize(new Dimension(450, 350));
        frame.setSize(800, 600);
        frame.setMaximumSize(new Dimension(800, 600));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        ImageCollectionModel icm = new ImageCollectionModel();
        Controller c = new Controller(icm);
        ImageCollectionView icv = new ImageCollectionView(icm, c);
        Toolbar toolbar = new Toolbar(c, icm);
        icm.addObserver(icv);

        icm.loadFile(c);

        JPanel rootPanel = new JPanel();
        rootPanel.setLayout(new BorderLayout());
        JScrollPane scrollPane = new JScrollPane(icv, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        rootPanel.add(toolbar, BorderLayout.NORTH);
        rootPanel.add(scrollPane, BorderLayout.CENTER);

        frame.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                icv.resize_com(frame.getBounds().width);
            }
        });

        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                super.windowClosing(e);
                icm.saveFile();
            }
        });

        frame.add(rootPanel);

        frame.setVisible(true);

    }
}
