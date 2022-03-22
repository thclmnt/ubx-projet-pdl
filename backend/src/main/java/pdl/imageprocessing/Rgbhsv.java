package imageprocessing;

public class Rgbhsv {
    
    static void rgbToHsv(int r, int g, int b, float[] hsv){
        float h, s, v;
        int min =Math.min(Math.min(r, g), b);
        int max = Math.max(Math.max(r, g), b);

        if(max==min){
            h=0; 
            hsv[0]=h;
        }
        else if(max==r){
            h=(60*((g-b)/(max-min))+360)%360;
            hsv[0]=h;
        }
        else if(max==g){
            h=(60*((b-r)/(max-min))+120);
            hsv[0]=h;
        }
        else if(max==b){
            h=(60*((r-g)/(max-min))+240);
            hsv[0]=h;
        }
    
        if(max==0) s=0;
        else s=1-(min/max);
    
        v=max;
        
        hsv[1]=s;
        hsv[2]=v;
      }
    
      static void hsvToRgb(float h, float s, float v, int[] rgb){
        int part = (int) (h/60);
        int hi= part%6;
        float float_f=(h/60)-hi;
        float float_l=v*(1-s);
        float float_m=(v*(1-(float_f*s)));
        float float_n=(v*(1-((1-float_f)*s)));
        int l= (int) float_l;
        int m= (int) float_m;
        int n= (int) float_n;

        if(hi==0){
            rgb[0] = (int) v;
            rgb[1] = n; 
            rgb[2] = l;
        }
        else if(hi==1){
            rgb[0] = m;
            rgb[1] = (int) v; 
            rgb[2] = l;
        }
        else if(hi==2){
            rgb[0] = l;
            rgb[1] = (int) v; 
            rgb[2] = n;
        }
        else if(hi==3){
            rgb[0] = l;
            rgb[1] = m; 
            rgb[2] = (int) v;
        }
        else if(hi==4){
            rgb[0] = n;
            rgb[1] = l; 
            rgb[2] = (int) v;
        }
        else if(hi==5){
            rgb[0] = (int) v;
            rgb[1] = l; 
            rgb[2] = m;
        }
      }
}
