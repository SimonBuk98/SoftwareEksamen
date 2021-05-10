package dtu.calculator;

public class Tidsforbrug {
	
	public Bruger bruger;
	public double tid;

	//s191252
	public void satTid(double tid) {
		this.tid = this.tid + tid;
	}

	public Tidsforbrug(Bruger bruger) {
		this.bruger = bruger;
		tid = 0;
	}

}
