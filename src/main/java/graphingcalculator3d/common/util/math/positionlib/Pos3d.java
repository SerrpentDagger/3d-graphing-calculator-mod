package graphingcalculator3d.common.util.math.positionlib;

/**
 * <b>Please note: the reliability of this class is currently questionable.
 * I know for a fact that the {@link Pos3d#mirrorAcrossPlane(Pos3d, Pos3d)} methods are faulty in this class.
 * Alt3d and Pos2d are much more reliable, and though there's a good chance that something
 * is wrong with one or both of them, I haven't found it yet. If you do, please post it as an issue on the GitHub project for this library, if no one else has done
 * so. (link below)</b>
 * 
 * <p><b>The Pos3d class is different from the Alt3d in that the Y and Z axis are swapped.
 * In this system, Z is the vertical axis, and Y is the horizontal axis marking the 0-angle direction.</b>
 * 
 * <p>An instance of this class stores a 3D position, involving 6 doubles, an int, and an uninitialized Pos3d (null).
 * 
 * <p>With a Pos3d object, you can easily perform a wide variety of complex transformations on the 3D position stored within it, all of which function
 * properly on any coordinate system type supported by Pos3d.
 * 
 * <p>All transformation methods are chainable, meaning they return either this, or a new Pos3d, with updated values,
 * depending on whether you use a clean method or not.
 * 
 * <p>By default, transformation methods <b>do</b> alter the values of the Pos3d you call them on (they will <i>never</i> alter the values of parameters).
 * This is very helpful in various situations, and means less object-creation in general.
 * If you do need a new Pos3d, however, there is a "clean" version of every transformation method that
 * will return a new Pos3d with the values that would otherwise have been assigned to this. The new Pos3d will have the checkpoint of this as well.
 * 
 * <p>Checkpoints can be set in the middle of a method chain by calling {@link Pos3d#setCheckpoint()} or {@link Pos3d#setCheckpoint(Pos3d)}
 * on the Pos3d object. You can revert to a set checkpoint by calling {@link Pos3d#revert()} on the Pos3d object. Reverting does not alter the checkpoint.
 * 
 * <p>Methods are well optimized to not undergo unnecessary conversions, and to generally be as efficient as possible. There may be some minor infractions to this
 * statement, but as I update the library I will optimize where needed. If you are concerned with performance, then I would suggest performing all of your
 * transformations in groups corresponding to the coordinate system required by those transformations. This would remove unnecessary conversions.
 * 
 * <p><i>If you find a bug or want a feature that doesn't currently exist, open an issue on the GitHub project for this library, at <a
 * href="https://github.com/SerrpentDagger/positionlib/issues">this site</a>.</i>
 * 
 * @author SerpentDagger (M. H.)
 * 
 * <p>--------------------------------------------------------------------
 * 
 * <p>Copyright ? 2019 Merrick Harmon
 * 
 * <p>This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * <p>This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * <p>You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <a
 * href="https://www.gnu.org/licenses/">this website</a>.
 *
 */
public class Pos3d
{
	public static final int CARTESIAN = 0, SPHERICAL = 1, CYLINDRICAL = 2;
	public static final int QNNN = 0, QPNN = 1, QNPN = 2, QNNP = 3, QPPN = 4, QPNP = 5, QNPP = 6, QPPP = 7;
	
	private double x = 0, y = 0, z = 0, ang1 = 0, ang2 = 0, mag = 0;
	public int system = -1;
	public Pos3d checkpoint;
		
	
	/** 
	 * New Pos3d with cartesian values x, y, z.
	 * @param x
	 * @param y
	 * @param z 
	 */
	public Pos3d(double x, double y, double z)
	{
		this.x = x;
		this.y = y;
		this.z = z;
		system = CARTESIAN;
	}
	
	/**
	 * New Pos3d with values for specified system.
	 * <p>If system is...
	 * <p>Cartesian, (a, b, c) -> (x, y, z)
	 * <p>Spherical, (a, b, c) -> (spherical magnitude, rotation around z-axis, rotation from horizontal)
	 * <p>Cylindrical, (a, b, c) -> (cylindrical magnitude, rotation around z-axis, z)
	 * @param a
	 * @param b
	 * @param c
	 * @param system
	 */
	public Pos3d(double a, double b, double c, int system)
	{
		if (system == CARTESIAN)
		{
			this.x = a;
			this.y = b;
			this.z = c;
		}
		else if (system == SPHERICAL)
		{
			this.mag = a;
			this.ang1 = b;
			this.ang2 = c;
		}
		else if (system == CYLINDRICAL)
		{
			this.mag = a;
			this.ang1 = b;
			this.z = c;
		}
		this.system = system;
	}
	
	public Pos3d()
	{
	}
	
	/**
	 * Creates a clone of toClone, including the checkpoint if !null.
	 * @param toClone
	 */
	public Pos3d(Pos3d toClone)
	{
		if (toClone == null) { return; }
		this.x = toClone.x;
		this.y = toClone.y;
		this.z = toClone.z;
		this.ang1 = toClone.ang1;
		this.ang2 = toClone.ang2;
		this.mag = toClone.mag;
		this.system = toClone.system;
		if (toClone.checkpoint != null) { checkpoint = new Pos3d().setTo(toClone.checkpoint); }
		
	}
	
	/**
	 * Convert to Cartesian coordinates.
	 * @return this with updated values.
	 */
	public Pos3d toCartesian()
	{
		if (system == SPHERICAL)
		{
			this.x = mag * Math.sin(ang1) * Math.cos(ang2);
			this.z = mag * Math.sin(ang2);
			this.y = mag * Math.cos(ang1) * Math.cos(ang2);
		}
		else if (system == CYLINDRICAL)
		{
			this.x = mag * Math.sin(ang1);
			this.y = mag * Math.cos(ang1);
		}
		system = CARTESIAN;
		return this;
	}
	
	/**
	 * Convert to Cartesian coordinates (clean version).
	 * @return new Pos3d with updated values.
	 */
	public Pos3d toCartesianClean()
	{
		return new Pos3d(this).toCartesian();
	}
	
