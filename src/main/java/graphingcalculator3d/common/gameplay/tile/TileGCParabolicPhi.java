package graphingcalculator3d.common.gameplay.tile;

import graphingcalculator3d.common.util.math.positionlib.Alt3d;
import graphingcalculator3d.common.util.nbthandler.GCNBT;
import net.minecraft.util.math.Vec3d;

public class TileGCParabolicPhi extends TileGCBase
{
	public TileGCParabolicPhi()
	{
		domainA = GCNBT.copy(GCNBT.GC_DOM_CIAN);
		domainB = GCNBT.copy(GCNBT.GC_DOM_CIAN);
	}
	
	@Override
	public void genMesh()
	{
		varA = "sigma";
		varB = "tau";
		super.genMesh();

		if (isErrored())
			return;
		
		Alt3d temp = new Alt3d();
		
		double sigma;
		double tau;
		double phi;
		for (int j = 0; j < vertexArray.length; j++)
		{
			for (int k = 0; k < vertexArray[j].length; k++)
			{
				sigma = vertexArray[j][k].x;
				tau = vertexArray[j][k].z;
				phi = vertexArray[j][k].y;
				
				double x = sigma * tau * Math.cos(phi);
				double y = 0.5 * (sqr(tau) - sqr(sigma));
				double z = sigma * tau * Math.sin(phi);
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
