package ImageHoster.controller;

import ImageHoster.HardCodedImage;
import ImageHoster.model.Image;
import ImageHoster.model.User;
import ImageHoster.service.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Base64;
import java.util.Date;
import java.util.List;

@Controller
public class ImageController {

    @Autowired
    private ImageService imageService;

    @Autowired
    private HardCodedImage hardCodedImage;

    @RequestMapping("images")
    public String getUserImages(Model model) {
        List<Image> images;

        images = imageService.getAllImages();

        model.addAttribute("images", images);

        return "images";
    }

    @RequestMapping("/images/{title}")
    public String showImage(@PathVariable ("title") String title, Model model) {

        Image image = imageService.getImageByTitle(title);
        model.addAttribute("image", image);
        return "images/image";
    }

    @RequestMapping("/images/upload")
    public String newImage() {
        return "images/upload";
    }

    @RequestMapping(value = "/images/upload", method = RequestMethod.POST)
    public String createImage(@RequestParam("file") MultipartFile file, Image newImage, Model model, HttpSession session) throws IOException {

        Date date = new Date();

        User user = (User) session.getAttribute("loggeduser");
        newImage.setImageFile(convertUploadedFileToBase64(file));
        newImage.setDate(date);
        newImage.setUser(user);

        imageService.uploadImage(newImage);

        return "redirect:/images";
    }

    @RequestMapping(value = "/editImage")
    public String editImage(@RequestParam("imageId") Integer imageId, Model model) {

        Image image = imageService.getImage(imageId);
        model.addAttribute("image", image);
        return "images/edit";
    }

    @RequestMapping(value = "/editImage", method = RequestMethod.PUT)
    public String editImageSubmit(@RequestParam("file") MultipartFile file, @RequestParam("imageId") Integer imageId,
                                  Image updatedImage, HttpSession session) throws IOException {
        Image image = imageService.getImage(imageId);
        String updatedImageData = convertUploadedFileToBase64(file);

        if (updatedImageData.isEmpty())
            updatedImage.setImageFile(image.getImageFile());
        else {
            updatedImage.setImageFile(updatedImageData);
        }

        updatedImage.setId(imageId);
        User user = (User) session.getAttribute("loggeduser");
        updatedImage.setUser(user);
        updatedImage.setDate(new Date());

        imageService.updateImage(updatedImage);

        return "redirect:/images/" + updatedImage.getTitle();
    }

    @RequestMapping(value = "/deleteImage", method = RequestMethod.DELETE)
    public String deleteImageSubmit(@RequestParam(name = "imageId") Integer imageId) {

        imageService.deleteImage(imageId);
        return "redirect:/images";
    }

    private String convertUploadedFileToBase64(MultipartFile file) throws IOException {
        return Base64.getEncoder().encodeToString(file.getBytes());
    }
}