	/**
	 * Convert to Spherical coordinates.
	 * @return this with updated values.
	 */
	public Pos3d toSpherical()
	{
		if (system == CARTESIAN)
		{
			mag = Math.sqrt((x*x) + (y*y) + (z*z));
			ang1 = Math.atan2(x, y);
			ang2 = Math.atan(z / Math.sqrt((x*x) + (y*y)));
		}
		else if (system == CYLINDRICAL)
		{
			mag = Math.sqrt((mag*mag) + (z*z));
			ang2 = Math.atan2(z, mag);
			
		}
		system = SPHERICAL;
		return this;
	}
	
	/**
	 * Convert to Spherical coordinates (clean version).
	 * @return new Pos3d with updated values.
	 */
	public Pos3d toSphericalClean()
	{
		return new Pos3d(this).toSpherical();
	}
	
	/**
	 * Convert to Cylindrical coordinates.
	 * @return this with updated values.
	 */
	public Pos3d toCylindrical()
	{
		if (system == CARTESIAN)
		{
			mag = Math.sqrt((x*x) + (y*y));
			ang1 = Math.atan2(x, y);
		}
		else if (system == SPHERICAL)
		{
			z = mag * Math.sin(ang2);
			mag = mag * Math.cos(ang2);
		}
		system = CYLINDRICAL;
		return this;
	}
	
	/**
	 * Convert to Cylindrical coordinates.
	 * @return new Pos3d with updated values.
	 */
	public Pos3d toCylindricalClean()
	{
		return new Pos3d(this).toCylindrical();
	}
	
	/**
	 * Converts to Cartesian and adds toAdd to this.
	 * @param toAdd Pos3d to add to this.
	 * @return Cartesian addition of toAdd onto this.
	 */
	public Pos3d add(Pos3d toAdd)
	{		
		toCartesian();
		toAdd.toCartesian();
		x += toAdd.x;
		y += toAdd.y;
		z += toAdd.z;
		
		return this;
	}
	
	/**
	 * Converts to Cartesian and adds toAdd to this (clean version).
	 * @param toAdd Pos3d to add to this.
	 * @return new Pos3d containing Cartesian addition of toAdd onto this.
	 */
	public Pos3d addClean(Pos3d toAdd)
	{
		return new Pos3d(this).add(toAdd);
	}
	
	/**
	 * Converts to Cartesian and subtracts toSub from this.
	 * @param toSub Pos3d to subtract from this.
	 * @return Cartesian subtraction of toSub from this.
	 */
	public Pos3d sub(Pos3d toSub)
	{
		toSub.toCartesian();
		toCartesian();
		x -= toSub.x;
		y -= toSub.y;
		z -= toSub.z;
		
		return this;
	}
	
	/**
	 * Converts to Cartesian and subtracts toSub from this (clean version).
	 * @param toSub Pos3d to subtract from this.
	 * @return new Pos3d containing Cartesian subtraction of toSub from this.
	 */
	public Pos3d subClean(Pos3d toSub)
	{
		return new Pos3d(this).sub(toSub);
	}
	
	/**
	 * Calls the add method, using a new Pos3d built from the parameters.
	 * <p>If system is...
	 * <p>Cartesian, (a, b, c) -> (x, y, z)
	 * <p>Spherical, (a, b, c) -> (spherical magnitude, rotation around z-axis, rotation from horizontal)
	 * <p>Cylindrical, (a, b, c) -> (cylindrical magnitude, rotation around z-axis, z)
	 * @param a
	 * @param b
	 * @param c
	 * @param system
	 * @return this with updated values.
	 */
	public Pos3d add(double a, double b, double c, int system)
	{
		Pos3d temp = new Pos3d(a, b, c, system);
		
		this.add(temp);
		return this;
	}
	
	/**
	 * Calls the add method, using a new Pos3d built from the parameters (clean version).
	 * <p>If system is...
	 * <p>Cartesian, (a, b, c) -> (x, y, z)
	 * <p>Spherical, (a, b, c) -> (spherical magnitude, rotation around z-axis, rotation from horizontal)
	 * <p>Cylindrical, (a, b, c) -> (cylindrical magnitude, rotation around z-axis, z)
	 * @param a
	 * @param b
	 * @param c
	 * @param system
	 * @return new Pos3d with updated values.
	 */
	public Pos3d addClean(double a, double b, double c, int system)
	{
		return new Pos3d(this).add(a, b, c, system);
	}
	
	/**
	 * Calls the sub method, using a new Pos3d built from the parameters.
	 * <p>If system is...
	 * <p>Cartesian, (a, b, c) -> (x, y, z)
	 * <p>Spherical, (a, b, c) -> (spherical magnitude, rotation around z-axis, rotation from horizontal)
	 * <p>Cylindrical, (a, b, c) -> (cylindrical magnitude, rotation around z-axis, z)
	 * @param a
	 * @param b
	 * @param c
	 * @param system
	 * @return this with updated values.
	 */
	public Pos3d sub(double a, double b, double c, int system)
	{
		Pos3d temp = new Pos3d(a, b, c, system);
		
		this.sub(temp);
		return this;
	}
	
	/**
	 * Calls the sub method, using a new Pos3d built from the parameters (clean version).
	 * <p>If system is...
	 * <p>Cartesian, (a, b, c) -> (x, y, z)
	 * <p>Spherical, (a, b, c) -> (spherical magnitude, rotation around z-axis, rotation from horizontal)
	 * <p>Cylindrical, (a, b, c) -> (cylindrical magnitude, rotation around z-axis, z)
	 * @param a
	 * @param b
	 * @param c
	 * @param system
	 * @return new Pos3d with updated values.
	 */
	public Pos3d subClean(double a, double b, double c, int system)
	{
		return new Pos3d(this).sub(a, b, c, system);
	}
	
	/**
	 * Converts to Cartesian and multiplies this by toMult (x by x, y by y, z by z).
	 * @param toMult Pos3d to multiply this by.
	 * @return Cartesian multiplication of this by toMult.
	 */
	public Pos3d mult(Pos3d toMult)
	{
		toMult.toCartesian();
		toCartesian();
		x *= toMult.x;
		y *= toMult.y;
		z *= toMult.z;
		
		return this;
	}
	
