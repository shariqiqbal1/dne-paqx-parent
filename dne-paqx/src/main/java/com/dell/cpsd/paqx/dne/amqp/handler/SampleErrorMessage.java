/**
 * Copyright &copy; 2017 Dell Inc. or its subsidiaries. All Rights Reserved. Dell EMC Confidential/Proprietary Information
 */

package com.dell.cpsd.paqx.dne.amqp.handler;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import com.dell.cpsd.MessageProperties;
import com.dell.cpsd.contract.extension.amqp.annotation.Message;
import com.dell.cpsd.contract.extension.amqp.message.HasErrors;
import com.dell.cpsd.contract.extension.amqp.message.HasMessageProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyDescription;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

/**
 * Sample Error message
 * <p>
 * The message is sent when there is an error in the discovery of nodes
 * 
 * <p>
 * Copyright &copy; 2017 Dell Inc. or its subsidiaries. All Rights Reserved. Dell EMC Confidential/Proprietary Information
 * </p>
 * 
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@Message(value = "com.dell.cpsd.dne.paqx.error", version = "1.0")
@JsonPropertyOrder({"messageProperties", "errors"})
public class SampleErrorMessage implements HasErrors<Error>, HasMessageProperties<MessageProperties>
{

    /**
     * AMQP properties properties
     * <p>
     * 
     * 
     * Corresponds to the "messageProperties" property.AMQP properties. (Required)
     * 
     */
    @JsonProperty("messageProperties")
    @JsonPropertyDescription("AMQP properties.")
    @Valid
    @NotNull
    private MessageProperties messageProperties;
    /**
     * 
     * (Required)
     * 
     */
    @JsonProperty("errors")
    @Valid
    @NotNull
    private List<Error>       errors = new ArrayList<Error>();

    /**
     * No args constructor for use in serialization
     * 
     */
    public SampleErrorMessage()
    {
    }

    /**
     * 
     * @param messageProperties
     * @param errors
     */
    public SampleErrorMessage(MessageProperties messageProperties, List<Error> errors)
    {
        super();
        this.messageProperties = messageProperties;
        this.errors = errors;
    }

    /**
     * AMQP properties properties
     * <p>
     * 
     * 
     * Corresponds to the "messageProperties" property.AMQP properties. (Required)
     * 
     */
    @JsonProperty("messageProperties")
    public MessageProperties getMessageProperties()
    {
        return messageProperties;
    }

    /**
     * AMQP properties properties
     * <p>
     * 
     * 
     * Corresponds to the "messageProperties" property.AMQP properties. (Required)
     * 
     */
    @JsonProperty("messageProperties")
    public void setMessageProperties(MessageProperties messageProperties)
    {
        this.messageProperties = messageProperties;
    }

    public SampleErrorMessage withMessageProperties(MessageProperties messageProperties)
    {
        this.messageProperties = messageProperties;
        return this;
    }

    /**
     * 
     * (Required)
     * 
     */
    @JsonProperty("errors")
    public List<Error> getErrors()
    {
        return errors;
    }

    /**
     * 
     * (Required)
     * 
     */
    @JsonProperty("errors")
    public void setErrors(List<Error> errors)
    {
        this.errors = errors;
    }

    public SampleErrorMessage withErrors(List<Error> errors)
    {
        this.errors = errors;
        return this;
    }

    @Override
    public String toString()
    {
        return ToStringBuilder.reflectionToString(this);
    }

    @Override
    public int hashCode()
    {
        return new HashCodeBuilder().append(messageProperties).append(errors).toHashCode();
    }

    @Override
    public boolean equals(Object other)
    {
        if (other == this)
        {
            return true;
        }
        if ((other instanceof SampleErrorMessage) == false)
        {
            return false;
        }
        SampleErrorMessage rhs = ((SampleErrorMessage) other);
        return new EqualsBuilder().append(messageProperties, rhs.messageProperties).append(errors, rhs.errors).isEquals();
    }

}
