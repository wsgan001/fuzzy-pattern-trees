/*
 *    CachedInstancesStream.java
 *    Copyright (C) 2007 University of Waikato, Hamilton, New Zealand
 *    @author Richard Kirkby (rkirkby@cs.waikato.ac.nz)
 *
 *    This program is free software; you can redistribute it and/or modify
 *    it under the terms of the GNU General Public License as published by
 *    the Free Software Foundation; either version 3 of the License, or
 *    (at your option) any later version.
 *
 *    This program is distributed in the hope that it will be useful,
 *    but WITHOUT ANY WARRANTY; without even the implied warranty of
 *    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *    GNU General Public License for more details.
 *
 *    You should have received a copy of the GNU General Public License
 *    along with this program. If not, see <http://www.gnu.org/licenses/>.
 *    
 */
package moa.streams;

import java.util.Random;

import moa.AbstractMOAObject;
import moa.core.InstancesHeader;
import weka.core.Instance;
import weka.core.Instances;

/**
 * Stream generator for representing a stream that is cached in memory.
 * This generator is used with the task <code>CacheShuffledStream</code> that
 * stores and shuffles examples in memory.
 *
 * @author Richard Kirkby (rkirkby@cs.waikato.ac.nz)
 * @version $Revision: 7 $
 */
public class CachedNoReplacementInstancesStream extends AbstractMOAObject implements
		InstanceStream {

	private static final long serialVersionUID = 1L;

	protected Instances toStream;

	protected int streamPos;
	
	protected int randomSeed;

	protected Random instanceRandom;
	
	public CachedNoReplacementInstancesStream(Instances toStream, int seed) {
		this.toStream = toStream;
		this.randomSeed = seed ;
		this.instanceRandom = new Random(seed);
	}

    @Override
	public InstancesHeader getHeader() {
		return new InstancesHeader(this.toStream);
	}

    @Override
	public long estimatedRemainingInstances() {		
    	return this.toStream.numInstances() ;
    	//return this.toStream.numInstances() - this.streamPos;
	}

    @Override
	public boolean hasMoreInstances() {
		return true ;
    	//return this.streamPos < this.toStream.numInstances();
    	
	}

    @Override
	public Instance nextInstance() {
    	try{
    	int index=this.instanceRandom.nextInt(this.toStream.numInstances()) ;
    	Instance instance =this.toStream.instance(index);
    	this.toStream.remove(index) ;
		return instance ;
    	}catch (Exception ex)
    	{
    		ex.printStackTrace() ;
    		return null ;
    	}
		//return this.toStream.instance(this.instanceRandom.nextInt(this.toStream.numInstances()));
	}

    @Override
	public boolean isRestartable() {
		return true;
	}

    @Override
	public void restart() {
		this.instanceRandom = new Random(randomSeed);
//		this.streamPos = 0;
	}

    @Override
	public void getDescription(StringBuilder sb, int indent) {
		// TODO Auto-generated method stub

	}

}