	/**
	 * Converts to Cartesian and multiplies this by toMult (x by x, y by y, z by z) (clean version).
	 * @param toMult Pos3d to multiply this by.
	 * @return new Pos3d containing Cartesian multiplication of this by toMult.
	 */
	public Pos3d multClean(Pos3d toMult)
	{
		return new Pos3d(this).mult(toMult);
	}
	
	/**
	 * Scales this by scale.
	 * @param scale Scale to multiply this by.
	 * @return Scaling of this by scale.
	 */
	public Pos3d scale(double scale)
	{
		if (system == CARTESIAN)
		{
			x *= scale;
			y *= scale;
			z *= scale;
		}
		else if (system == SPHERICAL)
		{
			mag *= scale;
		}
		else if (system == CYLINDRICAL)
		{
			mag *= scale;
			z *= scale;
		}
		return this;
	}
	
	/**
	 * Scales this by scale (clean version).
	 * @param scale Scale to multiply this by.
	 * @return new Pos3d containing scaling of this by scale.
	 */
	public Pos3d scaleClean(double scale)
	{
		return new Pos3d(this).scale(scale);
	}
	
	/**
	 * Calls the mult method, using a new Pos3d built from the parameters.
	 * <p>If system is...
	 * <p>Cartesian, (a, b, c) -> (x, y, z)
	 * <p>Spherical, (a, b, c) -> (spherical magnitude, rotation around z-axis, rotation from horizontal)
	 * <p>Cylindrical, (a, b, c) -> (cylindrical magnitude, rotation around z-axis, z)
	 * @param a
	 * @param b
	 * @param c
	 * @param system
	 * @return this with updated values.
	 */
	public Pos3d mult(double a, double b, double c, int system)
	{
		Pos3d temp = new Pos3d(a, b, c, system);
		
		this.mult(temp);
		return this;
	}
	
	/**
	 * Calls the mult method, using a new Pos3d built from the parameters (clean version).
	 * <p>If system is...
	 * <p>Cartesian, (a, b, c) -> (x, y, z)
	 * <p>Spherical, (a, b, c) -> (spherical magnitude, rotation around z-axis, rotation from horizontal)
	 * <p>Cylindrical, (a, b, c) -> (cylindrical magnitude, rotation around z-axis, z)
	 * @param a
	 * @param b
	 * @param c
	 * @param system
	 * @return new Pos3d with updated values.
	 */
	public Pos3d multClean(double a, double b, double c, int system)
	{
		return new Pos3d(this).mult(a, b, c, system);
	}
	
	/**
	 * Converts to Cartesian and divides this by toDivi (x by x, y by y, z by z).
	 * @param toDivi Pos3d to divide this by.
	 * @return Cartesian division of this by toDivi.
	 */
	public Pos3d divi(Pos3d toDivi)
	{
		toDivi.toCartesian();
		toCartesian();
		x /= toDivi.x;
		y /= toDivi.y;
		z /= toDivi.z;
		
		return this;
	}
	
	/**
	 * Converts to Cartesian and divides this by toDivi (x by x, y by y, z by z) (clean version).
	 * @param toDivi Pos3d to divide this by.
	 * @return new Pos3d containing Cartesian division of this by toDivi.
	 */
	public Pos3d diviClean(Pos3d toDivi)
	{
		return new Pos3d(this).divi(toDivi);
	}
	
	/**
	 * Calls the divi method, using a new Pos3d built from the parameters.
	 * <p>If system is...
	 * <p>Cartesian, (a, b, c) -> (x, y, z)
	 * <p>Spherical, (a, b, c) -> (spherical magnitude, rotation around z-axis, rotation from horizontal)
	 * <p>Cylindrical, (a, b, c) -> (cylindrical magnitude, rotation around z-axis, z)
	 * @param a
	 * @param b
	 * @param c
	 * @param system
	 * @return this with updated values.
	 */
	public Pos3d divi(double a, double b, double c, int system)
	{
		Pos3d temp = new Pos3d(a, b, c, system);
		
		this.divi(temp);
		return this;
	}
	
	/**
	 * Calls the divi method, using a new Pos3d built from the parameters.
	 * <p>If system is...
	 * <p>Cartesian, (a, b, c) -> (x, y, z)
	 * <p>Spherical, (a, b, c) -> (spherical magnitude, rotation around z-axis, rotation from horizontal)
	 * <p>Cylindrical, (a, b, c) -> (cylindrical magnitude, rotation around z-axis, z)
	 * @param a
	 * @param b
	 * @param c
	 * @param system
	 * @return new Pos3d with updated values.
	 */
	public Pos3d diviClean(double a, double b, double c, int system)
	{
		return new Pos3d(this).divi(a, b, c, system);
	}
	
	/**
	 * Converts to Cartesian and squares the individual (x, y, z) values.
	 * @return Cartesian square of this.
	 */
	public Pos3d sqr()
	{
		toCartesian();
		x *= x;
		y *= y;
		z *= z;
		
		return this;
	}
	
	/**
	 * Converts to Cartesian and squares the individual (x, y, z) values (clean version).
	 * @return new Pos3d containing Cartesian square of this.
	 */
	public Pos3d sqrClean()
	{
		return new Pos3d(this).sqr();
	}
	
	/**
	 * Converts to Cartesian and takes the square root of the individual (x, y, z) values.
	 * @return Cartesian square root of this.
	 */
	public Pos3d sqrt()
	{
		toCartesian();
		x = Math.sqrt(x);
		y = Math.sqrt(y);
		z = Math.sqrt(z);
		
		return this;
	}
	
	/**
	 * Converts to Cartesian and takes the square root of the individual (x, y, z) values (clean version).
	 * @return new Pos3d containing Cartesian square root of this.
	 */
	public Pos3d sqrtClean()
	{
		return new Pos3d(this).sqrt();
	}
	
