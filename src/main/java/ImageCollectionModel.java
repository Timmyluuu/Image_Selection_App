import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.*;


public class ImageCollectionModel {

    private List<ImageModel> models;
    private List<Observer> observers;



    boolean check_filter = false;
    int filter = 0;
    int mode = 0; //Grid = 0, List = 1;

    public ImageCollectionModel() {
        models = new ArrayList<ImageModel>();
        observers = new ArrayList<Observer>();
    }


    public void setCheck_filter(boolean s) {
        check_filter = s;
        notifyObservers();
    }


    public void setFilter(int f) {
        filter = f;
    }

    public void setMode(int m) {
        mode = m;
        notifyObservers();
    }


    public boolean getcheck_filter() {
        return check_filter;
    }

    public int getFilter() {
        return filter;
    }

    public int getMode() {
        return mode;
    }

    public List<ImageModel> getModels() {
        return models;
    }

    public void addImage(ImageModel img) {
        models.add(img);
        notifyObservers();
    }

    public void saveFile() {
        File file = new File("save.txt");
        try {
            if (!file.exists()) {
                file.createNewFile();
            } else {
            }
            try {
                FileWriter fw = new FileWriter(file);
                for(ImageModel m: models) {
                    fw.write(m.getImagePath() + "\n");
                }
                fw.close();
            } catch (Exception e) {

            }

        } catch (IOException e) {

        }

    }

    public void loadFile(Controller c) {
        File file = new File("save.txt");
        try {
            if (file.exists()) {
                Scanner x = new Scanner(file);
                while(x.hasNext()) {
                    ImageModel tempModel = new ImageModel(x.next(), this);
                    ImageView tempView = new ImageView(tempModel, c);
                    tempModel.setIv(tempView);
                    addImage(tempModel);
                }
            } else {
            }
        } catch (Exception e) {

        }
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
