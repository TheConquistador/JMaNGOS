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
package org.jmangos.realm.network.packet.wow.client;

import java.nio.BufferUnderflowException;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import org.criteria4jpa.criterion.Criterion;
import org.criteria4jpa.criterion.Restrictions;
import org.jmangos.commons.model.AccountInfo;
import org.jmangos.commons.network.sender.AbstractPacketSender;
import org.jmangos.realm.entities.CharacterEntity;
import org.jmangos.realm.network.packet.wow.AbstractWoWClientPacket;
import org.jmangos.realm.network.packet.wow.server.SMSG_CHAR_ENUM;
import org.jmangos.realm.services.CharacterService;
import org.springframework.stereotype.Component;

/**
 * The Class CMSG_CHAR_ENUM.
 */
@Component
public class CMSG_CHAR_ENUM extends AbstractWoWClientPacket {
    
    /** The sender. */
    @Inject
    @Named("nettyPacketSender")
    private AbstractPacketSender sender;
    
    @Inject
    private CharacterService     characterService;
    
    @Override
    protected void readImpl() throws BufferUnderflowException, RuntimeException {
    
        // nothing to read
    }
    
    @Override
    protected void runImpl() {
    
        final AccountInfo account = (AccountInfo) getClient().getChanneledObject();
        final Criterion criterion = Restrictions.eq("account", account.getObjectId());
        final List<CharacterEntity> characters = this.characterService.readCharacters(criterion);
        this.sender.send(getClient(), new SMSG_CHAR_ENUM(characters));
    }
    
}