	/**
	 * Converts to Cartesian and takes the square root of the individual (x, y, z) values.
	 * <p>Always returns positive values.
	 * @return Positive Cartesian square root of this.
	 */
	public Pos3d sqrtPositive()
	{
		toCartesian();
		x = (x < 0) ? Math.sqrt(-x) : Math.sqrt(x);
		y = (y < 0) ? Math.sqrt(-y) : Math.sqrt(y);
		z = (z < 0) ? Math.sqrt(-z) : Math.sqrt(z);
		
		return this;
	}
	
	/**
	 * Converts to Cartesian and takes the square root of the individual (x, y, z) values (clean version).
	 * <p>Always returns positive values.
	 * @return new Pos3d containing positive Cartesian square root of this.
	 */
	public Pos3d sqrtPositiveClean()
	{
		return new Pos3d(this).sqrtPositive();
	}
	
	/**
	 * Converts to Cartesian and squares the individual (x, y, z) values while preserving sign.
	 * @return Cartesian square of this, preserving signs.
	 */
	public Pos3d sqrPreserveSign()
	{
		toCartesian();
		x *= (x < 0) ? -x : x;
		y *= (y < 0) ? -y : y;
		z *= (z < 0) ? -z : z;
		
		return this;
	}
	
	/**
	 * Converts to Cartesian and squares the individual (x, y, z) values while preserving sign (clean version).
	 * @return new Pos3d containing Cartesian square of this, preserving signs.
	 */
	public Pos3d sqrPreserveSignClean()
	{
		return new Pos3d(this).sqrPreserveSign();
	}
	
	/**
	 * Converts to Cartesian and takes the square root of the individual (x, y, z) values while preserving sign.
	 * @return Cartesian square root of this, preserving signs.
	 */
	public Pos3d sqrtPreserveSign()
	{
		toCartesian();
		x = (x < 0) ? -Math.sqrt(-x) : Math.sqrt(x);
		y = (y < 0) ? -Math.sqrt(-y) : Math.sqrt(y);
		z = (z < 0) ? -Math.sqrt(-z) : Math.sqrt(z);
		
		return this;
	}
	
	/**
	 * Converts to Cartesian and takes the square root of the individual (x, y, z) values while preserving sign (clean version).
	 * @return new Pos3d containing Cartesian square root of this, preserving signs.
	 */
	public Pos3d sqrtPreserveSignClean()
	{
		return new Pos3d(this).sqrtPreserveSign();
	}
	
	/**
	 * Set values of this to values of setTo (checkpoint not included).
	 * @param setTo Values to put into this.
	 * @return this with updated values.
	 */
	public Pos3d setTo(Pos3d setTo)
	{
		if (setTo == null) { return null; }
		this.x = setTo.x;
		this.y = setTo.y;
		this.z = setTo.z;
		this.ang1 = setTo.ang1;
		this.ang2 = setTo.ang2;
		this.mag = setTo.mag;
		this.system = setTo.system;
		
		return this;
	}
	
	/**
	 * Set values of this to values given (checkpoint not included).
	 * <p>If system is...
	 * <p>Cartesian, (a, b, c) -> (x, y, z)
	 * <p>Spherical, (a, b, c) -> (spherical magnitude, rotation around z-axis, rotation from horizontal)
	 * <p>Cylindrical, (a, b, c) -> (cylindrical magnitude, rotation around z-axis, z)
	 * @param a
	 * @param b
	 * @param c
	 * @param system
	 * @return this with updated values.
	 */
	public Pos3d setTo(double a, double b, double c, int system)
	{
		if (system == CARTESIAN)
		{
			this.x = a;
			this.y = b;
			this.z = c;
		}
		else if (system == SPHERICAL)
		{
			this.mag = a;
			this.ang1 = b;
			this.ang2 = c;
		}
		else if (system == CYLINDRICAL)
		{
			this.mag = a;
			this.ang1 = b;
			this.y = c;
		}
		this.system = system;
		
		return this;
	}
	
	/**
	 * Set values of this to values of setTo (checkpoint not included) (clean version).
	 * @param setTo Values to put into this.
	 * @return new Pos3d with updated values.
	 */
	public Pos3d setToClean(Pos3d setTo)
	{
		return new Pos3d(this).setTo(setTo);
	}
	
	/**
	 * Set values of this to values given (checkpoint not included) (clean version).
	 * <p>If system is...
	 * <p>Cartesian, (a, b, c) -> (x, y, z)
	 * <p>Spherical, (a, b, c) -> (spherical magnitude, rotation around z-axis, rotation from horizontal)
	 * <p>Cylindrical, (a, b, c) -> (cylindrical magnitude, rotation around z-axis, z)
	 * @param a
	 * @param b
	 * @param c
	 * @param system
	 * @return new Pos3d with updated values.
	 */
	public Pos3d setToClean(double a, double b, double c, int system)
	{
		return new Pos3d(this).setTo(a, b, c, system);
	}
	
	/**
	 * Converts to coordinate system specified.
	 * @param system System to convert to.
	 * @return this with updated values.
	 */
	public Pos3d toSystem(int system)
	{
		if (system == CARTESIAN)
		{
			toCartesian();
		}
		else if (system == SPHERICAL)
		{
			toSpherical();
		}
		else if (system == CYLINDRICAL)
		{
			toCylindrical();
		}
		return this;
	}
	
	/**
	 * Converts to coordinate system specified (clean version).
	 * @param system System to convert to.
	 * @return new Pos3d with updated values.
	 */
	public Pos3d toSystemClean(int system)
	{
		return new Pos3d(this).toSystem(system);
	}
	
	/**
	 * Sets z to 0, ang2 to 0.
	 * @return this with updated values.
	 */
	public Pos3d flatten()
	{
		this.z = 0;
		this.ang2 = 0;
		
		return this;
	}
	
	/**
	 * Sets z to 0, ang2 to 0.
	 * @return new Pos3d with updated values.
	 */
	public Pos3d flattenClean()
	{
		return new Pos3d(this).flatten();
	}
	
