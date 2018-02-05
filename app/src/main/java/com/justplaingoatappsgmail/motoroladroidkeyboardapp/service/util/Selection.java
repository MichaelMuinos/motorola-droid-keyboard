package com.justplaingoatappsgmail.motoroladroidkeyboardapp.service.util;

/**
 * Selection represents the status of the alt or caps button.
 * OFF - the button is not in use
 * ONCE - the button will be used once, then turned to OFF
 * FOREVER - the button will be used for as long as the user wants
 */
public enum Selection {
    OFF,
    ONCE,
    FOREVER
}
