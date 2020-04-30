/*
 * $Header: /cvsroot/main/PATCHED/commons-httpclient-3.0/src/java/org/apache/commons/httpclient/methods/PutMethod.java,v 1.1 2007-08-07 21:54:22 sereda Exp $
 * $Revision: 1.1 $
 * $Date: 2007-08-07 21:54:22 $
 *
 * ====================================================================
 *
 *  Copyright 1999-2004 The Apache Software Foundation
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 * ====================================================================
 *
 * This software consists of voluntary contributions made by many
 * individuals on behalf of the Apache Software Foundation.  For more
 * information on the Apache Software Foundation, please see
 * <http://www.apache.org/>.
 *
 */

package org.apache.commons.httpclient.methods;

/**
 * Implements the HTTP PUT method.
 * <p>
 * The HTTP PUT method is defined in section 9.6 of 
 * <a href="http://www.ietf.org/rfc/rfc2616.txt">RFC2616</a>:
 * <blockquote>
 * The PUT method requests that the enclosed entity be stored under the
 * supplied Request-URI. If the Request-URI refers to an already
 * existing resource, the enclosed entity SHOULD be considered as a
 * modified version of the one residing on the origin server. 
 * </blockquote>
 * </p>
 * 
 * @author <a href="mailto:remm@apache.org">Remy Maucherat</a>
 * @author <a href="mailto:mbowler@GargoyleSoftware.com">Mike Bowler</a>
 * @author <a href="mailto:oleg@ural.ru">Oleg Kalnichevski</a>
 * @author <a href="mailto:jsdever@apache.org">Jeff Dever</a>
 *
 * @version $Revision: 1.1 $
 * @since 1.0
 */
public class PutMethod extends EntityEnclosingMethod {

    // ----------------------------------------------------------- Constructors

    /**
     * No-arg constructor.
     *
     * @since 1.0
     */
    public PutMethod() {
        super();
    }


    /**
     * Constructor specifying a URI.
     *
     * @param uri either an absolute or relative URI
     *
     * @since 1.0
     */
    public PutMethod(String uri) {
        super(uri);
    }

    // --------------------------------------------------------- Public Methods

    /**
     * Return <tt>"PUT"</tt>.
     * @return <tt>"PUT"</tt>
     *
     * @since 2.0
     */
    public String getName() {
        return "PUT";
    }
}
