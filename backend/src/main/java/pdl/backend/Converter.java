package pdl.backend;

import java.io.IOException;
import java.io.InputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import javax.imageio.ImageIO;

import java.awt.image.BufferedImage;

import boofcv.io.image.ConvertBufferedImage;
import boofcv.struct.image.GrayU8;
import boofcv.struct.image.Planar;

public class Converter {
    public static Planar<GrayU8> InputStreamToPlanar(InputStream input) {
        try {
            BufferedImage buffer = ImageIO.read(input);
            Planar<GrayU8> planar = ConvertBufferedImage.convertFromPlanar(buffer, null, true, GrayU8.class);
            return planar;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static InputStream PlanarToInputStream(Planar<GrayU8> input) {
        try {
            BufferedImage buffer = ConvertBufferedImage.convertTo_U8(input, null, true);
            ByteArrayOutputStream bytearray = new ByteArrayOutputStream();
            ImageIO.write(buffer, "jpeg", bytearray);
            InputStream output = new ByteArrayInputStream(bytearray.toByteArray());
            return output;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
