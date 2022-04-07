package pdl.imageprocessing;

import boofcv.struct.image.GrayU8;
import boofcv.struct.image.Planar;


public class HistogramEqualization {
    public static void histogramEqualization(Planar<GrayU8> image, Planar<GrayU8> output, int delta){
		float[] hsv = new float[3];
        int [] rgb = new int[3];
		//int gl = input.get(x, y);
		//input.set(x, y, (cumH[gl]*255)/cumH[255]);
		int [] hist= new int[256];
		int [] cumH = new int[256];
		if (delta = 2){
			for (int y = 0; y < input.height; ++y) {
				for (int x = 0; x < input.width; ++x) {
					int red = image.getBand(0).get(x,y);
					int green = image.getBand(1).get(x,y);
					int blue = image.getBand(2).get(x,y);
					rgbToHsv(red, green, blue, hsv);
					int a=(int) hsv[2];
                    hist[a]+=1;

				
				}
			}
			cumH[0]=hist[0];
			for(int i=1;i<256;i++){
					cumH[i]=cumH[i-1]+cumH[i];
			}
			for (int y = 0; y < input.height; ++y) {
				for (int x = 0; x < input.width; ++x) {
					int red = image.getBand(0).get(x,y);
					int green = image.getBand(1).get(x,y);
					int blue = image.getBand(2).get(x,y);
					rgbToHsv(red, green, blue, hsv);
					hsv[2]=(cumH[(int) hsv[2]]*255)/(input.height*input.width);
                    hsvToRgb(hsv[0],hsv[1],hsv[2],rgb);

                    output.getBand(0).set(x,y,rgb[0]);
                    output.getBand(1).set(x,y,rgb[1]);
                    output.getBand(2).set(x,y,rgb[2]);
				}
			}
		}

		if (delta = 1){
			for (int y = 0; y < input.height; ++y) {
				for (int x = 0; x < input.width; ++x) {
						int red = image.getBand(0).get(x,y);
						int green = image.getBand(1).get(x,y);
						int blue = image.getBand(2).get(x,y);
						rgbToHsv(red, green, blue, hsv);
						int a=(int) hsv[1];
						histo[a]+=1;
				}
			}
			cumH[0]=hist[0];
			for(int i=1;i<256;i++){
					cumH[i]=cumH[i-1]+cumH[i];
			}
			for (int y = 0; y < input.height; ++y) {
				for (int x = 0; x < input.width; ++x) {
					int red = image.getBand(0).get(x,y);
					int green = image.getBand(1).get(x,y);
					int blue = image.getBand(2).get(x,y);
					rgbToHsv(red, green, blue, hsv);
					hsv[1]=(cumH[(int) hsv[1]]*255)/(input.height*input.width);
					hsvToRgb(hsv[0],hsv[1],hsv[2],rgb);
			
					output.getBand(0).set(x,y,rgb[0]);
					output.getBand(1).set(x,y,rgb[1]);
					output.getBand(2).set(x,y,rgb[2]);

				}
			}
		}
								



	}
				
}
	
    

