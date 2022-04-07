package pdl.imageprocessing;

import boofcv.struct.image.GrayU8;
import boofcv.struct.image.Planar;

public class Color {
    public static void color(Planar<GrayU8> image, Planar<GrayU8> output, int teinte){
        float[] hsv = new float[3];
        int [] rgb = new int[3];
		for (int y = 0; y < image.height; ++y) {
			for (int x = 0; x < image.width; ++x) {
                int red = image.getBand(0).get(x,y);
                int green = image.getBand(1).get(x,y);
                int blue = image.getBand(2).get(x,y);
                rgbToHsv(gl0, gl1, gl2, hsv);
                float ret0 = hsv[0]+teinte;
                float ret1 = hsv[1];
                float ret2 = hsv[2];
                hsvToRgb(ret0, ret1, ret2, rgb);
                red = rgb[0];
                green = rgb[1];
                blue = rgb[2];
                if(red > 255) red = 255;
                if(green > 255) green = 255;
                if(blue > 255) blue = 255;
                if(red<0) red = 0;
                if(green<0) green = 0;
                if(blue<0) blue = 0;
                output.getBand(0).set(x,y, red);
                output.getBand(1).set(x,y, green);
                output.getBand(2).set(x,y, blue);
			}
		}
	}
}
