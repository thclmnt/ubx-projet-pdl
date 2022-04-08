package pdl.imageprocessing;

import boofcv.struct.image.GrayU8;


public class HistogramEqualization {
    public static void histogramEqualization(GrayU8 input){
		Histogram hist= new Histogram(input);
		int [] cumH = hist.histoCum();
		for (int y = 0; y < input.height; ++y) {
			for (int x = 0; x < input.width; ++x) {
				int gl = input.get(x, y);
				input.set(x, y, (cumH[gl]*255)/cumH[255]);
			}
		}
	}
    
}
