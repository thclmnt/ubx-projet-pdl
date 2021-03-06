package pdl.imageprocessing;

import boofcv.struct.image.GrayU8;
import boofcv.struct.image.Planar;

public class Color {
    public static void color(Planar<GrayU8> image, Planar<GrayU8> output, int teinte){
        float[] hsv = new float[3];
        int [] rgb = new int[3];
		for (int y = 0; y < image.height; ++y) {
			for (int x = 0; x < image.width; ++x) {
                int gl0 = image.getBand(0).get(x,y);
                int gl1 = image.getBand(1).get(x,y);
                int gl2 = image.getBand(2).get(x,y);
                Rgbhsv.rgbToHsv(gl0, gl1, gl2, hsv);
                float ret0 = teinte;
                float ret1 = hsv[1];
                float ret2 = hsv[2];
                Rgbhsv.hsvToRgb(ret0, ret1, ret2, rgb);
                gl0 = rgb[0];
                gl1 = rgb[1];
                gl2 = rgb[2];
                output.getBand(0).set(x,y, gl0);
                output.getBand(1).set(x,y, gl1);
                output.getBand(2).set(x,y, gl2);
			}
		}
	}
}
