/*
 * The Apache Software License, Version 1.1
 *
 *
 * Copyright (c) 2000 The Apache Software Foundation.  All rights 
 * reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions
 * are met:
 *
 * 1. Redistributions of source code must retain the above copyright
 *    notice, this list of conditions and the following disclaimer. 
 *
 * 2. Redistributions in binary form must reproduce the above copyright
 *    notice, this list of conditions and the following disclaimer in
 *    the documentation and/or other materials provided with the
 *    distribution.
 *
 * 3. The end-user documentation included with the redistribution,
 *    if any, must include the following acknowledgment:  
 *       "This product includes software developed by the
 *        Apache Software Foundation (http://www.apache.org/)."
 *    Alternately, this acknowledgment may appear in the software itself,
 *    if and wherever such third-party acknowledgments normally appear.
 *
 * 4. The names "SOAP" and "Apache Software Foundation" must
 *    not be used to endorse or promote products derived from this
 *    software without prior written permission. For written 
 *    permission, please contact apache@apache.org.
 *
 * 5. Products derived from this software may not be called "Apache",
 *    nor may "Apache" appear in their name, without prior written
 *    permission of the Apache Software Foundation.
 *
 * THIS SOFTWARE IS PROVIDED ``AS IS'' AND ANY EXPRESSED OR IMPLIED
 * WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES
 * OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 * DISCLAIMED.  IN NO EVENT SHALL THE APACHE SOFTWARE FOUNDATION OR
 * ITS CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL,
 * SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT
 * LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF
 * USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND
 * ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY,
 * OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT
 * OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF
 * SUCH DAMAGE.
 * ====================================================================
 *
 * This software consists of voluntary contributions made by many
 * individuals on behalf of the Apache Software Foundation and was
 * originally based on software copyright (c) 2000, International
 * Business Machines, Inc., http://www.apache.org.  For more
 * information on the Apache Software Foundation, please see
 * <http://www.apache.org/>.
 */

package org.apache.soap;

/**
 * <em>SOAP</em> Exceptions.
 *
 * @author Sanjiva Weerawarana (sanjiva@watson.ibm.com)
 * @author Matthew J. Duftler (duftler@us.ibm.com)
 */
public class SOAPException extends Exception {
  private String faultCode;
  private Throwable targetException;

  public SOAPException (String faultCode, String msg) {
    super (Utils.cleanString (msg));
    this.faultCode = Utils.cleanString (faultCode);
  }

  public SOAPException (String faultCode, String msg,
                        Throwable targetException) {
    this (faultCode, msg);
    this.targetException = targetException;
  }

  public void setFaultCode (String faultCode) {
    this.faultCode = Utils.cleanString (faultCode);
  }

  public String getFaultCode () {
    return faultCode;
  }

  public void setTargetException (Throwable targetException) {
    this.targetException = targetException;
  }

  public Throwable getTargetException () {
    return targetException;
  }

  public Throwable getRootException() {
	return targetException != null ? targetException : this;
  }

  public String getMessage () {
    String superMsg = super.getMessage ();
    String targetMsg = (targetException != null)
                       ? targetException.getMessage ()
                       : null;
    String msg = ((superMsg == null || superMsg.equals (""))
                  && (targetMsg != null && !targetMsg.equals ("")))
                 ? targetMsg
                 : superMsg;

    if (msg == null || msg.equals ("")) {
      msg = (targetException != null)
            ? targetException.toString()
            : this.toString();
    }

    return msg;
  }

  public String toString () {
    return "[SOAPException: faultCode=" + faultCode + "; msg=" +
           super.getMessage () + ((targetException != null)
           ? ("; targetException=" + targetException) : "") + "]";
  }
}
