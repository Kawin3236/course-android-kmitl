package kmitl.kawin58070006.horyuni;

import java.util.List;

/**
 * Created by Administrator on 6/11/2560.
 */

public class Detail {
    private String name;
    private ImageUpload img;

    public Detail(String name) {
        this.name = name;
    }

    public Detail(String name, ImageUpload imageUpload) {
        this.name = name;
        this.img = imageUpload;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ImageUpload getImg() {
        return img;
    }

    public void setImg(ImageUpload img) {
        this.img = img;
    }

    public String getName() {
        return name;
    }
}
