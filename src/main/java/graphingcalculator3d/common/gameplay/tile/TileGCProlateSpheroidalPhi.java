package graphingcalculator3d.common.gameplay.tile;

import graphingcalculator3d.common.util.math.positionlib.Alt3d;
import graphingcalculator3d.common.util.nbthandler.GCNBT;
import net.minecraft.util.math.Vec3d;

public class TileGCProlateSpheroidalPhi extends TileGCBase
{
	public TileGCProlateSpheroidalPhi()
	{
		domainA = GCNBT.copy(GCNBT.GC_DOM_R);
		domainB = GCNBT.copy(GCNBT.GC_DOM_1_PI_POS);
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
		
		double mu;
		double nu;
		double phi;
		for (int j = 0; j < vertexArray.length; j++)
		{
			for (int k = 0; k < vertexArray[j].length; k++)
			{
				mu = vertexArray[j][k].x;
				nu = vertexArray[j][k].z;
				phi = vertexArray[j][k].y;
				
				double x = a * Math.sinh(mu) * Math.sin(nu) * Math.cos(phi);
				double y = a * Math.cosh(mu) * Math.cos(nu);
				double z = a * Math.sinh(mu) * Math.sin(nu) * Math.sin(phi);
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
