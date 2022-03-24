package pdl.imageprocessing;

import boofcv.struct.image.GrayU8;
import boofcv.struct.image.Planar;

public class Blur {
    public static void moyen(Planar<GrayU8> image, Planar<GrayU8> output, int size) {
        for (int y = size/2; y < image.height-size/2; ++y) {
            for (int x = size/2; x < image.width - size/2; ++x) {
                int r0=0;
                int r1=0;
                int r2=0;
                for(int u=-size/2; u<=size/2; u++){
                    for(int v=-size/2; v<=size/2; v++){
                    int i0 = image.getBand(0).get(x+u, y+v);
                    int i1 = image.getBand(1).get(x+u, y+v);
                    int i2 = image.getBand(2).get(x+u, y+v);
                    int k =1;
                    r0= r0+(i0*k);
                    r1= r1+(i1*k);
                    r2= r2+(i2*k);
                    }
                }
                r0 = r0/(size*size);
                r1 = r1/(size*size);
                r2 = r2/(size*size);
                output.getBand(0).set(x,y,r0);
                output.getBand(1).set(x,y,r1);
                output.getBand(2).set(x,y,r2);
            }
        }
    }

    public static void gaussien(Planar<GrayU8> image, Planar<GrayU8> output) {

        int [][] kernel =   {{1,2,3,2,1},
                            {2,6,8,6,2},
                            {3,8,10,8,3},
                            {2,6,8,6,2},
                            {1,2,3,2,1}};
        int s=0;
        for(int x=0; x<kernel.length; x++){
          for(int y=0; y<kernel[0].length; y++){
            s=s+kernel[x][y];
          }
        }
        for (int y = kernel.length/2; y < image.height-kernel.length/2; ++y) {
            for (int x = kernel[0].length/2; x < image.width - kernel[0].length/2; ++x) {
                int r0=0;
                int r1=0;
                int r2=0;
                for(int u=-kernel.length/2; u<=kernel.length/2; u++){
                    for(int v=-kernel[0].length/2; v<=kernel[0].length/2; v++){
                        int i0 = image.getBand(0).get(x+u, y+v);
                        int i1 = image.getBand(1).get(x+u, y+v);
                        int i2 = image.getBand(2).get(x+u, y+v);
                        int k = kernel[u+(kernel.length/2)][v+(kernel.length/2)];
                        r0= r0+(i0*k);
                        r1= r1+(i1*k);
                        r2= r2+(i2*k);
                    }
                }
                r0 = r0/s;
                r1 = r1/s;
                r2 = r2/s;
                output.getBand(0).set(x,y,r0);
                output.getBand(1).set(x,y,r1);
                output.getBand(2).set(x,y,r2);
            }
        }
    }
    
}
