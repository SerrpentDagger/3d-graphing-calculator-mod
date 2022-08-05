package graphingcalculator3d.common.gameplay.tile;

import graphingcalculator3d.common.util.math.positionlib.Alt3d;
import graphingcalculator3d.common.util.nbthandler.GCNBT;
import net.minecraft.util.math.Vec3d;

public class TileGCParabolicCylindricalSigma extends TileGCBase
{
	public TileGCParabolicCylindricalSigma()
	{
		domainA = GCNBT.copy(GCNBT.GC_DOM_CIAN);
		domainB = GCNBT.copy(GCNBT.GC_DOM_CIAN);
	}
	
	@Override
	public void genMesh()
	{
		varA = "tau";
		varB = "h";
		super.genMesh();

		if (isErrored())
			return;
		
		Alt3d temp = new Alt3d();
		
		for (int j = 0; j < vertexArray.length; j++)
		{
			for (int k = 0; k < vertexArray[j].length; k++)
			{
				double x = vertexArray[j][k].y * vertexArray[j][k].x;
				double y = vertexArray[j][k].z;
				double z = 0.5 * (sqr(vertexArray[j][k].x) - sqr(vertexArray[j][k].y));
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
