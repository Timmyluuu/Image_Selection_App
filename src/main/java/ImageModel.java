import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.*;
import java.io.File;
import java.text.SimpleDateFormat;


public class ImageModel {
    private List<Observer> observers;
    private ImageView iv;
    private BufferedImage image;
    private String imageName;
    private String imagePath;
    private String lastModifiedDate;
    private int rating = 0;

    private ImageCollectionModel icm;


    public ImageModel(String path, ImageCollectionModel i) {
        this.observers = new ArrayList();
        icm = i;
        File file = new File(path);
        try {
            image = ImageIO.read(file);
            imagePath = path;
            imageName = file.getName();
            SimpleDateFormat f = new SimpleDateFormat("MM/dd/yyyy");
            lastModifiedDate = f.format(file.lastModified());
        } catch (IOException e) {
            System.out.println("Fail to read a image!");
        }
    }


    public ImageView getIv() {
        return iv;
    }

    public BufferedImage getImage() {
        return image;
    }

    public String getImageName() {
        return imageName;
    }

    public String getImagePath() {
        return imagePath;
    }

    public String getLastModifiedDate() {
        return lastModifiedDate;
    }

    public int getRating() {
        return rating;
    }

    public ImageCollectionModel getIcm() {
        return icm;
    }

    public void setRating(int r) {
        rating = r;
    }

    public void setIv(ImageView i) {
        iv = i;
    }

    public void addObserver(Observer observer) {
        this.observers.add(observer);
    }

    public void removeObserver(Observer observer) {
        this.observers.remove(observer);
    }

    public void notifyObservers() {
        for (Observer observer: this.observers) {
            observer.update(this);
        }
    }
}
