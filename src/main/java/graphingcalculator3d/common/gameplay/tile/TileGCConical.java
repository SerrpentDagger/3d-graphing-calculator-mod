package graphingcalculator3d.common.gameplay.tile;

import graphingcalculator3d.common.util.math.positionlib.Alt3d;
import graphingcalculator3d.common.util.nbthandler.GCNBT;
import net.minecraft.util.math.Vec3d;

public class TileGCConical extends TileGCBase
{
	public TileGCConical()
	{
		domainA = GCNBT.GC_DOMAIN_A.defaultVal();
		domainB = GCNBT.GC_DOMAIN_B.defaultVal();
	}
	
	@Override
	public void genMesh()
	{
		varA = "mu";
		varB = "nu";
		super.genMesh();

		if (isErrored())
			return;
		
		Alt3d temp = new Alt3d();
		
		double r;
		double mu;
		double nu;
		for (int j = 0; j < vertexArray.length; j++)
		{
			for (int k = 0; k < vertexArray[j].length; k++)
			{
				r = vertexArray[j][k].y;
				mu = vertexArray[j][k].x;
				nu = vertexArray[j][k].z;
				
				double x = (r * mu * nu) / (b * c);
				double y = (r / c) * Math.sqrt(   ( (sqr(mu)-sqr(c))*(sqr(nu)-sqr(c)) )  /  (sqr(c) - sqr(b))   );
				double z = (r / b) * Math.sqrt(   ( (sqr(mu)-sqr(b))*(sqr(nu)-sqr(b)) )  /  (sqr(b) - sqr(c))   );
				temp.setTo(x, y, z, Alt3d.CARTESIAN);
				scaleTrans(temp);
				vertexArray[j][k] = new Vec3d(temp.getX(), temp.getY(), temp.getZ());
			
				if (j == 0 && k == 0)
				{
					highestF = vertexArray[j][k].y;
					lowestF = vertexArray[j][k].y;
				}
				
				if (vertexArray[j][k].y > highestF)
					highestF = vertexArray[j][k].y;
				if (vertexArray[j][k].y < lowestF)
					lowestF = vertexArray[j][k].y;
			}
		}
		
		postMesh();
	}
}