	/**
	 * Rotates this around z axis.
	 * 
	 * <p>All axis-based rotations rotate counter-clockwise around the axis, when said axis is viewed from the positive end looking towards the negative end.
	 * Vertical rotation (which is not axis-based) rotates "upwards?" from the positive direction in the horizontal plane, where the positive
	 * direction is defined by the horizontal angle.
	 * 
	 * <p>?Note that the "upwards" direction will not always be up. It will be up when the vertical angle resides in the first or fourth quadrant, and down when
	 * the vertical angle resides in the second or third quadrant, in the manner one would expect from increasing any angle.
	 * 
	 * @param radians Radian value to rotate by.
	 * @return this with updated values.
	 */
	public Pos3d rotateAroundZ(double radians)
	{
		if (system == CARTESIAN)
		{
			toCylindrical();
			this.ang1 -= radians;
		}
		else
		{
			this.ang1 += radians;
		}
		
		return this;
	}
	
	/**
	 * Rotates this around y axis (clean version).
	 * 
	 * <p>All axis-based rotations rotate counter-clockwise around the axis, when said axis is viewed from the positive end looking towards the negative end.
	 * Vertical rotation (which is not axis-based) rotates "upwards?" from the positive direction in the horizontal plane, where the positive
	 * direction is defined by the horizontal angle.
	 * 
	 * <p>?Note that the "upwards" direction will not always be up. It will be up when the vertical angle resides in the first or fourth quadrant, and down when
	 * the vertical angle resides in the second or third quadrant, in the manner one would expect from increasing any angle.
	 * 
	 * @param radians Radian value to rotate by.
	 * @return new Pos3d with updated values.
	 */
	public Pos3d rotateAroundYClean(double radians)
	{
		return new Pos3d(this).rotateAroundY(radians);
	}
	
	/**
	 * Rotates this vertically around the origin.
	 * 
	 * <p>All axis-based rotations rotate counter-clockwise around the axis, when said axis is viewed from the positive end looking towards the negative end.
	 * Vertical rotation (which is not axis-based) rotates "upwards?" from the positive direction in the horizontal plane, where the positive
	 * direction is defined by the horizontal angle.
	 * 
	 * <p>?Note that the "upwards" direction will not always be up. It will be up when the vertical angle resides in the first or fourth quadrant, and down when
	 * the vertical angle resides in the second or third quadrant, in the manner one would expect from increasing any angle.
	 * 
	 * @param radians Radian value to rotate by.
	 * @return this with updated values.
	 */
	public Pos3d rotateVertical(double radians)
	{
		toSpherical();
		this.ang2 += radians;
		
		return this;
	}
	
	/**
	 * Rotates this vertically around the origin (clean version).
	 * 
	 * <p>All axis-based rotations rotate counter-clockwise around the axis, when said axis is viewed from the positive end looking towards the negative end.
	 * Vertical rotation (which is not axis-based) rotates "upwards?" from the positive direction in the horizontal plane, where the positive
	 * direction is defined by the horizontal angle.
	 * 
	 * <p>?Note that the "upwards" direction will not always be up. It will be up when the vertical angle resides in the first or fourth quadrant, and down when
	 * the vertical angle resides in the second or third quadrant, in the manner one would expect from increasing any angle.
	 * 
	 * @param radians Radian value to rotate by.
	 * @return new Pos3d with updated values.
	 */
	public Pos3d rotateVerticalClean(double radians)
	{
		return new Pos3d(this).rotateVertical(radians);
	}
	
	/**
	 * Sets the Spherical magnitude of this.
	 * @param mag new Spherical magnitude.
	 * @return this with specified Spherical magnitude.
	 */
	public Pos3d setMagS(double mag)
	{
		toSpherical();
		this.mag = mag;
		
		return this;
	}
	
	/**
	 * Sets the Spherical magnitude of this (clean version).
	 * @param mag new Spherical magnitude.
	 * @return new Pos3d with specified Spherical magnitude.
	 */
	public Pos3d setMagSClean(double mag)
	{
		return new Pos3d(this).setMagS(mag);
	}
	
	/**
	 * Sets the Cylindrical magnitude of this.
	 * @param mag new Cylindrical magnitude.
	 * @return this with specified Cylindrical magnitude.
	 */
	public Pos3d setMagC(double mag)
	{
		toCylindrical();
		this.mag = mag;
		
		return this;
	}
	
	/**
	 * Sets the Cylindrical magnitude of this (clean version).
	 * @param mag new Cylindrical magnitude.
	 * @return new Pos3d with specified Cylindrical magnitude.
	 */
	public Pos3d setMagCClean(double mag)
	{
		return new Pos3d(this).setMagC(mag);
	}
	
	/**
	 * Converts angles to radians (Note, most transformations REQUIRE angles to be in radians. It is not recommended to ever have angles in degrees).
	 * @return this with updated values.
	 */
	public Pos3d toRad()
	{
		this.ang1 = ang1 * Math.PI / 180;
		this.ang2 = ang2 * Math.PI / 180;
		
		return this;
	}
	
	/**
	 * Sets this's checkpoint to this.
	 * @return this.
	 */
	public Pos3d setCheckpoint()
	{
		this.checkpoint = new Pos3d().setTo(this);
		
		return this;
	}
	
	/**
	 * Sets this's checkpoint to checkpoint.
	 * @param checkpoint Pos3d to become this's checkpoint.
	 * @return this.
	 */
	public Pos3d setCheckpoint(Pos3d checkpoint)
	{
		this.checkpoint = new Pos3d().setTo(checkpoint);
		
		return this;
	}
	
	/**
	 * Sets checkpoint of new Pos3d to checkpoint.
	 * @param checkpoint Pos3d to become new Pos3d's checkpoint
	 * @return new Pos3d with checkpoint checkpoint, and values of this.
	 */
	public Pos3d setCheckpointClean(Pos3d checkpoint)
	{
		return new Pos3d(this).setCheckpoint(checkpoint);
	}
	
	/**
	 * Sets checkpoint of new Pos3d to this.
	 * @return new Pos3d with checkpoint this, and values of this.
	 */
	public Pos3d setCheckpointClean()
	{
		return new Pos3d(this).setCheckpoint();
	}
	
