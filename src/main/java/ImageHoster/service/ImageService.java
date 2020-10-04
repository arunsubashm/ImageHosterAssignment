package ImageHoster.service;

import ImageHoster.HardCodedImage;
import ImageHoster.model.Image;
import ImageHoster.repository.ImageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class ImageService {

    @Autowired
    private ImageRepository imageRepository;

    private HardCodedImage hardCodedImage = new HardCodedImage();

    private List<Image> images = new ArrayList<>();


    public ImageService() {

        Date date = new Date();

        images.add(new Image(1, "Dr. Strange", hardCodedImage.getDrStrange(), "",date));
        images.add(new Image(2, "SpiderMan", hardCodedImage.getSpiderMan(), "",date));
    }

    public List<Image> getAllImages() {

        return imageRepository.getAllImages();
    }

    public void uploadImage(Image image) {
        Image newImage;

        newImage = imageRepository.uploadImage(image);

        return;
    }

    public Image getImageByTitle(String title) {

        return imageRepository.getImageByTitle(title);
    }

    public Image getImage(Integer imageId) {

        return imageRepository.getImage(imageId);
    }

    public void updateImage(Image updatedImage) {

        imageRepository.updateImage(updatedImage);
    }

    public void deleteImage(Integer imageId) {

        imageRepository.deleteImage(imageId);
    }
}
