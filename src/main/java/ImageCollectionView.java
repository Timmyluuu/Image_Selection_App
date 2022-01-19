import javax.swing.*;
import java.awt.*;
import java.util.List;


public class ImageCollectionView extends JPanel implements Observer {
    ImageCollectionModel imageCollectionModel;
    Controller controller;
    int curframe_width = 800;

    ImageCollectionView (ImageCollectionModel m, Controller c) {
        setBackground(Color.WHITE);
        setBorder(BorderFactory.createLineBorder(Color.GRAY));
        imageCollectionModel = m;
        controller = c;
    }

    public void drawGrid(int width) {
        List<ImageModel> models = imageCollectionModel.getModels();
        int filter = imageCollectionModel.getFilter();
        int counts = 0;
        for (ImageModel m: models) {
            if (imageCollectionModel.getcheck_filter()) {
                if (m.getRating() >= filter) {
                    counts++;
                }
            } else {
                counts++;
            }
        }
        int index = (width - 10) / 260;
        int temp = counts % index;
        int row = 0;
        int col = 0;
        if (temp != 0) {
            row = counts / index + 1;
            col = index;
        } else {
            row = counts / index;
            if (row == 0) {
                col = temp;
            } else {
                col = index;
            }
        }
        setPreferredSize(new Dimension(col * 260 + 10,row * 250 + 15));
        setLayout(new GridBagLayout());
        GridBagConstraints gc = new GridBagConstraints();
        int count = 0;
        gc.gridx = 0;
        gc.gridy = 0;
        for (ImageModel m: models) {
            m.getIv().update(m);
            if (imageCollectionModel.getcheck_filter()) {
                if (m.getRating() >= filter) {
                    if (count == index) {
                        gc.gridy++;
                        gc.gridx = 0;
                        count = 0;
                    }
                    count++;
                    add(m.getIv(), gc);
                    gc.gridx++;
                }
            } else {
                if (count == index) {
                    gc.gridy++;
                    gc.gridx = 0;
                    count = 0;
                }
                count++;
                add(m.getIv(), gc);
                gc.gridx++;
            }
        }
    }

    public void drawList(int width) {
        List<ImageModel> models = imageCollectionModel.getModels();
        int filter = imageCollectionModel.getFilter();
        int count = 0;
        for (ImageModel m: models) {
            if (imageCollectionModel.getcheck_filter()) {
                if (m.getRating() >= filter) {
                    count++;
                }
            } else {
                count++;
            }
        }
        setPreferredSize(new Dimension(width, count * 210));
        setLayout(new GridBagLayout());
        GridBagConstraints gc = new GridBagConstraints();
        gc.gridx = 0;
        gc.gridy = 0;
        for (ImageModel m: models) {
            m.getIv().update(m);
            if (imageCollectionModel.getcheck_filter()) {
                if (m.getRating() >= filter) {
                    add(m.getIv(), gc);
                    gc.gridy++;
                }
            } else {
                add(m.getIv(), gc);
                gc.gridy++;
            }
        }
    }

    public void update(Object observable) {
        int mode = imageCollectionModel.getMode();
        removeAll();
        if (mode == 0) {
            drawGrid(curframe_width);
        } else {
            drawList(curframe_width);
        }
        revalidate();
        repaint();
    }

    public void resize_com (int curwidth) {
        curframe_width = curwidth;
        int mode = imageCollectionModel.getMode();
        removeAll();
        if (mode == 0) {
            drawGrid(curwidth);
        } else {
            drawList(curwidth);
        }
        revalidate();
        repaint();
    }

}