	/**
	 * Reverts this to state saved in checkpoint (does not revert checkpoint).
	 * @return this with values of checkpoint.
	 */
	public Pos3d revert()
	{
		this.setTo(checkpoint);
		
		return this;
	}
	
	/**
	 * Reverts new Pos3d to state saved in checkpoint (checkpoint copied from this to new.)
	 * @return new Pos3d with values of this's checkpoint, and checkpoint of this's checkpoint.
	 */
	public Pos3d revertClean()
	{
		return new Pos3d(this).revert();
	}
	
	/**
	 * @return String representation of this Pos3d. Value order as follows: {x y z mag ang1 ang2 system ("null" if checkpoint == null, else checkpoint.toString())}
	 */
	@Override
	public String toString()
	{
		return "{" + x + " " + y + " " + z + " " + mag + " "
				 + ang1 + " " + ang2 + " " + system + " " + ((checkpoint == null) ? "null" : checkpoint.toString()) + "}";
	}
	
	/**
	 * @return The "programmer friendly" String representing this Pos3d, including names, colons, and commas separating values.
	 */
	public String toStringPF()
	{
		return "{X: " + x + ", Y: " + y + ", Z: " + z + ", mag: " + mag + ","
				+ "ang1: " + ang1 + ", ang2: " + ang2 + ", system: " + system + ", checkpoint: " + ((checkpoint == null) ? "null" : checkpoint.toStringPF()) + "}";
	}
	
	/**
	 * Flips this across the origin.
	 * @return this with updated values.
	 */
	public Pos3d flip()
	{
		return scale(-1);
	}
	
	/**
	 * Flips this across the origin (clean version).
	 * @return new Pos3d with updated values.
	 */
	public Pos3d flipClean()
	{
		return new Pos3d(this).flip();
	}
	
	/**
	 * Flips this across the origin and doubles.
	 * @return this with updated values.
	 */
	public Pos3d flipAndDouble()
	{
		return scaleClean(-2);
	}
	
	/**
	 * Flips this across the origin and doubles (clean version).
	 * @return new Pos3d with updated values.
	 */
	public Pos3d flipAndDoubleClean()
	{
		return new Pos3d(this).flipAndDouble();
	}
	
	/**
	 * Convenient addition of this onto this.
	 * @return this with updated values.
	 */
	public Pos3d doublePos()
	{
		if (system == CARTESIAN)
		{
			x *= 2;
			y *= 2;
			z *= 2;
		}
		else if (system == SPHERICAL)
		{
			mag *= 2;
		}
		else if (system == CYLINDRICAL)
		{
			z *= 2;
			mag *= 2;
		}
		return this;
	}
	
	/**
	 * Convenient addition of this onto this (clean version).
	 * @return new Pos3d with updated values.
	 */
	public Pos3d doublePosClean()
	{
		return new Pos3d(this).doublePos();
	}
	
	/**
	 * @return The Spherical magnitude of this Pos3d.
	 */
	public double getMagS()
	{
		return this.toSpherical().mag;
	}
	
	/**
	 * @return The Cylindrical magnitude of this Pos3d.
	 */
	public double getMagC()
	{
		return this.toCylindrical().mag;
	}
	
	/**
	 * @return The Cartesian x of this Pos3d.
	 */
	public double getX()
	{
		return this.toCartesian().x;
	}

	/**
	 * @return The Cartesian y of this Pos3d.
	 */
	public double getY()
	{
		return this.toCartesian().y;
	}
	
	/**
	 * @return The rotation around the Z-Axis.
	 */
	public double getAng1()
	{
		return this.toCylindrical().ang1;
	}
	
	/**
	 * @return The rotation from horizontal.
	 */
	public double getAng2()
	{
		return this.toSpherical().ang2;
	}
	
	/**
	 * @return The rotation around the X-Axis.
	 */
	public double getAngX()
	{
		return -Math.atan2(this.toCartesian().z, y);
	}
	
	/**
	 * @return The rotation around the Y-Axis.
	 */
	public double getAngY()
	{
		return Math.atan2(this.toCartesian().z, x);
	}
	
	/**
	 * @return The Cartesian Z of this Pos3d.
	 */
	public double getZ()
	{
		return this.toCartesian().z;
	}
	
	/**
	 * Get the quadrant this Pos3d is in. Zeros count as positive.
	 * <p>Converts to Cartesian to get values, then back to original.
	 * @return One of the public static final Q### variables in this class, depending on the quadrant.
	 * <p>(E.G, QNPN -> Quadrant Negative Positive Negative, in Cartesian coordinate order)
	 */
	public int getQuadrantZP()
	{
		toCartesian();
		
		if (x < 0)
		{
			if (y < 0)
			{
				if (z < 0)
				{
					return QNNN;
				}
				else return QNNP;
			}
			else if (z < 0)
			{
				return QNPN;
			}
			else return QNPP;
		}
		else if (y < 0)
		{
			if (z < 0)
			{
				return QPNN;
			}
			else return QPNP;
		}
		else if (z < 0)
		{
			return QPPN;
		}
		else return QPPP;
	}
	
	/**
	 * Get the quadrant this Pos3d is in. Zeros count as negative.
	 * <p>Converts to Cartesian to get values, then back to original.
	 * @return One of the public static final Q### variables in this class, depending on the quadrant.
	 * <p>(E.G, QNPN -> Quadrant Negative Positive Negative, in Cartesian coordinate order)
	 */
	public int getQuadrantZN()
	{
		toCartesian();
		
		if (x <= 0)
		{
			if (y <= 0)
			{
				if (z <= 0)
				{
					return QNNN;
				}
				else return QNNP;
			}
			else if (z <= 0)
			{
				return QNPN;
			}
			else return QNPP;
		}
		else if (y <= 0)
		{
			if (z <= 0)
			{
				return QPNN;
			}
			else return QPNP;
		}
		else if (z <= 0)
		{
			return QPPN;
		}
		else return QPPP;
	}
	
