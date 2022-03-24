package pdl.imageprocessing;

import boofcv.struct.image.GrayU8;
import boofcv.struct.image.Planar;

public class Outline {
    public static void outline(Planar<GrayU8> image, Planar<GrayU8> output){
        int [][]sobel_x = {{-1,0,1},
                          {-2,0,2},
                          {-1,0,1}};
    
        int [][] sobel_y = {{-1,-2,-1},
                            {0,0,0},
                             {1,2,1}};
    
        for(int y=sobel_x.length/2; y<image.height-sobel_x.length/2; ++y){
          for( int x=sobel_x.length/2; x<image.width-sobel_x.length/2; ++x){
            int pixel_x0 = 0;
            int pixel_x1 = 0;
            int pixel_x2 = 0;

            int pixel_y0 = 0;
            int pixel_y1 = 0;
            int pixel_y2 = 0;

            int val0 = 0;
            int val1 = 0;
            int val2 = 0;

            for(int u = -sobel_x.length/2; u <= sobel_x.length/2; u++){
              for(int v = -sobel_x.length/2; v <= sobel_x.length/2; v++){

                pixel_x0 = pixel_x0 + (image.getBand(0).get(x + u, y + v) * sobel_x[u + (sobel_x.length/2)][v + (sobel_x.length/2)]);
                pixel_x1 = pixel_x1 + (image.getBand(1).get(x + u, y + v) * sobel_x[u + (sobel_x.length/2)][v + (sobel_x.length/2)]);
                pixel_x2 = pixel_x2 + (image.getBand(2).get(x + u, y + v) * sobel_x[u + (sobel_x.length/2)][v + (sobel_x.length/2)]);

                pixel_y0 = pixel_y0 + (image.getBand(0).get(x + u, y + v) * sobel_y[u + (sobel_y.length/2)][v + (sobel_y.length/2)]);
                pixel_y1 = pixel_y1 + (image.getBand(1).get(x + u, y + v) * sobel_y[u + (sobel_y.length/2)][v + (sobel_y.length/2)]);
                pixel_y2 = pixel_y2 + (image.getBand(2).get(x + u, y + v) * sobel_y[u + (sobel_y.length/2)][v + (sobel_y.length/2)]);

                
                val0 = (int) Math.sqrt((pixel_x0 * pixel_x0) + (pixel_y0 * pixel_y0));
                val1 = (int) Math.sqrt((pixel_x1 * pixel_x1) + (pixel_y1 * pixel_y1));
                val2 = (int) Math.sqrt((pixel_x2 * pixel_x2) + (pixel_y2 * pixel_y2));

                if(val0 < 0) 
                    val0 = 0;
                if(val0 > 255) 
                    val0 = 255;
                if(val1 < 0) 
                    val1 = 0;
                if(val1 > 255) 
                    val1 = 255;
                if(val2 < 0) 
                    val2 = 0;
                if(val2 > 255) 
                    val2 = 255;
              }
            }
            output.getBand(0).set(x, y, val0);
            output.getBand(1).set(x, y, val1);
            output.getBand(2).set(x, y, val2);

          }
        }
    }
}
