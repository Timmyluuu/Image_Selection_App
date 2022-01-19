import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.util.List;
import javax.swing.*;
import javax.imageio.ImageIO;
import java.io.IOException;
import java.io.File;
import javax.swing.ImageIcon;


public class Controller implements ActionListener, MouseListener, MouseMotionListener {
    ImageCollectionModel icm;

    public Controller(ImageCollectionModel m) {
        icm = m;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();
        if (command == "Upload") {
            JFileChooser fc = new JFileChooser();
            fc.setMultiSelectionEnabled(true);
            int returnVal = fc.showOpenDialog(null);
            File[] files = null;
            if (returnVal == JFileChooser.APPROVE_OPTION) {
                files = fc.getSelectedFiles();
            } else {
                return;
            }
            for (File f: files) {
                ImageModel tempModel = new ImageModel(f.getAbsolutePath(), icm);
                ImageView tempView = new ImageView(tempModel, this);
                tempModel.setIv(tempView);
                boolean exist = false;
                List<ImageModel> m = icm.getModels();
                for (ImageModel models: m) {
                    if (tempModel.getImageName().equals(models.getImageName())) {
                        exist = true;
                    }
                }
                if (!exist) {
                    icm.addImage(tempModel);
                }
            }
        } else if (command == "List") {
            icm.setMode(1);
        } else if (command == "Grid") {
            icm.setMode(0);
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
            String path = e.getComponent().getName();
            File file = new File(path);

            try {
                BufferedImage image = ImageIO.read(file);
                ;
                String imageName = file.getName();
                JFrame frame = new JFrame(imageName);
                frame.setSize(new Dimension(800, 600));

                int height = image.getHeight();
                int width = image.getWidth();

                JLabel il = new JLabel("", SwingConstants.CENTER);
                il.setPreferredSize(new Dimension(800, 600));

                if (width >= 800 || height >= 600) {
                    while(width > 800 || height > 600) {
                        width = width / 10;
                        height = height / 10;
                    }
                }
                double indexW = 800 / width;
                double indexH = 600 / height;
                double factor;
                if (indexW > indexH) {
                    factor = indexW;
                } else {
                    factor = indexH;
                }
                int finalW = (int) factor * width;
                int finalH = (int) factor * height;

                if (finalW >= 760) {
                    finalW -= width;
                    finalH -= height;
                }

                if (finalH >= 670) {
                    finalW -= width;
                    finalH -= height;
                }

                BufferedImage resizedImage = new BufferedImage(finalW, finalH, image.TYPE_INT_RGB);
                Graphics2D g2 = resizedImage.createGraphics();
                g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
                g2.drawImage(image, 0, 0, finalW, finalH, null);
                g2.dispose();
                il.setIcon(new ImageIcon(resizedImage));
                frame.add(il);
                frame.setVisible(true);
            } catch (IOException ie) {

            }

    }


    @Override
    public void mousePressed(MouseEvent e) {

    }


    @Override
    public void mouseReleased(MouseEvent e) {

    }


    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void mouseDragged(MouseEvent e) {

    }

    @Override
    public void mouseMoved(MouseEvent e) {

    }
}