	/**
	 * Rotates this around X-axis.
	 * 
	 * <p>All axis-based rotations rotate counter-clockwise around the axis, when said axis is viewed from the positive end looking towards the negative end.
	 * Vertical rotation (which is not axis-based) rotates "upwards?" from the positive direction in the horizontal plane, where the positive
	 * direction is defined by the horizontal angle.
	 * 
	 * <p>?Note that the "upwards" direction will not always be up. It will be up when the vertical angle resides in the first or fourth quadrant, and down when
	 * the vertical angle resides in the second or third quadrant, in the manner one would expect from increasing any angle.
	 * 
	 * @param radians Radian value to rotate by.
	 * @return this with updated values.
	 */
	public Pos3d rotateAroundX(double radians)
	{
		toCartesian();
		
		double tempMag = Math.sqrt((z*z) + (y*y));
		double angle = Math.atan2(z, y);
		
		angle -= radians;
		
		z = tempMag * Math.sin(angle);
		y = tempMag * Math.cos(angle);
		
		return this;
	}
	
	/**
	 * Rotates this around X-axis (clean version).
	 * 
	 * <p>All axis-based rotations rotate counter-clockwise around the axis, when said axis is viewed from the positive end looking towards the negative end.
	 * Vertical rotation (which is not axis-based) rotates "upwards?" from the positive direction in the horizontal plane, where the positive
	 * direction is defined by the horizontal angle.
	 * 
	 * <p>?Note that the "upwards" direction will not always be up. It will be up when the vertical angle resides in the first or fourth quadrant, and down when
	 * the vertical angle resides in the second or third quadrant, in the manner one would expect from increasing any angle.
	 * 
	 * @param radians Radian value to rotate by.
	 * @return new Pos3d with updated values.
	 */
	public Pos3d rotateAroundXClean(double radians)
	{
		return new Pos3d(this).rotateAroundX(radians);
	}
	
	/**
	 * Rotates this around Y-axis.
	 * 
	 * <p>All axis-based rotations rotate counter-clockwise around the axis, when said axis is viewed from the positive end looking towards the negative end.
	 * Vertical rotation (which is not axis-based) rotates "upwards?" from the positive direction in the horizontal plane, where the positive
	 * direction is defined by the horizontal angle.
	 * 
	 * <p>?Note that the "upwards" direction will not always be up. It will be up when the vertical angle resides in the first or fourth quadrant, and down when
	 * the vertical angle resides in the second or third quadrant, in the manner one would expect from increasing any angle.
	 * 
	 * @param radians Radian value to rotate by.
	 * @return this with updated values.
	 */
	public Pos3d rotateAroundY(double radians)
	{
		toCartesian();
		
		double tempMag = Math.sqrt((x*x) + (z*z));
		double angle = Math.atan2(z, x);
		
		angle += radians;
		
		z = tempMag * Math.sin(angle);
		x = tempMag * Math.cos(angle);
		
		return this;
	}
	
	/**
	 * Rotates this around Z-axis (clean version).
	 * 
	 * <p>All axis-based rotations rotate counter-clockwise around the axis, when said axis is viewed from the positive end looking towards the negative end.
	 * Vertical rotation (which is not axis-based) rotates "upwards?" from the positive direction in the horizontal plane, where the positive
	 * direction is defined by the horizontal angle.
	 * 
	 * <p>?Note that the "upwards" direction will not always be up. It will be up when the vertical angle resides in the first or fourth quadrant, and down when
	 * the vertical angle resides in the second or third quadrant, in the manner one would expect from increasing any angle.
	 * 
	 * @param radians Radian value to rotate by.
	 * @return new Pos3d with updated values.
	 */
	public Pos3d rotateAroundZClean(double radians)
	{
		return new Pos3d(this).rotateAroundZ(radians);
	}
	
	/**
	 * Rotates this around arbitrary axis.
	 *
	 * <p>All axis-based rotations rotate counter-clockwise around the axis, when said axis is viewed from the positive end looking towards the negative end.
	 * Vertical rotation (which is not axis-based) rotates "upwards?" from the positive direction in the horizontal plane, where the positive
	 * direction is defined by the horizontal angle.
	 * 
	 * <p>?Note that the "upwards" direction will not always be up. It will be up when the vertical angle resides in the first or fourth quadrant, and down when
	 * the vertical angle resides in the second or third quadrant, in the manner one would expect from increasing any angle.
	 * 
	 * @param axis One endpoint of the axis of rotation, the other is the origin.
	 * @param radians Radian value to rotate by.
	 * @return this with updated values.
	 */
	public Pos3d rotateAroundAxis(Pos3d axis, double radians)
	{
		double axisAng1 = axis.toSpherical().ang1;
		double axisAng2 = axis.ang2;
		
		toCartesian();
		rotateAroundZ(-axisAng1);
		rotateAroundX(-((Math.PI / 2) - axisAng2));
		rotateAroundZ(radians);
		rotateAroundX(((Math.PI / 2) - axisAng2));
		rotateAroundZ(axisAng1);
		
		return this;
	}
	
	/**
	 * Rotates this around arbitrary axis (clean version).
	 * 
	 * <p>All axis-based rotations rotate counter-clockwise around the axis, when said axis is viewed from the positive end looking towards the negative end.
	 * Vertical rotation (which is not axis-based) rotates "upwards?" from the positive direction in the horizontal plane, where the positive
	 * direction is defined by the horizontal angle.
	 * 
	 * <p>?Note that the "upwards" direction will not always be up. It will be up when the vertical angle resides in the first or fourth quadrant, and down when
	 * the vertical angle resides in the second or third quadrant, in the manner one would expect from increasing any angle.
	 * 
	 * @param axis One endpoint of the axis of rotation, the other is the origin.
	 * @param radians Radian value to rotate by.
	 * @return new Pos3d with updated values.
	 */
	public Pos3d rotateAroundAxisClean(Pos3d axis, double radians)
	{
		return new Pos3d(this).rotateAroundAxis(axis, radians);
	}
	
