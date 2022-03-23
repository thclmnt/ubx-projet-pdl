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
import Histogram.java;


public class HistogramEqualization {
    public static void histogramEqualization(GrayU8 input){
		Histograme hist= new Histograme(input);
		int [] cumH = hist.histoCum();
		for (int y = 0; y < input.height; ++y) {
			for (int x = 0; x < input.width; ++x) {
				int gl = input.get(x, y);
				input.set(x, y, (cumH[gl]*255)/cumH[255]);
			}
		}
	}
    
}
