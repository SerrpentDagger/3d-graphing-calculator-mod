package graphingcalculator3d.common.gameplay.tile;

import graphingcalculator3d.common.util.math.positionlib.Alt3d;
import graphingcalculator3d.common.util.nbthandler.GCNBT;
import net.minecraft.util.math.Vec3d;

public class TileGC6SphereW extends TileGCBase
{
	public TileGC6SphereW()
	{
		domainA = GCNBT.copy(GCNBT.GC_DOM_CIAN);
		domainB = GCNBT.copy(GCNBT.GC_DOM_CIAN);
	}
	
	@Override
	public void genMesh()
	{
		varA = "u";
		varB = "v";
		super.genMesh();

		if (isErrored())
			return;
		
		Alt3d temp = new Alt3d();
		
		double u;
		double v;
		double w;
		for (int j = 0; j < vertexArray.length; j++)
		{
			for (int k = 0; k < vertexArray[j].length; k++)
			{
				u = vertexArray[j][k].x;
				v = vertexArray[j][k].z;
				w = vertexArray[j][k].y;
				
				double x = u / (sqr(u) + sqr(v) + sqr(w));
				double y = w / (sqr(u) + sqr(v) + sqr(w));
				double z = v / (sqr(u) + sqr(v) + sqr(w));
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