	/**
	 * Sets the Cartesian x value of this.
	 * @param x Value to set x to.
	 * @return this with updated values.
	 */
	public Pos3d setX(double x)
	{
		toCartesian().x = x;
		return this;
	}
	
	/**
	 * Sets the Cartesian y value of this.
	 * @param z Value to set z to.
	 * @return this with updated values.
	 */
	public Pos3d setZ(double z)
	{
		if (system == SPHERICAL) { toCylindrical(); }
		this.z = z;
		return this;
	}
	
	/**
	 * Sets the Cartesian z value of this.
	 * @param y Value to set y to.
	 * @return this with updated values.
	 */
	public Pos3d setY(double y)
	{
		toCartesian().y = y;
		return this;
	}
	
	/**
	 * Sets the Cartesian x value of this (clean version).
	 * @param x Value to set x to.
	 * @return new Pos3d with updated values.
	 */
	public Pos3d setXClean(double x)
	{
		return new Pos3d(this).setX(x);
	}
	
	/**
	 * Sets the Cartesian y value of this (clean version).
	 * @param y Value to set y to.
	 * @return new Pos3d with updated values.
	 */
	public Pos3d setYClean(double y)
	{
		return new Pos3d(this).setY(y);
	}
	
	/**
	 * Sets the Cartesian z value of this (clean version).
	 * @param z Value to set z to.
	 * @return new Pos3d with updated values.
	 */
	public Pos3d setZClean(double z)
	{
		return new Pos3d(this).setZ(z);
	}
	
	/**
	 * Sets the rotation around the z axis of this.
	 * @param ang1 Value to set ang1 to.
	 * @return this with updated values.
	 */
	public Pos3d setAng1(double ang1)
	{
		if (system == CARTESIAN)
		{
			toCylindrical();
		}
		this.ang1 = ang1;
		return this;
	}
	
	/**
	 * Sets the rotation from the horizontal of this.
	 * @param ang2 Value to set ang2 to.
	 * @return this with updated values.
	 */
	public Pos3d setAng2(double ang2)
	{
		if (system != SPHERICAL)
		{
			toSpherical();
		}
		this.ang2 = ang2;
		return this;
	}
	
	/**
	 * Sets the rotation around the y axis of this (clean version).
	 * @param ang1 Value to set ang1 to.
	 * @return new Pos3d with updated values.
	 */
	public Pos3d setAng1Clean(double ang1)
	{
		return new Pos3d(this).setAng1(ang1);
	}
	
	/**
	 * Sets the rotation from the horizontal of this (clean version).
	 * @param ang2 Value to set ang2 to.
	 * @return new Pos3d with updated values.
	 */
	public Pos3d setAng2Clean(double ang2)
	{
		return new Pos3d(this).setAng2(ang2);
	}
	
	/**
	 * Mirrors this across the ZX plane.
	 * @return this with updated values.
	 */
	public Pos3d mirrorAcrossZX()
	{
		if (system != CARTESIAN)
		{
			ang1 = -(ang1 + Math.PI);
		}
		else
		{
			y = -y;
		}
		return this;
	}
	
	/**
	 * Mirrors this across the YZ plane.
	 * @return this with updated values.
	 */
	public Pos3d mirrorAcrossYZ()
	{
		if (system != CARTESIAN)
		{
			ang1 = -ang1;
		}
		else
		{
			x = -x;
		}
		return this;
	}
	
	/**
	 * Mirrors this across the XY plane.
	 * @return this with updated values.
	 */
	public Pos3d mirrorAcrossXY()
	{
		if (system != SPHERICAL)
		{
			z = -z;
		}
		else
		{
			ang2 = -ang2;
		}
		return this;
	}
	
	/**
	 * Mirrors this across the XY plane (clean version).
	 * @return new Pos3d with updated values.
	 */
	public Pos3d mirrorAcrossXYClean()
	{
		return new Pos3d(this).mirrorAcrossXY();
	}
	
	/**
	 * Mirrors this across the YZ plane (clean version).
	 * @return new Pos3d with updated values.
	 */
	public Pos3d mirrorAcrossYZClean()
	{
		return new Pos3d(this).mirrorAcrossYZ();
	}
	
	/**
	 * Mirrors this across the ZX plane (clean version).
	 * @return new Pos3d with updated values.
	 */
	public Pos3d mirrorAcrossZXClean()
	{
		return new Pos3d(this).mirrorAcrossZX();
	}
	
	/**
	 * Mirrors this across the arbitrary plane defined by the two parameters and the origin.
	 * @param planePoint1 First point in the plane that will be mirrored across.
	 * @param planePoint2 Second point in the plane that will be mirrored across.
	 * @return this with updated values.
	 */
	public Pos3d mirrorAcrossPlane(Pos3d planePoint1, Pos3d planePoint2)
	{
		Pos3d temp = new Pos3d().setTo(planePoint1);
		Pos3d temp2 = new Pos3d().setTo(planePoint2);
		double pp1Ang1 = temp.toSpherical().ang1;
		
		toSpherical().rotateAroundZ(-pp1Ang1);
		temp.toSpherical().rotateAroundZ(-pp1Ang1);
		temp2.toSpherical().rotateAroundZ(-pp1Ang1);
		
		toCartesian().rotateAroundX(-temp.getAngX());
		temp2.toCartesian().rotateAroundX(-temp.getAngX());
		
		rotateAroundY(-temp.getAngY());
		
		mirrorAcrossXY();
		
		rotateAroundY(temp.getAngY());
		
		rotateAroundX(temp.getAngX());
		
		rotateAroundY(pp1Ang1);
		
		return this;
	}
	
	/**
	 * Mirrors this across the arbitrary plane defined by the two parameters and the origin (clean version).
	 * @param planePoint1 First point in the plane that will be mirrored across.
	 * @param planePoint2 Second point in the plane that will be mirrored across.
	 * @return new Pos3d with updated values.
	 */
	public Pos3d mirrorAcrossPlaneClean(Pos3d planePoint1, Pos3d planePoint2)
	{
		return new Pos3d(this).mirrorAcrossPlane(planePoint1, planePoint2);
	}
}