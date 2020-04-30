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

package org.apache.soap.util.mime;

import java.io.*;
import java.util.*;
import java.net.*;
import org.apache.soap.*;
import javax.mail.internet.*;

/**
 * Mime-related utility functions.
 *
 * @author Wouter Cloetens (wouter@mind.be)
 */

public class MimeUtils {
    /**
     * Get a unique value.
     *
     * Similar to javax.mail.internet.UniqueValue, this implementation
     * generates a unique value by concatenating a newly
     * created object's <code>hashCode()</code>, the current
     * time (in milliseconds), and this system's hostname generated by
     * <code>InternetAddress.getLocalAddress()</code>.
     */
    public static String getUniqueValue() {
        String host = null;
        try {
            host = InetAddress.getLocalHost().getHostName();
        } catch (UnknownHostException uhe) {
            host = "localhost";
        }

        StringBuffer s = new StringBuffer();

        // Unique string is <hashcode>.<currentTime>.apache-soap.<hostname>
        s.append(s.hashCode()).append('.').append(System.currentTimeMillis()).
            append(".apache-soap.").append(host);
        return s.toString();
    }

    /**
     * Extract a Java encoding from a Content-Type's MIME character set.
     */
    public static String getEncoding(String type, String defaultEncoding) {
        String encoding = null;
        try {
        if (type != null && !type.equals(""))
            encoding = new ContentType(type).getParameter("charset");
        } catch(ParseException pe) {
        }
        if (encoding == null)
            encoding = defaultEncoding;
        else
            encoding = MimeUtility.javaCharset(encoding);

        return encoding;
    }

    /**
     * URLDecode string.
     */
    private static final String hexmap = "0123456789ABCDEF";
    public static String decode(String s) {
        StringBuffer ret = new StringBuffer();
        char c;
        for (int i = 0; i < s.length(); i++) {
            c = s.charAt(i);
            switch(c) {
            case '+':
                ret.append(' ');
                break;
            case '%':
                ret.append((char)((hexmap.indexOf(s.charAt(++i)) << 4)
                                  + hexmap.indexOf(s.charAt(++i))));
                break;
            default:
                ret.append(c);
            }
        }
        return ret.toString();
    }
}
