package pdl.imageprocessing;
import javax.print.attribute.standard.MediaSize.Other;
import boofcv.alg.color.ColorHsv;
import boofcv.alg.filter.blur.GBlurImageOps;
import boofcv.alg.filter.convolve.GConvolveImageOps;
import boofcv.io.image.ConvertBufferedImage;
import boofcv.io.image.UtilImageIO;
import boofcv.struct.convolve.Kernel1D_S32;
import boofcv.struct.convolve.Kernel2D_S32;
import boofcv.struct.image.GrayU8;
import boofcv.struct.image.Planar;
import java.awt.image.BufferedImage;

public class Color {
    public static void color(Planar<GrayU8> image, Planar<GrayU8> output, int teinte){
        float[] hsv = new float[3];
        int [] rgb = new int[3];
        Rgbhsv my_fonc = new Rgbhsv();
        ColorHsv color = new ColorHsv();
		for (int y = 0; y < image.height; ++y) {
			for (int x = 0; x < image.width; ++x) {
                int gl0 = image.getBand(0).get(x,y);
                int gl1 = image.getBand(1).get(x,y);
                int gl2 = image.getBand(2).get(x,y);
                color.rgbToHsv(gl0, gl1, gl2, hsv);
                float ret0 = hsv[0]+teinte;
                float ret1 = hsv[1];
                float ret2 = hsv[2];
                my_fonc.hsvToRgb(ret0, ret1, ret2, rgb);
                gl0 = rgb[0];
                gl1 = rgb[1];
                gl2 = rgb[2];
                if(gl0 > 255) gl0 = 255;
                if(gl1 > 255) gl1 = 255;
                if(gl2 > 255) gl2 = 255;
                if(gl0<0) gl0 = 0;
                if(gl1<0) gl1 = 0;
                if(gl2<0) gl2 = 0;
                output.getBand(0).set(x,y, gl0);
                output.getBand(1).set(x,y, gl1);
                output.getBand(2).set(x,y, gl2);
			}
		}
	}
}
