/*
 * ============================================================================
 * GNU General Public License
 * ============================================================================
 *
 * Copyright (C) 2006-2011 Serotonin Software Technologies Inc. http://serotoninsoftware.com
 * @author Matthew Lohbihler
 * 
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.serotonin.bacnet4j.type.enumerated;

import com.serotonin.bacnet4j.type.primitive.Enumerated;
import com.serotonin.util.queue.ByteQueue;

/**
 * @author Matthew Lohbihler
 */
public class DoorValue extends Enumerated {
    private static final long serialVersionUID = -2200245400075159155L;
    public static final DoorValue lock = new DoorValue(0);
    public static final DoorValue unlock = new DoorValue(1);
    public static final DoorValue pulseUnlock = new DoorValue(2);
    public static final DoorValue extendedPulseUnlock = new DoorValue(3);

    public DoorValue(int value) {
        super(value);
    }

    public DoorValue(ByteQueue queue) {
        super(queue);
    }
}
