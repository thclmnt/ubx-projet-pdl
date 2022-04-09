package pdl.imageprocessing;

import boofcv.struct.image.GrayU8;
import boofcv.struct.image.Planar;

public class Flip {
    public static void flip(Planar<GrayU8> image, Planar<GrayU8> output) {
        for (int x = 0; x < image.width; x++) {
            for (int y = 0; y < image.height; y++) {
                for (int i = 0; i < image.getNumBands(); i++) {
                    output.getBand(i).set(image.width - x - 1, y, image.getBand(i).get(x, y));
                }
            }
        }
    }
}
