package pdl.imageprocessing;

import boofcv.struct.image.GrayU8;

public class Histogram {

    private int min;
    private int max;
    public GrayU8 input;
    private int [] calH;
    private int [] cumH;

    public Histogram(GrayU8 input){
        this.input = input;
        calH = new int[256];
        for(int ng=0; ng<255; ng++)
			calH[ng]= 0;
        cumH = new int[256];
        for(int ng=0; ng<255; ng++)
            cumH[ng]= 0;
    }

    public int retrieveMax(){
        this.max = this.input.get(0, 0);
        for (int y = 0; y < input.height; ++y) {
			for (int x = 0; x < input.width; ++x) {
				if(this.input.get(x, y) > this.max){
                    this.max = this.input.get(x, y);
                }
			}
		}
        return this.max;
    }

    public int retrieveMin(){
        this.min = this.input.get(0, 0);
        for (int y = 0; y < input.height; ++y) {
			for (int x = 0; x < input.width; ++x) {
				if(this.input.get(x, y) < this.min){
                    this.min = this.input.get(x, y);
                }
			}
		}
        return this.min;
    }

    public int [] calculHisto(){
        for (int y = 0; y < input.height; ++y) {
		    for (int x = 0; x < input.width; ++x) {
                this.calH[this.input.get(x, y)] ++;
            }
        }
        return this.calH;
    }

    public int [] histoCum(){
        this.calH = calculHisto();
        int tmp = 0;
        for(int i=0; i<cumH.length; i++){
            this.cumH[i] = tmp + this.calH[i];
            tmp = cumH[i];
        }
        return this.cumH;
    }

    public boolean compare(int [] imgComp){
        boolean comp= true; 
        for (int i=0; i<calH.length; i++){
            if(calH[i]!=imgComp[i])
                comp=false;
        }
        return comp;
    }
    
}