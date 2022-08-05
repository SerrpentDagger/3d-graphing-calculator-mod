package graphingcalculator3d.client.math;

public class Vertex
{
	public double x, y, z;
	public int r, g, b, a;
	public int l1, l2;
	public double u, v;
	
	public Vertex() {}
	public Vertex(double x, double y, double z)
	{
		this.x = x;
		this.y = y;
		this.z = z;
	}
	public Vertex(int r, int g, int b, int a)
	{
		this.r = r;
		this.g = g;
		this.b = b;
		this.a = a;
	}
	public Vertex(int u, int v)
	{
		this.u = u;
		this.v = v;
	}
	/////////
	
	public Vertex setPos(double x, double y, double z)
	{
		this.x = x;
		this.y = y;
		this.z = z;
		return this;
	}
	
	public Vertex setColor(int r, int g, int b, int a)
	{
		this.r = r;
		this.g = g;
		this.b = b;
		this.a = a;
		return this;
	}
	
	public Vertex setLightmap(int l1, int l2)
	{
		this.l1 = l1;
		this.l2 = l2;
		return this;
	}
	
	public Vertex setTexUV(double u2, double v2)
	{
		this.u = u2;
		this.v = v2;
		return this;
	}
	
	///////
	
	public static Vertex add(Vertex a, double x, double y, double z)
	{
		return a.setPos(a.x + x, a.y + y, a.z + z);
	}
}
