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

public class Luminosity {

    public static luminosity(Planar<GrayU8> image, Planar<GrayU8> output, int delta){
        for (int y = 0; y < image.height; ++y) {
			for (int x = 0; x < image.width; ++x) {
                int gr0 = image.getBand(0).get(x, y) + delta ;
                int gr1 = image.getBand(1).get(x, y) + delta ;
                int gr2 = image.getBand(2).get(x, y) + delta ;
                if (gr0 > 255)
                    gr0 = 255 ;
                if (gr1 > 255)  
                    gr1 = 255 ;
                if (gr2 > 255) 
                    gr2 = 255 ;
                if (gr0 < 0) 
                    gr0 = 0 ;
                if (gr1 < 0) 
                    gr1 = 0 ;
                if (gr2 < 0) 
                    gr2 = 0 ;
                output.getBand(0).set(x, y, gr0) ;
                output.getBand(1).set(x, y, gr1);
                output.getBand(2).set(x, y, gr2);
            }
        }

    }
    
}
