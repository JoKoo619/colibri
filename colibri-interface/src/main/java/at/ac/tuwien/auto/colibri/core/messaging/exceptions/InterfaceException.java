/**************************************************************************************************
 * Copyright (c) 2016, Automation Systems Group, Institute of Computer Aided Automation, TU Wien
 * All rights reserved.
 * 
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions
 * are met:
 * 1. Redistributions of source code must retain the above copyright
 *    notice, this list of conditions and the following disclaimer.
 * 2. Redistributions in binary form must reproduce the above copyright
 *    notice, this list of conditions and the following disclaimer in the
 *    documentation and/or other materials provided with the distribution.
 * 3. Neither the name of the Institute nor the names of its contributors
 *    may be used to endorse or promote products derived from this software
 *    without specific prior written permission.
 * 
 * THIS SOFTWARE IS PROVIDED BY THE INSTITUTE AND CONTRIBUTORS "AS IS" AND
 * ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE
 * IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
 * ARE DISCLAIMED.  IN NO EVENT SHALL THE INSTITUTE OR CONTRIBUTORS BE LIABLE
 * FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL
 * DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS
 * OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION)
 * HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT
 * LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY
 * OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF
 * SUCH DAMAGE.
 *************************************************************************************************/

package at.ac.tuwien.auto.colibri.core.messaging.exceptions;

import at.ac.tuwien.auto.colibri.core.messaging.Peer;
import at.ac.tuwien.auto.colibri.core.messaging.types.Message;
import at.ac.tuwien.auto.colibri.core.messaging.types.Status;
import at.ac.tuwien.auto.colibri.core.messaging.types.Status.Code;
import at.ac.tuwien.auto.colibri.core.messaging.types.StatusImpl;

/**
 * Generic exception for errors occurring in the interface and message handling.
 */
public abstract class InterfaceException extends Exception
{
	private static final long serialVersionUID = 1L;

	/**
	 * Erroneous message
	 */
	private Message message = null;

	/**
	 * Associated peer
	 */
	private Peer peer = null;

	/**
	 * Constructor for initialization.
	 * 
	 * @param text Error message
	 * @param message Referenced message
	 */
	public InterfaceException(String text, Message message)
	{
		super(text);

		this.message = message;
		this.peer = message.getPeer();
	}

	/**
	 * Constructor for initialization.
	 * 
	 * @param message Error message
	 * @param peer Peer reference
	 */
	public InterfaceException(String text, Peer peer)
	{
		super(text);

		this.peer = peer;
	}

	/**
	 * Returns the status code of the exception.
	 * 
	 * @return
	 */
	protected abstract Code getCode();

	/**
	 * Returns the STA message that corresponds to the exception.
	 * 
	 * @return
	 */
	public Status getStatus()
	{
		Status message = new StatusImpl(this.getCode(), this.getMessage());

		message.setReference(this.message);
		message.setPeer(this.peer);

		return message;
	}
}
