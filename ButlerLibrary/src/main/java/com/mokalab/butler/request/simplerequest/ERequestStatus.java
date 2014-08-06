package com.mokalab.butler.request.simplerequest;

/**
 * Created by Pirdad Sakhizada on 29/11/13.
 */
public enum ERequestStatus {

    // STATUS FOR REST APIS
    UNSUPPORTED_ENCODING_ERROR,
    CLIENT_PROTOCOL_ERROR,

    // STATUS FOR SOAP APIS?
    RESPONSE_NULL,
    XML_PULL_PARSER_ERROR,

    // STATUS FOR APP SPECIFIC
    NONE,
    CATCH_ALL_ERROR,
    TIMEDOUT,
    NO_NETWORK,
    PARSE_FAILURE,
    IO_ERROR,
    CANCELLED;
}
