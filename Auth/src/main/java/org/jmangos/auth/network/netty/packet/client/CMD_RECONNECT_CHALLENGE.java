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
package org.jmangos.auth.network.netty.packet.client;

import javax.inject.Inject;
import javax.inject.Named;

import org.jmangos.auth.network.netty.packet.AbstractWoWClientPacket;
import org.jmangos.auth.network.netty.packet.server.TCMD_RECONNECT_CHALLENGE;
import org.jmangos.auth.service.AccountService;
import org.jmangos.commons.network.model.NettyNetworkChannel;
import org.jmangos.commons.network.netty.sender.AbstractPacketSender;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * The Class <tt>CMD_RECONNECT_CHALLENGE</tt>.
 */
@Component
public class CMD_RECONNECT_CHALLENGE extends AbstractWoWClientPacket {
    
    /** The Constant logger. */
    @SuppressWarnings("unused")
    private static final Logger  logger = LoggerFactory.getLogger(CMD_RECONNECT_CHALLENGE.class);
    
    /** The sender. */
    @Inject
    @Named("nettyPacketSender")
    private AbstractPacketSender sender;
    
    /** The account service. */
    @Inject
    AccountService               accountService;
    
    /** The login. */
    private String               login;
    
    /**
     * Instantiates a new CMD_RECONNECT_CHALLENGE.
     */
    public CMD_RECONNECT_CHALLENGE() {
    
        super();
    }
    
    /**
     * @see org.jmangos.commons.network.model.ReceivablePacket#getMinimumLength()
     */
    @Override
    public int getMinimumLength() {
    
        return 0;
    }
    
    /**
     * {@inheritDoc}
     */
    @SuppressWarnings("unused")
    @Override
    protected void readImpl() {
    
        readC(); // error
        readH(); // size
        readB(4); // gamename
        readC(); // version 3
        readC(); // version 5
        readC(); // version 5
        readH(); // client buildNr
        readB(4); // platform
        readB(4); // OS
        readB(4); // country (enUS)
        /* int timezone_bias = */readD();
        /* int ip = */readD();
        final int lenLogin = readC();
        this.login = new String(readB(lenLogin), 0, lenLogin);
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    protected void runImpl() {
    
        this.accountService.loadClean(this.login, (NettyNetworkChannel) getClient());
        this.sender.send(getClient(), new TCMD_RECONNECT_CHALLENGE());
    }
}
