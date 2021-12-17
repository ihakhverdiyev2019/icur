package az.elixir.icurriculum.utils;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class CombineImages {

    public void combine(String img1, String img2, int x, int y) throws IOException {

        BufferedImage image = ImageIO.read(new File(img1));
        BufferedImage overlay = ImageIO.read(new File(img2));

        // create the new image, canvas size is the max. of both image sizes
        int w = Math.max(image.getWidth(), overlay.getWidth());
        int h = Math.max(image.getHeight(), overlay.getHeight());
        BufferedImage combined = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);

        // paint both images, preserving the alpha channels
        Graphics g = combined.getGraphics();
        g.drawImage(image, 0, 0, null);
        g.drawImage(overlay, x, y, null);

    // Save as new image
        ImageIO.write(combined, "PNG", new File("combined.png"));
    }
}
