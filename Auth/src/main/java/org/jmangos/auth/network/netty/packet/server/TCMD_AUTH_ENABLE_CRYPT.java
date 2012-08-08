/*******************************************************************************
 * Copyright (C) 2012 JMaNGOS <http://jmangos.org/>
 *
 * This program is free software; you can redistribute it and/or modify it
 * under the terms of the GNU General Public License as published by the
 * Free Software Foundation; either version 2 of the License, or (at your
 * option) any later version.
 *
 * This program is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE. See the GNU General Public License for
 * more details.
 *
 * You should have received a copy of the GNU General Public License along
 * with this program. If not, see <http://www.gnu.org/licenses/>.
 *******************************************************************************/
package org.jmangos.auth.network.netty.packet.server;

import org.apache.log4j.Logger;
import org.jmangos.auth.network.netty.packet.AbstractWoWServerPacket;

/**
 * The Class <tt>TCMD_AUTH_ENABLE_CRYPT</tt>.
 */
public class TCMD_AUTH_ENABLE_CRYPT extends AbstractWoWServerPacket {

	/** The Constant log. */
	@SuppressWarnings("unused")
	private static final Logger log = Logger.getLogger(TCMD_AUTH_ENABLE_CRYPT.class);

	/**
	 * Instantiates a new <tt>TCMD_AUTH_ENABLE_CRYPT</tt>.
	 */
	public TCMD_AUTH_ENABLE_CRYPT() {
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see org.jmangos.common.network.model.SendablePacket#writeImpl()
	 */
	@Override
	protected void writeImpl() {
		
	}
}